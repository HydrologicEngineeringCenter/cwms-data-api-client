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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import mil.army.usace.hec.cwms.radar.client.NoDataFoundException;
import mil.army.usace.hec.cwms.radar.client.ServerNotFoundException;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeries;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

class TestTimeSeriesController {

    private static final String BASE_URL = "http://localhost:11524";

    @Test
    void testRetrieveTimeSeries() throws IOException {
        Path path = new File(getClass().getClassLoader().getResource("radar/v2/json/timeseries.json")
            .getFile()).toPath();
        String collect = String.join("\n", Files.readAllLines(path));
        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(collect));
        server.start();
        Instant start = ZonedDateTime.of(2018, 1, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant end = ZonedDateTime.of(2018, 2, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        TimeSeries timeSeries = new TimeSeriesController().retrieveTimeSeries(server::url,
            "SWT", "arbu.Elev.Inst.1Hour.0.Ccp-Rev", "SI", "NAVD88",
            start, end, null, null);
        assertEquals(500, timeSeries.getValues().size());
        assertEquals(745, timeSeries.getTotal());
        assertEquals("SWT", timeSeries.getOfficeId());
        assertEquals("m", timeSeries.getUnits());
        assertEquals(Duration.ZERO, timeSeries.getInterval());
        assertEquals("ARBU.Elev.Inst.1Hour.0.Ccp-Rev", timeSeries.getName());
        assertEquals(start, timeSeries.getBegin().toInstant());
        Instant lastTime = Instant.ofEpochMilli(
            timeSeries.getValues().get(timeSeries.getValues().size() - 1).getDateTime());
        assertTrue(end.isAfter(lastTime));
    }

    @Test
    void testRetrieveTimeSeriesPagination() throws IOException {
        Path page1 =
            new File(getClass().getClassLoader().getResource("radar/v2/json/timeseries_page1.json")
                .getFile()).toPath();
        Path page2 =
            new File(getClass().getClassLoader().getResource("radar/v2/json/timeseries_page2.json")
                .getFile()).toPath();
        String page1Body = String.join("\n", Files.readAllLines(page1));
        String page2Body = String.join("\n", Files.readAllLines(page2));
        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(page1Body));
        server.enqueue(new MockResponse().setBody(page2Body));
        server.start();
        Instant start = ZonedDateTime.of(2018, 1, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant end = ZonedDateTime.of(2018, 2, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        TimeSeries timeSeries = new TimeSeriesController().retrieveTimeSeries(server::url,
            "SWT", "arbu.Elev.Inst.1Hour.0.Ccp-Rev", "SI", "NAVD88",
            start, end, null, 500);
        assertEquals(500, timeSeries.getValues().size());
        assertEquals(745, timeSeries.getTotal());
        assertEquals("SWT", timeSeries.getOfficeId());
        assertEquals("m", timeSeries.getUnits());
        assertEquals(Duration.ZERO, timeSeries.getInterval());
        assertEquals("ARBU.Elev.Inst.1Hour.0.Ccp-Rev", timeSeries.getName());
        assertEquals(start, timeSeries.getBegin().toInstant());
        Instant firstTime = Instant.ofEpochMilli(
            timeSeries.getValues().get(0).getDateTime());
        Instant lastTime = Instant.ofEpochMilli(
            timeSeries.getValues().get(timeSeries.getValues().size() - 1).getDateTime());
        assertTrue(end.isAfter(lastTime));
        assertEquals(start, firstTime);
        timeSeries = new TimeSeriesController().retrieveTimeSeries(server::url,
            "SWT", "arbu.Elev.Inst.1Hour.0.Ccp-Rev", "SI", "NAVD88",
            start, end, timeSeries.getNextPage(), 500);
        assertEquals(245, timeSeries.getValues().size());
        assertEquals(745, timeSeries.getTotal());
        assertEquals("SWT", timeSeries.getOfficeId());
        assertEquals("m", timeSeries.getUnits());
        assertEquals(Duration.ZERO, timeSeries.getInterval());
        assertEquals("ARBU.Elev.Inst.1Hour.0.Ccp-Rev", timeSeries.getName());
        assertEquals(start, timeSeries.getBegin().toInstant());
        Instant newFirstTime = Instant.ofEpochMilli(
            timeSeries.getValues().get(0).getDateTime());
        Instant newLastTime = Instant.ofEpochMilli(
            timeSeries.getValues().get(timeSeries.getValues().size() - 1).getDateTime());
        assertEquals(end, newLastTime);
        assertTrue(start.isBefore(newFirstTime));
    }

    @Test
    void testCwmsRadarDown() throws IOException {
        Path path = new File(getClass().getClassLoader().getResource("radar/v2/json/timeseries.json")
            .getFile()).toPath();
        String collect = String.join("\n", Files.readAllLines(path));
        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody(collect));
        server.start();
        Instant start = ZonedDateTime.of(2018, 1, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant end = ZonedDateTime.of(2018, 2, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        TimeSeriesController timeSeriesController = new TimeSeriesController();
        assertThrows(ServerNotFoundException.class, () -> timeSeriesController.retrieveTimeSeries(s -> HttpUrl.parse("http://localhost:11999" + s),
            "SWT", "arbu.Elev.Inst.1Hour.0.Bogus", "SI", "NAVD88",
            start, end, null, null));
    }

    @Test
    void testTimeSeriesNotFound() throws IOException {
        Path path = new File(getClass().getClassLoader().getResource("radar/v2/json/timeseries_notfound.json")
            .getFile()).toPath();
        String collect = String.join("\n", Files.readAllLines(path));
        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setResponseCode(404).setBody(collect));
        server.start();
        Instant start = ZonedDateTime.of(2018, 1, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant end = ZonedDateTime.of(2018, 2, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        TimeSeriesController timeSeriesController = new TimeSeriesController();
        assertThrows(NoDataFoundException.class, () -> timeSeriesController.retrieveTimeSeries(server::url,
            "SWT", "arbu.Elev.Inst.1Hour.0.Bogus", "SI", "NAVD88",
            start, end, null, null));
    }
}
