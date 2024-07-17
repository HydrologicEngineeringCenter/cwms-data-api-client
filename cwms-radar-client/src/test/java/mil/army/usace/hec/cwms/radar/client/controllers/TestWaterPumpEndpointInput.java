package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
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
        WaterPumpEndpointInput.Delete input = WaterPumpEndpointInput.delete(pumpId, office, DeleteMethod.ALL);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(pumpId, input.pumpId());
        assertEquals(DeleteMethod.ALL.toString(), mockHttpRequestBuilder.getQueryParameter(WaterPumpEndpointInput.Delete.METHOD_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNulls() {
        assertThrows(NullPointerException.class, () -> WaterPumpEndpointInput.delete(null, null, null));
        assertThrows(NullPointerException.class, () -> WaterPumpEndpointInput.delete(null, null, DeleteMethod.ALL));
        assertThrows(NullPointerException.class, () -> WaterPumpEndpointInput.delete(null, "", DeleteMethod.ALL));
        assertThrows(NullPointerException.class, () -> WaterPumpEndpointInput.delete("", null, DeleteMethod.ALL));
    }
}
