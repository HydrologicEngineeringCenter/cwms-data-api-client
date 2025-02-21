package hec.army.usace.hec.cwbi.auth.http.client;

import javax.net.ssl.SSLSocketFactory;

public interface RefreshTokenRequestFluentBuilder {
    TokenRequestFluentBuilder withRefreshToken(String refreshToken);
    RefreshTokenRequestBuilder withSSlSocketFactory(SSLSocketFactory sslSocketFactory);
}
