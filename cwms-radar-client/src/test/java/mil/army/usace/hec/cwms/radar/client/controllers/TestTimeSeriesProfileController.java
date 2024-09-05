package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesProfile;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

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
        List<TimeSeriesProfile> values = new TimeSeriesProfileController()
                .retrieveTimeSeriesProfiles(buildConnectionInfo(), input);
        assertNotNull(values);
        TimeSeriesProfile value = values.get(0);
        assertEquals("Test_TSP_Reference", value.getReferenceTsId().getName());
        assertEquals("SPK", value.getReferenceTsId().getOfficeId());
        assertEquals("Depth", value.getKeyParameter());
        assertEquals("Test Description", value.getDescription());
        value = values.get(1);
        assertEquals("Test_TSP_Reference2", value.getReferenceTsId().getName());
        assertEquals("SPK", value.getReferenceTsId().getOfficeId());
        assertEquals("Temp-Water", value.getKeyParameter());
        assertEquals("Test Description2", value.getDescription());
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
