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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mil.army.usace.hec.cwms.http.client.NoDataFoundException;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesIdentifierDescriptor;
import org.junit.jupiter.api.Test;

class TestTimeSeriesIdentifierController extends TestController {

    @Test
    void testRetrieveTimeSeriesIdentifier() throws IOException {
        String collect = readJsonFile("radar/v2/json/timeseriesidentifier.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesIdentifierEndpointInput.GetOne input = TimeSeriesIdentifierEndpointInput.getOne("arbu.Elev.Inst.1Hour.0.Ccp-Rev", "SWT");
        TimeSeriesIdentifierDescriptor timeSeries = new TimeSeriesIdentifierController().retrieveTimeSeriesIdentifier(buildConnectionInfo(), input);
        assertEquals("ARBU.Elev.Inst.1Hour.0.Ccp-Rev", timeSeries.getTimeSeriesId());
        assertEquals(0, timeSeries.getIntervalOffsetMinutes());
        assertEquals("SWT", timeSeries.getOfficeId());
        assertEquals("US/Central", timeSeries.getTimezoneName());
        assertTrue(timeSeries.isActive());
    }

    @Test
    void testTimeSeriesIdentifierNotFound() throws IOException {
        String collect = readJsonFile("radar/v2/json/timeseriesidentifier_notfound.json");
        mockHttpServer.enqueue(404, collect);
        mockHttpServer.start();
        TimeSeriesIdentifierController timeSeriesController = new TimeSeriesIdentifierController();
        TimeSeriesIdentifierEndpointInput.GetOne input = TimeSeriesIdentifierEndpointInput.getOne("arbu.Elev.Inst.1Hour.0.bogus", "ABC");
        assertThrows(NoDataFoundException.class, () -> timeSeriesController.retrieveTimeSeriesIdentifier(buildConnectionInfo(), input));
    }

    @Test
    void testStoreTimeSeriesIdentifier() throws IOException {
        Logger.getLogger("").setLevel(Level.ALL);
        String collect = readJsonFile("radar/v2/json/timeseriesidentifier.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesIdentifierDescriptor timeSeries = RadarObjectMapper.mapJsonToObject(collect, TimeSeriesIdentifierDescriptor.class);
        TimeSeriesIdentifierController timeSeriesController = new TimeSeriesIdentifierController();
        TimeSeriesIdentifierEndpointInput.Post input = TimeSeriesIdentifierEndpointInput.post(timeSeries);
        assertDoesNotThrow(() -> timeSeriesController.storeTimeSeriesIdentifier(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testDeleteTimeSeriesIdentifier() throws IOException {
        String collect = readJsonFile("radar/v2/json/timeseriesidentifier.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesIdentifierDescriptor timeSeries = RadarObjectMapper.mapJsonToObject(collect, TimeSeriesIdentifierDescriptor.class);
        timeSeries.setTimeSeriesId(timeSeries.getTimeSeriesId() + (System.currentTimeMillis() % 100_000));
        TimeSeriesIdentifierController timeSeriesController = new TimeSeriesIdentifierController();
        timeSeriesController.storeTimeSeriesIdentifier(buildConnectionInfo(cookieJarSupplier), TimeSeriesIdentifierEndpointInput.post(timeSeries));
        TimeSeriesIdentifierEndpointInput.Delete input =
            TimeSeriesIdentifierEndpointInput.delete(timeSeries.getTimeSeriesId(), timeSeries.getOfficeId());
        assertDoesNotThrow(() -> timeSeriesController.deleteTimeSeriesIdentifier(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testPatchTimeSeriesIdentifier() throws IOException {
        String collect = readJsonFile("radar/v2/json/timeseriesidentifier.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesIdentifierDescriptor timeSeries = RadarObjectMapper.mapJsonToObject(collect, TimeSeriesIdentifierDescriptor.class);
        timeSeries.setTimeSeriesId(timeSeries.getTimeSeriesId() + (System.currentTimeMillis() % 100_000));
        TimeSeriesIdentifierController timeSeriesController = new TimeSeriesIdentifierController();
        timeSeriesController.storeTimeSeriesIdentifier(buildConnectionInfo(cookieJarSupplier), TimeSeriesIdentifierEndpointInput.post(timeSeries));
        TimeSeriesIdentifierEndpointInput.Patch input =
            TimeSeriesIdentifierEndpointInput.patch(timeSeries.getTimeSeriesId(), timeSeries.getTimeSeriesId() + "-New", timeSeries.getOfficeId());
        assertDoesNotThrow(() -> timeSeriesController.updateTimeSeriesIdentifier(buildConnectionInfo(cookieJarSupplier), input));
    }
}
