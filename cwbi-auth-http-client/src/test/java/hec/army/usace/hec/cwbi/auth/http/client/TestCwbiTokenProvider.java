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

import static hec.army.usace.hec.cwbi.auth.http.client.trustmanagers.CwbiAuthTrustManager.TOKEN_TEST_URL;
import static hec.army.usace.hec.cwbi.auth.http.client.trustmanagers.CwbiAuthTrustManager.TOKEN_URL;
import java.util.Collections;
import javax.net.ssl.KeyManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import javax.net.ssl.SSLSocketFactory;
import mil.army.usace.hec.cwms.http.client.MockHttpServer;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfoBuilder;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;
import mil.army.usace.hec.cwms.http.client.request.QueryParameters;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestCwbiTokenProvider {

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
        String baseUrl = String.format("http://localhost:%s/openid-configuration", mockHttpServer.getPort());
        return new ApiConnectionInfoBuilder(baseUrl).build();
    }

    private String getResource(String resource) throws IOException {
        URL resourceUrl = getClass().getClassLoader().getResource(resource);
        if (resourceUrl == null) {
            throw new IOException("Failed to get resource: " + resource);
        }
        Path path = new File(resourceUrl.getFile()).toPath();
        String collect = String.join("\n", Files.readAllLines(path));
        return collect;
    }

    protected void launchMockServerWithResource(String resource) throws IOException {
        mockHttpServer.getMockServer().setDispatcher(new Dispatcher() {
            private final Logger LOGGER = Logger.getLogger(TestOidcTokenProvider.class.getName());
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                final HttpUrl url = request.getRequestUrl();
                final String path = url.encodedPath();
                LOGGER.fine("Request for: " + url.toString());
                LOGGER.fine("Path: " + path);

                try {
                    if (path.endsWith("openid-configuration")) {
                        return new MockResponse().setBody(getResource("openIdConfig2.json")
                                                .replace("PORT", ""+mockHttpServer.getPort()));
                    }
                    else if (path.endsWith("/auth")) {
                        throw new IOException("Endpoint should not be called. Request was " + url.toString());
                        //fail("CwbiTokenProvider uses direct grant and should not call the /auth endpoint.");
                    }
                    else if (path.endsWith("/token")) {
                        return new MockResponse().setBody(getResource("oauth2token.json"));
                    }
                } catch (IOException ex) {
                    fail("Couldn't process mocked request", ex);
                }
                return new MockResponse().setResponseCode(404).setBody("Request not mocked.");
            }
        });
        mockHttpServer.start();
    }

    @Test
    void testBuildTokenProvider() throws IOException {
        String resource = "oauth2token.json";
        launchMockServerWithResource(resource);
        SSLSocketFactory sslSocketFactory = CwbiAuthSslSocketFactory.buildSSLSocketFactory(
                Collections.singletonList(getTestKeyManager()));
        String url = buildConnectionInfo().getApiRoot();
        CwbiAuthTokenProvider tokenProvider = new CwbiAuthTokenProvider(url, "cumulus", sslSocketFactory);
        assertEquals(url, tokenProvider.getUrl().getApiRoot());
        assertEquals("cumulus", tokenProvider.getClientId());
    }

    @Test
    void testNulls() throws IOException {
        String resource = "oauth2token.json";
        launchMockServerWithResource(resource);
        String url = buildConnectionInfo().getApiRoot();
        assertThrows(NullPointerException.class, () -> new CwbiAuthTokenProvider(url, "cumulus", null));
        assertThrows(NullPointerException.class, () -> new CwbiAuthTokenProvider(url, null, getTestSslSocketFactory()));
        assertThrows(NullPointerException.class, () -> new CwbiAuthTokenProvider(null, "cumulus", getTestSslSocketFactory()));
    }

    private KeyManager getTestKeyManager() {
        return new KeyManager() {
        };
    }

    @Test
    void testGetToken() throws IOException {
        String resource = "oauth2token.json";
        launchMockServerWithResource(resource);
        String url = buildConnectionInfo().getApiRoot();
        CwbiAuthTokenProvider tokenProvider = new CwbiAuthTokenProvider(url, "cumulus", getTestSslSocketFactory());
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
        String url = buildConnectionInfo().getApiRoot();
        CwbiAuthTokenProvider tokenProvider = new CwbiAuthTokenProvider(url, "cumulus", getTestSslSocketFactory());
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
        String url = buildConnectionInfo().getApiRoot();
        CwbiAuthTokenProvider tokenProvider = new CwbiAuthTokenProvider(url, "cumulus", getTestSslSocketFactory());
        OAuth2Token token = tokenProvider.getToken();
        assertNotNull(token, "Failed to retrieve initial token.");

        OAuth2Token refreshedToken = tokenProvider.refreshToken();
        assertEquals("MTQ0NjJkZmQ5OTM2NDE1ZTZjNGZmZjI3", refreshedToken.getAccessToken());
        assertEquals("Bearer", refreshedToken.getTokenType());
        assertEquals(3600, refreshedToken.getExpiresIn());
        assertEquals("IwOGYzYTlmM2YxOTQ5MGE3YmNmMDFkNTVk", refreshedToken.getRefreshToken());
        assertEquals("create", refreshedToken.getScope());
    }

    @Test
    void testConstructor() throws IOException {
        String resource = "oauth2token.json";
        launchMockServerWithResource(resource);
        String url = buildConnectionInfo().getApiRoot();
        SSLSocketFactory sslSocketFactory = getTestSslSocketFactory();
        MockCwbiAuthTokenProvider tokenProvider = new MockCwbiAuthTokenProvider(url, "clientId", sslSocketFactory);
        assertEquals(url, tokenProvider.getUrl().getApiRoot());
        assertEquals("clientId", tokenProvider.getClientId());
        assertEquals(sslSocketFactory, tokenProvider.getSslSocketFactory());
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
}
