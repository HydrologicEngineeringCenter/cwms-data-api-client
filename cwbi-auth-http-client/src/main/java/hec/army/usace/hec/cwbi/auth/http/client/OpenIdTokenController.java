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
    protected abstract String retrieveWellKnownEndpoint(ApiConnectionInfo apiConnectionInfo) throws IOException;
    public final ApiConnectionInfo retrieveTokenUrl(ApiConnectionInfo apiConnectionInfo, SslSocketData sslSocketData) throws IOException {
        String wellKnownEndpoint = retrieveWellKnownEndpoint(apiConnectionInfo);
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(new ApiConnectionInfoBuilder(wellKnownEndpoint).build())
                .get()
                .withMediaType(ACCEPT_HEADER);
        try (HttpRequestResponse response = executor.execute()) {
            String tokenEndpoint = OAuth2ObjectMapper.getValueForKey(response.getBody(), TOKEN_ENDPOINT_KEY);
            return new ApiConnectionInfoBuilder(tokenEndpoint)
                    .withSslSocketData(sslSocketData)
                    .build();
        }
    }
}
