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
import mil.army.usace.hec.cwms.radar.client.model.Embankment;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

class TestEmbankmentController extends TestController {

    @Test
    void testRetrieveCatalog() throws IOException {
        String collect = readJsonFile("radar/v1/json/embankments.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        EmbankmentEndpointInput.GetAll input = EmbankmentEndpointInput.getAll("PROJECT", "SWT");
        List<Embankment> values = new EmbankmentController().retrieveEmbankments(buildConnectionInfo(), input);
        assertFalse(values.isEmpty());
        Embankment value = values.get(0);
        assertEquals("PROJECT-EMBANKMENT_LOC", value.getLocation().getName());
        assertEquals("SWT", value.getLocation().getOfficeId());
        assertEquals("PROJECT", value.getProjectId().getName());
        assertEquals("SWT", value.getProjectId().getOfficeId());
        assertEquals(15.0, value.getUpstreamSideSlope(), 0.0);
        assertEquals(90.0, value.getDownstreamSideSlope(), 0.0);
        assertEquals(5.0, value.getMaxHeight(), 0.0);
        assertEquals(20.0, value.getTopWidth(), 0.0);
        assertEquals("m", value.getLengthUnits());
        assertNotNull(value.getDownstreamProtectionType());
        assertNotNull(value.getUpstreamProtectionType());
        assertNotNull(value.getStructureType());
        assertEquals(25.0, value.getStructureLength(), 0.0);
        assertNotNull(value.getLocation());
    }

    @Test
    void testRetrieveOne() throws IOException {
        String collect = readJsonFile("radar/v1/json/embankment.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        EmbankmentEndpointInput.GetOne input = EmbankmentEndpointInput.getOne("PROJECT-EMBANKMENT_LOC", "SPK");
        Embankment value = new EmbankmentController().retrieveEmbankment(buildConnectionInfo(), input);
        assertEquals("PROJECT-EMBANKMENT_LOC", value.getLocation().getName());
        assertEquals("SWT", value.getLocation().getOfficeId());
        assertEquals("PROJECT", value.getProjectId().getName());
        assertEquals("SWT", value.getProjectId().getOfficeId());
        assertEquals(15.0, value.getUpstreamSideSlope(), 0.0);
        assertEquals(90.0, value.getDownstreamSideSlope(), 0.0);
        assertEquals(5.0, value.getMaxHeight(), 0.0);
        assertEquals(20.0, value.getTopWidth(), 0.0);
        assertEquals("m", value.getLengthUnits());
        assertNotNull(value.getDownstreamProtectionType());
        assertNotNull(value.getUpstreamProtectionType());
        assertNotNull(value.getStructureType());
        assertEquals(25.0, value.getStructureLength(), 0.0);
        assertNotNull(value.getLocation());
    }

    @Test
    void testStoreEmbankment() throws IOException {
        Logger.getLogger("").setLevel(Level.ALL);
        String collect = readJsonFile("radar/v1/json/embankment.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Embankment timeSeries = RadarObjectMapper.mapJsonToObject(collect, Embankment.class);
        EmbankmentController controller = new EmbankmentController();
        EmbankmentEndpointInput.Post input = EmbankmentEndpointInput.post(timeSeries);
        assertDoesNotThrow(() -> controller.storeEmbankment(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testDeleteEmbankment() throws IOException {
        String collect = readJsonFile("radar/v1/json/embankment.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Embankment value = RadarObjectMapper.mapJsonToObject(collect, Embankment.class);
        EmbankmentController controller = new EmbankmentController();
        controller.storeEmbankment(buildConnectionInfo(cookieJarSupplier), EmbankmentEndpointInput.post(value));
        EmbankmentEndpointInput.Delete input = EmbankmentEndpointInput.delete(value.getLocation().getName(), value.getLocation().getOfficeId());
        assertDoesNotThrow(() -> controller.deleteEmbankment(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testRenameEmbankment() throws Exception {
        String collect = readJsonFile("radar/v1/json/embankment.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Embankment value = RadarObjectMapper.mapJsonToObject(collect, Embankment.class);
        EmbankmentController controller = new EmbankmentController();
        EmbankmentEndpointInput.Patch input = EmbankmentEndpointInput.patch(value.getLocation().getName(),
                "NewName", "SPK");
        assertDoesNotThrow(() -> controller.renameEmbankment(buildConnectionInfo(cookieJarSupplier), input));
        var requestWrapper = mockHttpServer.takeRequest();
        assertEquals("PATCH", requestWrapper.getMethod());
        assertTrue(requestWrapper.getPath().startsWith("/embankments/" + value.getLocation().getName() + "?"));
    }
}
