package hec.army.usace.hec.cwbi.auth.http.client;

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfoBuilder;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;

import java.io.IOException;
import java.util.Objects;

public final class RefreshTokenRequestBuilder implements RefreshTokenRequestFluentBuilder {

    private String refreshToken;

    /**
     * Retrieved token via a refresh token.
     * @param refreshToken - token used to fetch new token
     * @return Builder for http request
     */
    public TokenRequestFluentBuilder withRefreshToken(String refreshToken) {
        this.refreshToken = Objects.requireNonNull(refreshToken, "Missing required refresh token");
        return new RefreshTokenRequestExecutor();
    }

    private class RefreshTokenRequestExecutor extends TokenRequestBuilder {

        @Override
        OAuth2Token retrieveToken() throws IOException {
            OAuth2Token retVal = null;
            HttpRequestExecutor executor =
                new HttpRequestBuilderImpl(new ApiConnectionInfoBuilder(getUrl()).build())
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
