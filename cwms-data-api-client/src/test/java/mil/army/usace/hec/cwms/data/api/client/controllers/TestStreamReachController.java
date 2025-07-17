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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;
import mil.army.usace.hec.cwms.data.api.client.model.StreamReach;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

class TestStreamReachController extends TestController {

    @Test
    void testRetrieveCatalog() throws IOException {
        String collect = readJsonFile("radar/v1/json/stream_reaches.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        StreamReachEndpointInput.GetAll input = StreamReachEndpointInput.getAll()
                .withOfficeIdMask("SPK");
        List<StreamReach> values = new StreamReachController().retrieveStreamReaches(buildConnectionInfo(), input);
        assertFalse(values.isEmpty());
        StreamReach value = values.get(0);
        assertEquals("Reach123", value.getId().getName());
        assertEquals("SPK", value.getOfficeId());
        assertEquals("Stream123", value.getStreamId().getName());
        assertEquals("DownstreamLoc123", value.getDownstreamNode().getId().getName());
        assertEquals("R", value.getDownstreamNode().getBank().getCode());
        assertEquals(25.0, value.getDownstreamNode().getStation(), 0.0);
        assertEquals("km", value.getDownstreamNode().getStationUnits());
        assertEquals("UpstreamLoc123", value.getUpstreamNode().getId().getName());
        assertEquals("L", value.getUpstreamNode().getBank().getCode());
        assertEquals(20.0, value.getUpstreamNode().getStation(), 0.0);
        assertEquals("km", value.getUpstreamNode().getStationUnits());
        assertEquals("OTHER", value.getConfigurationId().getName());
        assertEquals("This is a comment for the stream reach.", value.getComment());
    }

    @Test
    void testRetrieveOne() throws IOException {
        String collect = readJsonFile("radar/v1/json/stream_reach.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        StreamReachEndpointInput.GetOne input = StreamReachEndpointInput.getOne("SPK", "Stream123", "Reach123");
        StreamReach value = new StreamReachController().retrieveStreamReach(buildConnectionInfo(), input);
        assertEquals("Reach123", value.getId().getName());
        assertEquals("SPK", value.getOfficeId());
        assertEquals("Stream123", value.getStreamId().getName());
        assertEquals("DownstreamLoc123", value.getDownstreamNode().getId().getName());
        assertEquals("R", value.getDownstreamNode().getBank().getCode());
        assertEquals(25.0, value.getDownstreamNode().getStation(), 0.0);
        assertEquals("km", value.getDownstreamNode().getStationUnits());
        assertEquals("UpstreamLoc123", value.getUpstreamNode().getId().getName());
        assertEquals("L", value.getUpstreamNode().getBank().getCode());
        assertEquals(20.0, value.getUpstreamNode().getStation(), 0.0);
        assertEquals("km", value.getUpstreamNode().getStationUnits());
        assertEquals("OTHER", value.getConfigurationId().getName());
        assertEquals("This is a comment for the stream reach.", value.getComment());
    }

    @Test
    void testStoreStreamReach() throws IOException {
        String collect = readJsonFile("radar/v1/json/stream_reach.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        StreamReach streamReach = RadarObjectMapper.mapJsonToObject(collect, StreamReach.class);
        StreamReachController controller = new StreamReachController();
        StreamReachEndpointInput.Post input = StreamReachEndpointInput.post(streamReach);
        assertDoesNotThrow(() -> controller.storeStreamReach(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testDeleteStreamReach() throws IOException {
        String collect = readJsonFile("radar/v1/json/stream_reach.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        StreamReach value = RadarObjectMapper.mapJsonToObject(collect, StreamReach.class);
        StreamReachController controller = new StreamReachController();
        controller.storeStreamReach(buildConnectionInfo(cookieJarSupplier), StreamReachEndpointInput.post(value));
        StreamReachEndpointInput.Delete input = StreamReachEndpointInput.delete(value.getOfficeId(), value.getId().getName());
        assertDoesNotThrow(() -> controller.deleteStreamReach(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testRenameStreamReach() throws Exception {
        String collect = readJsonFile("radar/v1/json/stream_reach.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        StreamReach value = RadarObjectMapper.mapJsonToObject(collect, StreamReach.class);
        StreamReachController controller = new StreamReachController();
        StreamReachEndpointInput.Patch input = StreamReachEndpointInput.patch(value.getOfficeId(), value.getId().getName(), "NewName");
        assertDoesNotThrow(() -> controller.renameStreamReach(buildConnectionInfo(cookieJarSupplier), input));
        var requestWrapper = mockHttpServer.takeRequest();
        assertEquals("PATCH", requestWrapper.getMethod());
        assertTrue(requestWrapper.getPath().startsWith("/stream-reaches/" + value.getId().getName() + "?"));
    }
}
