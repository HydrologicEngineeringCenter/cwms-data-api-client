package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.WaterSupplyAccounting;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestWaterPumpAccountingController extends TestController {

    @Test
    void testRetrieveAll() throws IOException {
        String collect = readJsonFile("radar/v1/json/water_supply_accounting_list.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        WaterPumpAccountingEndpointInput.GetAll input = WaterPumpAccountingEndpointInput.getAll()
                .officeId("SWT").projectId("SACRAMENTO").waterContractName("TEST_CONTRACT").waterUserId("Test User");
        List<WaterSupplyAccounting> values = new WaterPumpAccountingController()
                .retrieveWaterPumpAccounting(buildConnectionInfo(cookieJarSupplier), input);
        assertFalse(values.isEmpty());
        WaterSupplyAccounting value = values.get(0);
        assertEquals("TEST_CONTRACT", value.getContractName());
        assertEquals("SWT", value.getWaterUser().getParentLocationRef().getOfficeId());
        assertEquals("SACRAMENTO", value.getWaterUser().getParentLocationRef().getName());
        assertEquals("Test Water Right", value.getWaterUser().getWaterRight());
        assertEquals("PUMP3", value.getPumpAccounting().get(0).getPumpLocation().getName());
        assertEquals("PUMP1", value.getPumpAccounting().get(1).getPumpLocation().getName());
        assertEquals("Test Transfer Type", value.getPumpAccounting().get(0).getTransferType().getDisplayValue());
        assertEquals(1.0, value.getPumpAccounting().get(0).getFlow());
        assertEquals(2.0, value.getPumpAccounting().get(1).getFlow());
        assertEquals("Test Comment 2", value.getPumpAccounting().get(1).getComment());
//        assertEquals("", value.getPumpAccounting().get(1).getTransferDate());
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
