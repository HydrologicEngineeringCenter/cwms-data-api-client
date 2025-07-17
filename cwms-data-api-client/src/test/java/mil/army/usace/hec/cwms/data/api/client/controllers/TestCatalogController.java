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

import java.util.List;
import mil.army.usace.hec.cwms.data.api.client.model.County;
import mil.army.usace.hec.cwms.data.api.client.model.DbTimeZone;
import mil.army.usace.hec.cwms.data.api.client.model.Parameter;
import mil.army.usace.hec.cwms.data.api.client.model.State;
import mil.army.usace.hec.cwms.data.api.client.model.Unit;
import org.junit.jupiter.api.Test;

final class TestCatalogController extends TestController {

    @Test
    void testParameterCatalog() throws Exception {
        String collect = readJsonFile("radar/v1/json/parameter_catalog.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        List<Parameter> parameters = new CatalogController().retrieveParameterCatalog(buildConnectionInfo());
        assertFalse(parameters.isEmpty());
    }

    @Test
    void testUnitCatalog() throws Exception {
        String collect = readJsonFile("radar/v1/json/unit_catalog.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        List<Unit> units = new CatalogController().retrieveUnitCatalog(buildConnectionInfo());
        assertFalse(units.isEmpty());
    }

    @Test
    void testTimeZoneCatalog() throws Exception {
        String collect = readJsonFile("radar/v2/json/time-zones.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        List<DbTimeZone> units = new CatalogController().retrieveTimeZoneCatalog(buildConnectionInfo());
        assertFalse(units.isEmpty());
    }

    @Test
    void testCountyCatalog() throws Exception {
        String collect = readJsonFile("radar/v2/json/county_catalog.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        List<County> counties = new CatalogController().retrieveCountyCatalog(buildConnectionInfo());
        assertFalse(counties.isEmpty());
        County county = counties.get(0);
        assertEquals("Unknown County or County N/A", county.getName());
        assertEquals("000", county.getCountyId());
        assertEquals("00", county.getStateInitial());
    }

    @Test
    void testStateCatalog() throws Exception {
        String collect = readJsonFile("radar/v2/json/state_catalog.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        List<State> states = new CatalogController().retrieveStateCatalog(buildConnectionInfo());
        assertFalse(states.isEmpty());
        State state = states.get(0);
        assertEquals("Unknown State or State N/A", state.getName());
        assertEquals("00", state.getStateInitial());
    }
}
