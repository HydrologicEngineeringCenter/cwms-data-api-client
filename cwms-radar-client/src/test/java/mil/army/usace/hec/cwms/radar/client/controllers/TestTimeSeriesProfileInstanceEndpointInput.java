package mil.army.usace.hec.cwms.radar.client.controllers;

import org.junit.jupiter.api.Test;
import mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesProfileInstanceEndpointInput.GetAll;
import mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesProfileInstanceEndpointInput.GetOne;
import mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesProfileInstanceEndpointInput.Post;
import mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesProfileInstanceEndpointInput.Delete;

import java.time.Instant;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class TestTimeSeriesProfileInstanceEndpointInput {

    @Test
    void testGetAllQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String locationMask = "*LOC*";
        String officeMask = "SPK";
        String parameterMask = "*PAR*";
        GetAll input = TimeSeriesProfileInstanceEndpointInput.getAll()
                .locationMask(locationMask).officeMask(officeMask).parameterMask(parameterMask);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
        assertEquals(locationMask, mockHttpRequestBuilder.getQueryParameter(GetAll.LOCATION_MASK_QUERY_PARAMETER));
        assertEquals(officeMask, mockHttpRequestBuilder.getQueryParameter(GetAll.OFFICE_MASK_QUERY_PARAMETER));
        assertEquals(parameterMask, mockHttpRequestBuilder.getQueryParameter(GetAll.PARAMETER_MASK_QUERY_PARAMETER));
    }

    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String office = "SPK";
        String timeSeriesId = "SPK.LOC.Temp-Water";
        String version = "1";
        String unit = "F";
        String parameter = "Temp-Water";
        boolean startInclusive = true;
        boolean endInclusive = true;
        boolean previous = true;
        boolean next = true;
        Instant start = Instant.parse("1970-01-01T00:00:00Z");
        Instant end = Instant.parse("1970-01-02T00:00:00Z");
        String timezone = "PST";
        boolean maxVersion = true;
        Instant versionDate = Instant.now();
        GetOne input = TimeSeriesProfileInstanceEndpointInput.getOne(office, timeSeriesId, parameter, version, unit)
                .startInclusive(startInclusive).endInclusive(endInclusive).start(start).end(end).next(next)
                .previous(previous).maxVersion(maxVersion).timezone(timezone).versionDate(versionDate);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(GetOne.OFFICE_ID_QUERY_PARAMETER));
        assertEquals(timeSeriesId, input.timeseriesId());
        assertEquals(parameter, mockHttpRequestBuilder.getQueryParameter(GetOne.PARAMETER_ID_QUERY_PARAMETER));
        assertEquals(version, mockHttpRequestBuilder.getQueryParameter(GetOne.VERSION_QUERY_PARAMETER));
        assertEquals(Boolean.toString(startInclusive), mockHttpRequestBuilder
                .getQueryParameter(GetOne.START_INCLUSIVE_QUERY_PARAMETER));
        assertEquals(Boolean.toString(endInclusive), mockHttpRequestBuilder
                .getQueryParameter(GetOne.END_INCLUSIVE_QUERY_PARAMETER));
        assertEquals(start.toString(), mockHttpRequestBuilder.getQueryParameter(GetOne.START_QUERY_PARAMETER));
        assertEquals(end.toString(), mockHttpRequestBuilder.getQueryParameter(GetOne.END_QUERY_PARAMETER));
        assertEquals(Boolean.toString(next), mockHttpRequestBuilder.getQueryParameter(GetOne.NEXT_QUERY_PARAMETER));
        assertEquals(Boolean.toString(previous), mockHttpRequestBuilder
                .getQueryParameter(GetOne.PREVIOUS_QUERY_PARAMETER));
        assertEquals(timezone, mockHttpRequestBuilder.getQueryParameter(GetOne.TIMEZONE_QUERY_PARAMETER));
        assertEquals(Boolean.toString(maxVersion), mockHttpRequestBuilder
                .getQueryParameter(GetOne.MAX_VERSION_QUERY_PARAMETER));
        assertEquals(versionDate.toString(), mockHttpRequestBuilder
                .getQueryParameter(GetOne.VERSION_DATE_QUERY_PARAMETER));
        assertEquals(unit, mockHttpRequestBuilder.getQueryParameter(GetOne.UNIT_QUERY_PARAMETER));
    }

    @Test
    void testPostQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String profileData = "1970/09/09,12:15:34,56.7";
        String version = "1";
        boolean overrideProtection = true;
        String method = "REPLACE ALL";
        Instant versionDate = Instant.now();
        Post input = TimeSeriesProfileInstanceEndpointInput.post(profileData, version)
                .method(method).versionDate(versionDate).overrideProtection(overrideProtection);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
        assertEquals(profileData, mockHttpRequestBuilder.getQueryParameter(Post.PROFILE_DATA_QUERY_PARAMETER));
        assertEquals(version, mockHttpRequestBuilder.getQueryParameter(Post.VERSION_QUERY_PARAMETER));
        assertEquals(method, mockHttpRequestBuilder.getQueryParameter(Post.METHOD_QUERY_PARAMETER));
        assertEquals(versionDate.toString(), mockHttpRequestBuilder.getQueryParameter(Post.VERSION_DATE_QUERY_PARAMETER));
        assertEquals(Boolean.toString(overrideProtection), mockHttpRequestBuilder.getQueryParameter(Post.OVERRIDE_PROTECTION_QUERY_PARAMETER));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String office = "SPK";
        String version = "1";
        String timeSeriesId = "SPK.LOC.Temp-Water";
        String parameter = "Temp-Water";
        boolean overrideProtection = true;
        Instant versionDate = Instant.now();
        Instant date = Instant.now();
        String timezone = "PST";
        Delete input = TimeSeriesProfileInstanceEndpointInput.delete(office, version, parameter, timeSeriesId)
                .date(date).timezone(timezone).versionDate(versionDate).overrideProtection(overrideProtection);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(Delete.OFFICE_ID_QUERY_PARAMETER));
        assertEquals(version, mockHttpRequestBuilder.getQueryParameter(Delete.VERSION_QUERY_PARAMETER));
        assertEquals(timeSeriesId, input.timeseriesId());
        assertEquals(date, Instant.parse(mockHttpRequestBuilder.getQueryParameter(Delete.DATE_QUERY_PARAMETER)));
        assertEquals(timezone, mockHttpRequestBuilder.getQueryParameter(Delete.TIMEZONE_QUERY_PARAMETER));
        assertEquals(versionDate.toString(), mockHttpRequestBuilder.getQueryParameter(Delete.VERSION_DATE_QUERY_PARAMETER));
        assertEquals(Boolean.toString(overrideProtection), mockHttpRequestBuilder.getQueryParameter(Delete.OVERRIDE_PROTECTION_QUERY_PARAMETER));
        assertEquals(parameter, mockHttpRequestBuilder.getQueryParameter(Delete.PARAMETER_ID_QUERY_PARAMETER));
    }

    @Test
    void testDeleteNulls() {
        assertThrows(NullPointerException.class, () ->
            TimeSeriesProfileInstanceEndpointInput.delete(null, null, null, null));
        assertThrows(NullPointerException.class, () ->
                TimeSeriesProfileInstanceEndpointInput.delete("SPK", "Raw", "Temp-Water", null));
        assertThrows(NullPointerException.class, () ->
                TimeSeriesProfileInstanceEndpointInput.delete("SPK", "Raw", null, "SPK.LOC.Temp-Water"));
        assertThrows(NullPointerException.class, () ->
                TimeSeriesProfileInstanceEndpointInput.delete("SPK", null, "Temp-Water", "SPK.LOC.Temp-Water"));
        assertThrows(NullPointerException.class, () ->
                TimeSeriesProfileInstanceEndpointInput.delete(null, "Raw", "Temp-Water", "SPK.LOC.Temp-Water"));
    }
}
