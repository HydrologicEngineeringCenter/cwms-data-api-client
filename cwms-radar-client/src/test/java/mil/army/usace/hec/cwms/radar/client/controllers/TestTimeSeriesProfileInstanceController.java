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
        List<String> parameterList = List.of("F", "m");
        Instant firstDate = Instant.ofEpochMilli(1594296000000L);
        Instant lastDate = Instant.ofEpochMilli(1752062400000L);
        TimeSeriesProfileInstanceEndpointInput.GetOne input = TimeSeriesProfileInstanceEndpointInput
                .getOne("SWT", "Test_TSP_Location", "Depth", "DSS-Obs",
                        parameterList, firstDate, lastDate)
                .page("CWMSTESTPAGE").pageSize(50);
        TimeSeriesProfileInstance value = new TimeSeriesProfileInstanceController()
                .retrieveTimeSeriesProfileInstance(buildConnectionInfo(), input);
        assertNotNull(value);
        assertEquals("DSS-Obs", value.getVersion());
        assertEquals("CWMSTESTPAGE", value.getPage());
        assertEquals(50, value.getPageSize());
        assertEquals("Depth", value.getTimeSeriesProfile().getKeyParameter());
        assertEquals("Description", value.getTimeSeriesProfile().getDescription());
        assertEquals("SWT", value.getTimeSeriesProfile().getLocationId().getOfficeId());
        assertEquals(firstDate, value.getFirstDate().toInstant());
        assertEquals(lastDate, value.getLastDate().toInstant());
        assertEquals(parameterList.get(0), value.getParameterColumns().get(0).getUnit());
        assertEquals(parameterList.get(1), value.getParameterColumns().get(1).getUnit());
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
        assertEquals("DSS-Obs", value.getVersion());
        assertEquals("Temp", value.getTimeSeriesProfile().getKeyParameter());
        assertEquals("Description 2", value.getTimeSeriesProfile().getDescription());
        assertEquals("SWT", value.getTimeSeriesProfile().getLocationId().getOfficeId());
        value = results.get(1);
        assertEquals("DSS-Obs", value.getVersion());
        assertEquals("Depth", value.getTimeSeriesProfile().getKeyParameter());
        assertEquals("Description", value.getTimeSeriesProfile().getDescription());
        assertEquals("SWT", value.getTimeSeriesProfile().getLocationId().getOfficeId());
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
