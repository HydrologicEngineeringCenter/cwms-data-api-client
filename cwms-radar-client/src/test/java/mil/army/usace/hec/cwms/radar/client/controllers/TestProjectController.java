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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.MockHttpServer;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.Project;
import mil.army.usace.hec.cwms.radar.client.model.ProjectChildLocations;
import mil.army.usace.hec.cwms.radar.client.model.Projects;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

class TestProjectController extends TestController {

    public static final String PROJECT_ID = "ProjectLocationId";

    public static final String MASK = "ProjectLocationId";
    public static final String OFFICE = "SWT";

    @Test
    void testRetrieveProject() throws IOException {
        String collect = readJsonFile("radar/v1/json/project.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        ProjectController controller = new ProjectController();

        ProjectEndpointInput.GetOne input = ProjectEndpointInput.getOne(PROJECT_ID, OFFICE);

        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        Project project = controller.retrieveProject(apiConnectionInfo, input);
        assertNotNull(project);

    }

    @Test
    void testRetrieveProjects() throws IOException {
        String collect = readJsonFile("radar/v1/json/projects.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        ProjectController controller = new ProjectController();

        ProjectEndpointInput.GetAll input = ProjectEndpointInput.getAll()
            .officeId(OFFICE)
            .projectIdMask(MASK);

        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        Projects projects = controller.retrieveProjects(apiConnectionInfo, input);
        assertNotNull(projects);
    }


    @Test
    void testPost() throws IOException {
        String collect = readJsonFile("radar/v1/json/project.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Project project = RadarObjectMapper.mapJsonToObject(collect, Project.class);
        ProjectController controller = new ProjectController();

        ProjectEndpointInput.Post input = ProjectEndpointInput.post(project);

        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        assertDoesNotThrow(() -> controller.storeProject(apiConnectionInfo, input));
    }

    @Test
    void testUpdate() throws IOException {
        String collect = readJsonFile("radar/v1/json/project.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Project project = RadarObjectMapper.mapJsonToObject(collect, Project.class);
        ProjectController controller = new ProjectController();

        ProjectEndpointInput.Patch input = ProjectEndpointInput.patch(project);

        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        assertDoesNotThrow(() -> controller.updateProject(apiConnectionInfo, input));
    }

    @Test
    void testDelete() throws IOException {
        String collect = readJsonFile("radar/v1/json/project.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        ProjectController controller = new ProjectController();

        ProjectEndpointInput.Delete input = ProjectEndpointInput.delete(PROJECT_ID, OFFICE, DeleteMethod.ALL);

        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        assertDoesNotThrow(() -> controller.deleteProject(apiConnectionInfo, input));
    }

    @Test
    void testPublishStatusUpdate() throws Exception {
        mockHttpServer.enqueue("");
        mockHttpServer.start();
        ProjectController controller = new ProjectController();
        Instant begin = ZonedDateTime.of(2024, 3, 1, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant end = ZonedDateTime.of(2024, 3, 2, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        ProjectEndpointInput.StatusUpdate input = ProjectEndpointInput.statusUpdate("SPK", "BIGH", "GitHub")
            .sourceId("TestProjectController")
            .timeSeriesId("ABC.Flow.Ave.1Day.1Day.TEST")
            .begin(begin)
            .end(end);
        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        assertDoesNotThrow(() -> controller.publishStatusUpdate(apiConnectionInfo, input));
        MockHttpServer.RequestWrapper request = mockHttpServer.takeRequest();
        assertEquals(
            "/projects/status-update/BIGH?timeseries-id=ABC.Flow.Ave.1Day.1Day.TEST&end=2024-03-02T00%3A00%3A00Z&office=SPK&begin=2024-03-01T00%3A00%3A00Z&application-id=GitHub&source-id=TestProjectController",
            request.getPath());
        assertEquals("POST", request.getMethod());
    }

    @Test
    void testGetProjectChildLocations() throws Exception {
        String collect = readJsonFile("radar/v1/json/project_children_list.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        ProjectController controller = new ProjectController();
        ProjectEndpointInput.GetProjectChildLocations input = ProjectEndpointInput.getProjectChildLocations("SPK")
            .projectIdMask("T*")
            .locationKindMask("*");
        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        List<ProjectChildLocations> projectChildLocations =
            controller.getProjectChildLocations(apiConnectionInfo, input);
        assertEquals("TestProject1", projectChildLocations.get(0).getProjectId().getName());
        assertEquals("SPK", projectChildLocations.get(0).getProjectId().getOfficeId());
        assertFalse(projectChildLocations.get(0).getLocationsByKind().isEmpty());
    }
}
