/*
 * MIT License
 *
 * Copyright (c) 2025 Hydrologic Engineering Center
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

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;

import java.io.IOException;
import java.util.List;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.data.api.client.model.VirtualOutlet;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;


public final class VirtualOutletController {
    private static final String ENDPOINT_PREFIX = "projects";
    private static final String MIDPOINT = "virtual-outlets";

    public VirtualOutlet retrieveVirtualOutlet(ApiConnectionInfo apiConnectionInfo,
            VirtualOutletEndpointInput.GetOne input)  throws IOException {
        String endpoint = ENDPOINT_PREFIX + "/" + input.officeId() + "/" + input.projectId() + "/" + MIDPOINT
                + "/" + input.outletName();
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .get();
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), VirtualOutlet.class);
        }
    }

    public List<VirtualOutlet> retrieveVirtualOutlets(ApiConnectionInfo apiConnectionInfo,
            VirtualOutletEndpointInput.GetAll input) throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, ENDPOINT_PREFIX + "/"
                + input.officeId() + "/" + input.projectId() + "/" + MIDPOINT)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .get();
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToListOfObjects(response.getBody(), VirtualOutlet.class);
        }
    }

    public void storeVirtualOutlet(ApiConnectionInfo apiConnectionInfo, VirtualOutletEndpointInput.Post input)
            throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.outletName());
        new HttpRequestBuilderImpl(apiConnectionInfo, ENDPOINT_PREFIX + "/" + MIDPOINT)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .post()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }

    public void renameVirtualOutlet(ApiConnectionInfo apiConnectionInfo, VirtualOutletEndpointInput.Patch input)
            throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo, ENDPOINT_PREFIX + "/" + input.officeId() + "/"
                + input.projectId() + "/" + MIDPOINT + "/" + input.oldOutletName())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .patch()
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }

    public void deleteVirtualOutlet(ApiConnectionInfo apiConnectionInfo, VirtualOutletEndpointInput.Delete input)
            throws IOException {
        String endpoint = ENDPOINT_PREFIX + "/" + input.officeId() + "/" + input.projectId() + "/" + MIDPOINT
                + "/" + input.outletName();
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .delete()
                .execute()
                .close();
    }
}
