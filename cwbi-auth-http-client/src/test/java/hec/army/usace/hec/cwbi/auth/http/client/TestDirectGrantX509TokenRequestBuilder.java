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
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfoBuilder;
import mil.army.usace.hec.cwms.http.client.SslSocketData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.net.ssl.SSLSocketFactory;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

class TestDirectGrantX509TokenRequestBuilder {

    @Test
    void testRetrieveTokenMissingParams() {

        SslSocketData sslSocketData = new SslSocketData(getTestSslSocketFactory(), CwbiAuthTrustManager.getTrustManager());
        assertThrows(NullPointerException.class, () -> {
            OAuth2Token token = new DirectGrantX509TokenRequestBuilder()
                .withUrl(new ApiConnectionInfoBuilder("https://test.com")
                        .withSslSocketData(sslSocketData)
                        .build())
                .withClientId(null)
                .fetchToken();
            assertNull(token);
        });

        assertThrows(NullPointerException.class, () -> {
            new DirectGrantX509TokenRequestBuilder()
                .withUrl(null);
        });
    }

    @Test
    void testDirectGrantX509TokenRequestBuilder() throws IOException {

        try (MockWebServer mockWebServer = new MockWebServer()) {
            SslSocketData sslSocketData = new SslSocketData(getTestSslSocketFactory(), CwbiAuthTrustManager.getTrustManager());
            String body = readJsonFile();
            mockWebServer.enqueue(new MockResponse().setBody(body).setResponseCode(200));
            mockWebServer.start();
            String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
            OAuth2Token token = new DirectGrantX509TokenRequestBuilder()
                .withUrl(new ApiConnectionInfoBuilder(baseUrl)
                        .withSslSocketData(sslSocketData)
                        .build())
                .withClientId("cumulus")
                .fetchToken();
            assertNotNull(token);
            assertEquals("MTQ0NjJkZmQ5OTM2NDE1ZTZjNGZmZjI3", token.getAccessToken());
            assertEquals("Bearer", token.getTokenType());
            assertEquals(3600, token.getExpiresIn());
            assertEquals("create", token.getScope());
            assertEquals("IwOGYzYTlmM2YxOTQ5MGE3YmNmMDFkNTVk", token.getRefreshToken());
        }
    }

    static SSLSocketFactory getTestSslSocketFactory() {
        return new SSLSocketFactory() {
            @Override
            public String[] getDefaultCipherSuites() {
                return new String[0];
            }

            @Override
            public String[] getSupportedCipherSuites() {
                return new String[0];
            }

            @Override
            public Socket createSocket(Socket socket, String s, int i, boolean b){
                return null;
            }

            @Override
            public Socket createSocket(String s, int i) {
                return null;
            }

            @Override
            public Socket createSocket(String s, int i, InetAddress inetAddress, int i1) {
                return null;
            }

            @Override
            public Socket createSocket(InetAddress inetAddress, int i) {
                return null;
            }

            @Override
            public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress1, int i1) {
                return null;
            }
        };
    }

    String readJsonFile() throws IOException {
        String jsonPath = "oauth2token.json";
        URL resource = getClass().getClassLoader().getResource(jsonPath);
        if (resource == null) {
            throw new IOException("Resource not found: " + jsonPath);
        }
        Path path = new File(resource.getFile()).toPath();
        return String.join("\n", Files.readAllLines(path));
    }

}
