package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.io.IOException;
import java.util.List;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.WaterUserContract;


public final class WaterContractController {
    private static final String WATER_CONTRACT_ENDPOINT = "projects/{office}/{project-id}/water-user/{water-user}/contracts";

    public WaterUserContract retrieveWaterContract(ApiConnectionInfo apiConnectionInfo,
            WaterContractEndpointInput.GetOne input) throws IOException {
        String endpoint = WATER_CONTRACT_ENDPOINT + "/" + input.waterContractId();
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
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, WATER_CONTRACT_ENDPOINT)
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
        new HttpRequestBuilderImpl(apiConnectionInfo, WATER_CONTRACT_ENDPOINT)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .post()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }

    public void renameWaterContract(ApiConnectionInfo apiConnectionInfo, WaterContractEndpointInput.Patch input) throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo, WATER_CONTRACT_ENDPOINT + "/" + input.oldWaterContractId())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .patch()
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }

    public void deleteWaterContract(ApiConnectionInfo apiConnectionInfo, WaterContractEndpointInput.Delete input) throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo, WATER_CONTRACT_ENDPOINT + "/" + input.waterContractId())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .delete()
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }
}
