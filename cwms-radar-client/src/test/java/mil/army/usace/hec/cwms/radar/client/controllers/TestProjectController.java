package mil.army.usace.hec.cwms.radar.client.controllers;

import java.io.IOException;

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.Project;
import mil.army.usace.hec.cwms.radar.client.model.Projects;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestProjectController extends TestController {

    public static final String PROJECT_ID = "ProjectLocationId";

    public static final String MASK = "ProjectLocationId";
    public static final String OFFICE = "SWT";

    @Test
    void testRetrieveProject() throws IOException {
        String collect = readJsonFile("radar/v2/json/project.json");
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
        String collect = readJsonFile("radar/v2/json/projects.json");
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
        String collect = readJsonFile("radar/v2/json/project.json");
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
        String collect = readJsonFile("radar/v2/json/project.json");
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
        String collect = readJsonFile("radar/v2/json/project.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        ProjectController controller = new ProjectController();

        ProjectEndpointInput.Delete input = ProjectEndpointInput.delete(PROJECT_ID, OFFICE, DeleteMethod.ALL);

        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        assertDoesNotThrow(() -> controller.deleteProject(apiConnectionInfo, input));
    }
}
