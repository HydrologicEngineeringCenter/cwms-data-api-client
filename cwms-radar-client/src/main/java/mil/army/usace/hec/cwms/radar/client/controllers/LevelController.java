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

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.LocationLevel;
import mil.army.usace.hec.cwms.radar.client.model.LocationLevels;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.SpecifiedLevel;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeries;

import java.io.IOException;
import java.util.Set;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;

public final class LevelController {

    private static final String SPECIFIED_LEVEL_ENDPOINT = "specified-levels";
    private static final String LOCATION_LEVEL_ENDPOINT = "levels";

    public Set<SpecifiedLevel> retrieveSpecifiedLevels(ApiConnectionInfo apiConnectionInfo, SpecifiedLevelEndpointInput.GetAll input)
            throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, SPECIFIED_LEVEL_ENDPOINT)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToSetOfObjects(response.getBody(), SpecifiedLevel.class);
        }
    }

    public void storeSpecifiedLevel(ApiConnectionInfo apiConnectionInfo, SpecifiedLevelEndpointInput.Post input)
            throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.specifiedLevel());
        new HttpRequestBuilderImpl(apiConnectionInfo, SPECIFIED_LEVEL_ENDPOINT)
                .addEndpointInput(input)
                .post()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_V2)
                .execute()
                .close();
    }

    public void updateSpecifiedLevel(ApiConnectionInfo apiConnectionInfo, SpecifiedLevelEndpointInput.Patch input)
            throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo, SPECIFIED_LEVEL_ENDPOINT + "/" + input.originalId())
                .addEndpointInput(input)
                .patch()
                .withBody("")
                .withMediaType(ACCEPT_HEADER_V2)
                .execute()
                .close();
    }

    public void deleteSpecifiedLevel(ApiConnectionInfo apiConnectionInfo, SpecifiedLevelEndpointInput.Delete input)
            throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo, SPECIFIED_LEVEL_ENDPOINT + "/" + input.specifiedLevel())
                .addEndpointInput(input)
                .delete()
                .withMediaType(ACCEPT_HEADER_V2)
                .execute()
                .close();
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

    public void deleteLevel(ApiConnectionInfo apiConnectionInfo, LocationLevelEndpointInput.Delete input) throws IOException {
        String endpoint = LOCATION_LEVEL_ENDPOINT + "/" + input.levelId();
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addEndpointInput(input)
                .delete()
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }

    public TimeSeries retrieveLevelAsTimeSeries(ApiConnectionInfo apiConnectionInfo, LocationLevelEndpointInput.GetTimeSeries input)
            throws IOException {
        String endpoint = LOCATION_LEVEL_ENDPOINT + "/" + input.levelId() + "/timeseries";
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), TimeSeries.class);
        }
    }

}
