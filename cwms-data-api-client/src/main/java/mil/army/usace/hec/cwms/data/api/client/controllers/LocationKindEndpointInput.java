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

import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;


public final class LocationKindEndpointInput {

    private LocationKindEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static LocationKindEndpointInput.GetAll getAll() {
        return new LocationKindEndpointInput.GetAll();
    }

    public static final class GetAll extends EndpointInput {

        static final String NAMES_QUERY_PARAMETER = "names";
        static final String LOCATION_KIND_LIKE_QUERY_PARAMETER = "location-kind-like";
        static final String OFFICE_QUERY_PARAMETER = "office";
        private String officeId;
        private String locationIdMask;
        private String locationKindMask;

        private GetAll() {
            //Empty private ctor
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(NAMES_QUERY_PARAMETER, locationIdMask)
                .addQueryParameter(LOCATION_KIND_LIKE_QUERY_PARAMETER, locationKindMask)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }

        public LocationKindEndpointInput.GetAll officeId(String officeId) {
            this.officeId = officeId;
            return this;
        }

        public LocationKindEndpointInput.GetAll locationIdMask(String locationIdMask) {
            this.locationIdMask = locationIdMask;
            return this;
        }

        public LocationKindEndpointInput.GetAll locationKindMask(String locationKindMask) {
            this.locationKindMask = locationKindMask;
            return this;
        }

    }

}
