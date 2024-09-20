package mil.army.usace.hec.cwms.radar.client.controllers;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestGateChangeEndpointInput {

    private static final String OFFICE_ID = "SPK";
    private static final String PROJECT_ID = "BIGH";
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
    void testGetAllQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        GateChangeEndpointInput.GetAll input = GateChangeEndpointInput.getAll(OFFICE_ID, PROJECT_ID, START, END)
                                                                      .endInclusive(END_INCLUSIVE)
                                                                      .startInclusive(START_INCLUSIVE)
                                                                      .pageSize(PAGE_SIZE)
                                                                      .unitSystem(UNIT_SYSTEM);
        input.addInputParameters(mockHttpRequestBuilder);
        assertAll(() -> assertEquals(Boolean.toString(START_INCLUSIVE), mockHttpRequestBuilder.getQueryParameter(GateChangeEndpointInput.GetAll.START_INCLUSIVE)),
                () -> assertEquals(Boolean.toString(END_INCLUSIVE), mockHttpRequestBuilder.getQueryParameter(GateChangeEndpointInput.GetAll.END_INCLUSIVE)),
                () -> assertEquals(START.toString(), mockHttpRequestBuilder.getQueryParameter(GateChangeEndpointInput.GetAll.BEGIN)),
                () -> assertEquals(END.toString(), mockHttpRequestBuilder.getQueryParameter(GateChangeEndpointInput.GetAll.END)),
                () -> assertEquals(Integer.toString(PAGE_SIZE), mockHttpRequestBuilder.getQueryParameter(GateChangeEndpointInput.GetAll.PAGE_SIZE)),
                () -> assertEquals(UNIT_SYSTEM, mockHttpRequestBuilder.getQueryParameter(GateChangeEndpointInput.GetAll.UNIT_SYSTEM)));
    }

    @Test
    void testDefaultGetAllQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        GateChangeEndpointInput.GetAll input = GateChangeEndpointInput.getAll(OFFICE_ID, PROJECT_ID, START, END);
        input.addInputParameters(mockHttpRequestBuilder);
        assertAll(() -> assertEquals(Boolean.toString(GateChangeEndpointInput.GetAll.DEFAULT_START_INCLUSIVE), mockHttpRequestBuilder.getQueryParameter(GateChangeEndpointInput.GetAll.START_INCLUSIVE)),
                () -> assertEquals(Boolean.toString(GateChangeEndpointInput.GetAll.DEFAULT_END_INCLUSIVE), mockHttpRequestBuilder.getQueryParameter(GateChangeEndpointInput.GetAll.END_INCLUSIVE)),
                () -> assertEquals(Integer.toString(GateChangeEndpointInput.GetAll.DEFAULT_PAGE_SIZE), mockHttpRequestBuilder.getQueryParameter(GateChangeEndpointInput.GetAll.PAGE_SIZE)),
                () -> assertEquals(GateChangeEndpointInput.GetAll.DEFAULT_UNIT_SYSTEM, mockHttpRequestBuilder.getQueryParameter(GateChangeEndpointInput.GetAll.UNIT_SYSTEM)));
    }

    @Test
    void testGetAllNulls() {
        assertAll(() -> assertThrows(NullPointerException.class, () -> GateChangeEndpointInput.getAll(OFFICE_ID, PROJECT_ID, START, null), "Missing End Date"),
                () -> assertThrows(NullPointerException.class, () -> GateChangeEndpointInput.getAll(OFFICE_ID, PROJECT_ID, null, END), "Missing Start Date"),
                () -> assertThrows(NullPointerException.class, () -> GateChangeEndpointInput.getAll(OFFICE_ID, null, START, END), "Missing Project ID"),
                () -> assertThrows(NullPointerException.class, () -> GateChangeEndpointInput.getAll(null, PROJECT_ID, START, END), "Missing Office ID"));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        GateChangeEndpointInput.Delete input = GateChangeEndpointInput.delete(OFFICE_ID, PROJECT_ID, START, END)
                                                                      .overrideProtection(OVERRIDE_PROTECTION);
        input.addInputParameters(mockHttpRequestBuilder);
        assertAll(() -> assertEquals(Boolean.toString(OVERRIDE_PROTECTION), mockHttpRequestBuilder.getQueryParameter(GateChangeEndpointInput.Delete.OVERRIDE_PROTECTION)));
    }

    @Test
    void testDefaultDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        GateChangeEndpointInput.Delete input = GateChangeEndpointInput.delete(OFFICE_ID, PROJECT_ID, START, END);
        input.addInputParameters(mockHttpRequestBuilder);
        assertAll(() -> assertEquals(Boolean.toString(GateChangeEndpointInput.Delete.DEFAULT_OVERRIDE_PROTECTION), mockHttpRequestBuilder.getQueryParameter(GateChangeEndpointInput.Delete.OVERRIDE_PROTECTION)));
    }

    @Test
    void testDeleteNulls() {
        assertAll(() -> assertThrows(NullPointerException.class, () -> GateChangeEndpointInput.delete(OFFICE_ID, PROJECT_ID, START, null), "Missing End Date"),
                () -> assertThrows(NullPointerException.class, () -> GateChangeEndpointInput.delete(OFFICE_ID, PROJECT_ID, null, END), "Missing Start Date"),
                () -> assertThrows(NullPointerException.class, () -> GateChangeEndpointInput.delete(OFFICE_ID, null, START, END), "Missing Project ID"),
                () -> assertThrows(NullPointerException.class, () -> GateChangeEndpointInput.delete(null, PROJECT_ID, START, END), "Missing Office ID"));
    }

    @Test
    void testPostQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();

        GateChangeEndpointInput.Post input = GateChangeEndpointInput.post(new HashSet<>(0))
                                                                    .failIfExists(FAIL_IF_EXISTS);
        input.addInputParameters(mockHttpRequestBuilder);
        assertAll(() -> assertEquals(Boolean.toString(FAIL_IF_EXISTS), mockHttpRequestBuilder.getQueryParameter(GateChangeEndpointInput.Post.FAIL_IF_EXISTS)));
    }

    @Test
    void testDefaultPostQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();

        GateChangeEndpointInput.Post input = GateChangeEndpointInput.post(new HashSet<>(0));
        input.addInputParameters(mockHttpRequestBuilder);
        assertAll(() -> assertEquals(Boolean.toString(GateChangeEndpointInput.Post.DEFAULT_FAIL_IF_EXISTS), mockHttpRequestBuilder.getQueryParameter(GateChangeEndpointInput.Post.FAIL_IF_EXISTS)));
    }

    @Test
    void testPostNulls() {
        assertThrows(NullPointerException.class, () -> GateChangeEndpointInput.post(null), "Missing Changes");
    }
}