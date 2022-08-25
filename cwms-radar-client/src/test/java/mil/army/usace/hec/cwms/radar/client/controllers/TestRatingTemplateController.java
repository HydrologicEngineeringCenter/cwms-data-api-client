package mil.army.usace.hec.cwms.radar.client.controllers;

import java.io.IOException;
import java.util.List;

import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.radar.client.model.ParameterSpec;
import mil.army.usace.hec.cwms.radar.client.model.RatingTemplate;
import mil.army.usace.hec.cwms.radar.client.model.RatingTemplates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestRatingTemplateController extends TestController {

	@Test
	void testRetrieveRatingTemplates() throws IOException {
		String collect = readJsonFile("radar/v2/json/rating_templates.json");
		mockHttpServer.enqueue(collect);
		mockHttpServer.start();
		RatingTemplateController controller = new RatingTemplateController();

		RatingTemplateEndpointInput input = new RatingTemplateEndpointInput()
				.pageSize(2)
				.officeId("LRL");

		ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
		RatingTemplates templates = controller.retrieveRatingTemplates(apiConnectionInfo, input);
		assertNotNull(templates);
		assertEquals(2, templates.getPageSize());
		assertFalse(templates.getTemplates().isEmpty());
		assertNotNull(templates.getPage());
		assertNotNull(templates.getNextPage());
		RatingTemplate ratingTemplate = templates.getTemplates().get(0);
		assertEquals("Stage;Stage.USGS-CORR", ratingTemplate.getId());
		assertEquals("USGS-CORR", ratingTemplate.getVersion());
		assertEquals("Stream Stage Correction Rating", ratingTemplate.getDescription());
		assertEquals("LRL", ratingTemplate.getOfficeId());
		assertFalse(ratingTemplate.getRatingIds().isEmpty());
		assertEquals("Stage", ratingTemplate.getDependentParameter());
		List<ParameterSpec> independentParameterSpecs = ratingTemplate.getIndependentParameterSpecs();
		assertEquals(1, independentParameterSpecs.size());
		ParameterSpec parameterSpec = independentParameterSpecs.get(0);
		assertEquals("Stage", parameterSpec.getParameter());
		assertEquals("ERROR", parameterSpec.getInRangeMethod());
		assertEquals("ERROR", parameterSpec.getOutRangeHighMethod());
		assertEquals("LINEAR", parameterSpec.getOutRangeLowMethod());

	}

	@Test
	void testRetrieveRatingTemplate() throws IOException {
		String collect = readJsonFile("radar/v2/json/rating_template.json");
		mockHttpServer.enqueue(collect);
		mockHttpServer.start();
		RatingTemplateController controller = new RatingTemplateController();

		RatingTemplateEndpointInput input = new RatingTemplateEndpointInput()
				.officeId("LRL")
				.templateId("Stage;Stage.USGS-CORR");

		ApiConnectionInfo apiConnectionInfo = buildConnectionInfo();
		RatingTemplate ratingTemplate = controller.retrieveRatingTemplate(apiConnectionInfo, input);
		assertNotNull(ratingTemplate);
		assertEquals("Stage;Stage.USGS-CORR", ratingTemplate.getId());
		assertEquals("USGS-CORR", ratingTemplate.getVersion());
		assertEquals("Stream Stage Correction Rating", ratingTemplate.getDescription());
		assertEquals("LRL", ratingTemplate.getOfficeId());
		assertFalse(ratingTemplate.getRatingIds().isEmpty());
		assertEquals("Stage", ratingTemplate.getDependentParameter());
		List<ParameterSpec> independentParameterSpecs = ratingTemplate.getIndependentParameterSpecs();
		assertEquals(1, independentParameterSpecs.size());
		ParameterSpec parameterSpec = independentParameterSpecs.get(0);
		assertEquals("Stage", parameterSpec.getParameter());
		assertEquals("ERROR", parameterSpec.getInRangeMethod());
		assertEquals("ERROR", parameterSpec.getOutRangeHighMethod());
		assertEquals("LINEAR", parameterSpec.getOutRangeLowMethod());
	}

}
