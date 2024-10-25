package mil.army.usace.hec.cwms.radar.client.controllers;

import java.util.List;

import mil.army.usace.hec.cwms.http.client.MockHttpServer;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.Lock;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestLockController extends TestController {

	@Test
	void testRetrieveAll() throws Exception {
		String collect = readJsonFile("radar/v1/json/locks.json");
		mockHttpServer.enqueue(collect);
		mockHttpServer.start();
		LockEndpointInput.GetAll input = LockEndpointInput.getAll("LRL", "PROJECT").unit("ft");
		List<Lock> values = new LockController().retrieveLocks(buildConnectionInfo(), input);
		assertFalse(values.isEmpty());
		assertEquals(2, values.size());
		Lock value = values.get(0);
		assertEquals("LRD", value.getProjectId().getOfficeId());
		assertEquals("LRL", value.getLocation().getOfficeId());
		assertEquals("PROJECT", value.getProjectId().getName());
		assertEquals("ft3", value.getVolumeUnits());
		assertEquals("ft", value.getUnits());
	}

	@Test
	void testRetrieveOne() throws Exception {
		String collect = readJsonFile("radar/v1/json/lock.json");
		mockHttpServer.enqueue(collect);
		mockHttpServer.start();
		LockEndpointInput.GetOne input = LockEndpointInput.getOne("LRL", "TEST_LOCATION2").unit("ft");
		Lock value = new LockController().retrieveLock(buildConnectionInfo(), input);
		assertNotNull(value);
		assertEquals("LRD", value.getProjectId().getOfficeId());
		assertEquals("PROJECT", value.getProjectId().getName());
		assertEquals("LRL", value.getLocation().getOfficeId());
		assertEquals("ft3", value.getVolumeUnits());
		assertEquals("ft", value.getUnits());
	}

	@Test
	void testStore() throws Exception {
		String collect = readJsonFile("radar/v1/json/lock.json");
		mockHttpServer.enqueue(collect);
		mockHttpServer.start();
		Lock lock = RadarObjectMapper.mapJsonToObject(collect, Lock.class);
		LockController controller = new LockController();
		LockEndpointInput.Post input = LockEndpointInput.post(lock).failIfExists(true);
		assertDoesNotThrow(() -> controller.storeLock(buildConnectionInfo(cookieJarSupplier), input));
	}

	@Test
	void testRename() throws Exception {
		String collect = readJsonFile("radar/v1/json/lock.json");
		mockHttpServer.enqueue(collect);
		mockHttpServer.start();
		LockController controller = new LockController();
		LockEndpointInput.Patch input = LockEndpointInput.patch("LRL", "TEST_LOCATION2", "TEST_LOCATION3");
		assertDoesNotThrow(() -> controller.renameLock(buildConnectionInfo(cookieJarSupplier), input));
		MockHttpServer.RequestWrapper request = mockHttpServer.takeRequest();
		assertEquals("PATCH", request.getMethod());
		assertEquals("/projects/lock/TEST_LOCATION2?name=TEST_LOCATION3&office=LRL", request.getPath());
	}

	@Test
	void testDelete() throws Exception {
		String collect = readJsonFile("radar/v1/json/lock.json");
		mockHttpServer.enqueue(collect);
		mockHttpServer.enqueue(collect);
		mockHttpServer.start();
		Lock lock = RadarObjectMapper.mapJsonToObject(collect, Lock.class);
		LockController controller = new LockController();
		controller.storeLock(buildConnectionInfo(cookieJarSupplier), LockEndpointInput.post(lock).failIfExists(true));
		LockEndpointInput.Delete input = LockEndpointInput.delete("LRL", "TEST_LOCATION2").method(DeleteMethod.ALL);
		assertDoesNotThrow(() -> controller.deleteLock(buildConnectionInfo(cookieJarSupplier), input));
	}
}
