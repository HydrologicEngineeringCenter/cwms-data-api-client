/*
 * MIT License
 *
 * Copyright (c) 2023 Hydrologic Engineering Center
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
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.auth.SimpleAuthKeyProvider;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

class SimpleAuthHeaderInterceptorTest {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String ACCESS_TOKEN = "eyJ0eXAiOiJKV1";

    @Test
    void testNewRequestWithAuthorizationHeader() throws IOException {
        try (MockWebServer mockWebServer = new MockWebServer()) {
            mockWebServer.enqueue(new MockResponse().setBody("").setResponseCode(200));
            mockWebServer.start();
            SimpleAuthKeyProvider tokenProvider = () -> ACCESS_TOKEN;
            SimpleAuthHeaderInterceptor interceptor = new SimpleAuthHeaderInterceptor(tokenProvider);
            Request request = getMockRequest(mockWebServer);
            OkHttpClient client = OkHttpClientInstance.getInstance().newBuilder()
                .addInterceptor(interceptor)
                .build();
            Response execute = client.newCall(request).execute();
            assertEquals(ACCESS_TOKEN, execute.request().header(AUTHORIZATION_HEADER));
        }
    }

    @Test
    void testNewRequestWithAuthorizationHeaderNull() throws IOException {
        try (MockWebServer mockWebServer = new MockWebServer()) {
            mockWebServer.enqueue(new MockResponse().setBody("").setResponseCode(200));
            mockWebServer.start();
            SimpleAuthKeyProvider tokenProvider = () -> null;
            SimpleAuthHeaderInterceptor interceptor = new SimpleAuthHeaderInterceptor(tokenProvider);
            Request request = getMockRequest(mockWebServer);
            OkHttpClient client = OkHttpClientInstance.getInstance().newBuilder()
                .addInterceptor(interceptor)
                .build();
            assertThrows(IOException.class, () -> client.newCall(request).execute());
        }
    }

    private Request getMockRequest(MockWebServer mockWebServer) {
        return new Request.Builder()
            .url(String.format("http://localhost:%s", mockWebServer.getPort()))
            .addHeader(AUTHORIZATION_HEADER, ACCESS_TOKEN)
            .build();
    }

    private static class MyAuthKeyProvider implements SimpleAuthKeyProvider {

        @Override
        public String getAuthorizationKey() {
            return ACCESS_TOKEN;
        }
    }
}
