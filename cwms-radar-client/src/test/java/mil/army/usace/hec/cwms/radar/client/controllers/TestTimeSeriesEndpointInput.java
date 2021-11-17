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
import static mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesEndpointInput.BEGIN_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesEndpointInput.DATUM_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesEndpointInput.END_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesEndpointInput.NAME_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesEndpointInput.OFFICE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesEndpointInput.PAGE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesEndpointInput.PAGE_SIZE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesEndpointInput.TIMEZONE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TimeSeriesEndpointInput.UNIT_QUERY_PARAMETER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;

class TestTimeSeriesEndpointInput {

    @Test
    void testQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        Instant start = ZonedDateTime.of(2018, 1, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant end = ZonedDateTime.of(2018, 2, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        TimeSeriesEndpointInput input = new TimeSeriesEndpointInput("arbu.Elev.Inst.1Hour.0.Ccp-Rev")
            .officeId("SWT")
            .unit("SI")
            .verticalDatum("NAVD88")
            .begin(start)
            .end(end)
            .timeZone(ZoneId.of("UTC"))
            .pageSize(10)
            .page("page");
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
        assertEquals("application/json;version=2", mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testNullTimeSeriesId() {
        assertThrows(NullPointerException.class, () -> new TimeSeriesEndpointInput(null));
    }
}
