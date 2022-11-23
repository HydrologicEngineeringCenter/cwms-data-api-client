/*
 * MIT License
 *
 * Copyright (c) 2022 Hydrologic Engineering Center
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

final class TestCwmsHttpLogger {

    private static final String ACCEPT_HEADER_V1 = "application/json";

    @Test
    void testNothingRedacted() throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        CwmsHttpLoggingInterceptor loggingInterceptor = CwmsHttpLoggingInterceptor.getInstance();
        loggingInterceptor.setLogLevel(Level.ALL);
        try {
            String body = readJsonFile("success.json");
            AtomicBoolean foundMessage = new AtomicBoolean(false);
            Logger.getLogger("okhttp3").addHandler(new Handler() {
                @Override
                public void publish(LogRecord record) {
                    String message = record.getMessage();
                    if (message.contains(body)) {
                        foundMessage.set(true);
                    }
                }

                @Override
                public void flush() {

                }

                @Override
                public void close() throws SecurityException {

                }
            });
            mockWebServer.enqueue(new MockResponse().setBody(body).setResponseCode(200));
            mockWebServer.start();
            String endpoint = "success";
            String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
            ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfoBuilder(baseUrl).build();
            HttpRequestExecutor executer = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
            try (HttpRequestResponse response = executer.execute()) {
                assertNotNull(response.getBody());
            }
            assertTrue(foundMessage.get());
        } finally {
            mockWebServer.shutdown();
        }
    }

    @Test
    void testRedacted() throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        System.setProperty("cwms.http.client.log.all.cookies", "true");
        CwmsHttpLoggingInterceptor loggingInterceptor = CwmsHttpLoggingInterceptor.getInstance();
        loggingInterceptor.setLogLevel(Level.ALL);
        try {
            String body = readJsonFile("tokens.json");
            AtomicBoolean foundMessage = new AtomicBoolean(false);
            Logger.getLogger("okhttp3").addHandler(new Handler() {
                @Override
                public void publish(LogRecord record) {
                    String message = record.getMessage();
                    if (message.contains("access_token") || message.contains("refresh_token")) {
                        foundMessage.set(true);
                    }
                }

                @Override
                public void flush() {

                }

                @Override
                public void close() throws SecurityException {

                }
            });
            mockWebServer.enqueue(new MockResponse().setBody(body).setResponseCode(200));
            mockWebServer.start();
            String endpoint = "tokens";
            String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
            ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfoBuilder(baseUrl).build();
            HttpRequestExecutor executer = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
            try (HttpRequestResponse response = executer.execute()) {
                assertNotNull(response.getBody());
            }
            assertFalse(foundMessage.get());
        } finally {
            mockWebServer.shutdown();
        }
    }

    @Test
    void testCookiesNotLogged() throws IOException {
        CwmsHttpLoggingInterceptor loggingInterceptor = CwmsHttpLoggingInterceptor.getInstance();
        loggingInterceptor.setLogLevel(Level.ALL);
        try(MockWebServer mockWebServer = new MockWebServer()) {
            String body = readJsonFile("tokens.json");
            AtomicBoolean foundMessage = new AtomicBoolean(false);
            Logger.getLogger("okhttp3").addHandler(new Handler() {
                @Override
                public void publish(LogRecord record) {
                    String message = record.getMessage();
                    if (message.contains("JSESSIONID")) {
                        foundMessage.set(true);
                    }
                }

                @Override
                public void flush() {

                }

                @Override
                public void close() throws SecurityException {

                }
            });
            MockResponse mockResponse = new MockResponse()
                .setBody(body)
                .addHeader("Set-Cookie", "JSESSIONID=53693739C7450D5D5261ED35E2093458")
                .setResponseCode(200);
            mockWebServer.enqueue(mockResponse);
            mockWebServer.start();
            String endpoint = "tokens";
            String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
            ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfoBuilder(baseUrl).build();
            HttpRequestExecutor executer = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
            try (HttpRequestResponse response = executer.execute()) {
                assertNotNull(response.getBody());
            }
            assertFalse(foundMessage.get());
        }
    }

    private String readJsonFile(String jsonPath) throws IOException {
        URL resource = getClass().getClassLoader().getResource(jsonPath);
        if (resource == null) {
            throw new IOException("Resource not found: " + jsonPath);
        }
        Path path = new File(resource.getFile()).toPath();
        return String.join("\n", Files.readAllLines(path));
    }
}
