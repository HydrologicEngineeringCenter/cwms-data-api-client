/*
 * Copyright (c) 2023
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesIdentifierDescriptor;

public final class TimeSeriesIdentifierController {
    private static final String TIME_SERIES_ENDPOINT = "timeseries/identifier-descriptor";

    public TimeSeriesIdentifierDescriptor retrieveTimeSeriesIdentifier(ApiConnectionInfo apiConnectionInfo,
                                                                       TimeSeriesIdentifierEndpointInput.GetOne input) throws IOException {
        TimeSeriesIdentifierDescriptor retVal;
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, TIME_SERIES_ENDPOINT)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
            .addEndpointInput(input)
            .get()
            .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            retVal = RadarObjectMapper.mapJsonToObject(response.getBody(), TimeSeriesIdentifierDescriptor.class);
        }
        return retVal;
    }

    public void storeTimeSeriesIdentifier(ApiConnectionInfo apiConnectionInfo, TimeSeriesIdentifierEndpointInput.Post input) throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.timeSeriesIdentifierDescriptor());
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, TIME_SERIES_ENDPOINT)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
            .addEndpointInput(input)
            .post()
            .withBody(body)
            .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
        }
    }

    public void deleteTimeSeriesIdentifier(ApiConnectionInfo apiConnectionInfo, TimeSeriesIdentifierEndpointInput.Delete input) throws IOException {
        String endpoint = TIME_SERIES_ENDPOINT + "/" + input.timeSeriesId();
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
            .addEndpointInput(input)
            .delete()
            .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
        }
    }
}
