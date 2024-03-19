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

import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.StandardTextValue;

import java.util.Objects;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.*;

public final class StandardTextEndpointInput {

    private StandardTextEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll() {
        return new GetAll();
    }

    public static GetOne getOne(String textId, String officeId) {
        return new GetOne(textId, officeId);
    }

    public static Post post(StandardTextValue standardTextValue) {
        return new Post(standardTextValue);
    }

    public static Delete delete(String timeSeriesId, DeleteMethod method, String officeId) {
        return new Delete(timeSeriesId, method, officeId);
    }

    public static final class GetAll extends EndpointInput {
        static final String OFFICE_ID_MASK_PARAMETER = "office-id-mask";
        static final String TEXT_ID_MASK_PARAMETER = "text-id-mask";
        private String textIdMask;
        private String officeIdMask;

        private GetAll() {
            //empty private ctor
        }

        public GetAll officeIdMask(String officeIdMask) {
            this.officeIdMask = officeIdMask;
            return this;
        }

        public GetAll textIdMask(String textIdMask) {
            this.textIdMask = textIdMask;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(TEXT_ID_MASK_PARAMETER, textIdMask)
                    .addQueryParameter(OFFICE_ID_MASK_PARAMETER, officeIdMask)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class GetOne extends EndpointInput {
        static final String OFFICE_PARAMETER = "office";
        private final String textId;
        private final String officeId;

        private GetOne(String textId, String officeId) {
            this.textId = Objects.requireNonNull(textId, "Text id required for getOne standard text id endpoint");
            this.officeId = Objects.requireNonNull(officeId, "Office id required for getOne standard text id endpoint");
        }

        public String textId() {
            return textId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Post extends EndpointInput {
        static final String FAIL_IF_EXISTS_PARAMETER = "fail-if-exists";
        private final StandardTextValue standardTextValue;
        private boolean failIfExists = false;

        private Post(StandardTextValue standardTextValue) {
            this.standardTextValue = Objects.requireNonNull(standardTextValue,
                    "Cannot access the standardTextValue POST endpoint without a standard text value");
        }

        StandardTextValue standardTextValue() {
            return standardTextValue;
        }

        public Post failIfExists(boolean failIfExists) {
            this.failIfExists = failIfExists;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(FAIL_IF_EXISTS_PARAMETER, Boolean.toString(failIfExists))
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Delete extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String METHOD_QUERY_PARAMETER = "method";
        private final DeleteMethod deleteMethod;
        private final String officeId;
        private final String textId;

        private Delete(String textId, DeleteMethod deleteMethod, String officeId) {
            this.textId = Objects.requireNonNull(textId, "Cannot access the standard text id DELETE endpoint without an id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the timeseries DELETE endpoint without an office id");
            this.deleteMethod = Objects.requireNonNull(deleteMethod, "Cannot access the timeseries DELETE endpoint without a delete method");
        }

        String textId() {
            return textId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(METHOD_QUERY_PARAMETER, deleteMethod.toString())
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
