package mil.army.usace.hec.cwms.radar.client.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TestLocationEndpointInput {
    @Test
    void testNullLocationName() {
        assertThrows(NullPointerException.class, () -> new LocationEndPointInput(null));
    }
}
