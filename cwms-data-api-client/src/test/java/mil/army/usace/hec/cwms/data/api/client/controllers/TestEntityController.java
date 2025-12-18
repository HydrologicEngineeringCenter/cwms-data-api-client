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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;
import mil.army.usace.hec.cwms.data.api.client.model.CwmsId;
import mil.army.usace.hec.cwms.data.api.client.model.Entity;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

class TestEntityController extends TestController {

    @Test
    void testRetrieveCatalog() throws IOException {
        String collect = readJsonFile("radar/v2/json/entities.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        EntityEndpointInput.GetAll input = EntityEndpointInput.getAll().officeId("SPK");
        List<Entity> values = new EntityController().retrieveEntities(buildConnectionInfo(), input);
        assertFalse(values.isEmpty());
        Entity value = values.get(0);
        assertEquals("NWS", value.getId().getName());
        assertEquals("SPK", value.getId().getOfficeId());
        assertEquals("NOAA", value.getParentEntityId());
        assertEquals("GOV", value.getCategoryId());
        assertEquals("National Weather Service", value.getLongName());
        assertNotNull(value);
    }

    @Test
    void testRetrieveOne() throws IOException {
        String collect = readJsonFile("radar/v2/json/entity.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        EntityEndpointInput.GetOne input = EntityEndpointInput.getOne("NWS", "SPK");
        Entity value = new EntityController().retrieveEntity(buildConnectionInfo(), input);
        assertEquals("NWS", value.getId().getName());
        assertEquals("SPK", value.getId().getOfficeId());
        assertEquals("NOAA", value.getParentEntityId());
        assertEquals("GOV", value.getCategoryId());
        assertEquals("National Weather Service", value.getLongName());
        assertNotNull(value);
    }

    @Test
    void testStoreEntity() throws IOException {
        String collect = readJsonFile("radar/v2/json/entity.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Entity entity = RadarObjectMapper.mapJsonToObject(collect, Entity.class);
        EntityController controller = new EntityController();
        EntityEndpointInput.Post input = EntityEndpointInput.post(entity);
        assertDoesNotThrow(() -> controller.storeEntity(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testDeleteEntity() throws IOException {
        String collect = readJsonFile("radar/v2/json/entity.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Entity value = RadarObjectMapper.mapJsonToObject(collect, Entity.class);
        EntityController controller = new EntityController();
        controller.storeEntity(buildConnectionInfo(cookieJarSupplier), EntityEndpointInput.post(value));
        EntityEndpointInput.Delete input = EntityEndpointInput.delete(value.getId().getName(), value.getId().getOfficeId(), true);
        assertDoesNotThrow(() -> controller.deleteEntity(buildConnectionInfo(cookieJarSupplier), input));
    }

    @Test
    void testUpdateEntity() throws Exception {
        String collect = readJsonFile("radar/v2/json/entity.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Entity value = RadarObjectMapper.mapJsonToObject(collect, Entity.class);
        EntityController controller = new EntityController();
        Entity updated = new Entity().id(new CwmsId().name("NWS").officeId("SPK"))
            .parentEntityId("NOAA").categoryId("GOV").longName("N W S");
        EntityEndpointInput.Patch input = EntityEndpointInput.patch(updated);
        assertDoesNotThrow(() -> controller.updateEntity(buildConnectionInfo(cookieJarSupplier), input));
        var requestWrapper = mockHttpServer.takeRequest();
        assertEquals("PATCH", requestWrapper.getMethod());
        assertTrue(requestWrapper.getPath().startsWith("/entity/" + value.getId().getName()));
    }
}
