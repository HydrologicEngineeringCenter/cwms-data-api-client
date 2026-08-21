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

package mil.army.usace.hec.cwms.data.api.client.controllers.timeseriesgroup;

import mil.army.usace.hec.cwms.data.api.client.controllers.TestController;
import mil.army.usace.hec.cwms.data.api.client.model.AssignedTimeSeries;
import mil.army.usace.hec.cwms.data.api.client.model.CwmsId;
import mil.army.usace.hec.cwms.data.api.client.model.TimeSeriesGroupMembership;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.data.api.client.model.TimeSeriesCategory;
import mil.army.usace.hec.cwms.data.api.client.model.TimeSeriesGroup;
import mil.army.usace.hec.cwms.data.api.client.model.TimeSeriesGroupPatch;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestTimeSeriesGroupControllerV2 extends TestController {

    @Test
    void testRetrieveSpecificTimeSeriesGroup() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_group.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesGroupEndpointV2Input.GetOne input = TimeSeriesGroupEndpointV2Input.getOne("QA Category", "Radar Test", "SWT", "SWT", "SWT");
        TimeSeriesGroup timeSeriesGroup = new TimeSeriesGroupControllerV2().retrieveTimeSeriesGroup(buildConnectionInfo(), input);
        assertEquals("Radar Test", timeSeriesGroup.getId());
        assertEquals("SWT", timeSeriesGroup.getOfficeId());
    }

    @Test
    void testRetrieveAllTimeSeriesGroups() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_groups.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesGroupEndpointV2Input.GetAll input = TimeSeriesGroupEndpointV2Input.getAll()
                .groupOfficeId("SWT");
        List<TimeSeriesGroup> timeSeriesGroups = new TimeSeriesGroupControllerV2().retrieveTimeSeriesGroups(buildConnectionInfo(), input);
        assertEquals(7, timeSeriesGroups.size());
    }

    @Test
    void testPost() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_group.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesGroup timeSeriesGroup = RadarObjectMapper.mapJsonToObject(collect, TimeSeriesGroup.class);
        TimeSeriesGroupEndpointV2Input.Post input = TimeSeriesGroupEndpointV2Input.post(timeSeriesGroup);
        assertDoesNotThrow(() -> new TimeSeriesGroupControllerV2().storeGroup(buildConnectionInfo(), input));
    }

    @Test
    void testPatch() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_group.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesCategory category = new TimeSeriesCategory().officeId("SWT").id("QA Category2");
        AssignedTimeSeries assignedTimeSeries = new AssignedTimeSeries()
                .officeId("SWT")
                .timeseriesId("Assign.Ts.Id")
                .aliasId("AliasId")
                .attribute(1);
        TimeSeriesGroupMembership membership = new TimeSeriesGroupMembership()
                .assign(Collections.singletonList(assignedTimeSeries))
                .unassign(Collections.singletonList(new CwmsId().officeId("SWT").name("Unassign.Ts.Id")));
        TimeSeriesGroupPatch timeSeriesGroupPatch = new TimeSeriesGroupPatch()
                .officeId("SWT")
                .id("Radar Test")
                .timeSeriesCategory(category)
                .membership(membership);
        TimeSeriesGroupEndpointV2Input.Patch input = TimeSeriesGroupEndpointV2Input
                .patch("SWT", "Radar Test", timeSeriesGroupPatch);
        assertDoesNotThrow(() -> new TimeSeriesGroupControllerV2().updateGroup(buildConnectionInfo(), input));
    }

    @Test
    void testDelete() throws IOException {
        String collect = readJsonFile("radar/v1/json/ts_group.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        TimeSeriesGroupEndpointV2Input.Delete input = TimeSeriesGroupEndpointV2Input.delete("QA Category", "Radar Test", "SWT");
        assertDoesNotThrow(() -> new TimeSeriesGroupControllerV2().deleteGroup(buildConnectionInfo(), input));
    }
}
