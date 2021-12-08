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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import mil.army.usace.hec.cwms.http.client.NoDataFoundException;
import mil.army.usace.hec.cwms.radar.client.model.AssignedLocation;
import mil.army.usace.hec.cwms.radar.client.model.LocationCategory;
import mil.army.usace.hec.cwms.radar.client.model.LocationGroup;
import org.junit.jupiter.api.Test;

class TestLocationGroupController extends TestController {

    @Test
    void testRetrieveLocationGroup() throws IOException {
        String resource = "radar/v1/json/location_group.json";
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
        assertEquals(BigDecimal.valueOf(540009), locationGroup.getAssignedLocations().get(0).getLocationCode());

        assertEquals("Testing", locationGroup.getAssignedLocations().get(1).getLocationId());
        assertEquals("Testing", locationGroup.getAssignedLocations().get(1).getBaseLocationId());
        assertEquals("Testing", locationGroup.getAssignedLocations().get(1).getAliasId());
        assertEquals(BigDecimal.valueOf(542009), locationGroup.getAssignedLocations().get(1).getLocationCode());

        assertEquals("NotThere", locationGroup.getAssignedLocations().get(2).getLocationId());
        assertEquals("NotThere", locationGroup.getAssignedLocations().get(2).getBaseLocationId());
        assertEquals("NotThere", locationGroup.getAssignedLocations().get(2).getAliasId());
        assertEquals(BigDecimal.valueOf(541009), locationGroup.getAssignedLocations().get(2).getLocationCode());
    }

    @Test
    void testRetrieveLocationGroupNoDataFound() throws IOException {
        String resource = "radar/v1/json/location_group_nodatafound.json";
        URL resourceUrl = getClass().getClassLoader().getResource(resource);
        if (resourceUrl == null) {
            throw new IOException("Failed to get resource: " + resource);
        }
        Path path = new File(resourceUrl.getFile()).toPath();
        String collect = String.join("\n", Files.readAllLines(path));
        mockHttpServer.enqueue(404, collect);
        mockHttpServer.start();
        LocationGroupEndpointInput input = new LocationGroupEndpointInput("Bogus")
            .officeId("SWT")
            .categoryId("NotReal");
        assertThrows(NoDataFoundException.class, () -> new LocationGroupController().retrieveLocationGroup(buildConnectionInfo(), input));
    }

    @Test
    void testRetrieveLocationGroups() throws IOException {
        String resource = "radar/v1/json/location_groups.json";
        URL resourceUrl = getClass().getClassLoader().getResource(resource);
        if (resourceUrl == null) {
            throw new IOException("Failed to get resource: " + resource);
        }
        Path path = new File(resourceUrl.getFile()).toPath();
        String collect = String.join("\n", Files.readAllLines(path));
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LocationGroupEndpointInput input = new LocationGroupEndpointInput()
            .includeAssigned(true)
            .officeId("CWMS");
        List<LocationGroup> locationGroups = new LocationGroupController().retrieveLocationGroups(buildConnectionInfo(), input);

        assertEquals(3, locationGroups.size());
        LocationGroup locationGroup = locationGroups.get(1);
        assertEquals("CWMS", locationGroup.getOfficeId());
        assertEquals("CWMS Legacy Naming", locationGroup.getId());
        assertEquals("Alias group for legacy location IDs", locationGroup.getDescription());
        LocationCategory locationCategory = locationGroup.getLocationCategory();
        assertEquals("Agency Aliases", locationCategory.getId());
        assertEquals("CWMS", locationCategory.getOfficeId());
        assertEquals("Location aliases for other agencies", locationCategory.getDescription());

        List<AssignedLocation> assignedLocations = locationGroup.getAssignedLocations();
        AssignedLocation assignedLocation = assignedLocations.get(0);
        assertEquals("Deleted TS ID", assignedLocation.getLocationId());
        assertEquals("Deleted TS ID", assignedLocation.getBaseLocationId());
        assertEquals("Deleted TS ID", assignedLocation.getAliasId());
        assertEquals(BigDecimal.ZERO, assignedLocation.getLocationCode());
    }

    @Test
    void testRetrieveLocationGroupsNoAssignedLocations() throws IOException {
        String resource = "radar/v1/json/location_groups_noassignedlocs.json";
        URL resourceUrl = getClass().getClassLoader().getResource(resource);
        if (resourceUrl == null) {
            throw new IOException("Failed to get resource: " + resource);
        }
        Path path = new File(resourceUrl.getFile()).toPath();
        String collect = String.join("\n", Files.readAllLines(path));
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LocationGroupEndpointInput input = new LocationGroupEndpointInput()
            .includeAssigned(false)
            .officeId("CWMS");
        List<LocationGroup> locationGroups = new LocationGroupController().retrieveLocationGroups(buildConnectionInfo(), input);

        assertEquals(823, locationGroups.size());
        LocationGroup locationGroup = locationGroups.get(1);
        assertEquals("SWT", locationGroup.getOfficeId());
        assertEquals("CWMS Standard Naming", locationGroup.getId());
        assertEquals("Alias group for location IDs that conform to CWMS Standard Naming Convention", locationGroup.getDescription());
        LocationCategory locationCategory = locationGroup.getLocationCategory();
        assertEquals("Agency Aliases", locationCategory.getId());
        assertEquals("CWMS", locationCategory.getOfficeId());
        assertEquals("Location aliases for other agencies", locationCategory.getDescription());

        List<AssignedLocation> assignedLocations = locationGroup.getAssignedLocations();
        assertNull(assignedLocations);
    }
}
