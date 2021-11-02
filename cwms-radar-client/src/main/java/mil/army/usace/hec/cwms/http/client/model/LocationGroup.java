/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;

/**
 * A representation of a location group
 */
@ApiModel(description = "A representation of a location group")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class LocationGroup {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("locationCategory")
    private LocationCategory locationCategory = null;

    @JsonProperty("officeId")
    private String officeId = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("sharedLocAliasId")
    private String sharedLocAliasId = null;

    @JsonProperty("sharedRefLocationId")
    private String sharedRefLocationId = null;

    @JsonProperty("locGroupAttribute")
    private BigDecimal locGroupAttribute = null;

    @JsonProperty("assignedLocations")
    @Valid
    private List<AssignedLocation> assignedLocations = null;

    public LocationGroup id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/
    @ApiModelProperty(value = "")

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocationGroup locationCategory(LocationCategory locationCategory) {
        this.locationCategory = locationCategory;
        return this;
    }

    /**
     * Get locationCategory
     *
     * @return locationCategory
     **/
    @ApiModelProperty(value = "")

    @Valid
    public LocationCategory getLocationCategory() {
        return locationCategory;
    }

    public void setLocationCategory(LocationCategory locationCategory) {
        this.locationCategory = locationCategory;
    }

    public LocationGroup officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    /**
     * Get officeId
     *
     * @return officeId
     **/
    @ApiModelProperty(value = "")

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public LocationGroup description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get description
     *
     * @return description
     **/
    @ApiModelProperty(value = "")

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocationGroup sharedLocAliasId(String sharedLocAliasId) {
        this.sharedLocAliasId = sharedLocAliasId;
        return this;
    }

    /**
     * Get sharedLocAliasId
     *
     * @return sharedLocAliasId
     **/
    @ApiModelProperty(value = "")

    public String getSharedLocAliasId() {
        return sharedLocAliasId;
    }

    public void setSharedLocAliasId(String sharedLocAliasId) {
        this.sharedLocAliasId = sharedLocAliasId;
    }

    public LocationGroup sharedRefLocationId(String sharedRefLocationId) {
        this.sharedRefLocationId = sharedRefLocationId;
        return this;
    }

    /**
     * Get sharedRefLocationId
     *
     * @return sharedRefLocationId
     **/
    @ApiModelProperty(value = "")

    public String getSharedRefLocationId() {
        return sharedRefLocationId;
    }

    public void setSharedRefLocationId(String sharedRefLocationId) {
        this.sharedRefLocationId = sharedRefLocationId;
    }

    public LocationGroup locGroupAttribute(BigDecimal locGroupAttribute) {
        this.locGroupAttribute = locGroupAttribute;
        return this;
    }

    /**
     * Get locGroupAttribute
     *
     * @return locGroupAttribute
     **/
    @ApiModelProperty(value = "")

    @Valid
    public BigDecimal getLocGroupAttribute() {
        return locGroupAttribute;
    }

    public void setLocGroupAttribute(BigDecimal locGroupAttribute) {
        this.locGroupAttribute = locGroupAttribute;
    }

    public LocationGroup assignedLocations(List<AssignedLocation> assignedLocations) {
        this.assignedLocations = assignedLocations;
        return this;
    }

    public LocationGroup addAssignedLocationsItem(AssignedLocation assignedLocationsItem) {
        if (this.assignedLocations == null) {
            this.assignedLocations = new ArrayList<AssignedLocation>();
        }
        this.assignedLocations.add(assignedLocationsItem);
        return this;
    }

    /**
     * Get assignedLocations
     *
     * @return assignedLocations
     **/
    @ApiModelProperty(value = "")
    @Valid
    public List<AssignedLocation> getAssignedLocations() {
        return assignedLocations;
    }

    public void setAssignedLocations(List<AssignedLocation> assignedLocations) {
        this.assignedLocations = assignedLocations;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LocationGroup locationGroup = (LocationGroup) o;
        return Objects.equals(this.id, locationGroup.id) &&
            Objects.equals(this.locationCategory, locationGroup.locationCategory) &&
            Objects.equals(this.officeId, locationGroup.officeId) &&
            Objects.equals(this.description, locationGroup.description) &&
            Objects.equals(this.sharedLocAliasId, locationGroup.sharedLocAliasId) &&
            Objects.equals(this.sharedRefLocationId, locationGroup.sharedRefLocationId) &&
            Objects.equals(this.locGroupAttribute, locationGroup.locGroupAttribute) &&
            Objects.equals(this.assignedLocations, locationGroup.assignedLocations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, locationCategory, officeId, description, sharedLocAliasId,
            sharedRefLocationId, locGroupAttribute, assignedLocations);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LocationGroup {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    locationCategory: ").append(toIndentedString(locationCategory)).append("\n");
        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    sharedLocAliasId: ").append(toIndentedString(sharedLocAliasId)).append("\n");
        sb.append("    sharedRefLocationId: ").append(toIndentedString(sharedRefLocationId))
            .append("\n");
        sb.append("    locGroupAttribute: ").append(toIndentedString(locGroupAttribute))
            .append("\n");
        sb.append("    assignedLocations: ").append(toIndentedString(assignedLocations))
            .append("\n");
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
