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

import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;

public final class LocationLevelEndpointInput extends EndpointInput {

    static final String OFFICE_QUERY_PARAMETER = "office";
    static final String LEVEL_ID_MASK_QUERY_PARAMETER = "name";
    static final String PAGE_SIZE_QUERY_PARAMETER = "page-size";
    static final String PAGE_QUERY_PARAMETER = "page";
    static final String BEGIN_QUERY_PARAMETER = "begin";
    static final String END_QUERY_PARAMETER = "end";
    static final String UNIT_QUERY_PARAMETER = "unit";
    static final String TIMEZONE_QUERY_PARAMETER = "timezone";
    private String officeId;
    private String levelIdMask;
    private Instant begin;
    private Instant end;
    private ZoneId zoneId = ZoneId.of("UTC");
    private String unit = "SI";
    private String page;
    private Integer pageSize;

    @Override
    protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
        String pageSizeString = Optional.ofNullable(pageSize).map(Object::toString).orElse(null);
        String beginString = Optional.ofNullable(begin).map(Object::toString).orElse(null);
        String endString = Optional.ofNullable(end).map(Object::toString).orElse(null);
        return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                                 .addQueryParameter(LEVEL_ID_MASK_QUERY_PARAMETER, levelIdMask)
                                 .addQueryParameter(UNIT_QUERY_PARAMETER, unit)
                                 .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                                 .addQueryParameter(BEGIN_QUERY_PARAMETER, beginString)
                                 .addQueryParameter(END_QUERY_PARAMETER, endString)
                                 .addQueryParameter(TIMEZONE_QUERY_PARAMETER, zoneId.getId())
                                 .addQueryParameter(PAGE_QUERY_PARAMETER, page)
                                 .addQueryParameter(PAGE_SIZE_QUERY_PARAMETER, pageSizeString)
                                 .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
    }

    public LocationLevelEndpointInput officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    public LocationLevelEndpointInput levelIdMask(String levelIdMask) {
        this.levelIdMask = levelIdMask;
        return this;
    }

    public LocationLevelEndpointInput begin(Instant begin) {
        this.begin = begin;
        return this;
    }

    public LocationLevelEndpointInput end(Instant end) {
        this.end = end;
        return this;
    }

    public LocationLevelEndpointInput timeZone(ZoneId zoneId) {
        this.zoneId = zoneId;
        return this;
    }

    public LocationLevelEndpointInput page(String page) {
        this.page = page;
        return this;
    }

    public LocationLevelEndpointInput pageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public LocationLevelEndpointInput unitSystem(String unit) {
        this.unit = unit;
        return this;
    }

    public LocationLevelEndpointInput unit(String unit) {
        this.unit = unit;
        return this;
    }

}
