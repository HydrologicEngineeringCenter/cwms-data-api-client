package mil.army.usace.hec.cwms.radar.client.controllers;

import java.util.Objects;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;

public class LocationCategoryEndpointInput extends EndpointInput {

    private final String categoryId;
    private String officeId;

    public LocationCategoryEndpointInput(String categoryId) {
        this.categoryId = Objects.requireNonNull(categoryId, "Cannot access the location category endpoint without a category id");
    }

    public LocationCategoryEndpointInput officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    @Override
    protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
        return httpRequestBuilder.addQueryParameter("category-id", categoryId)
            .addQueryParameter("office", officeId)
            .addQueryHeader("accept", "application/json");
    }
}
