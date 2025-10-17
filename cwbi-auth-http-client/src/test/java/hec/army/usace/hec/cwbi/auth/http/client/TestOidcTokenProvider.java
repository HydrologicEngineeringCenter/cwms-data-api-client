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
import javax.net.ssl.SSLSocketFactory;
import mil.army.usace.hec.cwms.http.client.MockHttpServer;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfoBuilder;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    protected void enqueueWithResource(MockHttpServer mockHttpServer, String resource) throws IOException {
        URL resourceUrl = getClass().getClassLoader().getResource(resource);
        if (resourceUrl == null) {
            throw new IOException("Failed to get resource: " + resource);
        }
        Path path = new File(resourceUrl.getFile()).toPath();
        String collect = String.join("\n", Files.readAllLines(path)).replace("PORT", ""+mockHttpServer.getPort());
        mockHttpServer.enqueue(collect);
    }

    @Test
    void testBuildTokenProvider() throws IOException {
        enqueueWithResource(mockAuthServer, "openIdConfig2.json");
        enqueueWithResource(mockAuthServer, "openIdConfig2.json"); // gets called twice, need to correct
        mockAuthServer.getMockServer().setDispatcher(new Dispatcher() {

            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                System.out.println(request.toString());
                return new MockResponse().setHeader("Location", request.getHeader("Location"));
            }
            
        });
        
        
        String wellKnown = "http://localhost:"+mockAuthServer.getPort();
        OidcAuthTokenProvider tokenProvider = new OidcAuthTokenProvider("test", wellKnown);
        enqueueWithResource(mockAuthServer, "oauth2token.json");
        OAuth2Token token = tokenProvider.getToken();
        assertNotNull(token);        
    }

}
