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

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2TokenProvider;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

class TestOAuth2TokenInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String ACCESS_TOKEN =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE2NTk5NzcyNjgsImV4cCI6MTY3NDQyMDc2NjgsImF1ZCI6Ind3dy5leGFtcGxlLmNvbSIsInN1YiI6Impyb2NrZXRAZXhhbXBsZS5jb20iLCJHaXZlbk5hbWUiOiJKb2hubnkiLCJTdXJuYW1lIjoiUm9ja2V0IiwiRW1haWwiOiJqcm9ja2V0QGV4YW1wbGUuY29tIiwiUm9sZSI6WyJNYW5hZ2VyIiwiUHJvamVjdCBBZG1pbmlzdHJhdG9yIl19.fuNn21aXq0ljupzvngo5_KHPwI4WYHP2UhSuSwP4NY8";

    @Test
    void testNewRequestWithAuthorizationHeader() throws IOException {
        try (MockWebServer mockWebServer = new MockWebServer()) {
            mockWebServer.enqueue(new MockResponse().setBody("").setResponseCode(200));
            mockWebServer.start();
            OAuth2TokenProvider tokenProvider = getTestTokenProvider();
            OAuth2TokenInterceptor interceptor = new OAuth2TokenInterceptor(tokenProvider);
            Request request = getMockRequest(mockWebServer);
            String accessToken = tokenProvider.getToken().getAccessToken();
            OkHttpClient client = OkHttpClientInstance.getInstance().newBuilder()
                .addInterceptor(interceptor)
                .build();
            Response execute = client.newCall(request).execute();
            assertEquals("Bearer " + accessToken, execute.request().header(AUTHORIZATION_HEADER));
        }
    }

    private Request getMockRequest(MockWebServer mockWebServer) {
        return new Request.Builder()
            .url(String.format("http://localhost:%s", mockWebServer.getPort()))
            .addHeader(AUTHORIZATION_HEADER, "Bearer " + ACCESS_TOKEN)
            .build();
    }

    private OAuth2TokenProvider getTestTokenProvider() {

        return new OAuth2TokenProvider() {
            OAuth2Token token;
            @Override
            public void clear() {
                token = null;
            }

            @Override
            public OAuth2Token getToken() {
                if(token == null)
                {
                    token = newToken();
                }
                return token;
            }

            @Override
            public OAuth2Token refreshToken() {
                OAuth2Token token = new OAuth2Token();
                token.setTokenType("Bearer");
                token.setAccessToken(ACCESS_TOKEN);
                token.setExpiresIn(3600);
                token.setRefreshToken(ACCESS_TOKEN);
                return token;
            }

            @Override
            public OAuth2Token newToken() {
                OAuth2Token token = new OAuth2Token();
                token.setTokenType("Bearer");
                token.setAccessToken(ACCESS_TOKEN);
                token.setExpiresIn(3600);
                token.setRefreshToken(ACCESS_TOKEN);
                return token;
            }

        };
    }
}
