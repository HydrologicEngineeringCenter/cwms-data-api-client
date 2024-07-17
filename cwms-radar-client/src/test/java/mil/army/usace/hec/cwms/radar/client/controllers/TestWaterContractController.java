package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.http.client.MockHttpServer;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.WaterUserContract;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestWaterContractController extends TestController {

    @Test
    void testRetrieveAll() throws IOException {
        String collect = readJsonFile("radar/v1/json/water_contracts.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        WaterContractEndpointInput.GetAll input = WaterContractEndpointInput.getAll().officeId("SPK").waterContractId("TEST_LOCATION2");
        List<WaterUserContract> values = new WaterContractController().retrieveWaterContracts(buildConnectionInfo(cookieJarSupplier), input);
        assertFalse(values.isEmpty());
        WaterUserContract value = values.get(1);
        assertEquals("TEST_CONTRACT 2", value.getContractId().getName());
        assertEquals("SPK", value.getContractId().getOfficeId());
        assertEquals("SACRAMENTO", value.getWaterUser().getParentLocationRef().getName());
        assertEquals("Test Water Right", value.getWaterUser().getWaterRight());
        assertEquals("Test User 2", value.getWaterUser().getEntityName());
        assertEquals(true, value.getWaterContract().isActive());
        assertEquals("PUMP1", value.getPumpOutLocation().getPumpLocation().getName());
    }

    @Test
    void testRetrieveOne() throws IOException {
        String collect = readJsonFile("radar/v1/json/water_contract.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        WaterContractEndpointInput.GetOne input = WaterContractEndpointInput.getOne("PROJECT-CONTRACT_LOC", "SPK");
        WaterUserContract value = new WaterContractController().retrieveWaterContract(buildConnectionInfo(cookieJarSupplier), input);
        assertEquals("TEST_CONTRACT", value.getContractId().getName());
        assertEquals("SWT", value.getContractId().getOfficeId());
        assertEquals("SACRAMENTO", value.getWaterUser().getParentLocationRef().getName());
        assertEquals("Test Water Right", value.getWaterUser().getWaterRight());
        assertEquals("Test User", value.getWaterUser().getEntityName());
        assertEquals(true, value.getWaterContract().isActive());
        assertEquals("PUMP3", value.getPumpInLocation().getPumpLocation().getName());
    }

    @Test
    void testStoreContract() throws IOException {
        String collect = readJsonFile("radar/v1/json/water_contract.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        WaterUserContract contract = RadarObjectMapper.mapJsonToObject(collect, WaterUserContract.class);
        WaterContractController controller = new WaterContractController();
        WaterContractEndpointInput.Post input = WaterContractEndpointInput.post(contract);
        assertDoesNotThrow(() -> controller.storeWaterContract(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testDeleteContract() throws IOException {
        String collect = readJsonFile("radar/v1/json/water_contract.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        WaterUserContract contract = RadarObjectMapper.mapJsonToObject(collect, WaterUserContract.class);
        WaterContractController controller = new WaterContractController();
        controller.storeWaterContract(buildConnectionInfo(cookieJarSupplier), WaterContractEndpointInput.post(contract));
        WaterContractEndpointInput.Delete input = WaterContractEndpointInput.delete(contract.getOfficeId(),
                contract.getContractId().getName(), DeleteMethod.ALL);
        assertDoesNotThrow(() -> controller.deleteWaterContract(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testRenameContract() throws Exception {
        String collect = readJsonFile("radar/v1/json/water_contract.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        WaterUserContract contract = RadarObjectMapper.mapJsonToObject(collect, WaterUserContract.class);
        WaterContractController controller = new WaterContractController();
        WaterContractEndpointInput.Patch input = WaterContractEndpointInput.patch(contract.getOfficeId(),
                contract.getContractId().getName(), "NEW_CONTRACT_NAME");
        assertDoesNotThrow(() -> controller.renameWaterContract(buildConnectionInfo(cookieJarSupplier), input));
        MockHttpServer.RequestWrapper request = mockHttpServer.takeRequest();
        assertEquals("PATCH", request.getMethod());
        assertTrue(request.getPath().startsWith("/projects/"));
    }
}
