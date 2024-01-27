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
