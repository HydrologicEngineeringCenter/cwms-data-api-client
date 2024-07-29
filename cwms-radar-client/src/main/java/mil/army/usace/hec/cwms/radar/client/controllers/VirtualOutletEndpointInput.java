package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.util.Objects;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.VirtualOutlet;


public final class VirtualOutletEndpointInput {
    private VirtualOutletEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll() {
        return new GetAll();
    }

    public static GetOne getOne(String officeId, String outletName, String projectId) {
        return new GetOne(officeId, outletName, projectId);
    }

    public static Post post(VirtualOutlet outlet) {
        return new Post(outlet);
    }

    public static Delete delete(String outletName, String officeId, String projectId, DeleteMethod deleteMethod) {
        return new Delete(outletName, officeId, projectId, deleteMethod);
    }

    public static Patch patch(String oldOutletName, String newOutletName, String projectId, String officeId) {
        return new Patch(oldOutletName, newOutletName, projectId, officeId);
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

        public String getOfficeId() {
            return officeId;
        }

        public String getProjectId() {
            return projectId;
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
        public static final String PROJECT_ID_QUERY_PARAMETER = "project-id";
        private final String officeId;
        private final String projectId;
        private final String outletName;

        private GetOne(String officeId, String outletName, String projectId) {
            this.officeId = Objects.requireNonNull(officeId, "Office Id required for getOne outlet endpoint");
            this.outletName = Objects.requireNonNull(outletName, "Outlet name required for getOne outlet endpoint");
            this.projectId = Objects.requireNonNull(projectId, "Project Id required for getOne outlet endpoint");
        }

        public String getOutletName() {
            return outletName;
        }

        public String getOfficeId() {
            return officeId;
        }

        public String getProjectId() {
            return projectId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(PROJECT_ID_QUERY_PARAMETER, projectId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        private final VirtualOutlet outletName;

        private Post(VirtualOutlet outletName) {
            this.outletName = Objects.requireNonNull(outletName, "Outlet required for outlet POST endpoint");
        }

        public VirtualOutlet outletName() {
            return outletName;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Delete extends EndpointInput {
        public static final String OFFICE_QUERY_PARAMETER = "office";
        public static final String DELETE_METHOD_QUERY_PARAMETER = "method";
        public static final String PROJECT_ID_QUERY_PARAMETER = "project-id";
        private final String officeId;
        private final String projectId;
        private final String outletName;
        private final DeleteMethod deleteMethod;

        private Delete(String outletName, String officeId, String projectId, DeleteMethod deleteMethod) {
            this.officeId = Objects.requireNonNull(officeId, "Office ID required for delete outlet endpoint");
            this.outletName = Objects.requireNonNull(outletName, "Outlet name required for delete outlet endpoint");
            this.projectId = Objects.requireNonNull(projectId, "Project ID required for delete outlet endpoint");
            this.deleteMethod = deleteMethod;
        }

        public String getOutletName() {
            return outletName;
        }

        public String getOfficeId() {
            return officeId;
        }

        public String getProjectId() {
            return projectId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(DELETE_METHOD_QUERY_PARAMETER, deleteMethod.toString())
                    .addQueryParameter(PROJECT_ID_QUERY_PARAMETER, projectId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Patch extends EndpointInput {
        public static final String OLD_OUTLET_NAME_QUERY_PARAMETER = "old-outlet-name";
        public static final String NEW_OUTLET_NAME_QUERY_PARAMETER = "name";
        public static final String OFFICE_QUERY_PARAMETER = "office";
        public static final String PROJECT_ID_QUERY_PARAMETER = "project-id";
        private final String newOutletName;
        private final String oldOutletName;
        private final String projectId;
        private final String officeId;

        private Patch(String oldOutletName, String newOutletName, String projectId, String officeId) {
            this.oldOutletName = Objects.requireNonNull(oldOutletName, "Old outlet name required for patch outlet endpoint");
            this.newOutletName = Objects.requireNonNull(newOutletName, "New outlet name required for patch outlet endpoint");
            this.projectId = Objects.requireNonNull(projectId, "Project ID required for patch outlet endpoint");
            this.officeId = Objects.requireNonNull(officeId, "Office ID required for patch outlet endpoint");
        }

        public String oldOutletName() {
            return oldOutletName;
        }

        public String getOfficeId() {
            return officeId;
        }

        public String getProjectId() {
            return projectId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OLD_OUTLET_NAME_QUERY_PARAMETER, oldOutletName)
                    .addQueryParameter(NEW_OUTLET_NAME_QUERY_PARAMETER, newOutletName)
                    .addQueryParameter(PROJECT_ID_QUERY_PARAMETER, projectId)
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
