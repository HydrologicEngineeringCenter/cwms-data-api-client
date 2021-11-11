/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client;

import java.io.IOException;
import java.net.ConnectException;
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

    public HttpRequestBuilder(ApiConnectionInfo apiConnectionInfo, String endpoint) {
        Objects.requireNonNull(apiConnectionInfo, "API connection info must be defined");
        Objects.requireNonNull(apiConnectionInfo.getApiRoot(), "API root must be defined");
        this.httpUrl = HttpUrl.parse(apiConnectionInfo.getApiRoot());
        this.endpoint = Objects.requireNonNull(endpoint, "Cannot process request against the API root endpoint");
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
        Request request = createRequest();
        ResponseBody body = null;
        try {
            OkHttpClient client = OkHttpClientInstance.getInstance();
            Response execute = client.newCall(request).execute();
            if (execute.isSuccessful()) {
                body = execute.body();
                if (body == null) {
                    throw new IOException("Error with request, body not returned for request: " + request);
                }
                return new HttpRequestResponse(body.string());
            }
            else {
                int code = execute.code();
                body = execute.body();
                if (code == 404) {
                    if (body == null) {
                        throw new NoDataFoundException("No data found for request: " + request);
                    }
                    throw new NoDataFoundException("No data found for request: " + request + "\n" + body.string());
                }
                else {
                    if (body == null) {
                        throw new IOException(
                            "Unknown error occurred for request: " + request + "\n Error code: " + code + " " + execute.message());
                    }
                    throw new IOException(
                        "Unknown error occurred for request: " + request + "\n Error code: " + code + " " + execute.message() + "\n" + body.string());
                }
            }
        }
        catch (ConnectException connectException) {
            throw new ServerNotFoundException(connectException);
        }
        finally {
            if (body != null) {
                body.close();
            }
        }
    }
}
