package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.ProjectLockInput.LockRequest.REVOKE_EXISTING;
import static mil.army.usace.hec.cwms.radar.client.controllers.ProjectLockInput.REVOKE_TIMEOUT;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.CACHE_CONTROL;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import mil.army.usace.hec.cwms.radar.client.model.ProjectLock;
import org.junit.jupiter.api.Test;


class TestProjectLockInput {

    @Test
    void testGetAllQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();

        String officeMask = "SWT";
        String projMask = "SomeProject";
        String appMask = "MockREGI";
        ProjectLockInput.GetAll input = ProjectLockInput.getAll(officeMask, projMask, appMask);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(officeMask, mockHttpRequestBuilder.getQueryParameter(ProjectLockRevokerRightsInput.OFFICE_MASK));
        assertEquals(projMask, mockHttpRequestBuilder.getQueryParameter(ProjectLockRevokerRightsInput.PROJECT_MASK));
        assertEquals(appMask, mockHttpRequestBuilder.getQueryParameter(ProjectLockRevokerRightsInput.APPLICATION_MASK));

        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
        assertTrue(mockHttpRequestBuilder.getQueryHeader(CACHE_CONTROL).contains("no-cache"));
    }

    @Test
    void testGetAllQueryRequestNulls() {
        String officeMask = "SWT";
        String projMask = "SomeProject";
        String appMask = "MockREGI";
        assertThrows(NullPointerException.class, () -> ProjectLockInput.getAll(null, projMask, appMask));
        assertDoesNotThrow(() -> ProjectLockInput.getAll(officeMask, null, appMask));
        assertDoesNotThrow(() -> ProjectLockInput.getAll(officeMask, projMask, null));
    }

    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();

        String office = "SWT";
        String projectId = "SomeProject";
        String appId = "MockREGI";
        ProjectLockInput.GetOne input = ProjectLockInput.getOne(office, projectId, appId);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(ProjectLockInput.OFFICE));
        assertEquals(appId, mockHttpRequestBuilder.getQueryParameter(ProjectLockInput.APPLICATION_ID));

        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
        assertTrue(mockHttpRequestBuilder.getQueryHeader(CACHE_CONTROL).contains("no-cache"));
    }

    @Test
    void testGetOneQueryRequestNulls() {
        String office = "SWT";
        String proj = "SomeProject";
        String app = "MockREGI";
        assertThrows(NullPointerException.class, () -> ProjectLockInput.getOne(null, proj, app));
        assertThrows(NullPointerException.class, () -> ProjectLockInput.getOne(office, null, app));
        assertThrows(NullPointerException.class, () -> ProjectLockInput.getOne(office, proj, null));
    }

    @Test
    void testRequestLockQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();

        String office = "SWT";
        String projectId = "SomeProject";
        String appId = "MockREGI";
        ProjectLock lock = new ProjectLock()
                .officeId(office)
                .projectId(projectId)
                .applicationId(appId)
                ;

        ProjectLockInput.LockRequest input = ProjectLockInput.requestLock(lock);
        input.addInputParameters(mockHttpRequestBuilder);

        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(REVOKE_EXISTING));
        assertEquals("10", mockHttpRequestBuilder.getQueryParameter(REVOKE_TIMEOUT));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));

        input.revokeTimeoutSeconds(11)
                .revokeExisting(true);
        input.addInputParameters(mockHttpRequestBuilder);

        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(REVOKE_EXISTING));
        assertEquals("11", mockHttpRequestBuilder.getQueryParameter(REVOKE_TIMEOUT));

        input.revokeExisting(false)
                .revokeTimeoutSeconds(12);
        input.addInputParameters(mockHttpRequestBuilder);

        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(REVOKE_EXISTING));
        assertEquals("12", mockHttpRequestBuilder.getQueryParameter(REVOKE_TIMEOUT));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));

    }

    @Test
    void testRequestLockQueryRequestNulls() {
        assertThrows(NullPointerException.class, () -> ProjectLockInput.requestLock(null));
    }

    @Test
    void testRequestLockRevokeDeny() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();

        String lockId = "somerandomstring_maybe_guid";
        ProjectLockInput.LockRevokeDeny deny = ProjectLockInput.denyRevoke(lockId);
        deny.addInputParameters(mockHttpRequestBuilder);

        assertEquals(lockId, mockHttpRequestBuilder.getQueryParameter(ProjectLockInput.LockRevokeDeny.LOCK_ID));
    }

    @Test
    void testRequestLockRevokeDenyNull() {
        assertThrows(NullPointerException.class, () -> ProjectLockInput.denyRevoke(null));
    }

    @Test
    void testRequestLockReleaseDeny() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();

        String lockId = "somerandomstring_maybe_guid";
        String office = "SWT";
        ProjectLockInput.LockRelease deny = ProjectLockInput.releaseLock(office, lockId);
        deny.addInputParameters(mockHttpRequestBuilder);

        assertEquals(lockId, mockHttpRequestBuilder.getQueryParameter(ProjectLockInput.LockRelease.LOCK_ID));
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(ProjectLockInput.OFFICE));
    }

    @Test
    void testRequestLockReleaseNull() {
        assertThrows(NullPointerException.class, () -> ProjectLockInput.releaseLock(null, "asdf"));
        assertThrows(NullPointerException.class, () -> ProjectLockInput.releaseLock("SWT", null));
    }

    @Test
    void testLockRevoke() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();

        String project = "SomeProject";
        String office = "SWT";
        ProjectLockInput.LockRevoke deny = ProjectLockInput.revokeLock(office, project);
        deny.addInputParameters(mockHttpRequestBuilder);

        assertEquals("10", mockHttpRequestBuilder.getQueryParameter(REVOKE_TIMEOUT));
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(ProjectLockInput.OFFICE));

        deny.revokeTimeout(11).addInputParameters(mockHttpRequestBuilder);

        assertEquals("11", mockHttpRequestBuilder.getQueryParameter(REVOKE_TIMEOUT));
    }

    @Test
    void testLockRevokeNull() {
        assertThrows(NullPointerException.class, () -> ProjectLockInput.revokeLock(null, "asdf"));
        assertThrows(NullPointerException.class, () -> ProjectLockInput.revokeLock("SWT", null));
    }


}
