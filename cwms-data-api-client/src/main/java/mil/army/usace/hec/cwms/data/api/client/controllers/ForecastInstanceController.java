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

import java.io.IOException;
import java.util.Set;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.data.api.client.model.ForecastInstance;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;

public final class ForecastInstanceController {
    private static final String FORECAST_INSTANCE_ENDPOINT = "forecast-instance";
    public ForecastInstance retrieveForecastInstance(ApiConnectionInfo apiConnectionInfo, ForecastInstanceEndpointInput.GetOne input) throws IOException {

        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, FORECAST_INSTANCE_ENDPOINT + "/" + input.spec().getSpecId())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), ForecastInstance.class);
        }
    }

    public Set<ForecastInstance> retrieveForecastInstances(ApiConnectionInfo apiConnectionInfo, ForecastInstanceEndpointInput.GetAll input) throws IOException {

        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, FORECAST_INSTANCE_ENDPOINT)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToSetOfObjects(response.getBody(), ForecastInstance.class);
        }
    }

    public void storeForecastInstance(ApiConnectionInfo apiConnectionInfo, ForecastInstanceEndpointInput.Post input) throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.forecastInstance());
        new HttpRequestBuilderImpl(apiConnectionInfo, FORECAST_INSTANCE_ENDPOINT)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
                .addEndpointInput(input)
                .post()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_V2)
                .execute()
                .close();
    }

    public void updateForecastInstance(ApiConnectionInfo apiConnectionInfo, ForecastInstanceEndpointInput.Patch input) throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.forecastInstance());
        new HttpRequestBuilderImpl(apiConnectionInfo, FORECAST_INSTANCE_ENDPOINT + "/" + input.originalSpecId())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
                .addEndpointInput(input)
                .patch()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_V2)
                .execute()
                .close();
    }

    public void deleteForecastInstance(ApiConnectionInfo apiConnectionInfo, ForecastInstanceEndpointInput.Delete input) throws IOException {
        String endpoint = FORECAST_INSTANCE_ENDPOINT + "/" + input.spec().getSpecId();
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
                .addEndpointInput(input)
                .delete()
                .withMediaType(ACCEPT_HEADER_V2)
                .execute()
                .close();
    }

    public byte[] getBytesFromUrl(ApiConnectionInfo apiConnectionInfo, ForecastInstance instance) throws IOException {
        byte[] retVal;
        String fileDataUrl = instance.getFileDataUrl();
        if(fileDataUrl == null || fileDataUrl.isEmpty()) {
            retVal = instance.getFileData();
        } else {
            retVal = DataDownloadUtil.readBytesFromUrl(apiConnectionInfo, fileDataUrl);
        }
        return retVal;
    }
}
