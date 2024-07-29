package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.http.client.MockHttpServer;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.VirtualOutlet;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestVirtualOutletController extends TestController {

    @Test
    void testRetrieveAll() throws IOException {
        String collect = readJsonFile("radar/v1/json/virtual_outlets.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        VirtualOutletEndpointInput.GetAll input = VirtualOutletEndpointInput.getAll().officeId("SPK").projectId("TEST_LOCATION2");
        List<VirtualOutlet> values = new VirtualOutletController().retrieveVirtualOutlets(buildConnectionInfo(), input);
        assertFalse(values.isEmpty());
        VirtualOutlet value = values.get(0);
        assertEquals("Compound Tainter Gates", value.getVirtualOutletId().getName());
        assertEquals("SPK", value.getVirtualOutletId().getOfficeId());
    }

    @Test
    void testRetrieveOne() throws IOException {
        String collect = readJsonFile("radar/v1/json/virtual_outlet.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        VirtualOutletEndpointInput.GetOne input = VirtualOutletEndpointInput.getOne("PROJECT-VIRTUAL_OUTLET_LOC", "SPK");
        VirtualOutlet value = new VirtualOutletController().retrieveVirtualOutlet(buildConnectionInfo(), input);
        assertEquals("Compound Tainter Gates", value.getVirtualOutletId().getName());
        assertEquals("SPK", value.getVirtualOutletId().getOfficeId());
    }

    @Test
    void testStore() throws IOException {
        String collect = readJsonFile("radar/v1/json/virtual_outlet.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        VirtualOutlet outlet = RadarObjectMapper.mapJsonToObject(collect, VirtualOutlet.class);
        VirtualOutletController controller = new VirtualOutletController();
        VirtualOutletEndpointInput.Post input = VirtualOutletEndpointInput.post(outlet);
        assertDoesNotThrow(() -> controller.storeVirtualOutlet(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testRename() throws Exception {
        String collect = readJsonFile("radar/v1/json/virtual_outlet.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        VirtualOutlet outlet = RadarObjectMapper.mapJsonToObject(collect, VirtualOutlet.class);
        VirtualOutletController controller = new VirtualOutletController();
        VirtualOutletEndpointInput.Patch input = VirtualOutletEndpointInput
                .patch(outlet.getVirtualOutletId().getName(), "NEW_VIRTUAL_OUTLET_LOC",
                        outlet.getVirtualOutletId().getOfficeId());
        assertDoesNotThrow(() -> controller.renameVirtualOutlet(buildConnectionInfo(cookieJarSupplier), input));
        MockHttpServer.RequestWrapper request = mockHttpServer.takeRequest();
        assertEquals("PATCH", request.getMethod());
        assertTrue(request.getPath()
                .startsWith("/projects/"));
    }

    @Test
    void testDelete() throws IOException {
        String collect = readJsonFile("radar/v1/json/virtual_outlet.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        VirtualOutlet outlet = RadarObjectMapper.mapJsonToObject(collect, VirtualOutlet.class);
        VirtualOutletController controller = new VirtualOutletController();
        VirtualOutletEndpointInput.Delete input = VirtualOutletEndpointInput.delete(outlet.getVirtualOutletId().getName(),
                outlet.getVirtualOutletId().getOfficeId(), DeleteMethod.ALL);
        assertDoesNotThrow(() -> controller.deleteVirtualOutlet(buildConnectionInfo(cookieJarSupplier), input));
    }
}
