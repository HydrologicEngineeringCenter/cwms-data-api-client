package mil.army.usace.hec.cwms.data.api.client.controllers;

import mil.army.usace.hec.cwms.data.api.client.controllers.WaterContractEndpointInput.GetAll;
import mil.army.usace.hec.cwms.data.api.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.data.api.client.model.WaterUserContract;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.TestController.readJsonFile;
import static org.junit.jupiter.api.Assertions.*;

class TestWaterContractEndpointInput {

    @Test
    void testGetAllQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String office = "SPK";
        String projectId = "PROJECT";
        String waterUser = "user";
        GetAll input = WaterContractEndpointInput.getAll(office, projectId, waterUser);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(office, input.officeId());
        assertEquals(projectId, input.projectId());
        assertEquals(waterUser, input.waterUser());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetOneQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String contractId = "CONTRACT";
        String office = "SPK";
        String projectId = "PROJECT";
        String waterUser = "user";
        WaterContractEndpointInput.GetOne input = WaterContractEndpointInput.getOne(office, projectId, waterUser, contractId);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(contractId, input.contractName());
        assertEquals(office, input.officeId());
        assertEquals(projectId, input.projectId());
        assertEquals(waterUser, input.waterUser());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPostQueryRequest() throws IOException {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String collect = readJsonFile("radar/v1/json/water_contract.json");
        WaterUserContract waterContract = RadarObjectMapper.mapJsonToObject(collect, WaterUserContract.class);
        WaterContractEndpointInput.Post input = WaterContractEndpointInput.post(waterContract)
                .failIfExists(false).ignoreNulls(true);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(waterContract, input.waterContract());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteQueryRequest() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String contractId = "CONTRACT";
        String office = "SPK";
        String projectId = "PROJECT";
        String waterUser = "user";
        DeleteMethod deleteMethod = DeleteMethod.ALL;
        WaterContractEndpointInput.Delete input = WaterContractEndpointInput.delete(office, projectId, waterUser,
                contractId).deleteMethod(deleteMethod);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(contractId, input.contractName());
        assertEquals(office, input.officeId());
        assertEquals(deleteMethod.toString(), mockHttpRequestBuilder.getQueryParameter(WaterContractEndpointInput
                .Delete.METHOD_QUERY_PARAMETER));
        assertEquals(projectId, input.projectId());
        assertEquals(waterUser, input.waterUserId());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testPatch() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        String oldContractId = "CONTRACT";
        String newContractId = "NEW-CONTRACT";
        String office = "SPK";
        String projectId = "PROJECT";
        String waterUser = "user";
        WaterContractEndpointInput.Patch input = WaterContractEndpointInput.patch(office, projectId, waterUser,
                oldContractId, newContractId);
        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(oldContractId, input.oldWaterContractName());
        assertEquals(newContractId, mockHttpRequestBuilder.getQueryParameter(WaterContractEndpointInput
                .Patch.NAME_QUERY_PARAMETER));
        assertEquals(office, input.officeId());
        assertEquals(projectId, input.projectId());
        assertEquals(waterUser, input.waterUser());
        assertEquals(ACCEPT_HEADER_V1, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testDeleteNulls() {
        assertAll(
            () -> assertThrows(NullPointerException.class, () -> WaterContractEndpointInput
                    .delete(null, "project", "User", "contract")),
            () -> assertThrows(NullPointerException.class, () -> WaterContractEndpointInput
                    .delete("HQ", null, "user", "contract")),
            () -> assertThrows(NullPointerException.class, () -> WaterContractEndpointInput
                    .delete("HQ", "project", null, "contract")),
            () -> assertThrows(NullPointerException.class, () -> WaterContractEndpointInput
                    .delete("HQ", "project", "user", null))
        );
    }

    @Test
    void testPatchNulls() {
        assertAll(
            () -> assertThrows(NullPointerException.class, () -> WaterContractEndpointInput.patch(null, "project", "User", "contract", "newContract")),
            () -> assertThrows(NullPointerException.class, () -> WaterContractEndpointInput.patch("HQ", null, "user", "contract", "newContract")),
            () -> assertThrows(NullPointerException.class, () -> WaterContractEndpointInput.patch("HQ", "project", null, "contract", "newContract")),
            () -> assertThrows(NullPointerException.class, () -> WaterContractEndpointInput.patch("HQ", "project", "user", null, "newContract")),
            () -> assertThrows(NullPointerException.class, () -> WaterContractEndpointInput.patch("HQ", "project", "user", "contract", null))
        );
    }

    @Test
    void testPostNulls() {
        assertThrows(NullPointerException.class, () -> WaterContractEndpointInput.post(null));
    }

    @Test
    void testGetAllNulls() {
        assertAll(
            () -> assertThrows(NullPointerException.class, () -> WaterContractEndpointInput.getAll(null, "project", "user")),
            () -> assertThrows(NullPointerException.class, () -> WaterContractEndpointInput.getAll("HQ", null, "user")),
            () -> assertThrows(NullPointerException.class, () -> WaterContractEndpointInput.getAll("HQ", "project", null))
        );
    }

    @Test
    void testGetOneNulls() {
        assertAll(
            () -> assertThrows(NullPointerException.class, () -> WaterContractEndpointInput.getOne(null, "project", "user", "contract")),
            () -> assertThrows(NullPointerException.class, () -> WaterContractEndpointInput.getOne("HQ", null, "user", "contract")),
            () -> assertThrows(NullPointerException.class, () -> WaterContractEndpointInput.getOne("HQ", "project", null, "contract")),
            () -> assertThrows(NullPointerException.class, () -> WaterContractEndpointInput.getOne("HQ", "project", "user", null))
        );
    }
}
