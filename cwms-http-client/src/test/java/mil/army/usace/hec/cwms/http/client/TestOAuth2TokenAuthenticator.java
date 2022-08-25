package mil.army.usace.hec.cwms.http.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @Test
    void testAuthenticate() throws IOException {
        OAuth2TokenProvider tokenProvider = getTestTokenProvider();
        OAuth2TokenAuthenticator authenticator = new OAuth2TokenAuthenticator(tokenProvider);
        Response response = getMockResponse();
        Request request = authenticator.authenticate(null, response);
        assertNotNull(request);
        String refreshAccessToken = tokenProvider.refreshToken().getAccessToken();
        assertEquals("Bearer " + refreshAccessToken, request.headers(AUTHORIZATION_HEADER).get(0));
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
