/*
 * MIT License
 *
 * Copyright (c) 2023 Hydrologic Engineering Center
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

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mil.army.usace.hec.cwms.radar.client.model.Clob;
import mil.army.usace.hec.cwms.radar.client.model.Clobs;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

class TestClobController extends TestController {

    @Test
    void testRetrieveClob() throws IOException {
        String collect = readJsonFile("radar/v2/json/clob.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        ClobEndpointInput.GetOne input = ClobEndpointInput.getOne("TEST.CLOB.ID", "SWT");
        Clob clob = new ClobController().retrieveClob(buildConnectionInfo(), input);
        assertEquals("TEST.CLOB.ID", clob.getId());
        assertEquals("UNIT TESTING CLOB ENDPOINT DESCRIPTION", clob.getDescription());
        assertEquals("SWT", clob.getOffice());
        assertEquals("UNIT TESTING CLOB ENDPOINT", clob.getValue());
    }

    @Test
    void testRetrieveClobs() throws IOException {
        String collect = readJsonFile("radar/v2/json/clobs.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        ClobEndpointInput.GetAll input = ClobEndpointInput.getAll()
            .officeId("SWT");
        Clobs clobs = new ClobController().retrieveClobs(buildConnectionInfo(), input);
        Clob clob = clobs.getClobs().get(0);
        assertEquals("TEST.CLOB.ID", clob.getId());
        assertEquals("UNIT TESTING CLOB ENDPOINT DESCRIPTION", clob.getDescription());
        assertEquals("SWT", clob.getOffice());
        assertEquals("UNIT TESTING CLOB ENDPOINT", clob.getValue());
    }

    @Test
    void testStoreClob() throws IOException {
        Logger.getLogger("").setLevel(Level.ALL);
        String collect = readJsonFile("radar/v2/json/clob.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Clob clob = RadarObjectMapper.mapJsonToObject(collect, Clob.class);
        ClobController clobController = new ClobController();
        ClobEndpointInput.Post input = ClobEndpointInput.post(clob);
        assertDoesNotThrow(() -> clobController.storeClob(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testDeleteClob() throws IOException {
        String collect = readJsonFile("radar/v2/json/clob.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Clob clob = RadarObjectMapper.mapJsonToObject(collect, Clob.class);
        clob.setId(clob.getId() + (System.currentTimeMillis() % 100_000));
        ClobController clobController = new ClobController();
        clobController.storeClob(buildConnectionInfo(cookieJarSupplier), ClobEndpointInput.post(clob));
        ClobEndpointInput.Delete input = ClobEndpointInput.delete(clob.getId(), clob.getOffice());
        assertDoesNotThrow(() -> clobController.deleteClob(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testPatchClob() throws IOException {
        String collect = readJsonFile("radar/v2/json/clob.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Clob clob = RadarObjectMapper.mapJsonToObject(collect, Clob.class);
        clob.setValue(clob.getValue() + "______________test");
        ClobController clobController = new ClobController();
        clobController.updateClob(buildConnectionInfo(cookieJarSupplier), ClobEndpointInput.patch(clob));
        ClobEndpointInput.Patch input = ClobEndpointInput.patch(clob);
        assertDoesNotThrow(() -> clobController.updateClob(buildConnectionInfo(cookieJarSupplier), input));
    }
}
