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
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import mil.army.usace.hec.cwms.htp.client.MockHttpServer;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.NoDataFoundException;
import mil.army.usace.hec.cwms.http.client.ServerNotFoundException;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeries;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestTimeSeriesController extends TestController {

    private static MockHttpServer mockHttpServer;

    @BeforeEach
    void setUp() throws IOException {
        mockHttpServer = MockHttpServer.create();
    }

    @AfterEach
    void tearDown() throws IOException {
        mockHttpServer.shutdown();
    }

    private ApiConnectionInfo buildConnectionInfo(MockHttpServer mockHttpServer) {
        String baseUrl = String.format("http://localhost:%s", mockHttpServer.getPort());
        return new ApiConnectionInfo(baseUrl);
    }

    @Test
    void testRetrieveTimeSeries() throws IOException {
        String collect = readJsonFile("radar/v2/json/timeseries.json");
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
        TimeSeries timeSeries = new TimeSeriesController().retrieveTimeSeries(buildConnectionInfo(mockHttpServer), input);
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
        String page1Body = readJsonFile("radar/v2/json/timeseries_page1.json");
        String page2Body = readJsonFile("radar/v2/json/timeseries_page2.json");
        mockHttpServer.enqueue(page1Body);
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
        mockHttpServer.enqueue(page2Body);
        TimeSeries timeSeries = new TimeSeriesController().retrieveTimeSeries(buildConnectionInfo(mockHttpServer), input);
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
        timeSeries = new TimeSeriesController().retrieveTimeSeries(buildConnectionInfo(mockHttpServer), input);
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
        String collect = readJsonFile("radar/v2/json/timeseries.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesController timeSeriesController = new TimeSeriesController();
        assertThrows(ServerNotFoundException.class, () -> timeSeriesController.retrieveTimeSeries(new ApiConnectionInfo("http://localhost:11999"),
            new TimeSeriesEndpointInput("")));
    }

    @Test
    void testTimeSeriesNotFound() throws IOException {
        String collect = readJsonFile("radar/v2/json/timeseries_notfound.json");
        mockHttpServer.enqueue(404, collect);
        mockHttpServer.start();
        TimeSeriesController timeSeriesController = new TimeSeriesController();
        TimeSeriesEndpointInput input = new TimeSeriesEndpointInput("arbu.Elev.Inst.1Hour.0.bogus");
        assertThrows(NoDataFoundException.class, () -> timeSeriesController.retrieveTimeSeries(buildConnectionInfo(mockHttpServer), input));
    }

    @Test
    void testMultiThreadedRetrieveTimeSeries() throws ExecutionException, InterruptedException {
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int fileNumber = i % 10 + 1;
            CompletableFuture<Void> future = CompletableFuture.runAsync(() ->
            {
                try {
                    String collect = readJsonFile("radar/v2/json/timeseries" + fileNumber + ".json");
                    MockHttpServer mockHttpServer = null;
                    try {
                        mockHttpServer = MockHttpServer.create();
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
                        TimeSeries timeSeries = new TimeSeriesController().retrieveTimeSeries(buildConnectionInfo(mockHttpServer), input);
                        assertEquals(500, timeSeries.getValues().size());
                        assertEquals(745, timeSeries.getTotal());
                        assertEquals("SWT", timeSeries.getOfficeId());
                        assertEquals("m", timeSeries.getUnits());
                        assertEquals(Duration.ZERO, timeSeries.getInterval());
                        assertEquals("ARBU" + fileNumber + ".Elev.Inst.1Hour.0.Ccp-Rev", timeSeries.getName());
                        assertEquals(start, timeSeries.getBegin().toInstant());
                        Instant lastTime = Instant.ofEpochMilli(
                            timeSeries.getValues().get(timeSeries.getValues().size() - 1).getDateTime());
                        assertTrue(end.isAfter(lastTime));
                    }
                    finally {
                        if (mockHttpServer != null) {
                            mockHttpServer.shutdown();
                        }
                    }

                }
                catch (Exception e) {
                    fail(e);
                }
            });
            futures.add(future);
        }
        for (CompletableFuture<Void> future : futures) {
            future.get();
        }
    }
}
