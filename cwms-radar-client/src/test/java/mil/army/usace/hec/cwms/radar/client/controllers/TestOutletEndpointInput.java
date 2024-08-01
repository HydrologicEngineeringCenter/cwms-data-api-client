package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.Outlet;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;
import mil.army.usace.hec.cwms.radar.client.controllers.OutletEndpointInput.GetAll;

import java.io.IOException;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestOutletEndpointInput {

    @Test
    void testGetAllQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        GetAll input = OutletEndpointInput.getAll("PROJ-Name").officeId("SPK");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SPK", mockHttpRequestBuilder.getQueryParameter(GetAll.OFFICE_QUERY_PARAMETER));
        assertEquals("PROJ-Name", mockHttpRequestBuilder.getQueryParameter(GetAll.PROJECT_ID_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String outletName = "OUT";
        String office = "SPK";
        OutletEndpointInput.GetOne input = OutletEndpointInput.getOne(office, outletName);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(outletName, input.outletName());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(OutletEndpointInput.GetOne.OFFICE_QUERY_PARAMETER));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v1/json/outlet.json");
        Outlet outlet = RadarObjectMapper.mapJsonToObject(collect, Outlet.class);
        OutletEndpointInput.Post input = OutletEndpointInput.post(outlet);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(outlet, input.outlet());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String outletName = "OUT";
        String office = "SPK";
        OutletEndpointInput.Delete input = OutletEndpointInput.delete(office, outletName).deleteMethod(DeleteMethod.ALL);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(outletName, input.outletName());
        assertEquals(office, mockHttpRequestBuilder
                .getQueryParameter(OutletEndpointInput.Delete.OFFICE_QUERY_PARAMETER));
        assertEquals(DeleteMethod.ALL.toString(), mockHttpRequestBuilder
                .getQueryParameter(OutletEndpointInput.Delete.METHOD_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPatchQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String oldOutletName = "OLD_OUT";
        String newOutletName = "NEW_OUT";
        String office = "SPK";
        OutletEndpointInput.Patch input = OutletEndpointInput.patch(office, oldOutletName, newOutletName);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(oldOutletName, input.oldOutletName());
        assertEquals(newOutletName, mockHttpRequestBuilder
                .getQueryParameter(OutletEndpointInput.Patch.NEW_NAME_QUERY_PARAMETER));
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(OutletEndpointInput.Patch.OFFICE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNulls() {
        assertThrows(NullPointerException.class, () -> OutletEndpointInput.delete(null, ""));
        assertThrows(NullPointerException.class, () -> OutletEndpointInput.delete("", null));

    }
}
