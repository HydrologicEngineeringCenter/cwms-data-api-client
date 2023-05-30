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

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_XML_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RatingEndpointInput.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestRatingEndpointInput {

    @Test
    void testGetOne() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String ratingId = "BUFF.Stage;Flow.WCDS.Production";

        ZonedDateTime begin = ZonedDateTime.of(2018, 1, 1, 1, 1, 1, 1, ZoneId.of("UTC"));
        ZonedDateTime end = ZonedDateTime.of(2019, 1, 1, 1, 1, 1, 1, ZoneId.of("UTC"));
        RatingEndpointInput.GetOne input = RatingEndpointInput.getOne(ratingId, "SWT")
                .begin(begin.toInstant())
                .end(end.toInstant())
                .lazy();

        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals("2018-01-01T01:01:01.000000001Z", mockHttpRequestBuilder.getQueryParameter(BEGIN_QUERY_PARAMETER));
        assertEquals("2019-01-01T01:01:01.000000001Z", mockHttpRequestBuilder.getQueryParameter(END_QUERY_PARAMETER));
        assertEquals(ACCEPT_XML_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPost() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        RatingEndpointInput.Post input = RatingEndpointInput.post("xml");

        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(ACCEPT_XML_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDelete() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String ratingId = "BUFF.Stage;Flow.WCDS.Production";
        ZonedDateTime begin = ZonedDateTime.of(2018, 1, 1, 1, 1, 1, 1, ZoneId.of("UTC"));
        ZonedDateTime end = ZonedDateTime.of(2019, 1, 1, 1, 1, 1, 1, ZoneId.of("UTC"));
        RatingEndpointInput.Delete input = RatingEndpointInput.delete(ratingId, "SWT", begin.toInstant(), end.toInstant());

        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals("2018-01-01T01:01:01.000000001Z", mockHttpRequestBuilder.getQueryParameter(BEGIN_QUERY_PARAMETER));
        assertEquals("2019-01-01T01:01:01.000000001Z", mockHttpRequestBuilder.getQueryParameter(END_QUERY_PARAMETER));
        assertEquals(ACCEPT_XML_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }
}
