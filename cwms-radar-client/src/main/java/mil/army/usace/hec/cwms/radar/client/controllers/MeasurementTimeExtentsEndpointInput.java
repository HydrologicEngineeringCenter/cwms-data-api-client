package mil.army.usace.hec.cwms.radar.client.controllers;

import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

public final class MeasurementTimeExtentsEndpointInput {
    private MeasurementTimeExtentsEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static MeasurementTimeExtentsEndpointInput.GetAll getAll() {
        return new MeasurementTimeExtentsEndpointInput.GetAll();
    }

    public static final class GetAll extends EndpointInput {
        static final String OFFICE_MASK_QUERY_PARAMETER = "office-mask";
        private String officeIdMask;

        private GetAll() {
        }

        public GetAll withOfficeIdMask(String officeId) {
            this.officeIdMask = officeId;
            return this;
        }
        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_MASK_QUERY_PARAMETER, officeIdMask)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
