package mil.army.usace.hec.cwms.http.client;

import mil.army.usace.hec.cwms.http.client.auth.OAuth2TokenProvider;

public class ApiConnectionInfoBuilder {

    private final String apiRoot;
    private OAuth2TokenProvider tokenProvider;
    private SslSocketData sslSocketData;

    public ApiConnectionInfoBuilder(String apiRoot) {
        this.apiRoot = apiRoot;
    }

    public ApiConnectionInfoBuilder withSslSocketData(SslSocketData sslSocketData) {
        this.sslSocketData = sslSocketData;
        return this;
    }

    public ApiConnectionInfoBuilder withTokenProvider(OAuth2TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
        return this;
    }

    public ApiConnectionInfo build() {
        return new ApiConnectionInfo(apiRoot, sslSocketData, tokenProvider);
    }
}
