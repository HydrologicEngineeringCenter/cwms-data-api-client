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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.prefs.Preferences;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.JavaNetCookieJar;
import org.junit.jupiter.api.Test;

final class TestPreferencesBackedCookies {

    @Test
    void testHttpRequestResponse() throws IOException {
        Preferences node = Preferences.userRoot().node("test").node("cwms").node("http_client");
        PreferencesBackedCookieStore preferencesBackedCookieStore = new PreferencesBackedCookieStore(node);
        CookieJarFactory.CookieJarSupplier cookieJarSupplier = CookieJarFactory.preferenceBackedCookieJar(preferencesBackedCookieStore);
        try (HttpRequestResponse execute = new HttpRequestBuilderImpl(new ApiConnectionInfoBuilder("https://www.google.com")
            .withCookieJarSupplier(cookieJarSupplier)
            .build())
            .get()
            .withMediaType("application/json")
            .execute()) {
            Set<HttpCookie> cookies = execute.getCookies();
            assertFalse(cookies.isEmpty());
        }
        JavaNetCookieJar cookieJar = (JavaNetCookieJar) cookieJarSupplier.getCookieJar();
        HttpUrl httpUrl = HttpUrl.get("https://www.google.com");
        List<Cookie> cookies = cookieJar.loadForRequest(httpUrl);
        preferencesBackedCookieStore.writeCookiesToPreferences();

        CookieJar newCookieJar = CookieJarFactory.preferenceBackedCookieJar(new PreferencesBackedCookieStore(node)).getCookieJar();
        List<Cookie> persistedCookies = newCookieJar.loadForRequest(httpUrl);
        assertFalse(persistedCookies.isEmpty());
        for(Cookie cookie : persistedCookies) {
            Optional<Cookie> first = cookies.stream()
                .filter(c -> c.name().equals(cookie.name()))
                .filter(c -> c.domain().equals(cookie.domain()))
                .filter(c -> c.path().equals(cookie.path()))
                .filter(c -> c.expiresAt() == cookie.expiresAt())
                .findFirst();
            assertTrue(first.isPresent());
        }
    }
}
