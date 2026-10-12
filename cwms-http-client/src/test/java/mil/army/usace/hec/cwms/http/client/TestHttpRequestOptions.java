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

import static mil.army.usace.hec.cwms.http.client.TestApiConnectionInfo.getTestSslSocketFactory;
import static mil.army.usace.hec.cwms.http.client.TestApiConnectionInfo.getTestX509TrustManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.time.Duration;
import java.util.stream.Stream;
import javax.net.ssl.HostnameVerifier;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import okhttp3.mockwebserver.SocketPolicy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class TestHttpRequestOptions {

    private static final String TIMEOUT_PROPERTY_PREFIX = "cwms.http.client.";

    private static ApiConnectionInfo connectionFor(MockWebServer server) {
        return new ApiConnectionInfoBuilder(server.url("/").toString()).build();
    }

    private static HttpRequestBuilderImpl requestTo(ApiConnectionInfo info) throws IOException {
        return new HttpRequestBuilderImpl(info, "data");
    }

    @Test
    void testNullTimeoutRejected() {
        HttpRequestOptions.Builder builder = HttpRequestOptions.builder();
        assertThrows(NullPointerException.class, () -> builder.timeout(null));
        assertThrows(NullPointerException.class, () -> builder.callTimeout(null));
        assertThrows(NullPointerException.class, () -> builder.connectTimeout(null));
        assertThrows(NullPointerException.class, () -> builder.readTimeout(null));
        assertThrows(NullPointerException.class, () -> builder.writeTimeout(null));
    }

    @ParameterizedTest
    @MethodSource("invalidTimeouts")
    void testInvalidTimeoutRejected(Duration invalid) {
        HttpRequestOptions.Builder builder = HttpRequestOptions.builder();
        assertThrows(IllegalArgumentException.class, () -> builder.timeout(invalid));
        assertThrows(IllegalArgumentException.class, () -> builder.callTimeout(invalid));
        assertThrows(IllegalArgumentException.class, () -> builder.connectTimeout(invalid));
        assertThrows(IllegalArgumentException.class, () -> builder.readTimeout(invalid));
        assertThrows(IllegalArgumentException.class, () -> builder.writeTimeout(invalid));
    }

    private static Stream<Duration> invalidTimeouts() {
        return Stream.of(Duration.ZERO, Duration.ofSeconds(-1), Duration.ofNanos(500_000),
            Duration.ofMillis(Integer.MAX_VALUE).plusMillis(1), Duration.ofSeconds(Long.MAX_VALUE));
    }

    @Test
    void testTimeoutBoundsAccepted() {
        HttpRequestOptions min = HttpRequestOptions.builder().timeout(Duration.ofMillis(1)).build();
        HttpRequestOptions max = HttpRequestOptions.builder().timeout(Duration.ofMillis(Integer.MAX_VALUE)).build();
        assertEquals(Duration.ofMillis(1), min.callTimeout().orElseThrow());
        assertEquals(Duration.ofMillis(Integer.MAX_VALUE), max.writeTimeout().orElseThrow());
    }

    @Test
    void testWithOptionsRejectsNull() throws IOException {
        HttpRequestBuilderImpl builder = new HttpRequestBuilderImpl(new ApiConnectionInfoBuilder("http://localhost:1/").build());
        assertThrows(NullPointerException.class, () -> builder.withOptions(null));
    }

    @Test
    void testOptionsAreImmutableValues() {
        Duration timeout = Duration.ofSeconds(3);
        HttpRequestOptions.Builder builder = HttpRequestOptions.builder().timeout(timeout);
        HttpRequestOptions first = builder.build();
        builder.readTimeout(Duration.ofSeconds(9)).followRedirects(false);
        assertEquals(timeout, first.readTimeout().orElseThrow());
        assertTrue(first.followRedirects().isEmpty());
        assertEquals(HttpRequestOptions.builder().timeout(timeout).build(), first);
        assertEquals(HttpRequestOptions.builder().timeout(timeout).build().hashCode(), first.hashCode());
    }

    @Test
    void testDefaultsSetNothingAndLeaveClientUntouched() {
        HttpRequestOptions defaults = HttpRequestOptions.defaults();
        assertTrue(defaults.callTimeout().isEmpty());
        assertTrue(defaults.connectTimeout().isEmpty());
        assertTrue(defaults.readTimeout().isEmpty());
        assertTrue(defaults.writeTimeout().isEmpty());
        assertTrue(defaults.retryOnConnectionFailure().isEmpty());
        assertTrue(defaults.followRedirects().isEmpty());
        assertTrue(defaults.followSslRedirects().isEmpty());
        assertEquals(defaults, HttpRequestOptions.builder().build());
        OkHttpClient base = OkHttpClientInstance.getInstance();
        assertSame(base, defaults.applyTo(base));
    }

    @Test
    void testUnsetFieldsInheritClientValues() {
        OkHttpClient base = OkHttpClientInstance.getInstance();
        OkHttpClient derived = HttpRequestOptions.builder().followRedirects(false).build().applyTo(base);
        assertFalse(derived.followRedirects());
        assertEquals(base.followSslRedirects(), derived.followSslRedirects());
        assertEquals(base.retryOnConnectionFailure(), derived.retryOnConnectionFailure());
        assertEquals(base.callTimeoutMillis(), derived.callTimeoutMillis());
        assertEquals(base.connectTimeoutMillis(), derived.connectTimeoutMillis());
        assertEquals(base.readTimeoutMillis(), derived.readTimeoutMillis());
        assertEquals(base.writeTimeoutMillis(), derived.writeTimeoutMillis());
    }

    @Test
    void testAllOptionsAppliedWithoutMutatingSharedState() throws IOException {
        OkHttpClient shared = OkHttpClientInstance.getInstance();
        int call = shared.callTimeoutMillis();
        int connect = shared.connectTimeoutMillis();
        int read = shared.readTimeoutMillis();
        int write = shared.writeTimeoutMillis();
        boolean retry = shared.retryOnConnectionFailure();
        boolean redirects = shared.followRedirects();
        boolean sslRedirects = shared.followSslRedirects();
        String propertyBefore = System.getProperty(TIMEOUT_PROPERTY_PREFIX + "readtimeout.seconds");

        HttpRequestOptions options = HttpRequestOptions.builder()
            .callTimeout(Duration.ofMillis(1111))
            .connectTimeout(Duration.ofMillis(2222))
            .readTimeout(Duration.ofMillis(3333))
            .writeTimeout(Duration.ofMillis(4444))
            .retryOnConnectionFailure(false)
            .followRedirects(false)
            .followSslRedirects(false)
            .build();
        OkHttpClient derived = new HttpRequestBuilderImpl(new ApiConnectionInfoBuilder("http://localhost:1/").build())
            .withOptions(options).buildRequestClient();

        assertEquals(1111, derived.callTimeoutMillis());
        assertEquals(2222, derived.connectTimeoutMillis());
        assertEquals(3333, derived.readTimeoutMillis());
        assertEquals(4444, derived.writeTimeoutMillis());
        assertFalse(derived.retryOnConnectionFailure());
        assertFalse(derived.followRedirects());
        assertFalse(derived.followSslRedirects());

        assertSame(shared, OkHttpClientInstance.getInstance());
        assertEquals(call, shared.callTimeoutMillis());
        assertEquals(connect, shared.connectTimeoutMillis());
        assertEquals(read, shared.readTimeoutMillis());
        assertEquals(write, shared.writeTimeoutMillis());
        assertEquals(retry, shared.retryOnConnectionFailure());
        assertEquals(redirects, shared.followRedirects());
        assertEquals(sslRedirects, shared.followSslRedirects());
        assertEquals(propertyBefore, System.getProperty(TIMEOUT_PROPERTY_PREFIX + "readtimeout.seconds"));
    }

    @Test
    void testDerivedClientSharesPoolAndDispatcher() throws IOException {
        OkHttpClient shared = OkHttpClientInstance.getInstance();
        HttpRequestOptions options = HttpRequestOptions.builder().timeout(Duration.ofSeconds(2)).build();
        OkHttpClient first = new HttpRequestBuilderImpl(new ApiConnectionInfoBuilder("http://localhost:1/").build())
            .withOptions(options).buildRequestClient();
        OkHttpClient second = new HttpRequestBuilderImpl(new ApiConnectionInfoBuilder("http://localhost:1/").build())
            .buildRequestClient();
        assertNotSame(shared, first);
        assertSame(shared.connectionPool(), first.connectionPool());
        assertSame(shared.dispatcher(), first.dispatcher());
        assertSame(shared.connectionPool(), second.connectionPool());
        assertSame(shared.dispatcher(), second.dispatcher());
    }

    @Test
    void testApiConnectionInfoSettingsPreserved() throws IOException {
        HostnameVerifier hostnameVerifier = (hostname, session) -> true;
        CookieJarFactory.CookieJarSupplier cookieJar = CookieJarFactory.inMemoryCookieJar();
        ApiConnectionInfo info = new ApiConnectionInfoBuilder("http://localhost:1/")
            .withSslSocketData(new SslSocketData(getTestSslSocketFactory(), getTestX509TrustManager()))
            .withAuthorizationKeyProvider(() -> "key")
            .withCookieJarSupplier(cookieJar)
            .withHostnameVerifier(hostnameVerifier)
            .withCacheSupplier(CacheFactory.okHttpCacheSupplier())
            .build();
        OkHttpClient plain = new HttpRequestBuilderImpl(info).buildRequestClient();
        OkHttpClient optioned = new HttpRequestBuilderImpl(info)
            .withOptions(HttpRequestOptions.builder().timeout(Duration.ofSeconds(2)).followRedirects(false).build())
            .buildRequestClient();

        assertSame(plain.sslSocketFactory(), optioned.sslSocketFactory());
        assertSame(plain.hostnameVerifier(), optioned.hostnameVerifier());
        assertSame(hostnameVerifier, optioned.hostnameVerifier());
        assertSame(plain.cookieJar(), optioned.cookieJar());
        assertSame(plain.cache(), optioned.cache());
        assertEquals(plain.interceptors(), optioned.interceptors());
        assertFalse(optioned.interceptors().isEmpty());
        assertSame(plain.authenticator(), optioned.authenticator());
    }

    @Test
    void testAuthHeaderAndCookiesPreservedEndToEnd() throws Exception {
        try (MockWebServer server = new MockWebServer()) {
            server.enqueue(new MockResponse().setBody("ok").addHeader("Set-Cookie", "session=abc; Path=/"));
            server.start();
            ApiConnectionInfo info = new ApiConnectionInfoBuilder(server.url("/").toString())
                .withAuthorizationKeyProvider(() -> "key-123")
                .withCookieJarSupplier(CookieJarFactory.inMemoryCookieJar())
                .build();
            try (HttpRequestResponse response = requestTo(info)
                .withOptions(HttpRequestOptions.builder().timeout(Duration.ofSeconds(5)).build())
                .get().execute()) {
                assertEquals("ok", response.getBody());
                assertFalse(response.getCookies().isEmpty());
            }
            RecordedRequest recorded = server.takeRequest();
            assertEquals("key-123", recorded.getHeader("Authorization"));
        }
    }

    @Test
    void testPerRequestTimeoutIsolation() throws Exception {
        try (MockWebServer server = new MockWebServer()) {
            server.enqueue(new MockResponse().setBody("slow").setHeadersDelay(1500, java.util.concurrent.TimeUnit.MILLISECONDS));
            server.enqueue(new MockResponse().setBody("patient").setHeadersDelay(300, java.util.concurrent.TimeUnit.MILLISECONDS));
            server.start();
            ApiConnectionInfo info = connectionFor(server);

            HttpRequestBuilderImpl impatient = requestTo(info)
                .withOptions(HttpRequestOptions.builder().readTimeout(Duration.ofMillis(100)).retryOnConnectionFailure(false).build());
            HttpRequestBuilderImpl patient = requestTo(info)
                .withOptions(HttpRequestOptions.builder().readTimeout(Duration.ofSeconds(10)).build());

            assertThrows(ServerNotFoundException.class, () -> impatient.get().execute());
            try (HttpRequestResponse response = patient.get().execute()) {
                assertEquals("patient", response.getBody());
            }
            // a request without options is unaffected by either
            assertEquals(OkHttpClientInstance.getInstance().readTimeoutMillis(),
                requestTo(info).buildRequestClient().readTimeoutMillis());
        }
    }

    @Test
    void testCallTimeoutCoversBodyConsumption() throws Exception {
        try (MockWebServer server = new MockWebServer()) {
            server.enqueue(new MockResponse().setBody("0123456789abcdefghij").throttleBody(2, 100, java.util.concurrent.TimeUnit.MILLISECONDS));
            server.start();
            HttpRequestBuilderImpl builder = requestTo(connectionFor(server))
                .withOptions(HttpRequestOptions.builder().callTimeout(Duration.ofMillis(300)).readTimeout(Duration.ofSeconds(10)).build());
            // body-level logging, when enabled, reads the body inside execute(); the timeout then surfaces there
            assertThrows(InterruptedIOException.class, () -> {
                try (HttpRequestResponse response = builder.get().execute()) {
                    response.getBody();
                }
            });
        }
    }

    @Test
    void testRedirectsFollowedByDefault() throws Exception {
        try (MockWebServer server = new MockWebServer()) {
            enqueueRedirect(server);
            server.start();
            try (HttpRequestResponse response = requestTo(connectionFor(server)).get().execute()) {
                assertEquals("target", response.getBody());
            }
            assertEquals(2, server.getRequestCount());
        }
    }

    @Test
    void testRedirectsNotFollowedWhenDisabled() throws Exception {
        try (MockWebServer server = new MockWebServer()) {
            enqueueRedirect(server);
            server.start();
            HttpRequestBuilderImpl builder = requestTo(connectionFor(server))
                .withOptions(HttpRequestOptions.builder().followRedirects(false).followSslRedirects(false).build());
            CwmsHttpResponseException ex = assertThrows(CwmsHttpResponseException.class, () -> builder.get().execute());
            assertEquals(302, ex.getErrorCode());
            assertEquals(1, server.getRequestCount());
        }
    }

    @Test
    void testConnectionRetryByDefault() throws Exception {
        try (MockWebServer server = new MockWebServer()) {
            enqueueStaleConnectionSequence(server);
            server.start();
            ApiConnectionInfo info = connectionFor(server);
            try (HttpRequestResponse first = requestTo(info).get().execute()) {
                assertEquals("first", first.getBody());
            }
            try (HttpRequestResponse second = requestTo(info).get().execute()) {
                assertEquals("retried", second.getBody());
            }
        }
    }

    @Test
    void testConnectionRetryDisabled() throws Exception {
        try (MockWebServer server = new MockWebServer()) {
            enqueueStaleConnectionSequence(server);
            server.start();
            ApiConnectionInfo info = connectionFor(server);
            HttpRequestOptions noRetry = HttpRequestOptions.builder().retryOnConnectionFailure(false).build();
            try (HttpRequestResponse first = requestTo(info).withOptions(noRetry).get().execute()) {
                assertEquals("first", first.getBody());
            }
            assertThrows(IOException.class, () -> requestTo(info).withOptions(noRetry).get().execute());
            assertEquals(2, server.getRequestCount());
        }
    }

    @Test
    void testFailureResponseIsClosedAndSurfacesStatusAndBody() throws Exception {
        try (MockWebServer server = new MockWebServer()) {
            server.enqueue(new MockResponse().setResponseCode(500).setStatus("HTTP/1.1 500 Boom").setBody("bad"));
            server.enqueue(new MockResponse().setBody("after"));
            server.start();
            ApiConnectionInfo info = connectionFor(server);
            HttpRequestOptions options = HttpRequestOptions.builder().timeout(Duration.ofSeconds(5)).build();
            CwmsHttpResponseException ex = assertThrows(CwmsHttpResponseException.class,
                () -> requestTo(info).withOptions(options).get().execute());
            assertEquals(500, ex.getErrorCode());
            assertEquals("bad", ex.getResponseBody().orElseThrow());
            // the connection was released to the shared pool, so the next request reuses it
            try (HttpRequestResponse response = requestTo(info).withOptions(options).get().execute()) {
                assertEquals("after", response.getBody());
            }
            assertEquals(0, server.takeRequest().getSequenceNumber());
            assertEquals(1, server.takeRequest().getSequenceNumber());
        }
    }

    @Test
    void testBodyReadFailureThenCloseReleasesConnection() throws Exception {
        try (MockWebServer server = new MockWebServer()) {
            server.enqueue(new MockResponse().setBody("0123456789").setSocketPolicy(SocketPolicy.DISCONNECT_DURING_RESPONSE_BODY));
            server.enqueue(new MockResponse().setBody("after"));
            server.start();
            ApiConnectionInfo info = connectionFor(server);
            HttpRequestOptions options = HttpRequestOptions.builder().timeout(Duration.ofSeconds(5)).build();
            // body-level logging, when enabled, reads the body inside execute(); the failure then surfaces there
            assertThrows(IOException.class, () -> {
                try (HttpRequestResponse response = requestTo(info).withOptions(options).get().execute()) {
                    response.getBody();
                }
            });
            try (HttpRequestResponse response = requestTo(info).withOptions(options).get().execute()) {
                assertEquals("after", response.getBody());
            }
        }
    }

    private static void enqueueRedirect(MockWebServer server) {
        server.enqueue(new MockResponse().setResponseCode(302).addHeader("Location", "/target"));
        server.enqueue(new MockResponse().setBody("target"));
    }

    private static void enqueueStaleConnectionSequence(MockWebServer server) {
        server.enqueue(new MockResponse().setBody("first"));
        server.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.DISCONNECT_AFTER_REQUEST));
        server.enqueue(new MockResponse().setBody("retried"));
    }
}
