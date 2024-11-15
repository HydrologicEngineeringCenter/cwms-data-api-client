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

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_JSON;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.util.Objects;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesGroup;

public final class TimeSeriesGroupEndpointInput {

    static final String OFFICE_QUERY_PARAMETER = "office";
    static final String CATEGORY_ID_QUERY_PARAMETER = "category-id";
    static final String INCLUDE_ASSIGNED_QUERY_PARAMETER = "include-assigned";
    static final String FAIL_IF_EXISTS = "fail-if-exists";
    static final String REPLACE_ASSIGNED_TS_QUERY_PARAMETER = "replace-assigned-ts";
    static final String CATEGORY_MASK_QUERY_PARAMETER = "timeseries-category-like";
    static final String GROUP_MASK_QUERY_PARAMETER = "timeseries-group-like";
    static final String CATEGORY_OFFICE_QUERY_PARAMETER = "category-office-id";
    static final String GROUP_OFFICE_QUERY_PARAMETER = "group-office-id";

    private TimeSeriesGroupEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll() {
        return new GetAll();
    }

    public static GetOne getOne(String categoryId, String groupId, String officeId) {
        return new GetOne(categoryId, groupId, officeId);
    }

    public static Post post(TimeSeriesGroup timeSeriesGroup) {
        return new Post(timeSeriesGroup);
    }

    public static Patch patch(String userOffice, String originalGroupId, TimeSeriesGroup timeSeriesGroup) {
        return new Patch(userOffice, originalGroupId, timeSeriesGroup);
    }

    public static Delete delete(String categoryId, String groupId, String groupOffice) {
        return new Delete(categoryId, groupId, groupOffice);
    }

    public static final class GetAll extends EndpointInput {
        private String officeId;
        private boolean includeAssigned = true;
        private String timeSeriesCategoryMask;
        private String categoryOfficeId;
        private String groupOfficeId;
        private String timeSeriesGroupMask;


        private GetAll() {

        }

        public GetAll includeAssigned(boolean includeAssigned) {
            this.includeAssigned = includeAssigned;
            return this;
        }

        public GetAll officeId(String officeId) {
            this.officeId = officeId;
            return this;
        }

        public GetAll timeSeriesCategoryMask(String timeSeriesCategoryMask) {
            this.timeSeriesCategoryMask = timeSeriesCategoryMask;
            return this;
        }

        public GetAll categoryOfficeId(String categoryOfficeId) {
            this.categoryOfficeId = categoryOfficeId;
            return this;
        }

        public GetAll groupOfficeId(String groupOfficeId) {
            this.groupOfficeId = groupOfficeId;
            return this;
        }

        public GetAll timeSeriesGroupMask(String timeSeriesGroupMask) {
            this.timeSeriesGroupMask = timeSeriesGroupMask;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(INCLUDE_ASSIGNED_QUERY_PARAMETER, Boolean.toString(includeAssigned))
                .addQueryParameter(CATEGORY_MASK_QUERY_PARAMETER, timeSeriesCategoryMask)
                .addQueryParameter(CATEGORY_OFFICE_QUERY_PARAMETER, categoryOfficeId)
                .addQueryParameter(GROUP_OFFICE_QUERY_PARAMETER, groupOfficeId)
                .addQueryParameter(GROUP_MASK_QUERY_PARAMETER, timeSeriesGroupMask)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON);
        }
    }

    public static final class GetOne extends EndpointInput {

        private final String categoryId;
        private final String groupId;
        private final String officeId;
        private String categoryOffice;
        private String groupOffice;

        private GetOne(String categoryId, String groupId, String officeId) {
            this.categoryId = Objects.requireNonNull(categoryId, "Cannot retrieve a time series group without specifying a category");
            this.groupId = Objects.requireNonNull(groupId, "Cannot retrieve a time series group without specifying a group Id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot retrieve a time series group without specifying an office");
        }

        String getGroupId() {
            return groupId;
        }

        public GetOne categoryOffice(String categoryOffice) {
            this.categoryOffice = categoryOffice;
            return this;
        }

        public GetOne groupOffice(String groupOffice) {
            this.groupOffice = groupOffice;
            return this;
        }


        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(CATEGORY_ID_QUERY_PARAMETER, categoryId)
                .addQueryParameter(CATEGORY_OFFICE_QUERY_PARAMETER, categoryOffice)
                .addQueryParameter(GROUP_OFFICE_QUERY_PARAMETER, groupOffice)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON);
        }
    }

    public static final class Post extends EndpointInput {

        private final TimeSeriesGroup timeSeriesGroup;
        private boolean failIfExists = true;

        private Post(TimeSeriesGroup timeSeriesGroup) {
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
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(FAIL_IF_EXISTS, Boolean.toString(failIfExists))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON);
        }
    }

    public static final class Patch extends EndpointInput {

        private final TimeSeriesGroup timeSeriesGroup;
        private final String originalGroupId;
        private boolean replaceAssignedTs = false;
        private final String userOffice;

        private Patch(String userOffice, String originalGroupId, TimeSeriesGroup timeSeriesGroup) {
            this.originalGroupId = Objects.requireNonNull(originalGroupId, "Cannot update a time series group without specifying the group id");
            this.timeSeriesGroup = Objects.requireNonNull(timeSeriesGroup, "Cannot update a time series group without a group data object");
            this.userOffice = Objects.requireNonNull(userOffice, "Cannot update a time series group without specifying the operating office");
        }

        TimeSeriesGroup timeSeriesGroup() {
            return timeSeriesGroup;
        }

        String originalLocationId() {
            return originalGroupId;
        }

        public Patch replaceAssignedTs(boolean replaceAssignedTs) {
            this.replaceAssignedTs = replaceAssignedTs;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(REPLACE_ASSIGNED_TS_QUERY_PARAMETER, Boolean.toString(replaceAssignedTs))
                .addQueryParameter(OFFICE_QUERY_PARAMETER, userOffice)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON);
        }
    }

    public static final class Delete extends EndpointInput {
        private final String timeSeriesGroupId;
        private final String categoryId;
        private final String groupOfficeId;

        private Delete(String categoryId, String timeSeriesGroupId, String groupOfficeId) {
            this.categoryId = Objects.requireNonNull(categoryId, "Cannot delete a time series group without specifying the category)");
            this.timeSeriesGroupId = Objects.requireNonNull(timeSeriesGroupId, "Cannot delete a time series group that is not defined");
            this.groupOfficeId = Objects.requireNonNull(groupOfficeId, "Cannot delete a time series group without specifying the office");
        }

        String timeSeriesGroupId() {
            return timeSeriesGroupId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, groupOfficeId)
                    .addQueryParameter(CATEGORY_ID_QUERY_PARAMETER, categoryId)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON);
        }
    }
}
