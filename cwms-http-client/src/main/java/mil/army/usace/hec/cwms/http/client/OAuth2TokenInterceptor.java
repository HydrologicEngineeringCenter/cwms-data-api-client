package mil.army.usace.hec.cwms.http.client;

import static mil.army.usace.hec.cwms.http.client.OAuth2TokenAuthenticator.AUTHORIZATION_HEADER;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2TokenProvider;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

final class OAuth2TokenInterceptor implements Interceptor {

    private static final Logger LOGGER = Logger.getLogger(OAuth2TokenInterceptor.class.getName());
    private final OAuth2TokenProvider tokenProvider;

    OAuth2TokenInterceptor(OAuth2TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public synchronized Response intercept(Chain chain) throws IOException {
        OAuth2Token oauth2Token = tokenProvider.getToken();
        if (oauth2Token == null) {
            throw new IOException("Authentication failed: No token retrieved from " + OAuth2TokenProvider.class.getName());
        }
        String accessToken = oauth2Token.getAccessToken();
        if (accessToken == null || accessToken.isEmpty()) {
            throw new IOException("Authentication failed: No access token present");
        }
        LOGGER.log(Level.FINEST, "Authenticating request with OAuth2 Token");
        //if need to support tokens as parameters, add logic checking if token provider is using header/param,
        //then call appropriate method.
        Request request = newRequestWithAccessTokenAsHeader(chain.request(), oauth2Token);
        return chain.proceed(request);
    }

    //package scoped for testing
    Request newRequestWithAccessTokenAsHeader(Request request, OAuth2Token oauth2Token) {
        CwmsHttpLoggingInterceptor.getInstance().redactHeader(AUTHORIZATION_HEADER);
        return request.newBuilder()
            .header(AUTHORIZATION_HEADER, oauth2Token.getTokenType() + " " + oauth2Token.getAccessToken())
            .build();
    }
}
