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

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import mil.army.usace.hec.cwms.data.api.client.model.LookupType;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static mil.army.usace.hec.cwms.data.api.client.controllers.LookupTypeEndpointInput.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestLookupTypeEndpointInput {

    @Test
    void testGetAllQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        GetAll input = getAll("SPK", "AT_EMBANK_STRUCTURE_TYPE", "STRUCTURE_TYPE");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
        assertEquals("SPK", mockHttpRequestBuilder.getQueryParameter(GetAll.OFFICE_QUERY_PARAMETER));
        assertEquals("AT_EMBANK_STRUCTURE_TYPE", mockHttpRequestBuilder.getQueryParameter(GetAll.CATEGORY_QUERY_PARAMETER));
        assertEquals("STRUCTURE_TYPE", mockHttpRequestBuilder.getQueryParameter(GetAll.PREFIX_QUERY_PARAMETER));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        LookupType lookupType = RadarObjectMapper.mapJsonToObject(TestController.readJsonFile("radar/v2/json/lookup_type.json"), LookupType.class);
        Post input = post("AT_EMBANK_STRUCTURE_TYPE", "STRUCTURE_TYPE", lookupType);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(lookupType, input.getLookupType());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String officeId = "SPK";
        String category = "AT_EMBANK_STRUCTURE_TYPE";
        String prefix = "STRUCTURE_TYPE";
        String displayValue = "test display value";
        Delete input = delete(officeId, category, prefix, displayValue);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(displayValue, input.getDisplayValue());
        assertEquals(officeId, mockHttpRequestBuilder.getQueryParameter(Delete.OFFICE_QUERY_PARAMETER));
        assertEquals(category, mockHttpRequestBuilder.getQueryParameter(Delete.CATEGORY_QUERY_PARAMETER));
        assertEquals(prefix, mockHttpRequestBuilder.getQueryParameter(Delete.PREFIX_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

}