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

import java.util.Optional;
import javax.net.ssl.HostnameVerifier;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2TokenProvider;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;

final class OkHttpClientFactory {

    private OkHttpClientFactory() {

    }

    static OkHttpClient buildOkHttpClient(ApiConnectionInfo apiConnectionInfo) {
        Optional<SslSocketData> optionalSslSocketData = apiConnectionInfo.getSslSocketData();
        OkHttpClient retVal = OkHttpClientInstance.getInstance();
        if (optionalSslSocketData.isPresent()) {
            SslSocketData sslSocketData = optionalSslSocketData.get();
            retVal = retVal.newBuilder()
                .sslSocketFactory(sslSocketData.getSslSocketFactory(), sslSocketData.getX509TrustManager())
                .build();
        }
        Optional<OAuth2TokenProvider> optionalTokenProvider = apiConnectionInfo.getOAuth2TokenProvider();
        if (optionalTokenProvider.isPresent()) {
            retVal = retVal.newBuilder()
                .authenticator(new OAuth2TokenAuthenticator(optionalTokenProvider.get()))
                .addInterceptor(new OAuth2TokenInterceptor(optionalTokenProvider.get()))
                .build();
        }
        Optional<CookieJar> cookieJar = apiConnectionInfo.cookieJar();
        if (cookieJar.isPresent()) {
            retVal = retVal.newBuilder()
                .cookieJar(cookieJar.get())
                .build();
        }
        return retVal;
    }
}
