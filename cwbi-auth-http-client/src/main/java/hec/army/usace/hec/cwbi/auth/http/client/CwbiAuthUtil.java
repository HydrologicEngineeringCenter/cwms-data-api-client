/*
 * MIT License
 *
 * Copyright (c) 2024 Hydrologic Engineering Center
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
package hec.army.usace.hec.cwbi.auth.http.client;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLSocketFactory;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2TokenProvider;

public final class CwbiAuthUtil {

    private static final Map<String, OAuth2TokenProvider> TOKEN_PROVIDER_CACHE = new ConcurrentHashMap<>();

    private CwbiAuthUtil() {
        throw new AssertionError("Utility class");
    }

    /**
     * Builds CumulusTokenProvider for retrieving and refreshing tokens for cumulus authentication.
     * Caches the TokenProvider instance per KeyCloak token URL to prevent redundant creation.
     * Ensures thread safety and propagates IOException.
     *
     * @param tokenUrl  - KeyCloak token URL
     * @param clientId  - Client ID for authentication
     * @param keyManager - KeyManager for client SSL
     * @return OAuth2TokenProvider - Cached or newly created TokenProvider
     * @throws IOException - thrown if failed to build CumulusTokenProvider
     */
    public static OAuth2TokenProvider buildCwbiAuthTokenProvider(String tokenUrl, String clientId, KeyManager keyManager) throws IOException {
        Objects.requireNonNull(tokenUrl, "Missing required tokenUrl");
        Objects.requireNonNull(clientId, "Missing required clientId");
        Objects.requireNonNull(keyManager, "Missing required KeyManager");

        try {
            return TOKEN_PROVIDER_CACHE.computeIfAbsent(tokenUrl, url -> {
                try {
                    SSLSocketFactory sslSocketFactory = CwbiAuthSslSocketFactory.buildSSLSocketFactory(
                            Collections.singletonList(keyManager));
                    return new CwbiAuthTokenProvider(url, clientId, sslSocketFactory);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        } catch (UncheckedIOException e) {
            throw e.getCause();
        }
    }
}


