/*
 * MIT License
 *
 * Copyright (c) 2022 Hydrologic Engineering Center
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

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.io.IOException;
import java.util.logging.Logger;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeries;

public final class TimeSeriesController {

    private static final Logger LOGGER = Logger.getLogger(TimeSeriesController.class.getName());
    private static final String TIME_SERIES_ENDPOINT = "timeseries";

    public TimeSeries retrieveTimeSeries(ApiConnectionInfo apiConnectionInfo, TimeSeriesEndpointInput.GetOne timeSeriesEndpointInput) throws IOException {
        TimeSeries retVal;
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, TIME_SERIES_ENDPOINT)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
            .addEndpointInput(timeSeriesEndpointInput)
            .get()
            .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            retVal = RadarObjectMapper.mapJsonToObject(response.getBody(), TimeSeries.class);
        }
        return retVal;
    }

    public void storeTimeSeries(ApiConnectionInfo apiConnectionInfo, TimeSeriesEndpointInput.Post timeSeriesEndpointInput) throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(timeSeriesEndpointInput.timeSeries());
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, TIME_SERIES_ENDPOINT)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
            .addEndpointInput(timeSeriesEndpointInput)
            .post()
            .withBody(body)
            .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
        }
    }

    public void deleteTimeSeries(ApiConnectionInfo apiConnectionInfo, TimeSeriesEndpointInput.Delete timeSeriesEndpointInput) throws IOException {
        String endpoint = TIME_SERIES_ENDPOINT + "/" + timeSeriesEndpointInput.timeSeriesId();
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
            .addEndpointInput(timeSeriesEndpointInput)
            .delete()
            .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            System.out.println(response.getBody());
        }
    }
}
