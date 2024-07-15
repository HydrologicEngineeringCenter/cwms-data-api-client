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

package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TestController.readJsonFile;
import static mil.army.usace.hec.cwms.radar.client.controllers.TurbineChangeEndpointInput.Post;
import static mil.army.usace.hec.cwms.radar.client.controllers.TurbineChangeEndpointInput.delete;
import static mil.army.usace.hec.cwms.radar.client.controllers.TurbineChangeEndpointInput.getAll;
import static mil.army.usace.hec.cwms.radar.client.controllers.TurbineChangeEndpointInput.post;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import mil.army.usace.hec.cwms.radar.client.controllers.TurbineChangeEndpointInput.Delete;
import mil.army.usace.hec.cwms.radar.client.controllers.TurbineChangeEndpointInput.GetAll;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.TurbineChange;
import org.junit.jupiter.api.Test;

class TestTurbineChangeEndpointInput {

    @Test
    void testGetAllQueryRequestDefault() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        Instant begin = Instant.now();
        Instant end = begin.plus(5, ChronoUnit.DAYS);
        GetAll input = getAll("SPK", "PROJ", begin, end);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(begin.toString(), mockHttpRequestBuilder.getQueryParameter(GetAll.BEGIN_QUERY_PARAMETER));
        assertEquals(end.toString(), mockHttpRequestBuilder.getQueryParameter(GetAll.END_QUERY_PARAMETER));
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(GetAll.START_INCLUSIVE_QUERY_PARAMETER));
        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(GetAll.END_INCLUSIVE_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(GetAll.PAGE_SIZE_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(GetAll.UNIT_SYSTEM_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetAllNullTimeSeriesId() {
        assertThrows(NullPointerException.class, () -> getAll(null, null, null, null));
        assertThrows(NullPointerException.class, () -> getAll("SPK", null, null, null));
        assertThrows(NullPointerException.class, () -> getAll("SPK", "PROJ", null, null));
        assertThrows(NullPointerException.class, () -> getAll("SPK", "PROJ", Instant.now(), null));
    }

    @Test
    void testGetAllQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        Instant begin = Instant.now();
        Instant end = begin.plus(5, ChronoUnit.DAYS);
        GetAll input = getAll("SPK", "PROJ", begin, end)
            .endTimeInclusive(true)
            .startTimeInclusive(false)
            .pageSize(50)
            .unitSystem("EN");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(begin.toString(), mockHttpRequestBuilder.getQueryParameter(GetAll.BEGIN_QUERY_PARAMETER));
        assertEquals(end.toString(), mockHttpRequestBuilder.getQueryParameter(GetAll.END_QUERY_PARAMETER));
        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(GetAll.START_INCLUSIVE_QUERY_PARAMETER));
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(GetAll.END_INCLUSIVE_QUERY_PARAMETER));
        assertEquals("50", mockHttpRequestBuilder.getQueryParameter(GetAll.PAGE_SIZE_QUERY_PARAMETER));
        assertEquals("EN", mockHttpRequestBuilder.getQueryParameter(GetAll.UNIT_SYSTEM_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v1/json/turbine-changes.json");
        List<TurbineChange> changes = RadarObjectMapper.mapJsonToListOfObjects(collect, TurbineChange.class);
        Post input = post("SPK", "PROJ", changes)
            .overrideProtection(true);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(changes, input.changes());
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(Post.OVERRIDE_PROTECTION_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostNullTimeSeries() {
        assertThrows(NullPointerException.class, () -> post(null, null, null));
        assertThrows(NullPointerException.class, () -> post("SPK", null, null));
        assertThrows(NullPointerException.class, () -> post("SPK", "PROJ", null));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String projectId = "PROJ";
        String office = "SPK";
        Instant begin = Instant.now();
        Instant end = begin.plus(5, ChronoUnit.DAYS);
        Delete input = delete(office, projectId, begin, end)
            .overrideProtection(true);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(Delete.OVERRIDE_PROTECTION_QUERY_PARAMETER));
        assertEquals(begin.toString(), mockHttpRequestBuilder.getQueryParameter(Delete.BEGIN_QUERY_PARAMETER));
        assertEquals(end.toString(), mockHttpRequestBuilder.getQueryParameter(Delete.END_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNulls() {
        assertThrows(NullPointerException.class, () -> delete(null, null, null, null));
        assertThrows(NullPointerException.class, () -> delete("SPK", null, null, null));
        assertThrows(NullPointerException.class, () -> delete("SPK", "PROJ", null, null));
        assertThrows(NullPointerException.class, () -> delete("SPK", "PROJ", Instant.now(), null));
    }

}
