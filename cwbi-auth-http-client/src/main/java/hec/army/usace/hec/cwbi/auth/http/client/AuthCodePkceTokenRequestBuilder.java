/*
 * MIT License
 *
 * 
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

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.awt.Desktop;
import java.awt.Desktop.Action;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public final class AuthCodePkceTokenRequestBuilder extends TokenRequestBuilder {

    @Override
    OAuth2Token retrieveToken() throws IOException {
    
        OAuth2Token retVal = null;
        // https://datatracker.ietf.org/doc/html/rfc7636#section-4.1
        try {
            byte[] verifierBytes = new byte[128];
            SecureRandom.getInstanceStrong().nextBytes(verifierBytes);
            Base64.Encoder b64encoder = Base64.getUrlEncoder();
            final String verifier = b64encoder.encodeToString(verifierBytes);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            final String challenge = b64encoder.encodeToString(md.digest(verifierBytes));
            HttpServer server = HttpServer.create(new InetSocketAddress(0), 0);
            int port = server.getAddress().getPort();
            String host = server.getAddress().getHostName();
            final AtomicReference<String> code = new AtomicReference<>();
            final AtomicReference<String> state = new AtomicReference<>();
            final CompletableFuture<Void> future = new CompletableFuture<>();
            
            server.createContext("/", new HttpHandler() {

                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    final String query = exchange.getRequestURI().getQuery();
                    Map<String, List<String>> parameters = new HashMap<>();
                    for (String pair: query.split("&")) {
                        String[] kv = pair.split("=");
                        String parameter = URLDecoder.decode(kv[0], StandardCharsets.UTF_8);
                        String value = kv.length > 1 ? URLDecoder.decode(kv[1], StandardCharsets.UTF_8) : null;
                        parameters.computeIfAbsent(parameter, p -> new ArrayList<>()).add(value);
                    }

                    code.set(parameters.get("code").get(0));
                    state.set(parameters.get("state").get(0));
                    future.complete(null);
                }

            });

            String formBody = new UrlEncodedFormData()
                    .addPassword("")
                    .addGrantType("code")
                    .addScopes("openid", "profile")
                    .addClientId(getClientId())
                    .addUsername("")
                    .addParameter("code_challenge_method", "S256")
                    .addParameter("code_challenge", challenge)
                    .addParameter("redirect_uri", String.format("http://%s:%d", host, port))
                    .buildEncodedString();
            String urlStr= String.format("%s/%s", getUrl().getApiRoot(), formBody);
            // start server to listen
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Action.BROWSE)) {
                Desktop.getDesktop().browse(URI.create(urlStr));
            } else {
                System.out.println(String.format("Paste the following into a browser to continue login: %s", urlStr));
            }

            future.join();
            System.out.println("Next steps.");

            
        } catch (NoSuchAlgorithmException ex) {
            throw new IOException("Unable to retrieve SecureRandom or Message Digest instance to generate verifier", ex);
        }
    
        return retVal;
    }
}
