/*
 * Copyright (c) 2023
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class TestOAuth2Token {

    @Test
    void testDeserialization() throws IOException {
        ObjectMapper om = new ObjectMapper();
        String json = readJsonFile();
        OAuth2Token token = om.readValue(json, OAuth2Token.class);
        assertEquals("MTQ0NjJkZmQ5OTM2NDE1ZTZjNGZmZjI3", token.getAccessToken());
        assertEquals("Bearer", token.getTokenType());
        assertEquals(3600, token.getExpiresIn());
        assertEquals("IwOGYzYTlmM2YxOTQ5MGE3YmNmMDFkNTVk", token.getRefreshToken());
        assertEquals("create", token.getScope());
    }

    @Test
    void testEquals() throws IOException {
        ObjectMapper om = new ObjectMapper();
        String json = readJsonFile();
        OAuth2Token token = om.readValue(json, OAuth2Token.class);
        OAuth2Token token2 = om.readValue(json, OAuth2Token.class);
        assertEquals(token.hashCode(), token2.hashCode());
        assertEquals(token, token2);
        assertEquals(token.toString(), token2.toString());
        assertEquals(token, token);
    }

    @Test
    void testNotEquals() throws IOException {
        ObjectMapper om = new ObjectMapper();
        String json = readJsonFile();
        OAuth2Token token = om.readValue(json, OAuth2Token.class);
        assertNotEquals(token, new Object());
        assertNotEquals(token, null);
        OAuth2Token token2 = new OAuth2Token();
        assertNotEquals(token.hashCode(), token2.hashCode());
        assertNotEquals(token, token2);
        assertNotEquals(token.toString(), token2.toString());
        assertNotEquals(token, token2);
        token2.setExpiresIn(token.getExpiresIn());
        assertNotEquals(token, token2);
        token2.setAccessToken(token.getAccessToken());
        assertNotEquals(token, token2);
    }

    private String readJsonFile() throws IOException {
        String jsonPath = "oauth2token.json";
        URL resource = getClass().getClassLoader().getResource(jsonPath);
        if (resource == null) {
            throw new IOException("Resource not found: " + jsonPath);
        }
        Path path = new File(resource.getFile()).toPath();
        return String.join("\n", Files.readAllLines(path));
    }
}
