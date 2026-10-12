/*
 * MIT License
 *
 * Copyright (c) 2023 Hydrologic Engineering Center
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

import okhttp3.ResponseBody;

import okhttp3.Headers;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Successful response of an executed request. The caller owns the response and must close it (for example with
 * try-with-resources) to release the underlying connection, whether or not the body was read. Failed responses are
 * never returned: the library closes their body itself before throwing. See {@link HttpRequestOptions} for the scope
 * of the timeouts that apply while the body is consumed.
 */
public final class HttpRequestResponse implements AutoCloseable {

    private final ResponseBody body;
    private final Set<HttpCookie> cookies;
    private final boolean usedCache;
    private final Headers headers;
    private final int statusCode;

    HttpRequestResponse(ResponseBody body, Set<HttpCookie> cookies, Headers headers, boolean usedCache) {
        this(body, cookies, headers, usedCache, 200);
    }

    HttpRequestResponse(ResponseBody body, Set<HttpCookie> cookies, Headers headers, boolean usedCache, int statusCode) {
        this.statusCode = statusCode;
        this.body = body;
        this.cookies = cookies;
        this.usedCache = usedCache;
        this.headers = headers;
    }

    /**
     * @return the HTTP status code of the response, always in the 2xx range for a returned response
     */
    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() throws IOException {
        return body.string();
    }

    public boolean usedCache() {
        return usedCache;
    }

    public InputStream getStream() {
        return body.byteStream();
    }

    public Set<HttpCookie> getCookies() {
        return new HashSet<>(cookies);
    }

    public Map<String, List<String>> getHeaders() {
        return headers.toMultimap();
    }

    @Override
    public void close() {
        body.close();
    }
}
