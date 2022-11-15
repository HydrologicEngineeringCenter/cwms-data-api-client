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
