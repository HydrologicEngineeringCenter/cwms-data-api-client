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
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jsse.provider.BouncyCastleJsseProvider;

import java.security.Security;
import java.util.Optional;

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
        if (!isHttp2NativelySupported()) {
            enableHttp2();
        }
        builder = apiConnectionInfo.authenticator().map(builder::authenticator).orElse(builder);
        CookieJar cookieJar = apiConnectionInfo.cookieJar().orElse(CookieJar.NO_COOKIES);
        return builder.cookieJar(cookieJar).build();
    }

    private static void enableHttp2() {
        //if Java 8 less than minor version 251, then use BouncyCastle to allow for HTTP/2 requests
        if (!isHttp2NativelySupported()) {
            Security.insertProviderAt(new BouncyCastleProvider(), 1);
            Security.insertProviderAt(new BouncyCastleJsseProvider(), 2);
        }
    }

    private static boolean isHttp2NativelySupported() {
        boolean retVal = false;
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
            retVal = Integer.parseInt(minorVersionStr) >= 251;
        } else if (majorVersion > 8) {
            retVal = true;
        }
        return retVal;
    }
}
