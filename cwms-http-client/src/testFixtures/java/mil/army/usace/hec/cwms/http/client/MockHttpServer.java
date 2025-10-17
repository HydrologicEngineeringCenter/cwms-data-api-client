/*
 * MIT License
 *
 * Copyright (c) 2024 Hydrologic Engineering Center
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

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import java.io.IOException;
import java.util.List;

public final class MockHttpServer implements AutoCloseable {

    private final MockWebServer mockWebServer;

    private MockHttpServer(MockWebServer mockWebServer) {
        this.mockWebServer = mockWebServer;
    }

    public static MockHttpServer create() throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        return new MockHttpServer(mockWebServer);
    }

    public void enqueue(String body) {
        mockWebServer.enqueue(new MockResponse().setBody(body));
    }

    public void enqueue(int responseCode, String body) {
        mockWebServer.enqueue(new MockResponse().setResponseCode(responseCode).setBody(body));
    }

    public void enqueue(MockResponse response) {
        mockWebServer.enqueue(response);
    }

    public void enqueue(String body, List<String> cookies) {
        MockResponse mockResponse = new MockResponse().setBody(body);
        for (String cookie : cookies) {
            mockResponse = mockResponse.addHeader("Set-Cookie", cookie);
        }
        mockWebServer.enqueue(mockResponse);
    }

    public void enqueueWithCache(String body, List<String> cacheControls) {
        MockResponse mockResponse = new MockResponse().setResponseCode(200).setBody(body);
        if (!cacheControls.isEmpty()) {
            mockResponse = mockResponse.addHeader("Cache-Control", String.join(", ", cacheControls));
        }
        mockWebServer.enqueue(mockResponse);
    }

    public void shutdown() throws IOException {
        mockWebServer.shutdown();
    }

    public int getPort() {
        return mockWebServer.getPort();
    }

    public void start() throws IOException {
        mockWebServer.start();
    }

    @Override
    public void close() throws Exception {
        mockWebServer.close();
    }

    public MockWebServer getMockServer() {
        return mockWebServer;
    }

    public RequestWrapper takeRequest() throws Exception {
        return new RequestWrapper(mockWebServer.takeRequest());
    }

    public static final class RequestWrapper {
        private final RecordedRequest request;

        private RequestWrapper(RecordedRequest request) {
            this.request = request;
        }

        public String getPath() {
            return request.getPath();
        }

        public String getMethod() {
            return request.getMethod();
        }
    }
}
