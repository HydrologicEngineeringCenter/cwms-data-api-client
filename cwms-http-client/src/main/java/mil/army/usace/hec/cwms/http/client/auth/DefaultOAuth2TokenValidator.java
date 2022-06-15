package mil.army.usace.hec.cwms.http.client.auth;

public class DefaultOAuth2TokenValidator implements OAuth2TokenValidator {

    @Override
    public void validateToken(OAuth2Token oauth2Token) throws OAuth2TokenException {
        String accessToken = oauth2Token.getAccessToken();
        if (accessToken == null || accessToken.isEmpty()) {
            throw new OAuth2TokenException("Missing access token");
        }
        long expiresIn = oauth2Token.getExpiresIn();
        if (expiresIn <= 0) {
            throw new OAuth2TokenException("Token has expired");
        }
        String type = oauth2Token.getTokenType();
        if (type == null || type.isEmpty()) {
            throw new OAuth2TokenException("Missing required token type");
        }
    }

}
