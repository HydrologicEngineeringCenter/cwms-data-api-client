/*
 * Copyright (c) 2023
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client;

import java.io.IOException;
import java.util.List;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public final class MockHttpServer {

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

    public void enqueue(String body, List<String> cookies) {
        MockResponse mockResponse = new MockResponse().setBody(body);
        for (String cookie : cookies) {
            mockResponse = mockResponse.addHeader("Set-Cookie", cookie);
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
}
