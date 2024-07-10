package mil.army.usace.hec.cwms.radar.client.controllers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.radar.client.model.LockRevokerRights;
import org.junit.jupiter.api.Test;


public class TestProjectLockRevokerRightsController extends TestController {
    public static final String OFFICE_MASK = "SWT";
    public static final String PROJECT_MASK = "TestProject";
    public static final String APPLICATION_MASK = "MockREGI";

    @Test
    void testRetrieveLockRevokerRights() throws IOException {
        String collect = readJsonFile("radar/v1/json/lock_revoker_rights.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        ProjectLockRevokerRightsController controller = new ProjectLockRevokerRightsController();

        ProjectLockRevokerRightsInput.GetAll input = ProjectLockRevokerRightsInput.getAll(OFFICE_MASK, PROJECT_MASK, APPLICATION_MASK);
        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        List<LockRevokerRights> lockRevokerRights = controller.retrieveLockRevokerRights(apiConnectionInfo, input);
        assertNotNull(lockRevokerRights);
        assertFalse(lockRevokerRights.isEmpty());
    }
}
