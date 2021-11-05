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
