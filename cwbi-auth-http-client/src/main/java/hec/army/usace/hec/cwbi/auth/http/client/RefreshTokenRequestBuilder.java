package hec.army.usace.hec.cwbi.auth.http.client;

import hec.army.usace.hec.cwbi.auth.http.client.trustmanagers.CwbiAuthTrustManager;
import java.util.Optional;
import javax.net.ssl.SSLSocketFactory;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfoBuilder;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.SslSocketData;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;

import java.io.IOException;
import java.util.Objects;

public final class RefreshTokenRequestBuilder implements RefreshTokenRequestFluentBuilder {

    private String refreshToken;
    private SSLSocketFactory sslSocketFactory;

    /**
     * Retrieved token via a refresh token.
     * @param refreshToken - token used to fetch new token
     * @return Builder for http request
     */
    @Override
    public TokenRequestFluentBuilder withRefreshToken(String refreshToken) {
        this.refreshToken = Objects.requireNonNull(refreshToken, "Missing required refresh token");
        return new RefreshTokenRequestExecutor();
    }

    /**
     * Set the SSLSocketFactory for the refresh request should it be needed.
     * @param sslSocketFactory - SSLSocketFactory to use
     * @return Builder for http request
     */
    @Override
    public RefreshTokenRequestBuilder withSSlSocketFactory(SSLSocketFactory sslSocketFactory) {
        this.sslSocketFactory = sslSocketFactory;
        return this;
    }

    //package scoped for testing
    Optional<SSLSocketFactory> getSslSocketFactory() {
        return Optional.ofNullable(sslSocketFactory);
    }

    private class RefreshTokenRequestExecutor extends TokenRequestBuilder {

        @Override
        OAuth2Token retrieveToken() throws IOException {
            OAuth2Token retVal = null;
            SslSocketData sslSocketData = getSslSocketFactory().map(sf -> new SslSocketData(sf, CwbiAuthTrustManager.getTrustManager()))
                    .orElse(null);
            HttpRequestExecutor executor =
                new HttpRequestBuilderImpl(new ApiConnectionInfoBuilder(getUrl())
                        .withSslSocketData(sslSocketData).build())
                    .post()
                    .withBody(new UrlEncodedFormData()
                        .addRefreshToken(refreshToken)
                        .addGrantType("refresh_token")
                        .addClientId(getClientId())
                        .buildEncodedString())
                    .withMediaType(MEDIA_TYPE);
            try (HttpRequestResponse response = executor.execute()) {
                String body = response.getBody();
                if (body != null) {
                    retVal = OAuth2ObjectMapper.mapJsonToObject(body, OAuth2Token.class);
                }
            }
            return retVal;
        }
    }
}
