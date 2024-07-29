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

    public static GetAll getAll() {
        return new GetAll();
    }

    public static GetOne getOne(String outletId, String officeId) {
        return new GetOne(outletId, officeId);
    }

    public static Post post(Outlet outlet) {
        return new Post(outlet);
    }

    public static Delete delete(String outletId, String officeId, DeleteMethod deleteMethod) {
        return new Delete(outletId, officeId, deleteMethod);
    }

    public static Patch patch(String oldOutletId, String newOutletId, String officeId) {
        return new Patch(oldOutletId, newOutletId, officeId);
    }

    public static final class GetAll extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String PROJECT_ID_QUERY_PARAMETER = "project-id";
        private String projectId;
        private String officeId;

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

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(PROJECT_ID_QUERY_PARAMETER, projectId)
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class GetOne extends EndpointInput {
        public static final String OFFICE_QUERY_PARAMETER = "office";
        public static final String NAME_QUERY_PARAMETER = "name";
        private final String outletName;
        private final String officeId;

        private GetOne(String outletName, String officeId) {
            this.outletName = outletName;
            this.officeId = officeId;
        }

        String outletName() {
            return outletName;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        private final Outlet outletName;

        private Post(Outlet outlet) {
            this.outletName = Objects.requireNonNull(outlet, "Cannot access the outlet POST "
                    + "endpoint without an outlet");
        }

        Outlet outlet() {
            return outletName;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Delete extends EndpointInput {
        public static final String DELETE_METHOD_QUERY_PARAMETER = "delete-method";
        public static final String OFFICE_QUERY_PARAMETER = "office";
        public static final String NAME_QUERY_PARAMETER = "name";
        private final String outletName;
        private final String officeId;
        private final DeleteMethod deleteMethod;

        private Delete(String outletName, String officeId, DeleteMethod deleteMethod) {
            this.outletName = Objects.requireNonNull(outletName, "Cannot access the outlet DELETE "
                    + "endpoint without an outlet name");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the outlet DELETE "
                    + "endpoint without an office ID");
            this.deleteMethod = deleteMethod;
        }

        String outletName() {
            return outletName;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(DELETE_METHOD_QUERY_PARAMETER, deleteMethod.toString())
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(NAME_QUERY_PARAMETER, outletName)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Patch extends EndpointInput {
        public static final String OFFICE_QUERY_PARAMETER = "office";
        public static final String NEW_NAME_QUERY_PARAMETER = "name";
        private final String oldOutletName;
        private final String newOutletName;
        private final String officeId;

        private Patch(String oldOutletName, String newOutletName, String officeId) {
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
