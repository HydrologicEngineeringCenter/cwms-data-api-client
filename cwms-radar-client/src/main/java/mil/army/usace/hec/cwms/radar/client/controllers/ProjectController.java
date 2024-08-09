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
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.Project;
import mil.army.usace.hec.cwms.radar.client.model.ProjectChildLocations;
import mil.army.usace.hec.cwms.radar.client.model.Projects;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;


public final class ProjectController {
    private static final String PROJECT_ENDPOINT = "projects";

    public Project retrieveProject(ApiConnectionInfo apiConnectionInfo, ProjectEndpointInput.GetOne input)
            throws IOException {
        String endpoint = PROJECT_ENDPOINT + "/" + input.projectId();
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), Project.class);
        }
    }

    public Projects retrieveProjects(ApiConnectionInfo apiConnectionInfo, ProjectEndpointInput.GetAll input)
            throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, PROJECT_ENDPOINT)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), Projects.class);
        }
    }

    public void storeProject(ApiConnectionInfo apiConnectionInfo, ProjectEndpointInput.Post input) throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.project());
        new HttpRequestBuilderImpl(apiConnectionInfo, PROJECT_ENDPOINT)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .post()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }

    public void updateProject(ApiConnectionInfo apiConnectionInfo, ProjectEndpointInput.Patch input) throws IOException {
        Project project = input.project();
        String body = RadarObjectMapper.mapObjectToJson(project);
        String endpoint = PROJECT_ENDPOINT + "/" + project.getLocation().getName();
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .patch()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }

    public void deleteProject(ApiConnectionInfo apiConnectionInfo, ProjectEndpointInput.Delete input) throws IOException {
        String endpoint = PROJECT_ENDPOINT + "/" + input.projectId();
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .delete()
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }

    public ProjectChildLocations getProjectChildLocations(ApiConnectionInfo apiConnectionInfo,
        ProjectEndpointInput.ProjectChildLocations input) throws IOException {
        String endpoint = PROJECT_ENDPOINT + "/locations";
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
            .addEndpointInput(input)
            .get()
            .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), ProjectChildLocations.class);
        }
    }

    public void publishStatusUpdate(ApiConnectionInfo apiConnectionInfo,
        ProjectEndpointInput.StatusUpdate input) throws IOException {
        String endpoint = PROJECT_ENDPOINT + "/status-update/" + input.projectId();
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
            .addEndpointInput(input)
            .post()
            .withBody("")
            .withMediaType(ACCEPT_HEADER_V1)
            .execute()
            .close();
    }

}
