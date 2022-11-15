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

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;

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

    public Set<SpecifiedLevel> retrieveSpecifiedLevels(ApiConnectionInfo apiConnectionInfo, SpecifiedLevelEndpointInput endpointInput)
        throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, SPECIFIED_LEVEL_ENDPOINT)
            .addEndpointInput(endpointInput)
            .get()
            .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToSetOfObjects(response.getBody(), SpecifiedLevel.class);
        }
    }

    public LocationLevels retrieveLocationLevels(ApiConnectionInfo apiConnectionInfo, LocationLevelEndpointInput endpointInput)
        throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, LOCATION_LEVEL_ENDPOINT)
            .addEndpointInput(endpointInput)
            .get()
            .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), LocationLevels.class);
        }
    }

}
