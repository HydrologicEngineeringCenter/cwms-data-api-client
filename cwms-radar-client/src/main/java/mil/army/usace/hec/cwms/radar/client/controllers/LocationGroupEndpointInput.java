package mil.army.usace.hec.cwms.radar.client.controllers;

import java.util.Objects;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;

public class LocationGroupEndpointInput extends EndpointInput {

    private final String groupId;
    private String officeId;
    private String categoryId;

    public LocationGroupEndpointInput(String groupId) {
        this.groupId = Objects.requireNonNull(groupId, "Cannot access the location group endpoint without a location group id");
    }

    public LocationGroupEndpointInput officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    public LocationGroupEndpointInput categoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    @Override
    protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
        return httpRequestBuilder.addQueryParameter("group-id", groupId)
            .addQueryParameter("office", officeId)
            .addQueryParameter("category-id", categoryId)
            .addQueryHeader("accept", "application/json");
    }
}
