/*
 * MIT License
 *
 * Copyright (c) 2026 Hydrologic Engineering Center
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import mil.army.usace.hec.cwms.data.api.client.model.LocationToPublishedData;
import mil.army.usace.hec.cwms.data.api.client.model.LocationToPublishedDataList;
import org.junit.jupiter.api.Test;

class TestPublishedController extends TestController {

    @Test
    void testRetrievePublishedTimeSeries() throws IOException {
        String json = readJsonFile("radar/v1/json/published_time_series.json");
        mockHttpServer.enqueue(json);
        mockHttpServer.start();
        PublishedEndpointInput.GetAll input = PublishedEndpointInput.getAll().withOfficeIdMask("SWT");
        LocationToPublishedDataList result = new PublishedController().retrievePublishedData(buildConnectionInfo(), input);
        assertNotNull(result);
        assertFalse(result.getLocationToPublishedData().isEmpty());
        LocationToPublishedData entry = result.getLocationToPublishedData().get(0);
        assertEquals("AARK", entry.getLocationId().getName());
        assertEquals("SPK", entry.getLocationId().getOfficeId());
        assertEquals("AARK.Stage.Inst.15Minutes.0.Ccp-Rev", entry.getPublishedTimesSeries().get("STAGE").getTimeSeriesId().getName());
    }
}
