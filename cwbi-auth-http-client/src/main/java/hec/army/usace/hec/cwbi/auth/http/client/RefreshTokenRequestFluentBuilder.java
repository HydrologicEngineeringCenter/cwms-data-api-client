package hec.army.usace.hec.cwbi.auth.http.client;

public interface RefreshTokenRequestFluentBuilder {
    <T> TokenRequestFluentBuilder<T> withRefreshToken(String refreshToken);
}
