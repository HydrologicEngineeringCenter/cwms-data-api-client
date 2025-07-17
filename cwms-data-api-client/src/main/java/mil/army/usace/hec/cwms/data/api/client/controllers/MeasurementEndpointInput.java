package mil.army.usace.hec.cwms.data.api.client.controllers;

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

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.data.api.client.model.Measurement;
import java.util.Objects;

public final class MeasurementEndpointInput {

    private MeasurementEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll() {
        return new GetAll();
    }

    public static Post post(List<Measurement> measurements) {
        return new Post(measurements);
    }

    public static Delete delete(String officeId, String locationId, Instant begin, Instant end) {
        return new Delete(officeId, locationId, begin, end);
    }

    public static final class GetAll extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String LOCATION_QUERY_PARAMETER = "location-id";
        static final String UNIT_SYSTEM_QUERY_PARAMETER = "unit-system";
        static final String MIN_NUMBER_QUERY_PARAMETER = "min-number";
        static final String MAX_NUMBER_QUERY_PARAMETER = "max-number";
        static final String BEGIN_QUERY_PARAMETER = "begin";
        static final String END_QUERY_PARAMETER = "end";
        static final String TIMEZONE_QUERY_PARAMETER = "timezone";
        static final String MIN_HEIGHT_QUERY_PARAMETER = "min-height";
        static final String MAX_HEIGHT_QUERY_PARAMETER = "max-height";
        static final String MIN_FLOW_QUERY_PARAMETER = "min-flow";
        static final String MAX_FLOW_QUERY_PARAMETER = "max-flow";
        static final String AGENCY_QUERY_PARAMETER = "agency";
        static final String QUALITY_QUERY_PARAMETER = "quality";

        private String officeId;
        private String locationId;
        private String unitSystem;
        private String minNum;
        private String maxNum;
        private Instant begin;
        private Instant end;
        private String timezone;
        private Number minHeight;
        private Number maxHeight;
        private Number minFlow;
        private Number maxFlow;
        private String agency;
        private String quality;

        private GetAll() {
            //all fields are optional masks
        }

        public GetAll withOfficeIdMask(String officeId) {
            this.officeId = officeId;
            return this;
        }

        public GetAll withLocationIdMask(String locationId) {
            this.locationId = locationId;
            return this;
        }

        public GetAll withUnitSystem(String unitSystem) {
            this.unitSystem = unitSystem;
            return this;
        }

        public GetAll withMinNumber(String minNum) {
            this.minNum = minNum;
            return this;
        }

        public GetAll withMaxNumber(String maxNum) {
            this.maxNum = maxNum;
            return this;
        }

        public GetAll withBegin(Instant minDate) {
            this.begin = minDate;
            return this;
        }

        public GetAll withEnd(Instant maxDate) {
            this.end = maxDate;
            return this;
        }

        public GetAll withTimezone(String timezone) {
            this.timezone = timezone;
            return this;
        }

        public GetAll withMinHeight(Number minHeight) {
            this.minHeight = minHeight;
            return this;
        }

        public GetAll withMaxHeight(Number maxHeight) {
            this.maxHeight = maxHeight;
            return this;
        }

        public GetAll withMinFlow(Number minFlow) {
            this.minFlow = minFlow;
            return this;
        }

        public GetAll withMaxFlow(Number maxFlow) {
            this.maxFlow = maxFlow;
            return this;
        }

        public GetAll withAgency(String agency) {
            this.agency = agency;
            return this;
        }

        public GetAll withQuality(String quality) {
            this.quality = quality;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(LOCATION_QUERY_PARAMETER, locationId)
                    .addQueryParameter(UNIT_SYSTEM_QUERY_PARAMETER, unitSystem)
                    .addQueryParameter(MIN_NUMBER_QUERY_PARAMETER, getNullableFieldString(minNum))
                    .addQueryParameter(MAX_NUMBER_QUERY_PARAMETER, getNullableFieldString(maxNum))
                    .addQueryParameter(BEGIN_QUERY_PARAMETER, getNullableFieldString(minHeight))
                    .addQueryParameter(END_QUERY_PARAMETER, getNullableFieldString(maxHeight))
                    .addQueryParameter(MIN_HEIGHT_QUERY_PARAMETER, getNullableFieldString(minHeight))
                    .addQueryParameter(MAX_HEIGHT_QUERY_PARAMETER, getNullableFieldString(maxHeight))
                    .addQueryParameter(MIN_FLOW_QUERY_PARAMETER, getNullableFieldString(minFlow))
                    .addQueryParameter(MAX_FLOW_QUERY_PARAMETER, getNullableFieldString(maxFlow))
                    .addQueryParameter(BEGIN_QUERY_PARAMETER, getNullableFieldString(begin))
                    .addQueryParameter(END_QUERY_PARAMETER, getNullableFieldString(end))
                    .addQueryParameter(TIMEZONE_QUERY_PARAMETER, timezone)
                    .addQueryParameter(AGENCY_QUERY_PARAMETER, agency)
                    .addQueryParameter(QUALITY_QUERY_PARAMETER, quality)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        static final String FAIL_IF_EXISTS_QUERY_PARAMETER = "fail-if-exists";
        private final List<Measurement> measurements;
        private boolean failIfExists = true;

        private Post(List<Measurement> measurement) {
            this.measurements = Objects.requireNonNull(measurement,"Measurement is required for the POST endpoint");
        }

        public MeasurementEndpointInput.Post failIfExists(boolean failIfExists) {
            this.failIfExists = failIfExists;
            return this;
        }

        List<Measurement> measurements() {
            return new ArrayList<>(measurements);
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER, Boolean.toString(failIfExists))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Delete extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String BEGIN_QUERY_PARAMETER = "begin";
        static final String END_QUERY_PARAMETER = "end";
        static final String TIMEZONE_QUERY_PARAMETER = "timezone";
        static final String MIN_NUMBER_QUERY_PARAMETER = "min-number";
        static final String MAX_NUMBER_QUERY_PARAMETER = "max-number";
        private final String locationId;
        private final String officeId;
        private final Instant begin;
        private final Instant end;
        private String minNumber;
        private String maxNumber;
        private String timezone;

        private Delete(String officeId, String locationId, Instant begin, Instant end) {
            this.locationId = Objects.requireNonNull(locationId, "location id is required for the DELETE endpoint");
            this.officeId = Objects.requireNonNull(officeId, "office id is required for the DELETE endpoint");
            this.begin = Objects.requireNonNull(begin, "begin date is required for the DELETE endpoint");
            this.end = Objects.requireNonNull(end, "end date is required for the DELETE endpoint");
        }

        String getLocationId() {
            return locationId;
        }

        public Delete withMinNumber(String minNumber) {
            this.minNumber = minNumber;
            return this;
        }

        public Delete withMaxNumber(String maxNumber) {
            this.maxNumber = maxNumber;
            return this;
        }

        public Delete withTimezone(String timezone) {
            this.timezone = timezone;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(BEGIN_QUERY_PARAMETER, begin.toString())
                    .addQueryParameter(END_QUERY_PARAMETER, end.toString())
                    .addQueryParameter(TIMEZONE_QUERY_PARAMETER, timezone)
                    .addQueryParameter(MIN_NUMBER_QUERY_PARAMETER, getNullableFieldString(minNumber))
                    .addQueryParameter(MAX_NUMBER_QUERY_PARAMETER, getNullableFieldString(maxNumber))
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}

