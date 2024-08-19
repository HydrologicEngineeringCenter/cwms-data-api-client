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

    public static GetAll getAll(String officeId, String projectId) {
        return new GetAll(officeId, projectId);
    }

    public static GetOne getOne(String officeId, String projectId, String outletName) {
        return new GetOne(officeId, projectId, outletName);
    }

    public static Post post(VirtualOutlet outlet) {
        return new Post(outlet);
    }

    public static Delete delete(String officeId, String projectId, String outletName) {
        return new Delete(officeId, projectId, outletName);
    }

    public static Patch patch(String officeId, String projectId, String oldOutletName, String newOutletName) {
        return new Patch(officeId, projectId, oldOutletName, newOutletName);
    }

    public static final class GetAll extends EndpointInput {
        private final String projectId;
        private final String officeId;

        private GetAll(String officeId, String projectId) {
            this.projectId = Objects.requireNonNull(projectId, "Cannot access the outlet GET "
                    + "endpoint without a project ID");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the outlet GET "
                    + "endpoint without an office ID");
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
        public static final String OFFICE_QUERY_PARAMETER = "office";
        private final String officeId;
        private final String projectId;
        private final String outletName;

        private GetOne(String officeId, String projectId, String outletName) {
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
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        private final VirtualOutlet outletName;
        private static final String FAIL_IF_EXISTS_QUERY_PARAMETER = "fail-if-exists";
        private boolean failIfExists = true;

        private Post(VirtualOutlet outletName) {
            this.outletName = Objects.requireNonNull(outletName, "Outlet required for outlet POST endpoint");
        }

        public Post failIfExists(boolean failIfExists) {
            this.failIfExists = failIfExists;
            return this;
        }

        public VirtualOutlet outletName() {
            return outletName;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER, String.valueOf(failIfExists))
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Delete extends EndpointInput {
        public static final String OFFICE_QUERY_PARAMETER = "office";
        public static final String DELETE_METHOD_QUERY_PARAMETER = "method";
        private final String officeId;
        private final String projectId;
        private final String outletName;
        private DeleteMethod deleteMethod;

        private Delete(String officeId, String projectId, String outletName) {
            this.officeId = Objects.requireNonNull(officeId, "Office ID required for delete outlet endpoint");
            this.outletName = Objects.requireNonNull(outletName, "Outlet name required for delete outlet endpoint");
            this.projectId = Objects.requireNonNull(projectId, "Project ID required for delete outlet endpoint");
        }

        public Delete deleteMethod(DeleteMethod deleteMethod) {
            this.deleteMethod = deleteMethod;
            return this;
        }

        public DeleteMethod getDeleteMethod() {
            return deleteMethod;
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
            return httpRequestBuilder.addQueryParameter(DELETE_METHOD_QUERY_PARAMETER, deleteMethod.toString())
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Patch extends EndpointInput {
        public static final String NEW_OUTLET_NAME_QUERY_PARAMETER = "name";
        public static final String OFFICE_QUERY_PARAMETER = "office";
        private final String newOutletName;
        private final String oldOutletName;
        private final String projectId;
        private final String officeId;

        private Patch(String officeId, String projectId, String oldOutletName, String newOutletName) {
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
            return httpRequestBuilder.addQueryParameter(NEW_OUTLET_NAME_QUERY_PARAMETER, newOutletName)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
