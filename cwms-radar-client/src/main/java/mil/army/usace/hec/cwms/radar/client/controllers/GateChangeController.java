/*
 * Copyright (c) 2024. Hydrologic Engineering Center (HEC).
 * United States Army Corps of Engineers
 * All Rights Reserved. HEC PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.radar.client.controllers;

import static java.lang.String.format;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.GateChange;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;

import java.io.IOException;
import java.util.Set;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

public class GateChangeController {
    static final String GATE_CHANGE_PATH = "projects/%s/%s/gate-changes";
    static final String GATE_CHANGE_CREATE_PATH = "projects/gate-changes";

    public Set<GateChange> retrieveGateChanges(ApiConnectionInfo apiConnectionInfo, GateChangeEndpointInput.GetAll input)
            throws IOException {
        String endpoint = format(GATE_CHANGE_PATH, input.officeId(), input.projectId());
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addQueryParameter(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
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
                .addQueryParameter(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
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
