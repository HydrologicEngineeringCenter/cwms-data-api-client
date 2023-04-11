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

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.Clob;
import mil.army.usace.hec.cwms.radar.client.model.Clobs;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;

public final class ClobController {

    private static final String CLOB_ENDPOINT = "clobs";

    public Clob retrieveClob(ApiConnectionInfo apiConnectionInfo, ClobEndpointInput.GetOne input)
        throws IOException {
        String endpoint = CLOB_ENDPOINT + "/" + input.clobId();
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addEndpointInput(input)
            .get()
            .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), Clob.class);
        }
    }

    public Clobs retrieveClobs(ApiConnectionInfo apiConnectionInfo, ClobEndpointInput.GetAll input)
        throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, CLOB_ENDPOINT)
            .addEndpointInput(input)
            .get()
            .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), Clobs.class);
        }
    }

    public void updateClob(ApiConnectionInfo apiConnectionInfo, ClobEndpointInput.Patch input) throws IOException {
        Clob clob = input.clob();
        String body = RadarObjectMapper.mapObjectToJson(clob);
        String endpoint = CLOB_ENDPOINT + "/" + clob.getId();
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addEndpointInput(input)
            .patch()
            .withBody(body)
            .withMediaType(ACCEPT_HEADER_V2)
            .execute()
            .close();
    }

    public void storeClob(ApiConnectionInfo apiConnectionInfo, ClobEndpointInput.Post input) throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.clob());
        new HttpRequestBuilderImpl(apiConnectionInfo, CLOB_ENDPOINT)
            .addEndpointInput(input)
            .post()
            .withBody(body)
            .withMediaType(ACCEPT_HEADER_V2)
            .execute()
            .close();
    }

    public void deleteClob(ApiConnectionInfo apiConnectionInfo, ClobEndpointInput.Delete input) throws IOException {
        String endpoint = CLOB_ENDPOINT + "/" + input.clobId();
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addEndpointInput(input)
            .delete()
            .withMediaType(ACCEPT_HEADER_V2)
            .execute()
            .close();
    }

}
