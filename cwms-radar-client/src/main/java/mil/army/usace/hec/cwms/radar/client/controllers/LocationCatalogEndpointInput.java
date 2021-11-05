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

import java.util.Optional;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;

public class LocationCatalogEndpointInput extends EndpointInput {

    private String cursor;
    private Integer pageSize;
    private String unitSystem = "SI";
    private String officeId;

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

    @Override
    protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
        String pageSizeString = Optional.ofNullable(pageSize).map(Object::toString).orElse(null);
        return httpRequestBuilder.addQueryParameter("office", officeId)
            .addQueryParameter("unitSystem", unitSystem)
            .addQueryParameter("cursor", cursor)
            .addQueryParameter("pageSize", pageSizeString)
            .addQueryHeader("accept", "application/json;version=2");
    }
}
