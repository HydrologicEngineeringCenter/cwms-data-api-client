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

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.radar.client.model.LocationCatalog;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesCatalog;

public final class CatalogController {

    private static final String CATALOG_TIMESERIES_ENDPOINT = "catalog/timeseries";
    private static final String CATALOG_LOCATIONS_ENDPOINT = "catalog/locations";

    public TimeSeriesCatalog retrieveTimeSeriesCatalog(ApiConnectionInfo apiConnectionInfo, TimeSeriesCatalogEndpointInput input) throws IOException {
        HttpRequestResponse response = new HttpRequestBuilderImpl(apiConnectionInfo, CATALOG_TIMESERIES_ENDPOINT)
            .addEndpointInput(input)
            .execute();
        return RadarObjectMapper.mapJsonToObject(response.getBody(), TimeSeriesCatalog.class);
    }

    public LocationCatalog retrieveLocationCatalog(ApiConnectionInfo apiConnectionInfo, LocationCatalogEndpointInput input) throws IOException {
        HttpRequestResponse response = new HttpRequestBuilder(apiConnectionInfo, CATALOG_LOCATIONS_ENDPOINT)
            .addEndpointInput(input)
            .execute();
        return RadarObjectMapper.mapJsonToObject(response.getBody(), LocationCatalog.class);
    }
}
