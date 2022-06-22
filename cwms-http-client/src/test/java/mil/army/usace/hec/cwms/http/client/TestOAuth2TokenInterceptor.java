package mil.army.usace.hec.cwms.http.client;

import static mil.army.usace.hec.cwms.http.client.OAuth2TokenAuthenticator.AUTHORIZATION_HEADER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2TokenProvider;
import okhttp3.Request;
import org.junit.jupiter.api.Test;

class TestOAuth2TokenInterceptor {

    @Test
    void testNewRequest() throws IOException {
        OAuth2TokenProvider tokenProvider = getTestTokenProvider();
        OAuth2TokenInterceptor interceptor = new OAuth2TokenInterceptor(tokenProvider);
        Request request = getMockRequest();
        String accessToken = tokenProvider.getToken().getAccessToken();
        Request newRequest = interceptor.newRequestWithAccessToken(request, accessToken);
        assertNotNull(newRequest);
        assertEquals("Bearer " + accessToken, newRequest.headers(AUTHORIZATION_HEADER).get(0));
    }

    private Request getMockRequest() {
        return new Request.Builder()
            .url("https://some-url.com")
            .addHeader(AUTHORIZATION_HEADER, "Bearer abc123")
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
