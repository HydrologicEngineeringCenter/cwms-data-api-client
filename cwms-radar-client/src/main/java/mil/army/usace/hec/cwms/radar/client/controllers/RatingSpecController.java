/*
 * MIT License
 *
 * Copyright (c) 2022 Hydrologic Engineering Center
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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