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

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_JSON;

import java.io.IOException;
import java.util.List;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesCategory;


public final class TimeSeriesCategoryController {

    private static final String TIME_SERIES_CATEGORY_ENDPOINT = "timeseries/category";

    public TimeSeriesCategory retrieveTimeSeriesCategory(ApiConnectionInfo apiConnectionInfo,
                                                         TimeSeriesCategoryEndpointInput.GetOne input)
            throws IOException {
        String endpoint = TIME_SERIES_CATEGORY_ENDPOINT + "/" + input.categoryId();
        TimeSeriesCategory retVal;
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_JSON);
        try (HttpRequestResponse response = executor.execute()) {
            retVal = RadarObjectMapper.mapJsonToObject(response.getBody(), TimeSeriesCategory.class);
        }
        return retVal;
    }

    public List<TimeSeriesCategory> retrieveTimeSeriesCategories(ApiConnectionInfo apiConnectionInfo,
                                                                 TimeSeriesCategoryEndpointInput.GetAll input)
            throws IOException {
        List<TimeSeriesCategory> retVal;
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, TIME_SERIES_CATEGORY_ENDPOINT)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_JSON);
        try (HttpRequestResponse response = executor.execute()) {
            retVal = RadarObjectMapper.mapJsonToListOfObjects(response.getBody(), TimeSeriesCategory.class);
        }
        return retVal;
    }

    public void storeTimeSeriesCategory(ApiConnectionInfo apiConnectionInfo,
                                        TimeSeriesCategoryEndpointInput.Post input)
            throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.timeSeriesCategory());
        new HttpRequestBuilderImpl(apiConnectionInfo, TIME_SERIES_CATEGORY_ENDPOINT)
                .addEndpointInput(input)
                .post()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_JSON)
                .execute()
                .close();
    }

    public void deleteCategory(ApiConnectionInfo apiConnectionInfo, TimeSeriesCategoryEndpointInput.Delete input)
            throws IOException {
        String endpoint = TIME_SERIES_CATEGORY_ENDPOINT + "/" + input.categoryId();
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addEndpointInput(input)
                .delete()
                .withMediaType(ACCEPT_HEADER_JSON)
                .execute()
                .close();
    }
}
