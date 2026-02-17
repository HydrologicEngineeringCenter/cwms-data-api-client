package hec.army.usace.hec.cwbi.auth.http.client;

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;

/**
 * A StaticOidcTokenController is an implementation of OpenIdTokenController that always returns the same well-known URL.
 * This is useful if the well-known URL is already known and does not need to be dynamically retrieved.
 * Note that if access to the well-known URL requires authentication, the wellKnownUrl provided to this controller should include the necessary authentication information (e.g. client cert data in the SslSocketData).
 */
class StaticOidcTokenController extends OpenIdTokenController {

    private final ApiConnectionInfo wellKnownUrl;

    StaticOidcTokenController(ApiConnectionInfo wellKnownUrl) {
        this.wellKnownUrl = wellKnownUrl;
    }

    @Override
    public ApiConnectionInfo retrieveWellKnownEndpoint(ApiConnectionInfo apiConnectionInfo) {
        return this.wellKnownUrl;
    }
}
