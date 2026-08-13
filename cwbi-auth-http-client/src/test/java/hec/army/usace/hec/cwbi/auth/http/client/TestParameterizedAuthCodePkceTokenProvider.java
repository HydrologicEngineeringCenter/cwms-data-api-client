package hec.army.usace.hec.cwbi.auth.http.client;

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfoBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestParameterizedAuthCodePkceTokenProvider {

    @Test
    void testConstructorAndUrls() {
        String clientId = "test-client";
        ApiConnectionInfo authUrl = new ApiConnectionInfoBuilder("http://auth.example.com").build();
        ApiConnectionInfo tokenUrl = new ApiConnectionInfoBuilder("http://token.example.com").build();

        ParameterizedAuthCodePkceTokenProvider provider = new ParameterizedAuthCodePkceTokenProvider(clientId, authUrl, tokenUrl);

        assertEquals(clientId, provider.getClientId());
        assertEquals(authUrl, provider.getAuthUrl());
        assertEquals(tokenUrl, provider.getTokenUrl());

        provider = new ParameterizedAuthCodePkceTokenProvider(clientId, authUrl, tokenUrl);

        assertEquals(clientId, provider.getClientId());
        assertEquals(authUrl, provider.getAuthUrl());
        assertEquals(tokenUrl, provider.getTokenUrl());
    }
}
