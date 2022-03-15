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
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import usace.metrics.noop.NoOpTimer;
import usace.metrics.services.Metrics;
import usace.metrics.services.Timer;

public class HttpRequestBuilderImpl implements HttpRequestBuilder {

    private final String endpoint;
    private final HttpUrl httpUrl;
    private final Map<String, String> queryParameters = new HashMap<>();
    private final Map<String, String> queryHeaders = new HashMap<>();

    public HttpRequestBuilderImpl(ApiConnectionInfo apiConnectionInfo, String endpoint) throws ServerNotFoundException {
        Objects.requireNonNull(apiConnectionInfo, "API connection info must be defined");
        Objects.requireNonNull(apiConnectionInfo.getApiRoot(), "API root must be defined");
        HttpUrl url = HttpUrl.parse(apiConnectionInfo.getApiRoot());
        if (url == null) {
            throw new ServerNotFoundException("Invalid HTTP URL: " + apiConnectionInfo.getApiRoot());
        }
        this.httpUrl = url;
        this.endpoint = Objects.requireNonNull(endpoint, "Cannot process request against the API root endpoint");
    }

    @Override
    public final HttpRequestBuilderImpl addQueryParameter(String key, String value) {
        if (value == null) {
            queryParameters.remove(key);
        } else {
            queryParameters.put(key, value);
        }
        return this;
    }

    @Override
    public final HttpRequestBuilderImpl addQueryHeader(String key, String value) {
        if (value == null) {
            queryParameters.remove(key);
        } else {
            queryHeaders.put(key, value);
        }
        return this;
    }

    @Override
    public final HttpRequestBuilderImpl addEndpointInput(EndpointInput endpointInput) {
        endpointInput.addInputParameters(this);
        return this;
    }

    //Package scoped for testing
    Request createRequest() throws IOException {
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

    @Override
    public final HttpRequestResponse execute() throws IOException {
        Request request = createRequest();
        ResponseBody body = null;
        try (Timer.Context timer = createTimer().start()) {
            OkHttpClient client = getOkHttpClient();
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
        } catch (ConnectException | UnknownHostException | SocketTimeoutException connectException) {
            throw new ServerNotFoundException(connectException);
        } finally {
            if (body != null) {
                body.close();
            }
        }
    }

    /**
     * protected scope to allow separate reuse of the OkHttpClient configuration for different API's
     * An example would be this default instance for CWMS RADAR and a separate OkHttpClient for Cumulus
     *
     * @return singleton OkHttpClient reused across API endpoints
     */
    protected OkHttpClient getOkHttpClient() {
        return OkHttpClientInstance.getInstance();
    }

    private Timer createTimer() {
        if (!CwmsHttpClientMetrics.isMetricsEnabled()) {
            return new NoOpTimer();
        }
        Metrics metrics = CwmsHttpClientMetrics.createMetrics(Objects.toString(httpUrl.resolve(endpoint)));
        Timer timer = metrics.createTimer();
        Properties metricsProperties = new Properties();
        metricsProperties.putAll(queryParameters);
        metricsProperties.putAll(queryHeaders);
        timer.setMetricProperties(metricsProperties);
        return timer;
    }
}
