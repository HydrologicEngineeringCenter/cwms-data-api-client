/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.radar.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.List;
import mil.army.usace.hec.cwms.radar.client.model.LocationsCatalog;
import mil.army.usace.hec.cwms.radar.client.model.LocationsCatalogEntry;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

class TestLocationsCatalogController extends TestController {

    @Test
    void testRetrieveTimeSeriesCatalog() throws IOException {
        MockWebServer server = new MockWebServer();
        String collect = readJsonFile(server, "radar/json/catalog_loc.json");
        server.enqueue(new MockResponse().setBody(collect));
        server.start();
        LocationsCatalog catalog = new CatalogController().retrieveLocationsCatalog(server::url, "SWT", "SI", null);
        List<LocationsCatalogEntry> entries = catalog.getEntries();
        assertEquals(500, entries.size());
        assertNotNull(catalog.getNextPage());
        assertEquals(500, catalog.getPageSize());
        assertNull(catalog.getPage());
        assertEquals(68233, catalog.getTotal());
        LocationsCatalogEntry catalogEntry = entries.get(0);
        assertEquals("000512.%.Ave.~1Day.0.abc", catalogEntry.getFullName());
        assertEquals("SWT", catalogEntry.getOffice());
    }

    @Test
    void testRetrieveTimeSeriesCatalogPagination() throws IOException {
        MockWebServer server = new MockWebServer();
        String page1Body = readJsonFile(server, "radar/json/catalog_locpage1.json");
        String page2Body = readJsonFile(server, "radar/json/catalog_locpage2.json");
        server.enqueue(new MockResponse().setBody(page1Body));
        server.enqueue(new MockResponse().setBody(page2Body));
        server.start();
        LocationsCatalog catalog = new CatalogController().retrieveLocationsCatalog(server::url, "SWT", "SI", null);
        List<LocationsCatalogEntry> entries = catalog.getEntries();
        assertEquals(500, entries.size());
        assertNotNull(catalog.getNextPage());
        assertEquals(500, catalog.getPageSize());
        assertNull(catalog.getPage());
        assertEquals(68233, catalog.getTotal());
        LocationsCatalogEntry catalogEntry = entries.get(0);
        assertEquals("000512.%.Ave.~1Day.0.abc", catalogEntry.getFullName());
        assertEquals("SWT", catalogEntry.getOffice());

        catalog = new CatalogController().retrieveLocationsCatalog(server::url,
            "SWT", "SI", catalog.getNextPage());
        entries = catalog.getEntries();
        assertEquals(500, entries.size());
        assertNotNull(catalog.getNextPage());
        assertEquals(500, catalog.getPageSize());
        assertNull(catalog.getPage());
        assertEquals(68233, catalog.getTotal());
        catalogEntry = entries.get(0);
        assertEquals("16FFA636.Volt-Battery Load.Inst.1Hour.0.Ccp-Rev", catalogEntry.getFullName());
        assertEquals("SWT", catalogEntry.getOffice());
    }

    @Test
    void testCwmsRadarDown() throws IOException {
        MockWebServer server = new MockWebServer();
        String collect = readJsonFile(server, "radar/json/catalog_loc.json");
        server.enqueue(new MockResponse().setBody(collect));
        server.start();
        CatalogController timeSeriesController = new CatalogController();
        assertThrows(ClientNotFoundException.class,
            () -> timeSeriesController.retrieveLocationsCatalog(s -> HttpUrl.parse("http://localhost:11999" + s), "SWT", "SI", null));
    }

}
