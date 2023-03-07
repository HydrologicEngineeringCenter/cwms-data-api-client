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

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.util.Objects;
import java.util.Optional;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesIdentifierDescriptor;

public final class TimeSeriesIdentifierEndpointInput {

    private TimeSeriesIdentifierEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetOne getOne(String timeSeriesId, String officeId) {
        return new GetOne(timeSeriesId, officeId);
    }

    public static Post post(TimeSeriesIdentifierDescriptor timeSeriesIdentifierDescriptor) {
        return new Post(timeSeriesIdentifierDescriptor);
    }

    public static Delete delete(String timeSeriesId, String officeId) {
        return new Delete(timeSeriesId, officeId);
    }

    public static final class GetOne extends EndpointInput {

        static final String OFFICE_QUERY_PARAMETER = "office";
        private final String timeSeriesId;
        private final String officeId;

        private GetOne(String timeSeriesId, String officeId) {
            this.timeSeriesId = Objects.requireNonNull(timeSeriesId,
                "Cannot access the timeseries identifier GET endpoint without a time series identifier");
            this.officeId = Objects.requireNonNull(officeId,
                "Cannot access the timeseries identifier GET endpoint without an office id");
        }

        String timeSeriesId() {
            return timeSeriesId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Post extends EndpointInput {

        static final String FAIL_IF_EXISTS_QUERY_PARAMETER = "fail-if-exists";
        private final TimeSeriesIdentifierDescriptor timeSeriesIdentifierDescriptor;
        private boolean failIfExists = true;

        private Post(TimeSeriesIdentifierDescriptor timeSeriesIdentifierDescriptor) {
            this.timeSeriesIdentifierDescriptor = Objects.requireNonNull(timeSeriesIdentifierDescriptor,
                "Cannot access the timeseries POST endpoint without a time series identifier");
        }

        TimeSeriesIdentifierDescriptor timeSeriesIdentifierDescriptor() {
            return timeSeriesIdentifierDescriptor;
        }

        public Post failIfExists(boolean failIfExists) {
            this.failIfExists = failIfExists;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            //Plan to add support for override protection and store rules here
            String failIfExistsString = Boolean.toString(failIfExists);
            return httpRequestBuilder.addQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER, failIfExistsString)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Delete extends EndpointInput {

        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String METHOD_QUERY_PARAMETER = "method";
        private final String timeSeriesId;
        private final String officeId;
        private DeleteMethod method = DeleteMethod.ALL;

        private Delete(String timeSeriesId, String officeId) {
            this.timeSeriesId = Objects.requireNonNull(timeSeriesId, "Cannot access the timeseries DELETE endpoint without a time series identifier");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the timeseries DELETE endpoint without an office id");
        }

        String timeSeriesId() {
            return timeSeriesId;
        }

        public Delete method(DeleteMethod method) {
            this.method = method;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            String methodString = Optional.ofNullable(method).map(DeleteMethod::toString).orElse(null);
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(METHOD_QUERY_PARAMETER, methodString)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
