package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.radar.client.model.CwmsId;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesProfile;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesProfileInstance;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

final class TestTimeSeriesProfileInstanceController extends TestController {

    @Test
    void testGetOne() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_profile_instance.json" );
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesProfileInstanceEndpointInput.GetOne input = TimeSeriesProfileInstanceEndpointInput
                .getOne("SPK", "Test_TSP_Location", "Depth", "VERSION", "m,F");
        TimeSeriesProfileInstance value = new TimeSeriesProfileInstanceController()
                .retrieveTimeSeriesProfileInstance(buildConnectionInfo(), input);
        assertNotNull(value);
        assertEquals("VERSION", value.getVersion());
        assertEquals("Depth", value.getTimeSeriesProfile().getKeyParameter());
        assertEquals("Description", value.getTimeSeriesProfile().getDescription());
        assertEquals("SPK", value.getTimeSeriesProfile().getLocationId().getOfficeId());
    }

    @Test
    void testGetAll() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_profile_instances.json" );
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesProfileInstanceEndpointInput.GetAll input = TimeSeriesProfileInstanceEndpointInput
                .getAll();
        TimeSeriesProfileInstanceController controller = new TimeSeriesProfileInstanceController();
        List<TimeSeriesProfileInstance> results = controller.retrieveTimeSeriesProfileInstances(buildConnectionInfo(), input);
        assertNotNull(results);
        TimeSeriesProfileInstance value = results.get(0);
        assertEquals("VERSION", value.getVersion());
        assertEquals("Depth", value.getTimeSeriesProfile().getKeyParameter());
        assertEquals("Description", value.getTimeSeriesProfile().getDescription());
        assertEquals("SPK", value.getTimeSeriesProfile().getLocationId().getOfficeId());
        value = results.get(1);
        assertEquals("VERSION2", value.getVersion());
        assertEquals("Temp-Water", value.getTimeSeriesProfile().getKeyParameter());
        assertEquals("Description2", value.getTimeSeriesProfile().getDescription());
        assertEquals("SPK", value.getTimeSeriesProfile().getLocationId().getOfficeId());
    }

    @Test
    void testStore() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_profile_instance.json" );
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesProfile profile = new TimeSeriesProfile()
                .description("Description")
                .keyParameter("Depth")
                .locationId(new CwmsId().officeId("SPK").name("Test_TSP_Location"))
                .parameterList(List.of("Depth", "Flow"));
        TimeSeriesProfileInstanceEndpointInput.Post input = TimeSeriesProfileInstanceEndpointInput
                .post("2019/05/15,12:15:45,12.5,18.3", profile, "VERSION")
                .versionDate(Instant.parse("2019-05-15T12:15:45Z"));
        assertDoesNotThrow(() -> new TimeSeriesProfileInstanceController()
                .storeTimeSeriesProfileInstance(buildConnectionInfo(), input));
    }

    @Test
    void testDelete() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_profile_instance.json" );
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesProfileInstanceEndpointInput.Delete input = TimeSeriesProfileInstanceEndpointInput
                .delete("SPK", "Test_TSP_Location", "Depth", "VERSION");
        assertDoesNotThrow(() -> new TimeSeriesProfileInstanceController()
                .deleteTimeSeriesProfileInstance(buildConnectionInfo(), input));
    }
}
