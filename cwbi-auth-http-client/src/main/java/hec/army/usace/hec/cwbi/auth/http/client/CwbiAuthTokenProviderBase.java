/*
 * MIT License
 *
 * Copyright (c) 2025 Hydrologic Engineering Center
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
package hec.army.usace.hec.cwbi.auth.http.client;

import java.io.IOException;

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;

abstract class CwbiAuthTokenProviderBase extends OidcAuthTokenProvider {

    protected CwbiAuthTokenProviderBase(String clientId, String wellKnownUrl) {
        super(clientId, wellKnownUrl);
    }

    abstract ApiConnectionInfo getUrl() throws IOException;


    @Override
    public synchronized OAuth2Token getToken() throws IOException {
        if (token == null) {
            token = newToken();
        }
        return token;
    }

    @Override
    public synchronized OAuth2Token refreshToken() throws IOException {
        OAuth2Token newToken = new RefreshTokenRequestBuilder()
                .withRefreshToken(token.getRefreshToken())
                .withUrl(tokenUrl)
                .withClientId(clientId)
                .fetchToken();
        token = newToken;
        return token;
    }

    //package scoped for testing
    String getClientId() {
        return clientId;
    }
}
