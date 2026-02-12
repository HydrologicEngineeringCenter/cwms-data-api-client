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

public class MockCwbiAuthTokenProvider extends OidcAuthTokenProvider {

    private final SSLSocketFactory sslSocketFactory;
    private final ApiConnectionInfo rawUrl;
    private final String clientId;

    /**
     * Provider for OAuth2Tokens.
     *
     * @param url - URL we are fetching token from
     * @param clientId - client name
     * @param sslSocketFactory - ssl socket factory
     */
    public MockCwbiAuthTokenProvider(ApiConnectionInfo url, String clientId, SSLSocketFactory sslSocketFactory) {
        super(clientId, url);
        this.clientId = Objects.requireNonNull(clientId, "Missing required client id.");
        this.rawUrl = Objects.requireNonNull(url, "Missing required url");
        this.sslSocketFactory = Objects.requireNonNull(sslSocketFactory, "Missing required sslSocketFactory");
    }

    //used to manually set token for testing (not used currently, but keep API)
    void setOAuth2Token(OAuth2Token token) {
        // No direct access to parent token; simulate by overriding newToken in tests if needed.
        throw new UnsupportedOperationException("Manual token injection not supported");
    }

    // Expose the provided URL with SSL settings (package scope for tests)
    ApiConnectionInfo getUrl() {
        return new ApiConnectionInfoBuilder(rawUrl.getApiRoot())
                .withSslSocketData(new SslSocketData(sslSocketFactory, CwbiAuthTrustManager.getTrustManager()))
                .build();
    }

    // Provide clientId for assertions (package scope for tests)
    String getClientId() {
        return clientId;
    }

    @Override
    public ApiConnectionInfo getTokenUrl() {
        // Use test SSL data when token URL is requested
        return getUrl();
    }

    @Override
    public ApiConnectionInfo getAuthUrl() {
        // For mock, just return same as token URL
        return getUrl();
    }

    @Override
    public OAuth2Token newToken() {
        // Not used in tests that construct MockCwbiAuthTokenProvider; throw if invoked unexpectedly
        throw new UnsupportedOperationException("Mock provider does not fetch real tokens");
    }

    //package scoped for testing
    SSLSocketFactory getSslSocketFactory() {
        return sslSocketFactory;
    }
}
