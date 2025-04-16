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
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLSocketFactory;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfoBuilder;
import mil.army.usace.hec.cwms.http.client.MockHttpServer;
import mil.army.usace.hec.cwms.http.client.SslSocketData;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDiscoveredCwbiTokenProvider {

    static MockHttpServer mockHttpServer;

    static ExecutorService executorService;

    @BeforeAll
    static void setUpExecutorService() {
        executorService = Executors.newFixedThreadPool(1);
    }

    @BeforeEach
    void setUp() throws IOException {
        mockHttpServer = MockHttpServer.create();
    }

    @AfterEach
    void tearDown() throws IOException {
        mockHttpServer.shutdown();
    }

    ApiConnectionInfo buildConnectionInfo() {
        SSLSocketFactory sslSocketFactory = getTestSslSocketFactory();
        SslSocketData sslSocketData = new SslSocketData(sslSocketFactory, CwbiAuthTrustManager.getTrustManager());
        String baseUrl = String.format("http://localhost:%s", mockHttpServer.getPort());
        return new ApiConnectionInfoBuilder(baseUrl)
                .withSslSocketData(sslSocketData)
                .build();
    }

    protected void launchMockServerWithResource(String resource) throws IOException {
        URL resourceUrl = getClass().getClassLoader().getResource(resource);
        if (resourceUrl == null) {
            throw new IOException("Failed to get resource: " + resource);
        }
        Path path = new File(resourceUrl.getFile()).toPath();
        String collect = String.join("\n", Files.readAllLines(path));
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
    }

    @Test
    void testBuildTokenProvider() {
        ApiConnectionInfo tokenUrl = buildConnectionInfo();
        MockTokenUrlDiscoveryService tokenUrlDiscoveryService = new MockTokenUrlDiscoveryService(tokenUrl);
        DiscoveredCwbiAuthTokenProvider tokenProvider = new DiscoveredCwbiAuthTokenProvider("cumulus", tokenUrlDiscoveryService);
        assertEquals(tokenUrl.getApiRoot(), tokenProvider.getUrl().getApiRoot());
        assertEquals("cumulus", tokenProvider.getClientId());
    }

    @Test
    void testNulls() {
        assertThrows(NullPointerException.class, () -> new DiscoveredCwbiAuthTokenProvider("cumulus", null));
        assertThrows(NullPointerException.class, () -> new DiscoveredCwbiAuthTokenProvider(null, new MockTokenUrlDiscoveryService(new ApiConnectionInfoBuilder("").build())));
    }

    @Test
    void testGetToken() throws IOException {
        String resource = "oauth2token.json";
        launchMockServerWithResource(resource);
        ApiConnectionInfo tokenUrl = buildConnectionInfo();
        MockTokenUrlDiscoveryService tokenUrlDiscoveryService = new MockTokenUrlDiscoveryService(tokenUrl);
        DiscoveredCwbiAuthTokenProvider tokenProvider = new DiscoveredCwbiAuthTokenProvider("cumulus", tokenUrlDiscoveryService);
        OAuth2Token token = tokenProvider.getToken();
        assertEquals("MTQ0NjJkZmQ5OTM2NDE1ZTZjNGZmZjI3", token.getAccessToken());
        assertEquals("Bearer", token.getTokenType());
        assertEquals(3600, token.getExpiresIn());
        assertEquals("IwOGYzYTlmM2YxOTQ5MGE3YmNmMDFkNTVk", token.getRefreshToken());
        assertEquals("create", token.getScope());
    }

    @Test
    void testClear() throws IOException {
        String resource = "oauth2token.json";
        launchMockServerWithResource(resource);
        ApiConnectionInfo tokenUrl = buildConnectionInfo();
        MockTokenUrlDiscoveryService tokenUrlDiscoveryService = new MockTokenUrlDiscoveryService(tokenUrl);
        MockDiscoveredCwbiAuthTokenProvider tokenProvider = new MockDiscoveredCwbiAuthTokenProvider("cumulus", tokenUrlDiscoveryService);
        OAuth2Token token = new OAuth2Token();
        token.setAccessToken("abc123");
        token.setTokenType("Bearer");
        token.setExpiresIn(3600);
        token.setRefreshToken("123abc");
        tokenProvider.setOAuth2Token(token);
        OAuth2Token token1 = tokenProvider.getToken();
        OAuth2Token token2 = tokenProvider.getToken();
        assertSame(token1, token2);
        tokenProvider.clear();
        assertNotSame(token1, tokenProvider.getToken());
    }

    @Test
    void testRefreshToken() throws IOException {
        String resource = "oauth2token.json";
        launchMockServerWithResource(resource);
        ApiConnectionInfo tokenUrl = buildConnectionInfo();
        MockTokenUrlDiscoveryService tokenUrlDiscoveryService = new MockTokenUrlDiscoveryService(tokenUrl);
        MockDiscoveredCwbiAuthTokenProvider tokenProvider = new MockDiscoveredCwbiAuthTokenProvider("cumulus", tokenUrlDiscoveryService);
        OAuth2Token token = new OAuth2Token();
        token.setAccessToken("abc123");
        token.setTokenType("Bearer");
        token.setExpiresIn(3600);
        token.setRefreshToken("123abc");
        tokenProvider.setOAuth2Token(token);

        OAuth2Token refreshedToken = tokenProvider.refreshToken();
        assertEquals("MTQ0NjJkZmQ5OTM2NDE1ZTZjNGZmZjI3", refreshedToken.getAccessToken());
        assertEquals("Bearer", refreshedToken.getTokenType());
        assertEquals(3600, refreshedToken.getExpiresIn());
        assertEquals("IwOGYzYTlmM2YxOTQ5MGE3YmNmMDFkNTVk", refreshedToken.getRefreshToken());
        assertEquals("create", refreshedToken.getScope());
    }

    @Test
    void testConstructor() {
        SSLSocketFactory sslSocketFactory = getTestSslSocketFactory();
        ApiConnectionInfo tokenUrl = new ApiConnectionInfoBuilder("test.com")
                .withSslSocketData(new SslSocketData(sslSocketFactory, CwbiAuthTrustManager.getTrustManager()))
                .build();
        MockTokenUrlDiscoveryService tokenUrlDiscoveryService = new MockTokenUrlDiscoveryService(tokenUrl);
        MockDiscoveredCwbiAuthTokenProvider tokenProvider = new MockDiscoveredCwbiAuthTokenProvider("clientId", tokenUrlDiscoveryService);
        assertEquals("test.com", tokenProvider.getUrl().getApiRoot());
        assertEquals("clientId", tokenProvider.getClientId());
        assertSame(tokenUrlDiscoveryService, tokenProvider.getDiscoveryService());
    }

    private SSLSocketFactory getTestSslSocketFactory() {
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
            public Socket createSocket(Socket socket, String s, int i, boolean b) {
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

    private static class MockTokenUrlDiscoveryService implements TokenUrlDiscoveryService {
        private final ApiConnectionInfo tokenUrl;

        private MockTokenUrlDiscoveryService(ApiConnectionInfo tokenUrl) {
            this.tokenUrl = tokenUrl;
        }

        @Override
        public ApiConnectionInfo discoverTokenUrl() {
            return tokenUrl;
        }
    }
}
