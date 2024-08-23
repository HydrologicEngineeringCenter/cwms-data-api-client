package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.util.Objects;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesProfile;


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

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_MASK_QUERY_PARAMETER, officeMask)
                    .addQueryParameter(LOCATION_MASK_QUERY_PARAMETER, locationMask)
                    .addQueryParameter(PARAMETER_MASK_QUERY_PARAMETER, parameterMask)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class GetOne extends EndpointInput {
        public static final String OFFICE_ID_QUERY_PARAMETER = "office-id";
        public static final String LOCATION_ID_QUERY_PARAMETER = "location-id";
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

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_ID_QUERY_PARAMETER, officeId)
                    .addQueryParameter(LOCATION_ID_QUERY_PARAMETER, locationId)
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
        public static final String OFFICE_ID_QUERY_PARAMETER = "office-id";
        public static final String LOCATION_ID_QUERY_PARAMETER = "location-id";
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

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_ID_QUERY_PARAMETER, officeId)
                    .addQueryParameter(LOCATION_ID_QUERY_PARAMETER, locationId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
