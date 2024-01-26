/*
 * MIT License
 *
 * Copyright (c) 2022 Hydrologic Engineering Center
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

import java.util.List;
import java.util.Optional;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

final class OkHttpClientFactory {

    private OkHttpClientFactory() {
        throw new AssertionError("Utility class");
    }

    static OkHttpClient buildOkHttpClient(ApiConnectionInfo apiConnectionInfo) {
        OkHttpClient.Builder builder = OkHttpClientInstance.getInstance().newBuilder();
        Optional<SslSocketData> optionalSslSocketData = apiConnectionInfo.sslSocketData();
        if (optionalSslSocketData.isPresent()) {
            SslSocketData sslSocketData = optionalSslSocketData.get();
            builder = builder.sslSocketFactory(sslSocketData.getSslSocketFactory(), sslSocketData.getX509TrustManager());
        }
        List<Interceptor> interceptors = apiConnectionInfo.interceptors();
        for (Interceptor interceptor : interceptors) {
            builder = builder.addInterceptor(interceptor);
        }
        builder = apiConnectionInfo.authenticator().map(builder::authenticator).orElse(builder);
        CookieJar cookieJar = apiConnectionInfo.cookieJar().orElse(CookieJar.NO_COOKIES);
        builder = builder.cache(apiConnectionInfo.cache().orElse(null));
        return builder.cookieJar(cookieJar).build();
    }
}
