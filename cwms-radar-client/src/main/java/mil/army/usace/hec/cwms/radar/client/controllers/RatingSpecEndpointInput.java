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
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;

import java.util.Objects;
import java.util.Optional;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;


public final class RatingSpecEndpointInput {

    static final String OFFICE_QUERY_PARAMETER = "office";
    static final String RATING_ID_MASK_QUERY_PARAMETER = "rating-id-mask";
    static final String PAGE_QUERY_PARAMETER = "page";
    static final String PAGE_SIZE_QUERY_PARAMETER = "page-size";
    static final String FAIL_IF_EXISTS_QUERY_PARAMETER = "fail-if-exists";
    static final String DELETE_METHOD_QUERY_PARAMETER = "method";

    private RatingSpecEndpointInput() {
        //factory class
    }

    public static GetOne getOne(String ratingId, String officeId) {
        return new GetOne(ratingId, officeId);
    }

    public static GetAll getAll() {
        return new GetAll();
    }

    public static Post post(String ratingSpecXml) {
        return new Post(ratingSpecXml);
    }

    public static Delete delete(String ratingId, String officeId, DeleteMethod deleteMethod) {
        return new Delete(ratingId, officeId, deleteMethod);
    }

    public static final class GetOne extends EndpointInput {
        private final String ratingId;
        private final String officeId;

        private GetOne(String ratingId, String officeId) {
            this.ratingId = Objects.requireNonNull(ratingId, "Cannot retrieve rating spec without an id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot retrieve rating spec without an office");
        }

        String ratingId() {
            return ratingId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {

            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class GetAll extends EndpointInput {
        private String ratingIdMask;
        private String officeId;
        private String page;
        private Integer pageSize;

        private GetAll() {
            //empty private ctor
        }

        public GetAll officeId(String officeId) {
            this.officeId = officeId;
            return this;
        }

        public GetAll ratingIdMask(String ratingMask) {
            this.ratingIdMask = ratingMask;
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

            return httpRequestBuilder
                    .addQueryParameter(RATING_ID_MASK_QUERY_PARAMETER, ratingIdMask)
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(PAGE_QUERY_PARAMETER, page)
                    .addQueryParameter(PAGE_SIZE_QUERY_PARAMETER, pageSizeString)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Post extends EndpointInput {

        private final String ratingSpecXml;
        private boolean failIfExists = true;

        private Post(String ratingSpecXml) {
            this.ratingSpecXml = Objects.requireNonNull(ratingSpecXml, "Cannot store rating spec without xml");
        }

        String ratingSpecXml() {
            return ratingSpecXml;
        }

        public Post failIfExists(boolean failIfExists) {
            this.failIfExists = failIfExists;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER, Boolean.toString(failIfExists))
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Delete extends EndpointInput {
        private final String ratingId;
        private final DeleteMethod deleteMethod;
        private final String officeId;

        private Delete(String ratingId, String officeId, DeleteMethod deleteMethod) {
            this.officeId = Objects.requireNonNull(officeId, "Cannot delete rating without an id");
            this.ratingId = Objects.requireNonNull(ratingId, "Cannot delete rating without an office");
            this.deleteMethod = Objects.requireNonNull(deleteMethod, "Cannot delete rating without a delete method");
        }

        String ratingId() {
            return ratingId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {

            return httpRequestBuilder.addQueryParameter(DELETE_METHOD_QUERY_PARAMETER, deleteMethod.toString())
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }
}