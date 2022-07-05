package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.RatingSpec;
import mil.army.usace.hec.cwms.radar.client.model.RatingSpecs;


public final class RatingSpecController {
    private static final String RATING_SPEC = "ratings/spec";

    /**
     * Retrieve Rating Spec.
     *
     * @param apiConnectionInfo       - connection info
     * @param ratingSpecEndpointInput - rating-id and office
     * @return RatingSpec
     * @throws IOException - thrown if retrieve failed
     */
    public RatingSpec retrieveRatingSpec(ApiConnectionInfo apiConnectionInfo,
                                         RatingSpecEndpointInput ratingSpecEndpointInput) throws IOException {
        RatingSpec retVal;
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo,
                RATING_SPEC + "/" + ratingSpecEndpointInput.getRatingId())
                .addEndpointInput(ratingSpecEndpointInput)
                .get()
                .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            retVal = RadarObjectMapper.mapJsonToObject(response.getBody(), RatingSpec.class);
        }
        return retVal;
    }

    /**
     * Retrieve Location Category.
     *
     * @param apiConnectionInfo       - connection info
     * @param ratingSpecEndpointInput - rating-id and office
     * @return RatingSpecs for office id
     * @throws IOException - thrown if retrieve failed
     */
    public RatingSpecs retrieveRatingSpecs(ApiConnectionInfo apiConnectionInfo,
                                           RatingSpecEndpointInput ratingSpecEndpointInput) throws IOException {
        RatingSpecs retVal;
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, RATING_SPEC)
                .addEndpointInput(ratingSpecEndpointInput)
                .get()
                .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            retVal = RadarObjectMapper.mapJsonToObject(response.getBody(), RatingSpecs.class);
        }
        return retVal;
    }
}