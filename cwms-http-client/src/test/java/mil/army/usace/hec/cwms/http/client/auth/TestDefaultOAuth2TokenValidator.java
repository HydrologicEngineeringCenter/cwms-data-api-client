/*
 * Copyright (c) 2023
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
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
