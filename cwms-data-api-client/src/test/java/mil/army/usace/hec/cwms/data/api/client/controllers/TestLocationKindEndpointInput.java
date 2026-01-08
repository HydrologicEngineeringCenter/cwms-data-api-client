/*
 * MIT License
 *
 * Copyright (c) 2026 Hydrologic Engineering Center
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

package mil.army.usace.hec.cwms.data.api.client.controllers;

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestLocationKindEndpointInput {


    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder request = new MockHttpRequestBuilder();
        String projectId = "PROJ";
        String officeId = "SPK";
        String kind = "Outlet";
        LocationKindEndpointInput.GetAll input = LocationKindEndpointInput.getAll()
            .locationIdMask(projectId)
            .locationKindMask(kind)
            .officeId(officeId);
        input.addInputParameters(request);
        assertEquals(officeId, request.getQueryParameter(LocationKindEndpointInput.GetAll.OFFICE_QUERY_PARAMETER));
        assertEquals(projectId, request.getQueryParameter(LocationKindEndpointInput.GetAll.NAMES_QUERY_PARAMETER));
        assertEquals(kind, request.getQueryParameter(LocationKindEndpointInput.GetAll.LOCATION_KIND_LIKE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, request.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

}
