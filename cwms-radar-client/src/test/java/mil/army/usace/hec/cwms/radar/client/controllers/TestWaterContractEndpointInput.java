package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.radar.client.controllers.WaterContractEndpointInput.GetAll;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.WaterUserContract;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestWaterContractEndpointInput {

    @Test
    void testGetAllQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String office = "SPK";
        String projectId = "PROJECT";
        String waterUser = "user";
        GetAll input = WaterContractEndpointInput.getAll(office, projectId, waterUser);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(office, input.getOfficeId());
        assertEquals(projectId, input.getProjectId());
        assertEquals(waterUser, input.getWaterUser());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String contractId = "CONTRACT";
        String office = "SPK";
        String projectId = "PROJECT";
        String waterUser = "user";
        WaterContractEndpointInput.GetOne input = WaterContractEndpointInput.getOne(office, contractId, projectId, waterUser);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(contractId, input.waterContractId());
        assertEquals(office, input.getOfficeId());
        assertEquals(projectId, input.getProjectId());
        assertEquals(waterUser, input.getWaterUser());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v1/json/water_contract.json");
        WaterUserContract waterContract = RadarObjectMapper.mapJsonToObject(collect, WaterUserContract.class);
        WaterContractEndpointInput.Post input = WaterContractEndpointInput.post(waterContract);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(waterContract, input.getWaterContract());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String contractId = "CONTRACT";
        String office = "SPK";
        String projectId = "PROJECT";
        String waterUser = "user";
        DeleteMethod deleteMethod = DeleteMethod.ALL;
        WaterContractEndpointInput.Delete input = WaterContractEndpointInput.delete(office, projectId, waterUser,
                contractId, deleteMethod);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(contractId, input.getWaterContractId());
        assertEquals(office, input.getOfficeId());
        assertEquals(deleteMethod.toString(), mockHttpRequestBuilder.getQueryParameter(WaterContractEndpointInput
                .Delete.METHOD_QUERY_PARAMETER));
        assertEquals(projectId, input.getProjectId());
        assertEquals(waterUser, input.getWaterUserId());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNulls() {
        assertThrows(NullPointerException.class, () -> WaterContractEndpointInput
                .delete(null, null, null, null, DeleteMethod.ALL));
        assertThrows(NullPointerException.class, () -> WaterContractEndpointInput
                .delete(null, "", null, null, DeleteMethod.ALL));
        assertThrows(NullPointerException.class, () -> WaterContractEndpointInput
                .delete("", null, null, null, DeleteMethod.ALL));
        assertThrows(NullPointerException.class, () -> WaterContractEndpointInput
                .delete(null, null, "", null, DeleteMethod.ALL));
        assertThrows(NullPointerException.class, () -> WaterContractEndpointInput
                .delete(null, null, null, "", DeleteMethod.ALL));
    }

    @Test
    void testPatch() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String oldContractId = "CONTRACT";
        String newContractId = "NEW-CONTRACT";
        String office = "SPK";
        String projectId = "PROJECT";
        String waterUser = "user";
        WaterContractEndpointInput.Patch input = WaterContractEndpointInput.patch(office, projectId, waterUser,
                oldContractId, newContractId);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(oldContractId, input.getOldWaterContractId());
        assertEquals(newContractId, mockHttpRequestBuilder.getQueryParameter(WaterContractEndpointInput
                .Patch.NAME_QUERY_PARAMETER));
        assertEquals(office, input.getOfficeId());
        assertEquals(projectId, input.getProjectId());
        assertEquals(waterUser, input.getWaterUser());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }
}
