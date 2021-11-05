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
import mil.army.usace.hec.cwms.htp.client.MockHttpServer;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.NoDataFoundException;
import mil.army.usace.hec.cwms.http.client.ServerNotFoundException;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeries;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestTimeSeriesController {

    private static final String BASE_URL = "http://localhost:11524";
    private static MockHttpServer mockHttpServer;

    @BeforeEach
    void setUp() throws IOException {
        mockHttpServer = MockHttpServer.create();
    }

    @AfterEach
    void tearDown() throws IOException {
        mockHttpServer.shutdown();
    }

    private ApiConnectionInfo buildConnectionInfo() {
        String baseUrl = String.format("http://localhost:%s", mockHttpServer.getPort());
        return new ApiConnectionInfo(baseUrl);
    }

    @Test
    void testRetrieveTimeSeries() throws IOException {
        Path path = new File(getClass().getClassLoader().getResource("radar/v2/json/timeseries.json")
            .getFile()).toPath();
        String collect = String.join("\n", Files.readAllLines(path));
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Instant start = ZonedDateTime.of(2018, 1, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant end = ZonedDateTime.of(2018, 2, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        TimeSeriesEndpointInput input = new TimeSeriesEndpointInput("arbu.Elev.Inst.1Hour.0.Ccp-Rev")
            .officeId("SWT")
            .unit("SI")
            .verticalDatum("NAVD88")
            .begin(start)
            .end(end)
            .page(null);
        TimeSeries timeSeries = new TimeSeriesController().retrieveTimeSeries(buildConnectionInfo(), input);
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
        mockHttpServer.enqueue(page1Body);
        mockHttpServer.enqueue(page2Body);
        mockHttpServer.start();
        Instant start = ZonedDateTime.of(2018, 1, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant end = ZonedDateTime.of(2018, 2, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        TimeSeriesEndpointInput input = new TimeSeriesEndpointInput("arbu.Elev.Inst.1Hour.0.Ccp-Rev")
            .officeId("SWT")
            .unit("SI")
            .verticalDatum("NAVD88")
            .begin(start)
            .end(end)
            .page(null)
            .pageSize(500);
        TimeSeries timeSeries = new TimeSeriesController().retrieveTimeSeries(buildConnectionInfo(), input);
        assertEquals(500, timeSeries.getValues().size());
        assertEquals(745, timeSeries.getTotal());
        assertEquals("SWT", timeSeries.getOfficeId());
        assertEquals("m", timeSeries.getUnits());
        assertEquals(Duration.ZERO, timeSeries.getInterval());
        assertEquals("ARBU.Elev.Inst.1Hour.0.Ccp-Rev", timeSeries.getName());
        assertEquals(start, timeSeries.getBegin().toInstant());
        Instant firstTime = Instant.ofEpochMilli(timeSeries.getValues().get(0).getDateTime());
        Instant lastTime = Instant.ofEpochMilli(timeSeries.getValues().get(timeSeries.getValues().size() - 1).getDateTime());
        assertTrue(end.isAfter(lastTime));
        assertEquals(start, firstTime);
        input.page(timeSeries.getNextPage());
        timeSeries = new TimeSeriesController().retrieveTimeSeries(buildConnectionInfo(), input);
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
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesController timeSeriesController = new TimeSeriesController();
        assertThrows(ServerNotFoundException.class, () -> timeSeriesController.retrieveTimeSeries(new ApiConnectionInfo("http://localhost:11999"),
            new TimeSeriesEndpointInput("")));
    }

    @Test
    void testTimeSeriesNotFound() throws IOException {
        Path path = new File(getClass().getClassLoader().getResource("radar/v2/json/timeseries_notfound.json")
            .getFile()).toPath();
        String collect = String.join("\n", Files.readAllLines(path));
        mockHttpServer.enqueue(404, collect);
        mockHttpServer.start();
        Instant start = ZonedDateTime.of(2018, 1, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant end = ZonedDateTime.of(2018, 2, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        TimeSeriesController timeSeriesController = new TimeSeriesController();
        TimeSeriesEndpointInput input = new TimeSeriesEndpointInput("arbu.Elev.Inst.1Hour.0.bogus");
        assertThrows(NoDataFoundException.class, () -> timeSeriesController.retrieveTimeSeries(buildConnectionInfo(), input));
    }
}
