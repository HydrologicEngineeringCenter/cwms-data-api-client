/*
 * MIT License
 *
 * Copyright (c) 2022 Hydrologic Engineering Center
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
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.io.IOException;
import java.util.Set;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.LocationLevel;
import mil.army.usace.hec.cwms.radar.client.model.LocationLevels;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.SpecifiedLevel;

public final class LevelController {

    private static final String SPECIFIED_LEVEL_ENDPOINT = "specified-levels";
    private static final String LOCATION_LEVEL_ENDPOINT = "levels";

    public Set<SpecifiedLevel> retrieveSpecifiedLevels(ApiConnectionInfo apiConnectionInfo, SpecifiedLevelEndpointInput input)
        throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, SPECIFIED_LEVEL_ENDPOINT)
            .addEndpointInput(input)
            .get()
            .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToSetOfObjects(response.getBody(), SpecifiedLevel.class);
        }
    }

    public LocationLevel retrieveLocationLevel(ApiConnectionInfo apiConnectionInfo, LocationLevelEndpointInput.GetOne input)
        throws IOException {
        String endpoint = LOCATION_LEVEL_ENDPOINT + "/" + input.levelId();
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addEndpointInput(input)
            .get()
            .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), LocationLevel.class);
        }
    }

    public LocationLevels retrieveLocationLevels(ApiConnectionInfo apiConnectionInfo, LocationLevelEndpointInput.GetAll input)
        throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, LOCATION_LEVEL_ENDPOINT)
            .addEndpointInput(input)
            .get()
            .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), LocationLevels.class);
        }
    }

    public void storeLevel(ApiConnectionInfo apiConnectionInfo, LocationLevelEndpointInput.Post input) throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.level());
        new HttpRequestBuilderImpl(apiConnectionInfo, LOCATION_LEVEL_ENDPOINT)
            .addEndpointInput(input)
            .post()
            .withBody(body)
            .withMediaType(ACCEPT_HEADER_V1)
            .execute()
            .close();
    }

    public void updateLocationLevel(ApiConnectionInfo apiConnectionInfo, LocationLevelEndpointInput.Patch input) throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.level());
        String endpoint = LOCATION_LEVEL_ENDPOINT + "/" + input.originalLocationLevelId();
        HttpRequestExecutor httpRequestExecutor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addEndpointInput(input)
            .patch()
            .withBody(body)
            .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse httpRequestResponse = httpRequestExecutor.execute()) {
        }
    }

    public void deleteLevel(ApiConnectionInfo apiConnectionInfo, LocationLevelEndpointInput.Delete input) throws IOException {
        String endpoint = LOCATION_LEVEL_ENDPOINT + "/" + input.levelId();
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
            .addEndpointInput(input)
            .delete()
            .withMediaType(ACCEPT_HEADER_V1)
            .execute()
            .close();
    }
}
