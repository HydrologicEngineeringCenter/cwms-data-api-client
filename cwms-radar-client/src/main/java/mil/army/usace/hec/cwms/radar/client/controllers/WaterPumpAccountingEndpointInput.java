package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.radar.client.model.WaterSupplyAccounting;

import java.time.Instant;
import java.util.Objects;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

public final class WaterPumpAccountingEndpointInput {
    private WaterPumpAccountingEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll(String officeId, String projectId, String waterUserId, String waterContractName) {
        return new GetAll(officeId, projectId, waterUserId, waterContractName);
    }

    public static Post post(WaterSupplyAccounting waterSupplyAccounting) {
        return new Post(waterSupplyAccounting);
    }

    public static final class GetAll extends EndpointInput {
        static final String START_TIME_QUERY_PARAMETER = "start";
        static final String END_TIME_QUERY_PARAMETER = "end";
        static final String START_INCLUSIVE_QUERY_PARAMETER = "start-inclusive";
        static final String END_INCLUSIVE_QUERY_PARAMETER = "end-inclusive";
        static final String ASCENDING_QUERY_PARAMETER = "ascending";
        static final String ROW_LIMIT_QUERY_PARAMETER = "row-limit";
        private final String waterUserId;
        private final String waterContractName;
        private final String projectId;
        private final String officeId;
        private Instant startTime = Instant.ofEpochSecond(-5364662400L);
        private Instant endTime = Instant.ofEpochSecond(32503680000L);
        private boolean startInclusive = true;
        private boolean endInclusive = true;
        private boolean ascending = true;
        private int rowLimit = 0;

        private GetAll(String officeId, String projectId, String waterUserId, String waterContractName) {
            this.officeId = Objects.requireNonNull(officeId, "Cannot access the water pump accounting GET "
                    + "endpoint without providing office id");
            this.projectId = Objects.requireNonNull(projectId, "Cannot access the water pump accounting GET "
                    + "endpoint without providing project id");
            this.waterUserId = Objects.requireNonNull(waterUserId, "Cannot access the water pump accounting "
                    + "GET endpoint without providing water user id");
            this.waterContractName = Objects.requireNonNull(waterContractName, "Cannot access the water pump "
                    + "accounting GET endpoint without providing water contract name");
        }

        public GetAll setStartTime(Instant startTime) {
            this.startTime = Objects.requireNonNull(startTime, "Cannot set the start time to null");
            return this;
        }

        public GetAll setEndTime(Instant endTime) {
            this.endTime = Objects.requireNonNull(endTime, "Cannot set the end time to null");
            return this;
        }

        public GetAll setStartInclusive(boolean startInclusive) {
            this.startInclusive = startInclusive;
            return this;
        }

        public GetAll setEndInclusive(boolean endInclusive) {
            this.endInclusive = endInclusive;
            return this;
        }

        public GetAll setAscending(boolean ascending) {
            this.ascending = ascending;
            return this;
        }

        public GetAll setRowLimit(int rowLimit) {
            this.rowLimit = rowLimit;
            return this;
        }

        public String getOfficeId() {
            return officeId;
        }

        public String getProjectId() {
            return projectId;
        }

        public String getWaterUserId() {
            return waterUserId;
        }

        public String getWaterContractName() {
            return waterContractName;
        }

        public Instant getStartTime() {
            return startTime;
        }

        public Instant getEndTime() {
            return endTime;
        }

        public boolean isStartInclusive() {
            return startInclusive;
        }

        public boolean isEndInclusive() {
            return endInclusive;
        }

        public boolean isAscending() {
            return ascending;
        }

        public int getRowLimit() {
            return rowLimit;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(START_TIME_QUERY_PARAMETER, startTime.toString())
                    .addQueryParameter(END_TIME_QUERY_PARAMETER, endTime.toString())
                    .addQueryParameter(START_INCLUSIVE_QUERY_PARAMETER, startInclusive ? "true" : "false")
                    .addQueryParameter(END_INCLUSIVE_QUERY_PARAMETER, endInclusive ? "true" : "false")
                    .addQueryParameter(ASCENDING_QUERY_PARAMETER, ascending ? "true" : "false")
                    .addQueryParameter(ROW_LIMIT_QUERY_PARAMETER, String.valueOf(rowLimit))
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        private final WaterSupplyAccounting waterSupplyAccounting;

        private Post(WaterSupplyAccounting waterSupplyAccounting) {
            this.waterSupplyAccounting = Objects.requireNonNull(waterSupplyAccounting, "Cannot access the" +
                    " water supply accounting POST endpoint without providing water supply accounting");
        }

        WaterSupplyAccounting getWaterSupplyAccounting() {
            return waterSupplyAccounting;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
