/*
 * MIT License
 *
 * Copyright (c) 2024 Hydrologic Engineering Center
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.ProjectEndpointInput.ProjectChildLocations.LOCATION_KIND_LIKE_QUERY_MASK;
import static mil.army.usace.hec.cwms.radar.client.controllers.ProjectEndpointInput.ProjectChildLocations.PROJECT_LIKE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.ProjectEndpointInput.StatusUpdate.APPLICATION_ID_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.ProjectEndpointInput.StatusUpdate.BEGIN_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.ProjectEndpointInput.StatusUpdate.END_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.ProjectEndpointInput.StatusUpdate.SOURCE_ID_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.ProjectEndpointInput.StatusUpdate.TIMESERIES_ID_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RatingTemplateEndpointInput.DELETE_METHOD_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RatingTemplateEndpointInput.OFFICE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
        assertEquals(officeId,
            mockHttpRequestBuilder.getQueryParameter(ProjectEndpointInput.GetOne.OFFICE_QUERY_PARAMETER));
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
        assertEquals(Integer.toString(pageSize),
            mockHttpRequestBuilder.getQueryParameter(ProjectEndpointInput.GetAll.PAGE_SIZE_QUERY_PARAMETER));

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

    @Test
    void testStatusUpdateDefaults() {
        ProjectEndpointInput.StatusUpdate input = ProjectEndpointInput.statusUpdate(OFFICE, "BIGH", "GitHub");
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("BIGH", input.projectId());
        assertEquals(OFFICE, mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals("GitHub", mockHttpRequestBuilder.getQueryParameter(APPLICATION_ID_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(SOURCE_ID_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(TIMESERIES_ID_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(BEGIN_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(END_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testStatusUpdate() {
        Instant begin = ZonedDateTime.of(2024, 3, 1, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant end = ZonedDateTime.of(2024, 3, 2, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        ProjectEndpointInput.StatusUpdate input = ProjectEndpointInput.statusUpdate(OFFICE, "BIGH", "GitHub")
            .sourceId("TestProjectEndpointInput")
            .timeSeriesId("ABC.Flow.Ave.1Day.1Day.TEST")
            .begin(begin)
            .end(end);
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("BIGH", input.projectId());
        assertEquals(OFFICE, mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals("GitHub", mockHttpRequestBuilder.getQueryParameter(APPLICATION_ID_QUERY_PARAMETER));
        assertEquals("TestProjectEndpointInput", mockHttpRequestBuilder.getQueryParameter(SOURCE_ID_QUERY_PARAMETER));
        assertEquals("ABC.Flow.Ave.1Day.1Day.TEST",
            mockHttpRequestBuilder.getQueryParameter(TIMESERIES_ID_QUERY_PARAMETER));
        assertEquals(begin.toString(), mockHttpRequestBuilder.getQueryParameter(BEGIN_QUERY_PARAMETER));
        assertEquals(end.toString(), mockHttpRequestBuilder.getQueryParameter(END_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testProjectChildLocationsDefault() {
        ProjectEndpointInput.ProjectChildLocations input = ProjectEndpointInput.projectChildLocations(OFFICE);
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(OFFICE, mockHttpRequestBuilder.getQueryParameter(
            ProjectEndpointInput.ProjectChildLocations.OFFICE_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(PROJECT_LIKE_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(LOCATION_KIND_LIKE_QUERY_MASK));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testProjectChildLocations() {
        ProjectEndpointInput.ProjectChildLocations input = ProjectEndpointInput.projectChildLocations(OFFICE)
            .projectIdMask("T*")
            .locationKindMask("*");
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(OFFICE, mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals("T*", mockHttpRequestBuilder.getQueryParameter(PROJECT_LIKE_QUERY_PARAMETER));
        assertEquals("*", mockHttpRequestBuilder.getQueryParameter(LOCATION_KIND_LIKE_QUERY_MASK));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

}
