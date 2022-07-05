package mil.army.usace.hec.cwms.radar.client.controllers;

import org.junit.jupiter.api.Test;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RatingSpecEndpointInput.OFFICE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RatingSpecEndpointInput.PAGE_SIZE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RatingSpecEndpointInput.RATING_ID_MASK_QUERY_PARAMETER;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestRatingSpecEndpointInput
{
	@Test
	void testQueryResult(){
		MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
		String ratingId = "BUFF.Stage;Flow.WCDS.Production";
		RatingSpecEndpointInput input = new RatingSpecEndpointInput()
				.officeId("SWT")
				.pageSize(5)
				.ratingIdMask(ratingId)
				;

		input.addInputParameters(mockHttpRequestBuilder);
		assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));

		assertEquals("5", mockHttpRequestBuilder.getQueryParameter(PAGE_SIZE_QUERY_PARAMETER));
		assertEquals(ratingId, mockHttpRequestBuilder.getQueryParameter(RATING_ID_MASK_QUERY_PARAMETER));
		assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
	}
}
