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

import mil.army.usace.hec.cwms.radar.client.model.LocationLevel;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static mil.army.usace.hec.cwms.radar.client.controllers.LocationLevelEndpointInput.*;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.*;
import static mil.army.usace.hec.cwms.radar.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TestLocationLevelEndpointInput {

    @Test
    void testQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        ZoneId zoneId = ZoneId.of("America/Los_Angeles");
        Instant begin = ZonedDateTime.of(2015, 1, 1, 0, 0, 0, 0, zoneId).toInstant();
        Instant end = begin.plusSeconds(30);
        LocationLevelEndpointInput.GetAll input = LocationLevelEndpointInput.getAll()
                .officeId("SWT")
                .levelIdMask("MASK")
                .page("abc")
                .pageSize(10)
                .begin(begin)
                .end(end)
                .unit("EN");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals("MASK", mockHttpRequestBuilder.getQueryParameter(LEVEL_ID_MASK_QUERY_PARAMETER));
        assertEquals("10", mockHttpRequestBuilder.getQueryParameter(PAGE_SIZE_QUERY_PARAMETER));
        assertEquals("abc", mockHttpRequestBuilder.getQueryParameter(PAGE_QUERY_PARAMETER));
        assertEquals("2015-01-01T08:00:00Z", mockHttpRequestBuilder.getQueryParameter(BEGIN_QUERY_PARAMETER));
        assertEquals("2015-01-01T08:00:30Z", mockHttpRequestBuilder.getQueryParameter(END_QUERY_PARAMETER));
        assertEquals("EN", mockHttpRequestBuilder.getQueryParameter(UNIT_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testQueryRequestNulls() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        LocationLevelEndpointInput.GetAll input = LocationLevelEndpointInput.getAll();
        input.addInputParameters(mockHttpRequestBuilder);
        assertNull(mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals("*", mockHttpRequestBuilder.getQueryParameter(LEVEL_ID_MASK_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(PAGE_SIZE_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(PAGE_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(BEGIN_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(END_QUERY_PARAMETER));
        assertEquals("SI", mockHttpRequestBuilder.getQueryParameter(UNIT_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGet() {
        Instant now = Instant.now();
        LocationLevelEndpointInput.GetOne input = LocationLevelEndpointInput.getOne("Test", "SWT", now);
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals(now.toString(), mockHttpRequestBuilder.getQueryParameter(EFFECTIVE_DATE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPost() throws Exception {
        String json = readJsonFile("radar/v2/json/location_level.json");
        LocationLevel level = RadarObjectMapper.mapJsonToObject(json, LocationLevel.class);
        LocationLevelEndpointInput.Post input = LocationLevelEndpointInput.post(level);
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDelete() throws Exception {
        LocationLevelEndpointInput.Delete input = LocationLevelEndpointInput.delete("Test")
                .officeId("SPK");
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
        assertEquals("SPK", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
    }

    @Test
    void testGetAsTimeSeries() {
        Instant begin = Instant.now();
        Instant end = begin.plus(10, ChronoUnit.DAYS);
        GetTimeSeries input = getAsTimeSeries("Test", "SPK", begin, end)
                .interval("1Day");
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
        assertEquals("SPK", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals(begin.toString(), mockHttpRequestBuilder.getQueryParameter(BEGIN_QUERY_PARAMETER));
        assertEquals(end.toString(), mockHttpRequestBuilder.getQueryParameter(END_QUERY_PARAMETER));
        assertEquals("1Day", mockHttpRequestBuilder.getQueryParameter(INTERVAL_QUERY_PARAMETER));
    }
}
