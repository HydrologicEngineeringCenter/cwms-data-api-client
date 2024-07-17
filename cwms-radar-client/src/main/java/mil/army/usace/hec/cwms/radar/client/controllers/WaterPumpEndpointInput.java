package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.util.Objects;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;


public class WaterPumpEndpointInput {
    private WaterPumpEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static Delete delete(String pumpId, String officeId, DeleteMethod deleteMethod) {
        return new Delete(pumpId, officeId, deleteMethod);
    }

    public static final class Delete extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String METHOD_QUERY_PARAMETER = "method";
        private final String pumpId;
        private final String officeId;
        private final DeleteMethod deleteMethod;

        private Delete(String pumpId, String officeId, DeleteMethod deleteMethod) {
            this.pumpId = Objects.requireNonNull(pumpId, "Cannot disassociate a pump without a pumpId");
            this.officeId = Objects.requireNonNull(officeId, "Cannot disassociate a pump without an officeId");
            this.deleteMethod = deleteMethod;
        }

        String pumpId() {
            return pumpId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(METHOD_QUERY_PARAMETER, deleteMethod.toString())
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
