/*
 * MIT License
 *
 * Copyright (c) 2025 Hydrologic Engineering Center
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

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import mil.army.usace.hec.cwms.data.api.client.model.CwmsId;
import mil.army.usace.hec.cwms.data.api.client.model.Entity;
import org.junit.jupiter.api.Test;

class TestEntityEndpointInput {

    @Test
    void testGetAllQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        EntityEndpointInput.GetAll input = EntityEndpointInput.getAll()
            .officeId("SPK")
            .entityId("NWS")
            .parentEntityId("NOAA")
            .categoryId("GOV")
            .longName("National Weather Service")
            .matchNullParents(Boolean.TRUE);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SPK", mockHttpRequestBuilder.getQueryParameter(EntityEndpointInput.GetAll.OFFICE_QUERY_PARAMETER));
        assertEquals("NWS", mockHttpRequestBuilder.getQueryParameter(EntityEndpointInput.GetAll.ENTITY_ID_QUERY_PARAMETER));
        assertEquals("NOAA", mockHttpRequestBuilder.getQueryParameter(EntityEndpointInput.GetAll.PARENT_ENTITY_ID_QUERY_PARAMETER));
        assertEquals("GOV", mockHttpRequestBuilder.getQueryParameter(EntityEndpointInput.GetAll.CATEGORY_ID_QUERY_PARAMETER));
        assertEquals("National Weather Service", mockHttpRequestBuilder.getQueryParameter(EntityEndpointInput.GetAll.LONG_NAME_QUERY_PARAMETER));
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(EntityEndpointInput.GetAll.MATCH_NULL_PARENTS_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        EntityEndpointInput.GetOne input = EntityEndpointInput.getOne("NWS", "SPK");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SPK", mockHttpRequestBuilder.getQueryParameter(EntityEndpointInput.GetOne.OFFICE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetOneNulls() {
        assertThrows(NullPointerException.class, () -> EntityEndpointInput.getOne(null, "SPK"));
        assertThrows(NullPointerException.class, () -> EntityEndpointInput.getOne("NWS", null));
    }

    @Test
    void testPostQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        Entity entity = new Entity().id(new CwmsId().name("NWS").officeId("SPK"))
            .parentEntityId("NOAA").categoryId("GOV").longName("National Weather Service");
        EntityEndpointInput.Post input = EntityEndpointInput.post(entity);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(entity, input.entity());
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostNulls() {
        assertThrows(NullPointerException.class, () -> EntityEndpointInput.post(null));
    }

    @Test
    void testPatch() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        Entity entity = new Entity().id(new CwmsId().name("NWS").officeId("SPK"))
            .parentEntityId("NOAA").categoryId("GOV").longName("National Weather Service");
        EntityEndpointInput.Patch input = EntityEndpointInput.patch(entity);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(entity, input.entity());
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPatchNulls() {
        assertThrows(NullPointerException.class, () -> EntityEndpointInput.patch(null));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        EntityEndpointInput.Delete input = EntityEndpointInput.delete("NWS", "SPK", true);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SPK", mockHttpRequestBuilder.getQueryParameter(EntityEndpointInput.Delete.OFFICE_QUERY_PARAMETER));
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(EntityEndpointInput.Delete.CASCADE_DELETE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNulls() {
        assertThrows(NullPointerException.class, () -> EntityEndpointInput.delete(null, "SPK", true));
        assertThrows(NullPointerException.class, () -> EntityEndpointInput.delete("NWS", null, true));
    }
}
