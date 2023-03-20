/*
 * MIT License
 *
 * Copyright (c) 2023 Hydrologic Engineering Center
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

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TestController.readJsonFile;
import static mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesIdentifierEndpointInput.Delete.METHOD_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesIdentifierEndpointInput.GetOne.OFFICE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesIdentifierEndpointInput.Patch.INTERVAL_OFFSET_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesIdentifierEndpointInput.Patch.TIMESERIES_ID_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesIdentifierEndpointInput.Post.FAIL_IF_EXISTS_QUERY_PARAMETER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesIdentifierDescriptor;
import org.junit.jupiter.api.Test;

class TestTimeSeriesIdentifierEndpointInput {

    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        TimeSeriesIdentifierEndpointInput.GetOne input = TimeSeriesIdentifierEndpointInput.getOne("arbu.Elev.Inst.1Hour.0.Ccp-Rev", "SWT");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetOneNullTimeSeriesId() {
        assertThrows(NullPointerException.class, () -> TimeSeriesIdentifierEndpointInput.getOne(null, null));
    }

    @Test
    void testGetOneNullOfficeId() {
        assertThrows(NullPointerException.class, () -> TimeSeriesIdentifierEndpointInput.getOne("", null));
    }

    @Test
    void testPostQueryRequestDefaults() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v2/json/timeseriesidentifier.json");
        TimeSeriesIdentifierDescriptor timeSeries = RadarObjectMapper.mapJsonToObject(collect, TimeSeriesIdentifierDescriptor.class);
        TimeSeriesIdentifierEndpointInput.Post input = TimeSeriesIdentifierEndpointInput.post(timeSeries);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(timeSeries, input.timeSeriesIdentifierDescriptor());
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v2/json/timeseriesidentifier.json");
        TimeSeriesIdentifierDescriptor timeSeries = RadarObjectMapper.mapJsonToObject(collect, TimeSeriesIdentifierDescriptor.class);
        Instant now = Instant.now();
        TimeSeriesIdentifierEndpointInput.Post input = TimeSeriesIdentifierEndpointInput.post(timeSeries)
            .failIfExists(false);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostNullTimeSeries() {
        assertThrows(NullPointerException.class, () -> TimeSeriesIdentifierEndpointInput.post(null));
    }

    @Test
    void testDeleteQueryRequestDefaults() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        TimeSeriesIdentifierEndpointInput.Delete input = TimeSeriesIdentifierEndpointInput.delete("arbu.Elev.Inst.1Hour.0.Ccp-Rev", "SWT");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("arbu.Elev.Inst.1Hour.0.Ccp-Rev", input.timeSeriesId());
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals(DeleteMethod.ALL.toString(), mockHttpRequestBuilder.getQueryParameter(METHOD_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        Instant start = ZonedDateTime.of(2018, 1, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant end = ZonedDateTime.of(2018, 2, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant now = Instant.now();
        TimeSeriesIdentifierEndpointInput.Delete input = TimeSeriesIdentifierEndpointInput.delete("arbu.Elev.Inst.1Hour.0.Ccp-Rev", "SWT")
            .method(DeleteMethod.DATA);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals(DeleteMethod.DATA.toString(), mockHttpRequestBuilder.getQueryParameter(METHOD_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNullTimeSeriesId() {
        assertThrows(NullPointerException.class, () -> TimeSeriesIdentifierEndpointInput.delete(null, null));
    }

    @Test
    void testDeleteNullOffice() {
        assertThrows(NullPointerException.class, () -> TimeSeriesIdentifierEndpointInput.delete("", null));
    }
    @Test
    void testPatchQueryRequestDefaults() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        TimeSeriesIdentifierEndpointInput.Patch input = TimeSeriesIdentifierEndpointInput.patch("arbu.Elev.Inst.1Hour.0.Ccp-Rev","arbu.Elev.Inst.1Hour.0.Ccp-Rev-new", "SWT");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("arbu.Elev.Inst.1Hour.0.Ccp-Rev", input.originalIdentifier());
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(INTERVAL_OFFSET_QUERY_PARAMETER));
        assertEquals("arbu.Elev.Inst.1Hour.0.Ccp-Rev-new", mockHttpRequestBuilder.getQueryParameter(TIMESERIES_ID_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPatchQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        Instant start = ZonedDateTime.of(2018, 1, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant end = ZonedDateTime.of(2018, 2, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant now = Instant.now();
        TimeSeriesIdentifierEndpointInput.Patch input = TimeSeriesIdentifierEndpointInput.patch("arbu.Elev.Inst.1Hour.0.Ccp-Rev","arbu.Elev.Inst.1Hour.0.Ccp-Rev-new", "SWT")
            .intervalOffsetMinutes(500);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("arbu.Elev.Inst.1Hour.0.Ccp-Rev", input.originalIdentifier());
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals("500", mockHttpRequestBuilder.getQueryParameter(INTERVAL_OFFSET_QUERY_PARAMETER));
        assertEquals("arbu.Elev.Inst.1Hour.0.Ccp-Rev-new", mockHttpRequestBuilder.getQueryParameter(TIMESERIES_ID_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPatchNullOriginalTimeSeriesId() {
        assertThrows(NullPointerException.class, () -> TimeSeriesIdentifierEndpointInput.patch(null, null, null));
    }

    @Test
    void testPatchNullNewTimeSeriesId() {
        assertThrows(NullPointerException.class, () -> TimeSeriesIdentifierEndpointInput.patch("", null, null));
    }

    @Test
    void testPatchNullOffice() {
        assertThrows(NullPointerException.class, () -> TimeSeriesIdentifierEndpointInput.patch("", "", null));
    }

}
