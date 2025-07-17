package mil.army.usace.hec.cwms.data.api.client.controllers;

import java.util.Objects;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.data.api.client.model.TimeSeriesProfile;


public class TimeSeriesProfileEndpointInput {

    private TimeSeriesProfileEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll() {
        return new GetAll();
    }

    public static GetOne getOne(String officeId, String locationId, String parameterId) {
        return new GetOne(officeId, locationId, parameterId);
    }

    public static Post post(TimeSeriesProfile profile) {
        return new Post(profile);
    }

    public static Delete delete(String officeId, String locationId, String parameterId) {
        return new Delete(officeId, locationId, parameterId);
    }

    public static final class GetAll extends EndpointInput {
        public static final String OFFICE_MASK_QUERY_PARAMETER = "office-mask";
        public static final String LOCATION_MASK_QUERY_PARAMETER = "location-mask";
        public static final String PARAMETER_MASK_QUERY_PARAMETER = "parameter-id-mask";
        public static final String PAGE_QUERY_PARAMETER = "page";
        public static final String PAGE_SIZE_QUERY_PARAMETER = "page-size";
        private String page;
        private int pageSize = 500;
        private String officeMask;
        private String locationMask;
        private String parameterMask;

        private GetAll() {

        }

        public GetAll officeMask(String officeMask) {
            this.officeMask = officeMask;
            return this;
        }

        public GetAll locationMask(String locationMask) {
            this.locationMask = locationMask;
            return this;
        }

        public GetAll parameterMask(String parameterMask) {
            this.parameterMask = parameterMask;
            return this;
        }

        public GetAll page(String page) {
            this.page = page;
            return this;
        }

        public GetAll pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_MASK_QUERY_PARAMETER, officeMask)
                    .addQueryParameter(LOCATION_MASK_QUERY_PARAMETER, locationMask)
                    .addQueryParameter(PARAMETER_MASK_QUERY_PARAMETER, parameterMask)
                    .addQueryParameter(PAGE_QUERY_PARAMETER, page)
                    .addQueryParameter(PAGE_SIZE_QUERY_PARAMETER, String.valueOf(pageSize))
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class GetOne extends EndpointInput {
        public static final String OFFICE_ID_QUERY_PARAMETER = "office";
        private final String officeId;
        private final String locationId;
        private final String parameterId;

        private GetOne(String officeId, String locationId, String parameterId) {
            this.officeId = Objects.requireNonNull(officeId, "Office ID cannot be null");
            this.locationId = Objects.requireNonNull(locationId, "Location ID cannot be null");
            this.parameterId = Objects.requireNonNull(parameterId, "Parameter ID cannot be null");
        }

        public String parameterId() {
            return parameterId;
        }

        public String locationId() {
            return locationId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_ID_QUERY_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        public static final String FAIL_IF_EXISTS_QUERY_PARAMETER = "fail-if-exists";
        private boolean failIfExists;
        private final TimeSeriesProfile profile;

        private Post(TimeSeriesProfile profile) {
            this.profile = Objects.requireNonNull(profile, "TimeSeriesProfile cannot be null");
        }

        public Post failIfExists(boolean failIfExists) {
            this.failIfExists = failIfExists;
            return this;
        }

        public TimeSeriesProfile profile() {
            return profile;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(FAIL_IF_EXISTS_QUERY_PARAMETER, String.valueOf(failIfExists))
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Delete extends EndpointInput {
        public static final String OFFICE_QUERY_PARAMETER = "office";
        private final String officeId;
        private final String locationId;
        private final String parameterId;

        private Delete(String officeId, String locationId, String parameterId) {
            this.officeId = Objects.requireNonNull(officeId, "Office ID cannot be null");
            this.locationId = Objects.requireNonNull(locationId, "Location ID cannot be null");
            this.parameterId = Objects.requireNonNull(parameterId, "Parameter ID cannot be null");
        }

        public String parameterId() {
            return parameterId;
        }

        public String locationId() {
            return locationId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
