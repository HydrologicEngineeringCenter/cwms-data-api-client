package mil.army.usace.hec.cwms.radar.client.controllers;

import static java.lang.String.format;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.io.IOException;
import java.util.List;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.WaterUser;


public final class WaterUserController {
    private static final String ENDPOINT = "projects/%s/%s/water-user";

    public static WaterUser retrieveWaterUser(ApiConnectionInfo apiConnectionInfo,
            WaterUserEndpointInput.GetOne input) throws IOException {
        String endpoint = format(ENDPOINT + "/%s", input.getOfficeId(), input.getProjectId(), input.waterUserId());
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), WaterUser.class);
        }
    }

    public static List<WaterUser> retrieveWaterUsers(ApiConnectionInfo apiConnectionInfo,
            WaterUserEndpointInput.GetAll input) throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo,
                format(ENDPOINT, input.getOfficeId(), input.getProjectId()))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToListOfObjects(response.getBody(), WaterUser.class);
        }
    }

    public static void storeWaterUser(ApiConnectionInfo apiConnectionInfo, WaterUserEndpointInput.Post input)
            throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.getWaterUser());
        new HttpRequestBuilderImpl(apiConnectionInfo, format(ENDPOINT, input.getOfficeId(), input.getProjectId()))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .post()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }

    public static void renameWaterUser(ApiConnectionInfo apiConnectionInfo, WaterUserEndpointInput.Patch input)
            throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo,
                format(ENDPOINT + "/%s", input.getOfficeId(), input.getProjectId(), input.oldWaterUserId()))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .patch()
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }

    public static void deleteWaterUser(ApiConnectionInfo apiConnectionInfo, WaterUserEndpointInput.Delete input)
            throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo,
                format(ENDPOINT + "/%s", input.getOfficeId(), input.getProjectId(), input.waterUserId()))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .delete()
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }
}
