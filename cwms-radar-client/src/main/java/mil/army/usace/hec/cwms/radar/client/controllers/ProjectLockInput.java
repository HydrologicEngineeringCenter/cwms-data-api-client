package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.CACHE_CONTROL;

import java.util.Objects;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.radar.client.model.ProjectLock;


public final class ProjectLockInput {
    static final String OFFICE = "office";
    static final String OFFICE_MASK = "office-mask";
    static final String PROJECT_MASK = "project-mask";
    static final String APPLICATION_ID = "application-id";
    static final String APPLICATION_MASK = "application-mask";
    static final String REVOKE_TIMEOUT = "revoke-timeout";

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

    public static ProjectLockInput.LockRequest requestLock(ProjectLock lock) {
        return new ProjectLockInput.LockRequest(lock);
    }

    public static ProjectLockInput.LockRevokeDeny denyRevoke(String lockId) {
        return new LockRevokeDeny(lockId);
    }

    public static ProjectLockInput.LockRelease releaseLock(String officeId, String lockId) {
        return new LockRelease(officeId, lockId);
    }

    public static ProjectLockInput.LockRevoke revokeLock(String officeId, String projectId) {
        return new LockRevoke(officeId, projectId);
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
                    .addQueryHeader(CACHE_CONTROL, "no-cache, no-store") // Always fresh
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
                    .addQueryHeader(CACHE_CONTROL, "no-cache, no-store") // Always fresh
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }

        public String projectId() {
            return projectId;
        }
    }

    public static final class LockRequest extends EndpointInput {
        public static final String REVOKE_EXISTING = "revoke-existing";

        private final ProjectLock lock;
        private boolean revokeExisting = false;
        private int revokeTimeoutSeconds = 10;


        private LockRequest(ProjectLock lock) {
            this.lock = Objects.requireNonNull(lock, "Cannot retrieve a ProjectLock without a ProjectLock.");
        }

        public LockRequest revokeExisting(boolean revokeExisting) {
            this.revokeExisting = revokeExisting;
            return this;
        }

        public LockRequest revokeTimeoutSeconds(int revokeTimeout) {
            this.revokeTimeoutSeconds = revokeTimeout;
            return this;
        }

        public ProjectLock projectLock() {
            return lock;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(REVOKE_EXISTING, String.valueOf(revokeExisting))
                    .addQueryParameter(REVOKE_TIMEOUT, String.valueOf(revokeTimeoutSeconds))
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }

    }

    public static final class LockRevokeDeny extends EndpointInput {
        public static final String LOCK_ID = "lock-id";
        private final String lockId;

        private LockRevokeDeny(String lockId) {
            this.lockId = Objects.requireNonNull(lockId, "Cannot deny a lock revoke request without the lock id.");
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(LOCK_ID, lockId);
        }
    }

    public static final class LockRelease extends EndpointInput {
        public static final String LOCK_ID = "lock-id";
        private final String officeId;
        private final String lockId;

        private LockRelease(String officeId, String lockId) {
            this.officeId = Objects.requireNonNull(officeId, "Cannot release a lock without an officeId.");
            this.lockId = Objects.requireNonNull(lockId, "Cannot release a lock without the lock id.");
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE, officeId)
                    .addQueryParameter(LOCK_ID, lockId);
        }
    }

    public static final class LockRevoke extends EndpointInput {
        private final String officeId;
        private final String projectId;
        private int revokeTimeout = 10;

        private LockRevoke(String officeId, String projectId) {
            this.officeId = Objects.requireNonNull(officeId, "Cannot revoke a lock without an officeId.");
            this.projectId = Objects.requireNonNull(projectId, "Cannot revoke a lock without the projectId.");
        }

        public LockRevoke revokeTimeout(int revokeTimeout) {
            this.revokeTimeout = revokeTimeout;
            return this;
        }

        public String projectId() {
            return projectId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE, officeId)
                    .addQueryParameter(REVOKE_TIMEOUT, String.valueOf(revokeTimeout));
        }
    }


}
