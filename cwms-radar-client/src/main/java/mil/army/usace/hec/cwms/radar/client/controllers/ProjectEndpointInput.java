/*
 * MIT License
 *
 * Copyright (c) 2024 Hydrologic Engineering Center
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.time.Instant;
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

    public static GetProjectChildLocations getProjectChildLocations(String office) {
        return new GetProjectChildLocations(office);
    }

    public static ProjectEndpointInput.Patch patch(Project project) {
        // update
        return new ProjectEndpointInput.Patch(project);
    }

    public static ProjectEndpointInput.Post post(Project project) {
        // create
        return new ProjectEndpointInput.Post(project);
    }

    public static ProjectEndpointInput.StatusUpdate statusUpdate(String office, String projectId,
        String applicationId) {
        return new ProjectEndpointInput.StatusUpdate(office, projectId, applicationId);
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
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
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
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
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

    public static final class GetProjectChildLocations extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String PROJECT_LIKE_QUERY_PARAMETER = "project-like";
        static final String LOCATION_KIND_LIKE_QUERY_MASK = "location-kind-like";
        private final String officeId;
        private String projectIdMask;
        private String locationKindMask;

        private GetProjectChildLocations(String officeId) {
            this.officeId = Objects.requireNonNull(officeId, "Cannot request project locations without an office");
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(PROJECT_LIKE_QUERY_PARAMETER, projectIdMask)
                .addQueryParameter(LOCATION_KIND_LIKE_QUERY_MASK, locationKindMask)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }

        public GetProjectChildLocations locationKindMask(String locationKindMask) {
            this.locationKindMask = locationKindMask;
            return this;
        }

        public GetProjectChildLocations projectIdMask(String projectIdMask) {
            this.projectIdMask = projectIdMask;
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
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
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
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class StatusUpdate extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String APPLICATION_ID_QUERY_PARAMETER = "application-id";
        static final String SOURCE_ID_QUERY_PARAMETER = "source-id";
        static final String TIMESERIES_ID_QUERY_PARAMETER = "timeseries-id";
        static final String BEGIN_QUERY_PARAMETER = "begin";
        static final String END_QUERY_PARAMETER = "end";

        private final String officeId;
        private final String projectId;
        private final String applicationId;
        private String sourceId;
        private String timeSeriesId;
        private Instant begin;
        private Instant end;

        private StatusUpdate(String officeId, String projectId, String applicationId) {
            this.officeId = Objects.requireNonNull(officeId, "Cannot post Project status update without an office");
            this.projectId =
                Objects.requireNonNull(projectId, "Cannot post Project status update without a Project id");
            this.applicationId =
                Objects.requireNonNull(applicationId, "Cannot post Project status update without an application");
        }

        String projectId() {
            return projectId;
        }

        public StatusUpdate sourceId(String sourceId) {
            this.sourceId = sourceId;
            return this;
        }

        public StatusUpdate timeSeriesId(String timeSeriesId) {
            this.timeSeriesId = timeSeriesId;
            return this;
        }

        public StatusUpdate begin(Instant begin) {
            this.begin = begin;
            return this;
        }

        public StatusUpdate end(Instant end) {
            this.end = end;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder
                .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(APPLICATION_ID_QUERY_PARAMETER, applicationId)
                .addQueryParameter(SOURCE_ID_QUERY_PARAMETER, sourceId)
                .addQueryParameter(TIMESERIES_ID_QUERY_PARAMETER, timeSeriesId)
                .addQueryParameter(BEGIN_QUERY_PARAMETER,
                    Optional.ofNullable(begin).map(Instant::toString).orElse(null))
                .addQueryParameter(END_QUERY_PARAMETER, Optional.ofNullable(end).map(Instant::toString).orElse(null))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
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
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

}
