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

package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfoFactory;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.RegularTextTimeSeriesRow;
import mil.army.usace.hec.cwms.radar.client.model.TextTimeSeries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.FALSE;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.NEW_LRTS_ID_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.USE_NEW_LRTS_ID;

public final class TextTimeSeriesController {

    private static final String TEXT_TIME_SERIES_ENDPOINT = "timeseries/text";
    private static final int BUFFER_SIZE = 1024;

    public TextTimeSeries retrieveTimeSeries(ApiConnectionInfo apiConnectionInfo, TextTimeSeriesEndpointInput.GetAll timeSeriesEndpointInput)
            throws IOException {
        TextTimeSeries retVal;
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, TEXT_TIME_SERIES_ENDPOINT)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
                .addQueryHeader(NEW_LRTS_ID_HEADER, System.getProperty(USE_NEW_LRTS_ID, FALSE))
                .addEndpointInput(timeSeriesEndpointInput)
                .get()
                .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            retVal = RadarObjectMapper.mapJsonToObject(response.getBody(), TextTimeSeries.class);
        }
        return retVal;
    }

    public void storeTimeSeries(ApiConnectionInfo apiConnectionInfo, TextTimeSeriesEndpointInput.Post timeSeriesEndpointInput) throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(timeSeriesEndpointInput.timeSeries());
        new HttpRequestBuilderImpl(apiConnectionInfo, TEXT_TIME_SERIES_ENDPOINT)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
                .addQueryHeader(NEW_LRTS_ID_HEADER, System.getProperty(USE_NEW_LRTS_ID, FALSE))
                .addEndpointInput(timeSeriesEndpointInput)
                .post()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_V2)
                .execute()
                .close();
    }

    public void deleteTimeSeries(ApiConnectionInfo apiConnectionInfo, TextTimeSeriesEndpointInput.Delete timeSeriesEndpointInput) throws IOException {
        String endpoint = TEXT_TIME_SERIES_ENDPOINT + "/" + timeSeriesEndpointInput.timeSeriesId();
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
                .addQueryHeader(NEW_LRTS_ID_HEADER, System.getProperty(USE_NEW_LRTS_ID, FALSE))
                .addEndpointInput(timeSeriesEndpointInput)
                .delete()
                .withMediaType(ACCEPT_HEADER_V2)
                .execute()
                .close();
    }

    public String getTextValueFromUrl(ApiConnectionInfo apiConnectionInfo, RegularTextTimeSeriesRow row) throws IOException {
        if(row.getValueUrl() == null) {
            throw new IllegalArgumentException("Value URL is null for this row.");
        }
        String valueUrl = row.getValueUrl();
        return download(ApiConnectionInfoFactory.cloneWithNewUrl(apiConnectionInfo, valueUrl));
    }

    private String download(ApiConnectionInfo apiConnectionInfo) throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo)
                .get()
                .withMediaType("text/plain");

        try (HttpRequestResponse response = executor.execute();
             InputStream inputStream = response.getStream()) {
            return readFully(inputStream);
        }
    }

    // Method to read input stream fully into a String
    private String readFully(InputStream inputStream) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder stringBuilder = new StringBuilder();
            char[] buffer = new char[BUFFER_SIZE];
            int numCharsRead;
            while ((numCharsRead = bufferedReader.read(buffer)) != -1) {
                stringBuilder.append(buffer, 0, numCharsRead);
            }
            return stringBuilder.toString();
        }
    }
}
