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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.net.ssl.SSLSocketFactory;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfoBuilder;
import mil.army.usace.hec.cwms.http.client.SslSocketData;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

final class TestOpenIdTokenController {
    @Test
    void testRetrieveTokenUrl() throws Exception {
        try (MockWebServer mockWebServer = new MockWebServer()) {
            SSLSocketFactory mockSslSocketFactory = Mockito.mock(SSLSocketFactory.class);
            SslSocketData sslSocketData = new SslSocketData(mockSslSocketFactory, CwbiAuthTrustManager.getTrustManager());
            String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
            String wellKnownEndpoint = baseUrl + "/.well-known/openid-configuration";
            mockWebServer.enqueue(new MockResponse().setBody(readResourceAsString("openIdConfig.json")).setResponseCode(200));
            ApiConnectionInfo tokenUrl = new OpenIdTokenController(){

                @Override
                protected String retrieveWellKnownEndpoint(ApiConnectionInfo apiConnectionInfo) {
                    return wellKnownEndpoint;
                }
            }.retrieveTokenUrl(new ApiConnectionInfoBuilder(baseUrl).build(), sslSocketData);
            assertEquals("https://api.example.com/auth/realms/cwbi/protocol/openid-connect/token", tokenUrl.getApiRoot());
        }
    }

    private String readResourceAsString(String resource) throws IOException {
        URL resourceUrl = getClass().getClassLoader().getResource(resource);
        if (resourceUrl == null) {
            throw new IOException("Failed to get resource: " + resource);
        }
        Path path = new File(resourceUrl.getFile()).toPath();
        return String.join("\n", Files.readAllLines(path));
    }
}
