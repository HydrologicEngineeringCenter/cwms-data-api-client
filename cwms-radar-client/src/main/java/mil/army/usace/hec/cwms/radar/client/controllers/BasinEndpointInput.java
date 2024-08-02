package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.util.Objects;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.radar.client.model.Basin;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;


public final class BasinEndpointInput {
    private BasinEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll() {
        return new GetAll();
    }

    public static GetOne getOne(String basinId, String officeId) {
        return new GetOne(basinId, officeId);
    }

    public static Post post(Basin basin) {
        return new Post(basin);
    }

    public static Delete delete(String basinId, String officeId, DeleteMethod deleteMethod) {
        return new Delete(basinId, officeId, deleteMethod);
    }

    public static Patch patch(String oldBasinId, String newBasinId, String officeId) {
        return new Patch(oldBasinId, newBasinId, officeId);
    }

    public static final class GetAll extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String PROJECT_ID_QUERY_PARAMETER = "project-id";
        static final String UNITS_QUERY_PARAMETER = "units";
        private String projectId;
        private String officeId;
        private String units;

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

        public GetAll units(String units) {
            this.units = units;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(PROJECT_ID_QUERY_PARAMETER, projectId)
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(UNITS_QUERY_PARAMETER, units)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class GetOne extends EndpointInput {
        public static final String OFFICE_QUERY_PARAMETER = "office";
        public static final String UNITS_QUERY_PARAMETER = "units";
        private final String officeId;
        private final String basinId;
        private String units;

        private GetOne(String basinId, String officeId) {
            this.basinId = Objects.requireNonNull(basinId, "Basin Id required for getOne basin endpoint");
            this.officeId = Objects.requireNonNull(officeId, "Basin office Id required for getOne basin endpoint");
        }

        public GetOne units(String units) {
            this.units = units;
            return this;
        }

        String basinId() {
            return basinId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(UNITS_QUERY_PARAMETER, units)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        private final Basin basin;

        private Post(Basin basin) {
            this.basin = Objects.requireNonNull(basin,
                    "Cannot access the basin POST endpoint without a basin value");
        }

        Basin basin() {
            return basin;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Delete extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String METHOD_QUERY_PARAMETER = "method";
        private final String basinId;
        private final String officeId;
        private final DeleteMethod deleteMethod;

        private Delete(String basinId, String officeId, DeleteMethod deleteMethod) {
            this.basinId = Objects.requireNonNull(basinId, "Cannot access the basin DELETE endpoint without a basin id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the basin DELETE endpoint without an office id");
            this.deleteMethod = Objects.requireNonNull(deleteMethod, "Cannot access the basin DELETE endpoint without a delete method");
        }

        String basinId() {
            return basinId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(METHOD_QUERY_PARAMETER, deleteMethod.toString())
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Patch extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String NAME_QUERY_PARAMETER = "name";
        private final String oldBasinId;
        private final String newBasinId;
        private final String officeId;

        private Patch(String oldBasinId, String newBasinId, String officeId) {
            this.oldBasinId = Objects.requireNonNull(oldBasinId, "Cannot access the basin PATCH endpoint without an old basin id");
            this.newBasinId = Objects.requireNonNull(newBasinId, "Cannot access the basin PATCH endpoint without a new basin id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the basin PATCH endpoint without an office id");
        }

        String oldBasinId() {
            return oldBasinId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(NAME_QUERY_PARAMETER, newBasinId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
