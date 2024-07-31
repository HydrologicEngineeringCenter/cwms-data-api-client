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
import mil.army.usace.hec.cwms.radar.client.model.LocationGroup;

public final class LocationGroupEndpointInput {

    static final String OFFICE_QUERY_PARAMETER = "office";
    static final String GROUP_ID_QUERY_PARAMETER = "group-id";
    static final String CATEGORY_ID_LIKE_QUERY_PARAMETER = "location-category-like";
    static final String CATEGORY_ID_QUERY_PARAMETER = "category-id";
    static final String INCLUDE_ASSIGNED_QUERY_PARAMETER = "include-assigned";
    static final String REPLACE_ASSIGNED_LOCS = "replace-assigned-locs";

    public static GetOne getOne(String categoryId, String groupId, String officeId) {
        return new GetOne(categoryId, groupId, officeId);
    }

    public static GetAll getAll() {
        return new GetAll();
    }

    public static Post post(LocationGroup locationGroup) {
        return new Post(locationGroup);
    }

    public static Patch patch(String originalGroupId, LocationGroup locationGroup) {
        return new Patch(originalGroupId, locationGroup);
    }

    public static Delete delete(String categoryId, String groupId, String officeId) {
        return new Delete(categoryId, groupId, officeId);
    }

    public static final class GetOne extends EndpointInput {
        private final String groupId;
        private final String officeId;
        private final String categoryId;

        private GetOne(String categoryId, String groupId, String officeId) {
            this.categoryId = Objects.requireNonNull(categoryId, "Cannot retrieve a location group without a category id");
            this.groupId = Objects.requireNonNull(groupId, "Cannot retrieve a location group without a group id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot retrieve a location group without an office id");
        }

        String groupId() {
            return groupId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(GROUP_ID_QUERY_PARAMETER, groupId)
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(CATEGORY_ID_QUERY_PARAMETER, categoryId)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON);
        }
    }

    public static final class GetAll extends EndpointInput {
        private String officeId;
        private boolean includeAssigned = false;
        private String categoryIdMask;

        private GetAll() {

        }

        public GetAll officeId(String officeId) {
            this.officeId = officeId;
            return this;
        }

        public GetAll includeAssigned(boolean includeAssigned) {
            this.includeAssigned = includeAssigned;
            return this;
        }

        public GetAll categoryIdMask(String categoryIdMask) {
            this.categoryIdMask = categoryIdMask;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(INCLUDE_ASSIGNED_QUERY_PARAMETER, Boolean.toString(includeAssigned))
                    .addQueryParameter(CATEGORY_ID_LIKE_QUERY_PARAMETER, categoryIdMask)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON);
        }

    }

    public static final class Post extends EndpointInput {

        private final LocationGroup locationGroup;

        private Post(LocationGroup locationGroup) {
            this.locationGroup = Objects.requireNonNull(locationGroup, "Cannot create a location group without a data object");
        }

        LocationGroup locationGroup() {
            return locationGroup;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON);
        }

    }

    public static final class Patch extends EndpointInput {

        private final LocationGroup locationGroup;
        private final String originalGroupId;
        private boolean replaceAssignedLocs;

        private Patch(String originalGroupId, LocationGroup locationGroup) {
            this.originalGroupId = Objects.requireNonNull(originalGroupId, "Cannot update a location group without an original group id");
            this.locationGroup = Objects.requireNonNull(locationGroup, "Cannot update a location group without a data object");
        }

        String originalGroupId() {
            return originalGroupId;
        }

        LocationGroup locationGroup() {
            return locationGroup;
        }

        public Patch replaceAssignedLocs(boolean replaceAssignedLocs) {
            this.replaceAssignedLocs = replaceAssignedLocs;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(REPLACE_ASSIGNED_LOCS, Boolean.toString(replaceAssignedLocs))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON);
        }

    }

    public static final class Delete extends EndpointInput {

        private final String groupId;
        private final String officeId;
        private final String categoryId;

        private Delete(String categoryId, String groupId, String officeId) {
            this.categoryId = Objects.requireNonNull(categoryId, "Cannot delete a location group without a category id");
            this.groupId = Objects.requireNonNull(groupId, "Cannot delete a location group without a group id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot delete a location group without an office id");
        }

        String groupId() {
            return groupId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(GROUP_ID_QUERY_PARAMETER, groupId)
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(CATEGORY_ID_QUERY_PARAMETER, categoryId)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON);
        }
    }
}
