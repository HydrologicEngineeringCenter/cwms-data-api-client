package mil.army.usace.hec.cwms.radar.client.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import mil.army.usace.hec.cwms.radar.client.model.LocationCategory;
import org.junit.jupiter.api.Test;

class TestLocationCategoryController extends TestController {

    @Test
    void testRetrieveLocation() throws IOException {
        String resource = "radar/json/location_category.json";
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
}
