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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mil.army.usace.hec.cwms.radar.client.model.Stream;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

class TestStreamController extends TestController {

    @Test
    void testRetrieveCatalog() throws IOException {
        String collect = readJsonFile("radar/v1/json/streams.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        StreamEndpointInput.GetAll input = StreamEndpointInput.getAll()
                .withOfficeIdMask("SPK");
        List<Stream> values = new StreamController().retrieveStreams(buildConnectionInfo(), input);
        assertFalse(values.isEmpty());
        Stream value = values.get(0);
        assertEquals("Stream123Test", value.getId().getName());
        assertEquals("SPK", value.getOfficeId());
        assertEquals(true, value.isStartsDownstream());
        assertEquals("DownstreamStream123", value.getFlowsIntoStreamNode().getStreamId().getName());
        assertEquals(123.45, value.getFlowsIntoStreamNode().getStation(), 0.0);
        assertEquals("km", value.getFlowsIntoStreamNode().getStationUnits());
        assertEquals("UpstreamStream123", value.getDivertsFromStreamNode().getStreamId().getName());
        assertEquals(678.9, value.getDivertsFromStreamNode().getStation(), 0.0);
        assertEquals("km", value.getDivertsFromStreamNode().getStationUnits());
        assertEquals(10.5, value.getLength(), 0.0);
        assertEquals(0.01, value.getAverageSlope(), 0.0);
        assertEquals("km", value.getLengthUnits());
        assertEquals("%", value.getSlopeUnits());
        assertNotNull(value.getComment());
    }

    @Test
    void testRetrieveOne() throws IOException {
        String collect = readJsonFile("radar/v1/json/stream.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        StreamEndpointInput.GetOne input = StreamEndpointInput.getOne("SPK", "Stream123Test");
        Stream value = new StreamController().retrieveStream(buildConnectionInfo(), input);
        assertEquals("Stream123Test", value.getId().getName());
        assertEquals("SPK", value.getOfficeId());
        assertEquals(true, value.isStartsDownstream());
        assertEquals("DownstreamStream123", value.getFlowsIntoStreamNode().getStreamId().getName());
        assertEquals(123.45, value.getFlowsIntoStreamNode().getStation(), 0.0);
        assertEquals("km", value.getFlowsIntoStreamNode().getStationUnits());
        assertEquals("UpstreamStream123", value.getDivertsFromStreamNode().getStreamId().getName());
        assertEquals(678.9, value.getDivertsFromStreamNode().getStation(), 0.0);
        assertEquals("km", value.getDivertsFromStreamNode().getStationUnits());
        assertEquals(10.5, value.getLength(), 0.0);
        assertEquals(0.01, value.getAverageSlope(), 0.0);
        assertEquals("km", value.getLengthUnits());
        assertEquals("%", value.getSlopeUnits());
        assertNotNull(value.getComment());
    }

    @Test
    void testStoreStream() throws IOException {
        Logger.getLogger("").setLevel(Level.ALL);
        String collect = readJsonFile("radar/v1/json/stream.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Stream stream = RadarObjectMapper.mapJsonToObject(collect, Stream.class);
        StreamController controller = new StreamController();
        StreamEndpointInput.Post input = StreamEndpointInput.post(stream);
        assertDoesNotThrow(() -> controller.storeStream(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testDeleteStream() throws IOException {
        String collect = readJsonFile("radar/v1/json/stream.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Stream value = RadarObjectMapper.mapJsonToObject(collect, Stream.class);
        StreamController controller = new StreamController();
        controller.storeStream(buildConnectionInfo(cookieJarSupplier), StreamEndpointInput.post(value));
        StreamEndpointInput.Delete input = StreamEndpointInput.delete(value.getOfficeId(), value.getId().getName());
        assertDoesNotThrow(() -> controller.deleteStream(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testRenameStream() throws Exception {
        String collect = readJsonFile("radar/v1/json/stream.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Stream value = RadarObjectMapper.mapJsonToObject(collect, Stream.class);
        StreamController controller = new StreamController();
        StreamEndpointInput.Patch input = StreamEndpointInput.patch(value.getOfficeId(), value.getId().getName(), "NewName");
        assertDoesNotThrow(() -> controller.renameStream(buildConnectionInfo(cookieJarSupplier), input));
        var requestWrapper = mockHttpServer.takeRequest();
        assertEquals("PATCH", requestWrapper.getMethod());
        assertTrue(requestWrapper.getPath().startsWith("/streams/" + value.getId().getName() + "?"));
    }
}
