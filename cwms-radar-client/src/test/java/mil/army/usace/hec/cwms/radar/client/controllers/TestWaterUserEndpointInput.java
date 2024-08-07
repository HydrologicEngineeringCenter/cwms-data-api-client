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
        assertEquals(office, input.getOfficeId());
        assertEquals(projectId, input.getProjectId());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetOneQuery() {
        String office = "SPK";
        String userId = "user";
        String projectId = "SACRAMENTO";
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        WaterUserEndpointInput.GetOne input = WaterUserEndpointInput.getOne(office, userId, projectId);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(office, input.getOfficeId());
        assertEquals(userId, input.waterUserId());
        assertEquals(projectId, input.getProjectId());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostQuery() throws IOException {
        String collect = readJsonFile("radar/v1/json/water_user.json");
        WaterUser waterUser = RadarObjectMapper.mapJsonToObject(collect, WaterUser.class);
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        WaterUserEndpointInput.Post input = WaterUserEndpointInput.post(waterUser);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(waterUser, input.getWaterUser());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteQuery() {
        String office = "SPK";
        String userId = "user";
        String projectId = "SACRAMENTO";
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        WaterUserEndpointInput.Delete input = WaterUserEndpointInput.delete(office, userId, projectId, DeleteMethod.ALL);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(office, input.getOfficeId());
        assertEquals(userId, input.waterUserId());
        assertEquals(projectId, input.getProjectId());
        assertEquals(DeleteMethod.ALL.toString(), mockHttpRequestBuilder.getQueryParameter(WaterUserEndpointInput.Delete.METHOD_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNulls() {
        assertThrows(NullPointerException.class, () -> WaterUserEndpointInput.delete("", null, null, DeleteMethod.ALL));
        assertThrows(NullPointerException.class, () -> WaterUserEndpointInput.delete(null, "", null, DeleteMethod.ALL));
        assertThrows(NullPointerException.class, () -> WaterUserEndpointInput.delete(null, null, "", DeleteMethod.ALL));
        assertThrows(NullPointerException.class, () -> WaterUserEndpointInput.delete(null, null, null, DeleteMethod.ALL));
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
        assertEquals(office, input.getOfficeId());
        assertEquals(projectId, input.getProjectId());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }
}
