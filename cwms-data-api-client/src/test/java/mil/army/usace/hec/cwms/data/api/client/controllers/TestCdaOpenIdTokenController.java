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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hec.army.usace.hec.cwbi.auth.http.client.trustmanagers.CwbiAuthTrustManager;
import java.io.IOException;
import javax.net.ssl.SSLSocketFactory;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.SslSocketData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

final class TestCdaOpenIdTokenController extends TestController {
    @Test
    void testRetrieveTokenUrl() throws Exception {
        SSLSocketFactory mockSslSocketFactory = Mockito.mock(SSLSocketFactory.class);
        String resource = readSwaggerYamlAsJson();
        String openIdConfig = "radar/v1/json/openIdConfig.json";
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = (ObjectNode) mapper.readTree(resource);
        ApiConnectionInfo webServiceUrl = buildConnectionInfo();
        ObjectNode components = node.with("components");
        ObjectNode securitySchemes = components.with("securitySchemes");
        ObjectNode openIdConnect = securitySchemes.with("OpenIDConnect");
        openIdConnect.put("openIdConnectUrl", webServiceUrl.getApiRoot() + "/.well-known/openid-configuration");
        String updatedIdpConfig = mapper.writeValueAsString(node);
        mockHttpServer.enqueue(updatedIdpConfig);
        mockHttpServer.enqueue(readJsonFile(openIdConfig));
        SslSocketData sslSocketData = new SslSocketData(mockSslSocketFactory, CwbiAuthTrustManager.getTrustManager());
        ApiConnectionInfo tokenUrl = new CdaOpenIdTokenController().retrieveTokenUrl(buildConnectionInfo(), sslSocketData);
        assertEquals("https://api.example.com/auth/realms/cwbi/protocol/openid-connect/token", tokenUrl.getApiRoot());
    }

    @Test
    void testRetrieveTokenUrlNoSpec() throws Exception {
        SSLSocketFactory mockSslSocketFactory = Mockito.mock(SSLSocketFactory.class);
        String resource = "{\"spec\": \"none\"}";
        String openIdConfig = "radar/v1/json/openIdConfig.json";
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = (ObjectNode) mapper.readTree(resource);
        ApiConnectionInfo webServiceUrl = buildConnectionInfo();
        ObjectNode components = node.with("components");
        ObjectNode securitySchemes = components.with("securitySchemes");
        ObjectNode openIdConnect = securitySchemes.with("OpenIDConnect");
        openIdConnect.remove("openIdConnectUrl");
        openIdConnect.put("openIdConnectUrl", webServiceUrl.getApiRoot() + "/.well-known/openid-configuration");
        String updatedIdpConfig = mapper.writeValueAsString(node);
        mockHttpServer.enqueue(updatedIdpConfig);
        mockHttpServer.enqueue(readJsonFile(openIdConfig));
        SslSocketData sslSocketData = new SslSocketData(mockSslSocketFactory, CwbiAuthTrustManager.getTrustManager());
        assertThrows(IOException.class, () -> new CdaOpenIdTokenController().retrieveTokenUrl(buildConnectionInfo(), sslSocketData));
    }
}
