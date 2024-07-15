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

import static mil.army.usace.hec.cwms.radar.client.controllers.TurbineChangeEndpointInput.delete;
import static mil.army.usace.hec.cwms.radar.client.controllers.TurbineChangeEndpointInput.getAll;
import static mil.army.usace.hec.cwms.radar.client.controllers.TurbineChangeEndpointInput.post;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mil.army.usace.hec.cwms.http.client.MockHttpServer;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.TurbineChange;
import org.junit.jupiter.api.Test;

class TestTurbineChangeController extends TestController {

    @Test
    void testRetrieveCatalog() throws Exception {
        String collect = readJsonFile("radar/v1/json/turbine-changes.json");
        List<TurbineChange> changes = RadarObjectMapper.mapJsonToListOfObjects(collect, TurbineChange.class);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Instant begin = Instant.now();
        Instant end = begin.plus(5, ChronoUnit.DAYS);
        TurbineChangeEndpointInput.GetAll input = getAll("SPK", "PROJ", begin, end);
        List<TurbineChange> values = new TurbineChangeController().retrieveTurbineChanges(buildConnectionInfo(), input);
        assertFalse(values.isEmpty());
        assertEquals(changes, values);
        MockHttpServer.RequestWrapper request = mockHttpServer.takeRequest();
        assertTrue(request.getPath().startsWith("/projects/SPK/PROJ/turbine-changes?"));
        assertEquals("GET", request.getMethod());
    }

    @Test
    void testStoreTurbine() throws Exception {
        Logger.getLogger("").setLevel(Level.ALL);
        String collect = readJsonFile("radar/v1/json/turbine-changes.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TurbineChangeController controller = new TurbineChangeController();
        List<TurbineChange> changes = RadarObjectMapper.mapJsonToListOfObjects(collect, TurbineChange.class);
        TurbineChangeEndpointInput.Post input = post("SPK", "PROJ", changes);
        assertDoesNotThrow(() -> controller.storeTurbineChanges(buildConnectionInfo(cookieJarSupplier), input));
        MockHttpServer.RequestWrapper request = mockHttpServer.takeRequest();
        assertTrue(request.getPath().startsWith("/projects/SPK/PROJ/turbine-changes?"));
        assertEquals("POST", request.getMethod());
    }

    @Test
    void testDeleteTurbine() throws Exception {
        String collect = readJsonFile("radar/v1/json/turbine-changes.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TurbineChangeController controller = new TurbineChangeController();
        String projectId = "PROJ";
        String office = "SPK";
        Instant begin = Instant.now();
        Instant end = begin.plus(5, ChronoUnit.DAYS);
        TurbineChangeEndpointInput.Delete input = delete(office, projectId, begin, end)
            .overrideProtection(true);
        assertDoesNotThrow(() -> controller.deleteTurbineChanges(buildConnectionInfo(cookieJarSupplier), input));
        MockHttpServer.RequestWrapper request = mockHttpServer.takeRequest();
        assertTrue(request.getPath().startsWith("/projects/SPK/PROJ/turbine-changes?"));
        assertEquals("DELETE", request.getMethod());
    }
}
