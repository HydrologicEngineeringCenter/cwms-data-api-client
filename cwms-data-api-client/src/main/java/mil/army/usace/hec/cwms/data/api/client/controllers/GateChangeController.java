/*
 * Copyright (c) 2024. Hydrologic Engineering Center (HEC).
 * United States Army Corps of Engineers
 * All Rights Reserved. HEC PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.data.api.client.controllers;

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.data.api.client.model.GateChange;
import mil.army.usace.hec.cwms.data.api.client.model.RadarObjectMapper;
import java.io.IOException;
import java.util.Set;

import static java.lang.String.format;

public class GateChangeController {
    static final String GATE_CHANGE_PATH = "projects/%s/%s/gate-changes";
    static final String GATE_CHANGE_CREATE_PATH = "projects/gate-changes";

    public Set<GateChange> retrieveGateChanges(ApiConnectionInfo apiConnectionInfo, GateChangeEndpointInput.GetAll input)
            throws IOException {
        String endpoint = format(GATE_CHANGE_PATH, input.officeId(), input.projectId());
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToSetOfObjects(response.getBody(), GateChange.class);
        }
    }

    public void storeGateChange(ApiConnectionInfo apiConnectionInfo, GateChangeEndpointInput.Post input)
            throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.gateChanges());
        new HttpRequestBuilderImpl(apiConnectionInfo, GATE_CHANGE_CREATE_PATH)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .post()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }

    public void deleteGateChanges(ApiConnectionInfo apiConnectionInfo, GateChangeEndpointInput.Delete input)
            throws IOException {
        String endpoint = format(GATE_CHANGE_PATH, input.officeId(), input.projectId());
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addEndpointInput(input)
                .delete()
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }
}
