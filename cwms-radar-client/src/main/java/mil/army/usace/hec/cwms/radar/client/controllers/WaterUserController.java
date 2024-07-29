package mil.army.usace.hec.cwms.radar.client.controllers;

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
    private static final String ENDPOINT_PREFIX = "projects/";
    private static final String ENDPOINT_WATER_USER = "/water-user";

    public static WaterUser retrieveWaterUser(ApiConnectionInfo apiConnectionInfo,
            WaterUserEndpointInput.GetOne input) throws IOException {
        String endpoint = ENDPOINT_PREFIX + input.getOfficeId() + "/" + input.getProjectId() + ENDPOINT_WATER_USER
                + "/" + input.waterUserId();
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
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, ENDPOINT_PREFIX
                + input.getOfficeId() + "/" + input.getProjectId() + ENDPOINT_WATER_USER)
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
        String body = RadarObjectMapper.mapObjectToJson(input.waterUser());
        new HttpRequestBuilderImpl(apiConnectionInfo, ENDPOINT_PREFIX + input.getOfficeId() + "/"
                + input.getProjectId() + ENDPOINT_WATER_USER)
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
        new HttpRequestBuilderImpl(apiConnectionInfo, ENDPOINT_PREFIX + input.getOfficeId() + "/"
                + input.getProjectId() + ENDPOINT_WATER_USER+ "/" + input.oldWaterUserId())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .patch()
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }

    public static void deleteWaterUser(ApiConnectionInfo apiConnectionInfo, WaterUserEndpointInput.Delete input)
            throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo, ENDPOINT_PREFIX + input.getOfficeId() + "/"
                + input.getProjectId() + ENDPOINT_WATER_USER+ "/" + input.waterUserId())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .delete()
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }
}
