package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.http.client.request.HttpPostRequest;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestMediaType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MockHttpRequestBuilder implements HttpRequestBuilder {

    private final Map<String, String> queryParameters = new HashMap<>();
    private final Map<String, String> queryHeaders = new HashMap<>();

    @Override
    public MockHttpRequestBuilder addQueryParameter(String key, String value) {
        queryParameters.put(key, value);
        return this;
    }

    @Override
    public MockHttpRequestBuilder addQueryHeader(String key, String value) {
        queryHeaders.put(key, value);
        return this;
    }

    @Override
    public MockHttpRequestBuilder addEndpointInput(EndpointInput endpointInput) {
        return this;
    }

    @Override
    public HttpPostRequest post() {
        return null;
    }

    @Override
    public HttpRequestMediaType get() throws IOException {
        return null;
    }

    String getQueryParameter(String parameter) {
        return queryParameters.get(parameter);
    }

    String getQueryHeader(String header) {
        return queryHeaders.get(header);
    }
}
