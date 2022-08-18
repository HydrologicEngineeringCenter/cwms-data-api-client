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

import java.io.IOException;
import java.util.Optional;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class CwmsHttpResponseException extends IOException {
    private static final String ERROR_MESSAGE = "Unknown error occurred for request: %s %s \nError code: %s %s %s";
    private final String reasonPhrase;
    private final String url;
    private final int errorCode;
    private final String responseBody;
    private final String requestType;

    CwmsHttpResponseException(Response execute, Request request, ResponseBody responseBody) throws IOException {
        this.reasonPhrase = execute.message();
        this.url = request.url().toString();
        this.errorCode = execute.code();
        this.requestType = request.method();
        if (responseBody == null) {
            this.responseBody = null;
        } else {
            this.responseBody = responseBody.string();
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

    public final Optional<String> getResponseBody() {
        return Optional.ofNullable(responseBody);
    }

    @Override
    public String getMessage() {
        return String.format(ERROR_MESSAGE, getRequestType(), getUrl(), getErrorCode(), getReasonPhrase(),
            getResponseBody().map(c -> "\n" + c).orElse(""));
    }
}
