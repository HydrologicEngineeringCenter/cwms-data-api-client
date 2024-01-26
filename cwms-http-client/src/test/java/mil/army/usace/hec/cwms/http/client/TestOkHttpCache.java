package mil.army.usace.hec.cwms.http.client;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import mil.army.usace.hec.cwms.htp.client.MockHttpServer;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class TestOkHttpCache {

    private void resetSingleton() throws Exception {
        Arrays.stream(Logger.getLogger(OkHttpClientInstance.class.getName()).getHandlers())
                .forEach(h -> h.setLevel(Level.FINEST));
        Logger.getLogger(OkHttpClientInstance.class.getName()).setLevel(Level.FINEST);
        Field cacheField = CwmsHttpCache.class.getDeclaredField("INSTANCE");
        cacheField.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(cacheField, cacheField.getModifiers() & ~Modifier.FINAL);
        cacheField.set(null, new CwmsHttpCache.Builder().build());
        Field field = OkHttpClientInstance.class.getDeclaredField("INSTANCE");
        field.setAccessible(true);
        modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, OkHttpClientInstance.createClient());
    }

    @Test
    void testOkHttpCache() throws Exception {
        MockHttpServer mockServer = MockHttpServer.create();
        Path cacheDirectory = CwmsHttpCache.CACHE_DEFAULT_DIRECTORY.getParent().resolve("test");
        System.setProperty(CwmsHttpCache.CACHE_DIRECTORY_PROPERTY_KEY, cacheDirectory.toString());
        try {
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
        } finally {
            System.clearProperty(CwmsHttpCache.CACHE_DIRECTORY_PROPERTY_KEY);
            resetSingleton();
            mockServer.shutdown();
        }
    }

    private ApiConnectionInfo buildMockApiConnectionInfo(MockHttpServer mockHttpServer) {
        return new ApiConnectionInfoBuilder(String.format("http://localhost:%s", mockHttpServer.getPort()))
                .build();
    }
}
