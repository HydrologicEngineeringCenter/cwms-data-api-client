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

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.data.api.client.model.DeleteMethod;

import java.util.Objects;
import java.util.Optional;


public final class RatingTemplateEndpointInput {

    static final String OFFICE_QUERY_PARAMETER = "office";
    static final String TEMPLATE_ID_MASK_QUERY_PARAMETER = "template-id-mask";
    static final String PAGE_QUERY_PARAMETER = "page";
    static final String PAGE_SIZE_QUERY_PARAMETER = "page-size";
    static final String FAIL_IF_EXISTS_QUERY_PARAMETER = "fail-if-exists";
    static final String DELETE_METHOD_QUERY_PARAMETER = "method";

    private RatingTemplateEndpointInput() {
        //Factory class
    }

    public static GetOne getOne(String templateId, String officeId) {
        return new GetOne(templateId, officeId);
    }

    public static GetAll getAll() {
        return new GetAll();
    }

    public static Post post(String templateXml) {
        return new Post(templateXml);
    }

    public static Delete delete(String templateId, String officeId, DeleteMethod method) {
        return new Delete(templateId, officeId, method);
    }

    public static final class GetOne extends EndpointInput {

        private final String templateId;
        private final String officeId;

        public GetOne(String templateId, String officeId) {
            this.officeId = Objects.requireNonNull(officeId, "Cannot retrieve a rating template without an id");
            this.templateId = Objects.requireNonNull(templateId, "Cannot retrieve a rating template without an office");
        }

        String templateId() {
            return templateId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class GetAll extends EndpointInput {
        private String templateIdMask;
        private String officeId;
        private String page;
        private Integer pageSize;

        private GetAll() {
            //private ctor
        }

        public GetAll officeId(String officeId) {
            this.officeId = officeId;
            return this;
        }

        public GetAll templateIdMask(String templateIdMask) {
            this.templateIdMask = templateIdMask;
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
            return httpRequestBuilder.addQueryParameter(TEMPLATE_ID_MASK_QUERY_PARAMETER, templateIdMask)
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(PAGE_QUERY_PARAMETER, page)
                    .addQueryParameter(PAGE_SIZE_QUERY_PARAMETER, pageSizeString)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Post extends EndpointInput {

        private final String templateXml;
        private boolean failIfExists = true;

        private Post(String templateXml) {
            this.templateXml = Objects.requireNonNull(templateXml, "Cannot store a rating template without xml");
        }

        public Post failIfExists(boolean failIfExists) {
            this.failIfExists = failIfExists;
            return this;
        }

        String templateXml() {
            return templateXml;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER, Boolean.toString(failIfExists))
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Delete extends EndpointInput {

        private final String templateId;
        private final String officeId;
        private final DeleteMethod method;

        private Delete(String templateId, String officeId, DeleteMethod deleteMethod) {
            this.officeId = Objects.requireNonNull(officeId, "Cannot delete rating template without an office");
            this.templateId = Objects.requireNonNull(templateId, "Cannot delete rating template without an id");
            this.method = Objects.requireNonNull(deleteMethod, "Cannot delete rating template without a delete method");
        }

        String templateId() {
            return templateId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {

            return httpRequestBuilder.addQueryParameter(DELETE_METHOD_QUERY_PARAMETER, method.toString())
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }
}