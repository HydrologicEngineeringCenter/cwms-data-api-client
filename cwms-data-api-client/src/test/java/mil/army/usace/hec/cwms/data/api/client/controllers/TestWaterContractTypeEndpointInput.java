package mil.army.usace.hec.cwms.data.api.client.controllers;

import mil.army.usace.hec.cwms.data.api.client.controllers.WaterContractTypeEndpointInput.GetAll;
import mil.army.usace.hec.cwms.data.api.client.model.LookupType;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.*;

class TestWaterContractTypeEndpointInput {
    @Test
    void testGetAllQuery() {
        String officeId = "SPK";
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        GetAll input = WaterContractTypeEndpointInput.getAll(officeId);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(officeId, input.officeId());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostQuery() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v1/json/water_contract_type.json");
        LookupType waterContractType = RadarObjectMapper.mapJsonToObject(collect, LookupType.class);
        WaterContractTypeEndpointInput.Post input = WaterContractTypeEndpointInput.post(waterContractType);
        input.failIfExists(false);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(waterContractType, input.waterContractType());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testFailIfExistsDefault() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v1/json/water_contract_type.json");
        LookupType waterContractType = RadarObjectMapper.mapJsonToObject(collect, LookupType.class);
        WaterContractTypeEndpointInput.Post input = WaterContractTypeEndpointInput.post(waterContractType);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(waterContractType, input.waterContractType());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteQuery() {
        String officeId = "SPK";
        String displayValue = "Storage";
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        WaterContractTypeEndpointInput.Delete input = WaterContractTypeEndpointInput.delete(officeId, displayValue);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(officeId, input.officeId());
        assertEquals(displayValue, input.displayValue());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testStoreNulls() {
        assertThrows(NullPointerException.class, () -> WaterContractTypeEndpointInput.post(null));
    }

    @Test
    void testGetAllNulls() {
        assertThrows(NullPointerException.class, () -> WaterContractTypeEndpointInput.getAll(null));
    }
}
