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
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.Turbine;
import org.junit.jupiter.api.Test;

class TestTurbineController extends TestController {

    @Test
    void testRetrieveCatalog() throws IOException {
        String collect = readJsonFile("radar/v1/json/turbines.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TurbineEndpointInput.GetAll input = TurbineEndpointInput.getAll("PROJECT", "SWT");
        List<Turbine> values = new TurbineController().retrieveTurbines(buildConnectionInfo(), input);
        assertFalse(values.isEmpty());
        Turbine value = values.get(0);
        assertEquals("PROJECT-TURBINE_LOC", value.getLocation().getName());
        assertEquals("SWT", value.getLocation().getOfficeId());
        assertEquals("PROJECT", value.getProjectId().getName());
        assertEquals("SWT", value.getProjectId().getOfficeId());
        assertNotNull(value.getLocation());
    }

    @Test
    void testRetrieveOne() throws IOException {
        String collect = readJsonFile("radar/v1/json/turbine.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TurbineEndpointInput.GetOne input = TurbineEndpointInput.getOne("PROJECT-TURBINE_LOC", "SPK");
        Turbine value = new TurbineController().retrieveTurbine(buildConnectionInfo(), input);
        assertEquals("PROJECT-TURBINE_LOC", value.getLocation().getName());
        assertEquals("SWT", value.getLocation().getOfficeId());
        assertEquals("PROJECT", value.getProjectId().getName());
        assertEquals("SWT", value.getProjectId().getOfficeId());
        assertNotNull(value.getLocation());
    }

    @Test
    void testStoreTurbine() throws IOException {
        Logger.getLogger("").setLevel(Level.ALL);
        String collect = readJsonFile("radar/v1/json/turbine.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Turbine timeSeries = RadarObjectMapper.mapJsonToObject(collect, Turbine.class);
        TurbineController controller = new TurbineController();
        TurbineEndpointInput.Post input = TurbineEndpointInput.post(timeSeries);
        assertDoesNotThrow(() -> controller.storeTurbine(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testDeleteTurbine() throws IOException {
        String collect = readJsonFile("radar/v1/json/turbine.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Turbine value = RadarObjectMapper.mapJsonToObject(collect, Turbine.class);
        TurbineController controller = new TurbineController();
        controller.storeTurbine(buildConnectionInfo(cookieJarSupplier), TurbineEndpointInput.post(value));
        TurbineEndpointInput.Delete input = TurbineEndpointInput.delete(value.getLocation().getName(), value.getLocation().getOfficeId());
        assertDoesNotThrow(() -> controller.deleteTurbine(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testRenameTurbine() throws Exception {
        String collect = readJsonFile("radar/v1/json/turbine.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Turbine value = RadarObjectMapper.mapJsonToObject(collect, Turbine.class);
        TurbineController controller = new TurbineController();
        TurbineEndpointInput.Patch input = TurbineEndpointInput.patch(value.getLocation().getName(),
                "NewName", "SPK");
        assertDoesNotThrow(() -> controller.renameTurbine(buildConnectionInfo(cookieJarSupplier), input));
        var requestWrapper = mockHttpServer.takeRequest();
        assertEquals("PATCH", requestWrapper.getMethod());
        assertTrue(requestWrapper.getPath().startsWith("/turbines/" + value.getLocation().getName() + "?"));
    }
}
