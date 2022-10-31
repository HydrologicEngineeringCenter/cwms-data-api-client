package mil.army.usace.hec.cwms.http.client;

import mil.army.usace.hec.cwms.http.client.auth.OAuth2TokenProvider;
import okhttp3.CookieJar;

public class ApiConnectionInfoBuilder {

    private final String apiRoot;
    private OAuth2TokenProvider tokenProvider;
    private SslSocketData sslSocketData;
    private CookieJarFactory.CookieJarBuilder cookieJarBuilder;

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

    public ApiConnectionInfoBuilder withCookieJarBuilder(CookieJarFactory.CookieJarBuilder cookieJarBuilder) {
        this.cookieJarBuilder = cookieJarBuilder;
        return this;
    }

    public ApiConnectionInfo build() {
        CookieJar cookieJar = null;
        if (cookieJarBuilder != null) {
            cookieJar = cookieJarBuilder.buildCookieJar();
        }
        return new ApiConnectionInfo(apiRoot, sslSocketData, tokenProvider, cookieJar);
    }
}
