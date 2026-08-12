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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;
import mil.army.usace.hec.cwms.data.api.client.model.Pool;
import mil.army.usace.hec.cwms.data.api.client.model.Pools;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

class TestPoolController extends TestController {

    @Test
    void testRetrieveCatalog() throws IOException {
        String collect = readJsonFile("radar/v2/json/pools.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        PoolEndpointInput.GetAll input = PoolEndpointInput.getAll()
                .officeId("SPK")
                .projectIdMask("PROJECT")
                .nameMask("Conservation")
                .pageSize(1);
        Pools values = new PoolController().retrievePools(buildConnectionInfo(), input);
        assertNotNull(values);
        assertEquals("abcdefg", values.getPage());
        assertEquals("hijklmnop", values.getNextPage());
        assertEquals(1, values.getPageSize());
        assertEquals(1, values.getTotal());
        List<Pool> pools = values.getPools();
        assertFalse(pools.isEmpty());
        Pool value = pools.get(0);
        assertEquals("Conservation", value.getPoolName().getPoolName());
        assertEquals("SPK", value.getPoolName().getOfficeId());
        assertEquals("PROJECT", value.getProjectId());
        assertEquals("Bottom of Conservation", value.getBottomLevelId());
        assertEquals("Top of Conservation", value.getTopLevelId());
        assertFalse(value.isImplicit());
        assertEquals(1.0, value.getAttribute().doubleValue(), 0.0);
        assertEquals("Conservation pool used for unit testing", value.getDescription());
        assertEquals("UNIT TESTING POOL ENDPOINT", value.getClobText());
    }

    @Test
    void testRetrieveOne() throws IOException {
        String collect = readJsonFile("radar/v2/json/pool.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        PoolEndpointInput.GetOne input = PoolEndpointInput.getOne("Conservation", "PROJECT", "SPK");
        Pool value = new PoolController().retrievePool(buildConnectionInfo(), input);
        assertEquals("Conservation", value.getPoolName().getPoolName());
        assertEquals("SPK", value.getPoolName().getOfficeId());
        assertEquals("PROJECT", value.getProjectId());
        assertEquals("Bottom of Conservation", value.getBottomLevelId());
        assertEquals("Top of Conservation", value.getTopLevelId());
        assertFalse(value.isImplicit());
        assertEquals(1.0, value.getAttribute().doubleValue(), 0.0);
        assertEquals("Conservation pool used for unit testing", value.getDescription());
        assertEquals("UNIT TESTING POOL ENDPOINT", value.getClobText());
    }

    @Test
    void testStorePool() throws Exception {
        String collect = readJsonFile("radar/v2/json/pool.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Pool pool = RadarObjectMapper.mapJsonToObject(collect, Pool.class);
        PoolController controller = new PoolController();
        PoolEndpointInput.Post input = PoolEndpointInput.post(pool);
        assertDoesNotThrow(() -> controller.storePool(buildConnectionInfo(cookieJarSupplier), input));
        var requestWrapper = mockHttpServer.takeRequest();
        assertEquals("POST", requestWrapper.getMethod());
        assertTrue(requestWrapper.getPath().startsWith("/pools?"));
    }

    @Test
    void testDeletePool() throws Exception {
        String collect = readJsonFile("radar/v2/json/pool.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Pool value = RadarObjectMapper.mapJsonToObject(collect, Pool.class);
        PoolController controller = new PoolController();
        PoolEndpointInput.Delete input = PoolEndpointInput.delete(value.getPoolName().getPoolName(),
                value.getProjectId(), value.getPoolName().getOfficeId());
        assertDoesNotThrow(() -> controller.deletePool(buildConnectionInfo(cookieJarSupplier), input));
        var requestWrapper = mockHttpServer.takeRequest();
        assertEquals("DELETE", requestWrapper.getMethod());
        assertTrue(requestWrapper.getPath().startsWith("/pools/" + value.getPoolName().getPoolName() + "?"));
    }

    @Test
    void testRenamePool() throws Exception {
        String collect = readJsonFile("radar/v2/json/pool.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Pool value = RadarObjectMapper.mapJsonToObject(collect, Pool.class);
        PoolController controller = new PoolController();
        PoolEndpointInput.Patch input = PoolEndpointInput.patch(value.getPoolName().getPoolName(),
                "NewName", "SPK");
        assertDoesNotThrow(() -> controller.renamePool(buildConnectionInfo(cookieJarSupplier), input));
        var requestWrapper = mockHttpServer.takeRequest();
        assertEquals("PATCH", requestWrapper.getMethod());
        assertTrue(requestWrapper.getPath().startsWith("/pools/" + value.getPoolName().getPoolName() + "?"));
    }
}
