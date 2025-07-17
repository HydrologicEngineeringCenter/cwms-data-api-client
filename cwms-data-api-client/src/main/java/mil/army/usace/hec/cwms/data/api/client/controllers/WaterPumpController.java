package mil.army.usace.hec.cwms.data.api.client.controllers;

import static java.lang.String.format;

import java.io.IOException;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;


public final class WaterPumpController {
    private static final String ENDPOINT = "projects/%s/%s/water-user/%s/contracts/%s/pumps/%s";

    public void disassociateWaterPump(ApiConnectionInfo apiConnectionInfo, WaterPumpEndpointInput.Delete input)
            throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo,
                format(ENDPOINT, input.officeId(), input.projectId(),
                        input.waterUser(), input.contractName(), input.pumpId()))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .delete()
                .withMediaType(ACCEPT_HEADER_V1);
        executor.execute().close();
    }
}
