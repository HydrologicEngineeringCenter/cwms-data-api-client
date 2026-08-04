package hec.army.usace.hec.cwbi.auth.http.client;

import java.util.Objects;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;

/**
 * Handle OIDC auth using directly provided Authorization and Token URLs.
 * This is useful when the Identity Provider does not support OIDC discovery (.well-known/openid-configuration).
 */
public class ParameterizedAuthCodePkceTokenProvider extends AuthorizationCodePkceTokenProvider {

    private final ApiConnectionInfo authUrl;
    private final ApiConnectionInfo tokenUrl;

    public ParameterizedAuthCodePkceTokenProvider(String clientId, ApiConnectionInfo authUrl, ApiConnectionInfo tokenUrl) {
        super(clientId);
        this.authUrl = Objects.requireNonNull(authUrl, "Missing required Authorization URL.");
        this.tokenUrl = Objects.requireNonNull(tokenUrl, "Missing required Token URL.");
    }

    @Override
    public ApiConnectionInfo getAuthUrl() {
        return authUrl;
    }

    @Override
    public ApiConnectionInfo getTokenUrl() {
        return tokenUrl;
    }
}
