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

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_JSON;
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

        assertEquals(ACCEPT_HEADER_JSON, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
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
        assertEquals(appMask, mockHttpRequestBuilder.getQueryParameter(ProjectLockRevokerRightsInput.APPLICATION_ID));
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
