/*
 * MIT License
 *
 * Copyright (c) 2023 Hydrologic Engineering Center
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

package mil.army.usace.hec.cwms.http.client.auth;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TestDefaultOAuth2TokenValidator {

    @Test
    void testOAuth2TokenMissingAccessToken() {
        OAuth2Token token = new OAuth2Token();
        token.setAccessToken("");
        token.setTokenType("Bearer");
        token.setExpiresIn(100);
        DefaultOAuth2TokenValidator oauth2TokenValidator = new DefaultOAuth2TokenValidator();
        assertThrows(OAuth2TokenException.class, () -> oauth2TokenValidator.validateToken(token));
        token.setAccessToken(null);
        assertThrows(OAuth2TokenException.class, () -> oauth2TokenValidator.validateToken(token));
    }

    @Test
    void testOAuth2TokenMissingType() {
        OAuth2Token token = new OAuth2Token();
        token.setAccessToken("asfksdfjh1k2j3h123124234123kjnnskak");
        token.setTokenType(null);
        token.setExpiresIn(100);
        DefaultOAuth2TokenValidator oauth2TokenValidator = new DefaultOAuth2TokenValidator();
        assertThrows(OAuth2TokenException.class, () -> oauth2TokenValidator.validateToken(token));
        token.setTokenType("");
        assertThrows(OAuth2TokenException.class, () -> oauth2TokenValidator.validateToken(token));
    }

    @Test
    void testOAuth2TokenExpired() {
        OAuth2Token token = new OAuth2Token();
        token.setAccessToken("asfksdfjh1k2j3h123124234123kjnnskak");
        token.setTokenType("Bearer");
        token.setExpiresIn(0);
        DefaultOAuth2TokenValidator oauth2TokenValidator = new DefaultOAuth2TokenValidator();
        assertThrows(OAuth2TokenException.class, () -> oauth2TokenValidator.validateToken(token));
    }

    @Test
    void testValidOAuth2Token() {
        OAuth2Token token = new OAuth2Token();
        token.setAccessToken("asfksdfjh1k2j3h123124234123kjnnskak");
        token.setTokenType("Bearer");
        token.setExpiresIn(Long.MAX_VALUE);
        DefaultOAuth2TokenValidator oauth2TokenValidator = new DefaultOAuth2TokenValidator();
        assertDoesNotThrow(() -> oauth2TokenValidator.validateToken(token));
    }
}
