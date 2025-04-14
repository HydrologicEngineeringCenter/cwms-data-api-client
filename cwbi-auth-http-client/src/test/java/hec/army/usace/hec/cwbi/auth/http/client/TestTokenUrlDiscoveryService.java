/*
 * MIT License
 *
 * Copyright (c) 2025 Hydrologic Engineering Center
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

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfoBuilder;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

final class TestTokenUrlDiscoveryService {

    @Test
    void testDiscoverTokenUrl() {
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfoBuilder("http://localhost:8080").build();
        TokenUrlDiscoveryService tokenUrlDiscoveryService = new TokenUrlDiscoveryService(apiConnectionInfo) {
            @Override
            protected String retrieveTokenUrl(ApiConnectionInfo apiConnectionInfo) {
                return "http://token.url";
            }
        };
        assertSame(apiConnectionInfo, tokenUrlDiscoveryService.getApiConnectionInfo());
        assertEquals("http://token.url", tokenUrlDiscoveryService.discoverTokenUrl());
    }

    @Test
    void testNulls() {
        Assertions.assertThrows(NullPointerException.class, () -> new TokenUrlDiscoveryService(null) {
            @Override
            protected String retrieveTokenUrl(ApiConnectionInfo apiConnectionInfo) {
                return null;
            }
        });
    }
}
