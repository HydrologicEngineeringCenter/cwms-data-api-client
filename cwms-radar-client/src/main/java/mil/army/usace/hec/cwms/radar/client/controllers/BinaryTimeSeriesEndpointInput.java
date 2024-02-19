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
import mil.army.usace.hec.cwms.radar.client.model.BinaryTimeSeries;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.*;

public final class BinaryTimeSeriesEndpointInput {

    private BinaryTimeSeriesEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll(String timeSeriesId) {
        return new GetAll(timeSeriesId);
    }

    public static Post post(BinaryTimeSeries timeSeries) {
        return new Post(timeSeries);
    }

    public static Delete delete(String timeSeriesId, String officeId) {
        return new Delete(timeSeriesId, officeId);
    }

    public static final class GetAll extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String BEGIN_QUERY_PARAMETER = "begin";
        static final String END_QUERY_PARAMETER = "end";
        static final String PAGE_QUERY_PARAMETER = "page";
        static final String PAGE_SIZE_QUERY_PARAMETER = "page-size";
        static final String MIN_ATTRIBUTE_QUERY_PARAMETER = "min-attribute";
        static final String MAX_ATTRIBUTE_QUERY_PARAMETER = "max-attribute";
        static final String NAME_QUERY_PARAMETER = "name";
        static final String BINARY_TYPE_MASK_PARAMETER = "binary-type-mask";
        private final String timeSeriesId;
        private String office;
        private Instant begin;
        private String page;
        private Integer pageSize;
        private Instant end;
        private String binaryTypeMask = "*";
        private Number minAttribute;
        private Number maxAttribute;

        private GetAll(String timeSeriesId) {
            this.timeSeriesId = Objects.requireNonNull(timeSeriesId, "Cannot access the timeseries GET endpoint without a time series identifier");
        }

        public GetAll officeId(String office) {
            this.office = office;
            return this;
        }

        public GetAll begin(Instant begin) {
            this.begin = begin;
            return this;
        }

        public GetAll end(Instant end) {
            this.end = end;
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

        public GetAll minAttribute(Number minAttribute) {
            this.minAttribute = minAttribute;
            return this;
        }

        public GetAll maxAttribute(Number maxAttribute) {
            this.maxAttribute = maxAttribute;
            return this;
        }

        public GetAll getBinaryTypeMask(String binaryTypeMask) {
            this.binaryTypeMask = Objects.requireNonNull(binaryTypeMask, "Cannot retrieve binary time series without a type mask");
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            String pageSizeString = Optional.ofNullable(pageSize).map(Object::toString).orElse(null);
            String beginString = Optional.ofNullable(begin).map(Object::toString).orElse(null);
            String endString = Optional.ofNullable(end).map(Object::toString).orElse(null);
            String minAttributeString = Optional.ofNullable(minAttribute).map(Object::toString).orElse(null);
            String maxAttributeString = Optional.ofNullable(maxAttribute).map(Object::toString).orElse(null);
            return httpRequestBuilder.addQueryParameter(NAME_QUERY_PARAMETER, timeSeriesId)
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, office)
                    .addQueryParameter(BINARY_TYPE_MASK_PARAMETER, binaryTypeMask)
                    .addQueryParameter(BEGIN_QUERY_PARAMETER, beginString)
                    .addQueryParameter(END_QUERY_PARAMETER, endString)
                    .addQueryParameter(PAGE_QUERY_PARAMETER, page)
                    .addQueryParameter(PAGE_SIZE_QUERY_PARAMETER, pageSizeString)
                    .addQueryParameter(MIN_ATTRIBUTE_QUERY_PARAMETER, minAttributeString)
                    .addQueryParameter(MAX_ATTRIBUTE_QUERY_PARAMETER, maxAttributeString)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Post extends EndpointInput {
        static final String REPLACE_ALL_PARAMETER = "replace-all";
        private final BinaryTimeSeries timeSeries;
        private boolean replaceAll = false;

        private Post(BinaryTimeSeries timeSeries) {
            this.timeSeries = Objects.requireNonNull(timeSeries, "Cannot access the timeseries POST endpoint without a time series");
        }

        BinaryTimeSeries timeSeries() {
            return timeSeries;
        }

        public Post replaceAll(boolean replaceAll) {
            this.replaceAll = replaceAll;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(REPLACE_ALL_PARAMETER, Boolean.toString(replaceAll))
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Delete extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String BEGIN_PARAMETER_QUERY = "begin";
        static final String END_PARAMETER_QUERY = "end";
        static final String MIN_ATTRIBUTE_QUERY_PARAMETER = "min-attribute";
        static final String MAX_ATTRIBUTE_QUERY_PARAMETER = "max-attribute";
        static final String BINARY_TYPE_MASK_QUERY_PARAMETER = "binary-type-mask";
        private final String timeSeriesId;
        private final String officeId;
        private Instant begin;
        private Instant end;
        private Number minAttribute;
        private Number maxAttribute;
        private String binaryTypeMask = "*";

        private Delete(String timeSeriesId, String officeId) {
            this.timeSeriesId = Objects.requireNonNull(timeSeriesId, "Cannot access the timeseries DELETE endpoint without a time series identifier");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the timeseries DELETE endpoint without an office id");
        }

        String timeSeriesId() {
            return timeSeriesId;
        }

        public Delete begin(Instant begin) {
            this.begin = begin;
            return this;
        }

        public Delete end(Instant end) {
            this.end = end;
            return this;
        }

        public Delete minAttribute(Number minAttribute) {
            this.minAttribute = minAttribute;
            return this;
        }

        public Delete maxAttribute(Number maxAttribute) {
            this.maxAttribute = maxAttribute;
            return this;
        }

        public Delete binaryTypeMask(String binaryTypeMask) {
            this.binaryTypeMask = Objects.requireNonNull(binaryTypeMask, "Null binary mask is not supported for deleting binary time series");
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            String beginString = Optional.ofNullable(begin).map(Instant::toString).orElse(null);
            String endString = Optional.ofNullable(end).map(Instant::toString).orElse(null);
            String minAttributeString = Optional.ofNullable(minAttribute).map(Object::toString).orElse(null);
            String maxAttributeString = Optional.ofNullable(maxAttribute).map(Object::toString).orElse(null);
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(BINARY_TYPE_MASK_QUERY_PARAMETER, binaryTypeMask)
                    .addQueryParameter(BEGIN_PARAMETER_QUERY, beginString)
                    .addQueryParameter(END_PARAMETER_QUERY, endString)
                    .addQueryParameter(MIN_ATTRIBUTE_QUERY_PARAMETER, minAttributeString)
                    .addQueryParameter(MAX_ATTRIBUTE_QUERY_PARAMETER, maxAttributeString)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public enum Mode {
        REGULAR,
        STANDARD,
        ALL
    }
}
