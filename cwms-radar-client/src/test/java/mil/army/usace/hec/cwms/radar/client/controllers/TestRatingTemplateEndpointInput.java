package mil.army.usace.hec.cwms.radar.client.controllers;

import org.junit.jupiter.api.Test;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RatingTemplateEndpointInput.OFFICE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RatingTemplateEndpointInput.PAGE_SIZE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RatingTemplateEndpointInput.TEMPLATE_ID_MASK_QUERY_PARAMETER;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestRatingTemplateEndpointInput
{
	@Test
	void testQueryResult(){
		MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
		String templateId = "Stage;Stage.USGS-CORR";
		RatingTemplateEndpointInput input = new RatingTemplateEndpointInput()
				.officeId("SWT")
				.pageSize(5)
				.templateIdMask(templateId);

		input.addInputParameters(mockHttpRequestBuilder);
		assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));

		assertEquals("5", mockHttpRequestBuilder.getQueryParameter(PAGE_SIZE_QUERY_PARAMETER));
		assertEquals(templateId, mockHttpRequestBuilder.getQueryParameter(TEMPLATE_ID_MASK_QUERY_PARAMETER));
		assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
	}
}
