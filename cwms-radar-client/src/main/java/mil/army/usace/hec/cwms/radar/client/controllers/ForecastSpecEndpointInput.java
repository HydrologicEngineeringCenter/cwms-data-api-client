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

import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.radar.client.model.DeleteMethod;
import mil.army.usace.hec.cwms.radar.client.model.ForecastSpec;

import java.util.Objects;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.*;

public final class ForecastSpecEndpointInput {
    private ForecastSpecEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static ForecastSpecEndpointInput.GetOne getOne(String officeId, String specId, String designator) {
        return new ForecastSpecEndpointInput.GetOne(officeId, specId, designator);
    }
    public static ForecastSpecEndpointInput.GetAll getAll() {
        return new ForecastSpecEndpointInput.GetAll();
    }

    public static ForecastSpecEndpointInput.Post post(ForecastSpec forecastSpec) {
        return new ForecastSpecEndpointInput.Post(forecastSpec);
    }

    public static ForecastSpecEndpointInput.Patch patch(String originalSpecId, ForecastSpec updatedForecastSpec) {
        return new ForecastSpecEndpointInput.Patch(originalSpecId, updatedForecastSpec);
    }

    public static ForecastSpecEndpointInput.Delete delete(String officeId, String specId, String designator) {
        return new ForecastSpecEndpointInput.Delete(officeId, specId, designator);
    }

    public static final class GetOne extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String NAME_QUERY_PARAMETER = "name";
        static final String DESIGNATOR_QUERY_PARAMETER = "designator";
        private final String officeId;
        private final String specId;
        private final String designator;

        private GetOne(String officeId, String specId, String designator) {
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the forecast spec GET ONE endpoint without an office id");
            this.specId = Objects.requireNonNull(specId, "Cannot access the forecast spec GET ONE endpoint without a spec id");
            this.designator = Objects.requireNonNull(designator, "Cannot access the forecast spec GET ONE endpoint without a designator");

        }

        String specId() {
            return specId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(NAME_QUERY_PARAMETER, specId)
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(DESIGNATOR_QUERY_PARAMETER, designator)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class GetAll extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String ID_MASK_QUERY_PARAMETER = "id-mask";
        static final String LOCATION_MASK_QUERY_PARAMETER = "location-mask";
        static final String DESIGNATOR_MASK_QUERY_PARAMETER = "designator-mask";
        static final String SOURCE_ENTITY_QUERY_PARAMETER = "source-entity";
        private String officeId;
        private String specIdMask;
        private String locationIdMask;
        private String designatorMask;
        private String sourceEntityId;


        private GetAll() {

        }

        public ForecastSpecEndpointInput.GetAll officeId(String officeId) {
            this.officeId = officeId;
            return this;
        }

        public ForecastSpecEndpointInput.GetAll specIdMask(String specIdMask) {
            this.specIdMask = specIdMask;
            return this;
        }

        public ForecastSpecEndpointInput.GetAll locationIdMask(String locationIdMask) {
            this.locationIdMask = locationIdMask;
            return this;
        }

        public ForecastSpecEndpointInput.GetAll designatorMask(String designatorMask) {
            this.designatorMask = designatorMask;
            return this;
        }

        public ForecastSpecEndpointInput.GetAll sourceEntityId(String sourceEntityId) {
            this.sourceEntityId = sourceEntityId;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(ID_MASK_QUERY_PARAMETER, specIdMask)
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(LOCATION_MASK_QUERY_PARAMETER, locationIdMask)
                    .addQueryParameter(DESIGNATOR_MASK_QUERY_PARAMETER, designatorMask)
                    .addQueryParameter(SOURCE_ENTITY_QUERY_PARAMETER, sourceEntityId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Post extends EndpointInput {
        private final ForecastSpec forecastSpec;

        private Post(ForecastSpec forecastSpec) {
            this.forecastSpec = Objects.requireNonNull(forecastSpec, "Cannot access the forecast spec POST endpoint without a forecast spec");
        }

        ForecastSpec forecastSpec() {
            return forecastSpec;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }

    public static final class Patch extends EndpointInput {
        static final String NAME_PARAMETER_QUERY = "name";
        private final ForecastSpec forecastSpec;
        private final String originalSpecId;

        private Patch(String originalSpecId, ForecastSpec patchedForecastSpec) {
            this.originalSpecId = Objects.requireNonNull(originalSpecId, "Cannot access the forecast spec PATCH endpoint without the spec id that is to be updated");
            this.forecastSpec = Objects.requireNonNull(patchedForecastSpec, "Cannot PATCH forecast spec endpoint without a spec");
        }

        String originalSpecId() {
            return originalSpecId;
        }

        ForecastSpec forecastSpec() {
            return forecastSpec;
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
        static final String DELETE_METHOD_PARAMETER_QUERY = "method";
        private final String officeId;
        private final String specId;
        private final String designator;
        private DeleteMethod deleteMethod = DeleteMethod.KEY;

        private Delete(String officeId, String specId, String designator) {
            this.specId = Objects.requireNonNull(specId, "Cannot access the forecast spec DELETE endpoint without a spec identifier");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the forecast spec DELETE endpoint without an office id");
            this.designator = Objects.requireNonNull(designator, "Cannot access the forecast spec DELETE endpoint without a designator");
        }

        public ForecastSpecEndpointInput.Delete deleteMethod(DeleteMethod deleteMethod) {
            this.deleteMethod = deleteMethod;
            return this;
        }

        String specId() {
            return specId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(NAME_PARAMETER_QUERY, specId)
                    .addQueryParameter(DESIGNATOR_PARAMETER_QUERY, designator)
                    .addQueryParameter(DELETE_METHOD_PARAMETER_QUERY, deleteMethod.toString())
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
