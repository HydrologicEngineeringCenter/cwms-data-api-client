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

    public static GetAll getAll() {
        return new GetAll();
    }

    public static GetOne getOne(String waterContractId, String officeId) {
        return new GetOne(waterContractId, officeId);
    }

    public static Post post(WaterUserContract waterContract) {
        return new Post(waterContract);
    }

    public static Delete delete(String officeId, String waterContractId, DeleteMethod deleteMethod) {
        return new Delete(officeId, waterContractId, deleteMethod);
    }

    public static Patch patch(String officeId, String oldWaterContractId, String newWaterContractId) {
        return new Patch(officeId, oldWaterContractId, newWaterContractId);
    }

    public static final class GetAll extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String PROJECT_ID_QUERY_PARAMETER = "project-id";
        private String waterContractId;
        private String officeId;

        private GetAll() {
        }

        public GetAll officeId(String officeId) {
            this.officeId = officeId;
            return this;
        }

        public GetAll waterContractId(String waterContractId) {
            this.waterContractId = waterContractId;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(PROJECT_ID_QUERY_PARAMETER, waterContractId)
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class GetOne extends EndpointInput {
        public static final String OFFICE_QUERY_PARAMETER = "office";
        private final String waterContractId;
        private final String officeId;

        private GetOne(String waterContractId, String officeId) {
            this.waterContractId = Objects.requireNonNull(waterContractId);
            this.officeId = Objects.requireNonNull(officeId);
        }

        String waterContractId() {
            return waterContractId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Delete extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String METHOD_QUERY_PARAMETER = "method";
        private final String waterContractId;
        private final String officeId;
        private final DeleteMethod deleteMethod;

        private Delete(String officeId, String waterContractId, DeleteMethod deleteMethod) {
            this.waterContractId = Objects.requireNonNull(waterContractId);
            this.officeId = Objects.requireNonNull(officeId);
            this.deleteMethod = Objects.requireNonNull(deleteMethod);
        }

        String waterContractId() {
            return waterContractId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
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

        WaterUserContract waterContract() {
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
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String NAME_QUERY_PARAMETER = "name";

        private final String officeId;

        private Patch(String officeId, String oldWaterContractId, String newWaterContractId) {
            this.oldWaterContractId = Objects.requireNonNull(oldWaterContractId);
            this.newWaterContractId = Objects.requireNonNull(newWaterContractId);
            this.officeId = Objects.requireNonNull(officeId);
        }

        String oldWaterContractId() {
            return oldWaterContractId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(NAME_QUERY_PARAMETER, newWaterContractId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
