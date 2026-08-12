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

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;

import java.util.Objects;
import java.util.Optional;
import mil.army.usace.hec.cwms.data.api.client.model.Pool;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;

public final class PoolEndpointInput {

    static final String ANY_MASK = "*";

    private PoolEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll() {
        return new GetAll();
    }

    public static GetOne getOne(String poolId, String projectId, String officeId) {
        return new GetOne(poolId, projectId, officeId);
    }

    public static Post post(Pool pool) {
        return new Post(pool);
    }

    public static Patch patch(String oldPoolId, String newPoolId, String officeId) {
        return new Patch(oldPoolId, newPoolId, officeId);
    }

    public static Delete delete(String poolId, String projectId, String officeId) {
        return new Delete(poolId, projectId, officeId);
    }

    public static final class GetAll extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String ID_MASK_QUERY_PARAMETER = "id-mask";
        static final String NAME_MASK_QUERY_PARAMETER = "name-mask";
        static final String BOTTOM_MASK_QUERY_PARAMETER = "bottom-mask";
        static final String TOP_MASK_QUERY_PARAMETER = "top-mask";
        static final String INCLUDE_EXPLICIT_QUERY_PARAMETER = "include-explicit";
        static final String INCLUDE_IMPLICIT_QUERY_PARAMETER = "include-implicit";
        static final String PAGE_QUERY_PARAMETER = "page";
        static final String PAGE_SIZE_QUERY_PARAMETER = "page-size";

        private String officeId;
        private String projectIdMask = ANY_MASK;
        private String nameMask = ANY_MASK;
        private String bottomMask = ANY_MASK;
        private String topMask = ANY_MASK;
        private boolean includeExplicit = false;
        private boolean includeImplicit = true;
        private String page;
        private Integer pageSize;

        private GetAll() {
            //Empty private ctor - all parameters are optional for the getAll pool endpoint
        }

        public GetAll officeId(String officeId) {
            this.officeId = officeId;
            return this;
        }

        public GetAll projectIdMask(String projectIdMask) {
            this.projectIdMask = projectIdMask;
            return this;
        }

        public GetAll nameMask(String nameMask) {
            this.nameMask = nameMask;
            return this;
        }

        public GetAll bottomMask(String bottomMask) {
            this.bottomMask = bottomMask;
            return this;
        }

        public GetAll topMask(String topMask) {
            this.topMask = topMask;
            return this;
        }

        public GetAll includeExplicit(boolean includeExplicit) {
            this.includeExplicit = includeExplicit;
            return this;
        }

        public GetAll includeImplicit(boolean includeImplicit) {
            this.includeImplicit = includeImplicit;
            return this;
        }

        public GetAll page(String page) {
            this.page = page;
            return this;
        }

        public GetAll pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            String pageSizeString = Optional.ofNullable(pageSize).map(Object::toString).orElse(null);
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(ID_MASK_QUERY_PARAMETER, projectIdMask)
                    .addQueryParameter(NAME_MASK_QUERY_PARAMETER, nameMask)
                    .addQueryParameter(BOTTOM_MASK_QUERY_PARAMETER, bottomMask)
                    .addQueryParameter(TOP_MASK_QUERY_PARAMETER, topMask)
                    .addQueryParameter(INCLUDE_EXPLICIT_QUERY_PARAMETER, Boolean.toString(includeExplicit))
                    .addQueryParameter(INCLUDE_IMPLICIT_QUERY_PARAMETER, Boolean.toString(includeImplicit))
                    .addQueryParameter(PAGE_QUERY_PARAMETER, page)
                    .addQueryParameter(PAGE_SIZE_QUERY_PARAMETER, pageSizeString)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class GetOne extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String PROJECT_ID_QUERY_PARAMETER = "project-id";
        static final String BOTTOM_MASK_QUERY_PARAMETER = "bottom-mask";
        static final String TOP_MASK_QUERY_PARAMETER = "top-mask";
        static final String INCLUDE_EXPLICIT_QUERY_PARAMETER = "include-explicit";
        static final String INCLUDE_IMPLICIT_QUERY_PARAMETER = "include-implicit";

        private final String poolId;
        private final String projectId;
        private final String officeId;
        private String bottomMask = ANY_MASK;
        private String topMask = ANY_MASK;
        private boolean includeExplicit = true;
        private boolean includeImplicit = true;

        private GetOne(String poolId, String projectId, String officeId) {
            this.poolId = Objects.requireNonNull(poolId, "Pool id required for getOne pool endpoint");
            this.projectId = Objects.requireNonNull(projectId, "Project id required for getOne pool endpoint");
            this.officeId = Objects.requireNonNull(officeId, "Office id required for getOne pool endpoint");
        }

        public String poolId() {
            return poolId;
        }

        public GetOne bottomMask(String bottomMask) {
            this.bottomMask = bottomMask;
            return this;
        }

        public GetOne topMask(String topMask) {
            this.topMask = topMask;
            return this;
        }

        public GetOne includeExplicit(boolean includeExplicit) {
            this.includeExplicit = includeExplicit;
            return this;
        }

        public GetOne includeImplicit(boolean includeImplicit) {
            this.includeImplicit = includeImplicit;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(PROJECT_ID_QUERY_PARAMETER, projectId)
                    .addQueryParameter(BOTTOM_MASK_QUERY_PARAMETER, bottomMask)
                    .addQueryParameter(TOP_MASK_QUERY_PARAMETER, topMask)
                    .addQueryParameter(INCLUDE_EXPLICIT_QUERY_PARAMETER, Boolean.toString(includeExplicit))
                    .addQueryParameter(INCLUDE_IMPLICIT_QUERY_PARAMETER, Boolean.toString(includeImplicit))
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Post extends EndpointInput {
        static final String FAIL_IF_EXISTS_QUERY_PARAMETER = "fail-if-exists";
        static final String CREATE_POOL_NAME_QUERY_PARAMETER = "create-pool-name";

        private final Pool pool;
        private boolean failIfExists = true;
        private boolean createPoolName = true;

        private Post(Pool pool) {
            this.pool = Objects.requireNonNull(pool, "Cannot access the pool POST endpoint without a pool value");
        }

        public Post failIfExists(boolean failIfExists) {
            this.failIfExists = failIfExists;
            return this;
        }

        public Post createPoolName(boolean createPoolName) {
            this.createPoolName = createPoolName;
            return this;
        }

        Pool pool() {
            return pool;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER, Boolean.toString(failIfExists))
                    .addQueryParameter(CREATE_POOL_NAME_QUERY_PARAMETER, Boolean.toString(createPoolName))
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Patch extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String NAME_QUERY_PARAMETER = "name";

        private final String oldPoolId;
        private final String newPoolId;
        private final String officeId;

        private Patch(String oldPoolId, String newPoolId, String officeId) {
            this.oldPoolId = Objects.requireNonNull(oldPoolId, "Cannot access the pool PATCH endpoint without an old id");
            this.newPoolId = Objects.requireNonNull(newPoolId, "Cannot access the pool PATCH endpoint without a new id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the pool PATCH endpoint without an office id");
        }

        String poolId() {
            return oldPoolId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(NAME_QUERY_PARAMETER, newPoolId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Delete extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String PROJECT_ID_QUERY_PARAMETER = "project-id";

        private final String poolId;
        private final String projectId;
        private final String officeId;

        private Delete(String poolId, String projectId, String officeId) {
            this.poolId = Objects.requireNonNull(poolId, "Cannot access the pool DELETE endpoint without an id");
            this.projectId = Objects.requireNonNull(projectId, "Cannot access the pool DELETE endpoint without a project id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the pool DELETE endpoint without an office id");
        }

        String poolId() {
            return poolId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(PROJECT_ID_QUERY_PARAMETER, projectId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }
}
