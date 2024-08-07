package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.util.Objects;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;


public class WaterPumpEndpointInput {
    private WaterPumpEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static Delete delete(String officeId, String projectId, String contractName, String waterUser, String pumpId,
            boolean deleteAccounting) {
        return new Delete(officeId, projectId, contractName, waterUser, pumpId, deleteAccounting);
    }

    public static final class Delete extends EndpointInput {
        static final String METHOD_QUERY_PARAMETER = "method";
        private final String pumpId;
        private final String officeId;
        private final String waterUser;
        private final String contractName;
        private final String projectId;
        private final boolean deleteAccounting;

        private Delete(String officeId, String projectId, String contractName, String waterUser, String pumpId, boolean deleteAccounting) {
            this.pumpId = Objects.requireNonNull(pumpId, "Cannot disassociate a pump without a pumpId");
            this.officeId = Objects.requireNonNull(officeId, "Cannot disassociate a pump without an officeId");
            this.deleteAccounting = deleteAccounting;
            this.waterUser = Objects.requireNonNull(waterUser, "Cannot disassociate a pump without a waterUser");
            this.contractName = Objects.requireNonNull(contractName, "Cannot disassociate a pump without a contractName");
            this.projectId = Objects.requireNonNull(projectId, "Cannot disassociate a pump without a projectId");
        }

        String pumpId() {
            return pumpId;
        }

        String getOfficeId() {
            return officeId;
        }

        String getWaterUser() {
            return waterUser;
        }

        String getContractName() {
            return contractName;
        }

        String getProjectId() {
            return projectId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder
                    .addQueryParameter(METHOD_QUERY_PARAMETER, deleteAccounting ? "true" : "false")
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
