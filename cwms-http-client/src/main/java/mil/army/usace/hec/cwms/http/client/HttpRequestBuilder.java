package mil.army.usace.hec.cwms.http.client;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.request.HttpPostRequest;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestMediaType;

public interface HttpRequestBuilder {
    HttpRequestBuilder addQueryParameter(String key, String value);

    HttpRequestBuilder addQueryHeader(String key, String value);

    HttpRequestBuilder addEndpointInput(EndpointInput endpointInput);

    HttpRequestBuilder enableHttp2();

    HttpPostRequest post() throws IOException;

    HttpRequestMediaType get() throws IOException;

}

