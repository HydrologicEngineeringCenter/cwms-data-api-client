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
        return httpRequestBuilder.addQueryParameter("name", timeSeriesId)
            .addQueryParameter("office", officeId)
            .addQueryParameter("unit", unit)
            .addQueryParameter("datum", verticalDatum)
            .addQueryParameter("begin", beginString)
            .addQueryParameter("end", endString)
            .addQueryParameter("timezone", zoneId.getId())
            .addQueryParameter("page", page)
            .addQueryParameter("pageSize", pageSizeString)
            .addQueryHeader("accept", "application/json;version=2");
    }
}
