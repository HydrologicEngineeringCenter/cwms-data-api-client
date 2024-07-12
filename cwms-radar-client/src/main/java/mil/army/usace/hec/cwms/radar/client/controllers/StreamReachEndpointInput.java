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
import mil.army.usace.hec.cwms.radar.client.model.StreamReach;

import java.util.Objects;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

public final class StreamReachEndpointInput {

    private StreamReachEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll() {
        return new GetAll();
    }

    public static GetOne getOne(String officeId, String streamId, String reachId) {
        return new GetOne(officeId, streamId, reachId);
    }

    public static Post post(StreamReach streamReach) {
        return new Post(streamReach);
    }

    public static Delete delete(String officeId, String reachId) {
        return new Delete(officeId, reachId);
    }

    public static Patch patch(String officeId, String oldReachId, String newReachId) {
        return new Patch(officeId, oldReachId, newReachId);
    }

    public static final class GetAll extends EndpointInput {
        static final String OFFICE_MASK_QUERY_PARAMETER = "office-mask";
        static final String STREAM_ID_MASK_QUERY_PARAMETER = "stream-id-mask";
        static final String REACH_ID_MASK_QUERY_PARAMETER = "reach-id-mask";
        static final String CONFIGURATION_ID_MASK_QUERY_PARAMETER = "configuration-id-mask";
        static final String STATION_UNITS_QUERY_PARAMETER = "station-unit";
        private String officeIdMask;
        private String stationUnits;

        private GetAll() {
        }

        public GetAll withOfficeIdMask(String officeIdMask) {
            this.officeIdMask = officeIdMask;
            return this;
        }

        public GetAll withStationUnits(String stationUnits) {
            this.stationUnits = stationUnits;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_MASK_QUERY_PARAMETER, officeIdMask)
                    .addQueryParameter(STATION_UNITS_QUERY_PARAMETER, stationUnits)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class GetOne extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String STREAM_ID_QUERY_PARAMETER = "stream-id";
        static final String STATION_UNITS_QUERY_PARAMETER = "station-unit";
        private final String reachId;
        private final String officeId;
        private final String streamId;
        private String stationUnits;

        private GetOne(String officeId, String streamId, String reachId) {
            this.reachId = Objects.requireNonNull(reachId, "Id required for getOne reach endpoint");
            this.streamId = Objects.requireNonNull(streamId, "Stream id required for getOne reach endpoint");
            this.officeId = Objects.requireNonNull(officeId, "Office id required for getOne reach endpoint");
        }

        String reachId() {
            return reachId;
        }

        public GetOne withStationUnits(String stationUnits) {
            this.stationUnits = stationUnits;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(STREAM_ID_QUERY_PARAMETER, streamId)
                    .addQueryParameter(STATION_UNITS_QUERY_PARAMETER, stationUnits)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        static final String FAIL_IF_EXISTS_QUERY_PARAMETER = "fail-if-exists";
        private final StreamReach streamReach;
        private boolean failIfExists = true;

        private Post(StreamReach streamReach) {
            this.streamReach = Objects.requireNonNull(streamReach,
                    "Cannot access the reach POST endpoint without a streamReach value");
        }

        public Post failIfExists(boolean failIfExists) {
            this.failIfExists = failIfExists;
            return this;
        }

        StreamReach streamReach() {
            return streamReach;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER, Boolean.toString(failIfExists))
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Delete extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        private final String reachId;
        private final String officeId;

        private Delete(String officeId, String reachId) {
            this.reachId = Objects.requireNonNull(reachId, "Cannot access the reach DELETE endpoint without an id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the reach DELETE endpoint without an office id");
        }

        String reachId() {
            return reachId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Patch extends EndpointInput {
        static final String OFFICE_QUERY_PARAMETER = "office";
        static final String NAME_QUERY_PARAMETER = "name";
        private final String oldReachId;
        private final String newReachId;
        private final String officeId;

        private Patch(String officeId, String oldReachId, String newReachId) {
            this.oldReachId = Objects.requireNonNull(oldReachId, "Cannot access the reach PATCH endpoint without an old id");
            this.newReachId = Objects.requireNonNull(newReachId, "Cannot access the reach PATCH endpoint without a new id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the reach PATCH endpoint without an office id");
        }

        String reachId() {
            return oldReachId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(NAME_QUERY_PARAMETER, newReachId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
