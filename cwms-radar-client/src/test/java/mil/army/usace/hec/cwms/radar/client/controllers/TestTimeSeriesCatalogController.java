/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.radar.client.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.List;
import mil.army.usace.hec.cwms.radar.client.ClientNotFoundException;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesCatalog;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesCatalogEntry;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

class TestTimeSeriesCatalogController extends TestController {

    @Test
    void testRetrieveTimeSeriesCatalog() throws IOException {
        MockWebServer server = new MockWebServer();
        String collect = readJsonFile(server, "radar/json/catalog_ts.json");
        server.enqueue(new MockResponse().setBody(collect));
        server.start();
        TimeSeriesCatalog catalog = new CatalogController().retrieveTimeSeriesCatalog(server::url, "SWT", "SI", null);
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
        MockWebServer server = new MockWebServer();
        String page1Body = readJsonFile(server, "radar/json/catalog_tspage1.json");
        String page2Body = readJsonFile(server, "radar/json/catalog_tspage2.json");
        server.enqueue(new MockResponse().setBody(page1Body));
        server.enqueue(new MockResponse().setBody(page2Body));
        server.start();
        TimeSeriesCatalog catalog = new CatalogController().retrieveTimeSeriesCatalog(server::url, "SWT", "SI", null);
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

        catalog = new CatalogController().retrieveTimeSeriesCatalog(server::url,
            "SWT", "SI", catalog.getNextPage());
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
        MockWebServer server = new MockWebServer();
        String collect = readJsonFile(server, "radar/json/catalog_ts.json");
        server.enqueue(new MockResponse().setBody(collect));
        server.start();
        CatalogController timeSeriesController = new CatalogController();
        assertThrows(ClientNotFoundException.class,
            () -> timeSeriesController.retrieveTimeSeriesCatalog(s -> HttpUrl.parse("http://localhost:11999" + s), "SWT", "SI", null));
    }

}
