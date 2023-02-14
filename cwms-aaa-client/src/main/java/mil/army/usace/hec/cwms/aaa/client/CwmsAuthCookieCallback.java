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

package mil.army.usace.hec.cwms.aaa.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.AuthCookieCallback;
import mil.army.usace.hec.cwms.http.client.CookieJarFactory;
import mil.army.usace.hec.cwms.http.client.HttpCookie;

public final class CwmsAuthCookieCallback implements AuthCookieCallback {

    private static final Logger LOGGER = Logger.getLogger(CwmsAuthCookieCallback.class.getName());
    private final ApiConnectionInfo apiConnectionInfo;
    private final CookieJarFactory.CookieJarSupplier cookieJarSupplier;

    public CwmsAuthCookieCallback(ApiConnectionInfo apiConnectionInfo, CookieJarFactory.CookieJarSupplier cookieJarSupplier) {
        this.apiConnectionInfo = apiConnectionInfo;
        this.cookieJarSupplier = cookieJarSupplier;
    }

    @Override
    public List<HttpCookie> authenticate() throws IOException {
        new CwmsLoginController().login(apiConnectionInfo);
        String apiRoot = apiConnectionInfo.getApiRoot();
        List<HttpCookie> retval = new ArrayList<>();
        LOGGER.log(Level.CONFIG, "Attempting to obtain CWMS_AAA login token");
        String message = "Attempted to obtain CWMS_AAA login token, but could not find auth cooke: %s for URL: %s";
        retval.add(cookieJarSupplier.getCookie(apiRoot, CwmsLoginController.JSESSIONIDSSO)
            .orElseThrow(() -> new IOException(String.format(message, CwmsLoginController.JSESSIONIDSSO, apiRoot))));
        retval.add(cookieJarSupplier.getCookie(apiRoot, CwmsLoginController.JSESSIONID)
            .orElseThrow(() -> new IOException(String.format(message, CwmsLoginController.JSESSIONID, apiRoot))));
        LOGGER.log(Level.CONFIG, "CWMS_AAA login token successfully obtained from: {0}", apiRoot);
        return retval;
    }
}
