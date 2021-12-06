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

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.util.Objects;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;

public final class LocationCategoryEndpointInput extends EndpointInput {

    static final String OFFICE_QUERY_PARAMETER = "office";
    static final String CATEGORY_ID_QUERY_PARAMETER = "category-id";

    private final String categoryId;
    private String officeId;

    public LocationCategoryEndpointInput(String categoryId) {
        this.categoryId = Objects.requireNonNull(categoryId, "Cannot access the location category endpoint without a category id");
    }

    public LocationCategoryEndpointInput() {
        this.categoryId = null;
    }

    String getCategoryId() {
        return categoryId;
    }

    public LocationCategoryEndpointInput officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    @Override
    protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
        return httpRequestBuilder.addQueryParameter(CATEGORY_ID_QUERY_PARAMETER, categoryId)
                                 .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                                 .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
    }
}
