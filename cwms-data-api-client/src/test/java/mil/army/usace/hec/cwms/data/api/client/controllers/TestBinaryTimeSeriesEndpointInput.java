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

package mil.army.usace.hec.cwms.data.api.client.controllers;

import mil.army.usace.hec.cwms.data.api.client.model.BinaryTimeSeries;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static mil.army.usace.hec.cwms.data.api.client.controllers.BinaryTimeSeriesEndpointInput.Delete.BEGIN_PARAMETER_QUERY;
import static mil.army.usace.hec.cwms.data.api.client.controllers.BinaryTimeSeriesEndpointInput.Delete.END_PARAMETER_QUERY;
import static mil.army.usace.hec.cwms.data.api.client.controllers.BinaryTimeSeriesEndpointInput.GetAll.BEGIN_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.BinaryTimeSeriesEndpointInput.GetAll.BINARY_TYPE_MASK_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.BinaryTimeSeriesEndpointInput.GetAll.END_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.BinaryTimeSeriesEndpointInput.GetAll.NAME_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.BinaryTimeSeriesEndpointInput.GetAll.PAGE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.BinaryTimeSeriesEndpointInput.GetAll.PAGE_SIZE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.BinaryTimeSeriesEndpointInput.GetAll.VERSION_DATE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.BinaryTimeSeriesEndpointInput.Post.REPLACE_ALL_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestBinaryTimeSeriesEndpointInput {

    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        Instant start = ZonedDateTime.of(2018, 1, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant end = ZonedDateTime.of(2018, 2, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant versionDate = ZonedDateTime.of(2018, 1, 12, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        BinaryTimeSeriesEndpointInput.GetAll input = BinaryTimeSeriesEndpointInput.getAll("arbu.Binary.Inst.1Hour.0.Ccp-Rev", "SWT", start, end)
                .pageSize(10)
                .versionDate(versionDate)
                .binaryTypeMask("image/png")
                .page("page");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(BinaryTimeSeriesEndpointInput.GetAll.OFFICE_QUERY_PARAMETER));
        assertEquals(start.toString(), mockHttpRequestBuilder.getQueryParameter(BEGIN_QUERY_PARAMETER));
        assertEquals(end.toString(), mockHttpRequestBuilder.getQueryParameter(END_QUERY_PARAMETER));
        assertEquals(versionDate.toString(), mockHttpRequestBuilder.getQueryParameter(VERSION_DATE_QUERY_PARAMETER));
        assertEquals(Integer.toString(10), mockHttpRequestBuilder.getQueryParameter(PAGE_SIZE_QUERY_PARAMETER));
        assertEquals("page", mockHttpRequestBuilder.getQueryParameter(PAGE_QUERY_PARAMETER));
        assertEquals("arbu.Binary.Inst.1Hour.0.Ccp-Rev", mockHttpRequestBuilder.getQueryParameter(NAME_QUERY_PARAMETER));
        assertEquals("image/png", mockHttpRequestBuilder.getQueryParameter(BINARY_TYPE_MASK_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testNullRequiredFields() {
        assertThrows(NullPointerException.class, () -> BinaryTimeSeriesEndpointInput.getAll(null, "SWT", Instant.now(), Instant.now())
                .pageSize(10));

        assertThrows(NullPointerException.class, () -> BinaryTimeSeriesEndpointInput.getAll("arbu.Binary.Inst.1Hour.0.Ccp-Rev", null, Instant.now(), Instant.now())
                .pageSize(10));

        assertThrows(NullPointerException.class, () -> BinaryTimeSeriesEndpointInput.getAll("arbu.Binary.Inst.1Hour.0.Ccp-Rev", "SWT", null, Instant.now())
                .pageSize(10));

        assertThrows(NullPointerException.class, () -> BinaryTimeSeriesEndpointInput.getAll("arbu.Binary.Inst.1Hour.0.Ccp-Rev", "SWT", Instant.now(), null)
                .pageSize(10));
    }

    @Test
    void testPostQueryRequestDefaults() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v2/json/binarytimeseries.json");
        BinaryTimeSeries binaryTimeSeries = RadarObjectMapper.mapJsonToObject(collect, BinaryTimeSeries.class);
        BinaryTimeSeriesEndpointInput.Post input = BinaryTimeSeriesEndpointInput.post(binaryTimeSeries);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(binaryTimeSeries, input.timeSeries());
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v2/json/binarytimeseries.json");
        BinaryTimeSeries binaryTimeSeries = RadarObjectMapper.mapJsonToObject(collect, BinaryTimeSeries.class);
        BinaryTimeSeriesEndpointInput.Post input = BinaryTimeSeriesEndpointInput.post(binaryTimeSeries)
                .replaceAll(true);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(REPLACE_ALL_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostNullTimeSeries() {
        assertThrows(NullPointerException.class, () -> BinaryTimeSeriesEndpointInput.post(null));
    }

    @Test
    void testDeleteQueryRequestDefaults() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        BinaryTimeSeriesEndpointInput.Delete input = BinaryTimeSeriesEndpointInput.delete("arbu.Binary.Inst.1Hour.0.Ccp-Rev", "SWT");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("arbu.Binary.Inst.1Hour.0.Ccp-Rev", input.timeSeriesId());
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(BinaryTimeSeriesEndpointInput.Delete.OFFICE_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(BEGIN_PARAMETER_QUERY));
        assertNull(mockHttpRequestBuilder.getQueryParameter(END_PARAMETER_QUERY));
        assertNull(mockHttpRequestBuilder.getQueryParameter(VERSION_DATE_QUERY_PARAMETER));
        assertEquals("*", mockHttpRequestBuilder.getQueryParameter(BinaryTimeSeriesEndpointInput.Delete.BINARY_TYPE_MASK_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        Instant start = ZonedDateTime.of(2018, 1, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant end = ZonedDateTime.of(2018, 2, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant versionDate = ZonedDateTime.of(2018, 1, 12, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        BinaryTimeSeriesEndpointInput.Delete input = BinaryTimeSeriesEndpointInput.delete("arbu.Binary.Inst.1Hour.0.Ccp-Rev", "SWT")
                .begin(start)
                .end(end)
                .versionDate(versionDate)
                .binaryTypeMask("Hello, World");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(BinaryTimeSeriesEndpointInput.Delete.OFFICE_QUERY_PARAMETER));
        assertEquals(start.toString(), mockHttpRequestBuilder.getQueryParameter(BEGIN_PARAMETER_QUERY));
        assertEquals(end.toString(), mockHttpRequestBuilder.getQueryParameter(END_PARAMETER_QUERY));
        assertEquals(versionDate.toString(), mockHttpRequestBuilder.getQueryParameter(VERSION_DATE_QUERY_PARAMETER));
        assertEquals("Hello, World", mockHttpRequestBuilder.getQueryParameter(BinaryTimeSeriesEndpointInput.Delete.BINARY_TYPE_MASK_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNullTimeSeriesId() {
        assertThrows(NullPointerException.class, () -> BinaryTimeSeriesEndpointInput.delete(null, null));
    }

    @Test
    void testDeleteNullOffice() {
        assertThrows(NullPointerException.class, () -> BinaryTimeSeriesEndpointInput.delete("", null));
    }

}
