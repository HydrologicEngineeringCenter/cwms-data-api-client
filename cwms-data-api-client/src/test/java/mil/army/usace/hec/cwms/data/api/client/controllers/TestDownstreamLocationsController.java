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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import mil.army.usace.hec.cwms.http.client.MockHttpServer;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.data.api.client.model.StreamLocation;
import org.junit.jupiter.api.Test;

class TestDownstreamLocationsController extends TestController {

    @Test
    void testRetrieveDownstreamLocations() throws Exception {
        String collect = readJsonFile("radar/v1/json/stream_locations.json");
        List<StreamLocation> locations = RadarObjectMapper.mapJsonToListOfObjects(collect, StreamLocation.class);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        DownstreamLocationsEndpointInput.GetAll input = DownstreamLocationsEndpointInput.getAll("SPK", "Loc123")
                .allDownstream(true)
                .sameStreamOnly(true)
                .areaUnits("km2")
                .stageUnits("m")
                .stationUnits("km");
        List<StreamLocation> values = new DownstreamLocationsController().retrieveDownstreamLocations(buildConnectionInfo(), input);
        assertFalse(values.isEmpty());
        assertEquals(locations, values);
        MockHttpServer.RequestWrapper request = mockHttpServer.takeRequest();
        assertTrue(request.getPath().startsWith("/stream-locations/SPK/Loc123/downstream-locations?"));
        assertEquals("GET", request.getMethod());
    }
}
