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

package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_JSON;

import java.io.IOException;
import java.util.List;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesGroup;

public final class TimeSeriesGroupController {

    private static final String TIME_SERIES_GROUP_ENDPOINT = "timeseries/group";

    public TimeSeriesGroup retrieveTimeSeriesGroup(ApiConnectionInfo apiConnectionInfo,
                                                   TimeSeriesGroupEndpointInput.GetOne input)
            throws IOException {
        String endpoint = TIME_SERIES_GROUP_ENDPOINT + "/" + input.getGroupId();
        TimeSeriesGroup retVal;
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addEndpointInput(input)
                .get()
            .withMediaType(ACCEPT_HEADER_JSON);
        try (HttpRequestResponse response = executor.execute()) {
            retVal = RadarObjectMapper.mapJsonToObject(response.getBody(), TimeSeriesGroup.class);
        }
        return retVal;
    }

    public List<TimeSeriesGroup> retrieveTimeSeriesGroups(ApiConnectionInfo apiConnectionInfo,
                                                          TimeSeriesGroupEndpointInput.GetAll input)
            throws IOException {
        List<TimeSeriesGroup> retVal;
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, TIME_SERIES_GROUP_ENDPOINT)
                .addEndpointInput(input)
                .get()
            .withMediaType(ACCEPT_HEADER_JSON);
        try (HttpRequestResponse response = executor.execute()) {
            retVal = RadarObjectMapper.mapJsonToListOfObjects(response.getBody(), TimeSeriesGroup.class);
        }
        return retVal;
    }

    public void storeGroup(ApiConnectionInfo apiConnectionInfo, TimeSeriesGroupEndpointInput.Post input)
            throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.timeSeriesGroup());
        new HttpRequestBuilderImpl(apiConnectionInfo, TIME_SERIES_GROUP_ENDPOINT)
                .addEndpointInput(input)
                .post()
                .withBody(body)
            .withMediaType(ACCEPT_HEADER_JSON)
                .execute()
                .close();
    }

    public void updateGroup(ApiConnectionInfo apiConnectionInfo, TimeSeriesGroupEndpointInput.Patch input)
            throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.timeSeriesGroup());
        new HttpRequestBuilderImpl(apiConnectionInfo, TIME_SERIES_GROUP_ENDPOINT + "/" + input.originalLocationId())
                .addEndpointInput(input)
                .patch()
                .withBody(body)
            .withMediaType(ACCEPT_HEADER_JSON)
                .execute()
                .close();
    }

    public void deleteGroup(ApiConnectionInfo apiConnectionInfo, TimeSeriesGroupEndpointInput.Delete input)
            throws IOException {
        String endpoint = TIME_SERIES_GROUP_ENDPOINT + "/" + input.timeSeriesGroupId();
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addEndpointInput(input)
                .delete()
            .withMediaType(ACCEPT_HEADER_JSON)
                .execute()
                .close();
    }

}
