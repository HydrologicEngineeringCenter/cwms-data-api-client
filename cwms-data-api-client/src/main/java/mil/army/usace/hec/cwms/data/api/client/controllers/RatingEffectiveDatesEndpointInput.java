package mil.army.usace.hec.cwms.data.api.client.controllers;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_V2;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;

public final class RatingEffectiveDatesEndpointInput {

    static final String EFFECTIVE_DATES_ENDPOINT = "effective-dates";
    static final String OFFICE_MASK_QUERY_PARAMETER = "office-mask";
    static final String RATING_ID_MASK_QUERY_PARAMETER = "rating-id-mask";
    static final String TIMEZONE_QUERY_PARAMETER = "timezone";
    static final String BEGIN_QUERY_PARAMETER = "begin";
    static final String END_QUERY_PARAMETER = "end";

    public static GetAll getAll() {
        return new GetAll();
    }

    public static final class GetAll extends EndpointInput{
        private String officeMask;
        private String ratingIdMask;
        private Instant begin;
        private Instant end;
        private ZoneId zoneId;

        private GetAll() {
            //empty private ctor
        }

        public GetAll officeMask(String officeMask) {
            this.officeMask = officeMask;
            return this;
        }

        public GetAll ratingIdMask(String ratingIdMask) {
            this.ratingIdMask = ratingIdMask;
            return this;
        }

        public GetAll zoneId(ZoneId timezone) {
            this.zoneId = timezone;
            return this;
        }

        public GetAll begin(Instant begin) {
            this.begin = begin;
            return this;
        }

        public GetAll end(Instant end) {
            this.end = end;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            String beginString = Optional.ofNullable(begin).map(Object::toString).orElse(null);
            String endString = Optional.ofNullable(end).map(Object::toString).orElse(null);
            String zoneIdString = Optional.ofNullable(this.zoneId).map(ZoneId::getId).orElse(null);
            return httpRequestBuilder.addQueryParameter(OFFICE_MASK_QUERY_PARAMETER, officeMask)
                    .addQueryParameter(RATING_ID_MASK_QUERY_PARAMETER, ratingIdMask)
                    .addQueryParameter(TIMEZONE_QUERY_PARAMETER, zoneIdString)
                    .addQueryParameter(BEGIN_QUERY_PARAMETER, beginString)
                    .addQueryParameter(END_QUERY_PARAMETER, endString)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V2);
        }
    }
}
