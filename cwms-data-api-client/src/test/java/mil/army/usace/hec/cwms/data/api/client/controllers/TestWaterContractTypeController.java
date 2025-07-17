package mil.army.usace.hec.cwms.data.api.client.controllers;

import mil.army.usace.hec.cwms.data.api.client.controllers.WaterContractTypeEndpointInput.GetAll;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import mil.army.usace.hec.cwms.data.api.client.model.LookupType;

import static org.junit.jupiter.api.Assertions.*;

class TestWaterContractTypeController extends TestController {
    @Test
    void testRetrieveAll() throws IOException {
        String collect = readJsonFile("radar/v1/json/water_contract_types.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        GetAll input = WaterContractTypeEndpointInput.getAll("SPK");
        List<LookupType> values = new WaterContractTypeController()
                .retrieveWaterContractTypes(buildConnectionInfo(cookieJarSupplier), input);
        assertFalse(values.isEmpty());
        LookupType value = values.get(0);
        assertAll(
                () -> assertEquals("Storage", value.getDisplayValue()),
                () -> assertEquals("SWT", value.getOfficeId()),
                () -> assertEquals("Storage contract", value.getTooltip()),
                () -> assertEquals(true, value.isActive())
        );
        LookupType value2 = values.get(1);
        assertAll(
                () -> assertEquals("Conveyance", value2.getDisplayValue()),
                () -> assertEquals("SWT", value2.getOfficeId()),
                () -> assertEquals("Conveyance contract", value2.getTooltip()),
                () -> assertEquals(true, value2.isActive())
        );
    }

    @Test
    void testStoreContractType() throws IOException {
        String collect = readJsonFile("radar/v1/json/water_contract_type.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LookupType waterContractType = RadarObjectMapper.mapJsonToObject(collect, LookupType.class);
        WaterContractTypeController controller = new WaterContractTypeController();
        WaterContractTypeEndpointInput.Post input = WaterContractTypeEndpointInput.post(waterContractType);
        assertDoesNotThrow(() -> controller.storeWaterContractType(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testDeleteContractType() throws IOException {
        String collect = readJsonFile("radar/v1/json/water_contract_type.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        WaterContractTypeController controller = new WaterContractTypeController();
        WaterContractTypeEndpointInput.Delete input = WaterContractTypeEndpointInput.delete("SWT", "Storage");
        assertDoesNotThrow(() -> controller.deleteWaterContractType(buildConnectionInfo(cookieJarSupplier), input));
    }


    @Test
    void testStoreNulls() {
        assertThrows(NullPointerException.class, () -> WaterContractTypeEndpointInput.post(null));
    }

    @Test
    void testRetrieveNulls () {
        assertThrows(NullPointerException.class, () -> WaterContractTypeEndpointInput.getAll(null));
    }
}
