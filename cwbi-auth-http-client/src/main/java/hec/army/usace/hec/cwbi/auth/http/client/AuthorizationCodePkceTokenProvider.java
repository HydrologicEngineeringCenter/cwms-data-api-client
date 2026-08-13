/*
 * MIT License
 *
 * Copyright (c) 2026 Hydrologic Engineering Center
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package hec.army.usace.hec.cwbi.auth.http.client;

import java.io.IOException;
import java.net.URI;
import java.util.Objects;
import java.util.function.Consumer;

import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2TokenProvider;

/**
 * Base implementation of {@link OAuth2TokenProvider} that provides the token lifecycle behavior
 * shared by all CWBI OIDC based token providers: caching the current token, clearing it,
 * refreshing it via a refresh token, and retrieving a brand new token using the
 * Authorization Code + PKCE flow.
 *
 * <p>Subclasses differ only in how they resolve the authorization and token endpoint URLs
 * (e.g. via OIDC discovery vs. URLs supplied directly by the caller), so they are only
 * responsible for implementing {@link #getAuthUrl()} and {@link #getTokenUrl()}. Subclasses
 * that require an entirely different token retrieval mechanism (e.g. direct grant) may still
 * override {@link #newToken()}.
 */
public abstract class AuthorizationCodePkceTokenProvider implements OAuth2TokenProvider {

    private final String clientId;
    private OAuth2Token token = null;
    // Default to open browser or print to console for usage, but allow overriding for testing and
    // other usages.
    private Consumer<URI> authCallback = TokenRequestBuilder.BROWSER_OR_CONSOLE_AUTH_CALLBACK;

    protected AuthorizationCodePkceTokenProvider(String clientId) {
        this.clientId = Objects.requireNonNull(clientId, "Missing required client id.");
    }

    @Override
    public void clear() {
        synchronized (this) {
            this.token = null;
        }
    }

    @Override
    public Consumer<URI> getAuthCallback() {
        return authCallback;
    }

    @Override
    public void setAuthCallback(Consumer<URI> authCallback) {
        this.authCallback = authCallback;
    }

    @Override
    public OAuth2Token getToken() throws IOException {
        synchronized (this) {
            if (token == null) {
                token = newToken();
            }
            return token;
        }
    }

    @Override
    public OAuth2Token refreshToken() throws IOException {
        synchronized (this) {
            token = new RefreshTokenRequestBuilder()
                    .withRefreshToken(token.getRefreshToken())
                    .withUrl(getTokenUrl())
                    .withClientId(clientId)
                    .fetchToken();
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
                    .withAuthUrl(getAuthUrl())
                    .withTokenUrl(getTokenUrl())
                    .withAuthCallback(authCallback)
                    .buildRequest()
                    .withClientId(clientId)
                    .fetchToken();
            return token;
        }
    }

    String getClientId() {
        return this.clientId;
    }
}
