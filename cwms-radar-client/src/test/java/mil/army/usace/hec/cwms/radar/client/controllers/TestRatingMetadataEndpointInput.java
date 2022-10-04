package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RatingMetadataEndpointInput.END_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RatingMetadataEndpointInput.OFFICE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RatingMetadataEndpointInput.PAGE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RatingMetadataEndpointInput.PAGE_SIZE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RatingMetadataEndpointInput.RATING_ID_MASK_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RatingMetadataEndpointInput.START_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RatingMetadataEndpointInput.TIMEZONE_QUERY_PARAMETER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;

class TestRatingMetadataEndpointInput {
    @Test
    void testQueryResult() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String ratingId = "BUFF.Stage;Flow.WCDS.Production";
        RatingMetadataEndpointInput input = new RatingMetadataEndpointInput()
            .office("SWT")
            .start(ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant())
            .end(ZonedDateTime.of(2022, 2, 1, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant())
            .pageSize(5)
            .ratingIdMask(ratingId);

        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));

        assertEquals("5", mockHttpRequestBuilder.getQueryParameter(PAGE_SIZE_QUERY_PARAMETER));
        assertEquals(ratingId, mockHttpRequestBuilder.getQueryParameter(RATING_ID_MASK_QUERY_PARAMETER));
        assertEquals("2022-01-01T00:00:00Z", mockHttpRequestBuilder.getQueryParameter(START_QUERY_PARAMETER));
        assertEquals("2022-02-01T00:00:00Z", mockHttpRequestBuilder.getQueryParameter(END_QUERY_PARAMETER));
        assertEquals("UTC", mockHttpRequestBuilder.getQueryParameter(TIMEZONE_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(PAGE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }
}
