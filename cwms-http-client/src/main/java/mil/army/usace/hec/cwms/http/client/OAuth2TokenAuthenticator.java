package mil.army.usace.hec.cwms.http.client;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2TokenProvider;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

final class OAuth2TokenAuthenticator implements Authenticator {

    static final String AUTHORIZATION_HEADER = "Authorization";
    private final OAuth2TokenProvider tokenProvider;

    OAuth2TokenAuthenticator(OAuth2TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public synchronized Request authenticate(Route route, Response response) throws IOException {
        OAuth2Token token = tokenProvider.getToken();
        // Check if the request made was made as an authenticated request.
        if (token != null && response.request().header(AUTHORIZATION_HEADER) != null) {
            //refresh the token
            OAuth2Token updatedToken = tokenProvider.refreshToken();

            // Retry the request with the new token.
            return response.request()
                .newBuilder()
                .removeHeader(AUTHORIZATION_HEADER)
                .addHeader(AUTHORIZATION_HEADER, "Bearer " + updatedToken.getAccessToken())
                .build();
        }
        return null;
    }
}
