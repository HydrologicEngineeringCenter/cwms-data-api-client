package mil.army.usace.hec.cwms.http.client.auth;

import java.io.IOException;

public interface OAuth2TokenProvider {

    OAuth2Token getToken() throws IOException;

    OAuth2Token refreshToken() throws IOException;

    OAuth2Token getDirectX509Token() throws IOException;

}
