/*
 * MIT License
 *
 * Copyright (c) 2026 Hydrologic Engineering Center
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

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_JSON;

import java.io.IOException;
import java.util.List;

import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.data.api.client.model.TimeSeriesGroup;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;

/**
 * Controller for the CDA v1 time series group endpoints. See {@link TimeSeriesGroupControllerV2} for v2.
 */
public final class TimeSeriesGroupControllerV1 {

    private static final String TIME_SERIES_GROUP_ENDPOINT = "timeseries/group/";

    public TimeSeriesGroup retrieveTimeSeriesGroup(ApiConnectionInfo apiConnectionInfo,
                                                   TimeSeriesGroupEndpointV1Input.GetOne input) throws IOException {
        String endpoint = TIME_SERIES_GROUP_ENDPOINT + input.groupId();
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addEndpointInput(input)
                .get();
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), TimeSeriesGroup.class);
        }
    }

    public List<TimeSeriesGroup> retrieveTimeSeriesGroups(ApiConnectionInfo apiConnectionInfo,
                                                          TimeSeriesGroupEndpointV1Input.GetAll input) throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, TIME_SERIES_GROUP_ENDPOINT)
                .addEndpointInput(input)
                .get();
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToListOfObjects(response.getBody(), TimeSeriesGroup.class);
        }
    }

    public void storeGroup(ApiConnectionInfo apiConnectionInfo, TimeSeriesGroupEndpointV1Input.Post input) throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.timeSeriesGroup());
        new HttpRequestBuilderImpl(apiConnectionInfo, TIME_SERIES_GROUP_ENDPOINT)
                .addEndpointInput(input)
                .post()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_JSON)
                .execute()
                .close();
    }

    public void updateGroup(ApiConnectionInfo apiConnectionInfo, TimeSeriesGroupEndpointV1Input.Patch input) throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.timeSeriesGroup());
        String endpoint = TIME_SERIES_GROUP_ENDPOINT + input.originalGroupId();
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addEndpointInput(input)
                .patch()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_JSON)
                .execute()
                .close();
    }

    public void deleteGroup(ApiConnectionInfo apiConnectionInfo, TimeSeriesGroupEndpointV1Input.Delete input) throws IOException {
        String endpoint = TIME_SERIES_GROUP_ENDPOINT + input.timeSeriesGroupId();
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addEndpointInput(input)
                .delete()
                .execute()
                .close();
    }
}
