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

import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.StreamLocation;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static mil.army.usace.hec.cwms.radar.client.controllers.StreamLocationEndpointInput.Post.FAIL_IF_EXISTS_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestStreamLocationEndpointInput {

    @Test
    void testGetAllQueryRequestDefault() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        StreamLocationEndpointInput.GetAll input = StreamLocationEndpointInput.getAll()
                .withOfficeIdMask("SPK")
                .withStreamIdMask("STREAM_ID_MASK")
                .withStationUnits("ft")
                .withStageUnits("ft")
                .withAreaUnits("mi2");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SPK", mockHttpRequestBuilder.getQueryParameter(StreamLocationEndpointInput.GetAll.OFFICE_MASK_QUERY_PARAMETER));
        assertEquals("STREAM_ID_MASK", mockHttpRequestBuilder.getQueryParameter(StreamLocationEndpointInput.GetAll.STREAM_ID_MASK_QUERY_PARAMETER));
        assertEquals("ft", mockHttpRequestBuilder.getQueryParameter(StreamLocationEndpointInput.GetAll.STATION_UNITS_QUERY_PARAMETER));
        assertEquals("ft", mockHttpRequestBuilder.getQueryParameter(StreamLocationEndpointInput.GetAll.STAGE_UNITS_QUERY_PARAMETER));
        assertEquals("mi2", mockHttpRequestBuilder.getQueryParameter(StreamLocationEndpointInput.GetAll.AREA_UNITS_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String streamLocationId = "STREAM_LOC";
        String office = "SPK";
        String streamId = "STREAM_ID";
        StreamLocationEndpointInput.GetOne input = StreamLocationEndpointInput.getOne(office, streamId, streamLocationId)
                .withStationUnits("ft")
                .withStageUnits("ft")
                .withAreaUnits("mi2");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(streamLocationId, input.streamLocationId());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(StreamLocationEndpointInput.GetOne.OFFICE_QUERY_PARAMETER));
        assertEquals(streamId, mockHttpRequestBuilder.getQueryParameter(StreamLocationEndpointInput.GetOne.STREAM_ID_QUERY_PARAMETER));
        assertEquals("ft", mockHttpRequestBuilder.getQueryParameter(StreamLocationEndpointInput.GetOne.STATION_UNITS_QUERY_PARAMETER));
        assertEquals("ft", mockHttpRequestBuilder.getQueryParameter(StreamLocationEndpointInput.GetOne.STAGE_UNITS_QUERY_PARAMETER));
        assertEquals("mi2", mockHttpRequestBuilder.getQueryParameter(StreamLocationEndpointInput.GetOne.AREA_UNITS_QUERY_PARAMETER));
    }

    @Test
    void testGetOneNulls() {
        assertThrows(NullPointerException.class, () -> StreamLocationEndpointInput.getOne("", "",null));
        assertThrows(NullPointerException.class, () -> StreamLocationEndpointInput.getOne(null, "",""));
        assertThrows(NullPointerException.class, () -> StreamLocationEndpointInput.getOne("", null,""));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v1/json/stream_location.json");
        StreamLocation streamLocation = RadarObjectMapper.mapJsonToObject(collect, StreamLocation.class);
        StreamLocationEndpointInput.Post input = StreamLocationEndpointInput.post(streamLocation)
                .failIfExists(false);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(streamLocation, input.streamLocation());
        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostNulls() {
        assertThrows(NullPointerException.class, () -> StreamLocationEndpointInput.post(null));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String streamLocationId = "STREAM_LOC";
        String office = "SPK";
        StreamLocationEndpointInput.Delete input = StreamLocationEndpointInput.delete(office, streamLocationId);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(streamLocationId, input.streamLocationId());
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(StreamLocationEndpointInput.Delete.OFFICE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNulls() {
        assertThrows(NullPointerException.class, () -> StreamLocationEndpointInput.delete("", null));
        assertThrows(NullPointerException.class, () -> StreamLocationEndpointInput.delete(null, ""));
    }

    @Test
    void testPatch() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v1/json/stream_location.json");
        StreamLocation streamLocation = RadarObjectMapper.mapJsonToObject(collect, StreamLocation.class);
        StreamLocationEndpointInput.Patch input = StreamLocationEndpointInput.patch(streamLocation);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(streamLocation, input.streamLocation());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }
}
