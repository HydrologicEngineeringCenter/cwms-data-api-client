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

import java.time.Instant;
import java.util.Objects;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import mil.army.usace.hec.cwms.data.api.client.model.ForecastInstance;
import mil.army.usace.hec.cwms.data.api.client.model.ForecastSpec;

public final class ForecastInstanceEndpointInput {
    private ForecastInstanceEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static ForecastInstanceEndpointInput.GetOne getOne(ForecastSpec forecastSpec, Instant forecastDate, Instant issueDate) {
        return new ForecastInstanceEndpointInput.GetOne(forecastSpec, forecastDate, issueDate);
    }
    public static ForecastInstanceEndpointInput.GetAll getAll() {
        return new ForecastInstanceEndpointInput.GetAll();
    }

    public static ForecastInstanceEndpointInput.Post post(ForecastInstance forecastInstance) {
        return new ForecastInstanceEndpointInput.Post(forecastInstance);
    }

    public static ForecastInstanceEndpointInput.Patch patch(String specId, ForecastInstance forecastInstance) {
        return new ForecastInstanceEndpointInput.Patch(specId, forecastInstance);
    }

    public static ForecastInstanceEndpointInput.Delete delete(ForecastSpec forecastSpec, Instant forecastDate, Instant issueDate) {
        return new ForecastInstanceEndpointInput.Delete(forecastSpec, forecastDate, issueDate);
    }

    public static final class GetOne extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String NAME_QUERY_PARAMETER = "name";
        static final String DESIGNATOR_QUERY_PARAMETER = "designator";
        static final String FORECAST_DATE_PARAMETER_QUERY = "forecast-date";
        static final String ISSUE_DATE_PARAMETER_QUERY = "issue-date";
        private final Instant forecastDate;
        private final Instant issueDate;
        private final ForecastSpec forecastSpec;

        private GetOne(ForecastSpec forecastSpec, Instant forecastDate, Instant issueDate) {
            this.forecastSpec = Objects.requireNonNull(forecastSpec, "Cannot access the forecast instance GET ONE endpoint without a forecast spec");
            Objects.requireNonNull(forecastSpec.getOfficeId(), "Cannot access the forecast instance GET ONE endpoint without an office id");
            Objects.requireNonNull(forecastSpec.getSpecId(), "Cannot access the forecast instance GET ONE endpoint without a spec id");
            Objects.requireNonNull(forecastSpec.getDesignator(), "Cannot access the forecast instance GET ONE endpoint without a designator");
            this.forecastDate = Objects.requireNonNull(forecastDate, "Cannot access the forecast instance GET ONE endpoint without a forecast date");
            this.issueDate = Objects.requireNonNull(issueDate, "Cannot access the forecast instance GET ONE endpoint without an issue date");
        }

        ForecastSpec spec() {
            return forecastSpec;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(NAME_QUERY_PARAMETER, forecastSpec.getSpecId())
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, forecastSpec.getOfficeId())
                    .addQueryParameter(DESIGNATOR_QUERY_PARAMETER, forecastSpec.getDesignator())
                    .addQueryParameter(FORECAST_DATE_PARAMETER_QUERY, forecastDate.toString())
                    .addQueryParameter(ISSUE_DATE_PARAMETER_QUERY, issueDate.toString())
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class GetAll extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String NAME_QUERY_PARAMETER = "name";
        static final String DESIGNATOR_QUERY_PARAMETER = "designator";
        private String officeId;
        private String specId;
        private String designator;

        private GetAll() {
            //no required parameters
        }

        public ForecastInstanceEndpointInput.GetAll spec(ForecastSpec spec)
        {
            this.officeId = spec.getOfficeId();
            this.specId = spec.getSpecId();
            this.designator = spec.getDesignator();
            return this;
        }

        public ForecastInstanceEndpointInput.GetAll officeId(String officeId) {
            this.officeId = officeId;
            return this;
        }

        public ForecastInstanceEndpointInput.GetAll specId(String specId) {
            this.specId = specId;
            return this;
        }

        public ForecastInstanceEndpointInput.GetAll designator(String designator) {
            this.designator = designator;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(NAME_QUERY_PARAMETER, specId)
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(DESIGNATOR_QUERY_PARAMETER, designator)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Post extends EndpointInput {
        private final ForecastInstance forecastInstance;
        private Post(ForecastInstance forecastInstance) {
            this.forecastInstance = Objects.requireNonNull(forecastInstance, "Cannot access the forecast POST endpoint without a forecast instance");
        }

        ForecastInstance forecastInstance() {
            return forecastInstance;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Patch extends EndpointInput {
        static final String NAME_PARAMETER_QUERY = "name";
        private final ForecastInstance forecastInstance;
        private final String originalSpecId;

        private Patch(String originalSpecId, ForecastInstance patchedForecastInstance) {
            this.originalSpecId = Objects.requireNonNull(originalSpecId, "Cannot access the forecast PATCH endpoint without the spec id that is to be updated");
            this.forecastInstance = Objects.requireNonNull(patchedForecastInstance, "Cannot PATCH forecast endpoint without a spec");
        }

        String originalSpecId() {
            return originalSpecId;
        }

        ForecastInstance forecastInstance() {
            return forecastInstance;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(NAME_PARAMETER_QUERY, originalSpecId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Delete extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String NAME_PARAMETER_QUERY = "name";
        static final String DESIGNATOR_PARAMETER_QUERY = "designator";
        static final String FORECAST_DATE_PARAMETER_QUERY = "forecast-date";
        static final String ISSUE_DATE_PARAMETER_QUERY = "issue-date";
        private final Instant forecastDate;
        private final Instant issueDate;
        private final ForecastSpec forecastSpec;

        private Delete(ForecastSpec forecastSpec, Instant forecastDate, Instant issueDate) {
            this.forecastSpec = Objects.requireNonNull(forecastSpec, "Cannot access the forecast instance DELETE endpoint without a forecast spec");
            Objects.requireNonNull(forecastSpec.getSpecId(), "Cannot access the forecast instance DELETE endpoint without a spec identifier");
            Objects.requireNonNull(forecastSpec.getOfficeId(), "Cannot access the forecast instance DELETE endpoint without an office id");
            Objects.requireNonNull(forecastSpec.getDesignator(), "Cannot access the forecast instance DELETE endpoint without a designator");
            this.forecastDate = Objects.requireNonNull(forecastDate, "Cannot access the forecast instance DELETE endpoint without a forecast date");
            this.issueDate = Objects.requireNonNull(issueDate, "Cannot access the forecast instance DELETE endpoint without an issue date");
        }

        ForecastSpec spec() {
            return forecastSpec;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, forecastSpec.getOfficeId())
                    .addQueryParameter(NAME_PARAMETER_QUERY, forecastSpec.getSpecId())
                    .addQueryParameter(DESIGNATOR_PARAMETER_QUERY, forecastSpec.getDesignator())
                    .addQueryParameter(FORECAST_DATE_PARAMETER_QUERY, forecastDate.toString())
                    .addQueryParameter(ISSUE_DATE_PARAMETER_QUERY, issueDate.toString())
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
