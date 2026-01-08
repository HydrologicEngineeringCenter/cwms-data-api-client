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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import org.junit.jupiter.api.Test;

class TestLocationKindController extends TestController {

    public static final String MASK = "PROJ-*";
    public static final String OFFICE = "SPK";

    @Test
    void testGetLocationKindChildLocations() throws Exception {
        String collect = readJsonFile("radar/v1/json/cwmsid_location_kinds.json");
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LocationKindController controller = new LocationKindController();
        LocationKindEndpointInput.GetAll input = LocationKindEndpointInput.getAll()
            .locationIdMask(MASK)
            .officeId(OFFICE);
        ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
        var locations = controller.getLocationsWithKind(apiConnectionInfo, input);
        assertFalse(locations.isEmpty());
        var loc1 = locations.get(0);
        assertEquals("PROJ-ABCD", loc1.getLocationId().getName());
        assertEquals(OFFICE, loc1.getLocationId().getOfficeId());
        assertEquals("Outlet", loc1.getLocationKindId());
    }
}
