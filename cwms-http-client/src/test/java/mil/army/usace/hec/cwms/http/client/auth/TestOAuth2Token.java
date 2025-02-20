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
        assertEquals("eyJhbGciOiJub25lIiwidHlwIjoiSldUIn0.eyJleHAiOjE2NjMyMDA0ODgsImlhdCI6MTY2MzE5OTg4OCwianRpIjoiM2JiNmZkMDItNTYzZi00ZWE0LTgzYzUtY2JlM2IzNTJjZmU4IiwiaXNzIjoiaHR0cHM6Ly9hdXRoLmNvcnBzLmNsb3VkL2F1dGgvcmVhbG1zL3dhdGVyIiwiYXVkIjpbImdyYWZhbmEiLCJ3b3JrZm9yY2UiLCJhY2NvdW50Il0sInN1YiI6IjUwMzUwMzZiLTFhNTItNGExNS1hNmFlLTRkNDk4ZTBlNTc1NSIsInR5cCI6IkJlYXJlciIsImF6cCI6ImN1bXVsdXMiLCJzZXNzaW9uX3N0YXRlIjoiYmRmNTdlNDktOTY0Ny00N2UzLWI5ZDYtMjBlYjAxNTBhMWZiIiwiYWNyIjoiMSIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLXdhdGVyIiwib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImdyYWZhbmEiOnsicm9sZXMiOlsidmlld2VyIl19LCJjdW11bHVzIjp7InJvbGVzIjpbInB1YmxpYy5wdWJsaWMiXX0sIndvcmtmb3JjZSI6eyJyb2xlcyI6WyJwdWJsaWMucHVibGljIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBlbWFpbCBwcm9maWxlIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJ0ZXN0LnVzZXIubmFtZSIsImdpdmVuX25hbWUiOiIiLCJmYW1pbHlfbmFtZSI6IiJ9.", token.getAccessToken());
        assertEquals("Bearer", token.getTokenType());
        assertEquals(3600, token.getExpiresIn());
        assertEquals("IwOGYzYTlmM2YxOTQ5MGE3YmNmMDFkNTVk", token.getRefreshToken());
        assertEquals("create", token.getScope());
        assertEquals("test.user.name", token.getClaimAsString("preferred_username").orElse(""));
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
