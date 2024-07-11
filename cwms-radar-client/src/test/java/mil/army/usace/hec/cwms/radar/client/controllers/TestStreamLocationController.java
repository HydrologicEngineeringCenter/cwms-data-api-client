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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mil.army.usace.hec.cwms.radar.client.model.StreamLocation;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

class TestStreamLocationController extends TestController {

    @Test
    void testRetrieveCatalog() throws IOException {
        String collect = readJsonFile("radar/v1/json/stream_locations.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        StreamLocationEndpointInput.GetAll input = StreamLocationEndpointInput.getAll()
                .withOfficeIdMask("SPK");
        List<StreamLocation> values = new StreamLocationController().retrieveStreamLocations(buildConnectionInfo(), input);
        assertFalse(values.isEmpty());
        StreamLocation value = values.get(0);
        assertEquals("StreamLoc123", value.getId().getName());
        assertEquals("SPK", value.getId().getOfficeId());
        assertEquals("ImOnThisStream", value.getStreamId().getName());
        assertEquals("R", value.getBank().getCode());
        assertEquals(678.9, value.getStation(), 0.0);
        assertEquals("km", value.getStationUnits());
        assertEquals(123.45, value.getPublishedStation(), 0.0);
        assertEquals(12.0, value.getNavigationStation(), 0.0);
        assertEquals(1.5, value.getLowestMeasurableStage(), 0.0);
        assertEquals(10.5, value.getTotalDrainageArea(), 0.0);
        assertEquals(0.01, value.getUngagedDrainageArea(), 0.0);
        assertEquals("km2", value.getAreaUnits());
        assertEquals("m", value.getStageUnits());
    }

    @Test
    void testRetrieveOne() throws IOException {
        String collect = readJsonFile("radar/v1/json/stream_location.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        StreamLocationEndpointInput.GetOne input = StreamLocationEndpointInput.getOne("SPK", "ImOnThisStream", "StreamLoc123");
        StreamLocation value = new StreamLocationController().retrieveStreamLocation(buildConnectionInfo(), input);
        assertEquals("StreamLoc123", value.getId().getName());
        assertEquals("SPK", value.getOfficeId());
        assertEquals("ImOnThisStream", value.getStreamId().getName());
        assertEquals("R", value.getBank().getCode());
        assertEquals(678.9, value.getStation(), 0.0);
        assertEquals("km", value.getStationUnits());
        assertEquals(123.45, value.getPublishedStation(), 0.0);
        assertEquals(12.0, value.getNavigationStation(), 0.0);
        assertEquals(1.5, value.getLowestMeasurableStage(), 0.0);
        assertEquals(10.5, value.getTotalDrainageArea(), 0.0);
        assertEquals(0.01, value.getUngagedDrainageArea(), 0.0);
        assertEquals("km2", value.getAreaUnits());
        assertEquals("m", value.getStageUnits());
    }

    @Test
    void testStoreStreamLocation() throws IOException {
        Logger.getLogger("").setLevel(Level.ALL);
        String collect = readJsonFile("radar/v1/json/stream_location.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        StreamLocation streamLocation = RadarObjectMapper.mapJsonToObject(collect, StreamLocation.class);
        StreamLocationController controller = new StreamLocationController();
        StreamLocationEndpointInput.Post input = StreamLocationEndpointInput.post(streamLocation);
        assertDoesNotThrow(() -> controller.storeStreamLocation(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testDeleteStreamLocation() throws IOException {
        String collect = readJsonFile("radar/v1/json/stream_location.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        StreamLocation value = RadarObjectMapper.mapJsonToObject(collect, StreamLocation.class);
        StreamLocationController controller = new StreamLocationController();
        controller.storeStreamLocation(buildConnectionInfo(cookieJarSupplier), StreamLocationEndpointInput.post(value));
        StreamLocationEndpointInput.Delete input = StreamLocationEndpointInput.delete("SPK", "StreamLoc123");
        assertDoesNotThrow(() -> controller.deleteStreamLocation(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testUpdateStreamLocation() throws Exception {
        String collect = readJsonFile("radar/v1/json/stream_location.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        StreamLocation value = RadarObjectMapper.mapJsonToObject(collect, StreamLocation.class);
        StreamLocationController controller = new StreamLocationController();
        StreamLocationEndpointInput.Patch input = StreamLocationEndpointInput.patch(value);
        assertDoesNotThrow(() -> controller.updateStreamLocation(buildConnectionInfo(cookieJarSupplier), input));
        var requestWrapper = mockHttpServer.takeRequest();
        assertEquals("PATCH", requestWrapper.getMethod());
        assertTrue(requestWrapper.getPath().startsWith("/stream-locations/" + value.getId().getName()));
    }
}
