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

package mil.army.usace.hec.cwms.data.api.client.controllers.timeseriesgroup;

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_JSON;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TestController.readJsonFile;
import static mil.army.usace.hec.cwms.data.api.client.controllers.timeseriesgroup.TimeSeriesGroupEndpointInput.CASCADE_DELETE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.timeseriesgroup.TimeSeriesGroupEndpointInput.CATEGORY_ID_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.timeseriesgroup.TimeSeriesGroupEndpointInput.CATEGORY_MASK_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.timeseriesgroup.TimeSeriesGroupEndpointInput.CATEGORY_OFFICE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.timeseriesgroup.TimeSeriesGroupEndpointInput.FAIL_IF_EXISTS;
import static mil.army.usace.hec.cwms.data.api.client.controllers.timeseriesgroup.TimeSeriesGroupEndpointInput.GROUP_MASK_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.timeseriesgroup.TimeSeriesGroupEndpointInput.GROUP_OFFICE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.timeseriesgroup.TimeSeriesGroupEndpointInput.INCLUDE_ASSIGNED_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.timeseriesgroup.TimeSeriesGroupEndpointInput.OFFICE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.timeseriesgroup.TimeSeriesGroupEndpointInput.REPLACE_ASSIGNED_TS_QUERY_PARAMETER;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import mil.army.usace.hec.cwms.data.api.client.controllers.MockHttpRequestBuilder;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.data.api.client.model.TimeSeriesGroup;
import org.junit.jupiter.api.Test;

class TestTimeSeriesGroupEndpointV1Input {

    @Test
    void testGetOne() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        TimeSeriesGroupEndpointV1Input.GetOne input = TimeSeriesGroupEndpointV1Input
            .getOne("category-id", "group-id", "SWT", "SWT", "CWMS");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("category-id", mockHttpRequestBuilder.getQueryParameter(CATEGORY_ID_QUERY_PARAMETER));
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(GROUP_OFFICE_QUERY_PARAMETER));
        assertEquals("CWMS", mockHttpRequestBuilder.getQueryParameter(CATEGORY_OFFICE_QUERY_PARAMETER));
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_JSON, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetAll() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        TimeSeriesGroupEndpointV1Input.GetAll input = TimeSeriesGroupEndpointV1Input.getAll()
                .officeId("SWT")
                .groupOfficeId("SWT")
                .categoryOfficeId("SWT")
                .timeSeriesGroupMask("mask")
                .timeSeriesCategoryMask("mask")
                .includeAssigned(false);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(GROUP_OFFICE_QUERY_PARAMETER));
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(CATEGORY_OFFICE_QUERY_PARAMETER));
        assertEquals("mask", mockHttpRequestBuilder.getQueryParameter(GROUP_MASK_QUERY_PARAMETER));
        assertEquals("mask", mockHttpRequestBuilder.getQueryParameter(CATEGORY_MASK_QUERY_PARAMETER));
        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(INCLUDE_ASSIGNED_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_JSON, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPost() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v1/json/ts_group.json");
        TimeSeriesGroup timeSeriesGroup = RadarObjectMapper.mapJsonToObject(collect, TimeSeriesGroup.class);
        TimeSeriesGroupEndpointV1Input.Post input = TimeSeriesGroupEndpointV1Input.post(timeSeriesGroup)
                .failIfExists(true);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(FAIL_IF_EXISTS));
        assertEquals(ACCEPT_HEADER_JSON, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDelete() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        TimeSeriesGroupEndpointV1Input.Delete input = TimeSeriesGroupEndpointV1Input
                .delete("category-id", "group-id", "SWT")
                .cascadeDelete(true);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("category-id", mockHttpRequestBuilder.getQueryParameter(CATEGORY_ID_QUERY_PARAMETER));
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(CASCADE_DELETE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_JSON, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPatch() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v1/json/ts_group.json");
        TimeSeriesGroup timeSeriesGroup = RadarObjectMapper.mapJsonToObject(collect, TimeSeriesGroup.class);
        TimeSeriesGroupEndpointV1Input.Patch input = TimeSeriesGroupEndpointV1Input
                .patch("SWT", "group-id", timeSeriesGroup).replaceAssignedTs(true);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(REPLACE_ASSIGNED_TS_QUERY_PARAMETER));
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_JSON, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }
}
