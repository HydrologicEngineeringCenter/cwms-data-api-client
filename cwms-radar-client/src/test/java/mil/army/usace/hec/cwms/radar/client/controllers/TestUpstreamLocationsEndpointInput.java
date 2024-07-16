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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TestUpstreamLocationsEndpointInput {

    @Test
    void testGetAllQueryRequestDefault() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        UpstreamLocationsEndpointInput.GetAll input = UpstreamLocationsEndpointInput.getAll("SPK", "LOC123");
        input.addInputParameters(mockHttpRequestBuilder);
        assertNull(mockHttpRequestBuilder.getQueryParameter(UpstreamLocationsEndpointInput.GetAll.ALL_UPSTREAM_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(UpstreamLocationsEndpointInput.GetAll.SAME_STREAM_ONLY_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(UpstreamLocationsEndpointInput.GetAll.STATION_UNIT_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(UpstreamLocationsEndpointInput.GetAll.STAGE_UNIT_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(UpstreamLocationsEndpointInput.GetAll.AREA_UNIT_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetAllQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        UpstreamLocationsEndpointInput.GetAll input = UpstreamLocationsEndpointInput.getAll("SPK", "LOC123")
                .allUpstream(true)
                .sameStreamOnly(false)
                .stationUnits("km")
                .stageUnits("m")
                .areaUnits("km2");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("LOC123", input.locationId());
        assertEquals("SPK", input.officeId());
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(UpstreamLocationsEndpointInput.GetAll.ALL_UPSTREAM_QUERY_PARAMETER));
        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(UpstreamLocationsEndpointInput.GetAll.SAME_STREAM_ONLY_QUERY_PARAMETER));
        assertEquals("km", mockHttpRequestBuilder.getQueryParameter(UpstreamLocationsEndpointInput.GetAll.STATION_UNIT_QUERY_PARAMETER));
        assertEquals("m", mockHttpRequestBuilder.getQueryParameter(UpstreamLocationsEndpointInput.GetAll.STAGE_UNIT_QUERY_PARAMETER));
        assertEquals("km2", mockHttpRequestBuilder.getQueryParameter(UpstreamLocationsEndpointInput.GetAll.AREA_UNIT_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetAllNulls() {
        assertThrows(NullPointerException.class, () -> UpstreamLocationsEndpointInput.getAll(null, null));
        assertThrows(NullPointerException.class, () -> UpstreamLocationsEndpointInput.getAll("SPK", null));
    }
}
