package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.VirtualOutlet;
import org.junit.jupiter.api.Test;
import mil.army.usace.hec.cwms.radar.client.controllers.VirtualOutletEndpointInput.GetAll;

import java.io.IOException;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestVirtualOutletEndpointInput {

    @Test
    void testGetAllQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        GetAll input = VirtualOutletEndpointInput.getAll().officeId("SPK").projectId("PROJ");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SPK", mockHttpRequestBuilder.getQueryParameter(GetAll.OFFICE_QUERY_PARAMETER));
        assertEquals("PROJ", mockHttpRequestBuilder.getQueryParameter(GetAll.PROJECT_ID_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String outletName = "OUT";
        String office = "SPK";
        String project = "PROJ";
        VirtualOutletEndpointInput.GetOne input = VirtualOutletEndpointInput.getOne(office, outletName, project);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(outletName, input.getOutletName());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(VirtualOutletEndpointInput.GetOne.OFFICE_QUERY_PARAMETER));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v1/json/virtual_outlet.json");
        VirtualOutlet outlet = RadarObjectMapper.mapJsonToObject(collect, VirtualOutlet.class);
        VirtualOutletEndpointInput.Post input = VirtualOutletEndpointInput.post(outlet);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(outlet, input.outletName());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String outletName = "OUT";
        String office = "SPK";
        String project = "PROJ";
        VirtualOutletEndpointInput.Delete input = VirtualOutletEndpointInput.delete(outletName, office,
                project, DeleteMethod.ALL);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(outletName, input.getOutletName());
        assertEquals(DeleteMethod.ALL.toString(), mockHttpRequestBuilder
                .getQueryParameter(VirtualOutletEndpointInput.Delete.DELETE_METHOD_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
        assertEquals(office, mockHttpRequestBuilder
                .getQueryParameter(VirtualOutletEndpointInput.Delete.OFFICE_QUERY_PARAMETER));
    }

    @Test
    void testPatchQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String oldOutletName = "OLD_OUT";
        String newOutletName = "NEW_OUT";
        String office = "SPK";
        String project = "PROJ";
        VirtualOutletEndpointInput.Patch input = VirtualOutletEndpointInput.patch(oldOutletName, newOutletName,
                project, office);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(oldOutletName, input.oldOutletName());
        assertEquals(newOutletName, mockHttpRequestBuilder.getQueryParameter(VirtualOutletEndpointInput.Patch.NEW_OUTLET_NAME_QUERY_PARAMETER));
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(VirtualOutletEndpointInput.Patch.OFFICE_QUERY_PARAMETER));
    }

}
