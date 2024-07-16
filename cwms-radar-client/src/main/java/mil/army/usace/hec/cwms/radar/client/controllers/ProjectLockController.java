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

public class ProjectLockController {
    public static final String PATH = "/project-locks/";

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
}
