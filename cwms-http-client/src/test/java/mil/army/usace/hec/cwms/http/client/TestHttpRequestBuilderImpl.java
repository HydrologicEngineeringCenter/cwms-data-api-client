/*
 * MIT License
 *
 * Copyright (c) 2021 Hydrologic Engineering Center
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

package mil.army.usace.hec.cwms.http.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Provider;
import java.security.Security;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl.HttpRequestExecutorImpl;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import okhttp3.Request;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jsse.provider.BouncyCastleJsseProvider;
import org.junit.jupiter.api.Test;

class TestHttpRequestBuilderImpl {

    private static final String ACCEPT_HEADER_V1 = "application/json";

    @Test
    void testHttpRequestBuilderCreateGetRequest() throws IOException {
        String root = "http://localhost:11524/cwms-data/";
        String endpoint = "timeseries";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        HttpRequestBuilderImpl httpRequestBuilder = ((HttpRequestExecutorImpl) new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .get()
            .withMediaType(ACCEPT_HEADER_V1))
            .getInstance();
        Request request = httpRequestBuilder.createRequest();
        assertEquals(root + endpoint, request.url().toString());
    }

    @Test
    void testHttpRequestBuilderCreatePostRequest() throws IOException {
        String root = "http://localhost:11524/cwms-data/";
        String endpoint = "timeseries";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        HttpRequestBuilderImpl httpRequestBuilder = ((HttpRequestExecutorImpl) new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .post()
            .withBody("{test}")
            .withMediaType(ACCEPT_HEADER_V1))
            .getInstance();
        Request request = httpRequestBuilder.createRequest(); //post request
        assertEquals(root + endpoint, request.url().toString());
    }

    @Test
    void testHttpReq7hgb4uestBuilderIpV6() throws IOException {
        String root = "http://[1080:0:0:0:8:800:200C:417A]/index/";
        String endpoint = "timeseries";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        HttpRequestBuilderImpl httpRequestBuilder = ((HttpRequestExecutorImpl) new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .get()
            .withMediaType(ACCEPT_HEADER_V1))
            .getInstance();
        Request request = httpRequestBuilder.createRequest();
        assertEquals("http://[1080::8:800:200c:417a]/index/timeseries", request.url().toString());
    }

    @Test
    void testHttpRequestBuilderCreateRequestInvalidUrl() throws IOException {
        String root = "//http://localhost:11524/cwms-data/";
        String endpoint = "timeseries";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        assertThrows(ServerNotFoundException.class, () -> new HttpRequestBuilderImpl(apiConnectionInfo, endpoint));
    }

    @Test
    void testHttpRequestBuilderCreateRequestNullRoot() throws IOException {
        String root = null;
        String endpoint = "timeseries";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        assertThrows(NullPointerException.class, () -> new HttpRequestBuilderImpl(apiConnectionInfo, endpoint));
    }

    @Test
    void testHttpRequestBuilderCreateRequestNullEndpoint() throws IOException {
        String root = "http://localhost:11524/cwms-data/";
        String endpoint = null;
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        assertThrows(NullPointerException.class, () -> new HttpRequestBuilderImpl(apiConnectionInfo, endpoint));
    }

    @Test
    void testHttpRequestBuilderCreateGetRequestWithParameters() throws IOException {
        String root = "http://localhost:11524/cwms-data/";
        String endpoint = "timeseries";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        HttpRequestBuilderImpl httpRequestBuilder = ((HttpRequestExecutorImpl) new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addQueryParameter("hello", "world")
            .addQueryParameter("green-eggs", "and ham")
            .get()
            .withMediaType(ACCEPT_HEADER_V1))
            .getInstance();
        Request request = httpRequestBuilder.createRequest();
        assertTrue(request.url().toString().startsWith(root + endpoint + "?"));
        assertTrue(request.url().toString().contains("hello=world"));
        assertTrue(request.url().toString().contains("green-eggs=and%20ham"));
    }

    @Test
    void testHttpRequestBuilderCreatePostRequestWithParameters() throws IOException {
        String root = "http://localhost:11524/cwms-data/";
        String endpoint = "timeseries";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        HttpRequestBuilderImpl httpRequestBuilder = ((HttpRequestExecutorImpl) new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addQueryParameter("hello", "world")
            .addQueryParameter("green-eggs", "and ham")
            .post()
            .withBody("{test}")
            .withMediaType(ACCEPT_HEADER_V1))
            .getInstance();
        Request request = httpRequestBuilder.createRequest();
        assertTrue(request.url().toString().startsWith(root + endpoint + "?"));
        assertTrue(request.url().toString().contains("hello=world"));
        assertTrue(request.url().toString().contains("green-eggs=and%20ham"));
    }

    @Test
    void testHttpRequestBuilderCreateRequestWithNullParameter() throws IOException {
        String root = "http://localhost:11524/cwms-data/";
        String endpoint = "timeseries";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        HttpRequestBuilderImpl httpRequestBuilder = ((HttpRequestExecutorImpl) new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addQueryParameter("hello", null)
            .get()
            .withMediaType(ACCEPT_HEADER_V1))
            .getInstance();
        Request request = httpRequestBuilder.createRequest();
        assertFalse(request.url().toString().contains("hello="));
        httpRequestBuilder = ((HttpRequestExecutorImpl) new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addQueryParameter("hello", null)
            .post()
            .withBody("{test}")
            .withMediaType(ACCEPT_HEADER_V1))
            .getInstance();
        request = httpRequestBuilder.createRequest();
        assertFalse(request.url().toString().contains("hello="));
    }

    @Test
    void testHttpRequestBuilderCreateGetRequestWithHeaders() throws IOException {
        String root = "http://localhost:11524/cwms-data/";
        String endpoint = "timeseries";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        HttpRequestBuilderImpl httpRequestBuilder = ((HttpRequestExecutorImpl) new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addQueryHeader("hello", "world")
            .addQueryHeader("green-eggs", "and ham")
            .get()
            .withMediaType(ACCEPT_HEADER_V1))
            .getInstance();
        Request request = httpRequestBuilder.createRequest();
        assertTrue(request.headers("hello").contains("world"));
        assertTrue(request.headers("green-eggs").contains("and ham"));
    }

    @Test
    void testHttpRequestBuilderCreatePostRequestWithHeaders() throws IOException {
        String root = "http://localhost:11524/cwms-data/";
        String endpoint = "timeseries";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        HttpRequestBuilderImpl httpRequestBuilder = ((HttpRequestExecutorImpl) new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addQueryHeader("hello", "world")
            .addQueryHeader("green-eggs", "and ham")
            .post()
            .withBody("{test}")
            .withMediaType(ACCEPT_HEADER_V1))
            .getInstance();
        Request request = httpRequestBuilder.createRequest();
        assertTrue(request.headers("hello").contains("world"));
        assertTrue(request.headers("green-eggs").contains("and ham"));
    }

    @Test
    void testHttpRequestBuilderCreateRequestWithNullHeader() throws IOException {
        String root = "http://localhost:11524/cwms-data/";
        String endpoint = "timeseries";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        HttpRequestBuilderImpl httpRequestBuilder = ((HttpRequestExecutorImpl) new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addQueryHeader("hello", null)
            .get()
            .withMediaType(ACCEPT_HEADER_V1))
            .getInstance();
        Request request = httpRequestBuilder.createRequest();
        assertTrue(request.headers("hello").isEmpty());
        httpRequestBuilder = ((HttpRequestExecutorImpl) new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addQueryHeader("hello", null)
            .post()
            .withBody("{test}")
            .withMediaType(ACCEPT_HEADER_V1))
            .getInstance();
        request = httpRequestBuilder.createRequest();
        assertTrue(request.headers("hello").isEmpty());
    }

    @Test
    void testHttpRequestBuilderCreateGetRequestWithEndpointInput() throws IOException {
        String root = "http://localhost:11524/cwms-data/";
        String endpoint = "timeseries";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        HttpRequestBuilderImpl httpRequestBuilder = ((HttpRequestExecutorImpl) new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addEndpointInput(new EndpointInput() {
                @Override
                protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
                    return httpRequestBuilder.addQueryHeader("hello", "world")
                        .addQueryHeader("green-eggs", "and ham")
                        .addQueryParameter("hello", "world")
                        .addQueryParameter("green-eggs", "and ham");
                }
            })
            .get()
            .withMediaType(ACCEPT_HEADER_V1))
            .getInstance();
        Request request = httpRequestBuilder.createRequest();
        assertTrue(request.url().toString().startsWith(root + endpoint + "?"));
        assertTrue(request.url().toString().contains("hello=world"));
        assertTrue(request.url().toString().contains("green-eggs=and%20ham"));
        assertTrue(request.headers("hello").contains("world"));
        assertTrue(request.headers("green-eggs").contains("and ham"));
    }

    @Test
    void testHttpRequestBuilderCreatePostRequestWithEndpointInput() throws IOException {
        String root = "http://localhost:11524/cwms-data/";
        String endpoint = "timeseries";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        HttpRequestBuilderImpl httpRequestBuilder = ((HttpRequestExecutorImpl) new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addEndpointInput(new EndpointInput() {
                @Override
                protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
                    return httpRequestBuilder.addQueryHeader("hello", "world")
                        .addQueryHeader("green-eggs", "and ham")
                        .addQueryParameter("hello", "world")
                        .addQueryParameter("green-eggs", "and ham");
                }
            })
            .post()
            .withBody("{test}")
            .withMediaType(ACCEPT_HEADER_V1))
            .getInstance();
        Request request = httpRequestBuilder.createRequest();
        assertTrue(request.url().toString().startsWith(root + endpoint + "?"));
        assertTrue(request.url().toString().contains("hello=world"));
        assertTrue(request.url().toString().contains("green-eggs=and%20ham"));
        assertTrue(request.headers("hello").contains("world"));
        assertTrue(request.headers("green-eggs").contains("and ham"));
    }

    @Test
    void testHttpRequestBuilderExecuteGetSuccess() throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        try {
            String body = readJsonFile("success.json");
            mockWebServer.enqueue(new MockResponse().setBody(body).setResponseCode(200));
            mockWebServer.start();
            String endpoint = "success";
            String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
            ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(baseUrl);
            HttpRequestExecutor executer = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
            try (HttpRequestResponse response = executer.execute()) {
                assertNotNull(response.getBody());
            }
        } finally {
            mockWebServer.shutdown();
        }
    }

    @Test
    void testHttpRequestBuilderExecutePostSuccess() throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        try {
            String body = readJsonFile("success.json");
            mockWebServer.enqueue(new MockResponse().setBody(body).setResponseCode(200));
            mockWebServer.start();
            String endpoint = "success";
            String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
            ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(baseUrl);
            HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .post()
                .withBody("{test}")
                .withMediaType(ACCEPT_HEADER_V1);
            try (HttpRequestResponse response = executor.execute()) {
                assertNotNull(response.getBody());
            }
        } finally {
            mockWebServer.shutdown();
        }
    }

    @Test
    void testHttpRequestBuilderExecuteGetServerError() throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        try {
            String body = readJsonFile("servererror.json");
            mockWebServer.enqueue(new MockResponse().setBody(body).setResponseCode(500));
            mockWebServer.start();
            String endpoint = "success";
            String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
            ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(baseUrl);
            HttpRequestBuilder builder = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint);
            HttpRequestExecutor executor = builder.get().withMediaType(ACCEPT_HEADER_V1);
            assertThrows(IOException.class, () -> {
                try (HttpRequestResponse response = executor.execute()) {
                    assertNull(response);
                }
            });
        } finally {
            mockWebServer.shutdown();
        }
    }

    @Test
    void testHttpRequestBuilderExecutePostServerError() throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        try {
            String body = readJsonFile("servererror.json");
            mockWebServer.enqueue(new MockResponse().setBody(body).setResponseCode(500));
            mockWebServer.start();
            String endpoint = "success";
            String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
            ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(baseUrl);
            HttpRequestBuilder builder = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint);
            HttpRequestExecutor executor = builder.post().withBody("{test}").withMediaType(ACCEPT_HEADER_V1);
            assertThrows(IOException.class, () -> {
                try (HttpRequestResponse response = executor.execute()) {
                    assertNull(response);
                }
            });
        } finally {
            mockWebServer.shutdown();
        }
    }

    @Test
    void testHttpRequestBuilderExecuteGetNoDataFound() throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        try {
            String body = readJsonFile("nodatafound.json");
            mockWebServer.enqueue(new MockResponse().setBody(body).setResponseCode(404));
            mockWebServer.start();
            String endpoint = "success";
            String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
            ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(baseUrl);
            HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint);
            HttpRequestExecutor executor = httpRequestBuilder.get().withMediaType(ACCEPT_HEADER_V1);
            assertThrows(NoDataFoundException.class, () -> {
                try (HttpRequestResponse response = executor.execute()) {
                    assertNull(response);
                }
            });
        } finally {
            mockWebServer.shutdown();
        }
    }

    @Test
    void testHttpRequestBuilderExecutePostNoDataFound() throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        try {
            String body = readJsonFile("nodatafound.json");
            mockWebServer.enqueue(new MockResponse().setBody(body).setResponseCode(404));
            mockWebServer.start();
            String endpoint = "success";
            String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
            ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(baseUrl);
            HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint);
            HttpRequestExecutor executor = httpRequestBuilder.post().withBody("{test}").withMediaType(ACCEPT_HEADER_V1);
            assertThrows(NoDataFoundException.class, () -> {
                try (HttpRequestResponse response = executor.execute()) {
                    assertNull(response);
                }
            });
        } finally {
            mockWebServer.shutdown();
        }
    }

    @Test
    void testEnableHttp2() throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        try {
            String body = readJsonFile("success.json");
            mockWebServer.enqueue(new MockResponse().setBody(body).setResponseCode(200));
            mockWebServer.start();
            String endpoint = "success";
            String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
            ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(baseUrl);
            new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .enableHttp2();
            Provider[] providers = Security.getProviders();
            if (isBeforeJava8_251()) {
                assertTrue(containsBouncyCastleProvider(providers));
            } else {
                assertFalse(containsBouncyCastleProvider(providers));
            }
        } finally {
            mockWebServer.shutdown();
        }
    }

    private boolean containsBouncyCastleProvider(Provider[] providers) {
        boolean retVal = false;
        for (Provider provider : providers) {
            if (provider instanceof BouncyCastleProvider || provider instanceof BouncyCastleJsseProvider) {
                retVal = true;
                break;
            }
        }
        return retVal;
    }

    @Test
    void testHttpGetRequestBuilderServerNotFoundUnknownHost() throws IOException {
        String endpoint = "unknownhost";
        String baseUrl = "https://bogus-should-not-exist.rmanet.com";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(baseUrl);
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint);
        HttpRequestExecutor executor = httpRequestBuilder.get().withMediaType(ACCEPT_HEADER_V1);
        assertThrows(ServerNotFoundException.class, () -> {
            try (HttpRequestResponse response = executor.execute()) {
                assertNull(response);
            }
        });
    }

    @Test
    void testHttpPostRequestBuilderServerNotFoundUnknownHost() throws IOException {
        String endpoint = "unknownhost";
        String baseUrl = "https://bogus-should-not-exist.rmanet.com";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(baseUrl);
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint);
        HttpRequestExecutor executor = httpRequestBuilder.post().withBody("{test}").withMediaType(ACCEPT_HEADER_V1);
        assertThrows(ServerNotFoundException.class, () -> {
            try (HttpRequestResponse response = executor.execute()) {
                assertNull(response);
            }
        });
    }

    @Test
    void testHttpGetRequestBuilderServerNotFoundUnknownPort() throws IOException {
        String endpoint = "unknownport";
        String baseUrl = "https://www.hec.usace.army.mil:1000";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(baseUrl);
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint);
        HttpRequestExecutor executor = httpRequestBuilder.get().withMediaType(ACCEPT_HEADER_V1);
        assertThrows(ServerNotFoundException.class, () -> {
            try (HttpRequestResponse response = executor.execute()) {
                assertNull(response);
            }
        });
    }

    @Test
    void testHttpPostRequestBuilderServerNotFoundUnknownPort() throws IOException {
        String endpoint = "unknownport";
        String baseUrl = "https://www.hec.usace.army.mil:1000";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(baseUrl);
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint);
        HttpRequestExecutor executor = httpRequestBuilder.post().withBody("{test}").withMediaType(ACCEPT_HEADER_V1);
        assertThrows(ServerNotFoundException.class, () -> {
            try (HttpRequestResponse response = executor.execute()) {
                assertNull(response);
            }
        });
    }

    private String readJsonFile(String jsonPath) throws IOException {
        URL resource = getClass().getClassLoader().getResource(jsonPath);
        if (resource == null) {
            throw new IOException("Resource not found: " + jsonPath);
        }
        Path path = new File(resource.getFile()).toPath();
        return String.join("\n", Files.readAllLines(path));
    }

    private boolean isBeforeJava8_251() {
        boolean retVal = true;
        String version = System.getProperty("java.version");
        if (version.startsWith("1.")) {
            version = version.substring(2, 3);
        } else { //if Java 9 or higher
            int dot = version.indexOf(".");
            if (dot != -1) {
                version = version.substring(0, dot);
            }
        }
        int majorVersion = Integer.parseInt(version);
        if (majorVersion == 8) {
            String minorVersionStr = version.substring(version.lastIndexOf("_") + 1);
            retVal = Integer.parseInt(minorVersionStr) < 251;
        } else if (majorVersion > 8) {
            retVal = false;
        }
        return retVal;
    }
}
