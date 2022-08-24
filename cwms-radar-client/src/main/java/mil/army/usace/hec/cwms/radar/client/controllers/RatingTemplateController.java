package mil.army.usace.hec.cwms.radar.client.controllers;

import java.io.IOException;

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.RatingTemplate;
import mil.army.usace.hec.cwms.radar.client.model.RatingTemplates;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;


public final class RatingTemplateController
{
	private static final String RATING_TEMPLATE = "ratings/template";

	/**
	 * Retrieve Rating Spec.
	 *
	 * @param apiConnectionInfo           - connection info
	 * @param ratingTemplateEndpointInput - rating-template-id and office
	 * @return RatingTemplate for input
	 * @throws IOException - thrown if retrieve failed
	 */
	public RatingTemplate retrieveRatingTemplate(ApiConnectionInfo apiConnectionInfo, RatingTemplateEndpointInput ratingTemplateEndpointInput)
			throws IOException {
		RatingTemplate retVal;
		HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo,
				RATING_TEMPLATE + "/" + ratingTemplateEndpointInput.getTemplateId())
				.addEndpointInput(ratingTemplateEndpointInput)
				.get()
				.withMediaType(ACCEPT_HEADER_V2);
		try(HttpRequestResponse response = executor.execute()) {
			retVal = RadarObjectMapper.mapJsonToObject(response.getBody(), RatingTemplate.class);
		}
		return retVal;
	}

	/**
	 * Retrieve Location Category.
	 *
	 * @param apiConnectionInfo           - connection info
	 * @param ratingTemplateEndpointInput - rating template id mask and office
	 * @return RatingTemplates for input
	 * @throws IOException - thrown if retrieve failed
	 */
	public RatingTemplates retrieveRatingTemplates(ApiConnectionInfo apiConnectionInfo, RatingTemplateEndpointInput ratingTemplateEndpointInput)
			throws IOException {
		RatingTemplates retVal;
		HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, RATING_TEMPLATE)
				.addEndpointInput(ratingTemplateEndpointInput)
				.get()
				.withMediaType(ACCEPT_HEADER_V2);
		try(HttpRequestResponse response = executor.execute())
		{
			retVal = RadarObjectMapper.mapJsonToObject(response.getBody(), RatingTemplates.class);
		}
		return retVal;
	}
}