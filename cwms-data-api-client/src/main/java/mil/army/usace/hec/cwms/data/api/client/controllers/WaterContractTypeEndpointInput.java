package mil.army.usace.hec.cwms.data.api.client.controllers;

import java.util.Objects;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.data.api.client.model.LookupType;


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

    public static Delete delete(String officeId, String displayValue) {
        return new Delete(officeId, displayValue);
    }

    public static final class GetAll extends EndpointInput {
        private final String officeId;

        private GetAll(String officeId) {
            this.officeId = Objects.requireNonNull(officeId);
        }

        String officeId() {
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
        private boolean failIfExists = true;

        private Post(LookupType waterContractType) {
            this.waterContractType = Objects.requireNonNull(waterContractType);
        }

        public Post failIfExists(boolean failIfExists) {
            this.failIfExists = failIfExists;
            return this;
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

    public static final class Delete extends EndpointInput {
        private final String officeId;
        private final String displayValue;

        private Delete(String officeId, String displayValue) {
            this.officeId = Objects.requireNonNull(officeId);
            this.displayValue = Objects.requireNonNull(displayValue);
        }

        String officeId() {
            return officeId;
        }

        String displayValue() {
            return displayValue;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
