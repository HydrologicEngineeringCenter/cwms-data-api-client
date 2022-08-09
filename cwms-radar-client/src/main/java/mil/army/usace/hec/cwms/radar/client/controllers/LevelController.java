package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;

import java.io.IOException;
import java.util.Set;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.SpecifiedLevel;

public final class LevelController {

    private static final String SPECIFIED_LEVEL_ENDPOINT = "specified-levels";

    public Set<SpecifiedLevel> retrieveSpecifiedLevels(ApiConnectionInfo apiConnectionInfo, SpecifiedLevelEndpointInput endpointInput)
        throws IOException {
        Set<SpecifiedLevel> retVal;
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, SPECIFIED_LEVEL_ENDPOINT)
            .addEndpointInput(endpointInput)
            .get()
            .withMediaType(ACCEPT_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            retVal = RadarObjectMapper.mapJsonToSetOfObjects(response.getBody(), SpecifiedLevel.class);
        }
        return retVal;
    }

}
