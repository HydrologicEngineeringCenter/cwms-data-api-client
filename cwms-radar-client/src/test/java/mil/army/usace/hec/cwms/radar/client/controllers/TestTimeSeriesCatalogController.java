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
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfoBuilder;
import mil.army.usace.hec.cwms.http.client.ServerNotFoundException;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesCatalog;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesCatalogEntry;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesExtents;
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
        assertEquals(13135, catalog.getTotal());
        TimeSeriesCatalogEntry catalogEntry = entries.get(0);
        assertEquals("106440.Flow.Inst.~1Day.0.-1040802796", catalogEntry.getTimeSeriesId());
        assertEquals("cms", catalogEntry.getUnits());
        assertEquals("SWT", catalogEntry.getOffice());
        assertEquals("UTC", catalogEntry.getLocationTimeZone());
        assertEquals(0, catalogEntry.getIntervalOffsetMinutes());
        List<TimeSeriesExtents> extents = catalogEntry.getExtents();
        TimeSeriesExtents timeSeriesExtents = extents.get(0);
        assertEquals(ZonedDateTime.of(2005, 1, 2, 0, 0, 0, 0, ZoneId.of("UTC")), timeSeriesExtents.getEarliestTime());
        assertEquals(ZonedDateTime.of(2005, 2, 1, 0, 0, 0, 0, ZoneId.of("UTC")), timeSeriesExtents.getLatestTime());
        assertEquals(ZonedDateTime.of(2018, 7, 5, 20, 40, 59, 398000000, ZoneId.of("UTC")), timeSeriesExtents.getLastUpdate());
        assertNull(timeSeriesExtents.getVersionTime());
    }

    @Test
    void testRetrieveTimeSeriesCatalogTsidFilter() throws IOException {
        String collect = readJsonFile("radar/v2/json/catalog_ts_idfilter.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesCatalogEndpointInput input = new TimeSeriesCatalogEndpointInput()
            .officeId("SWT")
            .unitSystem("SI")
            .timeSeriesIdFilter("ACSO2.Irrad.Ave.1Hour.1Hour.Raw-Mesonet")
            .pageSize(500);
        TimeSeriesCatalog catalog = new CatalogController().retrieveTimeSeriesCatalog(buildConnectionInfo(), input);
        List<TimeSeriesCatalogEntry> entries = catalog.getEntries();
        assertEquals(1, entries.size());
        assertNull(catalog.getNextPage());
        assertEquals(500, catalog.getPageSize());
        assertNull(catalog.getPage());
        assertEquals(1, catalog.getTotal());
        TimeSeriesCatalogEntry catalogEntry = entries.get(0);
        assertEquals("ACSO2.Irrad.Ave.1Hour.1Hour.Raw-Mesonet", catalogEntry.getTimeSeriesId());
        assertEquals("W/m2", catalogEntry.getUnits());
        assertEquals("SWT", catalogEntry.getOffice());
        assertEquals("US/Central", catalogEntry.getLocationTimeZone());
        assertEquals(0, catalogEntry.getIntervalOffsetMinutes());
        List<TimeSeriesExtents> extents = catalogEntry.getExtents();
        TimeSeriesExtents timeSeriesExtents = extents.get(0);
        assertEquals(ZonedDateTime.of(2016, 6, 1, 1, 0, 0, 0, ZoneId.of("UTC")), timeSeriesExtents.getEarliestTime());
        assertEquals(ZonedDateTime.of(2018, 8, 29, 21, 0, 0, 0, ZoneId.of("UTC")), timeSeriesExtents.getLatestTime());
        assertEquals(ZonedDateTime.of(2018, 8, 29, 21, 0, 0, 0, ZoneId.of("UTC")), timeSeriesExtents.getVersionTime());
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
        assertEquals(13135, catalog.getTotal());
        TimeSeriesCatalogEntry catalogEntry = entries.get(0);
        assertEquals("106440.Flow.Inst.~1Day.0.-1040802796", catalogEntry.getTimeSeriesId());
        assertEquals("cms", catalogEntry.getUnits());
        assertEquals("SWT", catalogEntry.getOffice());
        assertEquals("UTC", catalogEntry.getLocationTimeZone());
        assertEquals(0, catalogEntry.getIntervalOffsetMinutes());
        List<TimeSeriesExtents> extents = catalogEntry.getExtents();
        TimeSeriesExtents timeSeriesExtents = extents.get(0);
        assertEquals(ZonedDateTime.of(2005, 1, 2, 0, 0, 0, 0, ZoneId.of("UTC")), timeSeriesExtents.getEarliestTime());
        assertEquals(ZonedDateTime.of(2005, 2, 1, 0, 0, 0, 0, ZoneId.of("UTC")), timeSeriesExtents.getLatestTime());
        assertNull(timeSeriesExtents.getVersionTime());

        input.cursor(catalog.getNextPage());
        catalog = new CatalogController().retrieveTimeSeriesCatalog(buildConnectionInfo(), input);
        entries = catalog.getEntries();
        assertEquals(500, entries.size());
        assertNotNull(catalog.getNextPage());
        assertEquals(500, catalog.getPageSize());
        assertNotNull(catalog.getPage());
        assertEquals(13135, catalog.getTotal());
        catalogEntry = entries.get(0);
        assertEquals("ARBU.Elev.Inst.1Hour.0.Decodes-Raw", catalogEntry.getTimeSeriesId());
        assertEquals("m", catalogEntry.getUnits());
        assertEquals("SWT", catalogEntry.getOffice());
        assertEquals("US/Central", catalogEntry.getLocationTimeZone());
        assertEquals(0, catalogEntry.getIntervalOffsetMinutes());
        timeSeriesExtents = catalogEntry.getExtents().get(0);
        assertEquals(ZonedDateTime.of(2013, 12, 6, 20, 0, 0, 0, ZoneId.of("UTC")), timeSeriesExtents.getEarliestTime());
        assertEquals(ZonedDateTime.of(2018, 8, 29, 23, 0, 0, 0, ZoneId.of("UTC")), timeSeriesExtents.getLatestTime());
        assertNull(timeSeriesExtents.getVersionTime());
    }

    @Test
    void testCwmsRadarDown() throws IOException {
        String collect = readJsonFile("radar/v2/json/catalog_ts.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        CatalogController timeSeriesController = new CatalogController();
        assertThrows(ServerNotFoundException.class,
            () -> timeSeriesController.retrieveTimeSeriesCatalog(new ApiConnectionInfoBuilder("http://localhost:11999").build(),
                new TimeSeriesCatalogEndpointInput()));
    }

}
