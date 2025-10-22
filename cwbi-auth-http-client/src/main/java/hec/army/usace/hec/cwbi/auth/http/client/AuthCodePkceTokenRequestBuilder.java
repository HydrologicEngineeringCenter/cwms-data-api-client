/*
 * MIT License
 *
 * Copyright (c) 2024 Hydrologic Engineering Center
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

import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.auth.OAuth2Token;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.http.client.request.QueryParameters;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * Use Authorization Code + PKCE method to retrieve initial token set.
 * 
 * If a desktop is available users's default Browser is opened with the given auth URL
 * To complete the additional requirements.
 */
public final class AuthCodePkceTokenRequestBuilder extends TokenRequestBuilder<AuthCodePkceTokenRequestBuilder> {
    private static final Logger LOGGER = Logger.getLogger(AuthCodePkceTokenRequestBuilder.class.getName());
    @Override
    OAuth2Token retrieveToken() throws IOException {
    
        OAuth2Token retVal = null;
        // https://datatracker.ietf.org/doc/html/rfc7636#section-4.1
        try {
            byte[] verifierBytes = new byte[64];
            SecureRandom.getInstanceStrong().nextBytes(verifierBytes);
            Base64.Encoder b64encoder = Base64.getUrlEncoder().withoutPadding();
            final String verifier = b64encoder.encodeToString(verifierBytes);
            final String originalState = UUID.randomUUID().toString();

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            final String challenge = b64encoder.encodeToString(md.digest(verifier.getBytes(StandardCharsets.US_ASCII)));
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 0), 0);            
            int port = server.getAddress().getPort();
            String host = server.getAddress().getHostName();

            final CompletableFuture<Result> future = new CompletableFuture<>();
            
            server.createContext("/", new HttpHandler() {

                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    Result ret = null;
                    
                    final String query = exchange.getRequestURI().getQuery();
                    LOGGER.finest("Got auth server response." + query);
                    final QueryParameters parameters = QueryParameters.parse(query);
                    if (!parameters.get("error").isEmpty()) {
                        String error = parameters.get("error").get(0);
                        String errorDescription = parameters.get("error_description").get(0);
                        ret = Result.failure(error, errorDescription);                    
                    } else {
                        String code = parameters.get("code").get(0);
                        String state = parameters.get("state").get(0);
                        String session_state = parameters.get("session_state").get(0);
                        ret = Result.success(code ,state, session_state);
                    }
                    LOGGER.finest("Returning result back to thread.");
                    server.stop(0);
                    future.complete(ret);
                }

            });
            final String redirectUri = String.format("http://%s:%d", host, port);
            final QueryParameters authParameters = QueryParameters.empty()
                .set("grant_type", "code")
                .set("client_id", getClientId())
                .set("scopes", "openid profile")
                .set("response_type", "code")
                .set("code_challenge_method", "S256")
                .set("code_challenge", challenge)
                .set("redirect_uri", redirectUri)
                .set("state", originalState);
            String urlStr= String.format("%s?%s", getAuthUrl().getApiRoot(), authParameters.encode());
            // start server to listen
            server.start();
            LOGGER.info("Handling Auth Request");
            LOGGER.finer("Auth Request URL: " + urlStr);
            this.authCallBack.accept(URI.create(urlStr));
            
            Result result = future.get(3, TimeUnit.MINUTES); // The user is now required to perform manual operations.
            LOGGER.info("Retrieving Token.");
            if (result.error != null) {
                throw new IOException(String.format("Unable to login. %s : %s", result.error, result.errorDescription));
            }
            if (!result.state.equals(originalState)) {
                throw new IOException("Unable to continue login sequence, incorrect state value returned.");
            }
            final UrlEncodedFormData formData = new UrlEncodedFormData();
            formData.addClientId(getClientId())
                    .addGrantType("authorization_code")
                    .addParameter("code_verifier", verifier)
                    .addScopes("openid", "profile")
                    .addParameter("redirect_uri", redirectUri)
                    .addParameter("state", result.state)
                    .addParameter("session_state", result.session_state)
                    .addParameter("code", result.code)
                    .addParameter("response_mode", "fragment")
                    .addParameter("response_type", "id_token token");

            HttpRequestExecutor executor =
                new HttpRequestBuilderImpl(getTokenUrl())
                    .post()
                    .withBody(formData.buildEncodedString())
                    .withMediaType(MEDIA_TYPE);
            try (HttpRequestResponse response = executor.execute()) {
                String body = response.getBody();
                if (body != null) {
                    retVal = OAuth2ObjectMapper.mapJsonToObject(body, OAuth2Token.class);
                }
            }
            return retVal;
        } catch (NoSuchAlgorithmException ex) {
            throw new IOException("Unable to retrieve SecureRandom or Message Digest instance to generate verifier", ex);
        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            throw new IOException("Unable to form login sequence.", ex);
        }
    }

    private static class Result {
        public final String code;
        public final String state;
        public final String session_state;

        public final String error;
        public final String errorDescription;

        private Result(String code, String state, String session_state, String error, String errorDescription) {
            this.code = code;
            this.state = state;
            this.session_state = session_state;
            this.error = error;
            this.errorDescription = errorDescription;
        }

        public static Result success(String code, String state, String session_state) {
            return new Result(code, state, session_state, null, null);
        }

        public static Result failure(String error, String errorDescription) {
            return new Result(null, null, null, error, errorDescription);
        }
    };
}
