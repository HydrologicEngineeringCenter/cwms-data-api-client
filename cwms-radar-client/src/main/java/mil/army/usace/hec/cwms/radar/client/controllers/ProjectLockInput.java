package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.util.Objects;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;


public final class ProjectLockInput {
    static final String OFFICE = "office";
    static final String OFFICE_MASK = "office-mask";
    static final String PROJECT_MASK = "project-mask";
    static final String APPLICATION_ID = "application-id";
    static final String APPLICATION_MASK = "application-mask";

    private ProjectLockInput() {
        throw new AssertionError("factory class");
    }

    public static ProjectLockInput.GetAll getAll(String officeMask,
                                                 String projectMask, String applicationMask) {
        return new ProjectLockInput.GetAll(officeMask, projectMask, applicationMask);
    }

    public static ProjectLockInput.GetOne getOne(String officeId,  String projectId, String applicationId) {
        return new ProjectLockInput.GetOne(officeId, projectId, applicationId);
    }

    public static final class GetAll extends EndpointInput {
        private final String officeMask;
        private final String projectMask;
        private final String applicationMask;

        private GetAll(String officeMask, String projectMask, String applicationMask) {
            this.officeMask = Objects.requireNonNull(officeMask, "Cannot retrieve ProjectLocks without an officeMask.");
            this.projectMask = projectMask;
            this.applicationMask = applicationMask;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder
                    .addQueryParameter(OFFICE_MASK, officeMask)
                    .addQueryParameter(PROJECT_MASK, projectMask)
                    .addQueryParameter(APPLICATION_MASK, applicationMask)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class GetOne extends EndpointInput {
        private final String officeId;
        private final String projectId;
        private final String application;

        private GetOne(String officeId, String projectId, String applicationId) {
            this.officeId = Objects.requireNonNull(officeId, "Cannot retrieve a ProjectLock without an Office Id.");
            this.projectId = Objects.requireNonNull(projectId, "Cannot retrieve a ProjectLock without a Project Id.");
            this.application = Objects.requireNonNull(applicationId, "Cannot retrieve a ProjectLock without an Application Id.");
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder
                    .addQueryParameter(OFFICE, officeId)
                    .addQueryParameter(APPLICATION_ID, application)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }

        public String projectId() {
            return projectId;
        }
    }



}
