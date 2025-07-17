/*
 * MIT License
 *
 * Copyright (c) 2024 Hydrologic Engineering Center
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package mil.army.usace.hec.cwms.data.api.client.controllers;

import java.io.IOException;
import java.util.List;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_JSON;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.data.api.client.model.LocationGroup;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;

public final class LocationGroupController {

    private static final String LOCATION_GROUP = "location/group";

    /**
     * @param apiConnectionInfo - connection info
     * @param input             - group id, office id, and category id for location group
     * @return LocationGroup based on inputs given
     * @throws IOException - thrown if retrieval failed
     */
    public LocationGroup retrieveLocationGroup(ApiConnectionInfo apiConnectionInfo, LocationGroupEndpointInput.GetOne input)
            throws IOException {
        LocationGroup retVal;
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo,
                LOCATION_GROUP + "/" + input.groupId())
                .addEndpointInput(input)
                .get()
            .withMediaType(ACCEPT_HEADER_JSON);
        try (HttpRequestResponse response = executor.execute()) {
            retVal = RadarObjectMapper.mapJsonToObject(response.getBody(), LocationGroup.class);
        }
        return retVal;
    }

    /**
     * @param apiConnectionInfo - connection info
     * @param input             - office id
     * @return LocationGroup list for input office
     * @throws IOException - thrown if retrieval failed
     */
    public List<LocationGroup> retrieveLocationGroups(ApiConnectionInfo apiConnectionInfo, LocationGroupEndpointInput.GetAll input)
            throws IOException {
        List<LocationGroup> retVal;
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, LOCATION_GROUP)
                .addEndpointInput(input)
                .get()
            .withMediaType(ACCEPT_HEADER_JSON);
        try (HttpRequestResponse response = executor.execute()) {
            retVal = RadarObjectMapper.mapJsonToListOfObjects(response.getBody(), LocationGroup.class);
        }
        return retVal;
    }

    public void storeLocationGroup(ApiConnectionInfo apiConnectionInfo, LocationGroupEndpointInput.Post input)
            throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.locationGroup());
        new HttpRequestBuilderImpl(apiConnectionInfo, LOCATION_GROUP)
                .addEndpointInput(input)
                .post()
                .withBody(body)
            .withMediaType(ACCEPT_HEADER_JSON)
                .execute()
                .close();

    }

    public void updateLocationGroup(ApiConnectionInfo apiConnectionInfo, LocationGroupEndpointInput.Patch input)
            throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.locationGroup());
        new HttpRequestBuilderImpl(apiConnectionInfo, LOCATION_GROUP + "/" + input.originalGroupId())
                .addEndpointInput(input)
                .patch()
                .withBody(body)
            .withMediaType(ACCEPT_HEADER_JSON)
                .execute()
                .close();

    }

    public void deleteLocationGroup(ApiConnectionInfo apiConnectionInfo, LocationGroupEndpointInput.Delete input)
            throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo, LOCATION_GROUP + "/" + input.groupId())
                .addEndpointInput(input)
                .delete()
            .withMediaType(ACCEPT_HEADER_JSON)
                .execute()
                .close();

    }
}
