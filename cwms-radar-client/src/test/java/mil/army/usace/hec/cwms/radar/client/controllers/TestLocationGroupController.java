package mil.army.usace.hec.cwms.radar.client.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import mil.army.usace.hec.cwms.radar.client.model.LocationGroup;
import org.junit.jupiter.api.Test;

class TestLocationGroupController extends TestController {

    @Test
    void testRetrieveLocationGroup() throws IOException {
        String resource = "radar/json/locationgroup.json";
        URL resourceUrl = getClass().getClassLoader().getResource(resource);
        if (resourceUrl == null) {
            throw new IOException("Failed to get resource: " + resource);
        }
        Path path = new File(resourceUrl.getFile()).toPath();
        String collect = String.join("\n", Files.readAllLines(path));
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LocationGroupEndpointInput input = new LocationGroupEndpointInput("Lakes")
            .officeId("SWT")
            .categoryId("CWMS Mobile Location Listings");
        LocationGroup locationGroup = new LocationGroupController().retrieveLocationGroup(buildConnectionInfo(), input);
        assertNotNull(locationGroup);
        assertEquals("SWT", locationGroup.getOfficeId());
        assertEquals("Lakes", locationGroup.getId());
        assertEquals("CWMS Mobile Location Listings", locationGroup.getLocationCategory().getId());

        assertEquals("FakeLake", locationGroup.getAssignedLocations().get(0).getLocationId());
        assertEquals("FakeLake", locationGroup.getAssignedLocations().get(0).getBaseLocationId());
        assertEquals("FakeLake", locationGroup.getAssignedLocations().get(0).getAliasId());
        assertEquals(540009, locationGroup.getAssignedLocations().get(0).getLocationCode());

        assertEquals("Testing", locationGroup.getAssignedLocations().get(1).getLocationId());
        assertEquals("Testing", locationGroup.getAssignedLocations().get(1).getBaseLocationId());
        assertEquals("Testing", locationGroup.getAssignedLocations().get(1).getAliasId());
        assertEquals(542009, locationGroup.getAssignedLocations().get(1).getLocationCode());

        assertEquals("NotThere", locationGroup.getAssignedLocations().get(2).getLocationId());
        assertEquals("NotThere", locationGroup.getAssignedLocations().get(2).getBaseLocationId());
        assertEquals("NotThere", locationGroup.getAssignedLocations().get(2).getAliasId());
        assertEquals(541009, locationGroup.getAssignedLocations().get(2).getLocationCode());
    }
}
