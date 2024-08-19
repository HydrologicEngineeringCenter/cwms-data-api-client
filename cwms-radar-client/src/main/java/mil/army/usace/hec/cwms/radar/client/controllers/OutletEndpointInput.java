package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.util.Objects;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.Outlet;


public final class OutletEndpointInput {
    private OutletEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll(String officeId, String projectId) {
        return new GetAll(officeId, projectId);
    }

    public static GetOne getOne(String officeId, String outletId) {
        return new GetOne(officeId, outletId);
    }

    public static Post post(Outlet outlet) {
        return new Post(outlet);
    }

    public static Delete delete(String officeId, String outletId) {
        return new Delete(officeId, outletId);
    }

    public static Patch patch(String officeId, String oldOutletId, String newOutletId) {
        return new Patch(officeId, oldOutletId, newOutletId);
    }

    public static final class GetAll extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String PROJECT_ID_QUERY_PARAMETER = "project-id";
        private final String projectId;
        private final String officeId;

        private GetAll(String officeId, String projectId) {
            this.projectId = Objects.requireNonNull(projectId, "Cannot access the outlet GET "
                    + "endpoint without a project ID");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the outlet GET "
                    + "endpoint without an office ID");
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(PROJECT_ID_QUERY_PARAMETER, projectId)
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class GetOne extends EndpointInput {
        public static final String OFFICE_QUERY_PARAMETER = "office";
        private final String outletName;
        private final String officeId;

        private GetOne(String officeId, String outletName) {
            this.outletName = Objects.requireNonNull(outletName, "Cannot access the outlet GET "
                    + "endpoint without an outlet name");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the outlet GET "
                    + "endpoint without an office ID");
        }

        String outletName() {
            return outletName;
        }

        String officeId() {
            return officeId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        private final Outlet outlet;
        private static final String FAIL_IF_EXISTS_QUERY_PARAMETER = "fail-if-exists";
        private boolean failIfExists = true;

        private Post(Outlet outlet) {
            this.outlet = Objects.requireNonNull(outlet, "Cannot access the outlet POST "
                    + "endpoint without an outlet");
        }

        public Post failIfExists(boolean failIfExists) {
            this.failIfExists = failIfExists;
            return this;
        }

        Outlet outlet() {
            return outlet;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER, String.valueOf(failIfExists))
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Delete extends EndpointInput {
        public static final String METHOD_QUERY_PARAMETER = "method";
        public static final String OFFICE_QUERY_PARAMETER = "office";
        private final String outletName;
        private final String officeId;
        private DeleteMethod deleteMethod = DeleteMethod.KEY;

        private Delete(String officeId, String outletName) {
            this.outletName = Objects.requireNonNull(outletName, "Cannot access the outlet DELETE "
                    + "endpoint without an outlet name");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the outlet DELETE "
                    + "endpoint without an office ID");
        }

        public Delete deleteMethod(DeleteMethod deleteMethod) {
            this.deleteMethod = deleteMethod;
            return this;
        }

        String outletName() {
            return outletName;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(METHOD_QUERY_PARAMETER, deleteMethod.toString())
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Patch extends EndpointInput {
        public static final String OFFICE_QUERY_PARAMETER = "office";
        public static final String NEW_NAME_QUERY_PARAMETER = "name";
        private final String oldOutletName;
        private final String newOutletName;
        private final String officeId;

        private Patch(String officeId, String oldOutletName, String newOutletName) {
            this.oldOutletName = Objects.requireNonNull(oldOutletName, "Cannot access the outlet PATCH "
                    + "endpoint without an old outlet name");
            this.newOutletName = Objects.requireNonNull(newOutletName, "Cannot access the outlet PATCH "
                    + "endpoint without a new outlet name");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the outlet PATCH "
                    + "endpoint without an office id");
        }

        String oldOutletName() {
            return oldOutletName;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(NEW_NAME_QUERY_PARAMETER, newOutletName)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
