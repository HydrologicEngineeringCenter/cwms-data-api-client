/*
 * MIT License
 *
 * Copyright (c) 2021 Hydrologic Engineering Center
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;
import org.junit.jupiter.api.Test;

class TestApiConnectionInfo {

    @Test
    void testApiConnectionInfo() {
        String root = "http://localhost:11524/cwms-data/";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        assertEquals(root, apiConnectionInfo.getApiRoot());
    }

    @Test
    void testApiConnectionInfoWithOAuth2Token() {
        OAuth2Token token = new OAuth2Token();
        token.setAccessToken("MTQ0NjJkZmQ5OTM2NDE1ZTZjNGZmZjI3");
        token.setTokenType("Bearer");
        token.setExpiresIn(3600);
        String root = "http://localhost:11524/cwms-data/";
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root, token);
        assertEquals(root, apiConnectionInfo.getApiRoot());
        assertTrue(apiConnectionInfo.getOAuth2Token().isPresent());
        assertEquals(token, apiConnectionInfo.getOAuth2Token().get());
    }

    @Test
    void testApiConnectionInfoNulls() {
        String root = null;
        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfo(root);
        assertNull(apiConnectionInfo.getApiRoot());
    }

}
