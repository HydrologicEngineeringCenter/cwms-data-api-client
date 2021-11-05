/*
 * MIT License
 *
 * Copyright (c) 2021 Hydrologic Engineering Center
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
import java.net.ConnectException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public final class HttpRequestBuilder {

    private final String endpoint;
    private final HttpUrl httpUrl;
    private final Map<String, String> queryParameters = new HashMap<>();
    private final Map<String, String> queryHeaders = new HashMap<>();
    private Duration callTimeout = Duration.ofMinutes(5);
    private Duration readTimeout = Duration.ofMinutes(5);
    private Duration connectTimeout = Duration.ofSeconds(5);

    public HttpRequestBuilder(ApiConnectionInfo apiConnectionInfo, String endpoint) {
        Objects.requireNonNull(apiConnectionInfo, "API connection info must be defined");
        Objects.requireNonNull(apiConnectionInfo.getApiRoot(), "API root must be defined");
        this.httpUrl = HttpUrl.parse(apiConnectionInfo.getApiRoot());
        this.endpoint = Objects.requireNonNull(endpoint, "Cannot process request against the API root endpoint");
    }

    public HttpRequestBuilder callTimeout(Duration callTimeout) {
        this.callTimeout = callTimeout;
        return this;
    }

    public HttpRequestBuilder readTimeout(Duration readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    public HttpRequestBuilder connectTimeout(Duration connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public HttpRequestBuilder addQueryParameter(String key, String value) {
        queryParameters.put(key, value);
        return this;
    }

    public HttpRequestBuilder addQueryHeader(String key, String value) {
        queryHeaders.put(key, value);
        return this;
    }

    public HttpRequestBuilder addEndpointInput(EndpointInput endpointInput) {
        endpointInput.addInputParameters(this);
        return this;
    }

    private OkHttpClient createClient() {
        return new OkHttpClient.Builder()
            .callTimeout(callTimeout)
            .connectTimeout(readTimeout)
            .readTimeout(connectTimeout)
            .build();
    }

    private Request createRequest() throws IOException {
        HttpUrl resolve = httpUrl.resolve(endpoint);
        if (resolve == null) {
            throw new IOException("Endpoint to API is malformed: " + endpoint);
        }
        HttpUrl.Builder urlBuilder = resolve.newBuilder();
        queryParameters.forEach(urlBuilder::addQueryParameter);
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(urlBuilder.build());
        queryHeaders.forEach(requestBuilder::addHeader);
        return requestBuilder.build();
    }

    public HttpRequestResponse execute() throws IOException {
        OkHttpClient client = createClient();
        Request request = createRequest();
        ResponseBody body = null;
        try {
            Response execute = client.newCall(request).execute();
            if (execute.isSuccessful()) {
                body = execute.body();
                if (body == null) {
                    throw new IOException("Error with request, body not returned for request: " + request);
                }
                return new HttpRequestResponse(body.string());
            } else {
                int code = execute.code();
                body = execute.body();
                if (code == 404) {
                    if (body == null) {
                        throw new NoDataFoundException("No data found for request: " + request);
                    }
                    throw new NoDataFoundException("No data found for request: " + request + "\n" + body.string());
                } else {
                    if (body == null) {
                        throw new IOException(
                            "Unknown error occurred for request: " + request + "\n Error code: " + code + " " + execute.message());
                    }
                    throw new IOException(
                        "Unknown error occurred for request: " + request + "\n Error code: " + code + " " + execute.message() + "\n" + body.string());
                }
            }
        } catch (ConnectException connectException) {
            throw new ServerNotFoundException(connectException);
        } finally {
            if (body != null) {
                body.close();
            }
        }
    }
}
