package mil.army.usace.hec.cwms.data.api.client.controllers;

import mil.army.usace.hec.cwms.http.client.MockHttpServer;
import mil.army.usace.hec.cwms.data.api.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.data.api.client.model.WaterUser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TestWaterUserController extends TestController {
    @Test
    void testRetrieveAll() throws IOException {
        String collect = readJsonFile("radar/v1/json/water_users.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        WaterUserEndpointInput.GetAll input = WaterUserEndpointInput.getAll("SPK", "Test User 2");
        List<WaterUser> values = WaterUserController.retrieveWaterUsers(buildConnectionInfo(cookieJarSupplier), input);
        assertFalse(values.isEmpty());
        WaterUser value = values.get(1);
        assertEquals("Wyneewood", value.getEntityName());
        assertEquals("SWT", value.getProjectId().getOfficeId());
        assertEquals("ARBU", value.getProjectId().getName());
    }

    @Test
    void testRetrieveOne() throws IOException {
        String collect = readJsonFile("radar/v1/json/water_user.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        WaterUserEndpointInput.GetOne input = WaterUserEndpointInput.getOne("SPK", "user", "SACRAMENTO");
        WaterUser value = WaterUserController.retrieveWaterUser(buildConnectionInfo(cookieJarSupplier), input);
        assertEquals("City of Ardmore", value.getEntityName());
        assertEquals("SWT", value.getProjectId().getOfficeId());
        assertEquals("ARBU", value.getProjectId().getName());
    }

    @Test
    void testStoreBasin() throws IOException {
        String collect = readJsonFile("radar/v1/json/water_user.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        WaterUser waterUser = RadarObjectMapper.mapJsonToObject(collect, WaterUser.class);
        WaterUserEndpointInput.Post input = WaterUserEndpointInput.post(waterUser);
        assertDoesNotThrow(() -> WaterUserController.storeWaterUser(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testDeleteBasin() throws IOException {
        String collect = readJsonFile("radar/v1/json/water_user.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        WaterUser user = RadarObjectMapper.mapJsonToObject(collect, WaterUser.class);
        WaterUserController.storeWaterUser(buildConnectionInfo(cookieJarSupplier), WaterUserEndpointInput.post(user));
        WaterUserEndpointInput.Delete input = WaterUserEndpointInput.delete(user.getProjectId().getOfficeId(),
                user.getEntityName(), user.getProjectId().getName()).deleteMethod(DeleteMethod.ALL);
        assertDoesNotThrow(() -> WaterUserController.deleteWaterUser(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testRenameUser() throws Exception {
        String collect = readJsonFile("radar/v1/json/water_user.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        WaterUser user = RadarObjectMapper.mapJsonToObject(collect, WaterUser.class);
        WaterUserEndpointInput.Patch input = WaterUserEndpointInput.patch(user.getEntityName(),
                "newName", user.getProjectId().getName(), user.getProjectId().getOfficeId());
        assertDoesNotThrow(() -> WaterUserController.renameWaterUser(buildConnectionInfo(cookieJarSupplier), input));
        MockHttpServer.RequestWrapper request = mockHttpServer.takeRequest();
        assertEquals("PATCH", request.getMethod());
        assertTrue(request.getPath().startsWith("/projects/"));
    }
}
