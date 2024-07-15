package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


class TestProjectLockRevokerRightsInput {

    @Test
    void testGetAllQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();

        String officeMask = "SWT";
        String projMask = "SomeProject";
        String appMask = "MockREGI";
        ProjectLockRevokerRightsInput.GetAll input = ProjectLockRevokerRightsInput.getAll(officeMask, projMask, appMask);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(officeMask, mockHttpRequestBuilder.getQueryParameter(ProjectLockRevokerRightsInput.OFFICE_MASK));
        assertEquals(projMask, mockHttpRequestBuilder.getQueryParameter(ProjectLockRevokerRightsInput.PROJECT_MASK));
        assertEquals(appMask, mockHttpRequestBuilder.getQueryParameter(ProjectLockRevokerRightsInput.APPLICATION_MASK));

        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetAllQueryRequestNulls() {
        String officeMask = "SWT";
        String projMask = "SomeProject";
        String appMask = "MockREGI";
        assertThrows(NullPointerException.class, () -> ProjectLockRevokerRightsInput.getAll(null, projMask, appMask));
        assertDoesNotThrow(() -> ProjectLockRevokerRightsInput.getAll(officeMask, null, appMask));
        assertDoesNotThrow(() -> ProjectLockRevokerRightsInput.getAll(officeMask, projMask, null));
    }

    @Test
    void testUpdateRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();

        String sessionOffice = "SWT";
        String officeMask = "SWT";
        String projMask = "SomeProject";
        String appMask = "MockREGI";
        String user = "TheUser";
        boolean allow = true;
        ProjectLockRevokerRightsInput.Update input = ProjectLockRevokerRightsInput.update(sessionOffice, officeMask, projMask, appMask, user, allow);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(sessionOffice, mockHttpRequestBuilder.getQueryParameter(ProjectLockRevokerRightsInput.OFFICE));
        assertEquals(officeMask, mockHttpRequestBuilder.getQueryParameter(ProjectLockRevokerRightsInput.OFFICE_MASK));
        assertEquals(projMask, mockHttpRequestBuilder.getQueryParameter(ProjectLockRevokerRightsInput.PROJECT_MASK));
        assertEquals(appMask, mockHttpRequestBuilder.getQueryParameter(ProjectLockRevokerRightsInput.APPLICATION_MASK));
        assertEquals(user, mockHttpRequestBuilder.getQueryParameter(ProjectLockRevokerRightsInput.USER));
        assertEquals(String.valueOf(allow), mockHttpRequestBuilder.getQueryParameter(ProjectLockRevokerRightsInput.ALLOW));

    }

    @Test
    void testPostQueryRequestNulls() {
        String sessionOffice = "SWT";
        String officeMask = "SWT";
        String projMask = "SomeProject";
        String appMask = "MockREGI";
        String user = "TheUser";
        boolean allow = true;

        assertThrows(NullPointerException.class, () -> ProjectLockRevokerRightsInput.update(null, officeMask, projMask, appMask, user, allow));
        assertDoesNotThrow(() -> ProjectLockRevokerRightsInput.update(sessionOffice, null, projMask, appMask, user, allow));
        assertDoesNotThrow(() -> ProjectLockRevokerRightsInput.update(sessionOffice, officeMask, null, appMask, user, allow));
        assertThrows(NullPointerException.class, () -> ProjectLockRevokerRightsInput.update(sessionOffice, officeMask, projMask, null, user, allow));
        assertThrows(NullPointerException.class, () -> ProjectLockRevokerRightsInput.update(sessionOffice, officeMask, projMask, appMask, null, allow));
        assertDoesNotThrow(() -> ProjectLockRevokerRightsInput.update(sessionOffice, officeMask, projMask, appMask, user, allow));
    }


}
