/*
 * MIT License
 *
 * Copyright (c) 2021 Hydrologic Engineering Center
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.List;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.ServerNotFoundException;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesCatalog;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesCatalogEntry;
import org.junit.jupiter.api.Test;

class TestTimeSeriesCatalogController extends TestController {

    @Test
    void testRetrieveTimeSeriesCatalog() throws IOException {
        String collect = readJsonFile("radar/v2/json/catalog_ts.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesCatalogEndpointInput input = new TimeSeriesCatalogEndpointInput()
            .officeId("SWT")
            .unitSystem("SI");
        TimeSeriesCatalog catalog = new CatalogController().retrieveTimeSeriesCatalog(buildConnectionInfo(), input);
        List<TimeSeriesCatalogEntry> entries = catalog.getEntries();
        assertEquals(500, entries.size());
        assertNotNull(catalog.getNextPage());
        assertEquals(500, catalog.getPageSize());
        assertNull(catalog.getPage());
        assertEquals(68233, catalog.getTotal());
        TimeSeriesCatalogEntry catalogEntry = entries.get(0);
        assertEquals("000512.%.Ave.~1Day.0.abc", catalogEntry.getTsName());
        assertEquals("000512.%.Ave.~1Day.0.abc", catalogEntry.getFullName());
        assertEquals("%", catalogEntry.getUnits());
        assertEquals("SWT", catalogEntry.getOffice());
    }

    @Test
    void testRetrieveTimeSeriesCatalogPagination() throws IOException {
        String page1Body = readJsonFile("radar/v2/json/catalog_tspage1.json");
        String page2Body = readJsonFile("radar/v2/json/catalog_tspage2.json");
        mockHttpServer.enqueue(page1Body);
        mockHttpServer.enqueue(page2Body);
        mockHttpServer.start();
        TimeSeriesCatalogEndpointInput input = new TimeSeriesCatalogEndpointInput()
            .officeId("SWT")
            .unitSystem("SI");
        TimeSeriesCatalog catalog = new CatalogController().retrieveTimeSeriesCatalog(buildConnectionInfo(), input);
        List<TimeSeriesCatalogEntry> entries = catalog.getEntries();
        assertEquals(500, entries.size());
        assertNotNull(catalog.getNextPage());
        assertEquals(500, catalog.getPageSize());
        assertNull(catalog.getPage());
        assertEquals(68233, catalog.getTotal());
        TimeSeriesCatalogEntry catalogEntry = entries.get(0);
        assertEquals("000512.%.Ave.~1Day.0.abc", catalogEntry.getTsName());
        assertEquals("000512.%.Ave.~1Day.0.abc", catalogEntry.getFullName());
        assertEquals("%", catalogEntry.getUnits());
        assertEquals("SWT", catalogEntry.getOffice());

        input.cursor(catalog.getNextPage());
        catalog = new CatalogController().retrieveTimeSeriesCatalog(buildConnectionInfo(), input);
        entries = catalog.getEntries();
        assertEquals(500, entries.size());
        assertNotNull(catalog.getNextPage());
        assertEquals(500, catalog.getPageSize());
        assertNull(catalog.getPage());
        assertEquals(68233, catalog.getTotal());
        catalogEntry = entries.get(0);
        assertEquals("16FFA636.Volt-Battery Load.Inst.1Hour.0.Ccp-Rev", catalogEntry.getTsName());
        assertEquals("16FFA636.Volt-Battery Load.Inst.1Hour.0.Ccp-Rev", catalogEntry.getFullName());
        assertEquals("volt", catalogEntry.getUnits());
        assertEquals("SWT", catalogEntry.getOffice());
    }

    @Test
    void testCwmsRadarDown() throws IOException {
        String collect = readJsonFile("radar/v2/json/catalog_ts.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        CatalogController timeSeriesController = new CatalogController();
        assertThrows(ServerNotFoundException.class,
            () -> timeSeriesController.retrieveTimeSeriesCatalog(new ApiConnectionInfo("http://localhost:11999"),
                new TimeSeriesCatalogEndpointInput()));
    }

}
