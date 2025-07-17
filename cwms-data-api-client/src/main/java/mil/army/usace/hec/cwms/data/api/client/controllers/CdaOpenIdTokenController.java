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
package mil.army.usace.hec.cwms.data.api.client.controllers;

import hec.army.usace.hec.cwbi.auth.http.client.OpenIdTokenController;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.parser.OpenAPIV3Parser;
import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;

public final class CdaOpenIdTokenController extends OpenIdTokenController {
    private static final String SWAGGER_DOC_ENDPOINT = "swagger-docs";

    @Override
    protected String retrieveWellKnownEndpoint(ApiConnectionInfo apiConnectionInfo) throws IOException {
        String url = apiConnectionInfo.getApiRoot() + "/" + SWAGGER_DOC_ENDPOINT;
        OpenAPI openAPI = new OpenAPIV3Parser().read(url);
        if(openAPI == null) {
            throw new IOException("Failed to parse OpenAPI spec from " + url);
        }
        SecurityScheme openIdScheme = openAPI.getComponents()
                .getSecuritySchemes()
                .get("OpenIDConnect");
        if (openIdScheme == null) {
            throw new IOException("No OpenIDConnect security scheme defined in OpenAPI spec");
        }
        return openIdScheme.getOpenIdConnectUrl();
    }
}
