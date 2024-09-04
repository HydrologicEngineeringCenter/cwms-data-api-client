package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.radar.client.model.WaterSupplyPump.PumpTypeEnum;
import org.junit.jupiter.api.Test;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static org.junit.jupiter.api.Assertions.*;

class TestWaterPumpEndpointInput {
    @Test
    void testDeleteQuery() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        PumpTypeEnum pumpType = PumpTypeEnum.IN;
        String office = "SPK";
        String projectId = "PROJECT";
        String contractName = "CONTRACT";
        String waterUser = "user";
        String pumpId = "pumpId";
        WaterPumpEndpointInput.Delete input = WaterPumpEndpointInput.delete(office, projectId, waterUser, contractName,
                pumpId, pumpType, false);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(pumpId, input.pumpId());
        assertEquals(office, input.officeId());
        assertEquals(projectId, input.projectId());
        assertEquals(contractName, input.contractName());
        assertEquals(waterUser, input.waterUser());
        assertEquals(String.valueOf(false), mockHttpRequestBuilder
                .getQueryParameter(WaterPumpEndpointInput.Delete.METHOD_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNulls() {
        assertAll(
            () -> assertThrows(NullPointerException.class,
                () -> WaterPumpEndpointInput.delete(null, "project", "user", "contract", "pumpId", PumpTypeEnum.IN,false)),
            () ->assertThrows(NullPointerException.class,
                () -> WaterPumpEndpointInput.delete("HQ", null, "user", "contract", "pumpId", PumpTypeEnum.IN,true)),
            () -> assertThrows(NullPointerException.class,
                () -> WaterPumpEndpointInput.delete("HQ", "project", null, "contract", "pumpId", PumpTypeEnum.IN, true)),
            () -> assertThrows(NullPointerException.class,
                () -> WaterPumpEndpointInput.delete("HQ", "project", "user", null, "pumpId", PumpTypeEnum.IN,true)),
            () -> assertThrows(NullPointerException.class,
                () -> WaterPumpEndpointInput.delete("HQ", "project", "user", "contract", null, PumpTypeEnum.IN,true)),
            () -> assertThrows(NullPointerException.class,
                () -> WaterPumpEndpointInput.delete("HQ", "project", "user", "contract", "pumpId", null,true))
        );
    }
}
