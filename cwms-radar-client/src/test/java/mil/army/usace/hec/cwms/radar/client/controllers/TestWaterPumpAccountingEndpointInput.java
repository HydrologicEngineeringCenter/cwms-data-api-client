package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.WaterSupplyAccounting;
import mil.army.usace.hec.cwms.radar.client.controllers.WaterPumpAccountingEndpointInput.Post;
import mil.army.usace.hec.cwms.radar.client.controllers.WaterPumpAccountingEndpointInput.GetAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestWaterPumpAccountingEndpointInput {

    @Test
    void testGetAllQueryRequest()  {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String office = "SPK";
        String projectId = "PROJECT";
        String waterUserEntityName = "Test User";
        String waterContractName = "TEST_CONTRACT";
        GetAll input = WaterPumpAccountingEndpointInput.getAll().officeId(office).projectId(projectId)
                .waterUserId(waterUserEntityName).waterContractName(waterContractName);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(GetAll.OFFICE_QUERY_PARAMETER));
        assertEquals(projectId, mockHttpRequestBuilder.getQueryParameter(GetAll.PROJECT_ID_QUERY_PARAMETER));
        assertEquals(waterUserEntityName, mockHttpRequestBuilder.getQueryParameter(GetAll.WATER_USER_QUERY_PARAMETER));
        assertEquals(waterContractName, mockHttpRequestBuilder.getQueryParameter(GetAll.WATER_CONTRACT_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v1/json/water_supply_accounting.json");
        WaterSupplyAccounting waterSupplyAccounting = RadarObjectMapper.mapJsonToObject(collect, WaterSupplyAccounting.class);
        Post input = WaterPumpAccountingEndpointInput.post(waterSupplyAccounting);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(waterSupplyAccounting, input.waterSupplyAccounting());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }
}
