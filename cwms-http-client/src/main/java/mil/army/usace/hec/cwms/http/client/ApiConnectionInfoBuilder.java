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

import java.util.ArrayList;
import java.util.List;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2TokenProvider;
import okhttp3.Authenticator;
import okhttp3.CookieJar;
import okhttp3.Interceptor;

public class ApiConnectionInfoBuilder {

    private final String apiRoot;
    private OAuth2TokenProvider tokenProvider;
    private SslSocketData sslSocketData;
    private CookieJarFactory.CookieJarSupplier cookieJarSupplier;
    private CookieAuthenticator cookieAuthenticator;

    public ApiConnectionInfoBuilder(String apiRoot) {
        this.apiRoot = apiRoot;
    }

    public ApiConnectionInfoBuilder withSslSocketData(SslSocketData sslSocketData) {
        this.sslSocketData = sslSocketData;
        return this;
    }

    public ApiConnectionInfoBuilder withTokenProvider(OAuth2TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
        return this;
    }

    public ApiConnectionInfoBuilder withCookieJarSupplier(CookieJarFactory.CookieJarSupplier cookieJarSupplier) {
        this.cookieJarSupplier = cookieJarSupplier;
        return this;
    }

    public ApiConnectionInfoBuilder withCookieAuthenticator(AuthCookieCallback callback) {
        cookieAuthenticator = new CookieAuthenticator(callback);
        return this;
    }

    public ApiConnectionInfo build() {
        CookieJar cookieJar = null;
        if (cookieJarSupplier != null) {
            cookieJar = cookieJarSupplier.getCookieJar();
        }
        Authenticator authenticator = null;
        List<Interceptor> interceptors = new ArrayList<>();
        if (tokenProvider != null) {
            authenticator = new OAuth2TokenAuthenticator(tokenProvider);
            interceptors.add(new OAuth2TokenInterceptor(tokenProvider));
        }
        if (cookieAuthenticator != null) {
            authenticator = cookieAuthenticator;
        }
        return new ApiConnectionInfo(apiRoot, sslSocketData, cookieJar, interceptors, authenticator);
    }
}
