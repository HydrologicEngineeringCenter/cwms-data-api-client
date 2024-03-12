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

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Optional;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeries;

public final class TimeSeriesEndpointInput {

    private TimeSeriesEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetOne getOne(String timeSeriesId) {
        return new GetOne(timeSeriesId);
    }

    public static Post post(TimeSeries timeSeries) {
        return new Post(timeSeries);
    }

    public static Delete delete(String timeSeriesId, String officeId) {
        return new Delete(timeSeriesId, officeId);
    }

    public static final class GetOne extends EndpointInput {

        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String UNIT_QUERY_PARAMETER = "unit";
        static final String DATUM_QUERY_PARAMETER = "datum";
        static final String BEGIN_QUERY_PARAMETER = "begin";
        static final String END_QUERY_PARAMETER = "end";
        static final String TIMEZONE_QUERY_PARAMETER = "timezone";
        static final String PAGE_QUERY_PARAMETER = "page";
        static final String PAGE_SIZE_QUERY_PARAMETER = "page-size";
        static final String NAME_QUERY_PARAMETER = "name";
        static final String VERSION_DATE_QUERY_PARAMETER = "version-date";

        private final String timeSeriesId;
        private String officeId;
        private String unit = "SI";
        private ZoneId zoneId = ZoneId.of("UTC");
        private String verticalDatum;
        private Instant begin;
        private String page;
        private Integer pageSize;
        private Instant versionDate;
        private Instant end;

        private GetOne(String timeSeriesId) {
            this.timeSeriesId = Objects.requireNonNull(timeSeriesId, "Cannot access the timeseries GET endpoint without a time series identifier");
        }

        public GetOne officeId(String officeId) {
            this.officeId = officeId;
            return this;
        }

        public GetOne unitSystem(String unitSystem) {
            this.unit = unitSystem;
            return this;
        }

        public GetOne unit(String unit) {
            this.unit = unit;
            return this;
        }

        public GetOne verticalDatum(String verticalDatum) {
            this.verticalDatum = verticalDatum;
            return this;
        }

        public GetOne begin(Instant begin) {
            this.begin = begin;
            return this;
        }

        public GetOne end(Instant end) {
            this.end = end;
            return this;
        }

        public GetOne versionDate(Instant versionDate) {
            this.versionDate = versionDate;
            return this;
        }

        public GetOne timeZone(ZoneId zoneId) {
            this.zoneId = zoneId;
            return this;
        }

        public GetOne page(String page) {
            this.page = page;
            return this;
        }

        public GetOne pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            String pageSizeString = Optional.ofNullable(pageSize).map(Object::toString).orElse(null);
            String beginString = Optional.ofNullable(begin).map(Object::toString).orElse(null);
            String endString = Optional.ofNullable(end).map(Object::toString).orElse(null);
            String versionDateString = Optional.ofNullable(versionDate).map(Object::toString).orElse(null);
            return httpRequestBuilder.addQueryParameter(NAME_QUERY_PARAMETER, timeSeriesId)
                .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(UNIT_QUERY_PARAMETER, unit)
                .addQueryParameter(DATUM_QUERY_PARAMETER, verticalDatum)
                .addQueryParameter(BEGIN_QUERY_PARAMETER, beginString)
                .addQueryParameter(END_QUERY_PARAMETER, endString)
                .addQueryParameter(TIMEZONE_QUERY_PARAMETER, zoneId.getId())
                .addQueryParameter(PAGE_QUERY_PARAMETER, page)
                .addQueryParameter(PAGE_SIZE_QUERY_PARAMETER, pageSizeString)
                .addQueryParameter(VERSION_DATE_QUERY_PARAMETER, versionDateString)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);

        }
    }

    public static final class Post extends EndpointInput {

        static final String VERSION_DATE_QUERY_PARAMETER = "version-date";
        static final String CREATE_AS_LRTS_QUERY_PARAMETER = "create-as-lrts";
        static final String STORE_RULE_PARAMETER = "store-rule";
        static final String OVERRIDE_PROTECTION_PARAMETER = "override-protection";
        private final TimeSeries timeSeries;
        private Instant version;
        private boolean createAsLrts = false;
        private boolean overrideProtection;
        private String storeRule;

        private Post(TimeSeries timeSeries) {
            this.timeSeries = Objects.requireNonNull(timeSeries, "Cannot access the timeseries POST endpoint without a time series");
        }

        TimeSeries timeSeries() {
            return timeSeries;
        }

        public Post versionDate(Instant version) {
            this.version = version;
            return this;
        }

        public Post createAsLrts(boolean createAsLrts) {
            this.createAsLrts = createAsLrts;
            return this;
        }

        public Post storeRule(String storeRule) {
            this.storeRule = storeRule;
            return this;
        }

        public Post overrideProtection(boolean overrideProtection) {
            this.overrideProtection = overrideProtection;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            //Plan to add support for override protection and store rules here
            String versionString = null;
            if (version != null) {
                versionString = version.toString();
            }
            return httpRequestBuilder.addQueryParameter(VERSION_DATE_QUERY_PARAMETER, versionString)
                .addQueryParameter(CREATE_AS_LRTS_QUERY_PARAMETER, Boolean.toString(createAsLrts))
                .addQueryParameter(STORE_RULE_PARAMETER, storeRule)
                .addQueryParameter(OVERRIDE_PROTECTION_PARAMETER, Boolean.toString(overrideProtection))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Delete extends EndpointInput {

        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String BEGIN_PARAMETER_QUERY = "begin";
        static final String END_PARAMETER_QUERY = "end";
        static final String VERSION_DATE_PARAMETER_QUERY = "version-date";
        static final String START_TIME_INCLUSIVE_PARAMETER_QUERY = "start-time-inclusive";
        static final String END_TIME_INCLUSIVE_PARAMETER_QUERY = "end-time-inclusive";
        static final String MAX_VERSION_PARAMETER_QUERY = "max-version";
        static final String OVERRIDE_PROTECTION_PARAMETER_QUERY = "override-protection";
        private final String timeSeriesId;
        private final String officeId;
        private Instant begin;
        private Instant end;
        private Instant version;
        private boolean startTimeInclusive = true;
        private boolean endTimeInclusive = true;
        private boolean maxVersion = true;
        private boolean overrideProtection = false;

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

        public Delete versionDate(Instant versionDate) {
            this.version = versionDate;
            return this;
        }

        public Delete startTimeInclusive(boolean startTimeInclusive) {
            this.startTimeInclusive = startTimeInclusive;
            return this;
        }

        public Delete endTimeInclusive(boolean endTimeInclusive) {
            this.endTimeInclusive = endTimeInclusive;
            return this;
        }

        public Delete maxVersion(boolean maxVersion) {
            this.maxVersion = maxVersion;
            return this;
        }

        public Delete overrideProtection(boolean overrideProtection) {
            this.overrideProtection = overrideProtection;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            String beginString = Optional.ofNullable(begin).map(Instant::toString).orElse(null);
            String endString = Optional.ofNullable(end).map(Instant::toString).orElse(null);
            String versionString = Optional.ofNullable(version).map(Instant::toString).orElse(null);
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(BEGIN_PARAMETER_QUERY, beginString)
                .addQueryParameter(END_PARAMETER_QUERY, endString)
                .addQueryParameter(VERSION_DATE_PARAMETER_QUERY, versionString)
                .addQueryParameter(START_TIME_INCLUSIVE_PARAMETER_QUERY, Boolean.toString(startTimeInclusive))
                .addQueryParameter(END_TIME_INCLUSIVE_PARAMETER_QUERY, Boolean.toString(endTimeInclusive))
                .addQueryParameter(MAX_VERSION_PARAMETER_QUERY, Boolean.toString(maxVersion))
                .addQueryParameter(OVERRIDE_PROTECTION_PARAMETER_QUERY, Boolean.toString(overrideProtection))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
