/*
 * MIT License
 *
 * Copyright (c) 2024 Hydrologic Engineering Center
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

import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.RegularTextTimeSeriesRow;
import mil.army.usace.hec.cwms.radar.client.model.TextTimeSeries;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class TestTextTimeSeriesController extends TestController {

    @Test
    void testRetrieveTimeSeries() throws IOException {
        String collect = readJsonFile("radar/v2/json/texttimeseries.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Instant start = ZonedDateTime.of(2024, 2, 12, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant end = ZonedDateTime.of(2024, 2, 12, 2, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        TextTimeSeriesEndpointInput.GetAll input = TextTimeSeriesEndpointInput.getAll("TEST.Text.Inst.1Hour.0.MockTest", "SWT", start, end)
                .page(null);
        TextTimeSeries timeSeries = new TextTimeSeriesController().retrieveTimeSeries(buildConnectionInfo(), input);
        assertEquals("SWT", timeSeries.getOfficeId());
        assertEquals("TEST.Text.Inst.1Hour.0.MockTest", timeSeries.getName());
        assertEquals(0, timeSeries.getIntervalOffset());
        assertEquals("UTC", timeSeries.getTimeZone());
        assertEquals(TextTimeSeries.DateVersionTypeEnum.MAX_AGGREGATE, timeSeries.getDateVersionType());
        assertEquals(start, timeSeries.getVersionDate());
        List<RegularTextTimeSeriesRow> regularTextValues = timeSeries.getRegularTextValues();
        assertEquals(2, regularTextValues.size());
        RegularTextTimeSeriesRow regularRow = regularTextValues.get(0);
        assertEquals(start, regularRow.getDateTime());
        assertEquals(start, regularRow.getDataEntryDate());
        assertEquals("Hello, Davis", regularRow.getTextValue());
        assertEquals("filename.txt", regularRow.getFilename());
        assertEquals("application/json", regularRow.getMediaType());
        assertEquals(0, regularRow.getQualityCode());
        assertEquals(1, regularRow.getDestFlag());
        assertEquals("www.testHelloDavis.com", regularRow.getValueUrl());
    }

    @Test
    void testStoreTimeSeries() throws IOException {
        Logger.getLogger("").setLevel(Level.ALL);
        String collect = readJsonFile("radar/v2/json/texttimeseries.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TextTimeSeries timeSeries = RadarObjectMapper.mapJsonToObject(collect, TextTimeSeries.class);
        TextTimeSeriesController timeSeriesController = new TextTimeSeriesController();
        TextTimeSeriesEndpointInput.Post input = TextTimeSeriesEndpointInput.post(timeSeries);
        assertDoesNotThrow(() -> timeSeriesController.storeTimeSeries(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testDeleteTimeSeries() throws IOException {
        String collect = readJsonFile("radar/v2/json/texttimeseries.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TextTimeSeries timeSeries = RadarObjectMapper.mapJsonToObject(collect, TextTimeSeries.class);
        timeSeries.setName(timeSeries.getName() + (System.currentTimeMillis() % 100_000));
        TextTimeSeriesController timeSeriesController = new TextTimeSeriesController();
        timeSeriesController.storeTimeSeries(buildConnectionInfo(cookieJarSupplier), TextTimeSeriesEndpointInput.post(timeSeries));
        TextTimeSeriesEndpointInput.Delete input = TextTimeSeriesEndpointInput.delete(timeSeries.getName(), timeSeries.getOfficeId());
        assertDoesNotThrow(() -> timeSeriesController.deleteTimeSeries(buildConnectionInfo(cookieJarSupplier), input));
    }
}
