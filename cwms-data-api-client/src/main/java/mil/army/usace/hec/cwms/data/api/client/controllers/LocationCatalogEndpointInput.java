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

package mil.army.usace.hec.cwms.data.api.client.controllers;

import java.util.Optional;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;

public final class LocationCatalogEndpointInput extends EndpointInput {

    static final String OFFICE_QUERY_PARAMETER = "office";
    static final String UNIT_SYSTEM_QUERY_PARAMETER = "unit-system";
    static final String CURSOR_QUERY_PARAMETER = "cursor";
    static final String PAGE_SIZE_QUERY_PARAMETER = "page-size";
    static final String LIKE_QUERY_PARAMETER = "like";
    static final String CATEGORY_LIKE_QUERY_PARAMETER = "location-category-like";
    static final String GROUP_LIKE_QUERY_PARAMETER = "location-group-like";
    static final String LOCATION_KIND_LIKE_QUERY_PARAMETER = "location-kind-like";
    static final String LOCATION_TYPE_LIKE_QUERY_PARAMETER = "location-type-like";

    private String cursor;
    private Integer pageSize;
    private String unitSystem = "SI";
    private String officeId;
    private String locationIdFilter;
    private String categoryIdFilter;
    private String groupIdFilter;
    private String locationKindLike;
    private String locationTypeLike;

    public LocationCatalogEndpointInput cursor(String cursor) {
        this.cursor = cursor;
        return this;
    }

    public LocationCatalogEndpointInput pageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public LocationCatalogEndpointInput unitSystem(String unitSystem) {
        this.unitSystem = unitSystem;
        return this;
    }

    public LocationCatalogEndpointInput officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    public LocationCatalogEndpointInput locationIdFilter(String locationIdFilter) {
        this.locationIdFilter = locationIdFilter;
        return this;
    }

    public LocationCatalogEndpointInput categoryIdFilter(String categoryIdFilter) {
        this.categoryIdFilter = categoryIdFilter;
        return this;
    }

    public LocationCatalogEndpointInput groupIdFilter(String groupIdFilter) {
        this.groupIdFilter = groupIdFilter;
        return this;
    }

    public LocationCatalogEndpointInput locationKindLike(String locationKindLike) {
        this.locationKindLike = locationKindLike;
        return this;
    }

    public LocationCatalogEndpointInput locationTypeLike(String locationTypeLike) {
        this.locationTypeLike = locationTypeLike;
        return this;
    }

    @Override
    protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
        String pageSizeString = Optional.ofNullable(pageSize).map(Object::toString).orElse(null);
        return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
            .addQueryParameter(UNIT_SYSTEM_QUERY_PARAMETER, unitSystem)
            .addQueryParameter(CURSOR_QUERY_PARAMETER, cursor)
            .addQueryParameter(PAGE_SIZE_QUERY_PARAMETER, pageSizeString)
            .addQueryParameter(LIKE_QUERY_PARAMETER, locationIdFilter)
            .addQueryParameter(CATEGORY_LIKE_QUERY_PARAMETER, categoryIdFilter)
            .addQueryParameter(GROUP_LIKE_QUERY_PARAMETER, groupIdFilter)
            .addQueryParameter(LOCATION_KIND_LIKE_QUERY_PARAMETER, locationKindLike)
            .addQueryParameter(LOCATION_TYPE_LIKE_QUERY_PARAMETER, locationTypeLike)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
    }
}
