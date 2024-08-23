package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.radar.client.model.CwmsId;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesProfileParser;
import org.junit.jupiter.api.Test;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static org.junit.jupiter.api.Assertions.*;

import mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesProfileParserEndpointInput.GetAll;
import mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesProfileParserEndpointInput.GetOne;
import mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesProfileParserEndpointInput.Post;
import mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesProfileParserEndpointInput.Delete;


final class TestTimeSeriesProfileParserEndpointInput {

    @Test
    void testGetAllQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String locationMask = "*LOC*";
        String officeMask = "SPK";
        String parameterMask = "*PAR*";
        GetAll input = TimeSeriesProfileParserEndpointInput.getAll();
        input.locationMask(locationMask).officeMask(officeMask).parameterMask(parameterMask);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(locationMask, mockHttpRequestBuilder.getQueryParameter(GetAll.LOCATION_MASK_QUERY_PARAMETER));
        assertEquals(officeMask, mockHttpRequestBuilder.getQueryParameter(GetAll.OFFICE_MASK_QUERY_PARAMETER));
        assertEquals(parameterMask, mockHttpRequestBuilder.getQueryParameter(GetAll.PARAMETER_MASK_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String office = "SPK";
        String location = "LOC";
        String parameter = "Depth";
        GetOne input = TimeSeriesProfileParserEndpointInput.getOne(office, location, parameter);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(GetOne.OFFICE_ID_QUERY_PARAMETER));
        assertEquals(location, mockHttpRequestBuilder.getQueryParameter(GetOne.LOCATION_ID_QUERY_PARAMETER));
        assertEquals(parameter, input.parameterId());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String timezone = "PST";
        String keyParameter = "keyParameter";
        String office = "SPK";
        String location = "LOC";
        TimeSeriesProfileParser profileParser = new TimeSeriesProfileParser()
                .timeZone(timezone)
                .keyParameter(keyParameter)
                .recordDelimiter("\n")
                .timeInTwoFields(true)
                .locationId(new CwmsId().officeId(office).name(location));
        Post input = TimeSeriesProfileParserEndpointInput.post(profileParser).failIfExists(false);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(profileParser, input.profileParser());
        assertFalse(Boolean.parseBoolean(mockHttpRequestBuilder.getQueryParameter(Post.FAIL_IF_EXISTS_QUERY_PARAMETER)));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String office = "SPK";
        String location = "LOC";
        String parameter = "Depth";
        Delete input = TimeSeriesProfileParserEndpointInput.delete(office, location, parameter);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(location, mockHttpRequestBuilder.getQueryParameter(Delete.LOCATION_ID_QUERY_PARAMETER));
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(Delete.OFFICE_ID_QUERY_PARAMETER));
        assertEquals(parameter, input.parameterId());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNulls() {
        assertThrows(NullPointerException.class, () ->
                TimeSeriesProfileParserEndpointInput.delete(null, null, null));
        assertThrows(NullPointerException.class, () ->
                TimeSeriesProfileParserEndpointInput.delete("SPK", null, "Depth"));
        assertThrows(NullPointerException.class, () ->
                TimeSeriesProfileParserEndpointInput.delete(null, "PLACE", "Temp-Water"));
        assertThrows(NullPointerException.class, () ->
                TimeSeriesProfileParserEndpointInput.delete("LRL", "LOC", null));
    }
}
