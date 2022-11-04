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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.radar.client.model.AbstractRatingMetadata;
import mil.army.usace.hec.cwms.radar.client.model.RatingMetadata;
import mil.army.usace.hec.cwms.radar.client.model.RatingMetadataList;
import mil.army.usace.hec.cwms.radar.client.model.RatingSpec;
import mil.army.usace.hec.cwms.radar.client.model.UsgsStreamRating;
import org.junit.jupiter.api.Test;

class TestRatingController extends TestController {

	@Test
	void testRetrieveRatingSpecs() throws IOException
	{
		String collect = readJsonFile("radar/v2/xml/rating.xml");
		mockHttpServer.enqueue(collect);
		mockHttpServer.start();
		RatingController controller = new RatingController();

		RatingEndpointInput input = new RatingEndpointInput("BIGH.Elev;Stor.Linear.Production")
			.officeId("SWT");

		ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
		String xml = controller.retrieveRatingXml(apiConnectionInfo, input);
		assertNotNull(xml);
	}

	@Test
	public void testRatingsMetadata() throws Exception {
		String collect = readJsonFile("radar/v2/json/rating_metadata.json");
		mockHttpServer.enqueue(collect);
		mockHttpServer.start();
		RatingController ratingController = new RatingController();
		ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
		RatingMetadataEndpointInput ratingMetadataEndpointInput = new RatingMetadataEndpointInput();
		RatingMetadataList ratingMetadataList = ratingController.retrieveRatingMetadata(apiConnectionInfo, ratingMetadataEndpointInput);
		assertNotNull(ratingMetadataList.getPage());
		assertNotNull(ratingMetadataList.getNextPage());
		List<RatingMetadata> ratingMetadata = ratingMetadataList.getRatingMetadata();
		assertFalse(ratingMetadata.isEmpty());
		RatingMetadata ratingMetadatum = ratingMetadata.get(0);
		RatingSpec ratingSpec = ratingMetadatum.getRatingSpec();
		assertNotNull(ratingSpec);
		List<AbstractRatingMetadata> ratings = ratingMetadatum.getRatings();
		assertFalse(ratings.isEmpty());
		AbstractRatingMetadata abstractRatingMetadata = ratings.get(0);
		assertNotEquals(AbstractRatingMetadata.class, abstractRatingMetadata.getClass());
		assertEquals(UsgsStreamRating.class, abstractRatingMetadata.getClass());
		UsgsStreamRating usgsStreamRating = (UsgsStreamRating) abstractRatingMetadata;
		assertEquals("usgs", usgsStreamRating.getRatingType());
	}
}
