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

import mil.army.usace.hec.cwms.radar.client.model.Property;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.*;
import static mil.army.usace.hec.cwms.radar.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestPropertyEndpointInput {

    @Test
    void testGetAllQueryRequestDefault() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        PropertyEndpointInput.GetAll input = PropertyEndpointInput.getAll();
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetAllQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        PropertyEndpointInput.GetAll input = PropertyEndpointInput.getAll()
                .categoryIdMask("CAT")
                .officeIdMask("SPK")
                .propertyIdMask("PROP");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
        assertEquals("SPK", mockHttpRequestBuilder.getQueryParameter(PropertyEndpointInput.GetAll.OFFICE_ID_MASK_PARAMETER));
        assertEquals("CAT", mockHttpRequestBuilder.getQueryParameter(PropertyEndpointInput.GetAll.CATEGORY_ID_MASK_PARAMETER));
        assertEquals("PROP", mockHttpRequestBuilder.getQueryParameter(PropertyEndpointInput.GetAll.NAME_MASK_PARAMETER));
    }

    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String categoryId = "mockCategory";
        String propertyId = "HW";
        String office = "SPK";
        PropertyEndpointInput.GetOne input = PropertyEndpointInput.getOne(categoryId, propertyId, office);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(PropertyEndpointInput.GetOne.OFFICE_QUERY_PARAMETER));
        assertEquals(categoryId, mockHttpRequestBuilder.getQueryParameter(PropertyEndpointInput.GetOne.CATEGORY_ID_QUERY_PARAMETER));
    }

    @Test
    void testGetOneNullTimeSeriesId() {
        assertThrows(NullPointerException.class, () -> PropertyEndpointInput.getOne(null, null, null));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v2/json/property.json");
        Property property = RadarObjectMapper.mapJsonToObject(collect, Property.class);
        PropertyEndpointInput.Post input = PropertyEndpointInput.post(property);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(property, input.property());
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostNullTimeSeries() {
        assertThrows(NullPointerException.class, () -> PropertyEndpointInput.post(null));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String categoryId = "mockCategory";
        String propertyId = "HW";
        String office = "SPK";
        PropertyEndpointInput.Delete input = PropertyEndpointInput.delete(categoryId, propertyId, office);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(propertyId, input.propertyId());
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(PropertyEndpointInput.Delete.OFFICE_QUERY_PARAMETER));
        assertEquals(categoryId, mockHttpRequestBuilder.getQueryParameter(PropertyEndpointInput.Delete.CATEGORY_ID_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNullTimeSeriesId() {
        assertThrows(NullPointerException.class, () -> PropertyEndpointInput.delete(null, null, null));
    }

    @Test
    void testDeleteNullOffice() {
        assertThrows(NullPointerException.class, () -> PropertyEndpointInput.delete("", null, null));
    }

}
