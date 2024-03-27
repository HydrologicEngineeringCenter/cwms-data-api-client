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
import mil.army.usace.hec.cwms.radar.client.model.TextTimeSeries;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.*;

public final class TextTimeSeriesEndpointInput {

    private TextTimeSeriesEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll(String timeSeriesId, String office, Instant begin, Instant end) {
        return new GetAll(timeSeriesId, office, begin, end);
    }

    public static Post post(TextTimeSeries timeSeries) {
        return new Post(timeSeries);
    }

    public static Delete delete(String timeSeriesId, String officeId) {
        return new Delete(timeSeriesId, officeId);
    }

    public static final class GetAll extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String BEGIN_QUERY_PARAMETER = "begin";
        static final String END_QUERY_PARAMETER = "end";
        static final String VERSION_DATE_QUERY_PARAMETER = "version-date";
        static final String PAGE_QUERY_PARAMETER = "page";
        static final String PAGE_SIZE_QUERY_PARAMETER = "page-size";
        static final String NAME_QUERY_PARAMETER = "name";
        private final String timeSeriesId;
        private final String office;
        private final Instant begin;
        private final Instant end;
        private String page;
        private Integer pageSize;
        private Instant versionDate;

        private GetAll(String timeSeriesId, String office, Instant begin, Instant end) {
            this.timeSeriesId = Objects.requireNonNull(timeSeriesId, "Cannot access the timeseries GET endpoint without a time series identifier");
            this.office = Objects.requireNonNull(office, "Cannot access the timeseries GET endpoint without an office id");
            this.begin = Objects.requireNonNull(begin, "Cannot access the timeseries GET endpoint without a begin time");
            this.end = Objects.requireNonNull(end, "Cannot access the timeseries GET endpoint without an end time");
        }

        public GetAll page(String page) {
            this.page = page;
            return this;
        }

        public GetAll pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public GetAll versionDate(Instant versionDate) {
            this.versionDate = versionDate;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            String pageSizeString = Optional.ofNullable(pageSize).map(Object::toString).orElse(null);
            String versionDateString = Optional.ofNullable(versionDate).map(Instant::toString).orElse(null);
            String beginString = begin.toString();
            String endString = end.toString();
            return httpRequestBuilder.addQueryParameter(NAME_QUERY_PARAMETER, timeSeriesId)
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, office)
                    .addQueryParameter(BEGIN_QUERY_PARAMETER, beginString)
                    .addQueryParameter(END_QUERY_PARAMETER, endString)
                    .addQueryParameter(VERSION_DATE_QUERY_PARAMETER, versionDateString)
                    .addQueryParameter(PAGE_QUERY_PARAMETER, page)
                    .addQueryParameter(PAGE_SIZE_QUERY_PARAMETER, pageSizeString)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }


    public static final class Post extends EndpointInput {
        static final String REPLACE_ALL_PARAMETER = "replace-all";
        private final TextTimeSeries timeSeries;
        private boolean replaceAll = false;

        private Post(TextTimeSeries timeSeries) {
            this.timeSeries = Objects.requireNonNull(timeSeries, "Cannot access the timeseries POST endpoint without a time series");
        }

        TextTimeSeries timeSeries() {
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
        static final String TEXT_MASK_QUERY_PARAMETER = "text-mask";
        static final String VERSION_DATE_QUERY_PARAMETER = "version-date";
        private final String timeSeriesId;
        private final String officeId;
        private Instant begin;
        private Instant end;
        private Instant versionDate;
        private String textMask = "*";

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

        public Delete textMask(String textMask) {
            this.textMask = Objects.requireNonNull(textMask, "Null text mask is not supported for deleting text time series");
            return this;
        }

        public Delete versionDate(Instant versionDate) {
            this.versionDate = versionDate;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            String beginString = Optional.ofNullable(begin).map(Instant::toString).orElse(null);
            String endString = Optional.ofNullable(end).map(Instant::toString).orElse(null);
            String versionDate = Optional.ofNullable(this.versionDate).map(Instant::toString).orElse(null);
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(TEXT_MASK_QUERY_PARAMETER, textMask)
                    .addQueryParameter(BEGIN_PARAMETER_QUERY, beginString)
                    .addQueryParameter(END_PARAMETER_QUERY, endString)
                    .addQueryParameter(VERSION_DATE_QUERY_PARAMETER, versionDate)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
