/*
 * Copyright (c) 2023
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
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
}
