package mil.army.usace.hec.cwms.radar.client.controllers;

import java.time.Instant;
import static mil.army.usace.hec.cwms.radar.client.controllers.MeasurementTimeExtentsEndpointInput.GetAll.OFFICE_MASK_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public final class TestMeasurementTimeExtentsEndpointInput {

    @Test
    void testGetAllQueryRequestDefault() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        MeasurementTimeExtentsEndpointInput.GetAll input = MeasurementTimeExtentsEndpointInput.getAll()
                .withOfficeIdMask("SPK");

        input.addInputParameters(mockHttpRequestBuilder);

        assertEquals("SPK", mockHttpRequestBuilder.getQueryParameter(OFFICE_MASK_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }
}
