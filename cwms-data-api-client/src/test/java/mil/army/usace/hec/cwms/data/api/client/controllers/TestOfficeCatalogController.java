/*
 * MIT License
 *
 * Copyright (c) 2022 Hydrologic Engineering Center
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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import mil.army.usace.hec.cwms.data.api.client.model.Office;
import org.junit.jupiter.api.Test;

class TestOfficeCatalogController extends TestController {

    @Test
    void testRetrieveLocationCatalog() throws IOException {
        String collect = readJsonFile("radar/v2/json/offices.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        List<Office> catalog = new OfficeController().retrieveOffices(buildConnectionInfo());
        assertEquals(68, catalog.size());
        Office office = catalog.get(2);
        assertEquals("CRREL", office.getName(), "Should match test JSON data");
        assertEquals("Cold Regions Research and Engineering Lab", office.getLongName(), "Should match test JSON data");
        assertEquals("FOA", office.getType(), "Should match test JSON data");
        assertEquals("ERD", office.getReportsTo(), "Should match test JSON data");
    }

}
