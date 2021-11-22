/*
 * MIT License
 *
 * Copyright (c) 2021 Hydrologic Engineering Center
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

import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Optional;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;

public final class TimeSeriesEndpointInput extends EndpointInput {

    public static final String OFFICE_QUERY_PARAMETER = "office";
    public static final String UNIT_QUERY_PARAMETER = "unit";
    public static final String DATUM_QUERY_PARAMETER = "datum";
    public static final String BEGIN_QUERY_PARAMETER = "begin";
    public static final String END_QUERY_PARAMETER = "end";
    public static final String TIMEZONE_QUERY_PARAMETER = "timezone";
    public static final String PAGE_QUERY_PARAMETER = "page";
    public static final String PAGE_SIZE_QUERY_PARAMETER = "pageSize";
    public static final String ACCEPT_QUERY_HEADER = "accept";
    public static final String NAME_QUERY_PARAMETER = "name";
    private final String timeSeriesId;
    private String officeId;
    private String unit = "SI";
    private ZoneId zoneId = ZoneId.of("UTC");
    private String verticalDatum;
    private Instant begin;
    private String page;
    private Integer pageSize;
    private Instant end;

    public TimeSeriesEndpointInput(String timeSeriesId) {
        this.timeSeriesId = Objects.requireNonNull(timeSeriesId, "Cannot access the timeseries endpoint without a time series identifier");
    }

    public TimeSeriesEndpointInput officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    public TimeSeriesEndpointInput unitSystem(String unitSystem) {
        this.unit = unitSystem;
        return this;
    }

    public TimeSeriesEndpointInput unit(String unit) {
        this.unit = unit;
        return this;
    }

    public TimeSeriesEndpointInput verticalDatum(String verticalDatum) {
        this.verticalDatum = verticalDatum;
        return this;
    }

    public TimeSeriesEndpointInput begin(Instant begin) {
        this.begin = begin;
        return this;
    }

    public TimeSeriesEndpointInput end(Instant end) {
        this.end = end;
        return this;
    }

    public TimeSeriesEndpointInput timeZone(ZoneId zoneId) {
        this.zoneId = zoneId;
        return this;
    }

    public TimeSeriesEndpointInput page(String page) {
        this.page = page;
        return this;
    }

    public TimeSeriesEndpointInput pageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    @Override
    protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
        String pageSizeString = Optional.ofNullable(pageSize).map(Object::toString).orElse(null);
        String beginString = Optional.ofNullable(begin).map(Object::toString).orElse(null);
        String endString = Optional.ofNullable(end).map(Object::toString).orElse(null);
        return httpRequestBuilder.addQueryParameter(NAME_QUERY_PARAMETER, timeSeriesId)
                                 .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                                 .addQueryParameter(UNIT_QUERY_PARAMETER, unit)
                                 .addQueryParameter(DATUM_QUERY_PARAMETER, verticalDatum)
                                 .addQueryParameter(BEGIN_QUERY_PARAMETER, beginString)
                                 .addQueryParameter(END_QUERY_PARAMETER, endString)
                                 .addQueryParameter(TIMEZONE_QUERY_PARAMETER, zoneId.getId())
                                 .addQueryParameter(PAGE_QUERY_PARAMETER, page)
                                 .addQueryParameter(PAGE_SIZE_QUERY_PARAMETER, pageSizeString)
                                 .addQueryHeader(ACCEPT_QUERY_HEADER, "application/json;version=2");
    }
}
