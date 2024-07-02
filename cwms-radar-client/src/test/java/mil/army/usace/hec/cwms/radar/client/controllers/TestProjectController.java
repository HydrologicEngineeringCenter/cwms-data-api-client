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

class TestProjectController extends TestController
{

	@Test
	void testRetrieveProjects() throws IOException
	{
		String collect = readJsonFile("radar/v2/json/projects.json");
		mockHttpServer.enqueue(collect);
		mockHttpServer.start();
		ProjectController controller = new ProjectController();

		ProjectEndpointInput.GetAll input = ProjectEndpointInput.getAll()
				.officeId("SWT")
				.projectIdMask("BUFF.Stage;Flow.WCDS.Production");

		ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
		Projects projects = controller.retrieveProjects(apiConnectionInfo, input);
		assertNotNull(projects);
	}

	@Test
	void testRetrieveProject() throws IOException{
		String collect = readJsonFile("radar/v2/json/project.json");
		mockHttpServer.enqueue(collect);
		mockHttpServer.start();
		ProjectController controller = new ProjectController();

		ProjectEndpointInput.GetOne input = ProjectEndpointInput.getOne("BUFF.Stage;Flow.WCDS.Production", "SWT");

		ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
		Project project = controller.retrieveProject(apiConnectionInfo, input);
		assertNotNull(project);

	}

	@Test
	void testPost() throws IOException{
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
	void testDelete() throws IOException{
		String collect = readJsonFile("radar/v2/json/project.json");
		mockHttpServer.enqueue(collect);
		mockHttpServer.start();
		ProjectController controller = new ProjectController();

		ProjectEndpointInput.Delete input = ProjectEndpointInput.delete("BUFF.Stage;Flow.WCDS.Production", "SWT", DeleteMethod.ALL);

		ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
		assertDoesNotThrow(() -> controller.deleteProject(apiConnectionInfo, input));
	}
}
