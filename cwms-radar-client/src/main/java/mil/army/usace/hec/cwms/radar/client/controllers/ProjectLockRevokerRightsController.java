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

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_JSON;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.io.IOException;
import java.util.List;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.LockRevokerRights;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;

public class ProjectLockRevokerRightsController {
    public static final String PATH = "project-lock-rights";

    /**
     * Retrieve the lock revoker rights.
     *
     * @param apiConnectionInfo The API connection information.
     * @param input             The input parameters for retrieving the lock revoker rights.
     * @return A list of LockRevokerRights objects.
     * @throws IOException If an I/O error occurs during the operation.
     */
    public List<LockRevokerRights> retrieveLockRevokerRights(ApiConnectionInfo apiConnectionInfo, ProjectLockRevokerRightsInput.GetAll input)
            throws IOException {

        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, PATH)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON)
                .addEndpointInput(input)
                .get()
            .withMediaType(ACCEPT_HEADER_JSON);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToListOfObjects(response.getBody(), LockRevokerRights.class);
        }
    }

    public void updateLockRevokerRights(ApiConnectionInfo apiConnectionInfo, ProjectLockRevokerRightsInput.Update input)
            throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo, PATH + "/update")
                .addEndpointInput(input)
                .post()
                .withBody("")
            .withMediaType(ACCEPT_HEADER_JSON)
                .execute()
                .close()
                ;
    }

}
