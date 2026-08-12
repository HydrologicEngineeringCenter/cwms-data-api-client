/*
 * MIT License
 *
 * Copyright (c) 2026 Hydrologic Engineering Center
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
import static mil.army.usace.hec.cwms.data.api.client.controllers.PoolEndpointInput.ANY_MASK;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import mil.army.usace.hec.cwms.data.api.client.controllers.PoolEndpointInput.Delete;
import mil.army.usace.hec.cwms.data.api.client.controllers.PoolEndpointInput.GetAll;
import mil.army.usace.hec.cwms.data.api.client.controllers.PoolEndpointInput.GetOne;
import mil.army.usace.hec.cwms.data.api.client.controllers.PoolEndpointInput.Patch;
import mil.army.usace.hec.cwms.data.api.client.controllers.PoolEndpointInput.Post;
import mil.army.usace.hec.cwms.data.api.client.model.Pool;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

class TestPoolEndpointInput {

    @Test
    void testGetAllQueryRequestDefault() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        GetAll input = PoolEndpointInput.getAll();
        input.addInputParameters(mockHttpRequestBuilder);
        assertNull(mockHttpRequestBuilder.getQueryParameter(GetAll.OFFICE_QUERY_PARAMETER));
        assertEquals(ANY_MASK, mockHttpRequestBuilder.getQueryParameter(GetAll.ID_MASK_QUERY_PARAMETER));
        assertEquals(ANY_MASK, mockHttpRequestBuilder.getQueryParameter(GetAll.NAME_MASK_QUERY_PARAMETER));
        assertEquals(ANY_MASK, mockHttpRequestBuilder.getQueryParameter(GetAll.BOTTOM_MASK_QUERY_PARAMETER));
        assertEquals(ANY_MASK, mockHttpRequestBuilder.getQueryParameter(GetAll.TOP_MASK_QUERY_PARAMETER));
        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(GetAll.INCLUDE_EXPLICIT_QUERY_PARAMETER));
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(GetAll.INCLUDE_IMPLICIT_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(GetAll.PAGE_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(GetAll.PAGE_SIZE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetAllQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        GetAll input = PoolEndpointInput.getAll()
                .officeId("SPK")
                .projectIdMask("PROJ*")
                .nameMask("Conservation")
                .bottomMask("Bottom*")
                .topMask("Top*")
                .includeExplicit(true)
                .includeImplicit(false)
                .page("abcdefg")
                .pageSize(50);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SPK", mockHttpRequestBuilder.getQueryParameter(GetAll.OFFICE_QUERY_PARAMETER));
        assertEquals("PROJ*", mockHttpRequestBuilder.getQueryParameter(GetAll.ID_MASK_QUERY_PARAMETER));
        assertEquals("Conservation", mockHttpRequestBuilder.getQueryParameter(GetAll.NAME_MASK_QUERY_PARAMETER));
        assertEquals("Bottom*", mockHttpRequestBuilder.getQueryParameter(GetAll.BOTTOM_MASK_QUERY_PARAMETER));
        assertEquals("Top*", mockHttpRequestBuilder.getQueryParameter(GetAll.TOP_MASK_QUERY_PARAMETER));
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(GetAll.INCLUDE_EXPLICIT_QUERY_PARAMETER));
        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(GetAll.INCLUDE_IMPLICIT_QUERY_PARAMETER));
        assertEquals("abcdefg", mockHttpRequestBuilder.getQueryParameter(GetAll.PAGE_QUERY_PARAMETER));
        assertEquals("50", mockHttpRequestBuilder.getQueryParameter(GetAll.PAGE_SIZE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String poolId = "Conservation";
        String projectId = "PROJECT";
        String office = "SPK";
        GetOne input = PoolEndpointInput.getOne(poolId, projectId, office);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(poolId, input.poolId());
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(GetOne.OFFICE_QUERY_PARAMETER));
        assertEquals(projectId, mockHttpRequestBuilder.getQueryParameter(GetOne.PROJECT_ID_QUERY_PARAMETER));
        assertEquals(ANY_MASK, mockHttpRequestBuilder.getQueryParameter(GetOne.BOTTOM_MASK_QUERY_PARAMETER));
        assertEquals(ANY_MASK, mockHttpRequestBuilder.getQueryParameter(GetOne.TOP_MASK_QUERY_PARAMETER));
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(GetOne.INCLUDE_EXPLICIT_QUERY_PARAMETER));
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(GetOne.INCLUDE_IMPLICIT_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetOneOptionalQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        GetOne input = PoolEndpointInput.getOne("Conservation", "PROJECT", "SPK")
                .bottomMask("Bottom*")
                .topMask("Top*")
                .includeExplicit(false)
                .includeImplicit(false);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("Bottom*", mockHttpRequestBuilder.getQueryParameter(GetOne.BOTTOM_MASK_QUERY_PARAMETER));
        assertEquals("Top*", mockHttpRequestBuilder.getQueryParameter(GetOne.TOP_MASK_QUERY_PARAMETER));
        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(GetOne.INCLUDE_EXPLICIT_QUERY_PARAMETER));
        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(GetOne.INCLUDE_IMPLICIT_QUERY_PARAMETER));
    }

    @Test
    void testGetOneNulls() {
        assertThrows(NullPointerException.class, () -> PoolEndpointInput.getOne(null, "", ""));
        assertThrows(NullPointerException.class, () -> PoolEndpointInput.getOne("", null, ""));
        assertThrows(NullPointerException.class, () -> PoolEndpointInput.getOne("", "", null));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v2/json/pool.json");
        Pool pool = RadarObjectMapper.mapJsonToObject(collect, Pool.class);
        Post input = PoolEndpointInput.post(pool)
                .failIfExists(false)
                .createPoolName(false);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(pool, input.pool());
        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(Post.FAIL_IF_EXISTS_QUERY_PARAMETER));
        assertEquals("false", mockHttpRequestBuilder.getQueryParameter(Post.CREATE_POOL_NAME_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostQueryRequestDefault() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v2/json/pool.json");
        Pool pool = RadarObjectMapper.mapJsonToObject(collect, Pool.class);
        Post input = PoolEndpointInput.post(pool);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(Post.FAIL_IF_EXISTS_QUERY_PARAMETER));
        assertEquals("true", mockHttpRequestBuilder.getQueryParameter(Post.CREATE_POOL_NAME_QUERY_PARAMETER));
    }

    @Test
    void testPostNullPool() {
        assertThrows(NullPointerException.class, () -> PoolEndpointInput.post(null));
    }

    @Test
    void testPatchQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String oldPoolId = "Conservation";
        String newPoolId = "Conservation_NEW";
        String office = "SPK";
        Patch input = PoolEndpointInput.patch(oldPoolId, newPoolId, office);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(oldPoolId, input.poolId());
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(Patch.OFFICE_QUERY_PARAMETER));
        assertEquals(newPoolId, mockHttpRequestBuilder.getQueryParameter(Patch.NAME_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPatchNulls() {
        assertThrows(NullPointerException.class, () -> PoolEndpointInput.patch(null, "", ""));
        assertThrows(NullPointerException.class, () -> PoolEndpointInput.patch("", null, ""));
        assertThrows(NullPointerException.class, () -> PoolEndpointInput.patch("", "", null));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String poolId = "Conservation";
        String projectId = "PROJECT";
        String office = "SPK";
        Delete input = PoolEndpointInput.delete(poolId, projectId, office);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(poolId, input.poolId());
        assertEquals(office, mockHttpRequestBuilder.getQueryParameter(Delete.OFFICE_QUERY_PARAMETER));
        assertEquals(projectId, mockHttpRequestBuilder.getQueryParameter(Delete.PROJECT_ID_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNulls() {
        assertThrows(NullPointerException.class, () -> PoolEndpointInput.delete(null, "", ""));
        assertThrows(NullPointerException.class, () -> PoolEndpointInput.delete("", null, ""));
        assertThrows(NullPointerException.class, () -> PoolEndpointInput.delete("", "", null));
    }
}
