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
import mil.army.usace.hec.cwms.radar.client.model.Property;

import java.util.Objects;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

public final class PropertyEndpointInput {

    private PropertyEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll() {
        return new GetAll();
    }

    public static GetOne getOne(String categoryId, String propertyId, String officeId) {
        return new GetOne(categoryId, propertyId, officeId);
    }

    public static Post post(Property property) {
        return new Post(property);
    }

    public static Delete delete(String categoryId, String propertyId, String officeId) {
        return new Delete(categoryId, propertyId, officeId);
    }

    public static final class GetAll extends EndpointInput {
        static final String OFFICE_ID_MASK_PARAMETER = "office-id-mask";
        static final String CATEGORY_ID_MASK_PARAMETER = "category-id-mask";
        static final String NAME_MASK_PARAMETER = "name-mask";
        private String categoryIdMask;
        private String propertyIdMask;
        private String officeIdMask;

        private GetAll() {
            //empty private ctor
        }

        public GetAll officeIdMask(String officeIdMask) {
            this.officeIdMask = officeIdMask;
            return this;
        }

        public GetAll categoryIdMask(String categoryIdMask) {
            this.categoryIdMask = categoryIdMask;
            return this;
        }

        public GetAll propertyIdMask(String propertyIdMask) {
            this.propertyIdMask = propertyIdMask;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(CATEGORY_ID_MASK_PARAMETER, categoryIdMask)
                    .addQueryParameter(NAME_MASK_PARAMETER, propertyIdMask)
                    .addQueryParameter(OFFICE_ID_MASK_PARAMETER, officeIdMask)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class GetOne extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String CATEGORY_ID_QUERY_PARAMETER = "category-id";
        private final String categoryId;
        private final String propertyId;
        private final String officeId;

        private GetOne(String categoryId, String propertyId, String officeId) {
            this.categoryId = Objects.requireNonNull(categoryId, "Category id required for getOne property endpoint");
            this.propertyId = Objects.requireNonNull(propertyId, "Id required for getOne property endpoint");
            this.officeId = Objects.requireNonNull(officeId, "Office id required for getOne property endpoint");
        }

        public String propertyId() {
            return propertyId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(CATEGORY_ID_QUERY_PARAMETER, categoryId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        private final Property property;

        private Post(Property property) {
            this.property = Objects.requireNonNull(property,
                    "Cannot access the property POST endpoint without a property value");
        }

        Property property() {
            return property;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Delete extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String CATEGORY_ID_PARAMETER = "category-id";
        private final String categoryId;
        private final String propertyId;
        private final String officeId;

        private Delete(String categoryId, String propertyId, String officeId) {
            this.propertyId = Objects.requireNonNull(propertyId, "Cannot access the property DELETE endpoint without an id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the property DELETE endpoint without an office id");
            this.categoryId = Objects.requireNonNull(categoryId, "Cannot access the property DELETE endpoint without a category id");
        }

        String propertyId() {
            return propertyId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(CATEGORY_ID_PARAMETER, categoryId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
