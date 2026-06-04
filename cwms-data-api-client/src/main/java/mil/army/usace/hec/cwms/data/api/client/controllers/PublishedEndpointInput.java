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

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;

import java.util.Objects;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;

public final class PublishedEndpointInput {

    private PublishedEndpointInput() {
        throw new AssertionError("Factory class");
    }

    public static GetAll getAll() {
        return new GetAll();
    }

    public static final class GetAll extends EndpointInput {

        static final String OFFICE_MASK_QUERY_PARAMETER = "office-mask";
        static final String LOCATION_MASK_QUERY_PARAMETER = "location-mask";
        static final String PAGE_QUERY_PARAMETER = "page";
        static final String PAGE_SIZE_QUERY_PARAMETER = "page-size";

        private String officeIdMask;
        private String locationIdMask;
        private String page;
        private Integer pageSize;

        private GetAll() {
        }

        public GetAll withOfficeIdMask(String officeIdMask) {
            this.officeIdMask = officeIdMask;
            return this;
        }

        public GetAll withLocationIdMask(String locationIdMask) {
            this.locationIdMask = locationIdMask;
            return this;
        }

        public GetAll withPage(String page) {
            this.page = page;
            return this;
        }

        public GetAll withPageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_MASK_QUERY_PARAMETER, officeIdMask)
                .addQueryParameter(LOCATION_MASK_QUERY_PARAMETER, locationIdMask)
                .addQueryParameter(PAGE_QUERY_PARAMETER, page)
                .addQueryParameter(PAGE_SIZE_QUERY_PARAMETER, getNullableFieldString(pageSize))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            GetAll getAll = (GetAll) o;
            return Objects.equals(officeIdMask, getAll.officeIdMask)
                && Objects.equals(locationIdMask, getAll.locationIdMask)
                && Objects.equals(page, getAll.page)
                && Objects.equals(pageSize, getAll.pageSize);
        }

        @Override
        public int hashCode() {
            return Objects.hash(officeIdMask, locationIdMask, page, pageSize);
        }
    }
}
