/*
 * MIT License
 *
 * Copyright (c) 2025 Hydrologic Engineering Center
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

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;

import java.util.Objects;
import mil.army.usace.hec.cwms.data.api.client.model.Entity;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;

public final class EntityEndpointInput {

    private EntityEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll() {
        return new GetAll();
    }

    public static GetOne getOne(String entityId, String officeId) {
        return new GetOne(entityId, officeId);
    }

    public static Post post(Entity entity) {
        return new Post(entity);
    }

    public static Patch patch(Entity entity) {
        return new Patch(entity);
    }

    public static Delete delete(String entityId, String officeId, boolean cascadeDelete) {
        return new Delete(entityId, officeId, cascadeDelete);
    }

    public static final class GetAll extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String ENTITY_ID_QUERY_PARAMETER = "entity-id";
        static final String PARENT_ENTITY_ID_QUERY_PARAMETER = "parent-entity-id";
        static final String CATEGORY_ID_QUERY_PARAMETER = "category-id";
        static final String LONG_NAME_QUERY_PARAMETER = "long-name";
        static final String MATCH_NULL_PARENTS_QUERY_PARAMETER = "match-null-parents";

        private String officeId;
        private String entityId;
        private String parentEntityId;
        private String categoryId;
        private String longName;
        private boolean matchNullParents = true;

        private GetAll() {
            // factory
        }

        public GetAll officeId(String officeId) {
            this.officeId = officeId;
            return this;
        }

        public GetAll entityId(String entityId) {
            this.entityId = entityId;
            return this;
        }

        public GetAll parentEntityId(String parentEntityId) {
            this.parentEntityId = parentEntityId;
            return this;
        }

        public GetAll categoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public GetAll longName(String longName) {
            this.longName = longName;
            return this;
        }

        public GetAll matchNullParents(boolean matchNullParents) {
            this.matchNullParents = matchNullParents;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(ENTITY_ID_QUERY_PARAMETER, entityId)
                    .addQueryParameter(PARENT_ENTITY_ID_QUERY_PARAMETER, parentEntityId)
                    .addQueryParameter(CATEGORY_ID_QUERY_PARAMETER, categoryId)
                    .addQueryParameter(LONG_NAME_QUERY_PARAMETER, longName)
                    .addQueryParameter(MATCH_NULL_PARENTS_QUERY_PARAMETER, Boolean.toString(matchNullParents))
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class GetOne extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        private final String entityId;
        private final String officeId;

        private GetOne(String entityId, String officeId) {
            this.entityId = Objects.requireNonNull(entityId, "Id required for getOne entity endpoint");
            this.officeId = Objects.requireNonNull(officeId, "Office id required for getOne entity endpoint");
        }

        String entityId() {
            return entityId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        private final Entity entity;

        private Post(Entity entity) {
            this.entity = Objects.requireNonNull(entity, "Cannot access the entity POST endpoint without an entity object");
        }

        Entity entity() {
            return entity;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Patch extends EndpointInput {
        private final Entity entity;

        private Patch(Entity entity) {
            this.entity = Objects.requireNonNull(entity, "Cannot access the entity PATCH endpoint without an entity object");
        }

        Entity entity() {
            return entity;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Delete extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String CASCADE_DELETE_QUERY_PARAMETER = "cascade-delete";
        private final String entityId;
        private final String officeId;
        private final boolean cascadeDelete;

        private Delete(String entityId, String officeId, boolean cascadeDelete) {
            this.entityId = Objects.requireNonNull(entityId, "Cannot access the entity DELETE endpoint without an id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the entity DELETE endpoint without an office id");
            this.cascadeDelete = cascadeDelete;
        }

        String entityId() {
            return entityId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder
                .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(CASCADE_DELETE_QUERY_PARAMETER, Boolean.toString(cascadeDelete))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
