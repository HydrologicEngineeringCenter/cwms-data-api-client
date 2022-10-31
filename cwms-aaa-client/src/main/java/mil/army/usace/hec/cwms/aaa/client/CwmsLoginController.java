/*
 * Copyright (c) 2022
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.aaa.client;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.SslCanceledException;

public final class CwmsLoginController {

    private static final String LOGIN_ENDPOINT = "login";
    private static final String LOGOUT_ENDPOINT = "logout";
    private static final String DOD_AGREE_PARAMETER = "dod_agree";
    private static final String JSESSIONID = "JSESSIONID";
    private static final String JSESSIONIDSSO = "JSESSIONIDSSO";
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
    public CwmsAAAAuthToken login(ApiConnectionInfo apiConnectionInfo) throws IOException {
        /*
            Need to first get the jSessionId from the /login endpoint, this brings up the
            DoD agreement banner, which is designed for a browser redirect.
            Instead, we will bypass in a follow-up request ignoring the banner
            I would think eventually the banner bypass should still provide the jSessionId
        */
        String jsessionId;
        try (HttpRequestResponse login = new HttpRequestBuilderImpl(apiConnectionInfo, LOGIN_ENDPOINT)
            .get()
            .withMediaType("application/json")
            .execute()) {
            jsessionId = login.getCookies().get(JSESSIONID);
        }
        try (HttpRequestResponse login = new HttpRequestBuilderImpl(apiConnectionInfo, LOGIN_ENDPOINT)
            .addEndpointInput(new EndpointInput() {
                @Override
                protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
                    return httpRequestBuilder.addQueryParameter(DOD_AGREE_PARAMETER, "yes");
                }
            })
            .get()
            .withMediaType("application/json")
            .execute()) {
            String jsessionIdSso = login.getCookies().get(JSESSIONIDSSO);
            String jsonBody = login.getBody();
            CwmsAAAAuthToken retval = OBJECT_MAPPER.readValue(jsonBody, CwmsAAAAuthToken.class);
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
            .withMediaType("application/json")
            .execute()) {
            login.getBody();
        }
    }
}
