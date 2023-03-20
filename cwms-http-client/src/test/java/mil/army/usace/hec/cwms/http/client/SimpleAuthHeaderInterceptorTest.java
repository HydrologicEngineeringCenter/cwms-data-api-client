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
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.auth.SimpleAuthKeyProvider;
import okhttp3.Request;
import org.junit.jupiter.api.Test;

class SimpleAuthHeaderInterceptorTest {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String ACCESS_TOKEN = "eyJ0eXAiOiJKV1";

    @Test
    void testNewRequestWithAuthorizationHeader() throws IOException {
        MyAuthKeyProvider tokenProvider = new MyAuthKeyProvider();
        SimpleAuthHeaderInterceptor interceptor = new SimpleAuthHeaderInterceptor(tokenProvider);
        Request request = getMockRequest();
        String accessToken = tokenProvider.getAuthorizationKey();
        Request newRequest = interceptor.newRequestWithAccessTokenAsHeader(request, accessToken);
        assertNotNull(newRequest);
        assertEquals(accessToken, newRequest.headers(AUTHORIZATION_HEADER).get(0));
    }

    private Request getMockRequest() {
        return new Request.Builder()
            .url("https://some-url.com")
            .addHeader(AUTHORIZATION_HEADER, "Bearer " + ACCESS_TOKEN)
            .build();
    }

    private static class MyAuthKeyProvider implements SimpleAuthKeyProvider {

        @Override
        public String getAuthorizationKey() {
            return ACCESS_TOKEN;
        }
    }
}
