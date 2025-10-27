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

import hec.army.usace.hec.cwbi.auth.http.client.trustmanagers.CwbiAuthTrustManager;

import java.io.IOException;
import java.util.Objects;

import javax.net.ssl.SSLSocketFactory;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfoBuilder;
import mil.army.usace.hec.cwms.http.client.SslSocketData;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;

/**
 * Suitable only for CWBI Keycloaks direct grant setup.
 */
public final class CwbiAuthTokenProvider extends CwbiAuthTokenProviderBase {

    private final SSLSocketFactory sslSocketFactory;

    /**
     * Provider for OAuth2Tokens.
     *
     * @param wellKnownUrl - URL we are retrieving configuration from
     * @param clientId - client name
     * @param sslSocketFactory - ssl socket factory
     */
    public CwbiAuthTokenProvider(String wellKnownUrl, String clientId, SSLSocketFactory sslSocketFactory) {
        super(clientId, wellKnownUrl);
        this.sslSocketFactory = Objects.requireNonNull(sslSocketFactory, "Missing required sslSocketFactory");
    }

    @Override
    ApiConnectionInfo getUrl() {
        return new ApiConnectionInfoBuilder(this.wellKnownUrl)
                .withSslSocketData(new SslSocketData(sslSocketFactory, CwbiAuthTrustManager.getTrustManager()))
                .build();
    }

    @Override
    public ApiConnectionInfo getAuthUrl() {
        // This is specific to CWBI Direct Grant so this replacement as-is is fine
        return new ApiConnectionInfoBuilder(this.tokenUrl.getApiRoot().replace("identity", "identityc"))
                .withSslSocketData(new SslSocketData(sslSocketFactory, CwbiAuthTrustManager.getTrustManager()))
                .build();
    }

    @Override
    public OAuth2Token newToken() throws IOException {
        return new DirectGrantX509TokenRequestBuilder()
                .withTokenUrl(getAuthUrl())
                .buildRequest().withClientId(clientId)
                .fetchToken();
    }
}
