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

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;

import java.io.IOException;
import java.util.List;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.ProjectLock;
import mil.army.usace.hec.cwms.radar.client.model.ProjectLockId;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;

public final class ProjectLockController {
    public static final String PATH = "/project-locks";

    /**
     * Retrieve the project locks.
     *
     * @param apiConnectionInfo The API connection information.
     * @param input             The input parameters for retrieving the project locks.
     * @return A list of ProjectLock objects.
     * @throws IOException If an I/O error occurs during the operation.
     */
    public List<ProjectLock> retrieveProjectLocks(ApiConnectionInfo apiConnectionInfo, ProjectLockInput.GetAll input)
            throws IOException {

        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, PATH)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToListOfObjects(response.getBody(), ProjectLock.class);
        }
    }

    /**
     * Retrieves a ProjectLock object based on the provided API connection information and input parameters.
     *
     * @param apiConnectionInfo The API connection information.
     * @param input             The input parameters for retrieving the project lock.
     * @return The retrieved ProjectLock object.
     * @throws IOException If an I/O error occurs during the operation.
     */
    public ProjectLock retrieveProjectLock(ApiConnectionInfo apiConnectionInfo, ProjectLockInput.GetOne input)
            throws IOException {
        String endpoint = PATH + "/" + input.projectId();
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), ProjectLock.class);
        }
    }

    /**
     * Requests a lock for a project based on the provided API connection information and lock request input.
     *
     * @param apiConnectionInfo The API connection information.
     * @param input             The lock request input parameters.
     * @return The ProjectLockId representing the requested lock.
     * @throws IOException If an I/O error occurs during the operation.
     */
    public ProjectLockId requestLock(ApiConnectionInfo apiConnectionInfo, ProjectLockInput.LockRequest input)
            throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.projectLock());
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, PATH)
                .addEndpointInput(input)
                .post()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), ProjectLockId.class);
        }
    }

    /**
     * Denies a lock revoke request for a specific lock ID.
     *
     * @param apiConnectionInfo The API connection information.
     * @param input             The input parameters containing the lock ID to deny the revoke request for.
     * @throws IOException If an I/O error occurs during the operation.
     */
    public void denyLockRevoke(ApiConnectionInfo apiConnectionInfo, ProjectLockInput.LockRevokeDeny input)
            throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo, PATH + "/deny")
                .addEndpointInput(input)
                .post()
                .withBody("") // There is no body - its just a trigger
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }

    /**
     * Releases a lock for a specific office and lock ID in the API connection.
     *
     * @param apiConnectionInfo The API connection information containing the API root, SSL socket data,
     *                          cookie jar, interceptors, authenticator, cache supplier, and hostname verifier.
     * @param input             The input parameters containing the office ID and lock ID.
     * @throws IOException If an I/O error occurs during the operation.
     */
    public void releaseLock(ApiConnectionInfo apiConnectionInfo, ProjectLockInput.LockRelease input)
            throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo, PATH + "/release")
                .addEndpointInput(input)
                .put()
                .withBody("") // There is no body - its just a trigger
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }

    /**
     * Attempt to Revoke a lock for a specific project in the API connection.
     *
     * @param apiConnectionInfo The API connection information containing the API root, SSL socket data,
     *                         cookie jar, interceptors, authenticator, cache supplier, and hostname verifier.
     * @param input             The input parameters containing the project ID and office ID to revoke the lock for.
     * @throws IOException If an I/O error occurs during the operation.
     */
    public void revokeLock(ApiConnectionInfo apiConnectionInfo, ProjectLockInput.LockRevoke input)
            throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo, PATH + "/" + input.projectId())
                .addEndpointInput(input)
                .delete()
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }
}
