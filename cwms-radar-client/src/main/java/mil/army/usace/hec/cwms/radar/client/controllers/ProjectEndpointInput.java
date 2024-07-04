package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.util.Objects;
import java.util.Optional;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.Project;


public final class ProjectEndpointInput {
    static final String OFFICE_QUERY_PARAMETER = "office";

    private ProjectEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static ProjectEndpointInput.GetOne getOne(String projectId, String officeId) {
        return new ProjectEndpointInput.GetOne(projectId, officeId);
    }

    public static ProjectEndpointInput.GetAll getAll() {
        return new ProjectEndpointInput.GetAll();
    }

    public static ProjectEndpointInput.Patch patch(Project project) {
        // update
        return new ProjectEndpointInput.Patch(project);
    }

    public static ProjectEndpointInput.Post post(Project project) {
        // create
        return new ProjectEndpointInput.Post(project);
    }

    public static ProjectEndpointInput.Delete delete(String projectId, String office, DeleteMethod deleteMethod) {
        return new ProjectEndpointInput.Delete(projectId, office, deleteMethod);
    }

    public static final class GetOne extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        private final String officeId;
        private final String projectId;

        private GetOne(String projectId, String officeId) {
            this.projectId = Objects.requireNonNull(projectId, "Cannot retrieve Project data without a project id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot retrieve Project data without an office id");
        }

        String projectId() {
            return projectId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class GetAll extends EndpointInput {
        static final String PAGE_SIZE_QUERY_PARAMETER = "page-size";
        static final String PAGE_QUERY_PARAMETER = "page";
        static final String ID_MASK = "id-mask";
        private String officeId;
        private String projectIdMask;
        private String page;
        private Integer pageSize;

        private GetAll() {
            //Empty private ctor
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            String pageSizeString = Optional.ofNullable(pageSize).map(Object::toString).orElse(null);
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(ID_MASK, projectIdMask)
                    .addQueryParameter(PAGE_QUERY_PARAMETER, page)
                    .addQueryParameter(PAGE_SIZE_QUERY_PARAMETER, pageSizeString)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }

        public ProjectEndpointInput.GetAll officeId(String officeId) {
            this.officeId = officeId;
            return this;
        }

        public ProjectEndpointInput.GetAll projectIdMask(String levelIdMask) {
            this.projectIdMask = levelIdMask;
            return this;
        }

        public ProjectEndpointInput.GetAll page(String page) {
            this.page = page;
            return this;
        }

        public ProjectEndpointInput.GetAll pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

    }

    public static final class Patch extends EndpointInput {
        private final Project project;

        private Patch(Project project) {
            this.project = Objects.requireNonNull(project, "Cannot patch Project without Project data");
        }

        Project project() {
            return project;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Post extends EndpointInput {
        private final Project project;

        private Post(Project project) {
            this.project = Objects.requireNonNull(project, "Cannot store Project without a Project object");
        }

        Project project() {
            return project;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Delete extends EndpointInput {
        static final String DELETE_METHOD_QUERY_PARAMETER = "method";
        static final String NAME_QUERY_PARAMETER = "name";
        private final String projectId;
        private final String office;
        private final DeleteMethod deleteMethod;

        private Delete(String projectId, String office, DeleteMethod deleteMethod) {
            this.projectId = Objects.requireNonNull(projectId, "Cannot delete Project without a project id");
            this.office = Objects.requireNonNull(office, "Cannot delete Project without an office id");
            this.deleteMethod = Objects.requireNonNull(deleteMethod, "Cannot delete rating without a delete method");
        }

        String projectId() {
            return projectId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder
                    .addQueryParameter(DELETE_METHOD_QUERY_PARAMETER, deleteMethod.toString())
                    .addQueryParameter(NAME_QUERY_PARAMETER, projectId)
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, office)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

}
