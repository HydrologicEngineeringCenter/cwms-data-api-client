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

public final class HttpRequestResponse implements AutoCloseable {

    private final ResponseBody body;
    private final Set<HttpCookie> cookies;
    private final boolean usedCache;
    private final Headers headers;

    HttpRequestResponse(ResponseBody body, Set<HttpCookie> cookies, Headers headers, boolean usedCache) {
        this.body = body;
        this.cookies = cookies;
        this.usedCache = usedCache;
        this.headers = headers;
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
