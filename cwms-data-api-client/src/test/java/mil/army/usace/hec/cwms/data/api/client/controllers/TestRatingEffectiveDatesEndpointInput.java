package mil.army.usace.hec.cwms.data.api.client.controllers;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.RatingEffectiveDatesEndpointInput.END_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.RatingEffectiveDatesEndpointInput.OFFICE_MASK_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.RatingEffectiveDatesEndpointInput.RATING_ID_MASK_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.RatingEffectiveDatesEndpointInput.BEGIN_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.RatingEffectiveDatesEndpointInput.TIMEZONE_QUERY_PARAMETER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

final class TestRatingEffectiveDatesEndpointInput {

    @Test
    void testGetAll() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String ratingId = "BUFF.Stage;Flow.WCDS.Production";
        RatingEffectiveDatesEndpointInput.GetAll input = RatingEffectiveDatesEndpointInput.getAll()
                .officeMask("SWT")
                .ratingIdMask(ratingId)
                .zoneId(ZoneId.of("UTC"))
                .begin(ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant())
                .end(ZonedDateTime.of(2022, 2, 1, 0, 0, 0, 0, ZoneId.of("UTC")).toInstant());

        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_MASK_QUERY_PARAMETER));

        assertEquals(ratingId, mockHttpRequestBuilder.getQueryParameter(RATING_ID_MASK_QUERY_PARAMETER));
        assertEquals("2022-01-01T00:00:00Z", mockHttpRequestBuilder.getQueryParameter(BEGIN_QUERY_PARAMETER));
        assertEquals("2022-02-01T00:00:00Z", mockHttpRequestBuilder.getQueryParameter(END_QUERY_PARAMETER));
        assertEquals("UTC", mockHttpRequestBuilder.getQueryParameter(TIMEZONE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }
}
