package mil.army.usace.hec.cwms.http.client.auth;


public interface OAuth2TokenValidator {

    void validateToken(OAuth2Token oauth2Token) throws OAuth2TokenException;
}
