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

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.radar.client.model.TurbineChange;

public final class TurbineChangeEndpointInput {

    private TurbineChangeEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll(String officeId, String projectId, Instant begin, Instant end) {
        return new GetAll(officeId, projectId, begin, end);
    }

    public static Post post(String officeId, String projectId, List<TurbineChange> changes) {
        return new Post(officeId, projectId, changes);
    }

    public static Delete delete(String officeId, String projectId, Instant begin, Instant end) {
        return new Delete(officeId, projectId, begin, end);
    }

    public static final class GetAll extends EndpointInput {
        static final String BEGIN_QUERY_PARAMETER = "begin";
        static final String END_QUERY_PARAMETER = "end";
        static final String START_INCLUSIVE_QUERY_PARAMETER = "start-time-inclusive";
        static final String END_INCLUSIVE_QUERY_PARAMETER = "end-time-inclusive";
        static final String UNIT_SYSTEM_QUERY_PARAMETER = "unit-system";
        static final String PAGE_SIZE_QUERY_PARAMETER = "page-size";
        private final String projectId;
        private final String officeId;
        private final Instant begin;
        private final Instant end;
        private boolean startTimeInclusive = true;
        private boolean endTimeInclusive = false;
        private String unitSystem;
        private Integer pageSize;

        private GetAll(String officeId, String projectId, Instant begin, Instant end) {
            this.officeId =
                Objects.requireNonNull(officeId, "Cannot access the turbine DELETE endpoint without an office id");
            this.projectId =
                Objects.requireNonNull(projectId, "Cannot access the turbine DELETE endpoint without an id");
            this.begin =
                Objects.requireNonNull(begin, "Cannot access the turbine DELETE endpoint without a begining time");
            this.end = Objects.requireNonNull(end, "Cannot access the turbine DELETE endpoint without an ending time");
        }

        String officeId() {
            return officeId;
        }

        String projectId() {
            return projectId;
        }

        public GetAll startTimeInclusive(boolean startTimeInclusive) {
            this.startTimeInclusive = startTimeInclusive;
            return this;
        }

        public GetAll endTimeInclusive(boolean endTimeInclusive) {
            this.endTimeInclusive = endTimeInclusive;
            return this;
        }

        public GetAll unitSystem(String unitSystem) {
            this.unitSystem = unitSystem;
            return this;
        }

        public GetAll pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder
                .addQueryParameter(BEGIN_QUERY_PARAMETER, begin.toString())
                .addQueryParameter(END_QUERY_PARAMETER, end.toString())
                .addQueryParameter(START_INCLUSIVE_QUERY_PARAMETER, Boolean.toString(startTimeInclusive))
                .addQueryParameter(END_INCLUSIVE_QUERY_PARAMETER, Boolean.toString(endTimeInclusive))
                .addQueryParameter(UNIT_SYSTEM_QUERY_PARAMETER, unitSystem)
                .addQueryParameter(PAGE_SIZE_QUERY_PARAMETER, Optional.ofNullable(pageSize)
                    .map(i -> Integer.toString(i))
                    .orElse(null))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        static final String OVERRIDE_PROTECTION_QUERY_PARAMETER = "override-protection";
        private final String projectId;
        private final String officeId;
        private final List<TurbineChange> changes;
        private boolean overrideProtection;

        private Post(String officeId, String projectId, List<TurbineChange> changes) {
            this.officeId =
                Objects.requireNonNull(officeId, "Cannot access the turbine POST endpoint without an office id");
            this.projectId = Objects.requireNonNull(projectId, "Cannot access the turbine POST endpoint without an id");
            this.changes = Objects.requireNonNull(changes, "Cannot access the turbine POST endpoint without changes");
        }

        public Post overrideProtection(boolean overrideProtection) {
            this.overrideProtection = overrideProtection;
            return this;
        }

        String officeId() {
            return officeId;
        }

        String projectId() {
            return projectId;
        }

        List<TurbineChange> changes() {
            return this.changes;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder
                .addQueryParameter(OVERRIDE_PROTECTION_QUERY_PARAMETER, Boolean.toString(overrideProtection))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Delete extends EndpointInput {
        static final String BEGIN_QUERY_PARAMETER = "begin";
        static final String END_QUERY_PARAMETER = "end";
        static final String OVERRIDE_PROTECTION_QUERY_PARAMETER = "override-protection";
        private final String projectId;
        private final String officeId;
        private final Instant begin;
        private final Instant end;
        private boolean overrideProtection;

        private Delete(String officeId, String projectId, Instant begin, Instant end) {
            this.officeId =
                Objects.requireNonNull(officeId, "Cannot access the turbine DELETE endpoint without an office id");
            this.projectId =
                Objects.requireNonNull(projectId, "Cannot access the turbine DELETE endpoint without an id");
            this.begin =
                Objects.requireNonNull(begin, "Cannot access the turbine DELETE endpoint without a begining time");
            this.end = Objects.requireNonNull(end, "Cannot access the turbine DELETE endpoint without an ending time");
        }

        public Delete overrideProtection(boolean overrideProtection) {
            this.overrideProtection = overrideProtection;
            return this;
        }

        String officeId() {
            return officeId;
        }

        String projectId() {
            return projectId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder
                .addQueryParameter(BEGIN_QUERY_PARAMETER, begin.toString())
                .addQueryParameter(END_QUERY_PARAMETER, end.toString())
                .addQueryParameter(OVERRIDE_PROTECTION_QUERY_PARAMETER, Boolean.toString(overrideProtection))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
