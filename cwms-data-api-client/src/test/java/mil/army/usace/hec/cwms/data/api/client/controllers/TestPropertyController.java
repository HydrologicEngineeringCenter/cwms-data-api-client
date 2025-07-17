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

import mil.army.usace.hec.cwms.data.api.client.model.Property;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class TestPropertyController extends TestController {

    @Test
    void testRetrieveCatalog() throws IOException {
        String collect = readJsonFile("radar/v2/json/property_catalog.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        PropertyEndpointInput.GetAll input = PropertyEndpointInput.getAll();
        List<Property> values = new PropertyController().retrieveProperties(buildConnectionInfo(), input);
        assertFalse(values.isEmpty());
        Property value = values.get(0);
        assertEquals("PropertyControllerIT", value.getCategory());
        assertEquals("SPK", value.getOfficeId());
        assertEquals("integration test", value.getComment());
        assertEquals("test_prop_id", value.getName());
        assertEquals("test value", value.getValue());
    }

    @Test
    void testRetrieveOne() throws IOException {
        String collect = readJsonFile("radar/v2/json/property.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        PropertyEndpointInput.GetOne input = PropertyEndpointInput.getOne("PropertyControllerIT", "propertyId", "SPK");
        Property value = new PropertyController().retrieveProperty(buildConnectionInfo(), input);
        assertEquals("PropertyControllerIT", value.getCategory());
        assertEquals("SPK", value.getOfficeId());
        assertEquals("integration test", value.getComment());
        assertEquals("test_prop_id", value.getName());
        assertEquals("test value", value.getValue());
    }

    @Test
    void testStoreProperty() throws IOException {
        Logger.getLogger("").setLevel(Level.ALL);
        String collect = readJsonFile("radar/v2/json/property.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Property timeSeries = RadarObjectMapper.mapJsonToObject(collect, Property.class);
        PropertyController controller = new PropertyController();
        PropertyEndpointInput.Post input = PropertyEndpointInput.post(timeSeries);
        assertDoesNotThrow(() -> controller.storeProperty(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testDeleteProperty() throws IOException {
        String collect = readJsonFile("radar/v2/json/property.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Property value = RadarObjectMapper.mapJsonToObject(collect, Property.class);
        PropertyController controller = new PropertyController();
        controller.storeProperty(buildConnectionInfo(cookieJarSupplier), PropertyEndpointInput.post(value));
        PropertyEndpointInput.Delete input = PropertyEndpointInput.delete(value.getCategory(), value.getName(), value.getOfficeId());
        assertDoesNotThrow(() -> controller.deleteProperty(buildConnectionInfo(cookieJarSupplier), input));
    }
}
