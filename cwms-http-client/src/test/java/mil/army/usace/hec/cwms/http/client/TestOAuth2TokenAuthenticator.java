package mil.army.usace.hec.cwms.http.client;

import static mil.army.usace.hec.cwms.http.client.OAuth2TokenAuthenticator.AUTHORIZATION_HEADER;
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
            .addHeader(AUTHORIZATION_HEADER, "Bearer abc123")
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
                token.setAccessToken("abc123");
                token.setExpiresIn(3600);
                token.setRefreshToken("123abc");
                return token;
            }

            @Override
            public OAuth2Token refreshToken() {
                OAuth2Token token = new OAuth2Token();
                token.setTokenType("Bearer");
                token.setAccessToken("xyz456");
                token.setExpiresIn(3600);
                token.setRefreshToken("456xyz");
                return token;
            }

        };
    }
}
