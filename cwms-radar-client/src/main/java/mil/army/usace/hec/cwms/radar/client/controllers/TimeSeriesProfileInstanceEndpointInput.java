package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesProfile;


public final class TimeSeriesProfileInstanceEndpointInput {
    private TimeSeriesProfileInstanceEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll() {
        return new GetAll();
    }

    public static GetOne getOne(String officeId, String locationId, String parameterId, String version, List<String> unit) {
        return new GetOne(officeId, locationId, parameterId, version, unit);
    }

    public static Post post(String profileData, TimeSeriesProfile profile, String version) {
        return new Post(profileData, profile, version);
    }

    public static Delete delete(String officeId, String version, String parameterId, String locationId) {
        return new Delete(officeId, version, parameterId, locationId);
    }

    public static final class GetAll extends EndpointInput {
        public static final String OFFICE_MASK_QUERY_PARAMETER = "office-mask";
        public static final String LOCATION_MASK_QUERY_PARAMETER = "location-mask";
        public static final String PARAMETER_MASK_QUERY_PARAMETER = "parameter-id-mask";
        public static final String VERSION_QUERY_PARAMETER = "version";
        private String officeMask;
        private String locationMask;
        private String parameterMask;
        private String versionMask;

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

        public GetAll versionMask(String versionMask) {
            this.versionMask = versionMask;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_MASK_QUERY_PARAMETER, officeMask)
                .addQueryParameter(LOCATION_MASK_QUERY_PARAMETER, locationMask)
                .addQueryParameter(PARAMETER_MASK_QUERY_PARAMETER, parameterMask)
                .addQueryParameter(VERSION_QUERY_PARAMETER, versionMask)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class GetOne extends EndpointInput {
        public static final String OFFICE_QUERY_PARAMETER = "office";
        public static final String VERSION_DATE_QUERY_PARAMETER = "version-date";
        public static final String TIMEZONE_QUERY_PARAMETER = "timezone";
        public static final String UNIT_QUERY_PARAMETER = "unit";
        public static final String START_INCLUSIVE_QUERY_PARAMETER = "start-time-inclusive";
        public static final String END_INCLUSIVE_QUERY_PARAMETER = "end-time-inclusive";
        public static final String PREVIOUS_QUERY_PARAMETER = "previous";
        public static final String NEXT_QUERY_PARAMETER = "next";
        public static final String MAX_VERSION_QUERY_PARAMETER = "max-version";
        public static final String START_QUERY_PARAMETER = "start";
        public static final String END_QUERY_PARAMETER = "end";
        public static final String PAGE_SIZE_QUERY_PARAMETER = "page-size";
        public static final String PAGE_QUERY_PARAMETER = "page";
        private final String officeId;
        private final String parameterId;
        private final String version;
        private Instant versionDate = null;
        private String timezone = "UTC";
        private final List<String> unit;
        private boolean startInclusive = true;
        private boolean endInclusive = true;
        private boolean previous = false;
        private boolean next = false;
        private boolean maxVersion = false;
        private Instant start = Instant.now();
        private Instant end = Instant.now();
        private final String locationId;
        private String page;
        private int pageSize = 500;

        private GetOne(String officeId, String locationId, String parameterId, String version, List<String> unit) {
            this.officeId = Objects.requireNonNull(officeId, "Office is required");
            this.locationId = Objects.requireNonNull(locationId, "Location ID is required");
            this.parameterId = Objects.requireNonNull(parameterId, "Parameter ID is required");
            this.version = Objects.requireNonNull(version, "Version is required");
            this.unit = Objects.requireNonNull(unit,"Unit is required");

        }

        public GetOne versionDate(Instant versionDate) {
            this.versionDate = versionDate;
            return this;
        }

        public GetOne timezone(String timezone) {
            this.timezone = timezone;
            return this;
        }

        public GetOne startInclusive(boolean startInclusive) {
            this.startInclusive = startInclusive;
            return this;
        }

        public GetOne endInclusive(boolean endInclusive) {
            this.endInclusive = endInclusive;
            return this;
        }

        public GetOne previous(boolean previous) {
            this.previous = previous;
            return this;
        }

        public GetOne next(boolean next) {
            this.next = next;
            return this;
        }

        public GetOne maxVersion(boolean maxVersion) {
            this.maxVersion = maxVersion;
            return this;
        }

        public GetOne start(Instant start) {
            this.start = start;
            return this;
        }

        public GetOne end(Instant end) {
            this.end = end;
            return this;
        }

        public GetOne page(String page) {
            this.page = page;
            return this;
        }

        public GetOne pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public String version() {
            return version;
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
                .addQueryParameter(VERSION_DATE_QUERY_PARAMETER, versionDate == null ? null
                        : versionDate.toString())
                .addQueryParameter(TIMEZONE_QUERY_PARAMETER, timezone)
                .addQueryParameter(UNIT_QUERY_PARAMETER,
                        unit.stream().map(Object::toString).reduce((a, b) -> a + "," + b).orElse(""))
                .addQueryParameter(PAGE_SIZE_QUERY_PARAMETER, String.valueOf(pageSize))
                .addQueryParameter(PAGE_QUERY_PARAMETER, page)
                .addQueryParameter(START_INCLUSIVE_QUERY_PARAMETER, String.valueOf(startInclusive))
                .addQueryParameter(END_INCLUSIVE_QUERY_PARAMETER, String.valueOf(endInclusive))
                .addQueryParameter(PREVIOUS_QUERY_PARAMETER, String.valueOf(previous))
                .addQueryParameter(NEXT_QUERY_PARAMETER, String.valueOf(next))
                .addQueryParameter(MAX_VERSION_QUERY_PARAMETER, String.valueOf(maxVersion))
                .addQueryParameter(START_QUERY_PARAMETER, start.toString())
                .addQueryParameter(END_QUERY_PARAMETER, end.toString())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Post extends EndpointInput {
        public static final String METHOD_QUERY_PARAMETER = "method";
        public static final String OVERRIDE_PROTECTION_QUERY_PARAMETER = "override-protection";
        public static final String VERSION_DATE_QUERY_PARAMETER = "version-date";
        public static final String PROFILE_DATA_QUERY_PARAMETER = "profile-data";
        public static final String VERSION_QUERY_PARAMETER = "version";
        private String method;
        private boolean overrideProtection;
        private Instant versionDate;
        private final String profileData;
        private final String version;
        private final TimeSeriesProfile profile;

        public Post(String profileData, TimeSeriesProfile profile, String version) {
            this.profileData = Objects.requireNonNull(profileData, "Profile data is required");
            this.version = Objects.requireNonNull(version, "Version is required");
            this.profile = Objects.requireNonNull(profile, "TimeSeries Profile is required");
        }

        public Post method(String method) {
            this.method = method;
            return this;
        }

        public Post overrideProtection(boolean overrideProtection) {
            this.overrideProtection = overrideProtection;
            return this;
        }

        public Post versionDate(Instant versionDate) {
            this.versionDate = versionDate;
            return this;
        }

        public String profileData() {
            return profileData;
        }

        public TimeSeriesProfile profile() {
            return profile;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(METHOD_QUERY_PARAMETER, method)
                .addQueryParameter(OVERRIDE_PROTECTION_QUERY_PARAMETER, String.valueOf(overrideProtection))
                .addQueryParameter(VERSION_DATE_QUERY_PARAMETER, versionDate.toString())
                .addQueryParameter(PROFILE_DATA_QUERY_PARAMETER, profileData)
                .addQueryParameter(VERSION_QUERY_PARAMETER, version)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static final class Delete extends EndpointInput {
        public static final String OFFICE_QUERY_PARAMETER = "office";
        public static final String VERSION_DATE_QUERY_PARAMETER = "version-date";
        public static final String TIMEZONE_QUERY_PARAMETER = "timezone";
        public static final String OVERRIDE_PROTECTION_QUERY_PARAMETER = "override-protection";
        public static final String DATE_QUERY_PARAMETER = "date";
        private final String officeId;
        private final String parameterId;
        private final String version;
        private Instant versionDate = Instant.now();
        private String timezone = "UTC";
        private boolean overrideProtection = true;
        private Instant date = Instant.now();
        private final String locationId;

        private Delete(String officeId, String version, String parameterId, String locationId) {
            this.officeId = Objects.requireNonNull(officeId, "Office is required");
            this.version = Objects.requireNonNull(version, "Version is required");
            this.parameterId = Objects.requireNonNull(parameterId, "Parameter ID is required");
            this.locationId = Objects.requireNonNull(locationId, "Location ID is required");
        }

        public Delete versionDate(Instant versionDate) {
            this.versionDate = versionDate;
            return this;
        }

        public Delete timezone(String timezone) {
            this.timezone = timezone;
            return this;
        }

        public Delete overrideProtection(boolean overrideProtection) {
            this.overrideProtection = overrideProtection;
            return this;
        }

        public Delete date(Instant date) {
            this.date = date;
            return this;
        }

        public String locationId() {
            return locationId;
        }

        public String version() {
            return version;
        }

        public String parameterId() {
            return parameterId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                .addQueryParameter(VERSION_DATE_QUERY_PARAMETER, versionDate.toString())
                .addQueryParameter(TIMEZONE_QUERY_PARAMETER, timezone)
                .addQueryParameter(OVERRIDE_PROTECTION_QUERY_PARAMETER, String.valueOf(overrideProtection))
                .addQueryParameter(DATE_QUERY_PARAMETER, date.toString())
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
