/*
 * Copyright (c) 2022
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_XML_HEADER_V2;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;

public final class RatingController {

    private static final String RATINGS = "ratings";

    /**
     * Retrieve Rating set XML.
     *
     * @param apiConnectionInfo       - connection info
     * @param ratingEndpointInput - rating-id and office
     * @return RatingSpec
     * @throws IOException - thrown if retrieve failed
     */
    public String retrieveRatingXml(ApiConnectionInfo apiConnectionInfo,
                                         RatingEndpointInput ratingEndpointInput) throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo,
            RATINGS + "/" + ratingEndpointInput.getRatingId())
            .addEndpointInput(ratingEndpointInput)
            .get()
            .withMediaType(ACCEPT_XML_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            return response.getBody();
        }
    }
}
