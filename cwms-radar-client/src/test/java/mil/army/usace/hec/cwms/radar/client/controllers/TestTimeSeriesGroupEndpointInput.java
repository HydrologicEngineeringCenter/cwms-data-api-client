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
import static mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesGroupEndpointInput.CATEGORY_ID_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesGroupEndpointInput.OFFICE_QUERY_PARAMETER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class TestTimeSeriesGroupEndpointInput {

    @Test
    void testTimeSeriesGroupEndpointInputGetAll() {
        TimeSeriesGroupEndpointInput timeSeriesCategoryEndpointInput = new TimeSeriesGroupEndpointInput();
        assertFalse(timeSeriesCategoryEndpointInput.getGroupId().isPresent());
    }

    @Test
    void testTimeSeriesGroupEndpointInputCategoryId() {
        TimeSeriesGroupEndpointInput timeSeriesCategoryEndpointInput = new TimeSeriesGroupEndpointInput("category-id", "group-id");
        assertEquals("group-id", timeSeriesCategoryEndpointInput.getGroupId().get());
    }

    @Test
    void testQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        TimeSeriesGroupEndpointInput input = new TimeSeriesGroupEndpointInput("category-id", "group-id")
            .officeId("SWT");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("category-id", mockHttpRequestBuilder.getQueryParameter(CATEGORY_ID_QUERY_PARAMETER));
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals("application/json", mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }
}
