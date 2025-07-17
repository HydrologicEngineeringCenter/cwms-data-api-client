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

package mil.army.usace.hec.cwms.data.api.client.controllers;

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;

import java.time.Instant;
import java.util.Optional;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;

public final class RatingMetadataEndpointInput extends EndpointInput {

    static final String OFFICE_QUERY_PARAMETER = "office";
    static final String RATING_ID_MASK_QUERY_PARAMETER = "rating-id-mask";
    static final String START_QUERY_PARAMETER = "start";
    static final String END_QUERY_PARAMETER = "end";
    static final String TIMEZONE_QUERY_PARAMETER = "timezone";
    static final String PAGE_QUERY_PARAMETER = "page";
    static final String PAGE_SIZE_QUERY_PARAMETER = "page-size";

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
        this.ratingIdMask = ratingIdMask;
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
        return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, office)
            .addQueryParameter(RATING_ID_MASK_QUERY_PARAMETER, ratingIdMask)
            .addQueryParameter(START_QUERY_PARAMETER, startString)
            .addQueryParameter(END_QUERY_PARAMETER, endString)
            .addQueryParameter(TIMEZONE_QUERY_PARAMETER, timezone)
            .addQueryParameter(PAGE_QUERY_PARAMETER, page)
            .addQueryParameter(PAGE_SIZE_QUERY_PARAMETER, pageSizeString)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
    }
}
