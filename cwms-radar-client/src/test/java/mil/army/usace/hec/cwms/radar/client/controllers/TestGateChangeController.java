package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.http.client.MockHttpServer;
import mil.army.usace.hec.cwms.radar.client.model.GateChange;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestGateChangeController extends TestController {
    private static final String JSON_FILE = "radar/v1/json/gate_changes.json";
    private static final String OFFICE_ID = "SPK";
    private static final String PROJECT_ID = "BIGH";
    private static final String EXPECTED_CHANGE_PATH = "/" + String.format(GateChangeController.GATE_CHANGE_PATH, OFFICE_ID, PROJECT_ID);
    private static final String EXPECTED_CREATE_CHANGE_PATH = "/" + GateChangeController.GATE_CHANGE_CREATE_PATH;
    private static final Instant START = ZonedDateTime.of(2024, 9, 17, 0, 0, 0, 0, ZoneId.of("UTC"))
                                                      .toInstant();
    private static final Instant END = START.plus(15, ChronoUnit.MINUTES);
    private static final boolean START_INCLUSIVE = false;
    private static final boolean END_INCLUSIVE = false;
    private static final int PAGE_SIZE = 500;
    private static final String UNIT_SYSTEM = "EN";
    private static final boolean OVERRIDE_PROTECTION = true;
    private static final boolean FAIL_IF_EXISTS = false;

    @Test
    void testRetrieveGateChanges() throws Exception {
        String collect = readJsonFile(JSON_FILE);
        Set<GateChange> expectedChanges = RadarObjectMapper.mapJsonToSetOfObjects(collect, GateChange.class);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        GateChangeEndpointInput.GetAll input = GateChangeEndpointInput.getAll(OFFICE_ID, PROJECT_ID, START, END)
                                                                      .endInclusive(END_INCLUSIVE)
                                                                      .startInclusive(START_INCLUSIVE)
                                                                      .pageSize(PAGE_SIZE)
                                                                      .unitSystem(UNIT_SYSTEM);
        Set<GateChange> retrievedChanges = new GateChangeController().retrieveGateChanges(buildConnectionInfo(), input);
        assertFalse(retrievedChanges.isEmpty());
        assertEquals(expectedChanges, retrievedChanges);

        MockHttpServer.RequestWrapper request = mockHttpServer.takeRequest();
        assertTrue(request.getPath().startsWith(EXPECTED_CHANGE_PATH));
        assertEquals("GET", request.getMethod());
    }

    @Test
    void testStoreGateChange() throws Exception {
        String collect = readJsonFile(JSON_FILE);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        Set<GateChange> changes = RadarObjectMapper.mapJsonToSetOfObjects(collect, GateChange.class);
        GateChangeEndpointInput.Post input = GateChangeEndpointInput.post(changes)
                                                                    .failIfExists(FAIL_IF_EXISTS);
        assertDoesNotThrow(() -> new GateChangeController().storeGateChange(buildConnectionInfo(cookieJarSupplier), input));

        MockHttpServer.RequestWrapper request = mockHttpServer.takeRequest();
        assertTrue(request.getPath().startsWith(EXPECTED_CREATE_CHANGE_PATH));
        assertEquals("POST", request.getMethod());
    }

    @Test
    void testDeleteGateChanges() throws Exception {
        String collect = readJsonFile(JSON_FILE);
        mockHttpServer.enqueue(collect);
        mockHttpServer.start();
        GateChangeEndpointInput.Delete input = GateChangeEndpointInput.delete(OFFICE_ID, PROJECT_ID, START, END)
                                                                      .overrideProtection(OVERRIDE_PROTECTION);
        assertDoesNotThrow(() -> new GateChangeController().deleteGateChanges(buildConnectionInfo(), input));

        MockHttpServer.RequestWrapper request = mockHttpServer.takeRequest();
        assertTrue(request.getPath().startsWith(EXPECTED_CHANGE_PATH));
        assertEquals("DELETE", request.getMethod());
    }
}