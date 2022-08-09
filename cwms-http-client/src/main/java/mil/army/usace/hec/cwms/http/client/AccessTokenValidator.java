package mil.army.usace.hec.cwms.http.client;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.time.Instant;
import java.util.Date;

final class AccessTokenValidator {

    private static final String REFRESH_EXPIRED_BUFFER_PROPERTY_KEY = "cwms.http.client.token.refresh.buffer.millis";
    private static final long DEFAULT_REFRESH_EXPIRED_BUFFER_SECONDS = 1;

    private AccessTokenValidator() {
        throw new AssertionError("Utility class");
    }

    /**
     * Checks if token's access token is expired.
     *
     * @param token - OAuth2Token to check.
     * @return boolean TRUE if expired, else FALSE
     */
    static boolean isTokenExpired(String token) {
        DecodedJWT jwt = JWT.decode(token);
        long bufferMillis = Instant.ofEpochSecond(DEFAULT_REFRESH_EXPIRED_BUFFER_SECONDS)
            .toEpochMilli(); //default 1 second buffer
        String bufferStr = System.getProperty(REFRESH_EXPIRED_BUFFER_PROPERTY_KEY);
        if (bufferStr != null) {
            bufferMillis = Long.parseLong(bufferStr);
        }
        //take current time and subtract buffer. If token is expired at that time, then its no longer valid
        Instant noLongerValid = Instant.now().minusMillis(bufferMillis);
        return jwt.getExpiresAt().before(Date.from(noLongerValid));

    }

}
