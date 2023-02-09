package mil.army.usace.hec.cwms.http.client;

import mil.army.usace.hec.cwms.http.client.request.HttpPostRequest;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestMediaType;

import java.io.IOException;

public interface HttpRequestBuilder {
    HttpRequestBuilder addQueryParameter(String key, String value);

    HttpRequestBuilder addQueryHeader(String key, String value);

    HttpRequestBuilder addEndpointInput(EndpointInput endpointInput);

    HttpPostRequest post() throws IOException;

    HttpRequestMediaType get() throws IOException;

}

