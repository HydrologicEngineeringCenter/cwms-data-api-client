/*
 * MIT License
 *
 * Copyright (c) 2021 Hydrologic Engineering Center
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import mil.army.usace.hec.cwms.radar.client.model.LocationCategory;
import org.junit.jupiter.api.Test;

class TestLocationCategoryController extends TestController {

    @Test
    void testRetrieveLocationCategory() throws IOException {
        String resource = "radar/v1/json/location_category.json";
        URL resourceUrl = getClass().getClassLoader().getResource(resource);
        if (resourceUrl == null) {
            throw new IOException("Failed to get resource: " + resource);
        }
        Path path = new File(resourceUrl.getFile()).toPath();
        String collect = String.join("\n", Files.readAllLines(path));
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LocationCategoryEndpointInput input = new LocationCategoryEndpointInput("CWMS Mobile Location Listings")
            .officeId("SWT");
        LocationCategory locationCategory = new LocationCategoryController().retrieveLocationCategory(buildConnectionInfo(), input);
        assertNotNull(locationCategory);
        assertEquals("CWMS Mobile Location Listings", locationCategory.getId());
        assertEquals("SWT", locationCategory.getOfficeId());
        assertEquals("For Testing", locationCategory.getDescription());
    }

    @Test
    void testRetrieveLocationCategories() throws IOException {
        String resource = "radar/v1/json/location_categories.json";
        URL resourceUrl = getClass().getClassLoader().getResource(resource);
        if (resourceUrl == null) {
            throw new IOException("Failed to get resource: " + resource);
        }
        Path path = new File(resourceUrl.getFile()).toPath();
        String collect = String.join("\n", Files.readAllLines(path));
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LocationCategoryEndpointInput input = new LocationCategoryEndpointInput()
            .officeId("SWT");
        List<LocationCategory> locationCategories = new LocationCategoryController().retrieveLocationCategories(buildConnectionInfo(), input);
        LocationCategory locationCategory = locationCategories.get(0);
        assertEquals("RDL_Basins", locationCategory.getId());
        assertEquals("SWT", locationCategory.getOfficeId());
        assertEquals("Collection of Basins", locationCategory.getDescription());
    }
}
