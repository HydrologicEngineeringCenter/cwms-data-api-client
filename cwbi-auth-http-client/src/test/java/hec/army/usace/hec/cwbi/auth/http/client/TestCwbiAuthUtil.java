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

import static hec.army.usace.hec.cwbi.auth.http.client.trustmanagers.CwbiAuthTrustManager.TOKEN_TEST_URL;
import static hec.army.usace.hec.cwbi.auth.http.client.trustmanagers.CwbiAuthTrustManager.TOKEN_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import javax.net.ssl.KeyManager;
import org.junit.jupiter.api.Test;

class TestCwbiAuthUtil {

    @Test
    void testBuildTokenProvider() throws IOException {
        CwbiAuthTokenProvider tokenProvider = (CwbiAuthTokenProvider) CwbiAuthUtil.buildCwbiAuthTokenProvider(TOKEN_URL, "cumulus", getTestKeyManager());
        assertEquals(TOKEN_URL, tokenProvider.getUrl());
        assertEquals("cumulus", tokenProvider.getClientId());
    }

    @Test
    void testNulls() {
        assertThrows(NullPointerException.class, () -> CwbiAuthUtil.buildCwbiAuthTokenProvider(TOKEN_TEST_URL, "cumulus", null));
    }

    private KeyManager getTestKeyManager() {
        return new KeyManager() {
        };
    }
}
