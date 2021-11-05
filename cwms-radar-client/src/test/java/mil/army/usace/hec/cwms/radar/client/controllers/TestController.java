/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.radar.client.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import mil.army.usace.hec.cwms.htp.client.MockHttpServer;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

abstract class TestController {

    private static final String BASE_URL = "http://localhost:11524";
    static MockHttpServer mockHttpServer;

    @BeforeEach
    void setUp() throws IOException {
        mockHttpServer = MockHttpServer.create();
    }

    @AfterEach
    void tearDown() throws IOException {
        mockHttpServer.shutdown();
    }

    ApiConnectionInfo buildConnectionInfo() {
        String baseUrl = String.format("http://localhost:%s", mockHttpServer.getPort());
        return new ApiConnectionInfo(baseUrl);
    }

    final String readJsonFile(String jsonPath) throws IOException {
        URL resource = getClass().getClassLoader().getResource(jsonPath);
        if (resource == null) {
            throw new IOException("Resource not found: " + jsonPath);
        }
        Path path = new File(resource.getFile()).toPath();
        return String.join("\n", Files.readAllLines(path));
    }
}
