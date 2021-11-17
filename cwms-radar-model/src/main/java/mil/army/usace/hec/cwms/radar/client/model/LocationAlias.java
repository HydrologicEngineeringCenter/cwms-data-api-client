/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * LocationAlias
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class LocationAlias {
    @JsonProperty("name")
    private final String locationGroupId = null;

    @JsonProperty("value")
    private final String aliasId = null;

    /**
     * Get name
     *
     * @return name
     **/
    public String getLocationGroupId() {
        return locationGroupId;
    }

    /**
     * Get value
     *
     * @return value
     **/
    public String getAliasId() {
        return aliasId;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LocationAlias locationAlias = (LocationAlias) o;
        return Objects.equals(this.locationGroupId, locationAlias.locationGroupId) &&
            Objects.equals(this.aliasId, locationAlias.aliasId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationGroupId, aliasId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LocationAlias {\n");

        sb.append("    locationGroupId: ").append(toIndentedString(locationGroupId)).append("\n");
        sb.append("    aliasId: ").append(toIndentedString(aliasId)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}