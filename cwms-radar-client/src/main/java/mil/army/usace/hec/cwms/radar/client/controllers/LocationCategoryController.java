package mil.army.usace.hec.cwms.radar.client.controllers;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.radar.client.model.LocationCategory;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;

public class LocationCategoryController {

    /**
     * Retrieve Location Category.
     *
     * @param apiConnectionInfo             - connection info
     * @param locationCategoryEndpointInput - category id and office
     * @return LocationCategory
     * @throws IOException - thrown if retrieve failed
     */
    public LocationCategory retrieveLocationCategory(ApiConnectionInfo apiConnectionInfo, LocationCategoryEndpointInput locationCategoryEndpointInput)
        throws IOException {
        HttpRequestResponse response = new HttpRequestBuilder(apiConnectionInfo, "location/category")
            .addEndpointInput(locationCategoryEndpointInput)
            .execute();
        return RadarObjectMapper.mapJsonToObject(response.getBody(), LocationCategory.class);
    }
}
