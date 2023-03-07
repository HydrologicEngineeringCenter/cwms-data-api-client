/*
 * MIT License
 *
 * Copyright (c) 2023 Hydrologic Engineering Center
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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfoBuilder;
import mil.army.usace.hec.cwms.http.client.MockHttpServer;
import mil.army.usace.hec.cwms.http.client.NoDataFoundException;
import mil.army.usace.hec.cwms.http.client.ServerNotFoundException;
import mil.army.usace.hec.cwms.radar.client.model.Offset;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeries;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesValues;
import mil.army.usace.hec.cwms.radar.client.model.VerticalDatumInfo;
import org.junit.jupiter.api.Test;

class TestTimeSeriesController extends TestController {

    @Test
    void testRetrieveTimeSeries() throws IOException {
        String collect = readJsonFile("radar/v2/json/timeseries.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Instant start = ZonedDateTime.of(2018, 1, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant end = ZonedDateTime.of(2018, 2, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        TimeSeriesEndpointInput.GetOne input = TimeSeriesEndpointInput.getOne("arbu.Elev.Inst.1Hour.0.Ccp-Rev")
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
        TimeSeriesValues v1 = timeSeries.getValues().get(0);
        assertEquals(start.toEpochMilli(), v1.getDateTime());
        assertEquals(265.54786, v1.getValue(), .001);
        assertEquals(0, v1.getQualityCode());
        VerticalDatumInfo verticalDatumInfo = timeSeries.getVerticalDatumInfo();
        assertEquals(243.8, verticalDatumInfo.getElevation(), .001);
        assertEquals("SWT", verticalDatumInfo.getOffice());
        assertEquals("m", verticalDatumInfo.getUnit());
        assertEquals("NGVD-29", verticalDatumInfo.getNativeDatum());
        assertEquals("ARBU", verticalDatumInfo.getLocation());
        List<Offset> offsets = verticalDatumInfo.getOffsets();
        Offset offset = offsets.get(0);
        assertEquals(0.0632, offset.getValue(), .001);
        assertEquals("NAVD-88", offset.getToDatum());
        assertTrue(offset.isEstimate());
    }

    @Test
    void testRetrieveTimeSeriesWithoutVerticalDatumOffsets() throws IOException {
        String collect = readJsonFile("radar/v2/json/timeseries_no_vert_offsets.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Instant start = ZonedDateTime.of(2022, 7, 21, 16, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant end = ZonedDateTime.of(2022, 7, 22, 16, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        TimeSeriesEndpointInput.GetOne input = TimeSeriesEndpointInput.getOne("CHCR-Cherry_Creek_Dam-Cherry.Elev.Inst.1Day.0.0168")
            .officeId("NWD")
            .unit("EN")
            .begin(start)
            .end(end)
            .page(null);
        TimeSeries timeSeries = new TimeSeriesController().retrieveTimeSeries(buildConnectionInfo(), input);
        VerticalDatumInfo verticalDatumInfo = timeSeries.getVerticalDatumInfo();
        assertNull(verticalDatumInfo.getElevation());
        assertEquals("NWD", verticalDatumInfo.getOffice());
        assertEquals("ft", verticalDatumInfo.getUnit());
        assertEquals("UNKNOWN", verticalDatumInfo.getNativeDatum());
        assertEquals("CHCR-Cherry_Creek_Dam-Cherry", verticalDatumInfo.getLocation());
        List<Offset> offsets = verticalDatumInfo.getOffsets();
        assertNotNull(offsets);
    }

    @Test
    void testRetrieveTimeSeriesPagination() throws IOException {
        String page1Body = readJsonFile("radar/v2/json/timeseries_page1.json");
        String page2Body = readJsonFile("radar/v2/json/timeseries_page2.json");
        mockHttpServer.enqueue(page1Body);
        mockHttpServer.start();
        Instant start = ZonedDateTime.of(2018, 1, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant end = ZonedDateTime.of(2018, 2, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        TimeSeriesEndpointInput.GetOne input = TimeSeriesEndpointInput.getOne("arbu.Elev.Inst.1Hour.0.Ccp-Rev")
            .officeId("SWT")
            .unit("SI")
            .verticalDatum("NAVD88")
            .begin(start)
            .end(end)
            .page(null)
            .pageSize(500);
        mockHttpServer.enqueue(page2Body);
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
        String collect = readJsonFile("radar/v2/json/timeseries.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesController timeSeriesController = new TimeSeriesController();
        assertThrows(ServerNotFoundException.class, () -> timeSeriesController.retrieveTimeSeries(
            new ApiConnectionInfoBuilder("http://localhost:11999").build(), TimeSeriesEndpointInput.getOne("")));
    }

    @Test
    void testTimeSeriesNotFound() throws IOException {
        String collect = readJsonFile("radar/v2/json/timeseries_notfound.json");
        mockHttpServer.enqueue(404, collect);
        mockHttpServer.start();
        TimeSeriesController timeSeriesController = new TimeSeriesController();
        TimeSeriesEndpointInput.GetOne input = TimeSeriesEndpointInput.getOne("arbu.Elev.Inst.1Hour.0.bogus");
        assertThrows(NoDataFoundException.class, () -> timeSeriesController.retrieveTimeSeries(buildConnectionInfo(), input));
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
                        TimeSeriesEndpointInput.GetOne input = TimeSeriesEndpointInput.getOne("arbu.Elev.Inst.1Hour.0.Ccp-Rev")
                            .officeId("SWT")
                            .unit("SI")
                            .verticalDatum("NAVD88")
                            .begin(start)
                            .end(end)
                            .page(null);
                        String baseUrl = String.format("http://localhost:%s", mockHttpServer.getPort());
                        ApiConnectionInfo apiConnectionInfo = new ApiConnectionInfoBuilder(baseUrl).build();
                        TimeSeries timeSeries = new TimeSeriesController().retrieveTimeSeries(apiConnectionInfo, input);
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
                    } finally {
                        if (mockHttpServer != null) {
                            mockHttpServer.shutdown();
                        }
                    }

                } catch (Exception e) {
                    fail(e);
                }
            });
            futures.add(future);
        }
        for (CompletableFuture<Void> future : futures) {
            future.get();
        }
    }

    @Test
    void testStoreTimeSeries() throws IOException {
        Logger.getLogger("").setLevel(Level.ALL);
        String collect = readJsonFile("radar/v2/json/timeseries.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeries timeSeries = RadarObjectMapper.mapJsonToObject(collect, TimeSeries.class);
        TimeSeriesController timeSeriesController = new TimeSeriesController();
        TimeSeriesEndpointInput.Post input = TimeSeriesEndpointInput.post(timeSeries);
        assertDoesNotThrow(() -> timeSeriesController.storeTimeSeries(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testDeleteTimeSeries() throws IOException {
        String collect = readJsonFile("radar/v2/json/timeseries.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeries timeSeries = RadarObjectMapper.mapJsonToObject(collect, TimeSeries.class);
        timeSeries.setName(timeSeries.getName() + (System.currentTimeMillis() % 100_000));
        TimeSeriesController timeSeriesController = new TimeSeriesController();
        timeSeriesController.storeTimeSeries(buildConnectionInfo(cookieJarSupplier), TimeSeriesEndpointInput.post(timeSeries));
        TimeSeriesEndpointInput.Delete input = TimeSeriesEndpointInput.delete(timeSeries.getName(), timeSeries.getOfficeId());
        assertDoesNotThrow(() -> timeSeriesController.deleteTimeSeries(buildConnectionInfo(cookieJarSupplier), input));
    }
}
