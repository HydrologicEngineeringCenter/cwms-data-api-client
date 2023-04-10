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

import static mil.army.usace.hec.cwms.radar.client.controllers.ClobEndpointInput.CLOB_ID_MASK_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.ClobEndpointInput.FAIL_IF_EXISTS_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.ClobEndpointInput.IGNORE_NULLS_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.ClobEndpointInput.INCLUDE_VALUES_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.ClobEndpointInput.OFFICE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.ClobEndpointInput.PAGE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.ClobEndpointInput.PAGE_SIZE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import mil.army.usace.hec.cwms.radar.client.model.Clob;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

class TestClobEndpointInput {

    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String clobId = "TEST.CLOB.ID";
        String office = "SWT";
        ClobEndpointInput.GetOne input = ClobEndpointInput.getOne(clobId, office);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(clobId, input.clobId());
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetOneNullClobId() {
        assertThrows(NullPointerException.class, () -> ClobEndpointInput.getOne(null, null));
    }

    @Test
    void testGetOneNullOfficeId() {
        assertThrows(NullPointerException.class, () -> ClobEndpointInput.getOne("", null));
    }

    @Test
    void testGetAllQueryRequestDefaults() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        ClobEndpointInput.GetAll input = ClobEndpointInput.getAll();
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("*", mockHttpRequestBuilder.getQueryParameter(CLOB_ID_MASK_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(PAGE_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(PAGE_SIZE_QUERY_PARAMETER));
        assertFalse(Boolean.parseBoolean(mockHttpRequestBuilder.getQueryParameter(INCLUDE_VALUES_QUERY_PARAMETER)));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetAllQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String clobId = "TEST.CLOB.ID";
        String office = "SWT";
        ClobEndpointInput.GetAll input = ClobEndpointInput.getAll()
            .officeId(office)
            .clobIdMask(clobId)
            .page("page")
            .pageSize(50)
            .includeValues(true);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(clobId, mockHttpRequestBuilder.getQueryParameter(CLOB_ID_MASK_QUERY_PARAMETER));
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals("page", mockHttpRequestBuilder.getQueryParameter(PAGE_QUERY_PARAMETER));
        assertEquals(50, Integer.parseInt(mockHttpRequestBuilder.getQueryParameter(PAGE_SIZE_QUERY_PARAMETER)));
        assertTrue(Boolean.parseBoolean(mockHttpRequestBuilder.getQueryParameter(INCLUDE_VALUES_QUERY_PARAMETER)));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostQueryRequestDefaults() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v2/json/clob.json");
        Clob clob = RadarObjectMapper.mapJsonToObject(collect, Clob.class);
        ClobEndpointInput.Post input = ClobEndpointInput.post(clob);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(clob, input.clob());
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v2/json/clob.json");
        Clob clob = RadarObjectMapper.mapJsonToObject(collect, Clob.class);
        ClobEndpointInput.Post input = ClobEndpointInput.post(clob)
            .failIfExists(false);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostNullClob() {
        assertThrows(NullPointerException.class, () -> ClobEndpointInput.post(null));
    }

    @Test
    void testPatchQueryRequestDefaults() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v2/json/clob.json");
        Clob clob = RadarObjectMapper.mapJsonToObject(collect, Clob.class);
        ClobEndpointInput.Patch input = ClobEndpointInput.patch(clob);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(clob, input.clob());
        assertTrue(Boolean.parseBoolean(mockHttpRequestBuilder.getQueryParameter(IGNORE_NULLS_QUERY_PARAMETER)));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPatchQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v2/json/clob.json");
        Clob clob = RadarObjectMapper.mapJsonToObject(collect, Clob.class);
        ClobEndpointInput.Patch input = ClobEndpointInput.patch(clob)
            .ignoreNulls(false);
        input.addInputParameters(mockHttpRequestBuilder);
        assertFalse(Boolean.parseBoolean(mockHttpRequestBuilder.getQueryParameter(IGNORE_NULLS_QUERY_PARAMETER)));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPatchNullClob() {
        assertThrows(NullPointerException.class, () -> ClobEndpointInput.patch(null));
    }

    @Test
    void testDeleteQueryRequestDefaults() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        ClobEndpointInput.Delete input = ClobEndpointInput.delete("TEST.CLOB.ID", "SWT");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("TEST.CLOB.ID", input.clobId());
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        ClobEndpointInput.Delete input = ClobEndpointInput.delete("TEST.CLOB.ID", "SWT");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNullClobId() {
        assertThrows(NullPointerException.class, () -> ClobEndpointInput.delete(null, null));
    }

    @Test
    void testDeleteNullOffice() {
        assertThrows(NullPointerException.class, () -> ClobEndpointInput.delete("", null));
    }

}
