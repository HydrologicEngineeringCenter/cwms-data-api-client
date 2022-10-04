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
import java.util.Optional;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;

public final class RatingMetadataEndpointInput extends EndpointInput {

    private static final String OFFICE_QUERY_PARAMETER = "office";
    private static final String RATING_ID_MASK_QUERY_PARAMETER = "rating-id-mask";
    private static final String START_QUERY_PARAMETER = "start";
    private static final String END_QUERY_PARAMETER = "end";
    private static final String TIMEZONE_QUERY_PARAMETER = "timezone";
    private static final String PAGE_QUERY_PARAMETER = "page";
    private static final String PAGE_SIZE_QUERY_PARAMETER = "page-size";

    private String office;
    private String ratingIdMask;
    private Instant start;
    private Instant end;
    private String timezone = "UTC";
    private String page;
    private Integer pageSize;

    public RatingMetadataEndpointInput office(String office) {
        this.office = office;
        return this;
    }

    public RatingMetadataEndpointInput ratingIdMask(String ratingIdMask) {
        this.ratingIdMask = office;
        return this;
    }

    public RatingMetadataEndpointInput start(Instant start) {
        this.start = start;
        return this;
    }

    public RatingMetadataEndpointInput end(Instant end) {
        this.end = end;
        return this;
    }

    public RatingMetadataEndpointInput timezone(String timezone) {
        this.timezone = timezone;
        return this;
    }

    public RatingMetadataEndpointInput page(String page) {
        this.page = page;
        return this;
    }

    public RatingMetadataEndpointInput pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    @Override
    protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
        String pageSizeString = Optional.ofNullable(pageSize).map(Object::toString).orElse(null);
        String startString = Optional.ofNullable(start).map(Object::toString).orElse(null);
        String endString = Optional.ofNullable(end).map(Object::toString).orElse(null);
        return httpRequestBuilder.addQueryHeader(OFFICE_QUERY_PARAMETER, office)
            .addQueryHeader(RATING_ID_MASK_QUERY_PARAMETER, ratingIdMask)
            .addQueryHeader(START_QUERY_PARAMETER, startString)
            .addQueryHeader(END_QUERY_PARAMETER, endString)
            .addQueryHeader(TIMEZONE_QUERY_PARAMETER, timezone)
            .addQueryHeader(PAGE_QUERY_PARAMETER, page)
            .addQueryHeader(PAGE_SIZE_QUERY_PARAMETER, pageSizeString)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
    }
}
