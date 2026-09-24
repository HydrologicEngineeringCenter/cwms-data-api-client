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

import java.util.Objects;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_JSON;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesGroupQueryParameters.CASCADE_DELETE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesGroupQueryParameters.CATEGORY_ID_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesGroupQueryParameters.CATEGORY_MASK_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesGroupQueryParameters.CATEGORY_OFFICE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesGroupQueryParameters.FAIL_IF_EXISTS;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesGroupQueryParameters.GROUP_MASK_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesGroupQueryParameters.INCLUDE_ASSIGNED_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TimeSeriesGroupQueryParameters.OFFICE_QUERY_PARAMETER;

import mil.army.usace.hec.cwms.http.client.CollectionPatchEndpointInput;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.data.api.client.model.TimeSeriesGroup;
import mil.army.usace.hec.cwms.data.api.client.model.TimeSeriesGroupPatch;


public final class TimeSeriesGroupEndpointV2Input {

    private TimeSeriesGroupEndpointV2Input() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll(String groupOfficeId) {
        return new GetAll(groupOfficeId);
    }

    public static GetOne getOne(String categoryId, String groupId, String tsOfficeId, String groupOfficeId, String categoryOfficeId) {
        return new GetOne(groupOfficeId)
            .groupId(Objects.requireNonNull(groupId, "Cannot retrieve a time series group without specifying a group Id"))
            .categoryId(Objects.requireNonNull(categoryId, "Cannot retrieve a time series group without specifying a category"))
            .officeId(tsOfficeId)
            .categoryOffice(categoryOfficeId);
    }

    public static Post post(TimeSeriesGroup timeSeriesGroup) {
        return new Post(timeSeriesGroup);
    }

    public static Delete delete(String categoryId, String groupId, String groupOffice) {
        return new Delete(categoryId, groupId, groupOffice);
    }

    public static Patch patch(String groupOffice, String originalGroupId, TimeSeriesGroupPatch timeSeriesGroup) {
        return new Patch(groupOffice, originalGroupId, timeSeriesGroup);
    }

    public static final class GetAll extends EndpointInput {
        private String officeId;
        private boolean includeAssigned = true;
        private String timeSeriesCategoryMask;
        private String categoryOfficeId;
        private final String groupOfficeId;
        private String timeSeriesGroupMask;

        private GetAll(String groupOfficeId) {
            this.groupOfficeId = Objects.requireNonNull(groupOfficeId, "Cannot retrieve time series groups without specifying the group office");
        }

        public GetAll includeAssigned(boolean includeAssigned) {
            this.includeAssigned = includeAssigned;
            return this;
        }

        public GetAll timeSeriesOfficeId(String officeId) {
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

        String groupOfficeId() {
            return groupOfficeId;
        }

        public GetAll timeSeriesGroupMask(String timeSeriesGroupMask) {
            this.timeSeriesGroupMask = timeSeriesGroupMask;
            return this;
        }

        @Override
        public HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            // v2 puts the group office in the URL path, not in a query parameter.
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(INCLUDE_ASSIGNED_QUERY_PARAMETER, Boolean.toString(includeAssigned))
                .addQueryParameter(CATEGORY_MASK_QUERY_PARAMETER, timeSeriesCategoryMask)
                .addQueryParameter(CATEGORY_OFFICE_QUERY_PARAMETER, categoryOfficeId)
                .addQueryParameter(GROUP_MASK_QUERY_PARAMETER, timeSeriesGroupMask)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON);
        }
    }

    public static final class GetOne extends EndpointInput {

        private String categoryId;
        private String groupId;
        private String officeId;
        private String categoryOffice;
        private final String groupOffice;

        private GetOne(String groupOfficeId) {
            this.groupOffice = Objects.requireNonNull(groupOfficeId, "Cannot retrieve a time series group without specifying the group office");
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

        String getGroupId() {
            return groupId;
        }

        String groupOffice() {
            return groupOffice;
        }

        @Override
        public HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            // v2 puts the group office in the URL path, not in a query parameter.
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(CATEGORY_ID_QUERY_PARAMETER, categoryId)
                .addQueryParameter(CATEGORY_OFFICE_QUERY_PARAMETER, categoryOffice)
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
        public HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(FAIL_IF_EXISTS, Boolean.toString(failIfExists))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON);
        }
    }

    public static final class Delete extends EndpointInput {
        private final String timeSeriesGroupId;
        private final String categoryId;
        private final String groupOfficeId;
        private boolean cascadeDelete = false;

        private Delete(String categoryId, String timeSeriesGroupId, String groupOfficeId) {
            this.categoryId = Objects.requireNonNull(categoryId, "Cannot delete a time series group without specifying the category)");
            this.timeSeriesGroupId = Objects.requireNonNull(timeSeriesGroupId, "Cannot delete a time series group that is not defined");
            this.groupOfficeId = Objects.requireNonNull(groupOfficeId, "Cannot delete a time series group without specifying the office");
        }

        String timeSeriesGroupId() {
            return timeSeriesGroupId;
        }

        String groupOfficeId() {
            return groupOfficeId;
        }

        public Delete cascadeDelete(boolean cascadeDelete) {
            this.cascadeDelete = cascadeDelete;
            return this;
        }

        @Override
        public HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            // v2 puts the group office in the URL path, not in a query parameter.
            return httpRequestBuilder.addQueryParameter(CATEGORY_ID_QUERY_PARAMETER, categoryId)
                    .addQueryParameter(CASCADE_DELETE_QUERY_PARAMETER, Boolean.toString(cascadeDelete))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON);
        }
    }

    public static final class Patch extends CollectionPatchEndpointInput<Patch> {
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
        protected Patch self() {
            return this;
        }

        @Override
        protected HttpRequestBuilder buildInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON);
        }
    }
}
