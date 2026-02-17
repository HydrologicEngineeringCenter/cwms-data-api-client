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
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfoBuilder;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;

public abstract class OpenIdTokenController {
    private static final String TOKEN_ENDPOINT_KEY = "token_endpoint";
    private static final String AUTH_ENDPOINT_KEY = "authorization_endpoint";


    private String authEndpoint = null;
    private String tokenEndpoint = null;

    /**
     * Retrieve json text of the .wellknown/openid-configuration
     * @param apiConnectionInfo - connection info to the config endpoint containing the well-known endpoint
     * @return ApiConnectionInfo containing the well-known endpoint url
     * @throws IOException - throws IOException if there is an issue with the http request to the config endpoint
     */
    public abstract ApiConnectionInfo retrieveWellKnownEndpoint(ApiConnectionInfo apiConnectionInfo) throws IOException;

    public final ApiConnectionInfo retrieveTokenUrl(ApiConnectionInfo apiConnectionInfo) throws IOException {
        if (tokenEndpoint == null) {
            ApiConnectionInfo wellKnownApiConnectionInfo = retrieveWellKnownEndpoint(apiConnectionInfo);
            
            HttpRequestExecutor executor = new HttpRequestBuilderImpl(wellKnownApiConnectionInfo)
                    .get();
            try (HttpRequestResponse response = executor.execute()) {
                tokenEndpoint = OAuth2ObjectMapper.getValueForKey(response.getBody(), TOKEN_ENDPOINT_KEY);
            }
        }
        return new ApiConnectionInfoBuilder(tokenEndpoint)
                .build();
    }

    public final ApiConnectionInfo retrieveAuthUrl(ApiConnectionInfo apiConnectionInfo) throws IOException {
        
        if (authEndpoint == null) {
            ApiConnectionInfo wellKnownApiConnectionInfo = retrieveWellKnownEndpoint(apiConnectionInfo);
            HttpRequestExecutor executor = new HttpRequestBuilderImpl(wellKnownApiConnectionInfo)
                    .get();
            try (HttpRequestResponse response = executor.execute()) {
                authEndpoint = OAuth2ObjectMapper.getValueForKey(response.getBody(), AUTH_ENDPOINT_KEY);
            }
        }
        return new ApiConnectionInfoBuilder(authEndpoint)
                .build();
    }
}
