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

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesCatalogEndpointInput.CATEGORY_LIKE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesCatalogEndpointInput.CURSOR_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesCatalogEndpointInput.GROUP_LIKE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesCatalogEndpointInput.LIKE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesCatalogEndpointInput.OFFICE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesCatalogEndpointInput.PAGE_SIZE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesCatalogEndpointInput.UNIT_SYSTEM_QUERY_PARAMETER;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestTimeSeriesCatalogEndpointInput {

    @Test
    void testQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        TimeSeriesCatalogEndpointInput input = new TimeSeriesCatalogEndpointInput()
            .officeId("SWT")
            .unitSystem("SI")
            .cursor("test")
            .pageSize(5)
            .timeSeriesIdFilter("BASE-SUB.Flow-Out.Ave.~1Day.1Day.Test")
            .categoryIdFilter("CAT")
            .groupIdFilter("GROUP");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals("SI", mockHttpRequestBuilder.getQueryParameter(UNIT_SYSTEM_QUERY_PARAMETER));
        assertEquals("test", mockHttpRequestBuilder.getQueryParameter(CURSOR_QUERY_PARAMETER));
        assertEquals("5", mockHttpRequestBuilder.getQueryParameter(PAGE_SIZE_QUERY_PARAMETER));
        assertEquals("BASE-SUB.Flow-Out.Ave.~1Day.1Day.Test", mockHttpRequestBuilder.getQueryParameter(LIKE_QUERY_PARAMETER));
        assertEquals("CAT", mockHttpRequestBuilder.getQueryParameter(CATEGORY_LIKE_QUERY_PARAMETER));
        assertEquals("GROUP", mockHttpRequestBuilder.getQueryParameter(GROUP_LIKE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }
}
