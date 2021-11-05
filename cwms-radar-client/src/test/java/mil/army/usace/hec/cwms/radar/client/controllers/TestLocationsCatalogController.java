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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.ServerNotFoundException;
import mil.army.usace.hec.cwms.radar.client.model.LocationCatalog;
import mil.army.usace.hec.cwms.radar.client.model.LocationCatalogEntry;
import org.junit.jupiter.api.Test;

class TestLocationsCatalogController extends TestController {

    @Test
    void testRetrieveTimeSeriesCatalog() throws IOException {
        String collect = readJsonFile("radar/v2/json/catalog_loc.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LocationCatalogEndpointInput input = new LocationCatalogEndpointInput()
            .officeId("SWT")
            .unitSystem("SI");
        LocationCatalog catalog = new CatalogController().retrieveLocationCatalog(buildConnectionInfo(), input);
        List<LocationCatalogEntry> entries = catalog.getEntries();
        assertEquals(500, entries.size());
        assertNotNull(catalog.getNextPage());
        assertEquals(500, catalog.getPageSize());
        assertNotNull(catalog.getPage());
        assertEquals(6902, catalog.getTotal());
        LocationCatalogEntry catalogEntry = entries.get(0);
        assertEquals("782139", catalogEntry.getName());
        assertEquals("SWT", catalogEntry.getOffice());
        assertEquals("Davis", catalogEntry.getNearestCity());
        assertEquals("Resource Management Associates", catalogEntry.getPublicName());
        assertEquals("Resource Management Associates - Water Resources Engineering", catalogEntry.getLongName());
        assertEquals(
            "At RMA, our team of engineers and software developers create and apply advanced numerical models and software systems that support water resource management and environmental stewardship.",
            catalogEntry.getDescription());
        assertEquals("SITE", catalogEntry.getKind());
        assertEquals("Contractor", catalogEntry.getType());
        assertEquals("America/Los_Angeles", catalogEntry.getTimeZone());
        assertEquals(38.563258, catalogEntry.getLatitude());
        assertEquals(-121.730321, catalogEntry.getLongitude());
        assertEquals(38.56, catalogEntry.getPublishedLatitude());
        assertEquals(-121.73, catalogEntry.getPublishedLongitude());
        assertEquals("NAD83", catalogEntry.getHorizontalDatum());
        assertEquals(41.3, catalogEntry.getElevation());
        assertEquals("m", catalogEntry.getUnit());
        assertEquals("NGVD29", catalogEntry.getVerticalDatum());
        assertEquals("UNITED STATES", catalogEntry.getNation());
        assertEquals("CA", catalogEntry.getState());
        assertEquals("Yolo", catalogEntry.getCounty());
        assertEquals("SPD", catalogEntry.getBoundingOffice());
        assertEquals("RMA", catalogEntry.getMapLabel());
        assertTrue(catalogEntry.isActive());
        assertTrue(catalogEntry.getAliases().isEmpty());
    }

    @Test
    void testRetrieveTimeSeriesCatalogPagination() throws IOException {
        String page1Body = readJsonFile("radar/v2/json/catalog_locpage1.json");
        String page2Body = readJsonFile("radar/v2/json/catalog_locpage2.json");
        mockHttpServer.enqueue(page1Body);
        mockHttpServer.enqueue(page2Body);
        mockHttpServer.start();
        LocationCatalogEndpointInput input = new LocationCatalogEndpointInput()
            .officeId("SWT")
            .unitSystem("SI");
        LocationCatalog catalog = new CatalogController().retrieveLocationCatalog(buildConnectionInfo(), input);
        List<LocationCatalogEntry> entries = catalog.getEntries();
        assertEquals(500, entries.size());
        assertNotNull(catalog.getNextPage());
        assertEquals(500, catalog.getPageSize());
        assertNotNull(catalog.getPage());
        assertEquals(6902, catalog.getTotal());
        LocationCatalogEntry catalogEntry = entries.get(0);
        assertEquals("782139", catalogEntry.getName());
        assertEquals("SWT", catalogEntry.getOffice());
        assertEquals("SWT", catalogEntry.getOffice());
        assertEquals("Davis", catalogEntry.getNearestCity());
        assertEquals("Resource Management Associates", catalogEntry.getPublicName());
        assertEquals("Resource Management Associates - Water Resources Engineering", catalogEntry.getLongName());
        assertEquals(
            "At RMA, our team of engineers and software developers create and apply advanced numerical models and software systems that support water resource management and environmental stewardship.",
            catalogEntry.getDescription());
        assertEquals("SITE", catalogEntry.getKind());
        assertEquals("Contractor", catalogEntry.getType());
        assertEquals("America/Los_Angeles", catalogEntry.getTimeZone());
        assertEquals(38.563258, catalogEntry.getLatitude());
        assertEquals(-121.730321, catalogEntry.getLongitude());
        assertEquals(38.56, catalogEntry.getPublishedLatitude());
        assertEquals(-121.73, catalogEntry.getPublishedLongitude());
        assertEquals("NAD83", catalogEntry.getHorizontalDatum());
        assertEquals(41.3, catalogEntry.getElevation());
        assertEquals("m", catalogEntry.getUnit());
        assertEquals("NGVD29", catalogEntry.getVerticalDatum());
        assertEquals("UNITED STATES", catalogEntry.getNation());
        assertEquals("CA", catalogEntry.getState());
        assertEquals("Yolo", catalogEntry.getCounty());
        assertEquals("SPD", catalogEntry.getBoundingOffice());
        assertEquals("RMA", catalogEntry.getMapLabel());

        input.cursor(catalog.getNextPage());
        catalog = new CatalogController().retrieveLocationCatalog(buildConnectionInfo(), input);
        entries = catalog.getEntries();
        assertEquals(500, entries.size());
        assertNotNull(catalog.getNextPage());
        assertEquals(500, catalog.getPageSize());
        assertNotNull(catalog.getPage());
        assertEquals(6902, catalog.getTotal());
        catalogEntry = entries.get(0);
        assertEquals("587064", catalogEntry.getName());
        assertEquals("SWT", catalogEntry.getOffice());
        assertEquals("SWT", catalogEntry.getOffice());
        assertEquals("Davis", catalogEntry.getNearestCity());
        assertEquals("Resource Management Associates", catalogEntry.getPublicName());
        assertEquals("Resource Management Associates - Water Resources Engineering", catalogEntry.getLongName());
        assertEquals(
            "At RMA, our team of engineers and software developers create and apply advanced numerical models and software systems that support water resource management and environmental stewardship.",
            catalogEntry.getDescription());
        assertEquals("SITE", catalogEntry.getKind());
        assertEquals("Contractor", catalogEntry.getType());
        assertEquals("America/Los_Angeles", catalogEntry.getTimeZone());
        assertEquals(38.563258, catalogEntry.getLatitude());
        assertEquals(-121.730321, catalogEntry.getLongitude());
        assertEquals(38.56, catalogEntry.getPublishedLatitude());
        assertEquals(-121.73, catalogEntry.getPublishedLongitude());
        assertEquals("NAD83", catalogEntry.getHorizontalDatum());
        assertEquals(41.3, catalogEntry.getElevation());
        assertEquals("m", catalogEntry.getUnit());
        assertEquals("NGVD29", catalogEntry.getVerticalDatum());
        assertEquals("UNITED STATES", catalogEntry.getNation());
        assertEquals("CA", catalogEntry.getState());
        assertEquals("Yolo", catalogEntry.getCounty());
        assertEquals("SPD", catalogEntry.getBoundingOffice());
        assertEquals("RMA", catalogEntry.getMapLabel());
    }

    @Test
    void testCwmsRadarDown() throws IOException {
        String collect = readJsonFile("radar/v2/json/catalog_loc.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        CatalogController timeSeriesController = new CatalogController();
        assertThrows(ServerNotFoundException.class,
            () -> timeSeriesController.retrieveLocationCatalog(new ApiConnectionInfo("http://localhost:11999"), new LocationCatalogEndpointInput()));
    }

}
