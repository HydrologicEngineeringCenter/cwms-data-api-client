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
