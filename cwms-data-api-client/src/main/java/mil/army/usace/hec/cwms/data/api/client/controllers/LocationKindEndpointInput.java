/*
 * Copyright (c) 2025. Hydrologic Engineering Center (HEC).
 * United States Army Corps of Engineers
 * All Rights Reserved. HEC PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.data.api.client.controllers;

import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_JSON;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;

public final class LocationKindEndpointInput {

    static final String OFFICE_QUERY_PARAMETER = "office";
    static final String NAMES_QUERY_PARAMETER = "names";
    static final String LOCATION_KIND_LIKE_QUERY_PARAMETER = "location-kind-like";

    private LocationKindEndpointInput() {
        throw new AssertionError("Factory class");
    }

    public static GetAll getAll() {
        return new GetAll();
    }

    public static class GetAll extends EndpointInput {
        private String office;
        private String names;
        private String locationKindLike;

        public GetAll() {
        }

        public GetAll locationKindLike(String locationKindLike) {
            this.locationKindLike = locationKindLike;
            return this;
        }

        public GetAll names(String names) {
            this.names = names;
            return this;
        }

        public GetAll office(String office) {
            this.office = office;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, office)
                                     .addQueryParameter(NAMES_QUERY_PARAMETER, names)
                                     .addQueryParameter(LOCATION_KIND_LIKE_QUERY_PARAMETER, locationKindLike)
                                     .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON);
        }
    }
}
