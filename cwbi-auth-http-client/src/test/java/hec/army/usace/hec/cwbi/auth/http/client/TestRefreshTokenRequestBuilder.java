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

import static hec.army.usace.hec.cwbi.auth.http.client.TestDirectGrantX509TokenRequestBuilder.getTestSslSocketFactory;
import javax.net.ssl.SSLSocketFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

class TestRefreshTokenRequestBuilder {

    @Test
    void testRefreshTokenRequestBuilder() throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        try {
            String body = readJsonFile();
            mockWebServer.enqueue(new MockResponse().setBody(body).setResponseCode(200));
            mockWebServer.start();
            String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
            SSLSocketFactory sslSocketFactory = getTestSslSocketFactory();
            RefreshTokenRequestBuilder builder = new RefreshTokenRequestBuilder()
                    .withSSlSocketFactory(sslSocketFactory);
            assertSame(sslSocketFactory, builder.getSslSocketFactory().orElse(null));
            OAuth2Token token = new RefreshTokenRequestBuilder()
                    .withSSlSocketFactory(sslSocketFactory)
                    .withRefreshToken("abcdefghijklmnopqrstuvwxyz0123456789")
                    .withUrl(baseUrl)
                    .withClientId("cumulus")
                    .fetchToken();
            assertNotNull(token);
            assertEquals("MTQ0NjJkZmQ5OTM2NDE1ZTZjNGZmZjI3", token.getAccessToken());
            assertEquals("Bearer", token.getTokenType());
            assertEquals(3600, token.getExpiresIn());
            assertEquals("create", token.getScope());
            assertEquals("IwOGYzYTlmM2YxOTQ5MGE3YmNmMDFkNTVk", token.getRefreshToken());
        } finally {
            mockWebServer.shutdown();
        }
    }

    @Test
    void testRetrieveTokenMissingParams() {
        assertThrows(NullPointerException.class, () -> {
            new RefreshTokenRequestBuilder()
                .withRefreshToken("testToken")
                .withUrl(null);
        });

        assertThrows(NullPointerException.class, () -> {
            OAuth2Token token = new RefreshTokenRequestBuilder()
                .withRefreshToken("testToken")
                .withUrl("https://test.com")
                .withClientId(null)
                .fetchToken();
            assertNull(token);
        });


        assertThrows(NullPointerException.class, () -> {
            OAuth2Token token = new RefreshTokenRequestBuilder()
                .withRefreshToken(null)
                .withUrl("https://test.com")
                .withClientId("cumulus")
                .fetchToken();
            assertNull(token);
        });
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
