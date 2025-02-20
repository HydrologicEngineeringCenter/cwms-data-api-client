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
import javax.net.ssl.SSLSocketFactory;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2TokenProvider;

public class MockCwbiAuthTokenProvider implements OAuth2TokenProvider {

    private OAuth2Token oauth2Token;
    private final String url;
    private final String clientId;
    private final SSLSocketFactory sslSocketFactory;

    /**
     * Provider for OAuth2Tokens.
     *
     * @param url - URL we are fetching token from
     * @param clientId - client name
     * @param sslSocketFactory - ssl socket factory
     */
    public MockCwbiAuthTokenProvider(String url, String clientId, SSLSocketFactory sslSocketFactory) {
        this.url = url;
        this.clientId = clientId;
        this.sslSocketFactory = sslSocketFactory;
    }

    @Override
    public void clear() {
        oauth2Token = null;
    }

    @Override
    public OAuth2Token getToken() throws IOException {
        if (oauth2Token == null) {
            oauth2Token = newToken();
        }
        return oauth2Token;
    }

    @Override
    public OAuth2Token refreshToken() throws IOException {
        OAuth2Token token = new RefreshTokenRequestBuilder()
            .withRefreshToken(oauth2Token.getRefreshToken())
            .withUrl(url)
            .withClientId(clientId)
            .fetchToken();
        oauth2Token = token;
        return token;
    }

    @Override
    public OAuth2Token newToken() throws IOException {
        return new DirectGrantX509TokenRequestBuilder()
            .withSSlSocketFactory(sslSocketFactory)
            .withUrl(url)
            .withClientId(clientId)
            .fetchToken();
    }

    void setOAuth2Token(OAuth2Token token) {
        oauth2Token = token;
    }

    //package scoped for testing
    String getUrl() {
        return url;
    }

    //package scoped for testing
    String getClientId() {
        return clientId;
    }

    SSLSocketFactory getSslSocketFactory() {
        return sslSocketFactory;
    }
}
