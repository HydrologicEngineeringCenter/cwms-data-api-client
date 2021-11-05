package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.radar.client.model.Location;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;

import java.io.IOException;

public class LocationController {

    public Location retrieveLocation(ApiConnectionInfo apiConnectionInfo, LocationEndPointInput locationEndpointInput) throws IOException {
        HttpRequestResponse response = new HttpRequestBuilder(apiConnectionInfo, "locations")
                .addEndpointInput(locationEndpointInput)
                .execute();
        return RadarObjectMapper.mapJsonToObject(response.getBody(), Location.class);
    }
}
