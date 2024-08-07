package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.radar.client.model.Basin;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.controllers.BasinEndpointInput.GetAll;
import java.io.IOException;
import org.junit.jupiter.api.Test;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestBasinEndpointInput {

    @Test
    void testGetAllQueryRequestDefault() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        GetAll input = BasinEndpointInput.getAll().officeId("SPK").units("m2");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SPK", mockHttpRequestBuilder.getQueryParameter(GetAll.OFFICE_QUERY_PARAMETER));
        assertEquals("m2", mockHttpRequestBuilder.getQueryParameter(GetAll.UNIT_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String basinId = "BAS";
        String office = "SPK";
        BasinEndpointInput.GetOne input = BasinEndpointInput.getOne(basinId, office).units("m2");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(basinId, input.basinId());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
        assertEquals("m2", mockHttpRequestBuilder.getQueryParameter(BasinEndpointInput.GetOne.UNIT_QUERY_PARAMETER));
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(BasinEndpointInput.GetOne.OFFICE_QUERY_PARAMETER));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v1/json/basin.json");
        Basin basin = RadarObjectMapper.mapJsonToObject(collect, Basin.class);
        BasinEndpointInput.Post input = BasinEndpointInput.post(basin);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(basin, input.basin());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String basinId = "BAS";
        String office = "SPK";
        BasinEndpointInput.Delete input = BasinEndpointInput.delete(basinId, office, DeleteMethod.ALL);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(basinId, input.basinId());
        assertEquals(DeleteMethod.ALL.toString(), mockHttpRequestBuilder.getQueryParameter(BasinEndpointInput.Delete.METHOD_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(BasinEndpointInput.Delete.OFFICE_QUERY_PARAMETER));
    }

    @Test
    void testDeleteNulls() {
        assertThrows(NullPointerException.class, () -> BasinEndpointInput.delete(null, "", DeleteMethod.ALL));
        assertThrows(NullPointerException.class, () -> BasinEndpointInput.delete("", null, DeleteMethod.ALL));
    }

    @Test
    void testPatch() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String oldBasinId = "BAS";
        String newBasinId = "NEW-BAS";
        String office = "SPK";
        BasinEndpointInput.Patch input = BasinEndpointInput.patch(oldBasinId, newBasinId, office);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(oldBasinId, input.oldBasinId());
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(BasinEndpointInput.Patch.OFFICE_QUERY_PARAMETER));
        assertEquals(newBasinId, mockHttpRequestBuilder.getQueryParameter(BasinEndpointInput.Patch.NAME_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }
}
