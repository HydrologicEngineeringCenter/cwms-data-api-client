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

package mil.army.usace.hec.cwms.data.api.client.controllers;

import mil.army.usace.hec.cwms.data.api.client.model.Location;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

import static mil.army.usace.hec.cwms.data.api.client.controllers.LocationEndPointInput.*;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.*;


class TestLocationEndpointInput {

    @Test
    void testQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        LocationEndPointInput.GetOne input = LocationEndPointInput.getOne("LOC_TEST")
                .officeId("SWT")
                .unit("SI");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals("SI", mockHttpRequestBuilder.getQueryParameter(UNIT_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testQueryRequestDefaults() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        LocationEndPointInput.GetOne input = LocationEndPointInput.getOne("LOC_TEST");
        input.addInputParameters(mockHttpRequestBuilder);
        assertNull(mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals("SI", mockHttpRequestBuilder.getQueryParameter(UNIT_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testLocationName() {
        LocationEndPointInput.GetOne input = LocationEndPointInput.getOne("TEST");
        assertEquals("TEST", input.locationId());
    }

    @Test
    void testNullLocationName() {
        assertThrows(NullPointerException.class, () -> LocationEndPointInput.getOne(null));
    }

    @Test
    void testPost() throws Exception {
        String json = readJsonFile("radar/v2/json/location.json");
        Location location = RadarObjectMapper.mapJsonToObject(json, Location.class);
        LocationEndPointInput.Post input = LocationEndPointInput.post(location);
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPatch() throws Exception {
        String json = readJsonFile("radar/v2/json/location.json");
        Location location = RadarObjectMapper.mapJsonToObject(json, Location.class);
        LocationEndPointInput.Patch input = LocationEndPointInput.patch(location.getName(), location);
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDelete() throws Exception {
        LocationEndPointInput.Delete input = LocationEndPointInput.delete("id")
                .cascadeDelete(true);
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(CASCADE_DELETE_QUERY_PARAMETER));
    }

}
