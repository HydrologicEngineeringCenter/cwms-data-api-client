/*
 * MIT License
 *
 * Copyright (c) 2025 Hydrologic Engineering Center
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

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;

import java.io.IOException;
import mil.army.usace.hec.cwms.data.api.client.model.Location;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;

public final class LocationController {

    private static final String LOCATION_ENDPOINT = "locations";

    public Location retrieveLocation(ApiConnectionInfo apiConnectionInfo, LocationEndPointInput.GetOne locationEndpointInput) throws IOException {
        String locationId = locationEndpointInput.locationId();
        Location retVal;
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, LOCATION_ENDPOINT + "/" + locationId)
            .addEndpointInput(locationEndpointInput)
            .get();
        try (HttpRequestResponse response = executor.execute()) {
            retVal = RadarObjectMapper.mapJsonToObject(response.getBody(), Location.class);
        }
        return retVal;
    }

    public void storeLocation(ApiConnectionInfo apiConnectionInfo, LocationEndPointInput.Post endpointInput) throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(endpointInput.location());
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, LOCATION_ENDPOINT)
            .addEndpointInput(endpointInput)
            .post()
            .withBody(body)
            .withMediaType(ACCEPT_HEADER_V2);
        executor.execute().close();
    }

    public void updateLocation(ApiConnectionInfo apiConnectionInfo, LocationEndPointInput.Patch endpointInput) throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(endpointInput.location());
        new HttpRequestBuilderImpl(apiConnectionInfo, LOCATION_ENDPOINT + "/" + endpointInput.originalLocationId())
            .addEndpointInput(endpointInput)
            .patch()
            .withBody(body)
            .withMediaType(ACCEPT_HEADER_V2)
            .execute()
            .close();
    }

    public void deleteLocation(ApiConnectionInfo apiConnectionInfo, LocationEndPointInput.Delete locationEndPointInput) throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo, LOCATION_ENDPOINT + "/" + locationEndPointInput.getLocationId())
            .addEndpointInput(locationEndPointInput)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
            .delete()
            .execute()
            .close();
    }
}
