/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client;

import java.io.IOException;
import java.time.Duration;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public final class OkHttpUtil {

    private static final OkHttpClient CLIENT = new OkHttpClient.Builder()
        .callTimeout(Duration.ofMinutes(5))
        .connectTimeout(Duration.ofSeconds(30))
        .readTimeout(Duration.ofMinutes(5))
        .addInterceptor(new DefaultContentTypeInterceptor("application/json"))
        .build();

    private OkHttpUtil() {
        throw new AssertionError("Utility class");
    }

    public static OkHttpClient getClient() {
        return CLIENT;
    }

    private static class DefaultContentTypeInterceptor implements Interceptor {
        private final String contentType;

        public DefaultContentTypeInterceptor(String contentType) {
            this.contentType = contentType;
        }

        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request requestWithUserAgent = originalRequest
                .newBuilder()
                .header("Content-Type", contentType)
                .build();
            return chain.proceed(requestWithUserAgent);
        }
    }
}
