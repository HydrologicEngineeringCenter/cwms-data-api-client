package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.radar.client.model.CwmsId;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesProfile;
import org.junit.jupiter.api.Test;
import mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesProfileEndpointInput.GetAll;
import mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesProfileEndpointInput.GetOne;
import mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesProfileEndpointInput.Delete;
import mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesProfileEndpointInput.Post;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static org.junit.jupiter.api.Assertions.*;

final class TestTimeSeriesProfileEndpointInput {

    @Test
    void testGetAllQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String office = "SPK";
        String locationMask = "*LOC*";
        String parameterMask = "*PAR*";
        GetAll input = TimeSeriesProfileEndpointInput.getAll();
        input.locationMask(locationMask).officeMask(office).parameterMask(parameterMask).page("CWMSTESTPAGE").pageSize(12);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(GetAll.OFFICE_MASK_QUERY_PARAMETER));
        assertEquals(locationMask, mockHttpRequestBuilder.getQueryParameter(GetAll.LOCATION_MASK_QUERY_PARAMETER));
        assertEquals(parameterMask, mockHttpRequestBuilder.getQueryParameter(GetAll.PARAMETER_MASK_QUERY_PARAMETER));
        assertEquals("CWMSTESTPAGE", mockHttpRequestBuilder.getQueryParameter(GetAll.PAGE_QUERY_PARAMETER));
        assertEquals("12", mockHttpRequestBuilder.getQueryParameter(GetAll.PAGE_SIZE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String office = "SPK";
        String location = "LOC";
        String parameter = "PAR";
        GetOne input = TimeSeriesProfileEndpointInput.getOne(office, location, parameter);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(location, mockHttpRequestBuilder.getQueryParameter(GetOne.LOCATION_ID_QUERY_PARAMETER));
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(GetOne.OFFICE_ID_QUERY_PARAMETER));
        assertEquals(parameter, input.parameterId());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        TimeSeriesProfile profile = new TimeSeriesProfile()
                .description("description")
                .keyParameter("keyParameter")
                .locationId(new CwmsId().name("LOC").officeId("SPK"));
        Post input = TimeSeriesProfileEndpointInput.post(profile).failIfExists(false);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(profile, input.profile());
        assertFalse(Boolean.parseBoolean(mockHttpRequestBuilder
                .getQueryParameter(Post.FAIL_IF_EXISTS_QUERY_PARAMETER)));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String office = "SPK";
        String location = "LOC";
        String parameter = "PAR";
        Delete input = TimeSeriesProfileEndpointInput.delete(office, location, parameter);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(location, mockHttpRequestBuilder.getQueryParameter(Delete.LOCATION_ID_QUERY_PARAMETER));
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(Delete.OFFICE_QUERY_PARAMETER));
        assertEquals(parameter, input.parameterId());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNulls() {
        assertThrows(NullPointerException.class, () ->
                TimeSeriesProfileEndpointInput.delete(null, "LOC", "PAR"));
        assertThrows(NullPointerException.class, () ->
                TimeSeriesProfileEndpointInput.delete("SPK", null, "PAR"));
        assertThrows(NullPointerException.class, () ->
                TimeSeriesProfileEndpointInput.delete("SPK", "LOC", null));
        assertThrows(NullPointerException.class, () ->
                TimeSeriesProfileEndpointInput.delete(null, null, null));
    }
}
