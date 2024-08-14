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
import mil.army.usace.hec.cwms.radar.client.model.WaterSupplyAccounting;


public final class WaterPumpAccountingController {
    private static final String WATER_PUMP_ACCOUNTING_ENDPOINT
            = "/projects/{office}/{project-id}/water-user/{water-user}/contract/{contract-id}/accounting";

    public List<WaterSupplyAccounting> retrieveWaterPumpAccounting(ApiConnectionInfo apiConnectionInfo,
            WaterPumpAccountingEndpointInput.GetAll input) throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, WATER_PUMP_ACCOUNTING_ENDPOINT)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToListOfObjects(response.getBody(), WaterSupplyAccounting.class);
        }
    }

    public void storeWaterPumpAccounting(ApiConnectionInfo apiConnectionInfo,
            WaterPumpAccountingEndpointInput.Post input) throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.getWaterSupplyAccounting());
        new HttpRequestBuilderImpl(apiConnectionInfo, WATER_PUMP_ACCOUNTING_ENDPOINT)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .post()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }
}
