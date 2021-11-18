package mil.army.usace.hec.cwms.http.client;

import java.io.IOException;

public interface HttpRequestBuilder {
    HttpRequestBuilder addQueryParameter(String key, String value);

    HttpRequestBuilder addQueryHeader(String key, String value);

    HttpRequestBuilder addEndpointInput(EndpointInput endpointInput);

    HttpRequestResponse execute() throws IOException;
}
