package hec.army.usace.hec.cwbi.auth.http.client;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CompletionException;

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;

/**
 * Handle generic OIDC auth based on configuration elements in the .well-known/openid-configuration
 * values.
 *
 * Defaults to using Authorization Code + PKCE.
 * Support should be provided to support alternative flows as a user-at-login decision point.
 */
public class OidcDiscoveryAuthCodePkceTokenProvider extends AuthorizationCodePkceTokenProvider {

    private final ApiConnectionInfo wellKnownUrl;
    private final StaticOidcTokenController wellKnowEndpointController;
    private ApiConnectionInfo tokenUrl;
    private ApiConnectionInfo authUrl;

    public OidcDiscoveryAuthCodePkceTokenProvider(String clientId, ApiConnectionInfo wellKnownUrl) {
        super(clientId);
        this.wellKnownUrl = Objects.requireNonNull(wellKnownUrl, "Missing required well known Url.");
        this.wellKnowEndpointController = new StaticOidcTokenController(wellKnownUrl);
    }

    @Override
    public ApiConnectionInfo getAuthUrl() {
        if(authUrl == null) {
            initializeAuthUrl();
        }
        return authUrl;
    }

    @Override
    public ApiConnectionInfo getTokenUrl() {
        if(tokenUrl == null) {
            initializeTokenUrl();
        }
        return tokenUrl;
    }

    private void initializeAuthUrl() {
        try {
            this.authUrl = this.wellKnowEndpointController.retrieveAuthUrl(wellKnownUrl);
        } catch (IOException ex) {
            throw new CompletionException("Unable to return auth URL", ex);
        }
    }

    private void initializeTokenUrl() {
        try {
            this.tokenUrl = this.wellKnowEndpointController.retrieveTokenUrl(wellKnownUrl);
            // TODO: process appropriate extensions to determine things like "kc_idp_hint"
        } catch (IOException ex) {
            throw new CompletionException("Unable to return token URL", ex);
        }
    }

    ApiConnectionInfo getWellKnownUrl() {
        return this.wellKnownUrl;
    }

}
