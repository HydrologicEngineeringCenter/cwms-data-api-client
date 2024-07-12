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

import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.Stream;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static mil.army.usace.hec.cwms.radar.client.controllers.StreamEndpointInput.Post.FAIL_IF_EXISTS_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestStreamEndpointInput {

    @Test
    void testGetAllQueryRequestDefault() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        StreamEndpointInput.GetAll input = StreamEndpointInput.getAll()
                .withOfficeIdMask("SPK")
                .withFlowsIntoStreamIdMask("FLOWS_INTO")
                .withDivertsFromStreamIdMask("DIVERTS_FROM")
                .withStationUnits("km");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SPK", mockHttpRequestBuilder.getQueryParameter(StreamEndpointInput.GetAll.OFFICE_MASK_QUERY_PARAMETER));
        assertEquals("FLOWS_INTO", mockHttpRequestBuilder.getQueryParameter(StreamEndpointInput.GetAll.FLOWS_INTO_STREAM_MASK_QUERY_PARAMETER));
        assertEquals("DIVERTS_FROM", mockHttpRequestBuilder.getQueryParameter(StreamEndpointInput.GetAll.DIVERTS_FROM_STREAM_MASK_QUERY_PARAMETER));
        assertEquals("km", mockHttpRequestBuilder.getQueryParameter(StreamEndpointInput.GetAll.STATION_UNITS_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String streamId = "STREAM";
        String office = "SPK";
        StreamEndpointInput.GetOne input = StreamEndpointInput.getOne(office, streamId)
                        .withStationUnits("km");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(streamId, input.streamId());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(StreamEndpointInput.GetOne.OFFICE_QUERY_PARAMETER));
        assertEquals("km", mockHttpRequestBuilder.getQueryParameter(StreamEndpointInput.GetOne.STATION_UNITS_QUERY_PARAMETER));
    }

    @Test
    void testGetOneNullTimeSeriesId() {
        assertThrows(NullPointerException.class, () -> StreamEndpointInput.getOne("", null));
        assertThrows(NullPointerException.class, () -> StreamEndpointInput.getOne(null, ""));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v1/json/stream.json");
        Stream stream = RadarObjectMapper.mapJsonToObject(collect, Stream.class);
        StreamEndpointInput.Post input = StreamEndpointInput.post(stream)
                .failIfExists(false);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(stream, input.stream());
        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostNullTimeSeries() {
        assertThrows(NullPointerException.class, () -> StreamEndpointInput.post(null));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String streamId = "STREAM";
        String office = "SPK";
        StreamEndpointInput.Delete input = StreamEndpointInput.delete(office, streamId)
                .deleteMethod(DeleteMethod.ALL);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(streamId, input.streamId());
        assertEquals(DeleteMethod.ALL.toString(), mockHttpRequestBuilder.getQueryParameter(StreamEndpointInput.Delete.METHOD_QUERY_PARAMETER));
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(StreamEndpointInput.Delete.OFFICE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNulls() {
        assertThrows(NullPointerException.class, () -> StreamEndpointInput.delete("", null));
        assertThrows(NullPointerException.class, () -> StreamEndpointInput.delete(null, ""));
    }

    @Test
    void testPatch() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String oldStreamId = "STREAM";
        String newStreamId = "STREAM_NEW";
        String office = "SPK";
        StreamEndpointInput.Patch input = StreamEndpointInput.patch(office, oldStreamId, newStreamId);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(oldStreamId, input.streamId());
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(StreamEndpointInput.Patch.OFFICE_QUERY_PARAMETER));
        assertEquals(newStreamId, mockHttpRequestBuilder.getQueryParameter(StreamEndpointInput.Patch.NAME_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }
}
