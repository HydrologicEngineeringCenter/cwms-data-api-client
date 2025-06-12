package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesProfile;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesProfileList;


public final class TimeSeriesProfileController {
    private static final String TIME_SERIES_PROFILE = "timeseries/profile/";

    public TimeSeriesProfile retrieveTimeSeriesProfile(ApiConnectionInfo apiConnectionInfo,
            TimeSeriesProfileEndpointInput.GetOne input) throws IOException {
        String endpoint = TIME_SERIES_PROFILE + input.locationId() + "/" + input.parameterId();
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), TimeSeriesProfile.class);
        }
    }

    public TimeSeriesProfileList retrieveTimeSeriesProfiles(ApiConnectionInfo apiConnectionInfo,
            TimeSeriesProfileEndpointInput.GetAll input) throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, TIME_SERIES_PROFILE)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), TimeSeriesProfileList.class);
        }
    }

    public void storeTimeSeriesProfile(ApiConnectionInfo apiConnectionInfo, TimeSeriesProfileEndpointInput.Post input)
            throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.profile());
        new HttpRequestBuilderImpl(apiConnectionInfo, TIME_SERIES_PROFILE)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .post()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }

    public void deleteTimeSeriesProfile(ApiConnectionInfo apiConnectionInfo, TimeSeriesProfileEndpointInput.Delete input)
            throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo, TIME_SERIES_PROFILE + input.locationId() + "/" + input.parameterId())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .delete()
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }
}
