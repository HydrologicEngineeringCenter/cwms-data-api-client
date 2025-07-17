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

import mil.army.usace.hec.cwms.data.api.client.model.LookupType;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class TestLookupTypeController extends TestController {

    @Test
    void testRetrieveLookupTypes() throws IOException {
        String collect = readJsonFile("radar/v2/json/lookup_types.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LookupTypeEndpointInput.GetAll input = LookupTypeEndpointInput.getAll("SPK", "AT_EMBANK_STRUCTURE_TYPE", "STRUCTURE_TYPE");
        List<LookupType> values = new LookupTypeController().retrieveLookupTypes(buildConnectionInfo(), input);
        assertFalse(values.isEmpty());
        LookupType value = values.get(0);
        assertEquals("SPK", value.getOfficeId());
        assertEquals("test display value", value.getDisplayValue());
        assertEquals("This is a tooltip for the lookup type.", value.getTooltip());
        assertTrue(value.isActive());
        LookupType value2 = values.get(1);
        assertEquals("SPK", value2.getOfficeId());
        assertEquals("test display value2", value2.getDisplayValue());
        assertEquals("This is a tooltip for the lookup type2.", value2.getTooltip());
        assertFalse(value2.isActive());

    }

    @Test
    void testStoreLookupType() throws IOException {
        Logger.getLogger("").setLevel(Level.ALL);
        String collect = readJsonFile("radar/v2/json/lookup_type.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LookupType lookupType = RadarObjectMapper.mapJsonToObject(collect, LookupType.class);
        LookupTypeController controller = new LookupTypeController();
        LookupTypeEndpointInput.Post input = LookupTypeEndpointInput.post("AT_EMBANK_STRUCTURE_TYPE", "STRUCTURE_TYPE", lookupType);
        assertDoesNotThrow(() -> controller.storeLookupType(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testUpdateLookupType() throws IOException {
        Logger.getLogger("").setLevel(Level.ALL);
        String collect = readJsonFile("radar/v2/json/lookup_type.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LookupType lookupType = RadarObjectMapper.mapJsonToObject(collect, LookupType.class);
        LookupTypeController controller = new LookupTypeController();
        LookupTypeEndpointInput.Post input = LookupTypeEndpointInput.post("AT_EMBANK_STRUCTURE_TYPE", "STRUCTURE_TYPE", lookupType);
        assertDoesNotThrow(() -> controller.updateLookupType(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testDeleteLookupType() throws IOException {
        String collect = readJsonFile("radar/v2/json/lookup_type.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LookupType value = RadarObjectMapper.mapJsonToObject(collect, LookupType.class);
        LookupTypeController controller = new LookupTypeController();
        controller.storeLookupType(buildConnectionInfo(cookieJarSupplier), LookupTypeEndpointInput.post("AT_EMBANK_STRUCTURE_TYPE", "STRUCTURE_TYPE", value));
        LookupTypeEndpointInput.Delete input = LookupTypeEndpointInput.delete("SPK", "AT_EMBANK_STRUCTURE_TYPE", "STRUCTURE_TYPE", value.getDisplayValue());
        assertDoesNotThrow(() -> controller.deleteLookupType(buildConnectionInfo(cookieJarSupplier), input));
    }
}