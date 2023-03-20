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

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mil.army.usace.hec.cwms.http.client.auth.SimpleAuthKeyProvider;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

final class SimpleAuthHeaderInterceptor implements Interceptor {

    private static final Logger LOGGER = Logger.getLogger(SimpleAuthHeaderInterceptor.class.getName());
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final SimpleAuthKeyProvider keyProvider;

    SimpleAuthHeaderInterceptor(SimpleAuthKeyProvider keyProvider) {
        this.keyProvider = keyProvider;
        CwmsHttpLoggingInterceptor.getInstance().redactHeader(AUTHORIZATION_HEADER);
    }

    @Override
    public synchronized Response intercept(Chain chain) throws IOException {
        String authorizationKey = keyProvider.getAuthorizationKey();
        if (authorizationKey == null) {
            throw new IOException("Authentication failed: No authorization key retrieved from " + keyProvider.getClass().getName());
        }
        LOGGER.log(Level.FINEST, "Authenticating request with Authorization Key Token");
        Request request = newRequestWithAccessTokenAsHeader(chain.request(), authorizationKey);
        return chain.proceed(request);
    }

    //package scoped for testing
    Request newRequestWithAccessTokenAsHeader(Request request, String authorizationKey) {
        return request.newBuilder()
            .header(AUTHORIZATION_HEADER, authorizationKey)
            .build();
    }
}
