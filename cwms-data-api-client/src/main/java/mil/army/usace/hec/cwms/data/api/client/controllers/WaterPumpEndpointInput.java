package mil.army.usace.hec.cwms.data.api.client.controllers;

import java.util.Objects;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.data.api.client.model.WaterSupplyPump.PumpTypeEnum;


public class WaterPumpEndpointInput {
    private WaterPumpEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static Delete delete(String officeId, String projectId, String waterUser, String contractName, String pumpId, PumpTypeEnum pumpType,
            boolean deleteAccounting) {
        return new Delete(officeId, projectId, waterUser, contractName, pumpId, pumpType, deleteAccounting);
    }

    public static final class Delete extends EndpointInput {
        static final String DELETE_ACCOUNTING_QUERY_PARAMETER = "delete-accounting";
        static final String PUMP_TYPE_QUERY_PARAMETER = "pump-type";
        private final PumpTypeEnum pumpType;
        private final String pumpId;
        private final String officeId;
        private final String waterUser;
        private final String contractName;
        private final String projectId;
        private final boolean deleteAccounting;

        private Delete(String officeId, String projectId, String waterUser, String contractName, String pumpId, PumpTypeEnum pumpType, boolean deleteAccounting) {
            this.pumpType = Objects.requireNonNull(pumpType, "Cannot disassociate a pump without a pump type");
            this.officeId = Objects.requireNonNull(officeId, "Cannot disassociate a pump without an officeId");
            this.deleteAccounting = deleteAccounting;
            this.pumpId = Objects.requireNonNull(pumpId, "Cannot disassociate a pump without a pumpId");
            this.waterUser = Objects.requireNonNull(waterUser, "Cannot disassociate a pump without a waterUser");
            this.contractName = Objects.requireNonNull(contractName, "Cannot disassociate a pump without a contractName");
            this.projectId = Objects.requireNonNull(projectId, "Cannot disassociate a pump without a projectId");
        }

        String officeId() {
            return officeId;
        }

        String waterUser() {
            return waterUser;
        }

        String contractName() {
            return contractName;
        }

        String projectId() {
            return projectId;
        }

        String pumpId() {
            return pumpId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder
                    .addQueryParameter(DELETE_ACCOUNTING_QUERY_PARAMETER, deleteAccounting ? "true" : "false")
                    .addQueryParameter(PUMP_TYPE_QUERY_PARAMETER, pumpType.toString())
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
