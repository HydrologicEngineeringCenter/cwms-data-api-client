package mil.army.usace.hec.cwms.data.api.client.controllers;

import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.data.api.client.model.TimeSeriesProfile;
import mil.army.usace.hec.cwms.data.api.client.model.TimeSeriesProfileList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

final class TestTimeSeriesProfileController extends TestController {

    @Test
    void testGetOne() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_profile.json" );
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesProfileEndpointInput.GetOne input = TimeSeriesProfileEndpointInput
                .getOne("SPK", "Test_TSP_Location", "Depth");
        TimeSeriesProfile value = new TimeSeriesProfileController()
                .retrieveTimeSeriesProfile(buildConnectionInfo(), input);
        assertNotNull(value);
        assertEquals("Test_TSP_Reference", value.getReferenceTsId().getName());
        assertEquals("SPK", value.getReferenceTsId().getOfficeId());
        assertEquals("Depth", value.getKeyParameter());
        assertEquals("Test Description", value.getDescription());
    }

    @Test
    void testGetAll() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_profiles.json" );
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesProfileEndpointInput.GetAll input = TimeSeriesProfileEndpointInput
                .getAll();
        TimeSeriesProfileList values = new TimeSeriesProfileController()
                .retrieveTimeSeriesProfiles(buildConnectionInfo(), input);
        assertNotNull(values);
        TimeSeriesProfile value = values.getProfileList().get(0);
        assertEquals("Test_TSP_Location.Elev.Inst.1Hour.0.DSS-Obs", value.getReferenceTsId().getName());
        assertEquals("SPK", value.getReferenceTsId().getOfficeId());
        assertEquals("Depth", value.getKeyParameter());
        assertEquals("Test Description", value.getDescription());
        value = values.getProfileList().get(1);
        assertEquals("Test_TSP_Location2.Elev.Inst.1Hour.0.DSS-Obs", value.getReferenceTsId().getName());
        assertEquals("SPK", value.getReferenceTsId().getOfficeId());
        assertEquals("Temp-Water", value.getKeyParameter());
        assertEquals("Test Description 2", value.getDescription());
        assertEquals(1000, values.getPageSize());
        assertNotNull(values.getNextPage());
    }

    @Test
    void testStore() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_profile.json" );
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesProfile profile = RadarObjectMapper.mapJsonToObject(collect, TimeSeriesProfile.class);
        TimeSeriesProfileEndpointInput.Post input = TimeSeriesProfileEndpointInput.post(profile);
        assertDoesNotThrow(() -> new TimeSeriesProfileController().storeTimeSeriesProfile(buildConnectionInfo(), input));
    }

    @Test
    void testDelete() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_profile.json" );
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesProfile profile = RadarObjectMapper.mapJsonToObject(collect, TimeSeriesProfile.class);
        TimeSeriesProfileEndpointInput.Delete input = TimeSeriesProfileEndpointInput
                .delete(profile.getLocationId().getOfficeId(),
                        profile.getLocationId().getName(), profile.getKeyParameter());
        assertDoesNotThrow(() -> new TimeSeriesProfileController()
                .deleteTimeSeriesProfile(buildConnectionInfo(), input));
    }
}
