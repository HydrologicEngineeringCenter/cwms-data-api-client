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

import java.io.IOException;
import java.time.Instant;
import java.util.Set;
import mil.army.usace.hec.cwms.radar.client.model.ForecastInstance;
import mil.army.usace.hec.cwms.radar.client.model.ForecastSpec;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

final class TestForecastInstanceController extends TestController {
    @Test
    void testRetrieveSingle() throws IOException {
        String resource = "radar/v2/json/forecast_instance.json";
        String collect = readJsonFile(resource);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Instant forecastDateTime = Instant.ofEpochMilli(1624284010000L);
        Instant issueDateTime = Instant.ofEpochMilli(1653221020000L);
        ForecastSpec spec = new ForecastSpec()
                .officeId("SPK")
                .specId("test-spec")
                .designator("designator");
        ForecastInstanceEndpointInput.GetOne input = ForecastInstanceEndpointInput.getOne(spec, forecastDateTime, issueDateTime);
        ForecastInstance forecastInstance = new ForecastInstanceController().retrieveForecastInstance(buildConnectionInfo(), input);
        assertNotNull(forecastInstance);
        assertNotNull(forecastInstance.getSpec());
        assertEquals("SPK", forecastInstance.getSpec().getOfficeId());
        assertEquals("test-spec", forecastInstance.getSpec().getSpecId());
        assertEquals("designator", forecastInstance.getSpec().getDesignator());
        assertEquals("FcstInstTestLoc", forecastInstance.getSpec().getLocationIds().get(0));
        assertEquals(1624284010000L, forecastInstance.getDateTime().toEpochMilli());
        assertEquals(1653221020000L, forecastInstance.getIssueDateTime().toEpochMilli());
        assertEquals(1692702150000L, forecastInstance.getFirstDateTime().toEpochMilli());
        assertEquals(1727017260000L, forecastInstance.getLastDateTime().toEpochMilli());
        assertEquals(5, forecastInstance.getMaxAge());
        assertEquals(3, forecastInstance.getTimeSeriesCount());
        assertEquals("test notes", forecastInstance.getNotes());
        assertEquals("value1", forecastInstance.getMetadata().get("key1"));
        assertEquals("value2", forecastInstance.getMetadata().get("key2"));
        assertEquals("value3", forecastInstance.getMetadata().get("key3"));
        assertEquals("testFilename.txt", forecastInstance.getFilename());
        assertEquals("test file description", forecastInstance.getFileDescription());
        assertEquals("test file content", new String(forecastInstance.getFileData()));
    }

    @Test
    void testRetrieveMultiple() throws IOException {
        String resource = "radar/v2/json/forecast_instances.json";
        String collect = readJsonFile(resource);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        ForecastInstanceEndpointInput.GetAll input = ForecastInstanceEndpointInput.getAll();
        Set<ForecastInstance> forecastInstances = new ForecastInstanceController().retrieveForecastInstances(buildConnectionInfo(), input);
        assertNotNull(forecastInstances);
        ForecastInstance forecastInstance = forecastInstances.stream()
                .filter(f -> "test-spec".equals(f.getSpec().getSpecId()))
                .findAny()
                .orElse(null);
        assertNotNull(forecastInstance);
        assertEquals("SWT", forecastInstance.getSpec().getOfficeId());
        assertEquals("test-spec", forecastInstance.getSpec().getSpecId());
        assertEquals("designator", forecastInstance.getSpec().getDesignator());
        assertEquals("FcstInstTestLoc", forecastInstance.getSpec().getLocationIds().get(0));
        assertEquals(1624284010000L, forecastInstance.getDateTime().toEpochMilli());
        assertEquals(1653221020000L, forecastInstance.getIssueDateTime().toEpochMilli());
        assertEquals(1692702150000L, forecastInstance.getFirstDateTime().toEpochMilli());
        assertEquals(1727017260000L, forecastInstance.getLastDateTime().toEpochMilli());
        assertEquals(5, forecastInstance.getMaxAge());
        assertEquals(3, forecastInstance.getTimeSeriesCount());
        assertEquals("test notes", forecastInstance.getNotes());
        assertEquals("value1", forecastInstance.getMetadata().get("key1"));
        assertEquals("value2", forecastInstance.getMetadata().get("key2"));
        assertEquals("value3", forecastInstance.getMetadata().get("key3"));
        assertEquals("testFilename.txt", forecastInstance.getFilename());
        assertEquals("test file description", forecastInstance.getFileDescription());
        assertEquals("test file content", new String(forecastInstance.getFileData()));

        ForecastInstance forecastInstance2 = forecastInstances.stream()
                .filter(f -> "test-spec2".equals(f.getSpec().getSpecId()))
                .findAny()
                .orElse(null);
        assertNotNull(forecastInstance2);
        assertEquals("SWT", forecastInstance2.getSpec().getOfficeId());
        assertEquals("test-spec2", forecastInstance2.getSpec().getSpecId());
        assertEquals("designator2", forecastInstance2.getSpec().getDesignator());
        assertEquals("FcstInstTestLoc2", forecastInstance2.getSpec().getLocationIds().get(0));
        assertEquals(1624284020000L, forecastInstance2.getDateTime().toEpochMilli());
        assertEquals(1653221030000L, forecastInstance2.getIssueDateTime().toEpochMilli());
        assertEquals(1692702160000L, forecastInstance2.getFirstDateTime().toEpochMilli());
        assertEquals(1727017270000L, forecastInstance2.getLastDateTime().toEpochMilli());
        assertEquals(6, forecastInstance2.getMaxAge());
        assertEquals(3, forecastInstance2.getTimeSeriesCount());
        assertEquals("test notes2", forecastInstance2.getNotes());
        assertEquals("valueA", forecastInstance2.getMetadata().get("key1"));
        assertEquals("valueB", forecastInstance2.getMetadata().get("key2"));
        assertEquals("valueC", forecastInstance2.getMetadata().get("key3"));
    }

    @Test
    void testPost() throws IOException {
        String resource = "radar/v2/json/forecast_instance.json";
        String collect = readJsonFile(resource);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        ForecastInstance forecastInstance = RadarObjectMapper.mapJsonToObject(collect, ForecastInstance.class);
        ForecastInstanceEndpointInput.Post input = ForecastInstanceEndpointInput.post(forecastInstance);
        assertDoesNotThrow(() -> new ForecastInstanceController().storeForecastInstance(buildConnectionInfo(), input));
    }

    @Test
    void testPatch() throws IOException {
        String resource = "radar/v2/json/forecast_instance.json";
        String collect = readJsonFile(resource);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        ForecastInstance forecastInstance = RadarObjectMapper.mapJsonToObject(collect, ForecastInstance.class);
        ForecastInstanceEndpointInput.Patch input = ForecastInstanceEndpointInput.patch("test-spec", forecastInstance);
        assertDoesNotThrow(() -> new ForecastInstanceController().updateForecastInstance(buildConnectionInfo(), input));
    }

    @Test
    void testDelete() throws IOException {
        String resource = "radar/v2/json/forecast_instance.json";
        String collect = readJsonFile(resource);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        ForecastSpec spec = new ForecastSpec()
                .officeId("SWT")
                .specId("test-spec")
                .designator("designator");
        ForecastInstanceEndpointInput.Delete input = ForecastInstanceEndpointInput.delete(spec, Instant.ofEpochMilli(1624284010000L), Instant.ofEpochMilli(1653221020000L));
        assertDoesNotThrow(() -> new ForecastInstanceController().deleteForecastInstance(buildConnectionInfo(), input));
    }
}
