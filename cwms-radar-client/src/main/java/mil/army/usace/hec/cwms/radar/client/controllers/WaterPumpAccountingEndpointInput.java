package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.radar.client.model.WaterSupplyAccounting;
import java.util.Objects;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

public final class WaterPumpAccountingEndpointInput {
    private WaterPumpAccountingEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll() {
        return new GetAll();
    }

    public static Post post(WaterSupplyAccounting waterSupplyAccounting) {
        return new Post(waterSupplyAccounting);
    }

    public static final class GetAll extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String PROJECT_ID_QUERY_PARAMETER = "project-id";
        static final String WATER_USER_QUERY_PARAMETER = "water-user";
        static final String WATER_CONTRACT_QUERY_PARAMETER = "contract-id";
        private String waterUserId;
        private String waterContractName;
        private String projectId;
        private String officeId;

        private GetAll() {
        }

        public GetAll officeId(String officeId) {
            this.officeId = officeId;
            return this;
        }

        public GetAll projectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public GetAll waterUserId(String waterUserId) {
            this.waterUserId = waterUserId;
            return this;
        }

        public GetAll waterContractName(String waterContractName) {
            this.waterContractName = waterContractName;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(PROJECT_ID_QUERY_PARAMETER, projectId)
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(WATER_USER_QUERY_PARAMETER, waterUserId)
                    .addQueryParameter(WATER_CONTRACT_QUERY_PARAMETER, waterContractName)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        private final WaterSupplyAccounting waterSupplyAccounting;

        private Post(WaterSupplyAccounting waterSupplyAccounting) {
            this.waterSupplyAccounting = Objects.requireNonNull(waterSupplyAccounting, "Cannot access the" +
                    " water supply accounting POST endpoint without providing water supply accounting");
        }

        WaterSupplyAccounting waterSupplyAccounting() {
            return waterSupplyAccounting;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
