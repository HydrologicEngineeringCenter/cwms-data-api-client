/*
 * MIT License
 *
 * Copyright (c) 2023 Hydrologic Engineering Center
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

package mil.army.usace.hec.cwms.data.api.client.controllers;

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_XML_HEADER_V2;
import static mil.army.usace.hec.cwms.data.api.client.controllers.RatingEffectiveDatesEndpointInput.EFFECTIVE_DATES_ENDPOINT;
import mil.army.usace.hec.cwms.data.api.client.model.RatingEffectiveDatesMap;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.data.api.client.model.RatingMetadataList;

import java.io.IOException;

public final class RatingController {

    private static final String RATINGS = "ratings";
    private static final String RATINGS_METADATA = "ratings/metadata";

    /**
     * Retrieve Rating set XML.
     *
     * @param apiConnectionInfo   - connection info
     * @param ratingEndpointInput - rating-id and office
     * @return RatingSpec
     * @throws IOException - thrown if retrieve failed
     */
    public String retrieveRatingXml(ApiConnectionInfo apiConnectionInfo, RatingEndpointInput.GetOne ratingEndpointInput) throws IOException {
        HttpRequestExecutor executor =
            new HttpRequestBuilderImpl(apiConnectionInfo, RATINGS + "/" + ratingEndpointInput.getRatingId())
                .addEndpointInput(ratingEndpointInput)
                .get()
                .withMediaType(ACCEPT_XML_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            return response.getBody();
        }
    }

    public RatingEffectiveDatesMap retrieveRatingEffectiveDates(ApiConnectionInfo apiConnectionInfo, RatingEffectiveDatesEndpointInput.GetAll input) throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, RATINGS + "/" + EFFECTIVE_DATES_ENDPOINT)
            .addEndpointInput(input)
            .get()
            .withMediaType(ACCEPT_XML_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            String body = response.getBody();
            return RadarObjectMapper.mapJsonToObject(body, RatingEffectiveDatesMap.class);
        }
    }

    public void storeRatingSetXml(ApiConnectionInfo apiConnectionInfo, RatingEndpointInput.Post input) throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo, RATINGS)
                .addEndpointInput(input)
                .post()
                .withBody(input.ratingSetXml())
                .withMediaType(ACCEPT_XML_HEADER_V2)
                .execute()
                .close();
    }

    public void updateRatingSetXml(ApiConnectionInfo apiConnectionInfo, RatingEndpointInput.Put input) throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo, RATINGS)
                .addEndpointInput(input)
                .put()
                .withBody(input.ratingSetXml())
                .withMediaType(ACCEPT_XML_HEADER_V2)
                .execute()
                .close();
    }

    public void deleteRatings(ApiConnectionInfo apiConnectionInfo, RatingEndpointInput.Delete ratingEndpointInput) throws IOException {

        new HttpRequestBuilderImpl(apiConnectionInfo, RATINGS + "/" + ratingEndpointInput.getRatingId())
                .addEndpointInput(ratingEndpointInput)
                .delete()
                .withMediaType(ACCEPT_XML_HEADER_V2)
                .execute()
                .close();
    }

    public RatingMetadataList retrieveRatingMetadata(ApiConnectionInfo apiConnectionInfo, RatingMetadataEndpointInput input) throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, RATINGS_METADATA)
            .addEndpointInput(input)
            .get()
            .withMediaType(ACCEPT_XML_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            String body = response.getBody();
            return RadarObjectMapper.mapJsonToObject(body, RatingMetadataList.class);
        }
    }
}
