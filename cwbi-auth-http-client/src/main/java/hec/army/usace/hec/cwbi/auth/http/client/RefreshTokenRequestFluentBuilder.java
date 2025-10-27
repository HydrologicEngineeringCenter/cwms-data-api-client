package hec.army.usace.hec.cwbi.auth.http.client;

public interface RefreshTokenRequestFluentBuilder {
    <T> TokenRequestFluentBuilder<? extends TokenRequestFluentBuilder<?>> withRefreshToken(String refreshToken);
}
