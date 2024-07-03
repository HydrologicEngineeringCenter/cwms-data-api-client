package mil.army.usace.hec.cwms.radar.client.controllers;

import java.io.IOException;

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.Project;
import mil.army.usace.hec.cwms.radar.client.model.Projects;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

public final class ProjectController {
    private static final String PROJECT_ENDPOINT = "projects";

    public Project retrieveProject(ApiConnectionInfo apiConnectionInfo, ProjectEndpointInput.GetOne input)
            throws IOException {
        String endpoint = PROJECT_ENDPOINT + "/" + input.projectId();
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), Project.class);
        }
    }

    public Projects retrieveProjects(ApiConnectionInfo apiConnectionInfo, ProjectEndpointInput.GetAll input)
            throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, PROJECT_ENDPOINT)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), Projects.class);
        }
    }

    public void storeProject(ApiConnectionInfo apiConnectionInfo, ProjectEndpointInput.Post input) throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.project());
        new HttpRequestBuilderImpl(apiConnectionInfo, PROJECT_ENDPOINT)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
                .addEndpointInput(input)
                .post()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_V2)
                .execute()
                .close();
    }

    public void updateProject(ApiConnectionInfo apiConnectionInfo, ProjectEndpointInput.Patch input) throws IOException {
        Project project = input.project();
        String body = RadarObjectMapper.mapObjectToJson(project);
        String endpoint = PROJECT_ENDPOINT + "/" + project.getLocation().getName();
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
                .addEndpointInput(input)
                .patch()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_V2)
                .execute()
                .close();
    }

    public void deleteProject(ApiConnectionInfo apiConnectionInfo, ProjectEndpointInput.Delete input) throws IOException {
        String endpoint = PROJECT_ENDPOINT + "/" + input.projectId();
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
                .addEndpointInput(input)
                .delete()
                .withMediaType(ACCEPT_HEADER_V2)
                .execute()
                .close();
    }


}
