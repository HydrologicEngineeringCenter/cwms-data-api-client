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

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2TokenProvider;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

final class OAuth2TokenInterceptor implements Interceptor {

    private static final Logger LOGGER = Logger.getLogger(OAuth2TokenInterceptor.class.getName());
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final OAuth2TokenProvider tokenProvider;

    OAuth2TokenInterceptor(OAuth2TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
        CwmsHttpLoggingInterceptor.getInstance().redactHeader(AUTHORIZATION_HEADER);
    }

    @Override
    public synchronized Response intercept(Chain chain) throws IOException {
        OAuth2Token oauth2Token = tokenProvider.getToken();
        if (oauth2Token == null) {
            throw new IOException("Authentication failed: No token retrieved from " + OAuth2TokenProvider.class.getName());
        }
        String accessToken = oauth2Token.getAccessToken();
        if (accessToken == null || accessToken.isEmpty()) {
            throw new IOException("Authentication failed: No access token present");
        }
        LOGGER.log(Level.FINEST, "Authenticating request with OAuth2 Token");
        //if need to support tokens as parameters, add logic checking if token provider is using header/param,
        //then call appropriate method.
        Request request = newRequestWithAccessTokenAsHeader(chain.request(), oauth2Token);
        return chain.proceed(request);
    }

    //package scoped for testing
    Request newRequestWithAccessTokenAsHeader(Request request, OAuth2Token oauth2Token) {
        return request.newBuilder()
            .header(AUTHORIZATION_HEADER, oauth2Token.getTokenType() + " " + oauth2Token.getAccessToken())
            .build();
    }
}
