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

import hec.army.usace.hec.cwbi.auth.http.client.trustmanagers.CwbiAuthTrustManager;
import java.util.Objects;
import javax.net.ssl.SSLSocketFactory;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfoBuilder;
import mil.army.usace.hec.cwms.http.client.SslSocketData;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;

public class MockCwbiAuthTokenProvider extends CwbiAuthTokenProviderBase {

    private final String url;
    private final SSLSocketFactory sslSocketFactory;

    /**
     * Provider for OAuth2Tokens.
     *
     * @param url - URL we are fetching token from
     * @param clientId - client name
     * @param sslSocketFactory - ssl socket factory
     */
    public MockCwbiAuthTokenProvider(String url, String clientId, SSLSocketFactory sslSocketFactory) {
        super(clientId, url);
        this.sslSocketFactory = Objects.requireNonNull(sslSocketFactory, "Missing required sslSocketFactory");
        this.url = url;
    }

    //used to manually set token for testing
    void setOAuth2Token(OAuth2Token token) {
        this.token = token;
    }

    @Override
    ApiConnectionInfo getUrl() {
        return new ApiConnectionInfoBuilder(url)
                .withSslSocketData(new SslSocketData(sslSocketFactory, CwbiAuthTrustManager.getTrustManager()))
                .build();
    }

    //package scoped for testing
    SSLSocketFactory getSslSocketFactory() {
        return sslSocketFactory;
    }
}
