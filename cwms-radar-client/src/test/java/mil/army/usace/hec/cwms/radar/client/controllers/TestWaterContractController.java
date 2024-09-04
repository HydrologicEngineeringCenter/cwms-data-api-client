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
        WaterContractEndpointInput.GetAll input = WaterContractEndpointInput.getAll("SPK", "TEST_LOCATION2", "Test User 2");
        List<WaterUserContract> values = new WaterContractController().retrieveWaterContracts(buildConnectionInfo(cookieJarSupplier), input);
        assertFalse(values.isEmpty());
        WaterUserContract value = values.get(1);
        assertAll(
            () -> assertEquals("USACE Water Contract", value.getContractId().getName()),
            () -> assertEquals("SPK", value.getContractId().getOfficeId()),
            () -> assertEquals("Yolo County Water Project", value.getWaterUser().getProjectId().getName()),
            () -> assertEquals("Rights to 25% of flow", value.getWaterUser().getWaterRight()),
            () -> assertEquals("USACE Water User", value.getWaterUser().getEntityName()),
            () -> assertEquals(true, value.getContractType().isActive()),
            () -> assertEquals("PUMP1", value.getPumpOutLocation().getPumpLocation().getName())
        );
    }

    @Test
    void testRetrieveOne() throws IOException {
        String collect = readJsonFile("radar/v1/json/water_contract.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        WaterContractEndpointInput.GetOne input = WaterContractEndpointInput.getOne("SPK",
                "TEST_CONTRACT", "PROJECT-CONTRACT_LOC", "Test User");
        WaterUserContract value = new WaterContractController()
                .retrieveWaterContract(buildConnectionInfo(cookieJarSupplier), input);
        assertAll(
            () -> assertEquals("SAC_HEC_Contract", value.getContractId().getName()),
            () -> assertEquals("SWT", value.getContractId().getOfficeId()),
            () -> assertEquals("Sacramento River", value.getWaterUser().getProjectId().getName()),
            () -> assertEquals("Guaranteed water rights to 50% of flow", value.getWaterUser().getWaterRight()),
            () -> assertEquals("HEC Water User", value.getWaterUser().getEntityName()),
            () -> assertEquals(true, value.getContractType().isActive()),
            () -> assertEquals("PUMP3", value.getPumpInLocation().getPumpLocation().getName())
        );
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
        WaterContractEndpointInput.Delete input = WaterContractEndpointInput.delete(contract.getOfficeId(), contract.getWaterUser().getProjectId().getName(),
                contract.getWaterUser().getEntityName(), contract.getContractId().getName()).deleteMethod(DeleteMethod.ALL);
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
                contract.getWaterUser().getProjectId().getName(), contract.getWaterUser().getEntityName(),
                contract.getContractId().getName(), "NEW_CONTRACT_NAME");
        assertDoesNotThrow(() -> controller.renameWaterContract(buildConnectionInfo(cookieJarSupplier), input));
        MockHttpServer.RequestWrapper request = mockHttpServer.takeRequest();
        assertEquals("PATCH", request.getMethod());
        assertTrue(request.getPath().startsWith("/projects/"));
    }
}
