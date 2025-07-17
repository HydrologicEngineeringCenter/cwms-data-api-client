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

package mil.army.usace.hec.cwms.data.api.client.controllers;

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.CwmsHttpResponseException;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.NoDataFoundException;
import mil.army.usace.hec.cwms.http.client.UnauthorizedException;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.data.api.client.model.TimeSeriesIdentifierDescriptor;

public final class TimeSeriesIdentifierController {
    private static final String TIME_SERIES_ENDPOINT = "timeseries/identifier-descriptor";

    /**
     * Retrieves requested timeseries identifier descriptor
     *
     * @param apiConnectionInfo connection details for the service
     * @param input query and path parameter inputs
     * @return the matching time series identifier metadata for the request
     * @throws IOException thrown if there is an error with the web request
     * @throws NoDataFoundException if there is no matching identifier
     * @throws CwmsHttpResponseException if there is a non success HTTP response code returned from the request
     */
    public TimeSeriesIdentifierDescriptor retrieveTimeSeriesIdentifier(ApiConnectionInfo apiConnectionInfo,
                                                                       TimeSeriesIdentifierEndpointInput.GetOne input) throws IOException {
        TimeSeriesIdentifierDescriptor retVal;
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, TIME_SERIES_ENDPOINT + "/" + input.timeSeriesId())
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
            .addEndpointInput(input)
            .get()
            .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            retVal = RadarObjectMapper.mapJsonToObject(response.getBody(), TimeSeriesIdentifierDescriptor.class);
        }
        return retVal;
    }

    /**
     * Create new TimeSeriesIdentifierDescriptor
     *
     * @param apiConnectionInfo connection details for service
     * @param input query and path parameter inputs
     * @throws IOException thrown if there is an error with the web request
     * @throws NoDataFoundException if there is no matching identifier
     * @throws UnauthorizedException if authentication is not provided for the service
     * @throws CwmsHttpResponseException if there is a non success HTTP response code returned from the request.
     * Such as if the stored identifier is invalid.
     */
    public void storeTimeSeriesIdentifier(ApiConnectionInfo apiConnectionInfo, TimeSeriesIdentifierEndpointInput.Post input) throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.timeSeriesIdentifierDescriptor());
        new HttpRequestBuilderImpl(apiConnectionInfo, TIME_SERIES_ENDPOINT)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
            .addEndpointInput(input)
            .post()
            .withBody(body)
            .withMediaType(ACCEPT_HEADER_V2)
            .execute()
            .close();
    }

    /**
     * Update timeseries identifier with new metadata
     *
     * @param apiConnectionInfo connection details for service
     * @param input query and path parameter inputs
     * @throws IOException thrown if there is an error with the web request
     * @throws NoDataFoundException if there is no matching identifier
     * @throws UnauthorizedException if authentication is not provided for the service
     * @throws CwmsHttpResponseException if there is a non success HTTP response code returned from the request.
     * Such as if there is an invalid interval offset or an invalid new timeseries-id
     */
    public void updateTimeSeriesIdentifier(ApiConnectionInfo apiConnectionInfo, TimeSeriesIdentifierEndpointInput.Patch input) throws IOException {
        String endpoint = TIME_SERIES_ENDPOINT + "/" + input.originalIdentifier();
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
            .addEndpointInput(input)
            .patch()
            .withBody("")
            .withMediaType(ACCEPT_HEADER_V2)
            .execute()
            .close();
    }

    /**
     * Deletes requested timeseries identifier
     *
     * @param apiConnectionInfo connection details for service
     * @param input query and path parameter inputs
     * @throws IOException thrown if there is an error with the web request
     * @throws NoDataFoundException if there is no matching identifier
     * @throws UnauthorizedException if authentication is not provided for the service
     * @throws CwmsHttpResponseException if there is a non success HTTP response code returned from the request.
     */
    public void deleteTimeSeriesIdentifier(ApiConnectionInfo apiConnectionInfo, TimeSeriesIdentifierEndpointInput.Delete input) throws IOException {
        String endpoint = TIME_SERIES_ENDPOINT + "/" + input.timeSeriesId();
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
            .addEndpointInput(input)
            .delete()
            .withMediaType(ACCEPT_HEADER_V2)
            .execute()
            .close();
    }
}
