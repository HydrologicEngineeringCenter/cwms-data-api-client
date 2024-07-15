package mil.army.usace.hec.cwms.radar.client.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.radar.client.model.ProjectLock;
import org.junit.jupiter.api.Test;


public class TestProjectLockController extends TestController {
    public static final String OFFICE_MASK = "SWT";
    public static final String PROJECT_MASK = "TestProject";
    public static final String APPLICATION_MASK = "MockREGI";

    @Test
    void testRetrieveProjectLocks() throws IOException {
        String collect = readJsonFile("radar/v1/json/project_locks.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        ProjectLockController controller = new ProjectLockController();

        ProjectLockInput.GetAll input = ProjectLockInput.getAll(OFFICE_MASK, PROJECT_MASK, APPLICATION_MASK);
        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        List<ProjectLock> lockRevokerRights = controller.retrieveProjectLocks(apiConnectionInfo, input);
        assertNotNull(lockRevokerRights);
        assertFalse(lockRevokerRights.isEmpty());
    }

    @Test
    void testRetrieveProjectLock() throws IOException {
        String collect = readJsonFile("radar/v1/json/project_lock.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();

        ProjectLockController controller = new ProjectLockController();

        ProjectLockInput.GetOne input = ProjectLockInput.getOne(OFFICE_MASK, PROJECT_MASK, APPLICATION_MASK);
        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        ProjectLock prjLock = controller.retrieveProjectLock(apiConnectionInfo, input);
        assertNotNull(prjLock);
        assertEquals("SPK", prjLock.getOfficeId());
        assertEquals("ProjectId0", prjLock.getProjectId());
        assertEquals("ApplicationId", prjLock.getApplicationId());

    }

}
