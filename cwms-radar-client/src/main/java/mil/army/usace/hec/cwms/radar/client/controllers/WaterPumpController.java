package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;


public final class WaterPumpController {
    private static final String WATER_PUMP_ENDPOINT = "projects/{office}/{project-id}/water-user/{water-user}/contracts/{contract-id}/pumps";

    public void disassociateWaterPump(ApiConnectionInfo apiConnectionInfo, WaterPumpEndpointInput.Delete input)
            throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, WATER_PUMP_ENDPOINT + "/"
                + input.pumpId())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .delete()
                .withMediaType(ACCEPT_HEADER_V1);
        executor.execute().close();
    }
}
