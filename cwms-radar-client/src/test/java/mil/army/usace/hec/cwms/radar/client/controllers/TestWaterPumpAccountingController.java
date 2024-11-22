package mil.army.usace.hec.cwms.radar.client.controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.WaterSupplyAccounting;
import org.junit.jupiter.api.Test;


class TestWaterPumpAccountingController extends TestController {

    @Test
    void testRetrieveAll() throws IOException {
        String collect = readJsonFile("radar/v1/json/water_supply_accounting_list.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Instant startTime = Instant.ofEpochMilli(10000012648000L);
        Instant endTime = Instant.ofEpochSecond(10000016484000L);
        WaterPumpAccountingEndpointInput.GetAll input = WaterPumpAccountingEndpointInput
                .getAll("SPK", "SACRAMENTO", "California Department of Water Resources", "Sac. River Contract", startTime, endTime);
        List<WaterSupplyAccounting> values = new WaterPumpAccountingController()
                .retrieveWaterPumpAccounting(buildConnectionInfo(cookieJarSupplier), input);
        assertFalse(values.isEmpty());
        WaterSupplyAccounting value = values.get(0);
        assertEquals("Sac. River Contract", value.getContractName());
        assertEquals("SPK", value.getWaterUser().getProjectId().getOfficeId());
        assertEquals("Sacramento River Delta", value.getWaterUser().getProjectId().getName());
        assertEquals("State of California Water Rights Permit #12345", value.getWaterUser().getWaterRight());
        assertEquals("Sacramento River Delta-Dam Water Pump 3", value.getPumpLocations().getPumpBelow().getName());
        assertEquals("Sacramento River Delta-Dam Water Pump 1", value.getPumpLocations().getPumpIn().getName());
        assertEquals("Pipeline", value.getPumpAccounting().get(Instant.parse("2022-11-20T21:17:28Z")).get(0).getTransferTypeDisplay());
        assertEquals(1.0, value.getPumpAccounting().get(Instant.parse("2022-11-20T21:17:28Z")).get(0).getFlow());
        assertEquals(2.0, value.getPumpAccounting().get(Instant.parse("2022-11-20T21:17:28Z")).get(1).getFlow());
        assertEquals("Added water to the system", value.getPumpAccounting().get(Instant.parse("2022-11-20T21:17:28Z")).get(0).getComment());
    }

    @Test
    void testStoreWaterPumpAccounting() throws IOException {
        String collect = readJsonFile("radar/v1/json/water_supply_accounting.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        WaterSupplyAccounting waterSupplyAccounting
                = RadarObjectMapper.mapJsonToObject(collect, WaterSupplyAccounting.class);
        WaterPumpAccountingController controller = new WaterPumpAccountingController();
        WaterPumpAccountingEndpointInput.Post input = WaterPumpAccountingEndpointInput.post(waterSupplyAccounting);
        assertDoesNotThrow(() -> controller.storeWaterPumpAccounting(buildConnectionInfo(cookieJarSupplier), input));
    }
}
