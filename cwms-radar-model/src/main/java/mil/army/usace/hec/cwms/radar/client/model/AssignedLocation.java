/*
 * MIT License
 *
 * Copyright (c) 2021 Hydrologic Engineering Center
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
import java.util.Objects;
import javax.validation.Valid;

/**
 * AssignedLocation
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-12-01T13:20:30.413-08:00[America/Los_Angeles]")
public class AssignedLocation {

    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("location-id")
    private String locationId = null;

    @JsonProperty("alias-id")
    private String aliasId = null;

    @JsonProperty("attribute")
    private BigDecimal attribute = null;

    @JsonProperty("ref-location-id")
    private String refLocationId = null;

    public AssignedLocation officeId(String officeId) {
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

    public AssignedLocation locationId(String locationId) {
        this.locationId = locationId;
        return this;
    }

    /**
     * Get locationId
     *
     * @return locationId
     **/

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
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

    @Valid
    public BigDecimal getAttribute() {
        return attribute;
    }

    public void setAttribute(BigDecimal attribute) {
        this.attribute = attribute;
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

    public String getRefLocationId() {
        return refLocationId;
    }

    public void setRefLocationId(String refLocationId) {
        this.refLocationId = refLocationId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AssignedLocation assignedLocation = (AssignedLocation) o;
        return this.locationId == null || assignedLocation.locationId == null ? Objects.equals(this.locationId, assignedLocation.locationId) :
            this.locationId.equalsIgnoreCase(assignedLocation.locationId)
                && this.officeId == null || assignedLocation.officeId == null ?
                Objects.equals(this.officeId, assignedLocation.officeId) :
                this.officeId.equalsIgnoreCase(assignedLocation.officeId)
                    && this.aliasId == null || assignedLocation.aliasId == null ? Objects.equals(this.aliasId, assignedLocation.aliasId) :
                    this.aliasId.equalsIgnoreCase(assignedLocation.aliasId)
                        && Objects.equals(this.attribute, assignedLocation.attribute)
                        && this.refLocationId == null || assignedLocation.refLocationId == null ?
                        Objects.equals(this.refLocationId, assignedLocation.refLocationId) :
                        this.refLocationId.equalsIgnoreCase(assignedLocation.refLocationId)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId == null ? 0 : locationId.toLowerCase(), officeId == null ? 0 : officeId.toLowerCase(),
            aliasId == null ? 0 : aliasId.toLowerCase(), attribute, refLocationId == null ? 0 : refLocationId.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AssignedLocation {\n");

        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    locationId: ").append(toIndentedString(locationId)).append("\n");
        sb.append("    aliasId: ").append(toIndentedString(aliasId)).append("\n");
        sb.append("    attribute: ").append(toIndentedString(attribute)).append("\n");
        sb.append("    refLocationId: ").append(toIndentedString(refLocationId)).append("\n");
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
