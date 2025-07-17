package mil.army.usace.hec.cwms.data.api.client.controllers;

import static java.lang.String.format;

import java.io.IOException;
import java.util.List;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.data.api.client.model.WaterUserContract;


public final class WaterContractController {
    private static final String WATER_CONTRACT_ENDPOINT = "projects/%s/%s/water-user/%s/contracts";

    public WaterUserContract retrieveWaterContract(ApiConnectionInfo apiConnectionInfo,
            WaterContractEndpointInput.GetOne input) throws IOException {
        String endpoint = format(WATER_CONTRACT_ENDPOINT, input.officeId(), input.projectId(), input.waterUser())
                + "/" + input.contractName();
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), WaterUserContract.class);
        }
    }

    public List<WaterUserContract> retrieveWaterContracts(ApiConnectionInfo apiConnectionInfo,
            WaterContractEndpointInput.GetAll input) throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, format(WATER_CONTRACT_ENDPOINT,
                input.officeId(), input.projectId(), input.waterUser()))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToListOfObjects(response.getBody(), WaterUserContract.class);
        }
    }

    public void storeWaterContract(ApiConnectionInfo apiConnectionInfo, WaterContractEndpointInput.Post input) throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.waterContract());
        new HttpRequestBuilderImpl(apiConnectionInfo, format(WATER_CONTRACT_ENDPOINT,
                input.waterContract().getContractId().getOfficeId(),
                input.waterContract().getWaterUser().getProjectId().getName(),
                input.waterContract().getWaterUser().getEntityName()))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .post()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }

    public void renameWaterContract(ApiConnectionInfo apiConnectionInfo, WaterContractEndpointInput.Patch input) throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo, format(WATER_CONTRACT_ENDPOINT,
                input.officeId(), input.projectId(), input.waterUser()) + "/" + input.oldWaterContractName())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .patch()
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }

    public void deleteWaterContract(ApiConnectionInfo apiConnectionInfo, WaterContractEndpointInput.Delete input) throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo, format(WATER_CONTRACT_ENDPOINT,
                input.officeId(), input.projectId(), input.waterUserId()) + "/" + input.contractName())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .delete()
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }
}
