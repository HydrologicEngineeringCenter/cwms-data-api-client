package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.http.client.MockHttpServer;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.Outlet;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestOutletController extends TestController {

    @Test
    void testRetrieveAll() throws Exception {
        String collect = readJsonFile("radar/v1/json/outlets.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        OutletEndpointInput.GetAll input = OutletEndpointInput.getAll("TEST_LOCATION2").officeId("SPK");
        List<Outlet> values = new OutletController().retrieveOutlets(buildConnectionInfo(), input);
        assertFalse(values.isEmpty());
        Outlet value = values.get(0);
        assertEquals("BIGH", value.getProjectId().getName());
        assertEquals("SPK", value.getProjectId().getOfficeId());
        assertEquals("Rating-BIGH-TG1", value.getRatingGroupId().getName());
        value = values.get(1);
        assertEquals("BIGH", value.getProjectId().getName());
        assertEquals("SPK", value.getProjectId().getOfficeId());
        assertEquals("Rating-BIGH-TG2", value.getRatingGroupId().getName());
    }

    @Test
    void testRetrieveOne() throws Exception {
        String collect = readJsonFile("radar/v1/json/outlet.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        OutletEndpointInput.GetOne input = OutletEndpointInput.getOne("SPK", "PROJECT-OUTLET_LOC");
        Outlet value = new OutletController().retrieveOutlet(buildConnectionInfo(), input);
        assertEquals("BIGH", value.getProjectId().getName());
        assertEquals("SPK", value.getProjectId().getOfficeId());
        assertEquals(0.0, value.getLocation().getLatitude());
    }

    @Test
    void testStore() throws Exception {
        String collect = readJsonFile("radar/v1/json/outlet.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Outlet outlet = RadarObjectMapper.mapJsonToObject(collect, Outlet.class);
        OutletController controller = new OutletController();
        OutletEndpointInput.Post input = OutletEndpointInput.post(outlet);
        assertDoesNotThrow(() -> controller.storeOutlet(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testDelete() throws Exception {
        String collect = readJsonFile("radar/v1/json/outlet.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Outlet outlet = RadarObjectMapper.mapJsonToObject(collect, Outlet.class);
        OutletController controller = new OutletController();
        controller.storeOutlet(buildConnectionInfo(cookieJarSupplier), OutletEndpointInput.post(outlet));
        OutletEndpointInput.Delete input = OutletEndpointInput.delete("SPK", "PROJECT-OUTLET_LOC")
                .deleteMethod(DeleteMethod.ALL);
        assertDoesNotThrow(() -> controller.deleteOutlet(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testRename() throws Exception {
        String collect = readJsonFile("radar/v1/json/outlet.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Outlet outlet = RadarObjectMapper.mapJsonToObject(collect, Outlet.class);
        OutletController controller = new OutletController();
        OutletEndpointInput.Patch input = OutletEndpointInput.patch("SPK", "BIGH", "NEW_OUTLET_LOC");
        assertDoesNotThrow(() -> controller.renameOutlet(buildConnectionInfo(cookieJarSupplier), input));
        MockHttpServer.RequestWrapper request = mockHttpServer.takeRequest();
        assertEquals("PATCH", request.getMethod());
        assertTrue(request.getPath().startsWith("/projects/outlets/" + outlet.getProjectId().getName() + "?"));
    }
}
