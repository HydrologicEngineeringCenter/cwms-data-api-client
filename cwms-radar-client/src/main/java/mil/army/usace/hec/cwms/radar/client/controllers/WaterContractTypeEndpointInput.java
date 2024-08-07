package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.util.Objects;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.radar.client.model.LookupType;


public final class WaterContractTypeEndpointInput {
    private WaterContractTypeEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll(String officeId) {
        return new GetAll(officeId);
    }

    public static Post post(LookupType waterContractType) {
        return new Post(waterContractType);
    }

    public static final class GetAll extends EndpointInput {
        private final String officeId;

        private GetAll(String officeId) {
            this.officeId = Objects.requireNonNull(officeId);
        }

        String getOfficeId() {
            return officeId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        private static final String FAIL_IF_EXISTS_QUERY_PARAMETER = "fail-if-exists";
        private final LookupType waterContractType;
        private boolean failIfExists;

        private Post(LookupType waterContractType) {
            this.waterContractType = waterContractType;
        }

        public Post failIfExists(boolean failIfExists) {
            this.failIfExists = failIfExists;
            return this;
        }

        boolean getFailIfExists() {
            return failIfExists;
        }

        LookupType waterContractType() {
            return waterContractType;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER, failIfExists ? "true" : "false")
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
