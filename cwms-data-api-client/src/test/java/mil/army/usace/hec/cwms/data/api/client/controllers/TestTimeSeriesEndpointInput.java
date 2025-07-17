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

import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.data.api.client.model.TimeSeries;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TestController.readJsonFile;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesEndpointInput.Delete.BEGIN_PARAMETER_QUERY;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesEndpointInput.Delete.END_PARAMETER_QUERY;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesEndpointInput.Delete.END_TIME_INCLUSIVE_PARAMETER_QUERY;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesEndpointInput.Delete.MAX_VERSION_PARAMETER_QUERY;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesEndpointInput.Delete.OVERRIDE_PROTECTION_PARAMETER_QUERY;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesEndpointInput.Delete.START_TIME_INCLUSIVE_PARAMETER_QUERY;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesEndpointInput.Delete.VERSION_DATE_PARAMETER_QUERY;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesEndpointInput.GetOne.BEGIN_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesEndpointInput.GetOne.DATUM_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesEndpointInput.GetOne.END_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesEndpointInput.GetOne.NAME_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesEndpointInput.GetOne.OFFICE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesEndpointInput.GetOne.PAGE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesEndpointInput.GetOne.PAGE_SIZE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesEndpointInput.GetOne.TIMEZONE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesEndpointInput.GetOne.TRIM_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesEndpointInput.GetOne.UNIT_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesEndpointInput.Post.CREATE_AS_LRTS_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesEndpointInput.Post.OVERRIDE_PROTECTION_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesEndpointInput.Post.STORE_RULE_PARAMETER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestTimeSeriesEndpointInput {

    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        Instant start = ZonedDateTime.of(2018, 1, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant end = ZonedDateTime.of(2018, 2, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        TimeSeriesEndpointInput.GetOne input = TimeSeriesEndpointInput.getOne("arbu.Elev.Inst.1Hour.0.Ccp-Rev")
            .officeId("SWT")
            .unit("SI")
            .verticalDatum("NAVD88")
            .begin(start)
            .end(end)
            .timeZone(ZoneId.of("UTC"))
            .pageSize(10)
                .page("page")
                .trim(true);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals("SI", mockHttpRequestBuilder.getQueryParameter(UNIT_QUERY_PARAMETER));
        assertEquals("NAVD88", mockHttpRequestBuilder.getQueryParameter(DATUM_QUERY_PARAMETER));
        assertEquals(start.toString(), mockHttpRequestBuilder.getQueryParameter(BEGIN_QUERY_PARAMETER));
        assertEquals(end.toString(), mockHttpRequestBuilder.getQueryParameter(END_QUERY_PARAMETER));
        assertEquals("UTC", mockHttpRequestBuilder.getQueryParameter(TIMEZONE_QUERY_PARAMETER));
        assertEquals(Integer.toString(10), mockHttpRequestBuilder.getQueryParameter(PAGE_SIZE_QUERY_PARAMETER));
        assertEquals("page", mockHttpRequestBuilder.getQueryParameter(PAGE_QUERY_PARAMETER));
        assertEquals("arbu.Elev.Inst.1Hour.0.Ccp-Rev", mockHttpRequestBuilder.getQueryParameter(NAME_QUERY_PARAMETER));
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(TRIM_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetOneNullTimeSeriesId() {
        assertThrows(NullPointerException.class, () -> TimeSeriesEndpointInput.getOne(null));
    }

    @Test
    void testPostQueryRequestDefaults() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v2/json/timeseries.json");
        TimeSeries timeSeries = RadarObjectMapper.mapJsonToObject(collect, TimeSeries.class);
        TimeSeriesEndpointInput.Post input = TimeSeriesEndpointInput.post(timeSeries);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(timeSeries, input.timeSeries());
        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(CREATE_AS_LRTS_QUERY_PARAMETER));
        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(OVERRIDE_PROTECTION_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(STORE_RULE_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v2/json/timeseries.json");
        TimeSeries timeSeries = RadarObjectMapper.mapJsonToObject(collect, TimeSeries.class);
        Instant now = Instant.now();
        TimeSeriesEndpointInput.Post input = TimeSeriesEndpointInput.post(timeSeries)
            .createAsLrts(true)
            .storeRule("DELETE_INSERT")
            .overrideProtection(true);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(CREATE_AS_LRTS_QUERY_PARAMETER));
        assertEquals("DELETE_INSERT", mockHttpRequestBuilder.getQueryParameter(STORE_RULE_PARAMETER));
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(OVERRIDE_PROTECTION_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostNullTimeSeries() {
        assertThrows(NullPointerException.class, () -> TimeSeriesEndpointInput.post(null));
    }

    @Test
    void testDeleteQueryRequestDefaults() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        TimeSeriesEndpointInput.Delete input = TimeSeriesEndpointInput.delete("arbu.Elev.Inst.1Hour.0.Ccp-Rev", "SWT");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("arbu.Elev.Inst.1Hour.0.Ccp-Rev", input.timeSeriesId());
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(BEGIN_PARAMETER_QUERY));
        assertNull(mockHttpRequestBuilder.getQueryParameter(END_PARAMETER_QUERY));
        assertNull(mockHttpRequestBuilder.getQueryParameter(VERSION_DATE_PARAMETER_QUERY));
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(START_TIME_INCLUSIVE_PARAMETER_QUERY));
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(END_TIME_INCLUSIVE_PARAMETER_QUERY));
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(MAX_VERSION_PARAMETER_QUERY));
        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(OVERRIDE_PROTECTION_PARAMETER_QUERY));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        Instant start = ZonedDateTime.of(2018, 1, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant end = ZonedDateTime.of(2018, 2, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant now = Instant.now();
        TimeSeriesEndpointInput.Delete input = TimeSeriesEndpointInput.delete("arbu.Elev.Inst.1Hour.0.Ccp-Rev", "SWT")
            .begin(start)
            .end(end)
            .startTimeInclusive(false)
            .endTimeInclusive(false)
            .versionDate(now)
            .maxVersion(false)
            .overrideProtection(true);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals(start.toString(), mockHttpRequestBuilder.getQueryParameter(BEGIN_PARAMETER_QUERY));
        assertEquals(end.toString(), mockHttpRequestBuilder.getQueryParameter(END_PARAMETER_QUERY));
        assertEquals(now.toString(), mockHttpRequestBuilder.getQueryParameter(VERSION_DATE_PARAMETER_QUERY));
        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(START_TIME_INCLUSIVE_PARAMETER_QUERY));
        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(END_TIME_INCLUSIVE_PARAMETER_QUERY));
        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(MAX_VERSION_PARAMETER_QUERY));
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(OVERRIDE_PROTECTION_PARAMETER_QUERY));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNullTimeSeriesId() {
        assertThrows(NullPointerException.class, () -> TimeSeriesEndpointInput.delete(null, null));
    }

    @Test
    void testDeleteNullOffice() {
        assertThrows(NullPointerException.class, () -> TimeSeriesEndpointInput.delete("", null));
    }

}
