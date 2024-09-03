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

    public static GetOne getOne(String officeId, String projectId, String waterUserId) {
        return new GetOne(officeId, projectId, waterUserId);
    }

    public static Post post(WaterUser waterUser) {
        return new Post(waterUser);
    }

    public static Delete delete(String officeId, String projectId, String waterUserId) {
        return new Delete(officeId, projectId, waterUserId);
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

        public String officeId() {
            return officeId;
        }

        public String projectId() {
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

        private GetOne(String officeId, String projectId, String waterUserId) {
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

        String officeId() {
            return officeId;
        }

        String projectId() {
            return projectId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        private final WaterUser waterUser;
        private static final String FAIL_IF_EXISTS_QUERY_PARAMETER = "fail-if-exists";
        private boolean failIfExists = true;

        private Post(WaterUser waterUser) {
            this.waterUser = Objects.requireNonNull(waterUser, "Water User required for post water user endpoint");
        }

        public Post failIfExists(boolean failIfExists) {
            this.failIfExists = failIfExists;
            return this;
        }

        WaterUser waterUser() {
            return waterUser;
        }

        String officeId() {
            return waterUser.getProjectId().getOfficeId();
        }

        String projectId() {
            return waterUser.getProjectId().getName();
        }


        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER, String.valueOf(failIfExists))
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Delete extends EndpointInput {
        static final String METHOD_QUERY_PARAMETER = "method";
        private final String projectId;
        private final String waterUserId;
        private final String officeId;
        private DeleteMethod deleteMethod = DeleteMethod.ALL;

        private Delete(String officeId, String projectId, String waterUserId) {
            this.waterUserId = Objects.requireNonNull(waterUserId, "Water User Id required for delete water user endpoint");
            this.officeId = Objects.requireNonNull(officeId, "Water User office Id required for delete water user endpoint");
            this.projectId = Objects.requireNonNull(projectId, "Water User project Id required for delete water user endpoint");
        }

        public Delete deleteMethod(DeleteMethod deleteMethod) {
            this.deleteMethod = Objects.requireNonNull(deleteMethod, "Delete Method required for delete water user endpoint");
            return this;
        }

        String waterUserId() {
            return waterUserId;
        }

        String officeId() {
            return officeId;
        }

        String projectId() {
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
            this.officeId = Objects.requireNonNull(officeId, "Water User office Id required for "
                    + "patch water user endpoint");
        }

        String oldWaterUserId() {
            return oldWaterUserId;
        }

        String projectId() {
            return projectId;
        }

        String officeId() {
            return officeId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(NAME_QUERY_PARAMETER, newWaterUserId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
