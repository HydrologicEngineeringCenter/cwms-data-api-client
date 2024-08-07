package mil.army.usace.hec.cwms.radar.client.controllers;

import org.junit.jupiter.api.Test;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestWaterPumpEndpointInput {
    @Test
    void testDeleteQuery() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String pumpId = "PUMP";
        String office = "SPK";
        String projectId = "PROJECT";
        String contractName = "CONTRACT";
        String waterUser = "user";
        WaterPumpEndpointInput.Delete input = WaterPumpEndpointInput.delete(office, projectId, contractName, waterUser,
                pumpId, false);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(pumpId, input.pumpId());
        assertEquals(office, input.getOfficeId());
        assertEquals(projectId, input.getProjectId());
        assertEquals(contractName, input.getContractName());
        assertEquals(waterUser, input.getWaterUser());
        assertEquals(String.valueOf(false), mockHttpRequestBuilder
                .getQueryParameter(WaterPumpEndpointInput.Delete.METHOD_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNulls() {
        assertThrows(NullPointerException.class,
                () -> WaterPumpEndpointInput.delete(null, null, null, null, null, false));
        assertThrows(NullPointerException.class,
                () -> WaterPumpEndpointInput.delete("", null, null, null, null, false));
        assertThrows(NullPointerException.class,
                () -> WaterPumpEndpointInput.delete(null, "", null, null, null,true));
        assertThrows(NullPointerException.class,
                () -> WaterPumpEndpointInput.delete(null, null, "", null, null, true));
        assertThrows(NullPointerException.class,
                () -> WaterPumpEndpointInput.delete(null, null, null, "", null, true));
        assertThrows(NullPointerException.class,
                () -> WaterPumpEndpointInput.delete(null, null, null, null, "", true));
    }
}
