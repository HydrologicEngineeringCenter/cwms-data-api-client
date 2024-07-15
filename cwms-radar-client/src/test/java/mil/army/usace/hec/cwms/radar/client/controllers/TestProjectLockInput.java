package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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


}
