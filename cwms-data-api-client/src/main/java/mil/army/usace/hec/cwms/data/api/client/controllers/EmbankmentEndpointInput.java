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

package mil.army.usace.hec.cwms.data.api.client.controllers;

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.data.api.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.data.api.client.model.Embankment;

import java.util.Objects;

public final class EmbankmentEndpointInput {

    private EmbankmentEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll(String projectId, String officeId) {
        return new GetAll(projectId, officeId);
    }

    public static GetOne getOne(String embankmentId, String officeId) {
        return new GetOne(embankmentId, officeId);
    }

    public static Post post(Embankment embankment) {
        return new Post(embankment);
    }

    public static Delete delete(String embankmentId, String officeId) {
        return new Delete(embankmentId, officeId);
    }

    public static Patch patch(String oldEmbankmentId, String newEmbankmentId, String officeId) {
        return new Patch(oldEmbankmentId, newEmbankmentId, officeId);
    }

    public static final class GetAll extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String PROJECT_ID_QUERY_PARAMETER = "project-id";
        private final String projectId;
        private final String officeId;

        private GetAll(String projectId, String officeId) {
            this.projectId = Objects.requireNonNull(projectId, "Project Id required for getAll embankment endpoint");
            this.officeId = Objects.requireNonNull(officeId, "Project office Id required for getAll embankment endpoint");
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(PROJECT_ID_QUERY_PARAMETER, projectId)
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class GetOne extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        private final String embankmentId;
        private final String officeId;

        private GetOne(String embankmentId, String officeId) {
            this.embankmentId = Objects.requireNonNull(embankmentId, "Id required for getOne embankment endpoint");
            this.officeId = Objects.requireNonNull(officeId, "Office id required for getOne embankment endpoint");
        }

        public String embankmentId() {
            return embankmentId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        static final String FAIL_IF_EXISTS_QUERY_PARAMETER = "fail-if-exists";
        private final Embankment embankment;
        private boolean failIfExists = true;

        private Post(Embankment embankment) {
            this.embankment = Objects.requireNonNull(embankment,
                    "Cannot access the embankment POST endpoint without a embankment value");
        }

        public Post failIfExists(boolean failIfExists) {
            this.failIfExists = failIfExists;
            return this;
        }

        Embankment embankment() {
            return embankment;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER, Boolean.toString(failIfExists))
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Delete extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String METHOD_QUERY_PARAMETER = "method";
        private final String embankmentId;
        private final String officeId;
        private DeleteMethod deleteMethod;

        private Delete(String embankmentId, String officeId) {
            this.embankmentId = Objects.requireNonNull(embankmentId, "Cannot access the embankment DELETE endpoint without an id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the embankment DELETE endpoint without an office id");
        }

        public Delete deleteMethod(DeleteMethod deleteMethod) {
            this.deleteMethod = deleteMethod;
            return this;
        }

        String embankmentId() {
            return embankmentId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(METHOD_QUERY_PARAMETER, deleteMethod == null ? null : deleteMethod.toString())
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Patch extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String NAME_QUERY_PARAMETER = "name";
        private final String oldEmbankmentId;
        private final String newEmbankmentId;
        private final String officeId;

        private Patch(String oldEmbankmentId, String newEmbankmentId, String officeId) {
            this.oldEmbankmentId = Objects.requireNonNull(oldEmbankmentId, "Cannot access the embankment PATCH endpoint without an old id");
            this.newEmbankmentId = Objects.requireNonNull(newEmbankmentId, "Cannot access the embankment PATCH endpoint without a new id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the embankment DELETE endpoint without an office id");
        }

        String embankmentId() {
            return oldEmbankmentId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(NAME_QUERY_PARAMETER, newEmbankmentId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
