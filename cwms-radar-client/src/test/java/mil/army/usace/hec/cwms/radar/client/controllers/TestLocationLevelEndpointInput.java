/*
 * MIT License
 *
 * Copyright (c) 2022 Hydrologic Engineering Center
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;

class TestLocationLevelEndpointInput {

    @Test
    void testQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        ZoneId zoneId = ZoneId.of("America/Los_Angeles");
        Instant begin = ZonedDateTime.of(2015, 1, 1, 0, 0, 0, 0, zoneId).toInstant();
        Instant end = begin.plusSeconds(30);
        LocationLevelEndpointInput input = new LocationLevelEndpointInput()
            .officeId("SWT")
            .levelIdMask("MASK")
            .page("abc")
            .pageSize(10)
            .begin(begin)
            .end(end)
            .unit("EN")
            .timeZone(zoneId);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(LocationLevelEndpointInput.OFFICE_QUERY_PARAMETER));
        assertEquals("MASK", mockHttpRequestBuilder.getQueryParameter(LocationLevelEndpointInput.LEVEL_ID_MASK_QUERY_PARAMETER));
        assertEquals("10", mockHttpRequestBuilder.getQueryParameter(LocationLevelEndpointInput.PAGE_SIZE_QUERY_PARAMETER));
        assertEquals("abc", mockHttpRequestBuilder.getQueryParameter(LocationLevelEndpointInput.PAGE_QUERY_PARAMETER));
        assertEquals("2015-01-01T08:00:00Z", mockHttpRequestBuilder.getQueryParameter(LocationLevelEndpointInput.BEGIN_QUERY_PARAMETER));
        assertEquals("2015-01-01T08:00:30Z", mockHttpRequestBuilder.getQueryParameter(LocationLevelEndpointInput.END_QUERY_PARAMETER));
        assertEquals("EN", mockHttpRequestBuilder.getQueryParameter(LocationLevelEndpointInput.UNIT_QUERY_PARAMETER));
        assertEquals(zoneId.getId(), mockHttpRequestBuilder.getQueryParameter(LocationLevelEndpointInput.TIMEZONE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testQueryRequestNulls() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        LocationLevelEndpointInput input = new LocationLevelEndpointInput();
        input.addInputParameters(mockHttpRequestBuilder);
        assertNull(mockHttpRequestBuilder.getQueryParameter(LocationLevelEndpointInput.OFFICE_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(LocationLevelEndpointInput.LEVEL_ID_MASK_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(LocationLevelEndpointInput.PAGE_SIZE_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(LocationLevelEndpointInput.PAGE_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(LocationLevelEndpointInput.BEGIN_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(LocationLevelEndpointInput.END_QUERY_PARAMETER));
        assertEquals("SI", mockHttpRequestBuilder.getQueryParameter(LocationLevelEndpointInput.UNIT_QUERY_PARAMETER));
        assertEquals("UTC", mockHttpRequestBuilder.getQueryParameter(LocationLevelEndpointInput.TIMEZONE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }
}
