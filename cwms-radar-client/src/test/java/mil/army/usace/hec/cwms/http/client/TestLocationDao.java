/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import mil.army.usace.hec.cwms.radar.client.Locations;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestLocationDao {

    private static final String BASE_URL = "http://localhost:11524";
    private static MockWebServer server;

    @BeforeAll
    public static void setup() {
        server = new MockWebServer();
    }

    @Test
    void testAllLocationsGet() throws IOException {
        Path path = new File(getClass().getClassLoader().getResource("radar/json/locations.json")
            .getFile()).toPath();
        String collect = String.join("\n", Files.readAllLines(path));
        server.enqueue(new MockResponse().setBody(collect));
        server.start();
        Locations locations = new LocationDao()
            .retrieveLocations(s -> server.url(s), "*", "SWT", "SI", "NAVD88");
        assertEquals(1, locations.getLocations().getLocations().size());

    }
}
