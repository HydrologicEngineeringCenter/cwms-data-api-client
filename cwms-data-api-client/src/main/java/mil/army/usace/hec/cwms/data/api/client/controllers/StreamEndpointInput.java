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
import mil.army.usace.hec.cwms.data.api.client.model.Stream;

import java.util.Objects;

public final class StreamEndpointInput {

    private StreamEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll() {
        return new GetAll();
    }

    public static GetOne getOne(String officeId, String streamId) {
        return new GetOne(officeId, streamId);
    }

    public static Post post(Stream stream) {
        return new Post(stream);
    }

    public static Delete delete(String officeId, String streamId) {
        return new Delete(officeId, streamId);
    }

    public static Patch patch(String officeId, String oldStreamId, String newStreamId) {
        return new Patch(officeId, oldStreamId, newStreamId);
    }

    public static final class GetAll extends EndpointInput {
        static final String OFFICE_MASK_QUERY_PARAMETER = "office-mask";
        static final String FLOWS_INTO_STREAM_MASK_QUERY_PARAMETER = "flows-into-stream-id-mask";
        static final String DIVERTS_FROM_STREAM_MASK_QUERY_PARAMETER = "diverts-from-stream-id-mask";
        static final String STATION_UNITS_QUERY_PARAMETER = "station-unit";
        private String officeIdMask;
        private String flowsIntoStreamIdMask;
        private String divertsFromStreamIdMask;
        private String stationUnits;

        private GetAll() {
            //all fields are optional masks
        }

        public GetAll withOfficeIdMask(String officeId) {
            this.officeIdMask = officeId;
            return this;
        }

        public GetAll withFlowsIntoStreamIdMask(String flowsIntoStreamIdMask) {
            this.flowsIntoStreamIdMask = flowsIntoStreamIdMask;
            return this;
        }

        public GetAll withDivertsFromStreamIdMask(String divertsFromStreamIdMask) {
            this.divertsFromStreamIdMask = divertsFromStreamIdMask;
            return this;
        }

        public GetAll withStationUnits(String stationUnits) {
            this.stationUnits = stationUnits;
            return this;
        }

        String getFlowsIntoStreamIdMask() {
            return flowsIntoStreamIdMask;
        }

        String getDivertsFromStreamIdMask() {
            return divertsFromStreamIdMask;
        }

        String getStationUnits() {
            return stationUnits;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_MASK_QUERY_PARAMETER, officeIdMask)
                    .addQueryParameter(FLOWS_INTO_STREAM_MASK_QUERY_PARAMETER, getFlowsIntoStreamIdMask())
                    .addQueryParameter(DIVERTS_FROM_STREAM_MASK_QUERY_PARAMETER, getDivertsFromStreamIdMask())
                    .addQueryParameter(STATION_UNITS_QUERY_PARAMETER, getStationUnits())
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class GetOne extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String STATION_UNITS_QUERY_PARAMETER = "station-unit";
        private final String streamId;
        private final String officeId;
        private String stationUnits;

        private GetOne(String officeId, String streamId) {
            this.streamId = Objects.requireNonNull(streamId, "Id required for getOne stream endpoint");
            this.officeId = Objects.requireNonNull(officeId, "Office id required for getOne stream endpoint");
        }

        public GetOne withStationUnits(String stationUnits)
        {
            this.stationUnits = stationUnits;
            return this;
        }

        String streamId() {
            return streamId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(STATION_UNITS_QUERY_PARAMETER, stationUnits)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        static final String FAIL_IF_EXISTS_QUERY_PARAMETER = "fail-if-exists";
        private final Stream stream;
        private boolean failIfExists = true;

        private Post(Stream stream) {
            this.stream = Objects.requireNonNull(stream,
                    "Cannot access the stream POST endpoint without a stream value");
        }

        public Post failIfExists(boolean failIfExists) {
            this.failIfExists = failIfExists;
            return this;
        }

        Stream stream() {
            return stream;
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
        private final String streamId;
        private final String officeId;
        private DeleteMethod deleteMethod;

        private Delete(String officeId, String streamId) {
            this.streamId = Objects.requireNonNull(streamId, "Cannot access the stream DELETE endpoint without an id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the stream DELETE endpoint without an office id");
        }

        public Delete deleteMethod(DeleteMethod deleteMethod) {
            this.deleteMethod = deleteMethod;
            return this;
        }

        String streamId() {
            return streamId;
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
        private final String oldStreamId;
        private final String newStreamId;
        private final String officeId;

        private Patch(String officeId, String oldStreamId, String newStreamId) {
            this.oldStreamId = Objects.requireNonNull(oldStreamId, "Cannot access the stream PATCH endpoint without an old id");
            this.newStreamId = Objects.requireNonNull(newStreamId, "Cannot access the stream PATCH endpoint without a new id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the stream DELETE endpoint without an office id");
        }

        String streamId() {
            return oldStreamId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(NAME_QUERY_PARAMETER, newStreamId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}