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

import java.util.Objects;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.data.api.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.data.api.client.model.Turbine;

public final class TurbineEndpointInput {

    private TurbineEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll(String projectId, String officeId) {
        return new GetAll(projectId, officeId);
    }

    public static GetOne getOne(String turbineId, String officeId) {
        return new GetOne(turbineId, officeId);
    }

    public static Post post(Turbine turbine) {
        return new Post(turbine);
    }

    public static Delete delete(String turbineId, String officeId) {
        return new Delete(turbineId, officeId);
    }

    public static Patch patch(String oldTurbineId, String newTurbineId, String officeId) {
        return new Patch(oldTurbineId, newTurbineId, officeId);
    }

    public static final class GetAll extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String PROJECT_ID_QUERY_PARAMETER = "project-id";
        private final String projectId;
        private final String officeId;

        private GetAll(String projectId, String officeId) {
            this.projectId = Objects.requireNonNull(projectId, "Project Id required for getAll turbine endpoint");
            this.officeId = Objects.requireNonNull(officeId, "Project office Id required for getAll turbine endpoint");
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
        private final String turbineId;
        private final String officeId;

        private GetOne(String turbineId, String officeId) {
            this.turbineId = Objects.requireNonNull(turbineId, "Id required for getOne turbine endpoint");
            this.officeId = Objects.requireNonNull(officeId, "Office id required for getOne turbine endpoint");
        }

        String turbineId() {
            return turbineId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        static final String FAIL_IF_EXISTS_QUERY_PARAMETER = "fail-if-exists";
        private final Turbine turbine;
        private boolean failIfExists = true;

        private Post(Turbine turbine) {
            this.turbine = Objects.requireNonNull(turbine,
                    "Cannot access the turbine POST endpoint without a turbine value");
        }

        public Post failIfExists(boolean failIfExists) {
            this.failIfExists = failIfExists;
            return this;
        }

        Turbine turbine() {
            return turbine;
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
        private final String turbineId;
        private final String officeId;
        private DeleteMethod deleteMethod;

        private Delete(String turbineId, String officeId) {
            this.turbineId = Objects.requireNonNull(turbineId, "Cannot access the turbine DELETE endpoint without an id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the turbine DELETE endpoint without an office id");
        }

        public Delete deleteMethod(DeleteMethod deleteMethod) {
            this.deleteMethod = deleteMethod;
            return this;
        }

        String turbineId() {
            return turbineId;
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
        private final String oldTurbineId;
        private final String newTurbineId;
        private final String officeId;

        private Patch(String oldTurbineId, String newTurbineId, String officeId) {
            this.oldTurbineId = Objects.requireNonNull(oldTurbineId, "Cannot access the turbine PATCH endpoint without an old id");
            this.newTurbineId = Objects.requireNonNull(newTurbineId, "Cannot access the turbine PATCH endpoint without a new id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the turbine DELETE endpoint without an office id");
        }

        String turbineId() {
            return oldTurbineId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(NAME_QUERY_PARAMETER, newTurbineId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
