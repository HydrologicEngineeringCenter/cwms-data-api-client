package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.io.IOException;
import java.util.List;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.ProjectLock;
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
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToListOfObjects(response.getBody(), ProjectLock.class);
        }
    }
}
