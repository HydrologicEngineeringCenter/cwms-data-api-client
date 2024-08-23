package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesProfileParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

final class TestTimeSeriesProfileParserController extends TestController {
    @Test
    void testGetOne_indexed() throws IOException {
        String collect = readJsonFile("radar/v2/json/ts_profile_parser_indexed.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesProfileParserEndpointInput.GetOne input = TimeSeriesProfileParserEndpointInput
                .getOne("SPK", "Test_TSP_Location", "Depth");
        TimeSeriesProfileParser value = new TimeSeriesProfileParserController()
                .retrieveTimeSeriesProfileParser(buildConnectionInfo(), input);
        assertNotNull(value);
        assertEquals("SPK", value.getLocationId().getOfficeId());
        assertEquals("Depth", value.getKeyParameter());
        assertEquals("UTC", value.getTimeZone());
        assertEquals(",", value.getRecordDelimiter());
    }

    void testGetOne_columnar() throws IOException {
        String collect = readJsonFile("radar/v2/json/ts_profile_parser_columnar.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesProfileParserEndpointInput.GetOne input = TimeSeriesProfileParserEndpointInput
                .getOne("SPK", "Test_TSP_Location", "Depth");
        TimeSeriesProfileParser value = new TimeSeriesProfileParserController()
                .retrieveTimeSeriesProfileParser(buildConnectionInfo(), input);
        assertNotNull(value);
        assertEquals("SPK", value.getLocationId().getOfficeId());
        assertEquals("Depth", value.getKeyParameter());
        assertEquals("UTC", value.getTimeZone());
        assertEquals(",", value.getRecordDelimiter());
    }

    @Test
    void testGetAll_indexed() throws IOException {
        String collect = readJsonFile("radar/v2/json/ts_profile_parsers_indexed.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesProfileParserEndpointInput.GetAll input = TimeSeriesProfileParserEndpointInput
                .getAll();
        TimeSeriesProfileParserController controller = new TimeSeriesProfileParserController();
        List<TimeSeriesProfileParser> results = controller.retrieveTimeSeriesProfileParsers(buildConnectionInfo(), input);
        assertNotNull(results);
        TimeSeriesProfileParser value = results.get(0);
        assertEquals("SPK", value.getLocationId().getOfficeId());
        assertEquals("Depth", value.getKeyParameter());
        assertEquals("UTC", value.getTimeZone());
        assertEquals(",", value.getRecordDelimiter());
        value = results.get(1);
        assertEquals("SPK", value.getLocationId().getOfficeId());
        assertEquals("Temp-Water", value.getKeyParameter());
        assertEquals("UTC", value.getTimeZone());
        assertEquals(",", value.getRecordDelimiter());
    }

    @Test
    void testGetAll_columnar() throws IOException {
        String collect = readJsonFile("radar/v2/json/ts_profile_parsers_columnar.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesProfileParserEndpointInput.GetAll input = TimeSeriesProfileParserEndpointInput
                .getAll();
        TimeSeriesProfileParserController controller = new TimeSeriesProfileParserController();
        List<TimeSeriesProfileParser> results = controller.retrieveTimeSeriesProfileParsers(buildConnectionInfo(), input);
        assertNotNull(results);
        TimeSeriesProfileParser value = results.get(0);
        assertEquals("SPK", value.getLocationId().getOfficeId());
        assertEquals("Depth", value.getKeyParameter());
        assertEquals("UTC", value.getTimeZone());
        assertEquals(",", value.getRecordDelimiter());
        value = results.get(1);
        assertEquals("SPK", value.getLocationId().getOfficeId());
        assertEquals("Temp-Water", value.getKeyParameter());
        assertEquals("UTC", value.getTimeZone());
        assertEquals(",", value.getRecordDelimiter());
    }

    @Test
    void testStore_indexed() throws IOException {
        String collect = readJsonFile("radar/v2/json/ts_profile_parser_indexed.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesProfileParser profile = RadarObjectMapper.mapJsonToObject(collect, TimeSeriesProfileParser.class);
        TimeSeriesProfileParserEndpointInput.Post input = TimeSeriesProfileParserEndpointInput.post(profile);
        assertDoesNotThrow(() -> new TimeSeriesProfileParserController()
                .storeTimeSeriesProfileParser(buildConnectionInfo(), input));
    }

    @Test
    void testStore_columnar() throws IOException {
        String collect = readJsonFile("radar/v2/json/ts_profile_parser_columnar.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesProfileParser profile = RadarObjectMapper.mapJsonToObject(collect, TimeSeriesProfileParser.class);
        TimeSeriesProfileParserEndpointInput.Post input = TimeSeriesProfileParserEndpointInput.post(profile);
        assertDoesNotThrow(() -> new TimeSeriesProfileParserController()
                .storeTimeSeriesProfileParser(buildConnectionInfo(), input));
    }

    @Test
    void testDelete_indexed() throws IOException {
        String collect = readJsonFile("radar/v2/json/ts_profile_parser_indexed.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesProfileParserEndpointInput.Delete input = TimeSeriesProfileParserEndpointInput
                .delete("SPK", "Test_TSP_Location", "Depth");
        assertDoesNotThrow(() -> new TimeSeriesProfileParserController()
                .deleteTimeSeriesProfileParser(buildConnectionInfo(), input));
    }

    @Test
    void testDelete_columnar() throws IOException {
        String collect = readJsonFile("radar/v2/json/ts_profile_parser_columnar.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesProfileParserEndpointInput.Delete input = TimeSeriesProfileParserEndpointInput
                .delete("SPK", "Test_TSP_Location", "Depth");
        assertDoesNotThrow(() -> new TimeSeriesProfileParserController()
                .deleteTimeSeriesProfileParser(buildConnectionInfo(), input));
    }

}
