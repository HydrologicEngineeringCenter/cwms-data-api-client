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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.radar.client.model.ProjectLock;
import mil.army.usace.hec.cwms.radar.client.model.ProjectLockId;
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

    @Test
    void testRequestLock() throws IOException {
        String collect = readJsonFile("radar/v1/json/project_lock_id.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();

        ProjectLockController controller = new ProjectLockController();

        String office = "SWT";
        String projectId = "SomeProject";
        String appId = "MockREGI";
        ProjectLock lock = new ProjectLock()
                .officeId(office)
                .projectId(projectId)
                .applicationId(appId)
                ;

        ProjectLockInput.LockRequest input = ProjectLockInput.requestLock(lock);
        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        ProjectLockId prjLockId = controller.requestLock(apiConnectionInfo, input);
        assertNotNull(prjLockId);
        String id = prjLockId.getId();
        assertNotNull(id);
        assertFalse(id.isEmpty());

    }

    @Test
    void testDenyRevoke() throws IOException {
        mockHttpServer.enqueue(200, "");
        mockHttpServer.start();

        ProjectLockController controller = new ProjectLockController();

        String lockId = "1232aabbcc";
        ProjectLockInput.LockRevokeDeny input = ProjectLockInput.denyRevoke(lockId);
        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        controller.denyLockRevoke(apiConnectionInfo, input);
    }

    @Test
    void testLockRelease() throws IOException {
        mockHttpServer.enqueue(200, "");
        mockHttpServer.start();

        ProjectLockController controller = new ProjectLockController();

        String lockId = "1232aabbcc";
        String office = "SWT";
        ProjectLockInput.LockRelease input = ProjectLockInput.releaseLock(office, lockId);
        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        controller.releaseLock(apiConnectionInfo, input);
    }

    @Test
    void testRevoke() throws IOException {
        mockHttpServer.enqueue(200, "");
        mockHttpServer.start();

        ProjectLockController controller = new ProjectLockController();

        String office = "SWT";
        String prj = "SomeProject";

        ProjectLockInput.LockRevoke input = ProjectLockInput.revokeLock(office, prj, "GitHub");
        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        controller.revokeLock(apiConnectionInfo, input);
    }


}
