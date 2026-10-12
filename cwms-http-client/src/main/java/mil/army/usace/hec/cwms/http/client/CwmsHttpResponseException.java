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

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class CwmsHttpResponseException extends IOException {
    private static final String ERROR_MESSAGE = "Unknown error occurred for request: %s %s \nError code: %s %s %s";
    private final String reasonPhrase;
    private final String url;
    private final int errorCode;
    private final String responseBody;
    private final String requestType;
    private final String requestBody;
    private final Map<String, List<String>> headers;

    CwmsHttpResponseException(Response execute, Request request, ResponseBody responseBody) throws IOException {
        this.reasonPhrase = execute.message();
        this.url = request.url().toString();
        this.errorCode = execute.code();
        this.requestType = request.method();
        Map<String, List<String>> failureHeaders = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        execute.headers().toMultimap().forEach((name, values) ->
            failureHeaders.put(name, Collections.unmodifiableList(List.copyOf(values))));
        this.headers = Collections.unmodifiableMap(failureHeaders);
        String bodyText = null;
        if (responseBody != null) {
            try {
                bodyText = responseBody.string();
            } catch (IOException readError) {
                // keep status, phrase and headers; the read error is retained as the cause
                initCause(readError);
            }
        }
        this.responseBody = bodyText;
        RequestBody requestBody = request.body();
        if (requestBody == null) {
            this.requestBody = null;
        } else {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            this.requestBody = buffer.readUtf8();
        }
    }

    public final String getReasonPhrase() {
        return reasonPhrase;
    }

    public final String getUrl() {
        return url;
    }

    public final int getErrorCode() {
        return errorCode;
    }

    public final String getRequestType() {
        return requestType;
    }

    /**
     * Response headers of the failed response. Names are matched case-insensitively and each name maps to all of its
     * values in order (for example a repeated {@code Retry-After}). The map and its lists are immutable.
     *
     * @return the immutable failure headers, never null
     */
    public final Map<String, List<String>> getHeaders() {
        return headers;
    }

    /**
     * @param name the header name, case-insensitive
     * @return all values of the header in order, empty if the header is absent; immutable
     */
    public final List<String> getHeaderValues(String name) {
        return headers.getOrDefault(name, Collections.emptyList());
    }

    /**
     * The response body text. Empty when the response had no body, or when reading it failed; in the latter case
     * {@link #getCause()} is the read error and the status, phrase and headers are still available.
     *
     * @return the response body, if it could be read
     */
    public final Optional<String> getResponseBody() {
        return Optional.ofNullable(responseBody);
    }

    public final Optional<String> getRequestBody() {
        return Optional.ofNullable(requestBody);
    }

    @Override
    public String getMessage() {
        return String.format(ERROR_MESSAGE, getRequestType(), getUrl(), getErrorCode(), getReasonPhrase(),
                getResponseBody().map(c -> "\n" + c).orElse(""));
    }
}
