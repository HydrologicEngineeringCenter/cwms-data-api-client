package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.WaterSupplyAccounting;
import mil.army.usace.hec.cwms.radar.client.controllers.WaterPumpAccountingEndpointInput.Post;
import mil.army.usace.hec.cwms.radar.client.controllers.WaterPumpAccountingEndpointInput.GetAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.*;

class TestWaterPumpAccountingEndpointInput {

    @Test
    void testGetAllQueryRequest()  {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String office = "SPK";
        String projectId = "PROJECT";
        String waterUserEntityName = "Test User";
        String waterContractName = "TEST_CONTRACT";
        GetAll input = WaterPumpAccountingEndpointInput.getAll(office, projectId, waterUserEntityName,
                        waterContractName, Instant.MIN, Instant.MAX)
                .setAscending(false)
                .setRowLimit(100)
                .setTimeZone("PST")
                .setStartInclusive(false)
                .setEndInclusive(false);
        input.setStartInclusive(false);
        input.setEndInclusive(false);
        input.setAscending(false);
        input.setRowLimit(100);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(office, input.getOfficeId());
        assertEquals(projectId, input.getProjectId());
        assertEquals(waterUserEntityName, input.getWaterUserId());
        assertEquals(waterContractName, input.getWaterContractName());
        assertFalse(input.isAscending());
        assertEquals(Instant.MAX, input.getEndTime());
        assertEquals(Instant.MIN, input.getStartTime());
        assertEquals(100, input.getRowLimit());
        assertFalse(input.isAscending());
        assertFalse(input.isEndInclusive());
        assertFalse(input.isStartInclusive());
        assertEquals("PST", input.getTimeZone());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v1/json/water_supply_accounting.json");
        WaterSupplyAccounting waterSupplyAccounting = RadarObjectMapper
                .mapJsonToObject(collect, WaterSupplyAccounting.class);
        Post input = WaterPumpAccountingEndpointInput.post(waterSupplyAccounting);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(waterSupplyAccounting, input.getWaterSupplyAccounting());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }
}
