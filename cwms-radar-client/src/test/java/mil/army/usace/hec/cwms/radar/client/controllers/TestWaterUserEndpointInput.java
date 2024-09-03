package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.WaterUser;
import mil.army.usace.hec.cwms.radar.client.controllers.WaterUserEndpointInput.GetAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.*;

class TestWaterUserEndpointInput {
    @Test
    void testGetAllQuery() {
        String office = "SPK";
        String projectId = "SACRAMENTO";
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        GetAll input = WaterUserEndpointInput.getAll(office, projectId);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(office, input.officeId());
        assertEquals(projectId, input.projectId());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetOneQuery() {
        String office = "SPK";
        String userId = "user";
        String projectId = "SACRAMENTO";
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        WaterUserEndpointInput.GetOne input = WaterUserEndpointInput.getOne(office, projectId, userId);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(office, input.officeId());
        assertEquals(userId, input.waterUserId());
        assertEquals(projectId, input.projectId());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostQuery() throws IOException {
        String collect = readJsonFile("radar/v1/json/water_user.json");
        WaterUser waterUser = RadarObjectMapper.mapJsonToObject(collect, WaterUser.class);
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        WaterUserEndpointInput.Post input = WaterUserEndpointInput.post(waterUser);
        input.failIfExists(false); // Default is true
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(waterUser, input.waterUser());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteQuery() {
        String office = "SPK";
        String userId = "user";
        String projectId = "SACRAMENTO";
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        WaterUserEndpointInput.Delete input = WaterUserEndpointInput.delete(office, projectId, userId).deleteMethod(DeleteMethod.ALL);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(office, input.officeId());
        assertEquals(userId, input.waterUserId());
        assertEquals(projectId, input.projectId());
        assertEquals(DeleteMethod.ALL.toString(), mockHttpRequestBuilder.getQueryParameter(WaterUserEndpointInput.Delete.METHOD_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNulls() {
        assertAll(
            () -> assertThrows(NullPointerException.class, () -> WaterUserEndpointInput.delete(null, null, null)),
            () -> assertThrows(NullPointerException.class, () -> WaterUserEndpointInput.delete(null, "project", "user")),
            () -> assertThrows(NullPointerException.class, () -> WaterUserEndpointInput.delete("HQ", null, "user")),
            () -> assertThrows(NullPointerException.class, () -> WaterUserEndpointInput.delete("HQ", "project", null)),
            () -> assertThrows(NullPointerException.class, () -> WaterUserEndpointInput.delete("HQ", "project", "user").deleteMethod(null))
        );
    }

    @Test
    void testPatchNulls() {
        assertAll(
            () -> assertThrows(NullPointerException.class, () -> WaterUserEndpointInput.patch(null, null, null, null)),
            () -> assertThrows(NullPointerException.class, () -> WaterUserEndpointInput.patch(null, "project", "oldUser", "newUser")),
            () -> assertThrows(NullPointerException.class, () -> WaterUserEndpointInput.patch("HQ", null, "oldUser", "newUser")),
            () -> assertThrows(NullPointerException.class, () -> WaterUserEndpointInput.patch("HQ", "project", null, "newUser")),
            () -> assertThrows(NullPointerException.class, () -> WaterUserEndpointInput.patch("HQ", "project", "oldUser", null))
        );
    }

    @Test
    void testStoreNulls() {
        assertThrows(NullPointerException.class, () -> WaterUserEndpointInput.post(null));
    }

    @Test
    void testGetOneNulls() {
        assertAll(
                () -> assertThrows(NullPointerException.class, () -> WaterUserEndpointInput.getOne(null, null, null)),
                () -> assertThrows(NullPointerException.class, () -> WaterUserEndpointInput.getOne(null, "user", "project")),
                () -> assertThrows(NullPointerException.class, () -> WaterUserEndpointInput.getOne("HQ", null, "project")),
                () -> assertThrows(NullPointerException.class, () -> WaterUserEndpointInput.getOne("HQ", "user", null))
        );
    }

    @Test
    void testGetAllNulls() {
        assertAll(
                () -> assertThrows(NullPointerException.class, () -> WaterUserEndpointInput.getAll(null, null)),
                () -> assertThrows(NullPointerException.class, () -> WaterUserEndpointInput.getAll(null, "project")),
                () -> assertThrows(NullPointerException.class, () -> WaterUserEndpointInput.getAll("HQ", null))
        );
    }

    @Test
    void testPatch() {
        String oldUserId = "user";
        String newUserId = "newUser";
        String office = "SPK";
        String projectId = "SACRAMENTO";
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        WaterUserEndpointInput.Patch input = WaterUserEndpointInput.patch(office, projectId, oldUserId, newUserId);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(oldUserId, input.oldWaterUserId());
        assertEquals(newUserId, mockHttpRequestBuilder.getQueryParameter(WaterUserEndpointInput.Patch.NAME_QUERY_PARAMETER));
        assertEquals(office, input.officeId());
        assertEquals(projectId, input.projectId());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }
}
