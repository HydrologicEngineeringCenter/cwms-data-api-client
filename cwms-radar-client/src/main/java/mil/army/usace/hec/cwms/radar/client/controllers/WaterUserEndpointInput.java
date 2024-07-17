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

    public static GetAll getAll() {
        return new GetAll();
    }

    public static GetOne getOne(String officeId, String waterUserId) {
        return new GetOne(officeId, waterUserId);
    }

    public static Post post(WaterUser waterUser) {
        return new Post(waterUser);
    }

    public static Delete delete(String officeId, String waterUserId, DeleteMethod deleteMethod) {
        return new Delete(waterUserId, officeId, deleteMethod);
    }

    public static Patch patch(String oldWaterUserId, String newWaterUserId, String officeId) {
        return new Patch(oldWaterUserId, newWaterUserId, officeId);
    }

    public static final class GetAll extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String USER_ID_QUERY_PARAMETER = "water-user";
        private String waterUserId;
        private String officeId;

        private GetAll() {
        }

        public GetAll officeId(String officeId) {
            this.officeId = officeId;
            return this;
        }

        public GetAll waterUserId(String waterUserId) {
            this.waterUserId = waterUserId;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(USER_ID_QUERY_PARAMETER, waterUserId)
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class GetOne extends EndpointInput {
        public static final String OFFICE_QUERY_PARAMETER = "office";
        static final String USER_ID_QUERY_PARAMETER = "water-user";
        private final String waterUserId;
        private final String officeId;

        private GetOne(String officeId, String waterUserId) {
            this.waterUserId = Objects.requireNonNull(waterUserId, "Water User Id required for "
                    + "getOne water user endpoint");
            this.officeId = Objects.requireNonNull(officeId, "Water User office Id required for "
                    + "getOne water user endpoint");
        }

        String waterUserId() {
            return waterUserId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(USER_ID_QUERY_PARAMETER, waterUserId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        private final WaterUser waterUser;

        private Post(WaterUser waterUser) {
            this.waterUser = Objects.requireNonNull(waterUser, "Water User required for post water user endpoint");
        }

        WaterUser waterUser() {
            return waterUser;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Delete extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String METHOD_QUERY_PARAMETER = "method";
        static final String USER_ID_QUERY_PARAMETER = "water-user";
        private final String waterUserId;
        private final String officeId;
        private final DeleteMethod deleteMethod;

        private Delete(String waterUserId, String officeId, DeleteMethod deleteMethod) {
            this.waterUserId = Objects.requireNonNull(waterUserId, "Water User Id required for delete water user endpoint");
            this.officeId = Objects.requireNonNull(officeId, "Water User office Id required for delete water user endpoint");
            this.deleteMethod = deleteMethod;
        }

        String waterUserId() {
            return waterUserId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(USER_ID_QUERY_PARAMETER, waterUserId)
                    .addQueryParameter(METHOD_QUERY_PARAMETER, deleteMethod.toString())
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Patch extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String NAME_QUERY_PARAMETER = "name";
        private final String oldWaterUserId;
        private final String newWaterUserId;
        private final String officeId;

        private Patch(String oldWaterUserId, String newWaterUserId, String officeId) {
            this.oldWaterUserId = Objects.requireNonNull(oldWaterUserId, "Old Water User Id required for "
                    + "patch water user endpoint");
            this.newWaterUserId = Objects.requireNonNull(newWaterUserId, "New Water User Id required for "
                    + "patch water user endpoint");
            this.officeId = officeId;
        }

        String oldWaterUserId() {
            return oldWaterUserId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(NAME_QUERY_PARAMETER, newWaterUserId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
