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
