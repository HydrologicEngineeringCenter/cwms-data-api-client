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

import mil.army.usace.hec.cwms.radar.client.model.Location;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;


class TestLocationController extends TestController{

    @Test
    void testRetrieveLocation() throws IOException
    {
        String resource = "radar/json/location.json";
        URL resourceURL = getClass().getClassLoader().getResource(resource);
        if(resourceURL == null)
        {
            throw new IOException("Failed to get resource: " + resource);
        }
        Path path = new File(resourceURL.getFile()).toPath();
        String collect = String.join("\n", Files.readAllLines(path));
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LocationEndPointInput input = new LocationEndPointInput("LOC_TEST")
                .officeId("SWT")
                .unit("SI");
        Location location = new LocationController().retrieveLocation(buildConnectionInfo(), input);
        assertEquals("LOC_TEST", location.getName());
        assertEquals("LOC_TEST", location.getPublicName());
        assertEquals("SWT", location.getOfficeId());
        assertEquals(10.0, location.getLatitude().doubleValue());
        assertEquals(50.0, location.getLongitude().doubleValue());
        assertEquals("NGVD-29", location.getHorizontalDatum());
        assertEquals("UTC", location.getTimezoneName());
        assertEquals("US", location.getNation().toString());
        assertEquals("LOCATION_TEST", location.getLongName());
        assertEquals("For Testing", location.getDescription());
        assertEquals("CA", location.getStateInitial());
        assertEquals("Sacramento", location.getCountyName());
        assertEquals("Sacramento", location.getNearestCity());
        assertEquals(0.0, location.getElevation(), 0.0);
        assertEquals(10.0, location.getPublishedLongitude());
        assertEquals(50.0, location.getPublishedLatitude());
    }
}
