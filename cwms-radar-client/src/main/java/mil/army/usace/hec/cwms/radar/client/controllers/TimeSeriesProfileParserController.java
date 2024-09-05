package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesProfileParser;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesProfileParserColumnar;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesProfileParserIndexed;


public class TimeSeriesProfileParserController {
    private static final String TIME_SERIES_PROFILE_PARSER = "timeseries/parser/";

    public TimeSeriesProfileParser retrieveTimeSeriesProfileParser(ApiConnectionInfo apiConnectionInfo,
            TimeSeriesProfileParserEndpointInput.GetOne input) throws IOException {
        String endpoint = TIME_SERIES_PROFILE_PARSER + input.parameterId();
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), TimeSeriesProfileParser.class);
        }
    }

    public List<TimeSeriesProfileParser> retrieveTimeSeriesProfileParsers(ApiConnectionInfo apiConnectionInfo,
            TimeSeriesProfileParserEndpointInput.GetAll input) throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, TIME_SERIES_PROFILE_PARSER)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse response = executor.execute()) {
            String responseBody = response.getBody();
            try {
                return RadarObjectMapper.mapJsonToListOfObjects(responseBody, TimeSeriesProfileParserColumnar.class)
                        .stream()
                        .map(TimeSeriesProfileParserColumnar.class::cast)
                        .collect(Collectors.toList());
            } catch (Exception e) {
                return RadarObjectMapper.mapJsonToListOfObjects(responseBody, TimeSeriesProfileParserIndexed.class)
                        .stream()
                        .map(TimeSeriesProfileParserIndexed.class::cast)
                        .collect(Collectors.toList());
            }
        }
    }

    public void storeTimeSeriesProfileParser(ApiConnectionInfo apiConnectionInfo, TimeSeriesProfileParserEndpointInput.Post input)
            throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.profileParser());
        new HttpRequestBuilderImpl(apiConnectionInfo, TIME_SERIES_PROFILE_PARSER)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .post()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }

    public void deleteTimeSeriesProfileParser(ApiConnectionInfo apiConnectionInfo, TimeSeriesProfileParserEndpointInput.Delete input)
            throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo, TIME_SERIES_PROFILE_PARSER + input.parameterId())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .delete()
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }
}
