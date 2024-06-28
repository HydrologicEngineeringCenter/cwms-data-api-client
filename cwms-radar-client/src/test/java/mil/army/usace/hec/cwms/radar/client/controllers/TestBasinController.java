package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.http.client.MockHttpServer;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.Basin;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestBasinController extends TestController {

    @Test
    void testRetrieveAll() throws IOException {
        String collect = readJsonFile("radar/v1/json/basins.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        BasinEndpointInput.GetAll input = BasinEndpointInput.getAll("PROJECT", "SWT");
        List<Basin> values = new BasinController().retrieveBasins(buildConnectionInfo(), input);
        assertFalse(values.isEmpty());
        Basin value = values.get(0);
        assertEquals("TEST_LOCATION2", value.getBasinId().getName());
        assertEquals("MVR", value.getBasinId().getOfficeId());
        assertEquals(1.0, value.getSortOrder());
        assertEquals(1005.0, value.getTotalDrainageArea());
        assertEquals(850.0, value.getContributingDrainageArea());
        assertEquals("m2", value.getAreaUnit());
        if (value.getParentBasinId() != null) {
            assertEquals("TEST_LOCATION5", value.getParentBasinId().getName());
            assertEquals("NAE", value.getParentBasinId().getOfficeId());
        }
        if (value.getPrimaryStreamId() != null) {
            assertEquals("TEST_LOCATION4", value.getPrimaryStreamId().getName());
            assertEquals("MVP", value.getPrimaryStreamId().getOfficeId());
        }
    }

    @Test
    void testRetrieveOne() throws IOException {
        String collect = readJsonFile("radar/v1/json/basin.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        BasinEndpointInput.GetOne input = BasinEndpointInput.getOne("PROJECT-BASIN_LOC", "SPK");
        Basin value = new BasinController().retrieveBasin(buildConnectionInfo(), input);
        assertEquals("TEST_LOCATION2", value.getBasinId().getName());
        assertEquals("MVR", value.getBasinId().getOfficeId());
        assertEquals(1.0, value.getSortOrder());
        assertEquals(1005.0, value.getTotalDrainageArea());
        assertEquals(850.0, value.getContributingDrainageArea());
        assertEquals("m2", value.getAreaUnit());
        if (value.getParentBasinId() != null) {
            assertEquals("TEST_LOCATION5", value.getParentBasinId().getName());
            assertEquals("NAE", value.getParentBasinId().getOfficeId());
        }
        if (value.getPrimaryStreamId() != null) {
            assertEquals("TEST_LOCATION4", value.getPrimaryStreamId().getName());
            assertEquals("MVP", value.getPrimaryStreamId().getOfficeId());
        }
    }

    @Test
    void testStoreBasin() throws IOException {
        String collect = readJsonFile("radar/v1/json/basin.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Basin basin = RadarObjectMapper.mapJsonToObject(collect, Basin.class);
        BasinController controller = new BasinController();
        BasinEndpointInput.Post input = BasinEndpointInput.post(basin);
        assertDoesNotThrow(() -> controller.storeBasin(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testDeleteBasin() throws IOException {
        String collect = readJsonFile("radar/v1/json/basin.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Basin basin = RadarObjectMapper.mapJsonToObject(collect, Basin.class);
        BasinController controller = new BasinController();
        controller.storeBasin(buildConnectionInfo(cookieJarSupplier), BasinEndpointInput.post(basin));
        BasinEndpointInput.Delete input = BasinEndpointInput.delete(basin.getBasinId().getName(),
                basin.getBasinId().getOfficeId(), DeleteMethod.ALL);
        assertDoesNotThrow(() -> controller.deleteBasin(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testRenameBasin() throws Exception {
        String collect = readJsonFile("radar/v1/json/basin.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Basin value = RadarObjectMapper.mapJsonToObject(collect, Basin.class);
        BasinController controller = new BasinController();
        BasinEndpointInput.Patch input = BasinEndpointInput.patch(value.getBasinId().getName(), "NEW-BASIN",
                value.getBasinId().getOfficeId());
        assertDoesNotThrow(() -> controller.renameBasin(buildConnectionInfo(cookieJarSupplier), input));
        MockHttpServer.RequestWrapper requestWrapper = mockHttpServer.takeRequest();
        assertEquals("PATCH", requestWrapper.getMethod());
        assertTrue(requestWrapper.getPath().startsWith("/basins/" + value.getBasinId().getName() + "?"));
    }
}
