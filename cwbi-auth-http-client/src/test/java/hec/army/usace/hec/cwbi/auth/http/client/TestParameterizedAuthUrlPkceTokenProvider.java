package hec.army.usace.hec.cwbi.auth.http.client;

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfoBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestParameterizedAuthUrlPkceTokenProvider {

    @Test
    void testConstructorAndUrls() {
        String clientId = "test-client";
        ApiConnectionInfo authUrl = new ApiConnectionInfoBuilder("http://auth.example.com").build();
        ApiConnectionInfo tokenUrl = new ApiConnectionInfoBuilder("http://token.example.com").build();

        ParameterizedAuthUrlPkceTokenProvider provider = new ParameterizedAuthUrlPkceTokenProvider(clientId, authUrl, tokenUrl);

        assertEquals(clientId, provider.getClientId());
        assertEquals(authUrl, provider.getAuthUrl());
        assertEquals(tokenUrl, provider.getTokenUrl());

        provider = new ParameterizedAuthUrlPkceTokenProvider(clientId, authUrl, tokenUrl);

        assertEquals(clientId, provider.getClientId());
        assertEquals(tokenUrl, provider.getAuthUrl());
        assertEquals(tokenUrl, provider.getTokenUrl());
    }

    @Test
    void testInitializeAuthUrlsNoOp() {
        String clientId = "test-client";
        ApiConnectionInfo authUrl = new ApiConnectionInfoBuilder("http://auth.example.com").build();
        ApiConnectionInfo tokenUrl = new ApiConnectionInfoBuilder("http://token.example.com").build();

        ParameterizedAuthUrlPkceTokenProvider provider = new ParameterizedAuthUrlPkceTokenProvider(clientId, authUrl, tokenUrl);

        // This should not throw even though wellKnowEndpointController is null in OidcAuthTokenProvider
        provider.initializeAuthUrls();

        assertEquals(authUrl, provider.getAuthUrl());
        assertEquals(tokenUrl, provider.getTokenUrl());
    }
}
