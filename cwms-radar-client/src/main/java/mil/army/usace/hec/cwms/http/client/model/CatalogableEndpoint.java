/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Set of data for which a larger catalog can be built.
 */
public enum CatalogableEndpoint {
    TIMESERIES("TIMESERIES"),
    LOCATIONS("LOCATIONS");

    private String value;

    CatalogableEndpoint(String value) {
        this.value = value;
    }

    @JsonCreator
    public static CatalogableEndpoint fromValue(String text) {
        for (CatalogableEndpoint b : CatalogableEndpoint.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }
}
