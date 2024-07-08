package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RatingTemplateEndpointInput.DELETE_METHOD_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RatingTemplateEndpointInput.OFFICE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.Project;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

class TestProjectEndpointInput {

    public static final String OFFICE = "SWT";

    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String projectId = "project-id";
        String officeId = OFFICE;
        ProjectEndpointInput.GetOne input = ProjectEndpointInput.getOne(projectId, officeId);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(officeId, mockHttpRequestBuilder.getQueryParameter(ProjectEndpointInput.GetOne.OFFICE_QUERY_PARAMETER));
        assertEquals(projectId, input.projectId());

        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testNullGetOne() {
        assertThrows(NullPointerException.class, () -> ProjectEndpointInput.getOne(null, OFFICE));
        assertThrows(NullPointerException.class, () -> ProjectEndpointInput.getOne("id", null));
    }

    @Test
    void testGetAllQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();

        String officeId = OFFICE;
        String page = "asdfasdfasdf";
        int pageSize = 100;
        String idMask = "Location*";
        ProjectEndpointInput.GetAll input = ProjectEndpointInput.getAll()
                .officeId(officeId)
                .projectIdMask(idMask)
                .page(page)
                .pageSize(pageSize);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(officeId, mockHttpRequestBuilder.getQueryParameter(ProjectEndpointInput.OFFICE_QUERY_PARAMETER));
        assertEquals(idMask, mockHttpRequestBuilder.getQueryParameter(ProjectEndpointInput.GetAll.ID_MASK));
        assertEquals(page, mockHttpRequestBuilder.getQueryParameter(ProjectEndpointInput.GetAll.PAGE_QUERY_PARAMETER));
        assertEquals(Integer.toString(pageSize), mockHttpRequestBuilder.getQueryParameter(ProjectEndpointInput.GetAll.PAGE_SIZE_QUERY_PARAMETER));

        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v2/json/project.json");
        Project project = RadarObjectMapper.mapJsonToObject(collect, Project.class);
        ProjectEndpointInput.Post input = ProjectEndpointInput.post(project);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(project, input.project());
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostNullProject() {
        assertThrows(NullPointerException.class, () -> ProjectEndpointInput.post(null));
    }

    @Test
    void testDelete() {
        ProjectEndpointInput.Delete input = ProjectEndpointInput.delete("id", OFFICE, DeleteMethod.ALL);
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(OFFICE, mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals("DELETE_ALL", mockHttpRequestBuilder.getQueryParameter(DELETE_METHOD_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNullTimeSeriesId() {
        assertThrows(NullPointerException.class, () -> ProjectEndpointInput.delete(null, null, null));
    }

    @Test
    void testDeleteNullOffice() {
        assertThrows(NullPointerException.class, () -> ProjectEndpointInput.delete("", null, null));
    }

}
