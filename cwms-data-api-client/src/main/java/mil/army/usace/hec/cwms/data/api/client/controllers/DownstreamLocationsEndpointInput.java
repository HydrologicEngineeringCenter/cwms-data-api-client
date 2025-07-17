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
import java.util.Optional;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;

public final class DownstreamLocationsEndpointInput {

    private DownstreamLocationsEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll(String officeId, String locationId) {
        return new GetAll(officeId, locationId);
    }

    public static final class GetAll extends EndpointInput {
        static final String ALL_DOWNSTREAM_QUERY_PARAMETER = "all-downstream";
        static final String SAME_STREAM_ONLY_QUERY_PARAMETER = "same-stream-only";
        static final String STATION_UNIT_QUERY_PARAMETER = "station-units";
        static final String STAGE_UNIT_QUERY_PARAMETER = "stage-units";
        static final String AREA_UNIT_QUERY_PARAMETER = "area-units";
        private final String locationId;
        private final String officeId;
        private Boolean allDownstream;
        private Boolean sameStreamOnly;
        private String stationUnits;
        private String stageUnits;
        private String areaUnits;

        private GetAll(String officeId, String locationId) {
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the downstream locations GET endpoint without an office id");
            this.locationId = Objects.requireNonNull(locationId, "Cannot access the downstream locations GET endpoint without a location id");
        }

        public GetAll allDownstream(boolean allDownstream) {
            this.allDownstream = allDownstream;
            return this;
        }

        public GetAll sameStreamOnly(boolean sameStreamOnly) {
            this.sameStreamOnly = sameStreamOnly;
            return this;
        }

        public GetAll stationUnits(String stationUnits) {
            this.stationUnits = stationUnits;
            return this;
        }

        public GetAll stageUnits(String stageUnits) {
            this.stageUnits = stageUnits;
            return this;
        }

        public GetAll areaUnits(String areaUnits) {
            this.areaUnits = areaUnits;
            return this;
        }

        String officeId() {
            return officeId;
        }

        String locationId() {
            return locationId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder
                    .addQueryParameter(ALL_DOWNSTREAM_QUERY_PARAMETER, Optional.ofNullable(allDownstream).map(String::valueOf).orElse(null))
                    .addQueryParameter(SAME_STREAM_ONLY_QUERY_PARAMETER, Optional.ofNullable(sameStreamOnly).map(String::valueOf).orElse(null))
                    .addQueryParameter(STATION_UNIT_QUERY_PARAMETER, stationUnits)
                    .addQueryParameter(STAGE_UNIT_QUERY_PARAMETER, stageUnits)
                    .addQueryParameter(AREA_UNIT_QUERY_PARAMETER, areaUnits)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
