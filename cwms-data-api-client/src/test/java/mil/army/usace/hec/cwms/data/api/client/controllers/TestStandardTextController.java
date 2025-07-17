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

import mil.army.usace.hec.cwms.data.api.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.data.api.client.model.StandardTextCatalog;
import mil.army.usace.hec.cwms.data.api.client.model.StandardTextId;
import mil.army.usace.hec.cwms.data.api.client.model.StandardTextValue;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class TestStandardTextController extends TestController {

    @Test
    void testRetrieveCatalog() throws IOException {
        String collect = readJsonFile("radar/v2/json/standard_text_catalog.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        StandardTextEndpointInput.GetAll input = StandardTextEndpointInput.getAll();
        StandardTextCatalog catalog = new StandardTextController().retrieveStandardTextCatalog(buildConnectionInfo(), input);
        List<StandardTextValue> values = catalog.getValues();
        assertFalse(values.isEmpty());
        StandardTextValue value = values.get(0);
        assertEquals("NO RECORD", value.getStandardText());
        assertEquals("CWMS", value.getId().getOfficeId());
        assertEquals("A", value.getId().getId());
    }

    @Test
    void testRetrieveOne() throws IOException {
        String collect = readJsonFile("radar/v2/json/standard_text.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        StandardTextEndpointInput.GetOne input = StandardTextEndpointInput.getOne("HW", "SPK");
        StandardTextValue value = new StandardTextController().retrieveStandardText(buildConnectionInfo(), input);
        assertEquals("Hello, World", value.getStandardText());
        assertEquals("SPK", value.getId().getOfficeId());
        assertEquals("HW", value.getId().getId());
    }

    @Test
    void testStoreStandardText() throws IOException {
        Logger.getLogger("").setLevel(Level.ALL);
        String collect = readJsonFile("radar/v2/json/standard_text.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        StandardTextValue timeSeries = RadarObjectMapper.mapJsonToObject(collect, StandardTextValue.class);
        StandardTextController controller = new StandardTextController();
        StandardTextEndpointInput.Post input = StandardTextEndpointInput.post(timeSeries);
        assertDoesNotThrow(() -> controller.storeStandardText(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testDeleteStandardText() throws IOException {
        String collect = readJsonFile("radar/v2/json/standard_text.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        StandardTextValue value = RadarObjectMapper.mapJsonToObject(collect, StandardTextValue.class);
        StandardTextId standardTextId = value.getId();
        standardTextId.setId(standardTextId.getId() + (System.currentTimeMillis() % 100_000));
        StandardTextController controller = new StandardTextController();
        controller.storeStandardText(buildConnectionInfo(cookieJarSupplier), StandardTextEndpointInput.post(value));
        StandardTextEndpointInput.Delete input = StandardTextEndpointInput.delete(value.getId().getId(), DeleteMethod.ALL, value.getId().getOfficeId());
        assertDoesNotThrow(() -> controller.deleteStandardText(buildConnectionInfo(cookieJarSupplier), input));
    }
}
