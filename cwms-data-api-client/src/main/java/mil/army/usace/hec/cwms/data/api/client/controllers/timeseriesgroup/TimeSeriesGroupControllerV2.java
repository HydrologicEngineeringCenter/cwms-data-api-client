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


import java.io.IOException;
import java.util.List;

import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.data.api.client.model.TimeSeriesGroup;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;

public final class TimeSeriesGroupControllerV2 extends TimeSeriesGroupController {

    private static final String TIME_SERIES_GROUP_ENDPOINT_V2 = "v2/timeseries/group";

    public TimeSeriesGroup retrieveTimeSeriesGroup(ApiConnectionInfo apiConnectionInfo,
                                                   TimeSeriesGroupEndpointV2Input.GetOne input) throws IOException {
        String endpoint = TIME_SERIES_GROUP_ENDPOINT_V2 + "/" + input.groupOffice() + "/" + input.getGroupId();
        return retrieveTimeSeriesGroup(apiConnectionInfo, endpoint, input);
    }

    public List<TimeSeriesGroup> retrieveTimeSeriesGroups(ApiConnectionInfo apiConnectionInfo,
                                                          TimeSeriesGroupEndpointV2Input.GetAll input) throws IOException {
        String endpoint = TIME_SERIES_GROUP_ENDPOINT_V2 + "/" + input.groupOfficeId();
        return retrieveTimeSeriesGroups(apiConnectionInfo, endpoint, input);
    }

    public void storeGroup(ApiConnectionInfo apiConnectionInfo, TimeSeriesGroupEndpointV2Input.Post input) throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.timeSeriesGroup());
        String endpoint = TIME_SERIES_GROUP_ENDPOINT_V2 + "/" + input.timeSeriesGroup().getOfficeId();
        storeGroup(apiConnectionInfo, endpoint, body, input);
    }

    public void updateGroup(ApiConnectionInfo apiConnectionInfo, TimeSeriesGroupEndpointV2Input.Patch input) throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.timeSeriesGroupPatch());
        String endpoint = TIME_SERIES_GROUP_ENDPOINT_V2 + "/" + input.groupOffice() + "/" + input.originalGroupId();
        updateGroup(apiConnectionInfo, endpoint, body, input);
    }

    public void deleteGroup(ApiConnectionInfo apiConnectionInfo, TimeSeriesGroupEndpointV2Input.Delete input) throws IOException {
        String endpoint = TIME_SERIES_GROUP_ENDPOINT_V2 + "/" + input.groupOfficeId() + "/" + input.timeSeriesGroupId();
        deleteGroup(apiConnectionInfo, endpoint, input);
    }

}
