package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.util.Objects;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.WaterUser;


public final class WaterUserEndpointInput {
    private WaterUserEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll(String officeId, String projectId) {
        return new GetAll(officeId, projectId);
    }

    public static GetOne getOne(String officeId, String waterUserId, String projectId) {
        return new GetOne(officeId, waterUserId, projectId);
    }

    public static Post post(WaterUser waterUser) {
        return new Post(waterUser);
    }

    public static Delete delete(String officeId, String waterUserId, String projectId, DeleteMethod deleteMethod) {
        return new Delete(waterUserId, officeId, projectId, deleteMethod);
    }

    public static Patch patch(String officeId, String projectId, String oldWaterUserId, String newWaterUserId) {
        return new Patch(officeId, projectId, oldWaterUserId, newWaterUserId);
    }

    public static final class GetAll extends EndpointInput {
        private final String officeId;
        private final String projectId;

        private GetAll(String officeId, String projectId) {
            this.officeId = Objects.requireNonNull(officeId, "Office Id required for get all water user endpoint");
            this.projectId = Objects.requireNonNull(projectId, "Project Id required for get all water user endpoint");
        }

        public String getOfficeId() {
            return officeId;
        }

        public String getProjectId() {
            return projectId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class GetOne extends EndpointInput {
        private final String waterUserId;
        private final String officeId;
        private final String projectId;

        private GetOne(String officeId, String waterUserId, String projectId) {
            this.waterUserId = Objects.requireNonNull(waterUserId, "Water User Id required for "
                    + "getOne water user endpoint");
            this.officeId = Objects.requireNonNull(officeId, "Water User office Id required for "
                    + "getOne water user endpoint");
            this.projectId = Objects.requireNonNull(projectId, "Water User project Id required for "
                    + "getOne water user endpoint");
        }

        String waterUserId() {
            return waterUserId;
        }

        String getOfficeId() {
            return officeId;
        }

        String getProjectId() {
            return projectId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        private final WaterUser waterUser;

        private Post(WaterUser waterUser) {
            this.waterUser = Objects.requireNonNull(waterUser, "Water User required for post water user endpoint");
        }

        WaterUser getWaterUser() {
            return waterUser;
        }

        String getOfficeId() {
            return waterUser.getProjectId().getOfficeId();
        }

        String getProjectId() {
            return waterUser.getProjectId().getName();
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Delete extends EndpointInput {
        static final String METHOD_QUERY_PARAMETER = "method";
        private final String projectId;
        private final String waterUserId;
        private final String officeId;
        private final DeleteMethod deleteMethod;

        private Delete(String waterUserId, String officeId, String projectId, DeleteMethod deleteMethod) {
            this.waterUserId = Objects.requireNonNull(waterUserId, "Water User Id required for delete water user endpoint");
            this.officeId = Objects.requireNonNull(officeId, "Water User office Id required for delete water user endpoint");
            this.projectId = Objects.requireNonNull(projectId, "Water User project Id required for delete water user endpoint");
            this.deleteMethod = deleteMethod;
        }

        String waterUserId() {
            return waterUserId;
        }

        String getOfficeId() {
            return officeId;
        }

        String getProjectId() {
            return projectId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(METHOD_QUERY_PARAMETER, deleteMethod.toString())
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Patch extends EndpointInput {
        static final String NAME_QUERY_PARAMETER = "name";
        private final String projectId;
        private final String oldWaterUserId;
        private final String newWaterUserId;
        private final String officeId;

        private Patch(String officeId, String projectId, String oldWaterUserId, String newWaterUserId) {
            this.oldWaterUserId = Objects.requireNonNull(oldWaterUserId, "Old Water User Id required for "
                    + "patch water user endpoint");
            this.newWaterUserId = Objects.requireNonNull(newWaterUserId, "New Water User Id required for "
                    + "patch water user endpoint");
            this.projectId = Objects.requireNonNull(projectId, "Water User project Id required for "
                    + "patch water user endpoint");
            this.officeId = officeId;
        }

        String oldWaterUserId() {
            return oldWaterUserId;
        }

        String getProjectId() {
            return projectId;
        }

        String getOfficeId() {
            return officeId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(NAME_QUERY_PARAMETER, newWaterUserId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
