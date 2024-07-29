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
import mil.army.usace.hec.cwms.radar.client.model.VirtualOutlet;


public final class VirtualOutletController {
    private static final String VIRTUAL_OUTLET_ENDPOINT = "projects/{office}/{project-id}/virtual-outlets";

    public VirtualOutlet retrieveVirtualOutlet(ApiConnectionInfo apiConnectionInfo,
            VirtualOutletEndpointInput.GetOne input)  throws IOException {
        String endpoint = VIRTUAL_OUTLET_ENDPOINT + "/" + input.outletName();
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToObject(response.getBody(), VirtualOutlet.class);
        }
    }

    public List<VirtualOutlet> retrieveVirtualOutlets(ApiConnectionInfo apiConnectionInfo,
            VirtualOutletEndpointInput.GetAll input) throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, VIRTUAL_OUTLET_ENDPOINT)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_HEADER_V1);
        try (HttpRequestResponse response = executor.execute()) {
            return RadarObjectMapper.mapJsonToListOfObjects(response.getBody(), VirtualOutlet.class);
        }
    }

    public void storeVirtualOutlet(ApiConnectionInfo apiConnectionInfo, VirtualOutletEndpointInput.Post input)
            throws IOException {
        String body = RadarObjectMapper.mapObjectToJson(input.outletName());
        new HttpRequestBuilderImpl(apiConnectionInfo, VIRTUAL_OUTLET_ENDPOINT)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .post()
                .withBody(body)
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }

    public void renameVirtualOutlet(ApiConnectionInfo apiConnectionInfo, VirtualOutletEndpointInput.Patch input)
            throws IOException {
        new HttpRequestBuilderImpl(apiConnectionInfo, VIRTUAL_OUTLET_ENDPOINT + "/" + input.oldOutletName())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .patch()
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }

    public void deleteVirtualOutlet(ApiConnectionInfo apiConnectionInfo, VirtualOutletEndpointInput.Delete input)
            throws IOException {
        String endpoint = VIRTUAL_OUTLET_ENDPOINT + "/" + input.outletName();
        new HttpRequestBuilderImpl(apiConnectionInfo, endpoint)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                .addEndpointInput(input)
                .delete()
                .withMediaType(ACCEPT_HEADER_V1)
                .execute()
                .close();
    }
}
