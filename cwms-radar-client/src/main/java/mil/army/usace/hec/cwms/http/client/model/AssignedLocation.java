/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.Valid;

/**
 * AssignedLocation
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class AssignedLocation {
    @JsonProperty("locationId")
    private String locationId = null;

    @JsonProperty("baseLocationId")
    private String baseLocationId = null;

    @JsonProperty("subLocationId")
    private String subLocationId = null;

    @JsonProperty("aliasId")
    private String aliasId = null;

    @JsonProperty("attribute")
    private BigDecimal attribute = null;

    @JsonProperty("locationCode")
    private BigDecimal locationCode = null;

    @JsonProperty("refLocationId")
    private String refLocationId = null;

    public AssignedLocation locationId(String locationId) {
        this.locationId = locationId;
        return this;
    }

    /**
     * Get locationId
     *
     * @return locationId
     **/
    @ApiModelProperty(value = "")

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public AssignedLocation baseLocationId(String baseLocationId) {
        this.baseLocationId = baseLocationId;
        return this;
    }

    /**
     * Get baseLocationId
     *
     * @return baseLocationId
     **/
    @ApiModelProperty(value = "")

    public String getBaseLocationId() {
        return baseLocationId;
    }

    public void setBaseLocationId(String baseLocationId) {
        this.baseLocationId = baseLocationId;
    }

    public AssignedLocation subLocationId(String subLocationId) {
        this.subLocationId = subLocationId;
        return this;
    }

    /**
     * Get subLocationId
     *
     * @return subLocationId
     **/
    @ApiModelProperty(value = "")

    public String getSubLocationId() {
        return subLocationId;
    }

    public void setSubLocationId(String subLocationId) {
        this.subLocationId = subLocationId;
    }

    public AssignedLocation aliasId(String aliasId) {
        this.aliasId = aliasId;
        return this;
    }

    /**
     * Get aliasId
     *
     * @return aliasId
     **/
    @ApiModelProperty(value = "")

    public String getAliasId() {
        return aliasId;
    }

    public void setAliasId(String aliasId) {
        this.aliasId = aliasId;
    }

    public AssignedLocation attribute(BigDecimal attribute) {
        this.attribute = attribute;
        return this;
    }

    /**
     * Get attribute
     *
     * @return attribute
     **/
    @ApiModelProperty(value = "")

    @Valid
    public BigDecimal getAttribute() {
        return attribute;
    }

    public void setAttribute(BigDecimal attribute) {
        this.attribute = attribute;
    }

    public AssignedLocation locationCode(BigDecimal locationCode) {
        this.locationCode = locationCode;
        return this;
    }

    /**
     * Get locationCode
     *
     * @return locationCode
     **/
    @ApiModelProperty(value = "")

    @Valid
    public BigDecimal getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(BigDecimal locationCode) {
        this.locationCode = locationCode;
    }

    public AssignedLocation refLocationId(String refLocationId) {
        this.refLocationId = refLocationId;
        return this;
    }

    /**
     * Get refLocationId
     *
     * @return refLocationId
     **/
    @ApiModelProperty(value = "")

    public String getRefLocationId() {
        return refLocationId;
    }

    public void setRefLocationId(String refLocationId) {
        this.refLocationId = refLocationId;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AssignedLocation assignedLocation = (AssignedLocation) o;
        return Objects.equals(this.locationId, assignedLocation.locationId) &&
            Objects.equals(this.baseLocationId, assignedLocation.baseLocationId) &&
            Objects.equals(this.subLocationId, assignedLocation.subLocationId) &&
            Objects.equals(this.aliasId, assignedLocation.aliasId) &&
            Objects.equals(this.attribute, assignedLocation.attribute) &&
            Objects.equals(this.locationCode, assignedLocation.locationCode) &&
            Objects.equals(this.refLocationId, assignedLocation.refLocationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, baseLocationId, subLocationId, aliasId, attribute,
            locationCode, refLocationId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AssignedLocation {\n");

        sb.append("    locationId: ").append(toIndentedString(locationId)).append("\n");
        sb.append("    baseLocationId: ").append(toIndentedString(baseLocationId)).append("\n");
        sb.append("    subLocationId: ").append(toIndentedString(subLocationId)).append("\n");
        sb.append("    aliasId: ").append(toIndentedString(aliasId)).append("\n");
        sb.append("    attribute: ").append(toIndentedString(attribute)).append("\n");
        sb.append("    locationCode: ").append(toIndentedString(locationCode)).append("\n");
        sb.append("    refLocationId: ").append(toIndentedString(refLocationId)).append("\n");
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
