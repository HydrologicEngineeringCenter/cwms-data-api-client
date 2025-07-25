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
package mil.army.usace.hec.cwms.data.api.client.auth;

import hec.army.usace.hec.cwbi.auth.http.client.TokenUrlDiscoveryService;
import java.io.IOException;
import java.util.Objects;
import mil.army.usace.hec.cwms.data.api.client.controllers.CdaOpenIdTokenController;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.SslSocketData;

final class CdaTokenUrlDiscoveryService implements TokenUrlDiscoveryService {

    private final ApiConnectionInfo apiConnectionInfo;
    private final SslSocketData sslSocketData;

    CdaTokenUrlDiscoveryService(ApiConnectionInfo apiConnectionInfo, SslSocketData sslSocketData) {
        this.apiConnectionInfo = Objects.requireNonNull(apiConnectionInfo, "apiConnectionInfo is required");
        this.sslSocketData = Objects.requireNonNull(sslSocketData, "sslSocketData is required");
    }
    @Override
    public ApiConnectionInfo discoverTokenUrl() throws IOException {
        return new CdaOpenIdTokenController()
                .retrieveTokenUrl(apiConnectionInfo, sslSocketData);
    }
}
