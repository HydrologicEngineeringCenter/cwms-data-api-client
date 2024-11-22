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
                .getAll("SWT", "SACRAMENTO", "Test User", "TEST_CONTRACT", startTime, endTime);
        List<WaterSupplyAccounting> values = new WaterPumpAccountingController()
                .retrieveWaterPumpAccounting(buildConnectionInfo(cookieJarSupplier), input);
        assertFalse(values.isEmpty());
        WaterSupplyAccounting value = values.get(0);
        assertEquals("TEST_CONTRACT", value.getContractName());
        assertEquals("SWT", value.getWaterUser().getProjectId().getOfficeId());
        assertEquals("SACRAMENTO", value.getWaterUser().getProjectId().getName());
        assertEquals("Test Water Right", value.getWaterUser().getWaterRight());
        assertEquals("PUMP3", value.getPumpAccounting().get(0).getPumpLocation().getName());
        assertEquals("PUMP1", value.getPumpAccounting().get(1).getPumpLocation().getName());
        assertEquals("Test Transfer Type", value.getPumpAccounting().get(0).getTransferType().getDisplayValue());
        assertEquals(1.0, value.getPumpAccounting().get(0).getFlow());
        assertEquals(2.0, value.getPumpAccounting().get(1).getFlow());
        assertEquals("Test Comment 2", value.getPumpAccounting().get(1).getComment());
        assertEquals(Instant.ofEpochMilli(10000012648000L), value.getPumpAccounting().get(0).getTransferDate());
        assertEquals(Instant.ofEpochMilli(10000016484000L), value.getPumpAccounting().get(1).getTransferDate());
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
