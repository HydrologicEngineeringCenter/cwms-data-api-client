package mil.army.usace.hec.cwms.http.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

final class TestCwmsHttpLogger {

    private static final String ACCEPT_HEADER_V1 = "application/json";

    @Test
    void testNothingRedacted() throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        CwmsHttpLoggingInterceptor loggingInterceptor = CwmsHttpLoggingInterceptor.getInstance();
        StringBuilder logOutput = new StringBuilder();
        loggingInterceptor.setLogLevel(Level.ALL);
        loggingInterceptor.setLogCollector(logOutput);
        try {
            String body = readJsonFile("success.json");
            mockWebServer.enqueue(new MockResponse().setBody(body).setResponseCode(200));
            mockWebServer.start();
            String endpoint = "success";
            String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
            ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfoBuilder(baseUrl).build();
            HttpRequestExecutor executer = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
            try (HttpRequestResponse response = executer.execute()) {
                assertNotNull(response.getBody());
            }
            assertTrue(logOutput.toString().contains(body));
        } finally {
            mockWebServer.shutdown();
        }
    }

    @Test
    void testRedacted() throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        CwmsHttpLoggingInterceptor loggingInterceptor = CwmsHttpLoggingInterceptor.getInstance();
        StringBuilder logOutput = new StringBuilder();
        loggingInterceptor.setLogLevel(Level.ALL);
        loggingInterceptor.setLogCollector(logOutput);
        try {
            String body = readJsonFile("tokens.json");
            mockWebServer.enqueue(new MockResponse().setBody(body).setResponseCode(200));
            mockWebServer.start();
            String endpoint = "tokens";
            String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
            ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfoBuilder(baseUrl).build();
            HttpRequestExecutor executer = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
            try (HttpRequestResponse response = executer.execute()) {
                assertNotNull(response.getBody());
            }
            assertFalse(logOutput.toString().contains("access_token"));
            assertFalse(logOutput.toString().contains("refresh_token"));
        } finally {
            mockWebServer.shutdown();
        }
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
