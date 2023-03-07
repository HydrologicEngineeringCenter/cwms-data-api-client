/*
 * Copyright (c) 2023
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URI;
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


    @Test
    void testCookieAccess() throws IOException {
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
        List<java.net.HttpCookie> cookies = preferencesBackedCookieStore.getCookies();
        assertFalse(cookies.isEmpty());
        List<URI> urIs = preferencesBackedCookieStore.getURIs();
        assertFalse(urIs.isEmpty());
        assertTrue(preferencesBackedCookieStore.remove(urIs.get(0), cookies.get(0)));
        preferencesBackedCookieStore.removeAll();
        cookies = preferencesBackedCookieStore.getCookies();
        assertTrue(cookies.isEmpty());
        urIs = preferencesBackedCookieStore.getURIs();
        assertTrue(urIs.isEmpty());
    }
}
