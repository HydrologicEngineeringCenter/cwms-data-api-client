/*
 * Copyright (c) 2025. Hydrologic Engineering Center (HEC).
 * United States Army Corps of Engineers
 * All Rights Reserved. HEC PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.data.api.client.controllers;

import org.junit.jupiter.api.Test;

import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_HEADER_JSON;
import static mil.army.usace.hec.cwms.data.api.client.controllers.CdaEndpointConstants.ACCEPT_QUERY_HEADER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.LocationKindEndpointInput.LOCATION_KIND_LIKE_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.LocationKindEndpointInput.NAMES_QUERY_PARAMETER;
import static mil.army.usace.hec.cwms.data.api.client.controllers.LocationKindEndpointInput.OFFICE_QUERY_PARAMETER;
import static org.junit.jupiter.api.Assertions.*;

class TestLocationKindEndpointInput {
    private static final String TEST_OFFICE = "SPK";
    private static final String TEST_NAMES = "LOC_TEST";
    private static final String TEST_LOCATION_KIND_LIKE = "SITE";

    @Test
    void testGetAll() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        LocationKindEndpointInput.GetAll input = LocationKindEndpointInput.getAll();
        input.locationKindLike(TEST_LOCATION_KIND_LIKE)
             .names(TEST_NAMES)
             .office(TEST_OFFICE);

        input.addInputParameters(mockHttpRequestBuilder);
        assertEquals(TEST_OFFICE, mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertEquals(TEST_NAMES, mockHttpRequestBuilder.getQueryParameter(NAMES_QUERY_PARAMETER));
        assertEquals(TEST_LOCATION_KIND_LIKE, mockHttpRequestBuilder.getQueryParameter(LOCATION_KIND_LIKE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_JSON, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }

    @Test
    void testGetAllPlain() {
        MockHttpRequestBuilder mockHttpRequestBuilder = new MockHttpRequestBuilder();
        LocationKindEndpointInput.GetAll input = LocationKindEndpointInput.getAll();

        input.addInputParameters(mockHttpRequestBuilder);
        assertNull(mockHttpRequestBuilder.getQueryParameter(OFFICE_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(NAMES_QUERY_PARAMETER));
        assertNull(mockHttpRequestBuilder.getQueryParameter(LOCATION_KIND_LIKE_QUERY_PARAMETER));
        assertEquals(ACCEPT_HEADER_JSON, mockHttpRequestBuilder.getQueryHeader(ACCEPT_QUERY_HEADER));
    }
}
