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

import mil.army.usace.hec.cwms.radar.client.model.Parameter;
import mil.army.usace.hec.cwms.radar.client.model.Unit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

final class TestCatalogController extends TestController {

    @Test
    void testParameterCatalog() throws Exception {
        String collect = readJsonFile("radar/v1/json/parameter_catalog.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        List<Parameter> parameters = new CatalogController().retrieveParameterCatalog(buildConnectionInfo());
        assertFalse(parameters.isEmpty());
    }

    @Test
    void testUnitCatalog() throws Exception {
        String collect = readJsonFile("radar/v1/json/unit_catalog.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        List<Unit> units = new CatalogController().retrieveUnitCatalog(buildConnectionInfo());
        assertFalse(units.isEmpty());
    }
}
