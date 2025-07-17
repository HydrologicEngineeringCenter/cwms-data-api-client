package mil.army.usace.hec.cwms.data.api.client.controllers;

import java.util.Objects;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.data.api.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.data.api.client.model.Lock;


public final class LockEndpointInput {
    private LockEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll(String officeId, String projectId) {
        return new GetAll(officeId, projectId);
    }

    public static GetOne getOne(String officeId, String lockId) {
        return new GetOne(officeId, lockId);
    }

    public static Post post(Lock lock) {
        return new Post(lock);
    }

    public static Delete delete(String officeId, String lockId) {
        return new Delete(officeId, lockId);
    }

    public static Patch patch(String officeId, String oldLockId, String newLockId) {
        return new Patch(officeId, oldLockId, newLockId);
    }

    public static final class GetAll extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String PROJECT_ID_QUERY_PARAMETER = "project-id";
        private final String projectId;
        private final String officeId;

        private GetAll(String officeId, String projectId) {
            this.projectId = Objects.requireNonNull(projectId, "Cannot access the lock GET "
                    + "endpoint without a project ID");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the lock GET "
                    + "endpoint without an office ID");
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            httpRequestBuilder.addQueryParameter(PROJECT_ID_QUERY_PARAMETER, projectId)
                .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
            return httpRequestBuilder;
        }
    }

    public static final class GetOne extends EndpointInput {
        public static final String OFFICE_QUERY_PARAMETER = "office";
        public static final String UNIT_QUERY_PARAMETER = "unit";

        private final String lockName;
        private final String officeId;
        private String unit;

        private GetOne(String officeId, String lockName) {
            this.lockName = Objects.requireNonNull(lockName, "Cannot access the lock GET "
                    + "endpoint without a lock name");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the lock GET "
                    + "endpoint without an office ID");
        }

        public GetOne unit(String unit) {
            this.unit = unit;
            return this;
        }

        String lockName() {
            return lockName;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(UNIT_QUERY_PARAMETER, unit)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        private final Lock lock;
        public static final String FAIL_IF_EXISTS_QUERY_PARAMETER = "fail-if-exists";
        private boolean failIfExists = true;

        private Post(Lock lock) {
            this.lock = Objects.requireNonNull(lock, "Cannot store a lock without a lock object");
        }

        public Post failIfExists(boolean failIfExists) {
            this.failIfExists = failIfExists;
            return this;
        }

        Lock lock() {
            return lock;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1)
                    .addQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER, String.valueOf(failIfExists));
        }
    }

    public static final class Delete extends EndpointInput {
        public static final String OFFICE_QUERY_PARAMETER = "office";
        public static final String METHOD_QUERY_PARAMETER = "method";

        private final String officeId;
        private final String lockName;
        private DeleteMethod method = DeleteMethod.KEY;

        private Delete(String officeId, String lockName) {
            this.officeId = Objects.requireNonNull(officeId, "Cannot delete a lock without an office ID");
            this.lockName = Objects.requireNonNull(lockName, "Cannot delete a lock without a lock name");
        }

        public Delete method(DeleteMethod method) {
            this.method = method;
            return this;
        }

        String lockName() {
            return lockName;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(METHOD_QUERY_PARAMETER, method.toString())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Patch extends EndpointInput {
        public static final String OFFICE_QUERY_PARAMETER = "office";
        public static final String NAME_QUERY_PARAMETER = "name";

        private final String officeId;
        private final String oldLockName;
        private final String newLockName;

        private Patch(String officeId, String oldLockName, String newLockName) {
            this.officeId = Objects.requireNonNull(officeId, "Cannot rename a lock without an office ID");
            this.oldLockName = Objects.requireNonNull(oldLockName, "Cannot rename a lock without an old lock name");
            this.newLockName = Objects.requireNonNull(newLockName, "Cannot rename a lock without a new lock name");
        }

        String oldLockName() {
            return oldLockName;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(NAME_QUERY_PARAMETER, newLockName)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }


    }

}
