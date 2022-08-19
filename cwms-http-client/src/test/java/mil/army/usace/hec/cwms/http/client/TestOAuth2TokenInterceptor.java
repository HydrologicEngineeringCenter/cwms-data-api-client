package mil.army.usace.hec.cwms.http.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2TokenProvider;
import okhttp3.Request;
import org.junit.jupiter.api.Test;

class TestOAuth2TokenInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String ACCESS_TOKEN =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE2NTk5NzcyNjgsImV4cCI6MTY3NDQyMDc2NjgsImF1ZCI6Ind3dy5leGFtcGxlLmNvbSIsInN1YiI6Impyb2NrZXRAZXhhbXBsZS5jb20iLCJHaXZlbk5hbWUiOiJKb2hubnkiLCJTdXJuYW1lIjoiUm9ja2V0IiwiRW1haWwiOiJqcm9ja2V0QGV4YW1wbGUuY29tIiwiUm9sZSI6WyJNYW5hZ2VyIiwiUHJvamVjdCBBZG1pbmlzdHJhdG9yIl19.fuNn21aXq0ljupzvngo5_KHPwI4WYHP2UhSuSwP4NY8";

    @Test
    void testNewRequestWithAuthorizationHeader() throws IOException {
        OAuth2TokenProvider tokenProvider = getTestTokenProvider();
        OAuth2TokenInterceptor interceptor = new OAuth2TokenInterceptor(tokenProvider);
        Request request = getMockRequest();
        String accessToken = tokenProvider.getToken().getAccessToken();
        OAuth2Token oauth2Token = new OAuth2Token();
        oauth2Token.setTokenType("Bearer");
        oauth2Token.setAccessToken(accessToken);
        Request newRequest = interceptor.newRequestWithAccessTokenAsHeader(request, oauth2Token);
        assertNotNull(newRequest);
        assertEquals("Bearer " + accessToken, newRequest.headers(AUTHORIZATION_HEADER).get(0));
    }

    private Request getMockRequest() {
        return new Request.Builder()
            .url("https://some-url.com")
            .addHeader(AUTHORIZATION_HEADER, "Bearer " + ACCESS_TOKEN)
            .build();
    }

    private OAuth2TokenProvider getTestTokenProvider() {

        return new OAuth2TokenProvider() {
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
            public OAuth2Token newToken() throws IOException {
                OAuth2Token token = new OAuth2Token();
                token.setTokenType("Bearer");
                token.setAccessToken(ACCESS_TOKEN);
                token.setExpiresIn(3600);
                token.setRefreshToken(ACCESS_TOKEN);
                return token;
            }

        };
    }
}
