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
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

final class SimpleAuthHeaderAuthenticator implements Authenticator {

    private static final Logger LOGGER = Logger.getLogger(SimpleAuthHeaderAuthenticator.class.getName());
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final AtomicBoolean _alreadyRetried = new AtomicBoolean(false);
    private final SimpleAuthKeyProvider keyProvider;

    SimpleAuthHeaderAuthenticator(SimpleAuthKeyProvider keyProvider) {
        this.keyProvider = keyProvider;
        CwmsHttpLoggingInterceptor.getInstance().redactHeader(AUTHORIZATION_HEADER);
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        LOGGER.log(Level.FINE, () -> "Authentication required for: " + response.request().url() + " attempting acquiring authorization key");
        String authorizationKey = keyProvider.getAuthorizationKey();
        if (authorizationKey == null) {
            throw new IOException("No Authorization key retrieved from " + keyProvider.getClass().getName());
        }
        // Check if the request made was made as an authenticated request.
        if (authorizationKey.equals(response.request().header(AUTHORIZATION_HEADER))) {
            LOGGER.log(Level.FINE, () -> "Re-authentication attempt failed. Provider returned the same key as the original request. \n"
                    + "Requsted URL:" + response.request().url() + "\n"
                    + "Error Code: " + response.code());
            return null;
        }
        LOGGER.log(Level.FINE, () -> "Authentication required for: " + response.request().url() + " acquired authorization key and creating a new request");
        return newRequestWithAccessTokenAsHeader(response, authorizationKey);
    }

    //package scoped for testing
    Request newRequestWithAccessTokenAsHeader(Response response, String authorizationKey) {
        return response.request()
            .newBuilder()
            .header(AUTHORIZATION_HEADER, authorizationKey)
            .build();
    }

}
