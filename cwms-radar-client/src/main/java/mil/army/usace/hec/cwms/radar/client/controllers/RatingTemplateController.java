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

package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.RatingTemplate;
import mil.army.usace.hec.cwms.radar.client.model.RatingTemplates;

import java.io.IOException;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_XML_HEADER_V2;


public final class RatingTemplateController {
    private static final String RATING_TEMPLATE = "ratings/template";

    /**
     * Retrieve Rating Template.
     *
     * @param apiConnectionInfo           - connection info
     * @param ratingTemplateEndpointInput - rating-template-id and office
     * @return RatingTemplate for input
     * @throws IOException - thrown if retrieve failed
     */
    public RatingTemplate retrieveRatingTemplate(ApiConnectionInfo apiConnectionInfo, RatingTemplateEndpointInput.GetOne ratingTemplateEndpointInput)
            throws IOException {
        RatingTemplate retVal;
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo,
                RATING_TEMPLATE + "/" + ratingTemplateEndpointInput.templateId())
                .addEndpointInput(ratingTemplateEndpointInput)
                .get()
                .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            retVal = RadarObjectMapper.mapJsonToObject(response.getBody(), RatingTemplate.class);
        }
        return retVal;
    }

    /**
     * Retrieve Rating Templates.
     *
     * @param apiConnectionInfo - connection info
     * @param input             - rating template id mask and office
     * @return RatingTemplates for input
     * @throws IOException - thrown if retrieve failed
     */
    public RatingTemplates retrieveRatingTemplates(ApiConnectionInfo apiConnectionInfo, RatingTemplateEndpointInput.GetAll input)
            throws IOException {
        RatingTemplates retVal;
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, RATING_TEMPLATE)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            retVal = RadarObjectMapper.mapJsonToObject(response.getBody(), RatingTemplates.class);
        }
        return retVal;
    }

    /**
     * Store Rating Template.
     *
     * @param apiConnectionInfo - connection info
     * @param input             - rating-template-id and office
     * @throws IOException - thrown if retrieve failed
     */
    public void storeRatingTemplate(ApiConnectionInfo apiConnectionInfo, RatingTemplateEndpointInput.Post input)
            throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo, RATING_TEMPLATE)
                .addEndpointInput(input)
                .post()
                .withBody(input.templateXml())
                .withMediaType(ACCEPT_XML_HEADER_V2)
                .execute()
                .close();
    }

    /**
     * Delete Rating Template.
     *
     * @param apiConnectionInfo - connection info
     * @param input             - rating-template-id and office
     * @throws IOException - thrown if retrieve failed
     */
    public void deleteRatingTemplate(ApiConnectionInfo apiConnectionInfo, RatingTemplateEndpointInput.Delete input)
            throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo,
                RATING_TEMPLATE + "/" + input.templateId())
                .addEndpointInput(input)
                .delete()
                .withMediaType(ACCEPT_HEADER_V2)
                .execute()
                .close();
    }
}