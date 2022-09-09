package mil.army.usace.hec.cwms.radar.client.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
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

}
