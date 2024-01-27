/*
 * MIT License
 *
 * Copyright (c) 2023 Hydrologic Engineering Center
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

package mil.army.usace.hec.cwms.http.client;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2TokenProvider;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

final class OAuth2TokenAuthenticator implements Authenticator {

    private static final Logger LOGGER = Logger.getLogger(OAuth2TokenAuthenticator.class.getName());
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final OAuth2TokenProvider tokenProvider;

    OAuth2TokenAuthenticator(OAuth2TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
        CwmsHttpLoggingInterceptor.getInstance().redactHeader(AUTHORIZATION_HEADER);
    }

    @Override
    public synchronized Request authenticate(Route route, Response response) throws IOException {
        OAuth2Token token = tokenProvider.getToken();
        if (token == null) {
            throw new IOException("Attempt to refresh token failed: No token retrieved from " + OAuth2TokenProvider.class.getName());
        }
        // Check if the request made was made as an authenticated request.
        if (response.request().header(AUTHORIZATION_HEADER) == null) {
            throw new IOException("Cannot refresh authentication token due to missing " + AUTHORIZATION_HEADER + " header");
        }
        OAuth2Token updatedToken;
        //check if refresh token on current token is still valid
        String refreshToken = token.getRefreshToken();
        DecodedJWT jwt = JWT.decode(refreshToken);
        if (AccessTokenValidator.isTokenExpired(jwt)) {
            //if expired we need to get a new token
            LOGGER.log(Level.INFO, () -> "Refresh token issued by " + jwt.getIssuer() + " is expired. Re-authenticating with new token");
            updatedToken = tokenProvider.newToken();
            validateNewToken(updatedToken);
        } else {
            //if refresh token is still valid, refresh using refresh token
            LOGGER.log(Level.FINE, "Refreshing OAuth2 Token");
            updatedToken = tokenProvider.refreshToken();
            if(updatedToken == null) {
                throw new IOException("No access token present in refreshed authentication token");
            }
            String accessToken = updatedToken.getAccessToken();
            if (accessToken == null || accessToken.isEmpty()) {
                throw new IOException("No access token present in refreshed authentication token");
            }
            LOGGER.log(Level.FINE, "OAuth2 Token refreshed");
        }
        // Retry the request with the new token.
        return newRequestWithAccessTokenAsHeader(response, updatedToken);
    }

    //package scoped for testing
    Request newRequestWithAccessTokenAsHeader(Response response, OAuth2Token oauth2Token) {
        return response.request()
            .newBuilder()
            .header(AUTHORIZATION_HEADER, oauth2Token.getTokenType() + " " + oauth2Token.getAccessToken())
            .build();
    }

    private void validateNewToken(OAuth2Token updatedToken) throws IOException {
        if (updatedToken == null) {
            throw new IOException("Authentication failed: No token retrieved from " + OAuth2TokenProvider.class.getName());
        }
        String accessToken = updatedToken.getAccessToken();
        if (accessToken == null || accessToken.isEmpty()) {
            throw new IOException("Authentication failed: No access token present");
        }
    }

}
