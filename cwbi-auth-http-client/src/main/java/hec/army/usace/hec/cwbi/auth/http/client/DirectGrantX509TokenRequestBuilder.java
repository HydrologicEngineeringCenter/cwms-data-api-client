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

import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;

import java.io.IOException;

public final class DirectGrantX509TokenRequestBuilder extends TokenRequestBuilder<DirectGrantX509TokenRequestBuilder> {

    @Override
    OAuth2Token retrieveToken() throws IOException {
        OAuth2Token retVal = null;
        String formBody = new UrlEncodedFormData()
                .addPassword("")
                .addGrantType("password")
                .addScopes("openid", "profile")
                .addClientId(getClientId())
                .addUsername("")
                .buildEncodedString();
        HttpRequestExecutor executor =
                new HttpRequestBuilderImpl(getUrl())
                        .post()
                        .withBody(formBody)
                        .withMediaType(MEDIA_TYPE);
        try (HttpRequestResponse response = executor.execute()) {
            String body = response.getBody();
            if (body != null) {
                retVal = OAuth2ObjectMapper.mapJsonToObject(body, OAuth2Token.class);
            }
        }
        return retVal;
    }
}
