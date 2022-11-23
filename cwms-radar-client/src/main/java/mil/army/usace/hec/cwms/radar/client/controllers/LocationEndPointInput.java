/*
 * MIT License
 *
 * Copyright (c) 2022 Hydrologic Engineering Center
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

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.util.Objects;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.radar.client.model.Location;

public final class LocationEndPointInput {

    static final String OFFICE_QUERY_PARAMETER = "office";
    static final String UNIT_QUERY_PARAMETER = "unit";

    private LocationEndPointInput() {
        throw new AssertionError("Factory class");
    }

    public static GetOne getOne(String locationId) {
        return new GetOne(locationId);
    }

    public static Post post(Location location) {
        return new Post(location);
    }

    public static Delete delete(String locationId) {
        return new Delete(locationId);
    }

    public static final class GetOne extends EndpointInput {

        private final String locationId;
        private String officeId;
        private String unit = "SI";

        private GetOne(String locationId) {
            this.locationId = Objects.requireNonNull(locationId, "Cannot access the location endpoint GetOne without a location name");
        }

        String locationId() {
            return this.locationId;
        }

        public GetOne officeId(String officeId) {
            this.officeId = officeId;
            return this;
        }

        public GetOne unit(String unit) {
            this.unit = unit;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(UNIT_QUERY_PARAMETER, unit)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Post extends EndpointInput {

        private final Location location;

        private Post(Location location) {
            this.location =  Objects.requireNonNull(location, "Cannot access the location endpoint POST without a location");
        }

        Location location() {
            return location;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            //Office ID should eventually be retired server-side
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, location.getOfficeId())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }

    }

    public static final class Delete extends EndpointInput {

        private final String locationId;
        private String officeId;

        private Delete(String locationId) {
            this.locationId = Objects.requireNonNull(locationId, "Cannot access the location endpoint DELETE without a location name");
        }

        String getLocationId() {
            return this.locationId;
        }

        public Delete officeId(String officeId) {
            this.officeId = officeId;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }
}
