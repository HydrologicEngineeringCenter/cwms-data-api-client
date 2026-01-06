/*
 * Copyright (c) 2025. Hydrologic Engineering Center (HEC).
 * United States Army Corps of Engineers
 * All Rights Reserved. HEC PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.data.api.client.controllers;

import mil.army.usace.hec.cwms.data.api.client.model.CwmsIdLocationKind;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestLocationKindController extends TestController {
    private static final String TEST_ALL_LOCATION_KIND_MOCK_DATA = "radar/v1/json/locations_with_kinds.json";

    @Test
    void testOptionalLocationKindController() throws Exception {
        String collect = readJsonFile(TEST_ALL_LOCATION_KIND_MOCK_DATA);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        LocationKindEndpointInput.GetAll input = new LocationKindEndpointInput.GetAll();
        List<CwmsIdLocationKind> kinds = new LocationKindController().retrieveLocationsWithKind(buildConnectionInfo(), input);
        assertNotNull(kinds);
    }
}
