package mil.army.usace.hec.cwms.http.client.auth;

import java.io.IOException;

public final class OAuth2TokenException extends IOException {

    public OAuth2TokenException(String message) {
        super(message);
    }
}
