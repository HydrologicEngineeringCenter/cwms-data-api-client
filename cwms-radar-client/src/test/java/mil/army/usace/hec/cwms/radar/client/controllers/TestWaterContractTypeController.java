package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.radar.client.controllers.WaterContractTypeEndpointInput.GetAll;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import mil.army.usace.hec.cwms.radar.client.model.LookupType;

import static org.junit.jupiter.api.Assertions.*;

class TestWaterContractTypeController extends TestController {
    @Test
    void testRetrieveAll() throws IOException {
        String collect = readJsonFile("radar/v1/json/water_contract_types.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        GetAll input = WaterContractTypeEndpointInput.getAll().officeId("SPK").projectId("PROJ");
        List<LookupType> values = new WaterContractTypeController().retrieveWaterContractTypes(buildConnectionInfo(cookieJarSupplier), input);
        assertFalse(values.isEmpty());
        LookupType value = values.get(0);
        assertEquals("Test Display Value", value.getDisplayValue());
        assertEquals("SWT", value.getOfficeId());
        assertEquals("Test Tooltip", value.getTooltip());
        assertEquals(true, value.isActive());
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
}
