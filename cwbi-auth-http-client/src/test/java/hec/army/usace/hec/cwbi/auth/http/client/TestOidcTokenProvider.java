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

import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.net.ssl.SSLSocketFactory;
import mil.army.usace.hec.cwms.http.client.MockHttpServer;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfoBuilder;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
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
import org.mockito.Mock;

class TestOidcTokenProvider {
    
    static MockHttpServer mockCdaServer;
    static MockHttpServer mockAuthServer;

    static ExecutorService executorService;

    @BeforeAll
    static void setUpExecutorService() {
        executorService = Executors.newFixedThreadPool(1);
    }

    @BeforeEach
    void setUp() throws IOException {
        mockCdaServer = MockHttpServer.create();
        mockAuthServer = MockHttpServer.create();
        mockCdaServer.start();
        mockAuthServer.start();
    }

    @AfterEach
    void tearDown() throws IOException {
        mockCdaServer.shutdown();
        mockAuthServer.shutdown();
    }

    ApiConnectionInfo buildCdaInfo() {
        String baseUrl = String.format("http://localhost:%s", mockCdaServer.getPort());
        return new ApiConnectionInfoBuilder(baseUrl).build();
    }

    ApiConnectionInfo buildAuthInfo() {
        String baseUrl = String.format("http://localhost:%s", mockAuthServer.getPort());
        return new ApiConnectionInfoBuilder(baseUrl).build();
    }

    protected String getResource(String resource) throws IOException {
        URL resourceUrl = getClass().getClassLoader().getResource(resource);
        if (resourceUrl == null) {
            throw new IOException("Failed to get resource: " + resource);
        }
        Path path = new File(resourceUrl.getFile()).toPath();
        return String.join("\n", Files.readAllLines(path));
    }

    @Test
    void testBuildTokenProvider() throws Exception {
        mockAuthServer.getMockServer().setDispatcher(new Dispatcher() {
            private static final Logger LOGGER = Logger.getLogger(TestOidcTokenProvider.class.getName()+"_dispatcher");
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                final HttpUrl url = request.getRequestUrl();
                final String path = url.encodedPath();
                LOGGER.fine("Request for: " + url.toString());
                LOGGER.fine("Path: " + path);
                
                try {
                    if (path.endsWith("openid-configuration")) {
                        return new MockResponse().setBody(getResource("openIdConfig2.json")
                                                .replace("PORT", ""+mockAuthServer.getPort()));
                    }
                    else if (path.endsWith("/auth")) {
                        final String query = request.getRequestUrl().query();
                        final QueryParameters parameters = QueryParameters.parse(query);
                        final String loc = String.format("%s?code=test&state=a test", parameters.get("redirect_uri").get(0));
                        return new MockResponse().setResponseCode(302).setHeader("Location", loc);
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
        
        
        String wellKnown = "http://localhost:"+mockAuthServer.getPort()+"/auth/realms/cwbi/.well-known/openid-configuration";
        OidcAuthTokenProvider tokenProvider = new OidcAuthTokenProvider("test", wellKnown);
        tokenProvider.setAuthCallback(u -> {
            try {
                HttpRequestExecutor executor =
                    new HttpRequestBuilderImpl(new ApiConnectionInfoBuilder(u.toString()).build())
                        .get()
                        .withMediaType("text/plain");
                
                try (HttpRequestResponse response = executor.execute()) {
                    // redirect should be automatically followed. If changes 
                    // made that fail this section either enable redirect or handle it.
                }
                
            } catch (IOException ex) {
                fail("Unable to perform client side of authorization procedure.", ex);
            }
            
        });
        final OAuth2Token token = tokenProvider.getToken();
        assertNotNull(token);        
    }    
}
