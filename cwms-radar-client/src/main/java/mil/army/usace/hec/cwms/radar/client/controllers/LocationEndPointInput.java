package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;

import java.util.Objects;

public class LocationEndPointInput extends EndpointInput {

    private final String locationName;
    private String officeId;
    private String unit;

    public LocationEndPointInput(String locationName){
        this.locationName = Objects.requireNonNull(locationName, "Cannot access the location endpoint without a location name");
    }

    public LocationEndPointInput officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    public LocationEndPointInput unit(String unit) {
        this.unit = unit;
        return this;
    }

    @Override
    protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
        return httpRequestBuilder.addQueryParameter("name", locationName)
                .addQueryParameter("office", officeId)
                .addQueryParameter("unit", unit)
                .addQueryHeader("accept", "application/json;version=2");
    }
}
