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

package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesGroup;

import java.util.Objects;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

public final class TimeSeriesGroupEndpointInput {

    static final String OFFICE_QUERY_PARAMETER = "office";
    static final String CATEGORY_ID_QUERY_PARAMETER = "category-id";
    static final String INCLUDE_ASSIGNED_QUERY_PARAMETER = "include-assigned";
    static final String FAIL_IF_EXISTS = "fail-if-exists";
    static final String REPLACE_ASSIGNED_TS_QUERY_PARAMETER = "replace-assigned-ts";

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

    public static Patch patch(String originalGroupId, TimeSeriesGroup timeSeriesGroup) {
        return new Patch(originalGroupId, timeSeriesGroup);
    }

    public static Delete delete(String categoryId, String groupId, String officeId) {
        return new Delete(categoryId, groupId, officeId);
    }

    public static final class GetAll extends EndpointInput {
        private String officeId;
        private boolean includeAssigned = true;

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

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(INCLUDE_ASSIGNED_QUERY_PARAMETER, Boolean.toString(includeAssigned))
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class GetOne extends EndpointInput {

        private final String categoryId;
        private final String groupId;
        private final String officeId;

        private GetOne(String categoryId, String groupId, String officeId) {
            this.categoryId = Objects.requireNonNull(categoryId, "Cannot retrieve a time series group without specifying a category");
            this.groupId = Objects.requireNonNull(groupId, "Cannot retrieve a time series group without specifying a group Id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot retrieve a time series group without specifying an office");
        }

        String getGroupId() {
            return groupId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(CATEGORY_ID_QUERY_PARAMETER, categoryId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
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
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Patch extends EndpointInput {

        private final TimeSeriesGroup timeSeriesGroup;
        private final String originalGroupId;
        private boolean replaceAssignedTs;

        private Patch(String originalGroupId, TimeSeriesGroup timeSeriesGroup) {
            this.originalGroupId = Objects.requireNonNull(originalGroupId, "Cannot update a time series group without specifying the group id");
            this.timeSeriesGroup = Objects.requireNonNull(timeSeriesGroup, "Cannot update a time series group without a group data object");
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
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Delete extends EndpointInput {
        private final String timeSeriesGroupId;
        private final String categoryId;
        private final String officeId;

        private Delete(String categoryId, String timeSeriesGroupId, String officeId) {
            this.categoryId = Objects.requireNonNull(categoryId, "Cannot delete a time series group without specifying the category)");
            this.timeSeriesGroupId = Objects.requireNonNull(timeSeriesGroupId, "Cannot delete a time series group that is not defined");
            this.officeId = Objects.requireNonNull(officeId, "Cannot delete a time series group without specifying the office");
        }

        String timeSeriesGroupId() {
            return timeSeriesGroupId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(CATEGORY_ID_QUERY_PARAMETER, categoryId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
