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
package hec.army.usace.hec.cwbi.auth.http.client;

import java.io.IOException;
import java.util.Objects;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;

abstract class TokenRequestBuilder implements TokenRequestFluentBuilder {

    static final String MEDIA_TYPE = "application/x-www-form-urlencoded";
    private ApiConnectionInfo url;
    private String clientId;

    abstract OAuth2Token retrieveToken() throws IOException;

    ApiConnectionInfo getUrl() {
        return url;
    }

    String getClientId() {
        return clientId;
    }

    @Override
    public RequestClientId withUrl(ApiConnectionInfo url) {
        this.url = Objects.requireNonNull(url, "Missing required URL");
        return new RequestClientIdImpl();
    }

    private class RequestClientIdImpl implements RequestClientId {

        @Override
        public TokenRequestExecutor withClientId(String clientIdString) {
            clientId = Objects.requireNonNull(clientIdString, "Missing required Client ID");
            return new TokenRequestExecutorImpl();
        }
    }

    private class TokenRequestExecutorImpl implements TokenRequestExecutor {

        @Override
        public OAuth2Token fetchToken() throws IOException {
            return retrieveToken();
        }
    }
}
