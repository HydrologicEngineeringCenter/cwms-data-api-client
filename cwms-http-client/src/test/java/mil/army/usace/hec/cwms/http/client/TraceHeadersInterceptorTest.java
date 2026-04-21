/*
 * Copyright (c) 2026
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.regex.Pattern;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

final class TraceHeadersInterceptorTest {

    private static final Pattern TRACE_ID_PATTERN =
        Pattern.compile("[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}", Pattern.CASE_INSENSITIVE);


    @Test
    void testCookiesNotLogged() throws IOException, InterruptedException {
        try(MockWebServer mockWebServer = new MockWebServer()) {
            MockResponse mockResponse = new MockResponse()
                .setBody("test")
                .setResponseCode(200);
            mockWebServer.enqueue(mockResponse);
            mockWebServer.start();

            Request request = new Request.Builder()
                .url(mockWebServer.url("/test"))
                .build();
            try (Response response = OkHttpClientInstance.getInstance().newCall(request).execute()) {
                assertTrue(response.isSuccessful());
            }

            okhttp3.mockwebserver.RecordedRequest recordedRequest = mockWebServer.takeRequest();
            String traceId = recordedRequest.getHeader("X-Trace-Id");
            String traceParent = recordedRequest.getHeader("traceparent");

            assertTrue(traceId != null && TRACE_ID_PATTERN.matcher(traceId).matches(),
                "X-Trace-Id should be a UUID-like value");
            assertTrue(traceParent != null && traceParent.matches(
                    "00-[a-f0-9]{32}-[a-f0-9]{16}-[a-f0-9]{2}"),
                "traceparent should follow W3C format: https://www.w3.org/TR/trace-context/#traceparent-header");
            assertTrue(traceParent.startsWith("00-"));
            assertEquals(4, traceParent.split("-").length);
        }
    }
}
