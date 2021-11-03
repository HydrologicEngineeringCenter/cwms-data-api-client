/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Unit System desired in response. Can be SI (International Scientific) or EN (Imperial.) If unspecified, defaults to SI.
 */
public enum UnitSystem {
    SI("SI"),
    EN("EN");

    private final String value;

    UnitSystem(String value) {
        this.value = value;
    }

    @JsonCreator
    public static UnitSystem fromValue(String text) {
        for (UnitSystem b : UnitSystem.values()) {
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
