package mil.army.usace.hec.cwms.radar.client.controllers;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.radar.client.model.LocationGroup;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;

public class LocationGroupController {

    /**
     * @param apiConnectionInfo          - connection info
     * @param locationGroupEndpointInput - group id, office id, and category id for location group
     * @return LocationGroup based on inputs given
     * @throws IOException - thrown if retrieval failed
     */
    public LocationGroup retrieveLocationGroup(ApiConnectionInfo apiConnectionInfo, LocationGroupEndpointInput locationGroupEndpointInput)
        throws IOException {
        HttpRequestResponse response = new HttpRequestBuilder(apiConnectionInfo, "location/group")
            .addEndpointInput(locationGroupEndpointInput)
            .execute();
        return RadarObjectMapper.mapJsonToObject(response.getBody(), LocationGroup.class);
    }
}
