/*
 * Copyright (c) 2023
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2TokenProvider;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;

class TestOAuth2TokenAuthenticator {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String ACCESS_TOKEN =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE2NTk5NzcyNjgsImV4cCI6MTY3NDQyMDc2NjgsImF1ZCI6Ind3dy5leGFtcGxlLmNvbSIsInN1YiI6Impyb2NrZXRAZXhhbXBsZS5jb20iLCJHaXZlbk5hbWUiOiJKb2hubnkiLCJTdXJuYW1lIjoiUm9ja2V0IiwiRW1haWwiOiJqcm9ja2V0QGV4YW1wbGUuY29tIiwiUm9sZSI6WyJNYW5hZ2VyIiwiUHJvamVjdCBBZG1pbmlzdHJhdG9yIl19.fuNn21aXq0ljupzvngo5_KHPwI4WYHP2UhSuSwP4NY8";

    private static final String EXPIRED_ACCESS_TOKEN =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE2NTk5NzcyNjgsImV4cCI6MCwiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoianJvY2tldEBleGFtcGxlLmNvbSIsIkdpdmVuTmFtZSI6IkpvaG5ueSIsIlN1cm5hbWUiOiJSb2NrZXQiLCJFbWFpbCI6Impyb2NrZXRAZXhhbXBsZS5jb20iLCJSb2xlIjpbIk1hbmFnZXIiLCJQcm9qZWN0IEFkbWluaXN0cmF0b3IiXX0.3z7xmBRXAmrSXp9YL-hgipOKNWYNW1P5pejf_W71EKo";

    @Test
    void testAuthenticate() throws IOException {
        OAuth2TokenProvider tokenProvider = new MyOAuth2TokenProvider();
        OAuth2TokenAuthenticator authenticator = new OAuth2TokenAuthenticator(tokenProvider);
        Response response = getMockResponse();
        Request request = authenticator.authenticate(null, response);
        assertNotNull(request);
        String refreshAccessToken = tokenProvider.refreshToken().getAccessToken();
        assertEquals("Bearer " + refreshAccessToken, request.headers(AUTHORIZATION_HEADER).get(0));
    }

    @Test
    void testAuthenticateNoAuthHeader() throws IOException {
        OAuth2TokenProvider tokenProvider = new MyOAuth2TokenProvider();
        OAuth2TokenAuthenticator authenticator = new OAuth2TokenAuthenticator(tokenProvider);
        Response response = getMockResponseNoAuthHeader();
        assertThrows(IOException.class, () -> authenticator.authenticate(null, response));
    }

    @Test
    void testAuthenticateNullToken() throws IOException {
        OAuth2TokenProvider tokenProvider = new MyOAuth2TokenProvider() {
            @Override
            public OAuth2Token getToken() {
                return null;
            }
        };
        OAuth2TokenAuthenticator authenticator = new OAuth2TokenAuthenticator(tokenProvider);
        Response response = getMockResponse();
        assertThrows(IOException.class, () -> authenticator.authenticate(null, response));
    }

    @Test
    void testAuthenticateExpiredToken() throws IOException {
        OAuth2TokenProvider tokenProvider = new MyOAuth2TokenProvider() {
            @Override
            public OAuth2Token getToken() {
                OAuth2Token token = super.getToken();
                token.setAccessToken(EXPIRED_ACCESS_TOKEN);
                token.setRefreshToken(EXPIRED_ACCESS_TOKEN);
                return token;
            }
        };
        OAuth2TokenAuthenticator authenticator = new OAuth2TokenAuthenticator(tokenProvider);
        Response response = getMockResponse();
        Request request = authenticator.authenticate(null, response);
        assertNotNull(request);
        String refreshAccessToken = tokenProvider.refreshToken().getAccessToken();
        assertEquals("Bearer " + refreshAccessToken, request.headers(AUTHORIZATION_HEADER).get(0));
    }

    @Test
    void testAuthenticateNullNewToken() throws IOException {
        OAuth2TokenProvider tokenProvider = new MyOAuth2TokenProvider() {
            @Override
            public OAuth2Token getToken() {
                OAuth2Token token = super.getToken();
                token.setAccessToken(EXPIRED_ACCESS_TOKEN);
                token.setRefreshToken(EXPIRED_ACCESS_TOKEN);
                return token;
            }

            @Override
            public OAuth2Token newToken() {
                return null;
            }
        };
        OAuth2TokenAuthenticator authenticator = new OAuth2TokenAuthenticator(tokenProvider);
        Response response = getMockResponse();
        assertThrows(IOException.class, () -> authenticator.authenticate(null, response));
        tokenProvider = new MyOAuth2TokenProvider() {
            @Override
            public OAuth2Token getToken() {
                OAuth2Token token = super.getToken();
                token.setAccessToken(EXPIRED_ACCESS_TOKEN);
                token.setRefreshToken(EXPIRED_ACCESS_TOKEN);
                return token;
            }

            @Override
            public OAuth2Token newToken() {
                OAuth2Token oAuth2Token = super.newToken();
                oAuth2Token.setAccessToken("");
                return oAuth2Token;
            }
        };
        OAuth2TokenAuthenticator authenticator2 = new OAuth2TokenAuthenticator(tokenProvider);
        Response response2 = getMockResponse();
        assertThrows(IOException.class, () -> authenticator2.authenticate(null, response2));
        tokenProvider = new MyOAuth2TokenProvider() {
            @Override
            public OAuth2Token getToken() {
                OAuth2Token token = super.getToken();
                token.setAccessToken(EXPIRED_ACCESS_TOKEN);
                token.setRefreshToken(EXPIRED_ACCESS_TOKEN);
                return token;
            }

            @Override
            public OAuth2Token newToken() {
                OAuth2Token oAuth2Token = super.newToken();
                oAuth2Token.setAccessToken(null);
                return oAuth2Token;
            }
        };
        OAuth2TokenAuthenticator authenticator3 = new OAuth2TokenAuthenticator(tokenProvider);
        Response response3 = getMockResponse();
        assertThrows(IOException.class, () -> authenticator3.authenticate(null, response3));
    }

    @Test
    void testAuthenticateNullNewRefresh() throws IOException {
        OAuth2TokenProvider tokenProvider = new MyOAuth2TokenProvider() {
            @Override
            public OAuth2Token refreshToken() {
                return null;
            }
        };
        OAuth2TokenAuthenticator authenticator = new OAuth2TokenAuthenticator(tokenProvider);
        Response response = getMockResponse();
        assertThrows(IOException.class, () -> authenticator.authenticate(null, response));
        tokenProvider = new MyOAuth2TokenProvider() {
            @Override
            public OAuth2Token refreshToken() {
                OAuth2Token oAuth2Token = super.refreshToken();
                oAuth2Token.setAccessToken("");
                return oAuth2Token;
            }
        };
        OAuth2TokenAuthenticator authenticator2 = new OAuth2TokenAuthenticator(tokenProvider);
        Response response2 = getMockResponse();
        assertThrows(IOException.class, () -> authenticator2.authenticate(null, response2));
        tokenProvider = new MyOAuth2TokenProvider() {
            @Override
            public OAuth2Token refreshToken() {
                OAuth2Token oAuth2Token = super.refreshToken();
                oAuth2Token.setAccessToken(null);
                return oAuth2Token;
            }
        };
        OAuth2TokenAuthenticator authenticator3 = new OAuth2TokenAuthenticator(tokenProvider);
        Response response3 = getMockResponse();
        assertThrows(IOException.class, () -> authenticator3.authenticate(null, response3));
    }

    private Response getMockResponse() {
        Request mockRequest = new Request.Builder()
            .url("https://some-url.com")
            .addHeader(AUTHORIZATION_HEADER, "Bearer " + ACCESS_TOKEN)
            .build();
        return new Response.Builder()
            .request(mockRequest)
            .protocol(Protocol.HTTP_2)
            .code(401) // status code
            .message("")
            .body(ResponseBody.create("{}",
                MediaType.get("application/json; charset=utf-8")
            ))
            .build();
    }

    private Response getMockResponseNoAuthHeader() {
        Request mockRequest = new Request.Builder()
            .url("https://some-url.com")
            .build();
        return new Response.Builder()
            .request(mockRequest)
            .protocol(Protocol.HTTP_2)
            .code(401) // status code
            .message("")
            .body(ResponseBody.create("{}",
                MediaType.get("application/json; charset=utf-8")
            ))
            .build();
    }

    private static class MyOAuth2TokenProvider implements OAuth2TokenProvider {
        @Override
        public OAuth2Token getToken() {
            OAuth2Token token = new OAuth2Token();
            token.setTokenType("Bearer");
            token.setAccessToken(ACCESS_TOKEN);
            token.setExpiresIn(3600);
            token.setRefreshToken(ACCESS_TOKEN);
            return token;
        }

        @Override
        public OAuth2Token refreshToken() {
            OAuth2Token token = new OAuth2Token();
            token.setTokenType("Bearer");
            token.setAccessToken(ACCESS_TOKEN);
            token.setExpiresIn(3600);
            token.setRefreshToken(ACCESS_TOKEN);
            return token;
        }

        @Override
        public OAuth2Token newToken() {
            OAuth2Token token = new OAuth2Token();
            token.setTokenType("Bearer");
            token.setAccessToken(ACCESS_TOKEN);
            token.setExpiresIn(3600);
            token.setRefreshToken(ACCESS_TOKEN);
            return token;
        }

    }
}
