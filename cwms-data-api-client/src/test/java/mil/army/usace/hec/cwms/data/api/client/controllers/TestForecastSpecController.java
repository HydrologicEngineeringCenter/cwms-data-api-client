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

import mil.army.usace.hec.cwms.data.api.client.model.ForecastSpec;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

final class TestForecastSpecController extends TestController {

    @Test
    void testRetrieveSingle() throws IOException {
        String resource = "radar/v2/json/forecast_spec.json";
        String collect = readJsonFile(resource);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        ForecastSpecEndpointInput.GetOne input = ForecastSpecEndpointInput.getOne("SPK", "test-spec", "designator");
        ForecastSpec spec = new ForecastSpecController().retrieveForecastSpec(buildConnectionInfo(), input);
        assertNotNull(spec);
        assertEquals("SPK", spec.getOfficeId());
        assertEquals("test-spec", spec.getSpecId());
        assertEquals("designator", spec.getDesignator());
        assertEquals("location", spec.getLocationId());
        assertEquals("description", spec.getDescription());
        assertEquals("sourceEntity", spec.getSourceEntityId());
        assertEquals(3, spec.getTimeSeriesIds().size());
        assertEquals("tsid1", spec.getTimeSeriesIds().get(0));
        assertEquals("tsid2", spec.getTimeSeriesIds().get(1));
        assertEquals("tsid3", spec.getTimeSeriesIds().get(2));
    }

    @Test
    void testRetrieveMultiple() throws IOException {
        String resource = "radar/v2/json/forecast_specs.json";
        String collect = readJsonFile(resource);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        ForecastSpecEndpointInput.GetAll input = ForecastSpecEndpointInput.getAll();
        Set<ForecastSpec> specs = new ForecastSpecController().retrieveForecastSpecs(buildConnectionInfo(), input);
        assertNotNull(specs);
        ForecastSpec spec = specs.stream()
                .filter(s -> "test-spec".equals(s.getSpecId()))
                .findAny()
                .orElse(null);
        assertNotNull(spec);
        assertEquals("SPK", spec.getOfficeId());
        assertEquals("test-spec", spec.getSpecId());
        assertEquals("designator", spec.getDesignator());
        assertEquals("location", spec.getLocationId());
        assertEquals("description", spec.getDescription());
        assertEquals("sourceEntity", spec.getSourceEntityId());
        assertEquals(3, spec.getTimeSeriesIds().size());
        assertEquals("tsid1", spec.getTimeSeriesIds().get(0));
        assertEquals("tsid2", spec.getTimeSeriesIds().get(1));
        assertEquals("tsid3", spec.getTimeSeriesIds().get(2));

        ForecastSpec spec2 = specs.stream()
                .filter(s -> "test-spec2".equals(s.getSpecId()))
                .findAny()
                .orElse(null);
        assertNotNull(spec2);
        assertEquals("SPK", spec2.getOfficeId());
        assertEquals("test-spec2", spec2.getSpecId());
        assertEquals("designator2", spec2.getDesignator());
        assertEquals("locationA", spec2.getLocationId());
        assertEquals("description2", spec2.getDescription());
        assertEquals("sourceEntity2", spec2.getSourceEntityId());
        assertEquals(3, spec2.getTimeSeriesIds().size());
        assertEquals("tsidA", spec2.getTimeSeriesIds().get(0));
        assertEquals("tsidB", spec2.getTimeSeriesIds().get(1));
        assertEquals("tsidC", spec2.getTimeSeriesIds().get(2));
    }

    @Test
    void testPost() throws IOException {
        String resource = "radar/v2/json/forecast_spec.json";
        String collect = readJsonFile(resource);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        ForecastSpec spec = RadarObjectMapper.mapJsonToObject(collect, ForecastSpec.class);
        ForecastSpecEndpointInput.Post input = ForecastSpecEndpointInput.post(spec);
        assertDoesNotThrow(() -> new ForecastSpecController().storeForecastSpec(buildConnectionInfo(), input));
    }

    @Test
    void testPatch() throws IOException {
        String resource = "radar/v2/json/forecast_spec.json";
        String collect = readJsonFile(resource);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        ForecastSpec spec = RadarObjectMapper.mapJsonToObject(collect, ForecastSpec.class);
        ForecastSpecEndpointInput.Patch input = ForecastSpecEndpointInput.patch("test-spec", spec);
        assertDoesNotThrow(() -> new ForecastSpecController().updateForecastSpec(buildConnectionInfo(), input));
    }

    @Test
    void testDelete() throws IOException {
        String resource = "radar/v2/json/forecast_spec.json";
        String collect = readJsonFile(resource);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        ForecastSpecEndpointInput.Delete input = ForecastSpecEndpointInput.delete("SPK", "test-spec", "designator");
        assertDoesNotThrow(() -> new ForecastSpecController().deleteForecastSpec(buildConnectionInfo(), input));
    }
}
