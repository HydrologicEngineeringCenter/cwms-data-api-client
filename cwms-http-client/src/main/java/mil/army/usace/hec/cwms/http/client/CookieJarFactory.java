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

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.util.List;
import java.util.Optional;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.JavaNetCookieJar;

public final class CookieJarFactory {

    private CookieJarFactory() {
        throw new AssertionError("Utility class");
    }

    public static CookieJarSupplier inMemoryCookieJar() {
        return new InMemoryCookieJarSupplier();
    }

    public static CookieJarSupplier preferenceBackedCookieJar(PreferencesBackedCookieStore cookieStore) {
        return new InMemoryCookieJarSupplier(cookieStore);
    }

    public abstract static class CookieJarSupplier {
        abstract CookieJar getCookieJar();

        public boolean isCookieExpired(String apiRootUrl, String cookieName) {
            CookieJar cookieJar = getCookieJar();
            List<Cookie> cookies = cookieJar.loadForRequest(HttpUrl.get(apiRootUrl));
            if (cookies.isEmpty()) {
                return true;
            } else {
                return cookies.stream()
                    .filter(c -> c.name().equalsIgnoreCase(cookieName))
                    .anyMatch(c -> c.expiresAt() < System.currentTimeMillis());
            }
        }

        public Optional<? extends HttpCookie> getCookie(String url, String cookieName) {
            CookieJar cookieJar = getCookieJar();
            return cookieJar.loadForRequest(HttpUrl.get(url))
                .stream()
                .filter(c -> c.name().equalsIgnoreCase(cookieName))
                .map(OkHttpCookieWrapper::new)
                .findFirst();
        }
    }

    private static final class InMemoryCookieJarSupplier extends CookieJarSupplier {

        private final JavaNetCookieJar cookieJar;

        private InMemoryCookieJarSupplier() {
            //null cookie store defaults to java.net.InMemoryCookieStore by default
            CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER);
            this.cookieJar = new JavaNetCookieJar(cookieManager);
        }

        private InMemoryCookieJarSupplier(CookieStore store) {
            //null cookie store defaults to java.net.InMemoryCookieStore by default
            CookieManager cookieManager = new CookieManager(store, CookiePolicy.ACCEPT_ORIGINAL_SERVER);
            this.cookieJar = new JavaNetCookieJar(cookieManager);
        }

        @Override
        CookieJar getCookieJar() {
            return cookieJar;
        }
    }

}
