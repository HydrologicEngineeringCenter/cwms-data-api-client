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
import java.util.List;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.data.api.client.model.Turbine;

public final class TurbineController {

    private static final String TURBINE_ENDPOINT = "projects/turbines";

    public Turbine retrieveTurbine(ApiConnectionInfo apiConnectionInfo, TurbineEndpointInput.GetOne input)
        throws IOException {
        String endpoint = TURBINE_ENDPOINT + "/" + input.turbineId();
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
            .addEndpointInput(input)
            .get()
            .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), Turbine.class);
        }
    }

    public List<Turbine> retrieveTurbines(ApiConnectionInfo apiConnectionInfo, TurbineEndpointInput.GetAll input)
        throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, TURBINE_ENDPOINT)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
            .addEndpointInput(input)
            .get()
            .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToListOfObjects(response.getBody(), Turbine.class);
        }
    }

    public void storeTurbine(ApiConnectionInfo apiConnectionInfo, TurbineEndpointInput.Post input)
        throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.turbine());
        new HttpRequestBuilderImpl(apiConnectionInfo, TURBINE_ENDPOINT)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
            .addEndpointInput(input)
            .post()
            .withBody(body)
            .withMediaType(ACCEPT_HEADER_V1)
            .execute()
            .close();
    }

    public void renameTurbine(ApiConnectionInfo apiConnectionInfo, TurbineEndpointInput.Patch input)
        throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo, TURBINE_ENDPOINT + "/" + input.turbineId())
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
            .addEndpointInput(input)
            .patch()
            .withMediaType(ACCEPT_HEADER_V1)
            .execute()
            .close();
    }

    public void deleteTurbine(ApiConnectionInfo apiConnectionInfo, TurbineEndpointInput.Delete input)
        throws IOException {
        String endpoint = TURBINE_ENDPOINT + "/" + input.turbineId();
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
            .addEndpointInput(input)
            .delete()
            .withMediaType(ACCEPT_HEADER_V1)
            .execute()
            .close();
    }
}
