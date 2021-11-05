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
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.ServerNotFoundException;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesCatalog;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesCatalogEntry;
import org.junit.jupiter.api.Test;

class TestTimeSeriesCatalogController extends TestController {

    @Test
    void testRetrieveTimeSeriesCatalog() throws IOException {
        String collect = readJsonFile("radar/json/catalog_ts.json");
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
        String page1Body = readJsonFile("radar/json/catalog_tspage1.json");
        String page2Body = readJsonFile("radar/json/catalog_tspage2.json");
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
        String collect = readJsonFile("radar/json/catalog_ts.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        CatalogController timeSeriesController = new CatalogController();
        assertThrows(ServerNotFoundException.class,
            () -> timeSeriesController.retrieveTimeSeriesCatalog(new ApiConnectionInfo("http://localhost:11999"),
                new TimeSeriesCatalogEndpointInput()));
    }

}
