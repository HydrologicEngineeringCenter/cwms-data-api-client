package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.radar.client.model.WaterSupplyPump;
import org.junit.jupiter.api.Test;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static org.junit.jupiter.api.Assertions.*;

class TestWaterPumpEndpointInput {
    @Test
    void testDeleteQuery() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        WaterSupplyPump.PumpTypeEnum pumpId = WaterSupplyPump.PumpTypeEnum.IN;
        String office = "SPK";
        String projectId = "PROJECT";
        String contractName = "CONTRACT";
        String waterUser = "Test User";
        WaterPumpEndpointInput.Delete input = WaterPumpEndpointInput.delete(office, projectId, contractName, waterUser,
                pumpId, false);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(pumpId, input.pumpId());
        assertFalse(Boolean.parseBoolean(mockHttpRequestBuilder.getQueryParameter(WaterPumpEndpointInput.Delete.METHOD_QUERY_PARAMETER)));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNulls() {
        assertThrows(NullPointerException.class, () -> WaterPumpEndpointInput.delete(null, null, null, null, null, false));
        assertThrows(NullPointerException.class, () -> WaterPumpEndpointInput.delete(null, "", null, null, null, false));
        assertThrows(NullPointerException.class, () -> WaterPumpEndpointInput.delete(null, null, "", null, null, false));
        assertThrows(NullPointerException.class, () -> WaterPumpEndpointInput.delete(null, null, null, "", null, false));
    }
}
