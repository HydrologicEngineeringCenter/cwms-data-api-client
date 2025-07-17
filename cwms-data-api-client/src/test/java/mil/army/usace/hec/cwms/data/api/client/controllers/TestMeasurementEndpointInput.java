package mil.army.usace.hec.cwms.data.api.client.controllers;

import java.util.List;
import mil.army.usace.hec.cwms.data.api.client.model.Measurement;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;

import static mil.army.usace.hec.cwms.data.api.client.controllers.MeasurementEndpointInput.GetAll.*;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.*;

class TestMeasurementEndpointInput {

    @Test
    void testGetAllQueryRequestDefault() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        Instant begin = Instant.now();
        Instant end = Instant.now().plusSeconds(3600);
        MeasurementEndpointInput.GetAll input = MeasurementEndpointInput.getAll()
                .withOfficeIdMask("SPK")
                .withLocationIdMask("StreamLoc321")
                .withUnitSystem("km")
                .withMinNumber("1")
                .withMaxNumber("100")
                .withBegin(begin)
                .withEnd(end)
                .withTimezone("UTC")
                .withMinHeight(10)
                .withMaxHeight(20)
                .withMinFlow(100.0)
                .withMaxFlow(200.0)
                .withAgency("USGS")
                .withQuality("good");

        input.addInputParameters(mockHttpRequestBuilder);

        assertEquals("SPK", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals("StreamLoc321", mockHttpRequestBuilder.getQueryParameter(LOCATION_QUERY_PARAMETER));
        assertEquals("km", mockHttpRequestBuilder.getQueryParameter(UNIT_SYSTEM_QUERY_PARAMETER));
        assertEquals("1", mockHttpRequestBuilder.getQueryParameter(MIN_NUMBER_QUERY_PARAMETER));
        assertEquals("100", mockHttpRequestBuilder.getQueryParameter(MAX_NUMBER_QUERY_PARAMETER));
        assertEquals(begin.toString(), mockHttpRequestBuilder.getQueryParameter(BEGIN_QUERY_PARAMETER));
        assertEquals(end.toString(), mockHttpRequestBuilder.getQueryParameter(END_QUERY_PARAMETER));
        assertEquals("10", mockHttpRequestBuilder.getQueryParameter(MIN_HEIGHT_QUERY_PARAMETER));
        assertEquals("20", mockHttpRequestBuilder.getQueryParameter(MAX_HEIGHT_QUERY_PARAMETER));
        assertEquals("100.0", mockHttpRequestBuilder.getQueryParameter(MIN_FLOW_QUERY_PARAMETER));
        assertEquals("200.0", mockHttpRequestBuilder.getQueryParameter(MAX_FLOW_QUERY_PARAMETER));
        assertEquals("USGS", mockHttpRequestBuilder.getQueryParameter(AGENCY_QUERY_PARAMETER));
        assertEquals("good", mockHttpRequestBuilder.getQueryParameter(QUALITY_QUERY_PARAMETER));
        assertEquals("UTC", mockHttpRequestBuilder.getQueryParameter(TIMEZONE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v1/json/measurement.json");
        List<Measurement> measurements = RadarObjectMapper.mapJsonToListOfObjects(collect, Measurement.class);
        MeasurementEndpointInput.Post input = MeasurementEndpointInput.post(measurements);
        input.addInputParameters(mockHttpRequestBuilder);
        for(int i = 0; i < measurements.size(); i++) {
            assertEquals(measurements.get(i), input.measurements().get(i));
        }
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostNullMeasurement() {
        assertThrows(NullPointerException.class, () -> MeasurementEndpointInput.post(null));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String officeId = "SPK";
        String locationId = "LOCATION";
        Instant begin = Instant.now();
        Instant end = Instant.now().plusSeconds(3600);
        MeasurementEndpointInput.Delete input = MeasurementEndpointInput.delete(officeId, locationId, begin, end)
                .withMinNumber("1")
                .withMaxNumber("100")
                .withTimezone("UTC");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(locationId, input.getLocationId());
        assertEquals(officeId, mockHttpRequestBuilder.getQueryParameter(MeasurementEndpointInput.Delete.OFFICE_QUERY_PARAMETER));
        assertEquals(begin.toString(), mockHttpRequestBuilder.getQueryParameter(MeasurementEndpointInput.Delete.BEGIN_QUERY_PARAMETER));
        assertEquals(end.toString(), mockHttpRequestBuilder.getQueryParameter(MeasurementEndpointInput.Delete.END_QUERY_PARAMETER));
        assertEquals("1", mockHttpRequestBuilder.getQueryParameter(MeasurementEndpointInput.Delete.MIN_NUMBER_QUERY_PARAMETER));
        assertEquals("100", mockHttpRequestBuilder.getQueryParameter(MeasurementEndpointInput.Delete.MAX_NUMBER_QUERY_PARAMETER));
        assertEquals("UTC", mockHttpRequestBuilder.getQueryParameter(MeasurementEndpointInput.Delete.TIMEZONE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNullParameters() {
        assertThrows(NullPointerException.class, () -> MeasurementEndpointInput.delete(null, null, null, null));
        assertThrows(NullPointerException.class, () -> MeasurementEndpointInput.delete("SPK", null, null, null));
        assertThrows(NullPointerException.class, () -> MeasurementEndpointInput.delete("SPK", "LOCATION", null, null));
        assertThrows(NullPointerException.class, () -> MeasurementEndpointInput.delete("SPK", "LOCATION", Instant.now(), null));
    }
}
