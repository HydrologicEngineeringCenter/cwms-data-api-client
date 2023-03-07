/*
 * Copyright (c) 2023
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * Follows example from <a href="https://docs.oracle.com/javase/tutorial/networking/cookies/custom.html">Custom CookieManager</a>
 * for persisting cookies in Java. Delegate to default java.net.InMemoryCookieStore and add hooks for storing to preferences.
 */
public final class PreferencesBackedCookieStore implements CookieStore {
    private static final Logger LOGGER = Logger.getLogger(PreferencesBackedCookieStore.class.getName());
    //Used for preference readability only. Since the keys aren't read on restore this can be changed to anything
    private static final String DOMAIN_COOKIE_DELIMITER = " | ";
    private static final String URI_KEY = "URI";
    private final Preferences preferences;
    private final CookieManager cookieManager;

    public PreferencesBackedCookieStore(Preferences preferences) {
        this.preferences = preferences.node("cwms_http_client_cookies");
        this.cookieManager = new CookieManager();
        restoreCookiesFromPreferences();
        Runtime.getRuntime().addShutdownHook(new Thread(this::writeCookiesToPreferences));
    }

    @Override
    public void add(URI uri, HttpCookie cookie) {
        cookieManager.getCookieStore().add(uri, cookie);
    }

    @Override
    public List<HttpCookie> get(URI uri) {
        return cookieManager.getCookieStore().get(uri);
    }

    @Override
    public List<HttpCookie> getCookies() {
        return cookieManager.getCookieStore().getCookies();
    }

    @Override
    public List<URI> getURIs() {
        return cookieManager.getCookieStore().getURIs();
    }

    @Override
    public boolean remove(URI uri, HttpCookie cookie) {
        return cookieManager.getCookieStore().remove(uri, cookie);
    }

    @Override
    public boolean removeAll() {
        return cookieManager.getCookieStore().removeAll();
    }

    private void restoreCookiesFromPreferences() {
        try {
            Map<URI, List<String>> uriToCookie = extractCookiesFromPreferences();
            for (Map.Entry<URI, List<String>> entry : uriToCookie.entrySet()) {
                try {
                    Map<String, List<String>> storingCookies = new HashMap<>();
                    storingCookies.put("Set-Cookie", entry.getValue());
                    cookieManager.put(entry.getKey(), storingCookies);
                } catch (IOException e) {
                    LOGGER.log(Level.WARNING, e, () -> "Unable to store preference cookies to cookie manager for URL: " + entry.getKey());
                }
            }
        } catch (BackingStoreException e) {
            LOGGER.log(Level.CONFIG, e, () -> "Unable to obtain cookies stored in preferences at node: " + preferences.absolutePath());
        }
    }

    private Map<URI, List<String>> extractCookiesFromPreferences() throws BackingStoreException {
        Map<URI, List<String>> uriToCookie = new HashMap<>();
        for (String child : preferences.childrenNames()) {
            Preferences node = preferences.node(child);
            String uriAddress = node.get(URI_KEY, "");
            URI uri = URI.create(uriAddress);
            List<String> cookies = uriToCookie.computeIfAbsent(uri, u -> new ArrayList<>());
            for (String cookieName : node.keys()) {
                if (!URI_KEY.equals(cookieName)) {
                    cookies.add(node.get(cookieName, ""));
                }
            }
        }
        return uriToCookie;
    }

    public void writeCookiesToPreferences() {
        try {
            List<URI> urIs = cookieManager.getCookieStore().getURIs();
            for (URI key : urIs) {
                try {
                    for (List<String> value : cookieManager.get(key, new HashMap<>()).values()) {
                        storeCookiesForUrl(key, value);
                    }
                    //The InMemoryCookieStore strips out the 's' in "https" on storage. Need to check cookies for both URI's
                    if (key.toString().contains("http:")) {
                        URI https = URI.create(key.toString().replace("http:", "https:"));
                        for (List<String> value : cookieManager.get(https, new HashMap<>()).values()) {
                            storeCookiesForUrl(https, value);
                        }
                    }
                } catch (IOException e) {
                    LOGGER.log(Level.WARNING, e, () -> "Unable to obtain cookies for url: " + key);
                }
            }
            preferences.flush();
        } catch (BackingStoreException e) {
            LOGGER.log(Level.WARNING, e, () -> "Unable to write cookies to preferences node: " + preferences.absolutePath());
        }
    }

    private void storeCookiesForUrl(URI key, List<String> value) {
        Preferences node = preferences.node(key.getHost());
        node.put(URI_KEY, key.toString());
        for (String cookie : value) {
            HttpUrl httpUrl = HttpUrl.get(key);
            if (httpUrl != null) {
                Cookie parse = Cookie.parse(httpUrl, cookie);
                if (parse != null) {
                    node.put((parse.domain() + DOMAIN_COOKIE_DELIMITER + parse.name()).toLowerCase(), cookie);
                }
            }
        }
    }
}
