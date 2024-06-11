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

import mil.army.usace.hec.cwms.radar.client.controllers.EmbankmentEndpointInput.Delete;
import mil.army.usace.hec.cwms.radar.client.controllers.EmbankmentEndpointInput.GetAll;
import mil.army.usace.hec.cwms.radar.client.controllers.EmbankmentEndpointInput.Patch;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.Embankment;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static mil.army.usace.hec.cwms.radar.client.controllers.EmbankmentEndpointInput.Post.FAIL_IF_EXISTS_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestEmbankmentEndpointInput {

    @Test
    void testGetAllQueryRequestDefault() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        GetAll input = EmbankmentEndpointInput.getAll("PROJ", "SPK");
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SPK", mockHttpRequestBuilder.getQueryParameter(GetAll.OFFICE_QUERY_PARAMETER));
        assertEquals("PROJ", mockHttpRequestBuilder.getQueryParameter(GetAll.PROJECT_ID_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetAllNullTimeSeriesId() {
        assertThrows(NullPointerException.class, () -> EmbankmentEndpointInput.getOne(null, ""));
        assertThrows(NullPointerException.class, () -> EmbankmentEndpointInput.getOne("", null));
    }

    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String embankmentId = "EMB";
        String office = "SPK";
        EmbankmentEndpointInput.GetOne input = EmbankmentEndpointInput.getOne(embankmentId, office);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(embankmentId, input.embankmentId());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(EmbankmentEndpointInput.GetOne.OFFICE_QUERY_PARAMETER));
    }

    @Test
    void testGetOneNullTimeSeriesId() {
        assertThrows(NullPointerException.class, () -> EmbankmentEndpointInput.getOne("", null));
        assertThrows(NullPointerException.class, () -> EmbankmentEndpointInput.getOne(null, ""));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v1/json/embankment.json");
        Embankment embankment = RadarObjectMapper.mapJsonToObject(collect, Embankment.class);
        EmbankmentEndpointInput.Post input = EmbankmentEndpointInput.post(embankment)
                .failIfExists(false);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(embankment, input.embankment());
        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostNullTimeSeries() {
        assertThrows(NullPointerException.class, () -> EmbankmentEndpointInput.post(null));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String embankmentId = "EMB";
        String office = "SPK";
        EmbankmentEndpointInput.Delete input = EmbankmentEndpointInput.delete(embankmentId, office)
                .deleteMethod(DeleteMethod.ALL);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(embankmentId, input.embankmentId());
        assertEquals(DeleteMethod.ALL.name(), mockHttpRequestBuilder.getQueryParameter(Delete.METHOD_QUERY_PARAMETER));
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(Delete.OFFICE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNulls() {
        assertThrows(NullPointerException.class, () -> EmbankmentEndpointInput.delete("", null));
        assertThrows(NullPointerException.class, () -> EmbankmentEndpointInput.delete(null, ""));
    }

    @Test
    void testPatch() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String oldEmbankmentId = "EMB";
        String newEmbankmentId = "EMB_NEW";
        String office = "SPK";
        EmbankmentEndpointInput.Patch input = EmbankmentEndpointInput.patch(oldEmbankmentId, newEmbankmentId, office);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(oldEmbankmentId, input.embankmentId());
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(Patch.OFFICE_QUERY_PARAMETER));
        assertEquals(newEmbankmentId, mockHttpRequestBuilder.getQueryParameter(Patch.NAME_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

}
