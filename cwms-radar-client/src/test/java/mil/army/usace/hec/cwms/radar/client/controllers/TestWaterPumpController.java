package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.WaterSupplyPump;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TestWaterPumpController extends TestController {
    @Test
    void testDeleteWaterPump() throws IOException {
        String collect = readJsonFile("radar/v1/json/water_pump.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        WaterSupplyPump pump = RadarObjectMapper.mapJsonToObject(collect, WaterSupplyPump.class);
        assertFalse(pump.getPumpLocation() == null && pump.getPumpType() == null);
        WaterPumpController controller = new WaterPumpController();
        controller.disassociateWaterPump(buildConnectionInfo(cookieJarSupplier), WaterPumpEndpointInput.delete(
                pump.getPumpLocation().getOfficeId(), "ProjectId", "ContractName",
                "WaterUser", pump.getPumpType(), false));
        WaterPumpEndpointInput.Delete input = WaterPumpEndpointInput.delete(pump.getPumpLocation().getOfficeId(),
                "ProjectId", "ContractName", "WaterUser", pump.getPumpType(), false);
        assertDoesNotThrow(() -> controller.disassociateWaterPump(buildConnectionInfo(cookieJarSupplier), input));
    }
}
