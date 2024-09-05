package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.util.Objects;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.WaterUserContract;


public final class WaterContractEndpointInput {
    private WaterContractEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll(String officeId, String projectId, String waterUser) {
        return new GetAll(officeId, projectId, waterUser);
    }

    public static GetOne getOne(String officeId, String projectId, String waterUser, String waterContractId) {
        return new GetOne(officeId, projectId, waterUser, waterContractId);
    }

    public static Post post(WaterUserContract waterContract) {
        return new Post(waterContract);
    }

    public static Delete delete(String officeId, String projectId, String waterUser, String waterContractId) {
        return new Delete(officeId, projectId, waterUser, waterContractId);
    }

    public static Patch patch(String officeId, String projectId, String waterUser, String oldWaterContractId,
            String newWaterContractId) {
        return new Patch(officeId, projectId, waterUser, oldWaterContractId, newWaterContractId);
    }

    public static final class GetAll extends EndpointInput {
        private final String projectId;
        private final String officeId;
        private final String waterUser;

        private GetAll(String officeId, String projectId, String waterUser) {
            this.officeId = Objects.requireNonNull(officeId);
            this.projectId = Objects.requireNonNull(projectId);
            this.waterUser = Objects.requireNonNull(waterUser);
        }

        String projectId() {
            return this.projectId;
        }

        String waterUser() {
            return this.waterUser;
        }

        String officeId() {
            return this.officeId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class GetOne extends EndpointInput {
        private final String contractName;
        private final String officeId;
        private final String projectId;
        private final String waterUser;

        private GetOne(String officeId, String projectId, String waterUser, String contractName) {
            this.contractName = Objects.requireNonNull(contractName);
            this.officeId = Objects.requireNonNull(officeId);
            this.projectId = Objects.requireNonNull(projectId);
            this.waterUser = Objects.requireNonNull(waterUser);
        }

        String contractName() {
            return contractName;
        }

        String officeId() {
            return officeId;
        }

        String projectId() {
            return projectId;
        }

        String waterUser() {
            return waterUser;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Delete extends EndpointInput {
        static final String METHOD_QUERY_PARAMETER = "method";
        private final String contractName;
        private final String officeId;
        private DeleteMethod deleteMethod = null;
        private final String projectId;
        private final String waterUserId;

        private Delete(String officeId, String projectId, String waterUser, String contractName) {
            this.contractName = Objects.requireNonNull(contractName);
            this.officeId = Objects.requireNonNull(officeId);
            this.projectId = Objects.requireNonNull(projectId);
            this.waterUserId = Objects.requireNonNull(waterUser);
        }

        public Delete deleteMethod(DeleteMethod deleteMethod) {
            this.deleteMethod = deleteMethod;
            return this;
        }

        String contractName() {
            return contractName;
        }

        String officeId() {
            return officeId;
        }

        String projectId() {
            return projectId;
        }

        String waterUserId() {
            return waterUserId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder
                    .addQueryParameter(METHOD_QUERY_PARAMETER, deleteMethod == null ? null : deleteMethod.toString())
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        private static final String FAIL_IF_EXISTS_QUERY_PARAMETER = "fail-if-exists";
        private static final String IGNORE_NULLS_QUERY_PARAMETER = "ignore-nulls";
        private final WaterUserContract waterContract;
        private boolean failIfExists = true;
        private boolean ignoreNulls = false;

        private Post(WaterUserContract waterContract) {
            this.waterContract = Objects.requireNonNull(waterContract, "Cannot access the water contract "
                    + "POST endpoint without a water contract value");
        }

        public Post failIfExists(boolean failIfExists) {
            this.failIfExists = failIfExists;
            return this;
        }

        public Post ignoreNulls(boolean ignoreNulls) {
            this.ignoreNulls = ignoreNulls;
            return this;
        }

        WaterUserContract waterContract() {
            return waterContract;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER, String.valueOf(failIfExists))
                    .addQueryParameter(IGNORE_NULLS_QUERY_PARAMETER, String.valueOf(ignoreNulls))
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Patch extends EndpointInput {
        static final String NAME_QUERY_PARAMETER = "name";
        private final String oldWaterContractName;
        private final String newWaterContractName;
        private final String projectId;
        private final String waterUser;
        private final String officeId;

        private Patch(String officeId, String projectId, String waterUser, String oldWaterContractName, String newWaterContractName) {
            this.oldWaterContractName = Objects.requireNonNull(oldWaterContractName);
            this.newWaterContractName = Objects.requireNonNull(newWaterContractName);
            this.officeId = Objects.requireNonNull(officeId);
            this.projectId = Objects.requireNonNull(projectId);
            this.waterUser = Objects.requireNonNull(waterUser);
        }

        String oldWaterContractName() {
            return oldWaterContractName;
        }

        String projectId() {
            return projectId;
        }

        String waterUser() {
            return waterUser;
        }

        String officeId() {
            return officeId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder
                    .addQueryParameter(NAME_QUERY_PARAMETER, newWaterContractName)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
