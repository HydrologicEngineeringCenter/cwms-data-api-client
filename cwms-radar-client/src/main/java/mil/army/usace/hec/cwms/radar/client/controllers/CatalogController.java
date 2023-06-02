/*
 * MIT License
 *
 * Copyright (c) 2023 Hydrologic Engineering Center
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

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.*;

import java.io.IOException;
import java.util.List;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;

public final class CatalogController {

    private static final String CATALOG_TIMESERIES_ENDPOINT = "catalog/timeseries";
    private static final String CATALOG_LOCATIONS_ENDPOINT = "catalog/locations";
    private static final String CATALOG_PARAMETERS_ENDPOINT = "parameters";
    private static final String CATALOG_UNITS_ENDPOINT = "units";


    public TimeSeriesCatalog retrieveTimeSeriesCatalog(ApiConnectionInfo apiConnectionInfo, TimeSeriesCatalogEndpointInput input) throws IOException {
        TimeSeriesCatalog retVal;
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, CATALOG_TIMESERIES_ENDPOINT)
            .addEndpointInput(input)
            .get()
            .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            retVal = RadarObjectMapper.mapJsonToObject(response.getBody(), TimeSeriesCatalog.class);
        }
        return retVal;
    }

    public LocationCatalog retrieveLocationCatalog(ApiConnectionInfo apiConnectionInfo, LocationCatalogEndpointInput input) throws IOException {
        LocationCatalog retVal;
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, CATALOG_LOCATIONS_ENDPOINT)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            retVal = RadarObjectMapper.mapJsonToObject(response.getBody(), LocationCatalog.class);
        }
        return retVal;
    }

    public List<Parameter> retrieveParameterCatalog(ApiConnectionInfo apiConnectionInfo) throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, CATALOG_PARAMETERS_ENDPOINT)
                .addEndpointInput(new ParameterCatalogEndpointInput())
                .get()
                .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToListOfObjects(response.getBody(), Parameter.class, "parameters", "parameters");
        }
    }

    public List<Unit> retrieveUnitCatalog(ApiConnectionInfo apiConnectionInfo) throws Exception {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, CATALOG_UNITS_ENDPOINT)
                .addEndpointInput(new UnitCatalogEndpointInput())
                .get()
                .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToListOfObjects(response.getBody(), Unit.class, "units", "units");
        }
    }
}
