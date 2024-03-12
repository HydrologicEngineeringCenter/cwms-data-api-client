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

package mil.army.usace.hec.cwms.http.client;

import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


final class TestOkHttpCache {

    @AfterEach
    public void tearDown() {
        System.clearProperty(CwmsHttpCache.CACHE_DIRECTORY_PROPERTY_KEY);
    }

    @Test
    void testOkHttpCache() throws Exception {
        try (MockHttpServer mockServer = MockHttpServer.create()) {
            Path cacheDirectory = CwmsHttpCache.CACHE_DEFAULT_DIRECTORY.getParent().resolve("test");
            System.setProperty(CwmsHttpCache.CACHE_DIRECTORY_PROPERTY_KEY, cacheDirectory.toString());
            List<String> cacheControls = new ArrayList<>();
            cacheControls.add("max-age=3600");
            mockServer.enqueueWithCache("Mock response body", cacheControls);
            mockServer.start();
            ApiConnectionInfo apiConnectionInfo = buildMockApiConnectionInfo(mockServer);
            HttpRequestExecutor request = new HttpRequestBuilderImpl(apiConnectionInfo)
                    .get()
                    .withMediaType("text/plain");
            try (HttpRequestResponse response = request.execute()) {
                assertEquals("Mock response body", response.getBody());
                assertFalse(response.usedCache());
            }
            mockServer.enqueueWithCache("Mock response body", cacheControls);
            HttpRequestExecutor request2 = new HttpRequestBuilderImpl(apiConnectionInfo)
                    .get()
                    .withMediaType("text/plain");
            try (HttpRequestResponse response2 = request2.execute()) {
                assertEquals("Mock response body", response2.getBody());
                assertTrue(response2.usedCache());
            }
        }
    }

    private ApiConnectionInfo buildMockApiConnectionInfo(MockHttpServer mockHttpServer) {
        CwmsHttpCache build = new CwmsHttpCache.Builder().build();
        return new ApiConnectionInfoBuilder(String.format("http://localhost:%s", mockHttpServer.getPort()))
                .withCacheSupplier(CacheFactory.okHttpCacheSupplier(build))
                .build();
    }
}
