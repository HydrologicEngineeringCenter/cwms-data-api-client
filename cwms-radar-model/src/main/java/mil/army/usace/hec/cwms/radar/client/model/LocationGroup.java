/*
 * MIT License
 *
 * Copyright (c) 2022 Hydrologic Engineering Center
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;

/**
 * A representation of a location group
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-09T09:29:33.859-07:00[America/Los_Angeles]")
public class LocationGroup {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("location-category")
    private LocationCategory locationCategory = null;

    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("shared-loc-alias-id")
    private String sharedLocAliasId = null;

    @JsonProperty("shared-ref-location-id")
    private String sharedRefLocationId = null;

    @JsonProperty("loc-group-attribute")
    private BigDecimal locGroupAttribute = null;

    @JsonProperty("assigned-locations")
    @Valid
    private List<AssignedLocation> assignedLocations = new ArrayList<>();

    public LocationGroup id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/

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
    @Valid
    public List<AssignedLocation> getAssignedLocations() {
        return assignedLocations;
    }

    public void setAssignedLocations(List<AssignedLocation> assignedLocations) {
        this.assignedLocations = assignedLocations;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LocationGroup locationGroup = (LocationGroup) o;
        return this.id == null || locationGroup.id == null ? Objects.equals(this.id, locationGroup.id) :
            this.id.equalsIgnoreCase(locationGroup.id) && Objects.equals(this.locationCategory, locationGroup.locationCategory) &&
                this.officeId == null || locationGroup.officeId == null ? Objects.equals(this.officeId, locationGroup.officeId) :
                this.officeId.equalsIgnoreCase(locationGroup.officeId) && this.description == null || locationGroup.description == null ?
                    Objects.equals(this.description, locationGroup.description) :
                    this.description.equalsIgnoreCase(locationGroup.description) && this.sharedLocAliasId == null ||
                        locationGroup.sharedLocAliasId == null ? Objects.equals(this.sharedLocAliasId, locationGroup.sharedLocAliasId) :
                        this.sharedLocAliasId.equalsIgnoreCase(locationGroup.sharedLocAliasId) && this.sharedRefLocationId == null ||
                            locationGroup.sharedRefLocationId == null ? Objects.equals(this.sharedRefLocationId, locationGroup.sharedRefLocationId) :
                            this.sharedRefLocationId.equalsIgnoreCase(locationGroup.sharedRefLocationId) &&
                                Objects.equals(this.locGroupAttribute, locationGroup.locGroupAttribute) &&
                                Objects.equals(this.assignedLocations, locationGroup.assignedLocations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id == null ? 0 : id.toLowerCase(), locationCategory, officeId == null ? 0 : officeId.toLowerCase(),
            description == null ? 0 : description.toLowerCase(), sharedLocAliasId == null ? 0 : sharedLocAliasId.toLowerCase(),
            sharedRefLocationId == null ? 0 : sharedRefLocationId.toLowerCase(), locGroupAttribute, assignedLocations);
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
        sb.append("    sharedRefLocationId: ").append(toIndentedString(sharedRefLocationId)).append("\n");
        sb.append("    locGroupAttribute: ").append(toIndentedString(locGroupAttribute)).append("\n");
        sb.append("    assignedLocations: ").append(toIndentedString(assignedLocations)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
