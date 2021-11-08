package mil.army.usace.hec.cwms.radar.client.controllers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TestLocationGroupEndpointInput {
    @Test
    void testNullLocationName() {
        assertThrows(NullPointerException.class, () -> new LocationGroupEndpointInput(null));
    }
}
