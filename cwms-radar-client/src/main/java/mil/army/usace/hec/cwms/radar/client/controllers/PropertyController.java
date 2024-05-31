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

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.Property;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;

import java.io.IOException;
import java.util.List;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

public final class PropertyController {

    private static final String PROPERTY_ENDPOINT = "properties";

    public List<Property> retrieveProperties(ApiConnectionInfo apiConnectionInfo, PropertyEndpointInput.GetAll input)
            throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, PROPERTY_ENDPOINT)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToListOfObjects(response.getBody(), Property.class);
        }
    }

    public Property retrieveProperty(ApiConnectionInfo apiConnectionInfo, PropertyEndpointInput.GetOne input)
            throws IOException {
        String endpoint = PROPERTY_ENDPOINT + "/" + input.propertyId();
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), Property.class);
        }
    }

    public void storeProperty(ApiConnectionInfo apiConnectionInfo, PropertyEndpointInput.Post input) throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.property());
        new HttpRequestBuilderImpl(apiConnectionInfo, PROPERTY_ENDPOINT)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
                .addEndpointInput(input)
                .post()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_V2)
                .execute()
                .close();
    }

    public void deleteProperty(ApiConnectionInfo apiConnectionInfo, PropertyEndpointInput.Delete input) throws IOException {
        String endpoint = PROPERTY_ENDPOINT + "/" + input.propertyId();
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2)
                .addEndpointInput(input)
                .delete()
                .withMediaType(ACCEPT_HEADER_V2)
                .execute()
                .close();
    }
}
