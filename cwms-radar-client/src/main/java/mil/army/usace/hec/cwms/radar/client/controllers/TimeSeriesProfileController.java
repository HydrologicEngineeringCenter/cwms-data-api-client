package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.io.IOException;
import java.util.List;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesProfile;


public final class TimeSeriesProfileController {
    private static final String TIME_SERIES_PROFILE = "timeseries/profile/";

    public TimeSeriesProfile retrieveTimeSeriesProfile(ApiConnectionInfo apiConnectionInfo,
            TimeSeriesProfileEndpointInput.GetOne input) throws IOException {
        String endpoint = TIME_SERIES_PROFILE + input.parameterId();
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), TimeSeriesProfile.class);
        }
    }

    public List<TimeSeriesProfile> retrieveTimeSeriesProfiles(ApiConnectionInfo apiConnectionInfo,
            TimeSeriesProfileEndpointInput.GetAll input) throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, TIME_SERIES_PROFILE)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToListOfObjects(response.getBody(), TimeSeriesProfile.class);
        }
    }

    public void storeTimeSeriesProfile(ApiConnectionInfo apiConnectionInfo, TimeSeriesProfileEndpointInput.Post input)
            throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.profile());
        new HttpRequestBuilderImpl(apiConnectionInfo, TIME_SERIES_PROFILE)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
                .addEndpointInput(input)
                .post()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_V2)
                .execute()
                .close();
    }

    public void deleteTimeSeriesProfile(ApiConnectionInfo apiConnectionInfo, TimeSeriesProfileEndpointInput.Delete input)
            throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo, TIME_SERIES_PROFILE + input.parameterId())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
                .addEndpointInput(input)
                .delete()
                .withMediaType(ACCEPT_HEADER_V2)
                .execute()
                .close();
    }
}
