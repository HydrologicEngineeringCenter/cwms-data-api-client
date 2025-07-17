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
package mil.army.usace.hec.cwms.data.api.client.controllers;

import java.util.Objects;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.data.api.client.model.LookupType;

public final class LookupTypeEndpointInput {

    private LookupTypeEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll(String officeId, String category, String prefix) {
        return new GetAll(officeId, category, prefix);
    }

    public static Post post(String category, String prefix, LookupType lookupType) {
        return new Post(category, prefix, lookupType);
    }

    public static Delete delete(String officeId, String category, String prefix, String displayValue) {
        return new Delete(officeId, category, prefix, displayValue);
    }

    public static final class GetAll extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String CATEGORY_QUERY_PARAMETER = "category";
        static final String PREFIX_QUERY_PARAMETER = "prefix";
        private final String officeId;
        private final String category;
        private final String prefix;

        private GetAll(String officeId, String category, String prefix) {
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the lookup type GET endpoint without an office id");
            this.category = Objects.requireNonNull(category, "Cannot access the lookup type GET endpoint without a category value");
            this.prefix = Objects.requireNonNull(prefix, "Cannot access the lookup type GET endpoint without a prefix value");
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(CATEGORY_QUERY_PARAMETER, category)
                    .addQueryParameter(PREFIX_QUERY_PARAMETER, prefix)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        static final String CATEGORY_QUERY_PARAMETER = "category";
        static final String PREFIX_QUERY_PARAMETER = "prefix";
        private final String category;
        private final String prefix;
        private final LookupType lookupType;

        private Post(String category, String prefix, LookupType lookupType) {
            this.category = Objects.requireNonNull(category, "Cannot access the lookup type POST endpoint without a category value");
            this.prefix = Objects.requireNonNull(prefix, "Cannot access the lookup type POST endpoint without a prefix value");
            this.lookupType = Objects.requireNonNull(lookupType, "Cannot access the lookup type POST endpoint without a lookup type");
        }

        LookupType getLookupType() {
            return lookupType;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(CATEGORY_QUERY_PARAMETER, category)
                    .addQueryParameter(PREFIX_QUERY_PARAMETER, prefix)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Delete extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String CATEGORY_QUERY_PARAMETER = "category";
        static final String PREFIX_QUERY_PARAMETER = "prefix";
        private final String officeId;
        private final String category;
        private final String prefix;
        private final String displayValue;

        private Delete(String officeId, String category, String prefix, String displayValue) {
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the lookup type DELETE endpoint without an office id");
            this.category = Objects.requireNonNull(category, "Cannot access the lookup type DELETE endpoint without a category value");
            this.prefix = Objects.requireNonNull(prefix, "Cannot access the lookup type DELETE endpoint without a prefix value");
            this.displayValue = Objects.requireNonNull(displayValue, "Cannot access the lookup type DELETE endpoint without a specified display value to delete");
        }

        String getDisplayValue() {
            return displayValue;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(CATEGORY_QUERY_PARAMETER, category)
                    .addQueryParameter(PREFIX_QUERY_PARAMETER, prefix)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}