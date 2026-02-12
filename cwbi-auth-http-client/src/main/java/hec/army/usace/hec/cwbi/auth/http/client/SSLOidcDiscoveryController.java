package hec.army.usace.hec.cwbi.auth.http.client;

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfoBuilder;
import mil.army.usace.hec.cwms.http.client.SslSocketData;

import java.io.IOException;

/**
 * An extension of OpenIdTokenController that allows for SSL socket data to be included in the retrieval of the well-known endpoint.
 * This is necessary for cases where the well-known endpoint requires client certificate authentication.
 */
public abstract class SSLOidcDiscoveryController extends OpenIdTokenController {

    private final SslSocketData sslSocketData;

    protected SSLOidcDiscoveryController(SslSocketData sslSocketData) {
        this.sslSocketData = sslSocketData;
    }

    protected abstract String retrieveWellKnownEndpointUrl(ApiConnectionInfo apiConnectionInfo) throws IOException;

    @Override
    public final ApiConnectionInfo retrieveWellKnownEndpoint(ApiConnectionInfo apiConnectionInfo) throws IOException {
        return new ApiConnectionInfoBuilder(retrieveWellKnownEndpointUrl(apiConnectionInfo))
                .withSslSocketData(sslSocketData)
                .build();
    }
}
