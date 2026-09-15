package mil.army.usace.hec.cwms.data.api.client.controllers.timeseriesgroup;

import mil.army.usace.hec.cwms.data.api.client.model.TimeSeriesGroup;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;

import java.util.Objects;

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_JSON;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;

public class TimeSeriesGroupEndpointV1Input extends TimeSeriesGroupEndpointInput {

    public static GetAll getAll() {
        return new GetAll();
    }

    public static GetOne getOne(String categoryId, String groupId, String officeId, String groupOfficeId, String categoryOfficeId) {
        return TimeSeriesGroupEndpointInput.getOne(categoryId, groupId, officeId, groupOfficeId, categoryOfficeId);
    }

    public static Post post(TimeSeriesGroup timeSeriesGroup) {
        return TimeSeriesGroupEndpointInput.post(timeSeriesGroup);
    }

    public static Delete delete(String categoryId, String groupId, String groupOffice) {
        return new Delete(categoryId, groupId, groupOffice);
    }

    public static Patch patch(String groupOffice, String originalGroupId, TimeSeriesGroup timeSeriesGroup) {
        return new Patch(groupOffice, originalGroupId, timeSeriesGroup);
    }

    public static final class GetAll extends TimeSeriesGroupEndpointInput.GetAll<GetAll> {

        private GetAll() {
        }

        @Override
        protected GetAll self() {
            return this;
        }
    }

    public static final class Delete extends TimeSeriesGroupEndpointInput.Delete<Delete> {

        private Delete(String categoryId, String groupId, String groupOffice) {
            super(categoryId, groupId, groupOffice);
        }

        @Override
        protected Delete self() {
            return this;
        }
    }

    public static final class Patch extends EndpointInput {

        private final TimeSeriesGroup timeSeriesGroup;
        private final String originalGroupId;
        private boolean replaceAssignedTs = false;
        private final String groupOffice;

        private Patch(String groupOffice, String originalGroupId, TimeSeriesGroup timeSeriesGroup) {
            this.originalGroupId = Objects.requireNonNull(originalGroupId, "Cannot update a time series group without specifying the group id");
            this.timeSeriesGroup = Objects.requireNonNull(timeSeriesGroup, "Cannot update a time series group without a group data object");
            this.groupOffice = Objects.requireNonNull(groupOffice, "Cannot update a time series group without specifying the operating office");
        }

        TimeSeriesGroup timeSeriesGroup() {
            return timeSeriesGroup;
        }

        String originalLocationId() {
            return originalGroupId;
        }

        public Patch replaceAssignedTs(boolean replaceAssignedTs) {
            this.replaceAssignedTs = replaceAssignedTs;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(REPLACE_ASSIGNED_TS_QUERY_PARAMETER, Boolean.toString(replaceAssignedTs))
                    .addQueryParameter(OFFICE_QUERY_PARAMETER, groupOffice)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON);
        }
    }
}
