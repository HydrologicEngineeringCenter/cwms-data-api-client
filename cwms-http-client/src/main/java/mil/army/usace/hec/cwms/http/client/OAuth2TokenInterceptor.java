package mil.army.usace.hec.cwms.http.client;

import static mil.army.usace.hec.cwms.http.client.OAuth2TokenAuthenticator.AUTHORIZATION_HEADER;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2TokenProvider;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

final class OAuth2TokenInterceptor implements Interceptor {

    private final OAuth2TokenProvider tokenProvider;

    OAuth2TokenInterceptor(OAuth2TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public synchronized Response intercept(Chain chain) throws IOException {
        String accessToken = tokenProvider.getToken().getAccessToken();
        Request request = newRequestWithAccessToken(chain.request(), accessToken);
        return chain.proceed(request);
    }

    //package scopes for testing
    Request newRequestWithAccessToken(Request request, String accessToken) {
        return request.newBuilder()
            .header(AUTHORIZATION_HEADER, "Bearer " + accessToken)
            .build();
    }
}
