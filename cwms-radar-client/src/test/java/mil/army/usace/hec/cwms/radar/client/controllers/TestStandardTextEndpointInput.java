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

import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.StandardTextValue;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.*;
import static mil.army.usace.hec.cwms.radar.client.controllers.StandardTextEndpointInput.Post.FAIL_IF_EXISTS_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestStandardTextEndpointInput {

    @Test
    void testGetAllQueryRequestDefault() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        StandardTextEndpointInput.GetAll input = StandardTextEndpointInput.getAll();
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String textId = "HW";
        String office = "SPK";
        StandardTextEndpointInput.GetOne input = StandardTextEndpointInput.getOne(textId, office);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetOneNullTimeSeriesId() {
        assertThrows(NullPointerException.class, () -> StandardTextEndpointInput.getOne(null, null));
    }

    @Test
    void testPostQueryRequestDefaults() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v2/json/standard_text.json");
        StandardTextValue textTimeSerietextValue = RadarObjectMapper.mapJsonToObject(collect, StandardTextValue.class);
        StandardTextEndpointInput.Post input = StandardTextEndpointInput.post(textTimeSerietextValue);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(textTimeSerietextValue, input.standardTextValue());
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v2/json/standard_text.json");
        StandardTextValue textTimeSerietextValue = RadarObjectMapper.mapJsonToObject(collect, StandardTextValue.class);
        StandardTextEndpointInput.Post input = StandardTextEndpointInput.post(textTimeSerietextValue)
                .failIfExists(true);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(FAIL_IF_EXISTS_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostNullTimeSeries() {
        assertThrows(NullPointerException.class, () -> StandardTextEndpointInput.post(null));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        StandardTextEndpointInput.Delete input = StandardTextEndpointInput.delete("HW", DeleteMethod.ALL, "SPK");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("HW", input.textId());
        assertEquals("SPK", mockHttpRequestBuilder.getQueryParameter(StandardTextEndpointInput.Delete.OFFICE_QUERY_PARAMETER));
        assertEquals(DeleteMethod.ALL.toString(), mockHttpRequestBuilder.getQueryParameter(StandardTextEndpointInput.Delete.METHOD_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNullTimeSeriesId() {
        assertThrows(NullPointerException.class, () -> StandardTextEndpointInput.delete(null, null, null));
    }

    @Test
    void testDeleteNullOffice() {
        assertThrows(NullPointerException.class, () -> StandardTextEndpointInput.delete("", null, null));
    }

}
