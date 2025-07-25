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

package mil.army.usace.hec.cwms.data.api.client.controllers;

import mil.army.usace.hec.cwms.data.api.client.model.BinaryTimeSeries;
import mil.army.usace.hec.cwms.data.api.client.model.BinaryTimeSeriesRow;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestBinaryTimeSeriesController extends TestController {

    @Test
    void testRetrieveTimeSeries() throws IOException {
        String collect = readJsonFile("radar/v2/json/binarytimeseries.json");

        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Instant start = ZonedDateTime.of(2024, 2, 12, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant end = ZonedDateTime.of(2024, 2, 12, 2, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        BinaryTimeSeriesEndpointInput.GetAll input = BinaryTimeSeriesEndpointInput.getAll("TEST.Binary.Inst.1Hour.0.MockTest", "SWT", start, end)
                .page(null);
        BinaryTimeSeries timeSeries = new BinaryTimeSeriesController().retrieveTimeSeries(buildConnectionInfo(), input);
        assertEquals("SWT", timeSeries.getOfficeId());
        assertEquals("TEST.Binary.Inst.1Hour.0.MockTest", timeSeries.getName());
        assertEquals(0, timeSeries.getIntervalOffset());
        assertEquals("America/Los_Angeles", timeSeries.getTimeZone());
        assertEquals("MAX_AGGREGATE", timeSeries.getDateVersionType().toString());
        assertEquals(start, timeSeries.getVersionDate());
        List<BinaryTimeSeriesRow> regularBinaryValues = timeSeries.getBinaryValues();
        assertEquals(1, regularBinaryValues.size());
        BinaryTimeSeriesRow binaryRow = regularBinaryValues.get(0);
        assertEquals(start, binaryRow.getDateTime());
        assertEquals(start, binaryRow.getDataEntryDate());
        assertArrayEquals("Hello, World".getBytes(), binaryRow.getBinaryValue());
        assertEquals(0, binaryRow.getDestFlag());
        assertEquals("text/plain", binaryRow.getMediaType());
        assertEquals("filename.txt", binaryRow.getFilename());
        assertEquals("https://localhost:59947/cwms-data/timeseries/binary/Test.Flow.Inst.1Hour.0.raw/value?office=SPK&version-date=2008-05-01T15%3A00%3A00Z", binaryRow.getValueUrl());
        assertEquals(0, binaryRow.getQualityCode());
    }

    @Test
    void testRetrieveFromUrl() throws IOException {
        String collect = readJsonFile("radar/v2/json/binarytimeseries.json");

        // create large string to enqueue
        // considering 1 character = 2 bytes in Java, hence dividing by 2 to get character count for 100KB size
        int size = 1024 * 100 / 2;
        Random random = new Random();
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            // assuming only ascii characters.
            sb.append((char) (random.nextInt(26) + 'a'));
        }
        String largeString = sb.toString();
        byte[] b = largeString.getBytes(StandardCharsets.UTF_8);

        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(largeString);
        mockHttpServer.start();

        Instant start = ZonedDateTime.of(2024, 2, 12, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant end = ZonedDateTime.of(2024, 2, 12, 2, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        BinaryTimeSeriesEndpointInput.GetAll input = BinaryTimeSeriesEndpointInput.getAll("TEST.Binary.Inst.1Hour.0.MockTest", "SWT", start, end)
                .page(null);

        BinaryTimeSeries timeSeries = new BinaryTimeSeriesController().retrieveTimeSeries(buildConnectionInfo(), input);
        List<BinaryTimeSeriesRow> regularBinaryValues = timeSeries.getBinaryValues();
        assertEquals(1, regularBinaryValues.size());

        BinaryTimeSeriesRow binaryRow = regularBinaryValues.get(0);
        assertEquals("https://localhost:59947/cwms-data/timeseries/binary/Test.Flow.Inst.1Hour.0.raw/value?office=SPK&version-date=2008-05-01T15%3A00%3A00Z", binaryRow.getValueUrl());

        String replacementString = String.format("http://localhost:%s", mockHttpServer.getPort());
        String updatedValueUrl = binaryRow.getValueUrl().replace("https://localhost:59947", replacementString);
        binaryRow.setValueUrl(updatedValueUrl);
        byte[] downloadedByteArray = new BinaryTimeSeriesController().getBytesFromUrl(buildConnectionInfo(), binaryRow);
        assertArrayEquals(downloadedByteArray, b);
    }

    @Test
    void testStoreTimeSeries() throws IOException {
        Logger.getLogger("").setLevel(Level.ALL);
        String collect = readJsonFile("radar/v2/json/binarytimeseries.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        BinaryTimeSeries timeSeries = RadarObjectMapper.mapJsonToObject(collect, BinaryTimeSeries.class);
        BinaryTimeSeriesController timeSeriesController = new BinaryTimeSeriesController();
        BinaryTimeSeriesEndpointInput.Post input = BinaryTimeSeriesEndpointInput.post(timeSeries);
        assertDoesNotThrow(() -> timeSeriesController.storeTimeSeries(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testDeleteTimeSeries() throws IOException {
        String collect = readJsonFile("radar/v2/json/binarytimeseries.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        BinaryTimeSeries timeSeries = RadarObjectMapper.mapJsonToObject(collect, BinaryTimeSeries.class);
        timeSeries.setName(timeSeries.getName() + (System.currentTimeMillis() % 100_000));
        BinaryTimeSeriesController timeSeriesController = new BinaryTimeSeriesController();
        timeSeriesController.storeTimeSeries(buildConnectionInfo(cookieJarSupplier), BinaryTimeSeriesEndpointInput.post(timeSeries));
        BinaryTimeSeriesEndpointInput.Delete input = BinaryTimeSeriesEndpointInput.delete(timeSeries.getName(), timeSeries.getOfficeId());
        assertDoesNotThrow(() -> timeSeriesController.deleteTimeSeries(buildConnectionInfo(cookieJarSupplier), input));
    }
}
