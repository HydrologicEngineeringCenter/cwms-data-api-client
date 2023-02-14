/*
 * MIT License
 *
 * Copyright (c) 2022 Hydrologic Engineering Center
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

package mil.army.usace.hec.cwms.aaa.client;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpCookie;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.SslCanceledException;

public final class CwmsLoginController {

    static final String JSESSIONID = "JSESSIONID";
    static final String JSESSIONIDSSO = "JSESSIONIDSSO";
    private static final String LOGIN_ENDPOINT = "login";
    private static final String LOGOUT_ENDPOINT = "logout";
    private static final String DOD_AGREE_PARAMETER = "dod_agree";
    private static final String APPLICATION_JSON = "application/json";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true)
        .configure(JsonReadFeature.ALLOW_MISSING_VALUES.mappedFeature(), true);

    /**
     * This endpoint requires SslSocketData to be set on the ApiConnectionInfo
     * CWMS_AAA requires CAC authentication through the TrustManager and SSLSocketFactory
     * in order to verify the user.
     *
     * @param apiConnectionInfo contains the web server address for the login server
     * @return data object containing the JSESSIONID token as well as information about the login event
     * @throws SslCanceledException if the user canceled the CAC pin prompt
     * @throws IOException          thrown if any errors occur in the server request
     */
    public CwmsAuthToken login(ApiConnectionInfo apiConnectionInfo) throws IOException {
        /*
            Need to first get the jSessionId from the /login endpoint, this brings up the
            DoD agreement banner, which is designed for a browser redirect.
            Instead, we will bypass in a follow-up request ignoring the banner
            I would think eventually the banner bypass should still provide the jSessionId
        */
        String jsessionId;
        try (HttpRequestResponse login = new HttpRequestBuilderImpl(apiConnectionInfo, LOGIN_ENDPOINT)
            .get()
            .withMediaType(APPLICATION_JSON)
            .execute()) {
            jsessionId = login.getCookies()
                .stream()
                .filter(h -> h.name().equalsIgnoreCase(JSESSIONID))
                .map(HttpCookie::value)
                .findFirst()
                .orElseThrow(() -> new IOException("CWMS_AAA did not return a JSESSIONID cookie"));
        }
        try (HttpRequestResponse login = new HttpRequestBuilderImpl(apiConnectionInfo, LOGIN_ENDPOINT)
            .addEndpointInput(new EndpointInput() {
                @Override
                protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
                    return httpRequestBuilder.addQueryParameter(DOD_AGREE_PARAMETER, "yes");
                }
            })
            .get()
            .withMediaType(APPLICATION_JSON)
            .execute()) {
            String jsessionIdSso = login.getCookies()
                .stream()
                .filter(h -> h.name().equalsIgnoreCase(JSESSIONIDSSO))
                .map(HttpCookie::value)
                .findFirst()
                .orElseThrow(() -> new IOException("CWMS_AAA did not return a JSESSIONIDSSO cookie"));
            String jsonBody = login.getBody();
            CwmsAuthToken retval = OBJECT_MAPPER.readValue(jsonBody, CwmsAuthToken.class);
            retval.setJSessionId(jsessionId);
            retval.setJSessionIdSso(jsessionIdSso);
            return retval;
        }
    }

    /**
     * Perform logout through the CWMS_AAA, rendering the JSESSIONID token invalid.
     *
     * <p>This endpoint requires SslSocketData to be set on the ApiConnectionInfo
     * CWMS_AAA requires CAC authentication through the TrustManager and SSLSocketFactory
     * in order to verify the user.
     *
     * @param apiConnectionInfo contains the web server address for the login server
     * @throws SslCanceledException if the user canceled the CAC pin prompt
     * @throws IOException          thrown if any errors occur in the server request
     */
    public void logout(ApiConnectionInfo apiConnectionInfo) throws IOException {
        try (HttpRequestResponse login = new HttpRequestBuilderImpl(apiConnectionInfo, LOGOUT_ENDPOINT)
            .get()
            .withMediaType(APPLICATION_JSON)
            .execute()) {
            login.getBody();
        }
    }
}
