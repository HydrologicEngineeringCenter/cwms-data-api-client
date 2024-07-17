package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.http.client.MockHttpServer;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.WaterUser;
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
        WaterUserEndpointInput.GetAll input = WaterUserEndpointInput.getAll().officeId("SPK").waterUserId("user");
        List<WaterUser> values = new WaterUserController().retrieveWaterUsers(buildConnectionInfo(cookieJarSupplier), input);
        assertFalse(values.isEmpty());
        WaterUser value = values.get(1);
        assertEquals("Test User 2", value.getEntityName());
        assertEquals("SPK", value.getParentLocationRef().getOfficeId());
        assertEquals("TOPEKA", value.getParentLocationRef().getName());
    }

    @Test
    void testRetrieveOne() throws IOException {
        String collect = readJsonFile("radar/v1/json/water_user.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        WaterUserEndpointInput.GetOne input = WaterUserEndpointInput.getOne("SPK", "user");
        WaterUser value = new WaterUserController().retrieveWaterUser(buildConnectionInfo(cookieJarSupplier), input);
        assertEquals("Test User", value.getEntityName());
        assertEquals("SWT", value.getParentLocationRef().getOfficeId());
        assertEquals("SACRAMENTO", value.getParentLocationRef().getName());
    }

    @Test
    void testStoreBasin() throws IOException {
        String collect = readJsonFile("radar/v1/json/water_user.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        WaterUser waterUser = RadarObjectMapper.mapJsonToObject(collect, WaterUser.class);
        WaterUserController controller = new WaterUserController();
        WaterUserEndpointInput.Post input = WaterUserEndpointInput.post(waterUser);
        assertDoesNotThrow(() -> controller.storeWaterUser(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testDeleteBasin() throws IOException {
        String collect = readJsonFile("radar/v1/json/water_user.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        WaterUser user = RadarObjectMapper.mapJsonToObject(collect, WaterUser.class);
        WaterUserController controller = new WaterUserController();
        controller.storeWaterUser(buildConnectionInfo(cookieJarSupplier), WaterUserEndpointInput.post(user));
        WaterUserEndpointInput.Delete input = WaterUserEndpointInput.delete(user.getParentLocationRef().getOfficeId(),
                user.getEntityName(), DeleteMethod.ALL);
        assertDoesNotThrow(() -> controller.deleteWaterUser(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testRenameUser() throws Exception {
        String collect = readJsonFile("radar/v1/json/water_user.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        WaterUser user = RadarObjectMapper.mapJsonToObject(collect, WaterUser.class);
        WaterUserController controller = new WaterUserController();
        WaterUserEndpointInput.Patch input = WaterUserEndpointInput.patch(user.getParentLocationRef().getOfficeId(),
                user.getEntityName(), "newName");
        assertDoesNotThrow(() -> controller.renameWaterUser(buildConnectionInfo(cookieJarSupplier), input));
        MockHttpServer.RequestWrapper request = mockHttpServer.takeRequest();
        assertEquals("PATCH", request.getMethod());
        assertTrue(request.getPath().startsWith("/projects/"));
    }
}
