/*
 * MIT License
 *
 * Copyright (c) 2022 Hydrologic Engineering Center
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package mil.army.usace.hec.cwms.http.client;

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
     * @param jwt - JWT to check.
     * @return boolean TRUE if expired, else FALSE
     */
    static boolean isTokenExpired(DecodedJWT jwt) {
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
