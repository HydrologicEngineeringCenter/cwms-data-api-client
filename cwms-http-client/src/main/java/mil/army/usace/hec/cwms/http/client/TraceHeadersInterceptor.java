/*
 * Copyright (c) 2026
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client;

import java.io.IOException;
import java.util.UUID;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

final class TraceHeadersInterceptor implements Interceptor {

    private static final String TRACE_PARENT_HEADER = "traceparent";
    private static final String TRACE_ID_HEADER = "X-Trace-Id";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        String traceId = generateTraceId();
        String traceParent = createTraceParent(traceId);

        Request tracedRequest = request.newBuilder()
            .header(TRACE_ID_HEADER, traceId)
            .header(TRACE_PARENT_HEADER, traceParent)
            .build();

        return chain.proceed(tracedRequest);
    }

    private static String generateTraceId() {
        return UUID.randomUUID().toString().toLowerCase();
    }

    private static String createTraceParent(String traceId) {
        String traceIdHex = traceId.replace("-", "");
        String spanId = UUID.randomUUID().toString().replace("-", "").substring(0, 16).toLowerCase();
        return "00-" + traceIdHex + "-" + spanId + "-01";
    }
}
