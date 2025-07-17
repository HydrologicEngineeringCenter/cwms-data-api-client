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

import static java.lang.String.format;

import java.io.IOException;
import java.util.List;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.data.api.client.model.TurbineChange;

public final class TurbineChangeController {

    private static final String TURBINE_ENDPOINT = "projects/%s/%s/turbine-changes";

    public List<TurbineChange> retrieveTurbineChanges(ApiConnectionInfo apiConnectionInfo,
        TurbineChangeEndpointInput.GetAll input)
        throws IOException {
        String endpoint = format(TURBINE_ENDPOINT, input.officeId(), input.projectId());
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
            .addEndpointInput(input)
            .get()
            .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToListOfObjects(response.getBody(), TurbineChange.class);
        }
    }

    public void storeTurbineChanges(ApiConnectionInfo apiConnectionInfo, TurbineChangeEndpointInput.Post input)
        throws IOException {
        String endpoint = format(TURBINE_ENDPOINT, input.officeId(), input.projectId());
        String body = RadarObjectMapper.mapObjectToJson(input.changes());
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
            .addEndpointInput(input)
            .post()
            .withBody(body)
            .withMediaType(ACCEPT_HEADER_V1)
            .execute()
            .close();
    }

    public void deleteTurbineChanges(ApiConnectionInfo apiConnectionInfo, TurbineChangeEndpointInput.Delete input)
        throws IOException {
        String endpoint = format(TURBINE_ENDPOINT, input.officeId(), input.projectId());
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
            .addEndpointInput(input)
            .delete()
            .withMediaType(ACCEPT_HEADER_V1)
            .execute()
            .close();
    }
}
