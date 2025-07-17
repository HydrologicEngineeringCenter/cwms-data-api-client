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

package mil.army.usace.hec.cwms.data.api.client.controllers;

import mil.army.usace.hec.cwms.http.client.NoDataFoundException;
import mil.army.usace.hec.cwms.data.api.client.model.AssignedLocation;
import mil.army.usace.hec.cwms.data.api.client.model.LocationCategory;
import mil.army.usace.hec.cwms.data.api.client.model.LocationGroup;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestLocationGroupController extends TestController {

    @Test
    void testRetrieveLocationGroup() throws IOException {
        String collect = readJsonFile("radar/v1/json/location_group.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LocationGroupEndpointInput.GetOne input = LocationGroupEndpointInput.getOne("CWMS Mobile Location Listings", "Lakes", "SWT", "SWT", "SWT");
        LocationGroup locationGroup = new LocationGroupController().retrieveLocationGroup(buildConnectionInfo(), input);
        assertNotNull(locationGroup);
        assertEquals("SWT", locationGroup.getOfficeId());
        assertEquals("Lakes", locationGroup.getId());
        assertEquals("CWMS Mobile Location Listings", locationGroup.getLocationCategory().getId());

        assertEquals("FakeLake", locationGroup.getAssignedLocations().get(0).getLocationId());
        assertEquals("SWT", locationGroup.getAssignedLocations().get(0).getOfficeId());
        assertEquals("FakeLake", locationGroup.getAssignedLocations().get(0).getAliasId());

        assertEquals("Testing", locationGroup.getAssignedLocations().get(1).getLocationId());
        assertEquals("SWT", locationGroup.getAssignedLocations().get(1).getOfficeId());
        assertEquals("Testing", locationGroup.getAssignedLocations().get(1).getAliasId());

        assertEquals("NotThere", locationGroup.getAssignedLocations().get(2).getLocationId());
        assertEquals("SWT", locationGroup.getAssignedLocations().get(2).getOfficeId());
        assertEquals("NotThere", locationGroup.getAssignedLocations().get(2).getAliasId());
    }

    @Test
    void testRetrieveLocationGroupNoDataFound() throws IOException {
        String collect = readJsonFile("radar/v1/json/location_group_nodatafound.json");
        mockHttpServer.enqueue(404, collect);
        mockHttpServer.start();
        LocationGroupEndpointInput.GetOne input = LocationGroupEndpointInput.getOne("NotReal", "Bogus", "SWT", "SWT", "SWT");
        assertThrows(NoDataFoundException.class, () -> new LocationGroupController().retrieveLocationGroup(buildConnectionInfo(), input));
    }

    @Test
    void testRetrieveLocationGroups() throws IOException {
        String collect = readJsonFile("radar/v1/json/location_groups.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LocationGroupEndpointInput.GetAll input = LocationGroupEndpointInput.getAll()
                .includeAssigned(true)
                .locationOfficeId("CWMS");
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
        assertEquals("CWMS", assignedLocation.getOfficeId());
        assertEquals("Deleted TS ID", assignedLocation.getAliasId());
    }

    @Test
    void testRetrieveLocationGroupsNoAssignedLocations() throws IOException {
        String collect = readJsonFile("radar/v1/json/location_groups_noassignedlocs.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LocationGroupEndpointInput.GetAll input = LocationGroupEndpointInput.getAll()
                .includeAssigned(false)
                .locationOfficeId("CWMS");
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
        assertNotNull(assignedLocations);
        assertTrue(assignedLocations.isEmpty());
    }

    @Test
    void testPost() throws IOException {
        String collect = readJsonFile("radar/v1/json/location_group.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LocationGroup locationGroup = RadarObjectMapper.mapJsonToObject(collect, LocationGroup.class);
        LocationGroupEndpointInput.Post input = LocationGroupEndpointInput.post(locationGroup);
        assertDoesNotThrow(() -> new LocationGroupController().storeLocationGroup(buildConnectionInfo(), input));
    }

    @Test
    void testPatch() throws IOException {
        String collect = readJsonFile("radar/v1/json/location_group.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LocationGroup locationGroup = RadarObjectMapper.mapJsonToObject(collect, LocationGroup.class);
        LocationGroupEndpointInput.Patch input = LocationGroupEndpointInput.patch("SWT", locationGroup.getId() + "1", locationGroup);
        assertDoesNotThrow(() -> new LocationGroupController().updateLocationGroup(buildConnectionInfo(), input));
    }

    @Test
    void testDelete() throws IOException {
        String collect = readJsonFile("radar/v1/json/location_group.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LocationGroupEndpointInput.Delete input = LocationGroupEndpointInput.delete("CWMS Mobile Location Listings", "Lakes", "SWT");
        assertDoesNotThrow(() -> new LocationGroupController().deleteLocationGroup(buildConnectionInfo(), input));
    }
}
