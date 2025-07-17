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

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TestController.readJsonFile;
import mil.army.usace.hec.cwms.data.api.client.model.ForecastInstance;
import mil.army.usace.hec.cwms.data.api.client.model.ForecastSpec;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

final class TestForecastInstanceEndpointInput {
    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        Instant forecastDate = ZonedDateTime.of(2018, 1, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant issueDate = ZonedDateTime.of(2018, 2, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        ForecastSpec spec = new ForecastSpec()
                .specId("test-spec")
                .officeId("SWT")
                .designator("designator");
        ForecastInstanceEndpointInput.GetOne input = ForecastInstanceEndpointInput.getOne(spec, forecastDate, issueDate);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(ForecastInstanceEndpointInput.GetOne.OFFICE_QUERY_PARAMETER));
        assertEquals("test-spec", mockHttpRequestBuilder.getQueryParameter(ForecastInstanceEndpointInput.GetOne.NAME_QUERY_PARAMETER));
        assertEquals("designator", mockHttpRequestBuilder.getQueryParameter(ForecastInstanceEndpointInput.GetOne.DESIGNATOR_QUERY_PARAMETER));
        assertEquals(forecastDate.toString(), mockHttpRequestBuilder.getQueryParameter(ForecastInstanceEndpointInput.GetOne.FORECAST_DATE_PARAMETER_QUERY));
        assertEquals(issueDate.toString(), mockHttpRequestBuilder.getQueryParameter(ForecastInstanceEndpointInput.GetOne.ISSUE_DATE_PARAMETER_QUERY));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetAllQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        ForecastInstanceEndpointInput.GetAll input = ForecastInstanceEndpointInput.getAll()
                .officeId("SWT")
                .specId("test-spec")
                .designator("designator");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(ForecastInstanceEndpointInput.GetAll.OFFICE_QUERY_PARAMETER));
        assertEquals("test-spec", mockHttpRequestBuilder.getQueryParameter(ForecastInstanceEndpointInput.GetAll.NAME_QUERY_PARAMETER));
        assertEquals("designator", mockHttpRequestBuilder.getQueryParameter(ForecastInstanceEndpointInput.GetAll.DESIGNATOR_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetAllQueryRequestDefaults() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        ForecastInstanceEndpointInput.GetAll input = ForecastInstanceEndpointInput.getAll();
        input.addInputParameters(mockHttpRequestBuilder);
        assertNull(mockHttpRequestBuilder.getQueryParameter(ForecastInstanceEndpointInput.GetAll.OFFICE_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(ForecastInstanceEndpointInput.GetAll.NAME_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(ForecastInstanceEndpointInput.GetAll.DESIGNATOR_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testNullGetOneRequiredFields() {
        ForecastSpec spec = new ForecastSpec()
                .specId("test-spec")
                .officeId("SWT")
                .designator("designator");
        ForecastSpec specMissingSpecId = new ForecastSpec()
                .officeId("SWT")
                .designator("designator");
        ForecastSpec specMissingOfficeId = new ForecastSpec()
                .specId("test-spec")
                .designator("designator");
        ForecastSpec specMissingDesignator = new ForecastSpec()
                .specId("test-spec")
                .officeId("SWT");
        assertThrows(NullPointerException.class, () -> ForecastInstanceEndpointInput.getOne(null, Instant.now(), Instant.now()));
        assertThrows(NullPointerException.class, () -> ForecastInstanceEndpointInput.getOne(specMissingSpecId, Instant.now(), Instant.now()));
        assertThrows(NullPointerException.class, () -> ForecastInstanceEndpointInput.getOne(specMissingOfficeId, Instant.now(), Instant.now()));
        assertThrows(NullPointerException.class, () -> ForecastInstanceEndpointInput.getOne(specMissingDesignator, Instant.now(), Instant.now()));
        assertThrows(NullPointerException.class, () -> ForecastInstanceEndpointInput.getOne(spec, null, Instant.now()));
        assertThrows(NullPointerException.class, () -> ForecastInstanceEndpointInput.getOne(spec, Instant.now(), null));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v2/json/forecast_instance.json");
        ForecastInstance forecastSpec = RadarObjectMapper.mapJsonToObject(collect, ForecastInstance.class);
        ForecastInstanceEndpointInput.Post input = ForecastInstanceEndpointInput.post(forecastSpec);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(forecastSpec, input.forecastInstance());
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testNullPostRequiredFields() {
        assertThrows(NullPointerException.class, () -> ForecastInstanceEndpointInput.post(null));
    }

    @Test
    void testPatchQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v2/json/forecast_instance.json");
        ForecastInstance forecastInstance = RadarObjectMapper.mapJsonToObject(collect, ForecastInstance.class);
        ForecastInstanceEndpointInput.Patch input = ForecastInstanceEndpointInput.patch("original-id", forecastInstance);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("original-id", mockHttpRequestBuilder.getQueryParameter(ForecastInstanceEndpointInput.Patch.NAME_PARAMETER_QUERY));
        assertEquals(forecastInstance, input.forecastInstance());
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testNullPatchRequiredFields() throws IOException {
        String collect = readJsonFile("radar/v2/json/forecast_instance.json");
        ForecastInstance forecastInstance = RadarObjectMapper.mapJsonToObject(collect, ForecastInstance.class);
        assertThrows(NullPointerException.class, () -> ForecastInstanceEndpointInput.patch(null, forecastInstance));
        assertThrows(NullPointerException.class, () -> ForecastInstanceEndpointInput.patch("original-id", null));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        Instant forecastDate = ZonedDateTime.of(2018, 1, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant issueDate = ZonedDateTime.of(2018, 2, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        ForecastSpec spec = new ForecastSpec()
                .specId("spec-id")
                .officeId("SWT")
                .designator("designator");
        ForecastInstanceEndpointInput.Delete input = ForecastInstanceEndpointInput.delete(spec, forecastDate, issueDate);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("spec-id", mockHttpRequestBuilder.getQueryParameter(ForecastInstanceEndpointInput.Delete.NAME_PARAMETER_QUERY));
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(ForecastInstanceEndpointInput.Delete.OFFICE_QUERY_PARAMETER));
        assertEquals("designator", mockHttpRequestBuilder.getQueryParameter(ForecastInstanceEndpointInput.Delete.DESIGNATOR_PARAMETER_QUERY));
        assertEquals(forecastDate.toString(), mockHttpRequestBuilder.getQueryParameter(ForecastInstanceEndpointInput.Delete.FORECAST_DATE_PARAMETER_QUERY));
        assertEquals(issueDate.toString(), mockHttpRequestBuilder.getQueryParameter(ForecastInstanceEndpointInput.Delete.ISSUE_DATE_PARAMETER_QUERY));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNullRequiredFields() {
        Instant forecastDate = ZonedDateTime.of(2018, 1, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        Instant issueDate = ZonedDateTime.of(2018, 2, 5, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant();
        ForecastSpec spec = new ForecastSpec()
                .specId("test-spec")
                .officeId("SWT")
                .designator("designator");
        ForecastSpec specMissingSpecId = new ForecastSpec()
                .officeId("SWT")
                .designator("designator");
        ForecastSpec specMissingOfficeId = new ForecastSpec()
                .specId("test-spec")
                .designator("designator");
        ForecastSpec specMissingDesignator = new ForecastSpec()
                .specId("test-spec")
                .officeId("SWT");
        assertThrows(NullPointerException.class, () -> ForecastInstanceEndpointInput.delete(null, forecastDate, issueDate));
        assertThrows(NullPointerException.class, () -> ForecastInstanceEndpointInput.delete(specMissingSpecId, forecastDate, issueDate));
        assertThrows(NullPointerException.class, () -> ForecastInstanceEndpointInput.delete(specMissingOfficeId, forecastDate, issueDate));
        assertThrows(NullPointerException.class, () -> ForecastInstanceEndpointInput.delete(specMissingDesignator, forecastDate, issueDate));
        assertThrows(NullPointerException.class, () -> ForecastInstanceEndpointInput.delete(spec, null, issueDate));
        assertThrows(NullPointerException.class, () -> ForecastInstanceEndpointInput.delete(spec, forecastDate, null));
    }
}
