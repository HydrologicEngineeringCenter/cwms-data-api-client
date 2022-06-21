package mil.army.usace.hec.cwms.http.client;

import java.util.Optional;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2TokenProvider;
import okhttp3.OkHttpClient;

final class OkHttpClientFactory {

    private OkHttpClientFactory() {

    }

    static OkHttpClient buildOkHttpClient(ApiConnectionInfo apiConnectionInfo) {
        Optional<SslSocketData> optionalSslSocketData = apiConnectionInfo.getSslSocketData();
        Optional<OAuth2TokenProvider> optionalTokenProvider = apiConnectionInfo.getOAuth2TokenProvider();
        OkHttpClient retVal = OkHttpClientInstance.getInstance();
        if (optionalSslSocketData.isPresent()) {
            SslSocketData sslSocketData = optionalSslSocketData.get();
            retVal = retVal.newBuilder()
                .sslSocketFactory(sslSocketData.getSslSocketFactory(), sslSocketData.getX509TrustManager())
                .build();
        }
        if (optionalTokenProvider.isPresent()) {
            retVal = retVal.newBuilder()
                .authenticator(new OAuth2TokenAuthenticator(optionalTokenProvider.get()))
                .addInterceptor(new OAuth2TokenInterceptor(optionalTokenProvider.get()))
                .build();
        }
        return retVal;
    }
}
