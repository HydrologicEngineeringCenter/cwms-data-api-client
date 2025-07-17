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
package mil.army.usace.hec.cwms.data.api.client.controllers;

import mil.army.usace.hec.cwms.data.api.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.data.api.client.model.ForecastSpec;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.*;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.*;

final class TestForecastSpecEndpointInput {
    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        ForecastSpecEndpointInput.GetOne input = ForecastSpecEndpointInput.getOne("SWT", "test-spec", "designator");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(ForecastSpecEndpointInput.GetOne.OFFICE_QUERY_PARAMETER));
        assertEquals("test-spec", mockHttpRequestBuilder.getQueryParameter(ForecastSpecEndpointInput.GetOne.NAME_QUERY_PARAMETER));
        assertEquals("designator", mockHttpRequestBuilder.getQueryParameter(ForecastSpecEndpointInput.GetOne.DESIGNATOR_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetAllQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        ForecastSpecEndpointInput.GetAll input = ForecastSpecEndpointInput.getAll()
                .officeId("SWT")
                .specIdMask("test-spec")
                .designatorMask("designator")
                .sourceEntityId("source-entity")
                .locationIdMask("location");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(ForecastSpecEndpointInput.GetAll.OFFICE_QUERY_PARAMETER));
        assertEquals("test-spec", mockHttpRequestBuilder.getQueryParameter(ForecastSpecEndpointInput.GetAll.ID_MASK_QUERY_PARAMETER));
        assertEquals("designator", mockHttpRequestBuilder.getQueryParameter(ForecastSpecEndpointInput.GetAll.DESIGNATOR_MASK_QUERY_PARAMETER));
        assertEquals("source-entity", mockHttpRequestBuilder.getQueryParameter(ForecastSpecEndpointInput.GetAll.SOURCE_ENTITY_QUERY_PARAMETER));
        assertEquals("location", mockHttpRequestBuilder.getQueryParameter(ForecastSpecEndpointInput.GetAll.LOCATION_MASK_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetAllQueryRequestDefaults() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        ForecastSpecEndpointInput.GetAll input = ForecastSpecEndpointInput.getAll();
        input.addInputParameters(mockHttpRequestBuilder);
        assertNull(mockHttpRequestBuilder.getQueryParameter(ForecastSpecEndpointInput.GetAll.OFFICE_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(ForecastSpecEndpointInput.GetAll.ID_MASK_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(ForecastSpecEndpointInput.GetAll.DESIGNATOR_MASK_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(ForecastSpecEndpointInput.GetAll.SOURCE_ENTITY_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(ForecastSpecEndpointInput.GetAll.LOCATION_MASK_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testNullGetOneRequiredFields() {
        assertThrows(NullPointerException.class, () -> ForecastSpecEndpointInput.getOne(null, "test-spec", "designator"));
        assertThrows(NullPointerException.class, () -> ForecastSpecEndpointInput.getOne("SWT", null, "designator"));
        assertThrows(NullPointerException.class, () -> ForecastSpecEndpointInput.getOne("SWT", "test-spec", null));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v2/json/forecast_spec.json");
        ForecastSpec forecastSpec = RadarObjectMapper.mapJsonToObject(collect, ForecastSpec.class);
        ForecastSpecEndpointInput.Post input = ForecastSpecEndpointInput.post(forecastSpec);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(forecastSpec, input.forecastSpec());
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testNullPostRequiredFields() {
        assertThrows(NullPointerException.class, () -> ForecastSpecEndpointInput.post(null));
    }

    @Test
    void testPatchQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v2/json/forecast_spec.json");
        ForecastSpec forecastSpec = RadarObjectMapper.mapJsonToObject(collect, ForecastSpec.class);
        ForecastSpecEndpointInput.Patch input = ForecastSpecEndpointInput.patch("original-id", forecastSpec);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("original-id", mockHttpRequestBuilder.getQueryParameter(ForecastSpecEndpointInput.Patch.NAME_PARAMETER_QUERY));
        assertEquals(forecastSpec, input.forecastSpec());
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testNullPatchRequiredFields() throws IOException {
        String collect = readJsonFile("radar/v2/json/forecast_spec.json");
        ForecastSpec forecastSpec = RadarObjectMapper.mapJsonToObject(collect, ForecastSpec.class);
        assertThrows(NullPointerException.class, () -> ForecastSpecEndpointInput.patch(null, forecastSpec));
        assertThrows(NullPointerException.class, () -> ForecastSpecEndpointInput.patch("original-id", null));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        ForecastSpecEndpointInput.Delete input = ForecastSpecEndpointInput.delete("SWT", "spec-id", "designator")
                .deleteMethod(DeleteMethod.ALL);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("spec-id", mockHttpRequestBuilder.getQueryParameter(ForecastSpecEndpointInput.Delete.NAME_PARAMETER_QUERY));
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(ForecastSpecEndpointInput.Delete.OFFICE_QUERY_PARAMETER));
        assertEquals("designator", mockHttpRequestBuilder.getQueryParameter(ForecastSpecEndpointInput.Delete.DESIGNATOR_PARAMETER_QUERY));
        assertEquals("DELETE_ALL", mockHttpRequestBuilder.getQueryParameter(ForecastSpecEndpointInput.Delete.DELETE_METHOD_PARAMETER_QUERY));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNullRequiredFields() {
        assertThrows(NullPointerException.class, () -> ForecastSpecEndpointInput.delete(null, "spec-id", "designator"));
        assertThrows(NullPointerException.class, () -> ForecastSpecEndpointInput.delete("SWT", null, "designator"));
        assertThrows(NullPointerException.class, () -> ForecastSpecEndpointInput.delete("SWT", "spec-id", null));
    }

}
