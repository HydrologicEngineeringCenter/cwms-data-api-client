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

import java.util.Objects;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_JSON;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import mil.army.usace.hec.cwms.data.api.client.model.TimeSeriesGroup;
import mil.army.usace.hec.cwms.data.api.client.model.TimeSeriesGroupPatch;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;

public final class TimeSeriesGroupEndpointV2Input extends TimeSeriesGroupEndpointInput {

    public static GetAll getAll() {
        return new GetAll();
    }

    public static GetOne getOne(String categoryId, String groupId, String officeId, String groupOfficeId, String categoryOfficeId) {
        GetOne retVal = new GetOne();
        retVal.groupId(Objects.requireNonNull(groupId, "Cannot retrieve a time series group without specifying a group Id"));
        retVal.categoryId(Objects.requireNonNull(categoryId, "Cannot retrieve a time series group without specifying a category"));
        retVal.officeId(Objects.requireNonNull(officeId, "Cannot retrieve a time series group without specifying an office"));
        retVal.groupOffice(groupOfficeId);
        retVal.categoryOffice(categoryOfficeId);
        return retVal;
    }

    public static Post post(TimeSeriesGroup timeSeriesGroup) {
        return TimeSeriesGroupEndpointInput.post(timeSeriesGroup);
    }

    public static Delete delete(String categoryId, String groupId, String groupOffice) {
        return new Delete(categoryId, groupId, groupOffice);
    }

    public static TimeSeriesGroupEndpointV2Input.Patch patch(String groupOffice, String originalGroupId, TimeSeriesGroupPatch timeSeriesGroup) {
        return new TimeSeriesGroupEndpointV2Input.Patch(groupOffice, originalGroupId, timeSeriesGroup);
    }

    public static final class GetAll extends TimeSeriesGroupEndpointInput.GetAll<GetAll> {

        private GetAll() {
        }

        @Override
        protected GetAll self() {
            return this;
        }

        @Override
        public HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return super.addInputParameters(httpRequestBuilder)
                    .addQueryParameter(GROUP_OFFICE_QUERY_PARAMETER, null);
        }
    }

    public static final class GetOne extends TimeSeriesGroupEndpointInput.GetOne {

        private GetOne() {
            super();
        }

        @Override
        public HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return super.addInputParameters(httpRequestBuilder)
                    .addQueryParameter(GROUP_OFFICE_QUERY_PARAMETER, null);
        }
    }

    public static final class Delete extends TimeSeriesGroupEndpointInput.Delete<Delete> {

        private Delete(String categoryId, String groupId, String groupOffice) {
            super(categoryId, groupId, groupOffice);
        }

        @Override
        protected Delete self() {
            return this;
        }

        @Override
        public HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return super.addInputParameters(httpRequestBuilder)
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, null);
        }
    }

    public static final class Patch extends EndpointInput {
        private final TimeSeriesGroupPatch timeSeriesGroupPatch;
        private final String originalGroupId;
        private final String groupOffice;

        private Patch(String groupOffice, String originalGroupId, TimeSeriesGroupPatch timeSeriesGroupPatch) {
            this.originalGroupId = Objects.requireNonNull(originalGroupId, "Cannot update a time series group without specifying the group id");
            this.timeSeriesGroupPatch = Objects.requireNonNull(timeSeriesGroupPatch, "Cannot update a time series group without a group patch data object");
            this.groupOffice = Objects.requireNonNull(groupOffice, "Cannot update a time series group without specifying the operating office");
        }

        String originalGroupId() {
            return originalGroupId;
        }

        String groupOffice() {
            return groupOffice;
        }

        TimeSeriesGroupPatch timeSeriesGroupPatch() {
            return timeSeriesGroupPatch;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON);
        }
    }
}
