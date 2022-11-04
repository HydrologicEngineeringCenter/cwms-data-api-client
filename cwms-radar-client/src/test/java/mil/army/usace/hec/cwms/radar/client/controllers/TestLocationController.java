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

package mil.army.usace.hec.cwms.radar.client.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import mil.army.usace.hec.cwms.radar.client.model.Location;
import org.junit.jupiter.api.Test;


class TestLocationController extends TestController {

    @Test
    void testRetrieveLocation() throws IOException {
        String resource = "radar/v2/json/location.json";
        URL resourceUrl = getClass().getClassLoader().getResource(resource);
        if (resourceUrl == null) {
            throw new IOException("Failed to get resource: " + resource);
        }
        Path path = new File(resourceUrl.getFile()).toPath();
        String collect = String.join("\n", Files.readAllLines(path));
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LocationEndPointInput input = new LocationEndPointInput("AARK")
            .officeId("SWT")
            .unit("SI");
        Location location = new LocationController().retrieveLocation(buildConnectionInfo(), input);
        assertEquals("AARK", location.getName());
        assertEquals("ARKANSAS R AT ARKANSAS CITY, KS", location.getPublicName());
        assertEquals("SWT", location.getOfficeId());
        assertEquals(37.056418, location.getLatitude().doubleValue());
        assertEquals(-97.0580939, location.getLongitude().doubleValue());
        assertTrue(location.isActive());
        assertEquals("NAD83", location.getHorizontalDatum());
        assertEquals("NGVD29", location.getVerticalDatum());
        assertEquals("CST6CDT", location.getTimezoneName());
        assertEquals("US", location.getNation().toString());
        assertEquals("ARKANSAS R AT ARKANSAS CITY, KS", location.getLongName());
        assertEquals("ARKANSAS R AT ARKANSAS CITY, KS", location.getDescription());
        assertEquals("STREAM_LOCATION", location.getLocationKind());
        assertEquals("KS", location.getStateInitial());
        assertEquals("Unknown County or County N/A", location.getCountyName());
        assertEquals("Arkansas City, KS", location.getNearestCity());
        assertEquals(320.04, location.getElevation(), 0.0);
        assertNull(location.getPublishedLongitude());
        assertNull(location.getPublishedLatitude());
    }

}
