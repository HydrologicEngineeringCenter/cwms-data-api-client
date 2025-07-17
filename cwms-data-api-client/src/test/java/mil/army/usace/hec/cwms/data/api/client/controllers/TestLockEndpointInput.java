package mil.army.usace.hec.cwms.data.api.client.controllers;

import java.io.IOException;

import mil.army.usace.hec.cwms.data.api.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.data.api.client.model.Lock;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class TestLockEndpointInput {
	@Test
	void testGetAllQueryRequest() {
		MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
		LockEndpointInput.GetAll input = LockEndpointInput.getAll("LRL", "PROJECT");
		input.addInputParameters(mockHttpRequestBuilder);
		assertEquals("LRL", mockHttpRequestBuilder.getQueryParameter(LockEndpointInput.GetAll.OFFICE_QUERY_PARAMETER));
		assertEquals("PROJECT", mockHttpRequestBuilder.getQueryParameter(LockEndpointInput.GetAll.PROJECT_ID_QUERY_PARAMETER));
		assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
	}

	@Test
	void testGetOneQueryRequest() {
		MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
		String lockId = "TEST_LOCATION2";
		String office = "LRL";
		LockEndpointInput.GetOne input = LockEndpointInput.getOne(office, lockId).unit("SI");
		input.addInputParameters(mockHttpRequestBuilder);
		assertEquals(lockId, input.lockName());
		assertEquals("SI", mockHttpRequestBuilder.getQueryParameter(LockEndpointInput.GetOne.UNIT_QUERY_PARAMETER));
		assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
		assertEquals(office, mockHttpRequestBuilder.getQueryParameter(LockEndpointInput.GetOne.OFFICE_QUERY_PARAMETER));
	}

	@Test
	void testPostQueryRequest() throws IOException
	{
		MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
		String collect = readJsonFile("radar/v1/json/lock.json");
		Lock lock = RadarObjectMapper.mapJsonToObject(collect, Lock.class);
		LockEndpointInput.Post input = LockEndpointInput.post(lock).failIfExists(true);
		input.addInputParameters(mockHttpRequestBuilder);
		assertEquals(lock, input.lock());
		assertTrue(Boolean.parseBoolean(mockHttpRequestBuilder.getQueryParameter(LockEndpointInput.Post.FAIL_IF_EXISTS_QUERY_PARAMETER)));
		assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
	}

	@Test
	void testDeleteQueryRequest() {
		MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
		String lockId = "TEST_LOCATION2";
		String office = "LRL";
		LockEndpointInput.Delete input = LockEndpointInput.delete(office, lockId).method(DeleteMethod.ALL);
		input.addInputParameters(mockHttpRequestBuilder);
		assertEquals(lockId, input.lockName());
		assertEquals(DeleteMethod.ALL.toString(), mockHttpRequestBuilder.getQueryParameter(LockEndpointInput.Delete.METHOD_QUERY_PARAMETER));
		assertEquals(office, mockHttpRequestBuilder.getQueryParameter(LockEndpointInput.Delete.OFFICE_QUERY_PARAMETER));
	}

	@Test
	void testPatchQueryRequest() {
		MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
		String oldLockId = "TEST_LOCATION2";
		String newLockId = "NEW_TEST_LOCATION2";
		String office = "LRL";
		LockEndpointInput.Patch input = LockEndpointInput.patch(office, oldLockId, newLockId);
		input.addInputParameters(mockHttpRequestBuilder);
		assertEquals(oldLockId, input.oldLockName());
		assertEquals(newLockId, mockHttpRequestBuilder.getQueryParameter(LockEndpointInput.Patch.NAME_QUERY_PARAMETER));
		assertEquals(office, mockHttpRequestBuilder.getQueryParameter(LockEndpointInput.Patch.OFFICE_QUERY_PARAMETER));
	}
}
