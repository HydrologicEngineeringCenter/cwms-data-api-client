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

import java.util.Optional;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;


public final class RatingSpecEndpointInput extends EndpointInput {

    static final String OFFICE_QUERY_PARAMETER = "office";
    static final String RATING_ID_MASK_QUERY_PARAMETER = "rating-id-mask";
    static final String PAGE_QUERY_PARAMETER = "page";
    static final String PAGE_SIZE_QUERY_PARAMETER = "page-size";

    private String ratingId;
    private String ratingIdMask;
    private String officeId;

    private String page;
    private Integer pageSize;

    public RatingSpecEndpointInput() {
        this.officeId = null;
        this.ratingId = null;
        this.ratingIdMask = null;
    }

    String getRatingIdMask() {
        return ratingIdMask;
    }

    String getRatingId() {
        return ratingId;
    }

    public RatingSpecEndpointInput officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    public RatingSpecEndpointInput ratingId(String ratingId) {
        this.ratingId = ratingId;
        return this;
    }

    public RatingSpecEndpointInput ratingIdMask(String ratingMask) {
        this.ratingIdMask = ratingMask;
        return this;
    }

    public RatingSpecEndpointInput page(String page) {
        this.page = page;
        return this;
    }

    public RatingSpecEndpointInput pageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    @Override
    protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
        String pageSizeString = Optional.ofNullable(pageSize).map(Object::toString).orElse(null);

        return httpRequestBuilder
                .addQueryParameter(RATING_ID_MASK_QUERY_PARAMETER, ratingIdMask)
                .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(PAGE_QUERY_PARAMETER, page)
                .addQueryParameter(PAGE_SIZE_QUERY_PARAMETER, pageSizeString)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
    }
}