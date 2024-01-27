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

import mil.army.usace.hec.cwms.http.client.auth.SimpleAuthKeyProvider;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SimpleAuthHeaderAuthenticatorTest {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String ACCESS_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJ";

    @Test
    void testAuthenticate() throws IOException {
        SimpleAuthKeyProvider tokenProvider = new MyAuthKeyProvider();
        SimpleAuthHeaderAuthenticator authenticator = new SimpleAuthHeaderAuthenticator(tokenProvider);
        Response response = getMockResponse();
        Request request = authenticator.authenticate(null, response);
        assertNotNull(request);
        String refreshAccessToken = tokenProvider.getAuthorizationKey();
        assertEquals(refreshAccessToken, request.headers(AUTHORIZATION_HEADER).get(0));
    }

    @Test
    void testAuthenticateNullToken() throws IOException {
        SimpleAuthKeyProvider tokenProvider = () -> null;
        SimpleAuthHeaderAuthenticator authenticator = new SimpleAuthHeaderAuthenticator(tokenProvider);
        Response response = getMockResponse();
        assertThrows(IOException.class, () -> authenticator.authenticate(null, response));
    }

    @Test
    void testAuthenticateSameToken() throws IOException {
        SimpleAuthKeyProvider tokenProvider = () -> "Bearer " + ACCESS_TOKEN;
        SimpleAuthHeaderAuthenticator authenticator = new SimpleAuthHeaderAuthenticator(tokenProvider);
        Response response = getMockResponse();
        assertNull(authenticator.authenticate(null, response));
    }

    @Test
    void testAuthenticateMockServer() throws IOException {
        try (MockWebServer mockWebServer = new MockWebServer()) {
            mockWebServer.enqueue(new MockResponse().setBody("").setResponseCode(401));
            mockWebServer.enqueue(new MockResponse().setBody("").setResponseCode(401));
            mockWebServer.start();
            SimpleAuthKeyProvider tokenProvider = () -> "Bearer " + ACCESS_TOKEN;
            SimpleAuthHeaderAuthenticator authenticator = new SimpleAuthHeaderAuthenticator(tokenProvider);
            assertThrows(UnauthorizedException.class, () -> new HttpRequestBuilderImpl(new ApiConnectionInfo(String.format("http://localhost:%s", mockWebServer.getPort()), null,
                    null, new ArrayList<>(), authenticator, null))
                    .post()
                    .withBody("")
                    .withMediaType("application/json")
                    .execute()
                    .close());
        }
    }

    private Response getMockResponse() {
        Request mockRequest = new Request.Builder()
                .url("https://some-url.com")
                .addHeader(AUTHORIZATION_HEADER, "Bearer " + ACCESS_TOKEN)
                .build();
        return new Response.Builder()
                .request(mockRequest)
                .protocol(Protocol.HTTP_2)
                .code(401) // status code
            .message("")
            .body(ResponseBody.create("{}",
                MediaType.get("application/json; charset=utf-8")
            ))
            .build();
    }

    private static class MyAuthKeyProvider implements SimpleAuthKeyProvider {

        @Override
        public String getAuthorizationKey() {
            return ACCESS_TOKEN;
        }
    }
}
