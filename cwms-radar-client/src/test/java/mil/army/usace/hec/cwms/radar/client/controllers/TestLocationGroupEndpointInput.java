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

import static mil.army.usace.hec.cwms.radar.client.controllers.LocationGroupEndpointInput.CATEGORY_ID_LIKE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.LocationGroupEndpointInput.CATEGORY_ID_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.LocationGroupEndpointInput.GROUP_ID_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.LocationGroupEndpointInput.INCLUDE_ASSIGNED_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.LocationGroupEndpointInput.OFFICE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.LocationGroupEndpointInput.REPLACE_ASSIGNED_LOCS;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_JSON;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import mil.army.usace.hec.cwms.radar.client.model.LocationGroup;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

class TestLocationGroupEndpointInput {

    @Test
    void testQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        LocationGroupEndpointInput.GetOne input = LocationGroupEndpointInput.getOne("CWMS Mobile Location Listings", "Lakes", "SWT");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals("Lakes", mockHttpRequestBuilder.getQueryParameter(GROUP_ID_QUERY_PARAMETER));
        assertEquals("CWMS Mobile Location Listings", mockHttpRequestBuilder.getQueryParameter(CATEGORY_ID_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_JSON, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetAll() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        LocationGroupEndpointInput.GetAll input = LocationGroupEndpointInput.getAll()
                .officeId("SWT")
                .includeAssigned(true)
                .categoryIdMask("Default");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(INCLUDE_ASSIGNED_QUERY_PARAMETER));
        assertEquals("Default", mockHttpRequestBuilder.getQueryParameter(CATEGORY_ID_LIKE_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(CATEGORY_ID_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_JSON, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPost() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v1/json/location_group.json");
        LocationGroup locationGroup = RadarObjectMapper.mapJsonToObject(collect, LocationGroup.class);
        LocationGroupEndpointInput.Post input = LocationGroupEndpointInput.post(locationGroup);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(ACCEPT_HEADER_JSON, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPatch() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v1/json/location_group.json");
        LocationGroup locationGroup = RadarObjectMapper.mapJsonToObject(collect, LocationGroup.class);
        LocationGroupEndpointInput.Patch input = LocationGroupEndpointInput.patch(locationGroup.getId() + "1", locationGroup)
                .replaceAssignedLocs(true);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(REPLACE_ASSIGNED_LOCS));
        assertEquals(ACCEPT_HEADER_JSON, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDelete() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        LocationGroupEndpointInput.Delete input = LocationGroupEndpointInput.delete("CWMS Mobile Location Listings", "Lakes", "SWT");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals("Lakes", mockHttpRequestBuilder.getQueryParameter(GROUP_ID_QUERY_PARAMETER));
        assertEquals("CWMS Mobile Location Listings", mockHttpRequestBuilder.getQueryParameter(CATEGORY_ID_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_JSON, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }
}
