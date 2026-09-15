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

import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.data.api.client.model.TimeSeriesGroup;

abstract class TimeSeriesGroupEndpointInput {

    static final String OFFICE_QUERY_PARAMETER = "office";
    static final String CATEGORY_ID_QUERY_PARAMETER = "category-id";
    static final String INCLUDE_ASSIGNED_QUERY_PARAMETER = "include-assigned";
    static final String FAIL_IF_EXISTS = "fail-if-exists";
    static final String REPLACE_ASSIGNED_TS_QUERY_PARAMETER = "replace-assigned-ts";
    static final String CATEGORY_MASK_QUERY_PARAMETER = "timeseries-category-like";
    static final String GROUP_MASK_QUERY_PARAMETER = "timeseries-group-like";
    static final String CATEGORY_OFFICE_QUERY_PARAMETER = "category-office-id";
    static final String GROUP_OFFICE_QUERY_PARAMETER = "group-office-id";
    static final String CASCADE_DELETE_QUERY_PARAMETER = "cascade-delete";

    protected TimeSeriesGroupEndpointInput() {
        throw new AssertionError("factory class");
    }

    protected static GetOne getOne(String categoryId, String groupId, String officeId, String groupOfficeId, String categoryOfficeId) {
        return new GetOne()
            .groupId(Objects.requireNonNull(groupId, "Cannot retrieve a time series group without specifying a group Id"))
            .categoryId(Objects.requireNonNull(categoryId, "Cannot retrieve a time series group without specifying a category"))
            .officeId(Objects.requireNonNull(officeId, "Cannot retrieve a time series group without specifying an office"))
            .groupOffice(groupOfficeId)
            .categoryOffice(categoryOfficeId);
    }

    protected static Post post(TimeSeriesGroup timeSeriesGroup) {
        return new Post(timeSeriesGroup);
    }

    public abstract static class GetAll<T extends GetAll<T>> extends EndpointInput {
        private String officeId;
        private boolean includeAssigned = true;
        private String timeSeriesCategoryMask;
        private String categoryOfficeId;
        private String groupOfficeId;
        private String timeSeriesGroupMask;


        protected GetAll() {

        }

        protected abstract T self();

        public T includeAssigned(boolean includeAssigned) {
            this.includeAssigned = includeAssigned;
            return self();
        }

        public T officeId(String officeId) {
            this.officeId = officeId;
            return self();
        }

        public T timeSeriesCategoryMask(String timeSeriesCategoryMask) {
            this.timeSeriesCategoryMask = timeSeriesCategoryMask;
            return self();
        }

        public T categoryOfficeId(String categoryOfficeId) {
            this.categoryOfficeId = categoryOfficeId;
            return self();
        }

        public T groupOfficeId(String groupOfficeId) {
            this.groupOfficeId = groupOfficeId;
            return self();
        }

        String groupOfficeId() {
            return groupOfficeId;
        }

        public T timeSeriesGroupMask(String timeSeriesGroupMask) {
            this.timeSeriesGroupMask = timeSeriesGroupMask;
            return self();
        }

        @Override
        public HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(INCLUDE_ASSIGNED_QUERY_PARAMETER, Boolean.toString(includeAssigned))
                .addQueryParameter(CATEGORY_MASK_QUERY_PARAMETER, timeSeriesCategoryMask)
                .addQueryParameter(CATEGORY_OFFICE_QUERY_PARAMETER, categoryOfficeId)
                .addQueryParameter(GROUP_OFFICE_QUERY_PARAMETER, groupOfficeId)
                .addQueryParameter(GROUP_MASK_QUERY_PARAMETER, timeSeriesGroupMask)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON);
        }
    }

    public static class GetOne extends EndpointInput {

        private String categoryId;
        private String groupId;
        private String officeId;
        private String categoryOffice;
        private String groupOffice;

        protected GetOne() {
        }

        GetOne categoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        GetOne groupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        GetOne officeId(String officeId) {
            this.officeId = officeId;
            return this;
        }

        GetOne categoryOffice(String categoryOffice) {
            this.categoryOffice = categoryOffice;
            return this;
        }

        GetOne groupOffice(String groupOffice) {
            this.groupOffice = groupOffice;
            return this;
        }

        String getGroupId() {
            return groupId;
        }

        String groupOffice() {
            return groupOffice;
        }

        @Override
        public HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(CATEGORY_ID_QUERY_PARAMETER, categoryId)
                .addQueryParameter(CATEGORY_OFFICE_QUERY_PARAMETER, categoryOffice)
                .addQueryParameter(GROUP_OFFICE_QUERY_PARAMETER, groupOffice)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON);
        }
    }

    public static class Post extends EndpointInput {

        private final TimeSeriesGroup timeSeriesGroup;
        private boolean failIfExists = true;

        protected Post(TimeSeriesGroup timeSeriesGroup) {
            this.timeSeriesGroup = Objects.requireNonNull(timeSeriesGroup, "Cannot store a time series group without a data object");
        }

        TimeSeriesGroup timeSeriesGroup() {
            return timeSeriesGroup;
        }

        public Post failIfExists(boolean failIfExists) {
            this.failIfExists = failIfExists;
            return this;
        }

        @Override
        public HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(FAIL_IF_EXISTS, Boolean.toString(failIfExists))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON);
        }
    }

    public abstract static class Delete<T extends Delete<T>> extends EndpointInput {
        private final String timeSeriesGroupId;
        private final String categoryId;
        private final String groupOfficeId;
        private boolean cascadeDelete = false;

        protected Delete(String categoryId, String timeSeriesGroupId, String groupOfficeId) {
            this.categoryId = Objects.requireNonNull(categoryId, "Cannot delete a time series group without specifying the category)");
            this.timeSeriesGroupId = Objects.requireNonNull(timeSeriesGroupId, "Cannot delete a time series group that is not defined");
            this.groupOfficeId = Objects.requireNonNull(groupOfficeId, "Cannot delete a time series group without specifying the office");
        }

        protected abstract T self();

        String timeSeriesGroupId() {
            return timeSeriesGroupId;
        }

        String groupOfficeId() {
            return groupOfficeId;
        }

        public T cascadeDelete(boolean cascadeDelete) {
            this.cascadeDelete = cascadeDelete;
            return self();
        }

        @Override
        public HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, groupOfficeId)
                    .addQueryParameter(CATEGORY_ID_QUERY_PARAMETER, categoryId)
                    .addQueryParameter(CASCADE_DELETE_QUERY_PARAMETER, Boolean.toString(cascadeDelete))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON);
        }
    }
}
