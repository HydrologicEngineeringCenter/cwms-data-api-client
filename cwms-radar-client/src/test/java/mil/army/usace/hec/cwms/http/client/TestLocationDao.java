/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client;

import java.io.IOException;
import mil.army.usace.hec.cwms.radar.client.Locations;
import org.junit.jupiter.api.Test;

class TestLocationDao {

    private static final String BASE_URL = "http://localhost:11524";

    @Test
    void testAllLocationsGet() throws IOException {
        Locations locations = new LocationDao()
            .retrieveLocations(BASE_URL, "*", "SWT", "SI", "NAVD88");

    }
}
