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

import mil.army.usace.hec.cwms.data.api.client.model.AssignedTimeSeries;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.data.api.client.model.TimeSeriesCategory;
import mil.army.usace.hec.cwms.data.api.client.model.TimeSeriesGroup;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestTimeSeriesGroupControllerV1 extends TestController {

    @Test
    void testRetrieveSpecificTimeSeriesGroup() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_group.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesGroupEndpointV1Input.GetOne input = TimeSeriesGroupEndpointV1Input.getOne("QA Category", "Radar Test", "SWT", "SWT", "SWT");
        TimeSeriesGroup timeSeriesGroup = new TimeSeriesGroupControllerV1().retrieveTimeSeriesGroup(buildConnectionInfo(), input);
        assertEquals("Radar Test", timeSeriesGroup.getId());
        assertEquals("description123", timeSeriesGroup.getDescription());
        assertEquals("SWT", timeSeriesGroup.getOfficeId());
        assertEquals("TestAlias", timeSeriesGroup.getSharedAliasId());
        assertEquals("ADDI.Flow.Inst.1Hour.0.Ccp-Rev", timeSeriesGroup.getSharedRefTsId());
        TimeSeriesCategory timeSeriesCategory = timeSeriesGroup.getTimeSeriesCategory();
        assertEquals("QA Category", timeSeriesCategory.getId());
        assertEquals("Creating this category for testing on December 16, 2020", timeSeriesCategory.getDescription());
        assertEquals("SWT", timeSeriesCategory.getOfficeId());
        assertNotNull(timeSeriesGroup.getAssignedTimeSeries());
        assertTrue(timeSeriesGroup.getAssignedTimeSeries().isEmpty());
    }

    @Test
    void testRetrieveAllTimeSeriesGroups() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_groups.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesGroupEndpointV1Input.GetAll input = TimeSeriesGroupEndpointV1Input.getAll()
                .officeId("SWT");
        List<TimeSeriesGroup> timeSeriesGroups = new TimeSeriesGroupControllerV1().retrieveTimeSeriesGroups(buildConnectionInfo(), input);
        assertEquals(7, timeSeriesGroups.size());
        TimeSeriesGroup timeSeriesGroup = timeSeriesGroups.get(0);
        assertEquals("ACSO2", timeSeriesGroup.getId());
        assertNull(timeSeriesGroup.getDescription());
        assertEquals("SWT", timeSeriesGroup.getOfficeId());
        TimeSeriesCategory timeSeriesCategory = timeSeriesGroup.getTimeSeriesCategory();
        assertEquals("Lakes", timeSeriesCategory.getId());
        assertEquals("SWT", timeSeriesCategory.getOfficeId());
        List<AssignedTimeSeries> assignedTimeSeries = timeSeriesGroup.getAssignedTimeSeries();
        assertEquals(5, assignedTimeSeries.size());
        assertEquals("ACSO2.%-Humidity.Ave.15Minutes.15Minutes.Mesonet-raw", assignedTimeSeries.get(0).getTimeseriesId());
    }

    @Test
    void testPost() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_group.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesGroup timeSeriesGroup = RadarObjectMapper.mapJsonToObject(collect, TimeSeriesGroup.class);
        TimeSeriesGroupEndpointV1Input.Post input = TimeSeriesGroupEndpointV1Input.post(timeSeriesGroup);
        assertDoesNotThrow(() -> new TimeSeriesGroupControllerV1().storeGroup(buildConnectionInfo(), input));
    }

    @Test
    void testPatch() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_group.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesGroup timeSeriesGroup = RadarObjectMapper.mapJsonToObject(collect, TimeSeriesGroup.class);
        TimeSeriesGroupEndpointV1Input.Patch input = TimeSeriesGroupEndpointV1Input
                .patch("SWT", "Radar Test", timeSeriesGroup)
                .replaceAssignedTs(true);
        assertDoesNotThrow(() -> new TimeSeriesGroupControllerV1().updateGroup(buildConnectionInfo(), input));
    }

    @Test
    void testDelete() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_group.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesGroupEndpointV1Input.Delete input = TimeSeriesGroupEndpointV1Input.delete("QA Category", "Radar Test", "SWT");
        assertDoesNotThrow(() -> new TimeSeriesGroupControllerV1().deleteGroup(buildConnectionInfo(), input));
    }
}
