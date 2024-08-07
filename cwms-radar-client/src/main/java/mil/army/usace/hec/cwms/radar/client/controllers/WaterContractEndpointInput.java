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

    public static GetOne getOne(String officeId, String waterContractId, String projectId, String waterUser) {
        return new GetOne(officeId, waterContractId, projectId, waterUser);
    }

    public static Post post(WaterUserContract waterContract) {
        return new Post(waterContract);
    }

    public static Delete delete(String officeId, String projectId, String waterUser, String waterContractId,
            DeleteMethod deleteMethod) {
        return new Delete(officeId, projectId, waterUser, waterContractId, deleteMethod);
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

        public String getProjectId() {
            return this.projectId;
        }

        public String getWaterUser() {
            return this.waterUser;
        }

        public String getOfficeId() {
            return this.officeId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class GetOne extends EndpointInput {
        private final String waterContractId;
        private final String officeId;
        private final String projectId;
        private final String waterUser;

        private GetOne(String officeId, String waterContractId, String projectId, String waterUser) {
            this.waterContractId = Objects.requireNonNull(waterContractId);
            this.officeId = Objects.requireNonNull(officeId);
            this.projectId = Objects.requireNonNull(projectId);
            this.waterUser = Objects.requireNonNull(waterUser);
        }

        String waterContractId() {
            return waterContractId;
        }

        String getOfficeId() {
            return officeId;
        }

        String getProjectId() {
            return projectId;
        }

        String getWaterUser() {
            return waterUser;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Delete extends EndpointInput {
        static final String METHOD_QUERY_PARAMETER = "method";
        private final String waterContractId;
        private final String officeId;
        private final DeleteMethod deleteMethod;
        private final String projectId;
        private final String waterUserId;

        private Delete(String officeId, String projectId, String waterUser, String waterContractId, DeleteMethod deleteMethod) {
            this.waterContractId = Objects.requireNonNull(waterContractId);
            this.officeId = Objects.requireNonNull(officeId);
            this.deleteMethod = Objects.requireNonNull(deleteMethod);
            this.projectId = Objects.requireNonNull(projectId);
            this.waterUserId = Objects.requireNonNull(waterUser);
        }

        String getWaterContractId() {
            return waterContractId;
        }

        String getOfficeId() {
            return officeId;
        }

        String getProjectId() {
            return projectId;
        }

        String getWaterUserId() {
            return waterUserId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder
                    .addQueryParameter(METHOD_QUERY_PARAMETER, deleteMethod.toString())
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        private final WaterUserContract waterContract;

        private Post(WaterUserContract waterContract) {
            this.waterContract = Objects.requireNonNull(waterContract, "Cannot access the water contract "
                    + "POST endpoint without a water contract value");
        }

        WaterUserContract getWaterContract() {
            return waterContract;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Patch extends EndpointInput {
        private final String oldWaterContractId;
        private final String newWaterContractId;
        static final String NAME_QUERY_PARAMETER = "name";
        private final String projectId;
        private final String waterUser;
        private final String officeId;

        private Patch(String officeId, String projectId, String waterUser, String oldWaterContractId, String newWaterContractId) {
            this.oldWaterContractId = Objects.requireNonNull(oldWaterContractId);
            this.newWaterContractId = Objects.requireNonNull(newWaterContractId);
            this.officeId = Objects.requireNonNull(officeId);
            this.projectId = Objects.requireNonNull(projectId);
            this.waterUser = Objects.requireNonNull(waterUser);
        }

        String getOldWaterContractId() {
            return oldWaterContractId;
        }

        String getProjectId() {
            return projectId;
        }

        String getWaterUser() {
            return waterUser;
        }

        String getOfficeId() {
            return officeId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder
                    .addQueryParameter(NAME_QUERY_PARAMETER, newWaterContractId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
