/*
 * MIT License
 *
 * Copyright (c) 2021 Hydrologic Engineering Center
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

import static mil.army.usace.hec.cwms.http.client.EndpointInput.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.LocationEndPointInput.NAME_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.LocationEndPointInput.OFFICE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.LocationEndPointInput.UNIT_QUERY_PARAMETER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


class TestLocationEndpointInput {

    @Test
    void testQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        LocationEndPointInput input = new LocationEndPointInput("LOC_TEST")
            .officeId("SWT")
            .unit("SI");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals("SI", mockHttpRequestBuilder.getQueryParameter(UNIT_QUERY_PARAMETER));
        assertEquals("LOC_TEST", mockHttpRequestBuilder.getQueryParameter(NAME_QUERY_PARAMETER));
        assertEquals("application/json;version=2", mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testNullLocationName() {
        assertThrows(NullPointerException.class, () -> new LocationEndPointInput(null));
    }


}
