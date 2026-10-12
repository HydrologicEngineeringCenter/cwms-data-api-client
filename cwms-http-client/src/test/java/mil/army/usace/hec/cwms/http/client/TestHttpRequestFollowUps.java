/*
 * MIT License
 *
 * Copyright (c) 2026 Hydrologic Engineering Center
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package mil.army.usace.hec.cwms.http.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import mil.army.usace.hec.cwms.http.client.request.CancellableHttpRequest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.SocketPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestHttpRequestFollowUps {

    @BeforeEach
    void quietLogging() {
        // body-level logging (left on by other tests) would read bodies inside execute()
        CwmsHttpLoggingInterceptor.getInstance().setLogLevel(Level.INFO);
    }

    private static HttpRequestBuilderImpl requestTo(MockWebServer server) throws IOException {
        return new HttpRequestBuilderImpl(new ApiConnectionInfoBuilder(server.url("/").toString()).build(), "data");
    }

    @Test
    void testSuccessStatusCodeExposed() throws Exception {
        try (MockWebServer server = new MockWebServer()) {
            server.enqueue(new MockResponse().setResponseCode(201).setBody("made"));
            server.enqueue(new MockResponse().setBody("ok"));
            server.start();
            try (HttpRequestResponse response = requestTo(server).get().execute()) {
                assertEquals(201, response.getStatusCode());
            }
            try (HttpRequestResponse response = requestTo(server).get().execute()) {
                assertEquals(200, response.getStatusCode());
            }
        }
    }

    @Test
    void testFailureHeadersExposedImmutableAndRepeated() throws Exception {
        try (MockWebServer server = new MockWebServer()) {
            server.enqueue(new MockResponse().setResponseCode(503).setBody("busy")
                .addHeader("Retry-After", "7")
                .addHeader("X-Multi", "a")
                .addHeader("X-Multi", "b"));
            server.enqueue(new MockResponse().setResponseCode(404).setBody("none").addHeader("Retry-After", "1"));
            server.start();

            CwmsHttpResponseException ex = assertThrows(CwmsHttpResponseException.class, () -> requestTo(server).get().execute());
            assertEquals(503, ex.getErrorCode());
            assertEquals("busy", ex.getResponseBody().orElseThrow());
            assertEquals(List.of("7"), ex.getHeaderValues("retry-after"));
            assertEquals(List.of("a", "b"), ex.getHeaderValues("X-MULTI"));
            assertTrue(ex.getHeaderValues("Absent").isEmpty());
            assertEquals(List.of("7"), ex.getHeaders().get("Retry-After"));
            assertThrows(UnsupportedOperationException.class, () -> ex.getHeaders().put("x", List.of()));
            assertThrows(UnsupportedOperationException.class, () -> ex.getHeaderValues("X-Multi").add("c"));

            NoDataFoundException notFound = assertThrows(NoDataFoundException.class, () -> requestTo(server).get().execute());
            assertEquals(List.of("1"), notFound.getHeaderValues("Retry-After"));
        }
    }

    @Test
    void testFailureStatusAndHeadersSurviveBodyReadFailure() throws Exception {
        try (MockWebServer server = new MockWebServer()) {
            server.enqueue(new MockResponse().setResponseCode(502).setStatus("HTTP/1.1 502 Bad Gateway")
                .addHeader("Retry-After", "3").setBody("0123456789")
                .setSocketPolicy(SocketPolicy.DISCONNECT_DURING_RESPONSE_BODY));
            server.enqueue(new MockResponse().setBody("after"));
            server.start();

            CwmsHttpResponseException ex = assertThrows(CwmsHttpResponseException.class, () -> requestTo(server).get().execute());
            assertEquals(502, ex.getErrorCode());
            assertEquals("Bad Gateway", ex.getReasonPhrase());
            assertEquals(List.of("3"), ex.getHeaderValues("Retry-After"));
            assertTrue(ex.getResponseBody().isEmpty());
            assertInstanceOf(IOException.class, ex.getCause());

            // the connection was cleaned up and the shared client is still usable
            try (HttpRequestResponse response = requestTo(server).get().execute()) {
                assertEquals("after", response.getBody());
            }
        }
    }

    @Test
    void testCancelBeforeExecuteSendsNothing() throws Exception {
        try (MockWebServer server = new MockWebServer()) {
            server.enqueue(new MockResponse().setBody("never"));
            server.start();
            CancellableHttpRequest request = requestTo(server).get().cancellable();
            assertFalse(request.isCancelled());
            request.cancel();
            request.cancel();
            assertTrue(request.isCancelled());
            assertThrows(IOException.class, request::execute);
            assertEquals(0, server.getRequestCount());
        }
    }

    @Test
    void testCancelInFlightFailsCallAndLeavesClientUsable() throws Exception {
        try (MockWebServer server = new MockWebServer()) {
            server.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.NO_RESPONSE));
            server.enqueue(new MockResponse().setBody("after"));
            server.start();
            CancellableHttpRequest request = requestTo(server)
                .withOptions(HttpRequestOptions.builder().timeout(Duration.ofSeconds(30)).build())
                .get().cancellable();
            ExecutorService executor = Executors.newSingleThreadExecutor();
            try {
                CountDownLatch started = new CountDownLatch(1);
                Future<Throwable> outcome = executor.submit(() -> {
                    started.countDown();
                    try (HttpRequestResponse ignored = request.execute()) {
                        return null;
                    } catch (Throwable t) {
                        return t;
                    }
                });
                assertTrue(started.await(5, TimeUnit.SECONDS));
                server.takeRequest(5, TimeUnit.SECONDS);
                request.cancel();
                Throwable failure = outcome.get(5, TimeUnit.SECONDS);
                assertInstanceOf(IOException.class, failure);
            } finally {
                executor.shutdownNow();
            }
            try (HttpRequestResponse response = requestTo(server).get().execute()) {
                assertEquals("after", response.getBody());
            }
        }
    }

    @Test
    void testCancellableRequestIsSingleUse() throws Exception {
        try (MockWebServer server = new MockWebServer()) {
            server.enqueue(new MockResponse().setBody("once"));
            server.start();
            CancellableHttpRequest request = requestTo(server).get().cancellable();
            try (HttpRequestResponse response = request.execute()) {
                assertEquals("once", response.getBody());
            }
            assertThrows(IllegalStateException.class, request::execute);
            request.cancel(); // cancelling a completed call has no effect and does not throw
        }
    }

    @Test
    void testPlainExecuteStillWorks() throws Exception {
        try (MockWebServer server = new MockWebServer()) {
            server.enqueue(new MockResponse().setBody("plain"));
            server.start();
            try (HttpRequestResponse response = requestTo(server).get().execute()) {
                assertEquals("plain", response.getBody());
            }
        }
    }
}
