package mil.army.usace.hec.cwms.radar.client.controllers;

import static java.lang.String.format;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.io.IOException;
import java.util.List;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.LookupType;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;


public final class WaterContractTypeController {
    private static final String ENDPOINT = "projects/%s/contract-types";

    public List<LookupType> retrieveWaterContractTypes(ApiConnectionInfo apiConnectionInfo,
            WaterContractTypeEndpointInput.GetAll input)  throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, format(ENDPOINT, input.officeId()))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToListOfObjects(response.getBody(), LookupType.class);
        }
    }

    public void storeWaterContractType(ApiConnectionInfo apiConnectionInfo, WaterContractTypeEndpointInput.Post input)
            throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.waterContractType());
        new HttpRequestBuilderImpl(apiConnectionInfo, format(ENDPOINT, input.waterContractType().getOfficeId()))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .post()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }

    public void deleteWaterContractType(ApiConnectionInfo apiConnectionInfo, WaterContractTypeEndpointInput.Delete input)
            throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo, format(ENDPOINT, input.officeId()) + "/" + input.displayValue())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .delete()
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }
}
