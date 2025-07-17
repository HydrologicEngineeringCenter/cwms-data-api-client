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
import mil.army.usace.hec.cwms.data.api.client.model.StreamReach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static mil.army.usace.hec.cwms.data.api.client.controllers.StreamReachEndpointInput.Post.FAIL_IF_EXISTS_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestStreamReachEndpointInput {

    @Test
    void testGetAllQueryRequestDefault() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        StreamReachEndpointInput.GetAll input = StreamReachEndpointInput.getAll()
                .withOfficeIdMask("SPK")
                .withStationUnits("km");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SPK", mockHttpRequestBuilder.getQueryParameter(StreamReachEndpointInput.GetAll.OFFICE_MASK_QUERY_PARAMETER));
        assertEquals("km", mockHttpRequestBuilder.getQueryParameter(StreamReachEndpointInput.GetAll.STATION_UNITS_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String streamId = "STREAM";
        String reachId = "REACH";
        String office = "SPK";
        StreamReachEndpointInput.GetOne input = StreamReachEndpointInput.getOne(office, streamId, reachId)
                .withStationUnits("km");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(reachId, input.reachId());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(StreamReachEndpointInput.GetOne.OFFICE_QUERY_PARAMETER));
        assertEquals(streamId, mockHttpRequestBuilder.getQueryParameter(StreamReachEndpointInput.GetOne.STREAM_ID_QUERY_PARAMETER));
        assertEquals("km", mockHttpRequestBuilder.getQueryParameter(StreamReachEndpointInput.GetOne.STATION_UNITS_QUERY_PARAMETER));
    }

    @Test
    void testGetOneNullStreamOrReachId() {
        assertThrows(NullPointerException.class, () -> StreamReachEndpointInput.getOne("", null, ""));
        assertThrows(NullPointerException.class, () -> StreamReachEndpointInput.getOne(null, "", ""));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v1/json/stream_reach.json");
        StreamReach streamReach = RadarObjectMapper.mapJsonToObject(collect, StreamReach.class);
        StreamReachEndpointInput.Post input = StreamReachEndpointInput.post(streamReach)
                .failIfExists(false);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(streamReach, input.streamReach());
        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostNullStreamReach() {
        assertThrows(NullPointerException.class, () -> StreamReachEndpointInput.post(null));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String reachId = "REACH";
        String office = "SPK";
        StreamReachEndpointInput.Delete input = StreamReachEndpointInput.delete(office, reachId);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(reachId, input.reachId());
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(StreamReachEndpointInput.Delete.OFFICE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNulls() {
        assertThrows(NullPointerException.class, () -> StreamReachEndpointInput.delete("", null));
        assertThrows(NullPointerException.class, () -> StreamReachEndpointInput.delete(null, ""));
    }

    @Test
    void testPatch() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String oldReachId = "REACH";
        String newReachId = "REACH_NEW";
        String office = "SPK";
        StreamReachEndpointInput.Patch input = StreamReachEndpointInput.patch(office, oldReachId, newReachId);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(oldReachId, input.reachId());
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(StreamReachEndpointInput.Patch.OFFICE_QUERY_PARAMETER));
        assertEquals(newReachId, mockHttpRequestBuilder.getQueryParameter(StreamReachEndpointInput.Patch.NAME_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }
}