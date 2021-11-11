/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.radar.client.controllers;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeries;

public final class TimeSeriesController {

    private static final String TIME_SERIES_ENDPOINT = "timeseries";

    public TimeSeries retrieveTimeSeries(ApiConnectionInfo apiConnectionInfo, TimeSeriesEndpointInput timeSeriesEndpointInput) throws IOException {
        HttpRequestResponse response = new HttpRequestBuilder(apiConnectionInfo, TIME_SERIES_ENDPOINT)
            .addQueryHeader("accept", "application/json;version=2")
            .addEndpointInput(timeSeriesEndpointInput)
            .execute();
        return RadarObjectMapper.mapJsonToObject(response.getBody(), TimeSeries.class);
    }
}
