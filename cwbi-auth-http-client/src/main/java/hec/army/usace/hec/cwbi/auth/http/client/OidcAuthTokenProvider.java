package hec.army.usace.hec.cwbi.auth.http.client;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CompletionException;

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfoBuilder;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2TokenProvider;

/**
 * Handle generic OIDC auth based on configuration elements in the .well-known/openid-configuration
 * values.
 * 
 * Defaults to using Authorization Code + PKCE.
 * Support should be provided to support alternative flows as a user-at-login decision point.
 */
public final class OidcAuthTokenProvider implements OAuth2TokenProvider {

    private final String clientId;
    private final String wellKnownUrl;
    private final ApiConnectionInfo tokenUrl;
    private final ApiConnectionInfo authUrl;
    private OAuth2Token token = null;

    public OidcAuthTokenProvider(String clientId, String wellKnownUrl) {
        this.clientId = Objects.requireNonNull(clientId, "Missing required client id.");
        this.wellKnownUrl = Objects.requireNonNull(clientId, "Missing required well known Url.");

        OpenIdTokenController controller = new OpenIdTokenController() {

            @Override
            public String retrieveWellKnownEndpoint(ApiConnectionInfo apiConnectionInfo) throws IOException {
                return wellKnownUrl; // we already have it.
            }
            
        };
        ApiConnectionInfo info = new ApiConnectionInfoBuilder(wellKnownUrl).build();
        String what = "auth";
        try {
            this.authUrl = controller.retrieveAuthUrl(info, null);
            what = "token";
            this.tokenUrl = controller.retrieveTokenUrl(info, null);
            // TODO: process appropriate extensions to determine things like "kc_idp_hint"
        } catch (IOException ex) {
            throw new CompletionException("Unable to return " + what + " URL", ex);
        }
    }

    @Override
    public void clear() {
        synchronized (this) {
            this.token = null;
        }
    }

    @Override
    public OAuth2Token getToken() throws IOException {
        synchronized(this) {
            if (token == null) {
                token = newToken();
            }
            return token;
        }
    }

    @Override
    public OAuth2Token refreshToken() throws IOException {
        synchronized (this) {
            OAuth2Token newToken = new RefreshTokenRequestBuilder()
                    .withRefreshToken(token.getRefreshToken())
                    .withUrl(tokenUrl)
                    .withClientId(clientId)
                    .fetchToken();
            token = newToken;
            return token;
        }
    }

    @Override
    public OAuth2Token newToken() throws IOException {
        synchronized (this) {
            /**
             * It may make sense to allow something to override this usage, however that
             * *should* be a user setting. So like additional drop down or something in the gui.
             * There are various notes about it in different sections for discussion.
             */
            token = new AuthCodePkceTokenRequestBuilder()
                    .withAuthUrl(authUrl)
                    .withTokenUrl(tokenUrl)
                    .buildRequest()
                    .withClientId(clientId)
                    .fetchToken();
            return token;
        }
        
    }
    
}
