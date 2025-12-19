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
import mil.army.usace.hec.cwms.data.api.client.model.Entity;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;

public final class EntityController {

    private static final String ENTITY_ENDPOINT = "entity";

    public Entity retrieveEntity(ApiConnectionInfo apiConnectionInfo, EntityEndpointInput.GetOne input) throws IOException {
        String endpoint = ENTITY_ENDPOINT + "/" + input.entityId();
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
            .addEndpointInput(input)
            .get();
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), Entity.class);
        }
    }

    public List<Entity> retrieveEntities(ApiConnectionInfo apiConnectionInfo, EntityEndpointInput.GetAll input) throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, ENTITY_ENDPOINT)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
            .addEndpointInput(input)
            .get();
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToListOfObjects(response.getBody(), Entity.class);
        }
    }

    public void storeEntity(ApiConnectionInfo apiConnectionInfo, EntityEndpointInput.Post input) throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.entity());
        new HttpRequestBuilderImpl(apiConnectionInfo, ENTITY_ENDPOINT)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
            .addEndpointInput(input)
            .post()
            .withBody(body)
            .withMediaType(ACCEPT_HEADER_V1)
            .execute()
            .close();
    }

    public void updateEntity(ApiConnectionInfo apiConnectionInfo, EntityEndpointInput.Patch input) throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.entity());
        new HttpRequestBuilderImpl(apiConnectionInfo, ENTITY_ENDPOINT + "/" + input.entity().getId().getName())
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
            .addEndpointInput(input)
            .patch()
            .withBody(body)
            .withMediaType(ACCEPT_HEADER_V1)
            .execute()
            .close();
    }

    public void deleteEntity(ApiConnectionInfo apiConnectionInfo, EntityEndpointInput.Delete input) throws IOException {
        String endpoint = ENTITY_ENDPOINT + "/" + input.entityId();
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
            .addEndpointInput(input)
            .delete()
            .execute()
            .close();
    }
}
