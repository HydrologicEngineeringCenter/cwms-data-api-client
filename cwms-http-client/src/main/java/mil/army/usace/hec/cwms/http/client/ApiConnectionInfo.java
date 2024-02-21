/*
 * MIT License
 *
 * Copyright (c) 2023 Hydrologic Engineering Center
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
import javax.net.ssl.HostnameVerifier;
import okhttp3.Authenticator;
import okhttp3.CookieJar;
import okhttp3.Interceptor;

public final class ApiConnectionInfo {

    private final String apiRoot;
    private final SslSocketData sslSocketData;
    private final CookieJar cookieJar;
    private final List<Interceptor> interceptors;
    private final Authenticator authenticator;
    private final CacheFactory.CacheSupplier cacheSupplier;
    private final HostnameVerifier hostnameVerifier;

    ApiConnectionInfo(String apiRoot, SslSocketData sslSocketData, CookieJar cookieJar,
                      List<Interceptor> interceptors, Authenticator authenticator, HostnameVerifier hostnameVerifier, CacheFactory.CacheSupplier cache) {
        this.apiRoot = apiRoot;
        this.sslSocketData = sslSocketData;
        this.interceptors = interceptors;
        this.authenticator = authenticator;
        this.cookieJar = cookieJar;
        this.cacheSupplier = cache;
        this.hostnameVerifier = hostnameVerifier;
    }


    public String getApiRoot() {
        return apiRoot;
    }

    Optional<Authenticator> authenticator() {
        return Optional.ofNullable(authenticator);
    }

    List<Interceptor> interceptors() {
        return interceptors;
    }

    Optional<SslSocketData> sslSocketData() {
        return Optional.ofNullable(sslSocketData);
    }

    Optional<CookieJar> cookieJar() {
        return Optional.ofNullable(cookieJar);
    }

    Optional<HostnameVerifier> hostnameVerifier() {
        return Optional.ofNullable(hostnameVerifier);
    }

    Optional<CacheFactory.CacheSupplier> cacheSupplier() {
        return Optional.ofNullable(cacheSupplier);
    }

}
