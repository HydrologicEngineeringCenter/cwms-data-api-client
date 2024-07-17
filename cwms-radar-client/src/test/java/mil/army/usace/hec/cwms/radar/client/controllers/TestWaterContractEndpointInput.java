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
        String contractId = "CONTRACT";
        GetAll input = WaterContractEndpointInput.getAll().officeId(office).waterContractId(contractId);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(GetAll.OFFICE_QUERY_PARAMETER));
        assertEquals(contractId, mockHttpRequestBuilder.getQueryParameter(GetAll.PROJECT_ID_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String contractId = "CONTRACT";
        String office = "SPK";
        WaterContractEndpointInput.GetOne input = WaterContractEndpointInput.getOne(contractId, office);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(contractId, input.waterContractId());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(WaterContractEndpointInput
                .GetOne.OFFICE_QUERY_PARAMETER));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v1/json/water_contract.json");
        WaterUserContract waterContract = RadarObjectMapper.mapJsonToObject(collect, WaterUserContract.class);
        WaterContractEndpointInput.Post input = WaterContractEndpointInput.post(waterContract);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(waterContract, input.waterContract());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String contractId = "CONTRACT";
        String office = "SPK";
        DeleteMethod deleteMethod = DeleteMethod.ALL;
        WaterContractEndpointInput.Delete input = WaterContractEndpointInput.delete(office, contractId, deleteMethod);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(contractId, input.waterContractId());
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(WaterContractEndpointInput
                .Delete.OFFICE_QUERY_PARAMETER));
        assertEquals(deleteMethod.toString(), mockHttpRequestBuilder.getQueryParameter(WaterContractEndpointInput
                .Delete.METHOD_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNulls() {
        assertThrows(NullPointerException.class, () -> WaterContractEndpointInput
                .delete(null, null, DeleteMethod.ALL));
        assertThrows(NullPointerException.class, () -> WaterContractEndpointInput
                .delete(null, "", DeleteMethod.ALL));
        assertThrows(NullPointerException.class, () -> WaterContractEndpointInput
                .delete("", null, DeleteMethod.ALL));
    }

    @Test
    void testPatch() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String oldContractId = "CONTRACT";
        String newContractId = "NEW-CONTRACT";
        String office = "SPK";
        WaterContractEndpointInput.Patch input = WaterContractEndpointInput.patch(office, oldContractId, newContractId);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(oldContractId, input.oldWaterContractId());
        assertEquals(newContractId, mockHttpRequestBuilder.getQueryParameter(WaterContractEndpointInput
                .Patch.NAME_QUERY_PARAMETER));
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(WaterContractEndpointInput
                .Patch.OFFICE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }
}
