package mil.army.usace.hec.cwms.radar.client.controllers;

import java.io.IOException;

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.radar.client.model.RatingSpec;
import mil.army.usace.hec.cwms.radar.client.model.RatingSpecs;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestRatingSpecController extends TestController {

	@Test
	void testRetrieveRatingSpecs() throws IOException
	{
		String collect = readJsonFile("radar/v2/json/rating_specs.json");
		mockHttpServer.enqueue(collect);
		mockHttpServer.start();
		RatingSpecController controller = new RatingSpecController();

		RatingSpecEndpointInput input = new RatingSpecEndpointInput()
			.officeId("SWT")
			.ratingIdMask("BUFF.Stage;Flow.WCDS.Production")
				;

		ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
		RatingSpecs specs = controller.retrieveRatingSpecs(apiConnectionInfo, input);
		assertNotNull(specs);
	}

	@Test
	void testRetrieveRatingSpec() throws IOException
	{
		String collect = readJsonFile("radar/v2/json/rating_spec.json");
		mockHttpServer.enqueue(collect);
		mockHttpServer.start();
		RatingSpecController controller = new RatingSpecController();

		RatingSpecEndpointInput input = new RatingSpecEndpointInput()
				.officeId("SWT")
				.ratingId("BUFF.Stage;Flow.WCDS.Production")
				;

		ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
		RatingSpec spec = controller.retrieveRatingSpec(apiConnectionInfo, input);
		assertNotNull(spec);
	}

}
