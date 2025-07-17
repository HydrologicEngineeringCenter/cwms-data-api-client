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

package mil.army.usace.hec.cwms.data.api.client.controllers;

import mil.army.usace.hec.cwms.data.api.client.model.AssignedTimeSeries;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.data.api.client.model.TimeSeriesCategory;
import mil.army.usace.hec.cwms.data.api.client.model.TimeSeriesGroup;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestTimeSeriesGroupController extends TestController {

    @Test
    void testRetrieveSpecificTimeSeriesGroup() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_group.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesGroupEndpointInput.GetOne input = TimeSeriesGroupEndpointInput.getOne("QA Category", "Radar Test", "SWT", "SWT", "SWT");
        TimeSeriesGroup timeSeriesGroup = new TimeSeriesGroupController().retrieveTimeSeriesGroup(buildConnectionInfo(), input);
        assertEquals("Radar Test", timeSeriesGroup.getId());
        assertEquals("description123", timeSeriesGroup.getDescription());
        assertEquals("SWT", timeSeriesGroup.getOfficeId());
        assertEquals("SWT", timeSeriesGroup.getTimeSeriesCategory().getOfficeId());
        assertEquals("TestAlias", timeSeriesGroup.getSharedAliasId());
        assertEquals("ADDI.Flow.Inst.1Hour.0.Ccp-Rev", timeSeriesGroup.getSharedRefTsId());
        TimeSeriesCategory timeSeriesCategory = timeSeriesGroup.getTimeSeriesCategory();
        String description = timeSeriesCategory.getDescription();
        String id = timeSeriesCategory.getId();
        assertEquals("QA Category", id);
        assertEquals("Creating this category for testing on December 16, 2020", description);
        assertEquals("SWT", timeSeriesCategory.getOfficeId());
        assertNotNull(timeSeriesGroup.getAssignedTimeSeries());
        assertTrue(timeSeriesGroup.getAssignedTimeSeries().isEmpty());
    }

    @Test
    void testRetrieveAllTimeSeriesCategories() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_groups.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesGroupEndpointInput.GetAll input = TimeSeriesGroupEndpointInput.getAll()
                .officeId("SWT");
        List<TimeSeriesGroup> timeSeriesCategories = new TimeSeriesGroupController().retrieveTimeSeriesGroups(buildConnectionInfo(), input);
        assertEquals(7, timeSeriesCategories.size());
        TimeSeriesGroup timeSeriesGroup = timeSeriesCategories.get(0);
        assertEquals("ACSO2", timeSeriesGroup.getId());
        assertNull(timeSeriesGroup.getDescription());
        assertEquals("SWT", timeSeriesGroup.getOfficeId());
        assertNull(timeSeriesGroup.getSharedAliasId());
        assertNull(timeSeriesGroup.getSharedRefTsId());
        TimeSeriesCategory timeSeriesCategory = timeSeriesGroup.getTimeSeriesCategory();
        String description = timeSeriesCategory.getDescription();
        String id = timeSeriesCategory.getId();
        assertEquals("Lakes", id);
        assertNull(description);
        assertEquals("SWT", timeSeriesCategory.getOfficeId());
        List<AssignedTimeSeries> assignedTimeSeries = timeSeriesGroup.getAssignedTimeSeries();
        AssignedTimeSeries timeseries = assignedTimeSeries.get(0);
        assertEquals("ACSO2.%-Humidity.Ave.15Minutes.15Minutes.Mesonet-raw", timeseries.getTimeseriesId());
        assertEquals(3768317551L, timeseries.getTsCode().longValueExact());
        assertEquals("15Minutes-%-Humidity", timeseries.getAliasId());
        assertEquals(0, timeseries.getAttribute().intValue());
        assertEquals(5, assignedTimeSeries.size());
    }

    @Test
    void testPost() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_group.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesGroup timeSeriesGroup = RadarObjectMapper.mapJsonToObject(collect, TimeSeriesGroup.class);
        TimeSeriesGroupEndpointInput.Post input = TimeSeriesGroupEndpointInput.post(timeSeriesGroup);
        assertDoesNotThrow(() -> new TimeSeriesGroupController().storeGroup(buildConnectionInfo(), input));
    }

    @Test
    void testPatch() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_group.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesGroup timeSeriesGroup = RadarObjectMapper.mapJsonToObject(collect, TimeSeriesGroup.class);
        TimeSeriesGroupEndpointInput.Patch input = TimeSeriesGroupEndpointInput.patch("SWT", "QA Category2", timeSeriesGroup);
        assertDoesNotThrow(() -> new TimeSeriesGroupController().updateGroup(buildConnectionInfo(), input));
    }

    @Test
    void testDelete() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_group.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesGroupEndpointInput.Delete input = TimeSeriesGroupEndpointInput.delete("QA Category", "Radar Test", "SWT");
        assertDoesNotThrow(() -> new TimeSeriesGroupController().deleteGroup(buildConnectionInfo(), input));
    }
}
