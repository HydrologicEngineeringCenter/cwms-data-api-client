package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import org.junit.jupiter.api.Test;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RatingTemplateEndpointInput.DELETE_METHOD_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RatingTemplateEndpointInput.OFFICE_QUERY_PARAMETER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestProjectEndpointInput
{

	@Test
	void testGetOneQueryRequest() {
		MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
		String projectId = "project-id";
		String officeId = "SWT";
		ProjectEndpointInput.GetOne input = ProjectEndpointInput.getOne(projectId, officeId);
		input.addInputParameters(mockHttpRequestBuilder);
		assertEquals(officeId, mockHttpRequestBuilder.getQueryParameter(ProjectEndpointInput.GetOne.OFFICE_QUERY_PARAMETER));
		assertEquals(projectId, input.projectId());

		assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
	}





	@Test
	void testNullGetOne() {
		assertThrows(NullPointerException.class, () -> ProjectEndpointInput.getOne(null, "SWT"));
		assertThrows(NullPointerException.class, () -> ProjectEndpointInput.getOne("id", null));
	}


	@Test
	void testDelete() {
		ProjectEndpointInput.Delete input = ProjectEndpointInput.delete("id", "SWT", DeleteMethod.ALL);
		MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
		input.addInputParameters(mockHttpRequestBuilder);
		assertEquals("SWT", mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
		assertEquals("DELETE_ALL", mockHttpRequestBuilder.getQueryParameter(DELETE_METHOD_QUERY_PARAMETER));
		assertEquals(ACCEPT_HEADER_V2, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));

	}

}
