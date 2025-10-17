package hec.army.usace.hec.cwbi.auth.http.client;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfoBuilder;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.SslSocketData;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;

public abstract class OpenIdTokenController {

    static final String ACCEPT_HEADER = "application/json";
    private static final String TOKEN_ENDPOINT_KEY = "token_endpoint";
    private static final String AUTH_ENDPOINT_KEY = "authorization_endpoint";


    private String authEndpoint = null;
    private String tokenEndpoint = null;

    /**
     * Retrieve json text of the .wellknown/openid-configuration
     * @param apiConnectionInfo
     * @return
     * @throws IOException
     */
    public abstract String retrieveWellKnownEndpoint(ApiConnectionInfo apiConnectionInfo) throws IOException;

    public final ApiConnectionInfo retrieveTokenUrl(ApiConnectionInfo apiConnectionInfo, SslSocketData sslSocketData) throws IOException {
        if (tokenEndpoint == null) {
            String wellKnownEndpoint = retrieveWellKnownEndpoint(apiConnectionInfo)
                        // stop gap until source of information is corrected
                        .replace("identityc","identity");
            
            ApiConnectionInfo wellKnownApiConnectionInfo = new ApiConnectionInfoBuilder(wellKnownEndpoint)
                    .withSslSocketData(sslSocketData)
                    .build();
            HttpRequestExecutor executor = new HttpRequestBuilderImpl(wellKnownApiConnectionInfo)
                    .get()
                    .withMediaType(ACCEPT_HEADER);
            try (HttpRequestResponse response = executor.execute()) {
                tokenEndpoint = OAuth2ObjectMapper.getValueForKey(response.getBody(), TOKEN_ENDPOINT_KEY)
                        // stop gap until source of information is corrected
                        .replace("identityc","identity");
            }
        }
        return new ApiConnectionInfoBuilder(tokenEndpoint)
                .withSslSocketData(sslSocketData)
                .build();
    }

    public final ApiConnectionInfo retrieveAuthUrl(ApiConnectionInfo apiConnectionInfo, SslSocketData sslSocketData) throws IOException {
        
        if (authEndpoint == null) {
            String wellKnownEndpoint = retrieveWellKnownEndpoint(apiConnectionInfo)
                    // stop gap until source of information is corrected
                    .replace("identityc","identity");
            ApiConnectionInfo wellKnownApiConnectionInfo = new ApiConnectionInfoBuilder(wellKnownEndpoint)
                    .withSslSocketData(sslSocketData)
                    .build();
            HttpRequestExecutor executor = new HttpRequestBuilderImpl(wellKnownApiConnectionInfo)
                    .get()
                    .withMediaType(ACCEPT_HEADER);
            try (HttpRequestResponse response = executor.execute()) {
                authEndpoint = OAuth2ObjectMapper.getValueForKey(response.getBody(), AUTH_ENDPOINT_KEY)
                                // stop gap until source of information is corrected
                                .replace("identityc","identity");
            }
        }
        return new ApiConnectionInfoBuilder(authEndpoint)
                .withSslSocketData(sslSocketData)
                .build();
    }
}
