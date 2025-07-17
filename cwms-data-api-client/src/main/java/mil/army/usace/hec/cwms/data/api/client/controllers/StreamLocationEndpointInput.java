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
import mil.army.usace.hec.cwms.data.api.client.model.StreamLocation;

import java.util.Objects;

public final class StreamLocationEndpointInput {

    private StreamLocationEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll() {
        return new GetAll();
    }

    public static GetOne getOne(String officeId, String streamId, String streamLocationId) {
        return new GetOne(officeId, streamId, streamLocationId);
    }

    public static Post post(StreamLocation streamLocation) {
        return new Post(streamLocation);
    }

    public static Delete delete(String officeId, String streamId, String streamLocationId) {
        return new Delete(officeId, streamId, streamLocationId);
    }

    public static Patch patch(StreamLocation streamLocation) {
        return new Patch(streamLocation);
    }

    public static final class GetAll extends EndpointInput {
        static final String OFFICE_MASK_QUERY_PARAMETER = "office-mask";
        static final String STREAM_ID_MASK_QUERY_PARAMETER = "stream-id-mask";
        static final String NAME_MASK_QUERY_PARAMETER = "name-mask";
        static final String STATION_UNITS_QUERY_PARAMETER = "station-unit";
        static final String STAGE_UNITS_QUERY_PARAMETER = "stage-unit";
        static final String AREA_UNITS_QUERY_PARAMETER = "area-unit";
        private String officeIdMask;
        private String streamIdMask;
        private String streamLocationIdMask;
        private String stationUnits;
        private String stageUnits;
        private String areaUnits;

        private GetAll() {
        }

        public GetAll withOfficeIdMask(String officeId) {
            this.officeIdMask = officeId;
            return this;
        }

        public GetAll withStreamIdMask(String streamId) {
            this.streamIdMask = streamId;
            return this;
        }

        public GetAll withStreamLocationIdMask(String streamLocationIdMask) {
            this.streamLocationIdMask = streamLocationIdMask;
            return this;
        }

        public GetAll withStationUnits(String stationUnits) {
            this.stationUnits = stationUnits;
            return this;
        }

        public GetAll withStageUnits(String stageUnits) {
            this.stageUnits = stageUnits;
            return this;
        }

        public GetAll withAreaUnits(String areaUnits) {
            this.areaUnits = areaUnits;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_MASK_QUERY_PARAMETER, officeIdMask)
                    .addQueryParameter(STREAM_ID_MASK_QUERY_PARAMETER, streamIdMask)
                    .addQueryParameter(NAME_MASK_QUERY_PARAMETER, streamLocationIdMask)
                    .addQueryParameter(STATION_UNITS_QUERY_PARAMETER, stationUnits)
                    .addQueryParameter(STAGE_UNITS_QUERY_PARAMETER, stageUnits)
                    .addQueryParameter(AREA_UNITS_QUERY_PARAMETER, areaUnits)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class GetOne extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String STREAM_ID_QUERY_PARAMETER = "stream-id";
        static final String STREAM_LOCATION_ID_QUERY_PARAMETER = "name";
        static final String STATION_UNITS_QUERY_PARAMETER = "station-unit";
        static final String STAGE_UNITS_QUERY_PARAMETER = "stage-unit";
        static final String AREA_UNITS_QUERY_PARAMETER = "area-unit";
        private final String streamLocationId;
        private final String officeId;
        private final String streamId;
        private String stationUnits;
        private String stageUnits;
        private String areaUnits;


        private GetOne(String officeId, String streamId, String streamLocationId) {
            this.streamLocationId = Objects.requireNonNull(streamLocationId, "Id required for getOne stream location endpoint");
            this.streamId = Objects.requireNonNull(streamId, "Stream id required for getOne stream location endpoint");
            this.officeId = Objects.requireNonNull(officeId, "Office id required for getOne stream location endpoint");
        }

        String streamLocationId() {
            return streamLocationId;
        }

        public GetOne withStationUnits(String stationUnits) {
            this.stationUnits = stationUnits;
            return this;
        }

        public GetOne withStageUnits(String stageUnits) {
            this.stageUnits = stageUnits;
            return this;
        }

        public GetOne withAreaUnits(String areaUnits) {
            this.areaUnits = areaUnits;
            return this;
        }


        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(STREAM_ID_QUERY_PARAMETER, streamId)
                    .addQueryParameter(STREAM_LOCATION_ID_QUERY_PARAMETER, streamLocationId)
                    .addQueryParameter(STATION_UNITS_QUERY_PARAMETER, stationUnits)
                    .addQueryParameter(STAGE_UNITS_QUERY_PARAMETER, stageUnits)
                    .addQueryParameter(AREA_UNITS_QUERY_PARAMETER, areaUnits)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        static final String FAIL_IF_EXISTS_QUERY_PARAMETER = "fail-if-exists";
        private final StreamLocation streamLocation;
        private boolean failIfExists = true;

        private Post(StreamLocation streamLocation) {
            this.streamLocation = Objects.requireNonNull(streamLocation,
                    "Cannot access the stream location POST endpoint without a stream location value");
        }

        public Post failIfExists(boolean failIfExists) {
            this.failIfExists = failIfExists;
            return this;
        }

        StreamLocation streamLocation() {
            return streamLocation;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER, Boolean.toString(failIfExists))
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Delete extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String STREAM_ID_QUERY_PARAMETER = "stream-id";
        private final String streamLocationId;
        private final String officeId;
        private final String streamId;

        private Delete(String officeId, String streamId, String streamLocationId) {
            this.streamLocationId = Objects.requireNonNull(streamLocationId, "Cannot access the stream location DELETE endpoint without an id");
            this.streamId = Objects.requireNonNull(streamId, "Cannot access the stream location DELETE endpoint without a stream id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the stream location DELETE endpoint without an office id");
        }

        String streamLocationId() {
            return streamLocationId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(STREAM_ID_QUERY_PARAMETER, streamId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Patch extends EndpointInput {
        private final StreamLocation streamLocation;

        private Patch(StreamLocation streamLocation) {
            this.streamLocation = Objects.requireNonNull(streamLocation,
                    "Cannot access the stream location PATCH endpoint without a stream location value");
        }

        StreamLocation streamLocation() {
            return streamLocation;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
