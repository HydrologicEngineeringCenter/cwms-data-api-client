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

package mil.army.usace.hec.cwms.data.api.client.controllers;

import java.util.Objects;
import java.util.Optional;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.data.api.client.model.Clob;

public final class ClobEndpointInput {

    static final String OFFICE_QUERY_PARAMETER = "office";
    static final String CLOB_ID_MASK_QUERY_PARAMETER = "like";
    static final String CLOB_ID_QUERY_PARAMETER = "clob-id";
    static final String IGNORE_NULLS_QUERY_PARAMETER = "ignore-nulls";
    static final String FAIL_IF_EXISTS_QUERY_PARAMETER = "fail-if-exists";
    static final String PAGE_SIZE_QUERY_PARAMETER = "page-size";
    static final String PAGE_QUERY_PARAMETER = "page";
    static final String INCLUDE_VALUES_QUERY_PARAMETER = "include-values";

    private ClobEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetOne getOne(String clobId, String officeId) {
        return new GetOne(clobId, officeId);
    }

    public static GetAll getAll() {
        return new GetAll();
    }

    public static Patch patch(Clob clob) {
        return new Patch(clob);
    }

    public static Post post(Clob clob) {
        return new Post(clob);
    }

    public static Delete delete(String clobId, String office) {
        return new Delete(clobId, office);
    }

    public static final class GetOne extends EndpointInput {
        private final String officeId;
        private final String clobId;

        private GetOne(String clobId, String officeId) {
            this.clobId = Objects.requireNonNull(clobId, "Cannot retrieve Clob data without a clob id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot retrieve Clob data without an office id");
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId).addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }

        String clobId() {
            return clobId;
        }
    }

    public static final class GetAll extends EndpointInput {
        private String office;
        private String clobIdMask = "*";
        private String page;
        private Integer pageSize;
        private boolean includeValues = false;

        private GetAll() {
            //Empty private ctor
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            String pageSizeString = Optional.ofNullable(pageSize).map(Object::toString).orElse(null);
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, office).addQueryParameter(CLOB_ID_MASK_QUERY_PARAMETER, clobIdMask)
                .addQueryParameter(OFFICE_QUERY_PARAMETER, office).addQueryParameter(PAGE_QUERY_PARAMETER, page)
                .addQueryParameter(PAGE_SIZE_QUERY_PARAMETER, pageSizeString)
                .addQueryParameter(INCLUDE_VALUES_QUERY_PARAMETER, Boolean.toString(includeValues))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }

        public GetAll officeId(String officeId) {
            this.office = officeId;
            return this;
        }

        public GetAll clobIdMask(String levelIdMask) {
            this.clobIdMask = levelIdMask;
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

        public GetAll includeValues(boolean includeValues) {
            this.includeValues = includeValues;
            return this;
        }
    }

    public static final class Patch extends EndpointInput {

        private final Clob clob;
        private boolean ignoreNulls = true;

        private Patch(Clob clob) {
            this.clob = Objects.requireNonNull(clob, "Cannot patch Clob without Clob data");
        }

        public Patch ignoreNulls(boolean ignoreNulls) {
            this.ignoreNulls = ignoreNulls;
            return this;
        }

        Clob clob() {
            return clob;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(CLOB_ID_QUERY_PARAMETER, clob.getId())
                .addQueryParameter(IGNORE_NULLS_QUERY_PARAMETER, Boolean.toString(ignoreNulls))
                .addQueryParameter(OFFICE_QUERY_PARAMETER, clob.getOffice())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Post extends EndpointInput {

        private final Clob clob;
        private boolean failIfExists = true;

        private Post(Clob clob) {
            this.clob = Objects.requireNonNull(clob, "Cannot store Clob without a Clob object");
        }

        Clob clob() {
            return clob;
        }

        public Post failIfExists(boolean failIfExists) {
            this.failIfExists = failIfExists;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER, Boolean.toString(failIfExists))
                .addQueryParameter(OFFICE_QUERY_PARAMETER, clob.getOffice())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Delete extends EndpointInput {

        private final String clobId;
        private final String office;

        private Delete(String clobId, String office) {
            this.clobId = Objects.requireNonNull(clobId, "Cannot delete Clob without a clob id");
            this.office = Objects.requireNonNull(office, "Cannot delete Clob without an office id");
        }

        String clobId() {
            return clobId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(CLOB_ID_QUERY_PARAMETER, clobId).addQueryParameter(OFFICE_QUERY_PARAMETER, office)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }
}
