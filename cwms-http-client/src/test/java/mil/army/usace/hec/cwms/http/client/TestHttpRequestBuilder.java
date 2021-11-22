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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import okhttp3.Request;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

class TestHttpRequestBuilder {

    @Test
    void testHttpRequestBuilderCreateRequest() throws IOException {
        String root = "http://localhost:11524/cwms-data/";
        String endpoint = "timeseries";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder(apiConnectionInfo, endpoint);
        Request request = httpRequestBuilder.createRequest();
        assertEquals(root + endpoint, request.url().toString());
    }

    @Test
    void testHttpRequestBuilderCreateRequestInvalidUrl() throws IOException {
        String root = "//http://localhost:11524/cwms-data/";
        String endpoint = "timeseries";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        assertThrows(ServerNotFoundException.class, () -> new HttpRequestBuilder(apiConnectionInfo, endpoint));
    }

    @Test
    void testHttpRequestBuilderCreateRequestNullRoot() throws IOException {
        String root = null;
        String endpoint = "timeseries";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        assertThrows(NullPointerException.class, () -> new HttpRequestBuilder(apiConnectionInfo, endpoint));
    }

    @Test
    void testHttpRequestBuilderCreateRequestNullEndpoint() throws IOException {
        String root = "http://localhost:11524/cwms-data/";
        String endpoint = null;
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        assertThrows(NullPointerException.class, () -> new HttpRequestBuilder(apiConnectionInfo, endpoint));
    }

    @Test
    void testHttpRequestBuilderCreateRequestWithParameters() throws IOException {
        String root = "http://localhost:11524/cwms-data/";
        String endpoint = "timeseries";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder(apiConnectionInfo, endpoint)
            .addQueryParameter("hello", "world")
            .addQueryParameter("green-eggs", "and ham");
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
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder(apiConnectionInfo, endpoint)
            .addQueryParameter("hello", null);
        Request request = httpRequestBuilder.createRequest();
        assertFalse(request.url().toString().contains("hello="));
    }

    @Test
    void testHttpRequestBuilderCreateRequestWithHeaders() throws IOException {
        String root = "http://localhost:11524/cwms-data/";
        String endpoint = "timeseries";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder(apiConnectionInfo, endpoint)
            .addQueryHeader("hello", "world")
            .addQueryHeader("green-eggs", "and ham");
        Request request = httpRequestBuilder.createRequest();
        assertTrue(request.headers("hello").contains("world"));
        assertTrue(request.headers("green-eggs").contains("and ham"));
    }

    @Test
    void testHttpRequestBuilderCreateRequestWithNullHeader() throws IOException {
        String root = "http://localhost:11524/cwms-data/";
        String endpoint = "timeseries";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder(apiConnectionInfo, endpoint)
            .addQueryHeader("hello", null);
        Request request = httpRequestBuilder.createRequest();
        assertTrue(request.headers("hello").isEmpty());
    }

    @Test
    void testHttpRequestBuilderCreateRequestWithEndpointInput() throws IOException {
        String root = "http://localhost:11524/cwms-data/";
        String endpoint = "timeseries";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder(apiConnectionInfo, endpoint)
            .addEndpointInput(new EndpointInput() {
                @Override
                protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
                    return httpRequestBuilder.addQueryHeader("hello", "world")
                                             .addQueryHeader("green-eggs", "and ham")
                                             .addQueryParameter("hello", "world")
                                             .addQueryParameter("green-eggs", "and ham");
                }
            });
        Request request = httpRequestBuilder.createRequest();
        assertTrue(request.url().toString().startsWith(root + endpoint + "?"));
        assertTrue(request.url().toString().contains("hello=world"));
        assertTrue(request.url().toString().contains("green-eggs=and%20ham"));
        assertTrue(request.headers("hello").contains("world"));
        assertTrue(request.headers("green-eggs").contains("and ham"));
    }

    @Test
    void testHttpRequestBuilderExecuteSuccess() throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        try {
            String body = readJsonFile("success.json");
            mockWebServer.enqueue(new MockResponse().setBody(body).setResponseCode(200));
            mockWebServer.start();
            String endpoint = "success";
            String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
            ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(baseUrl);
            HttpRequestResponse execute = new HttpRequestBuilder(apiConnectionInfo, endpoint)
                .execute();
            assertNotNull(execute.getBody());
        }
        finally {
            mockWebServer.shutdown();
        }
    }

    @Test
    void testHttpRequestBuilderExecuteServerError() throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        try {
            String body = readJsonFile("servererror.json");
            mockWebServer.enqueue(new MockResponse().setBody(body).setResponseCode(500));
            mockWebServer.start();
            String endpoint = "success";
            String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
            ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(baseUrl);
            HttpRequestBuilder builder = new HttpRequestBuilder(apiConnectionInfo, endpoint);
            assertThrows(IOException.class, builder::execute);
        }
        finally {
            mockWebServer.shutdown();
        }
    }

    @Test
    void testHttpRequestBuilderExecuteNoDataFound() throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        try {
            String body = readJsonFile("nodatafound.json");
            mockWebServer.enqueue(new MockResponse().setBody(body).setResponseCode(404));
            mockWebServer.start();
            String endpoint = "success";
            String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
            ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(baseUrl);
            HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder(apiConnectionInfo, endpoint);
            assertThrows(NoDataFoundException.class, httpRequestBuilder::execute);
        }
        finally {
            mockWebServer.shutdown();
        }
    }

    @Test
    void testHttpRequestBuilderServerNotFoundUnknownHost() throws IOException {
        String endpoint = "unknownhost";
        String baseUrl = "https://bogus-should-not-exist.rmanet.com";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(baseUrl);
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder(apiConnectionInfo, endpoint);
        assertThrows(ServerNotFoundException.class, httpRequestBuilder::execute);
    }

    @Test
    void testHttpRequestBuilderServerNotFoundUnknownPort() throws IOException {
        String endpoint = "unknownport";
        String baseUrl = "https://www.hec.usace.army.mil:1000";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(baseUrl);
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder(apiConnectionInfo, endpoint);
        assertThrows(ServerNotFoundException.class, httpRequestBuilder::execute);
    }

    private String readJsonFile(String jsonPath) throws IOException {
        URL resource = getClass().getClassLoader().getResource(jsonPath);
        if (resource == null) {
            throw new IOException("Resource not found: " + jsonPath);
        }
        Path path = new File(resource.getFile()).toPath();
        return String.join("\n", Files.readAllLines(path));
    }
}
