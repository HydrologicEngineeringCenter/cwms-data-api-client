/*
 * MIT License
 *
 * Copyright (c) 2024 Hydrologic Engineering Center
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
import jakarta.validation.Valid;
import java.util.Objects;

/**
 * Embankment
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-06-28T08:16:15.026792-07:00[America/Los_Angeles]")
public class Embankment {

    @JsonProperty("project-id")
    private CwmsId projectId = null;

    @JsonProperty("location")
    private Location location = null;

    @JsonProperty("structure-type")
    private LookupType structureType = null;

    @JsonProperty("upstream-side-slope")
    private Double upstreamSideSlope = null;

    @JsonProperty("downstream-side-slope")
    private Double downstreamSideSlope = null;

    @JsonProperty("structure-length")
    private Double structureLength = null;

    @JsonProperty("max-height")
    private Double maxHeight = null;

    @JsonProperty("top-width")
    private Double topWidth = null;

    @JsonProperty("length-units")
    private String lengthUnits = null;

    @JsonProperty("downstream-protection-type")
    private LookupType downstreamProtectionType = null;

    @JsonProperty("upstream-protection-type")
    private LookupType upstreamProtectionType = null;

    public Embankment projectId(CwmsId projectId) {
        this.projectId = projectId;
        return this;
    }

    /**
     * Get projectId
     *
     * @return projectId
     **/

    @Valid
    public CwmsId getProjectId() {
        return projectId;
    }

    public void setProjectId(CwmsId projectId) {
        this.projectId = projectId;
    }

    public Embankment location(Location location) {
        this.location = location;
        return this;
    }

    /**
     * Get location
     *
     * @return location
     **/

    @Valid
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Embankment structureType(LookupType structureType) {
        this.structureType = structureType;
        return this;
    }

    /**
     * Get structureType
     *
     * @return structureType
     **/

    @Valid
    public LookupType getStructureType() {
        return structureType;
    }

    public void setStructureType(LookupType structureType) {
        this.structureType = structureType;
    }

    public Embankment upstreamSideSlope(Double upstreamSideSlope) {
        this.upstreamSideSlope = upstreamSideSlope;
        return this;
    }

    /**
     * Get upstreamSideSlope
     *
     * @return upstreamSideSlope
     **/

    public Double getUpstreamSideSlope() {
        return upstreamSideSlope;
    }

    public void setUpstreamSideSlope(Double upstreamSideSlope) {
        this.upstreamSideSlope = upstreamSideSlope;
    }

    public Embankment downstreamSideSlope(Double downstreamSideSlope) {
        this.downstreamSideSlope = downstreamSideSlope;
        return this;
    }

    /**
     * Get downstreamSideSlope
     *
     * @return downstreamSideSlope
     **/

    public Double getDownstreamSideSlope() {
        return downstreamSideSlope;
    }

    public void setDownstreamSideSlope(Double downstreamSideSlope) {
        this.downstreamSideSlope = downstreamSideSlope;
    }

    public Embankment structureLength(Double structureLength) {
        this.structureLength = structureLength;
        return this;
    }

    /**
     * Get structureLength
     *
     * @return structureLength
     **/

    public Double getStructureLength() {
        return structureLength;
    }

    public void setStructureLength(Double structureLength) {
        this.structureLength = structureLength;
    }

    public Embankment maxHeight(Double maxHeight) {
        this.maxHeight = maxHeight;
        return this;
    }

    /**
     * Get maxHeight
     *
     * @return maxHeight
     **/

    public Double getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(Double maxHeight) {
        this.maxHeight = maxHeight;
    }

    public Embankment topWidth(Double topWidth) {
        this.topWidth = topWidth;
        return this;
    }

    /**
     * Get topWidth
     *
     * @return topWidth
     **/

    public Double getTopWidth() {
        return topWidth;
    }

    public void setTopWidth(Double topWidth) {
        this.topWidth = topWidth;
    }

    public Embankment lengthUnits(String lengthUnits) {
        this.lengthUnits = lengthUnits;
        return this;
    }

    /**
     * Get lengthUnits
     *
     * @return lengthUnits
     **/

    public String getLengthUnits() {
        return lengthUnits;
    }

    public void setLengthUnits(String lengthUnits) {
        this.lengthUnits = lengthUnits;
    }

    public Embankment downstreamProtectionType(LookupType downstreamProtectionType) {
        this.downstreamProtectionType = downstreamProtectionType;
        return this;
    }

    /**
     * Get downstreamProtectionType
     *
     * @return downstreamProtectionType
     **/

    @Valid
    public LookupType getDownstreamProtectionType() {
        return downstreamProtectionType;
    }

    public void setDownstreamProtectionType(LookupType downstreamProtectionType) {
        this.downstreamProtectionType = downstreamProtectionType;
    }

    public Embankment upstreamProtectionType(LookupType upstreamProtectionType) {
        this.upstreamProtectionType = upstreamProtectionType;
        return this;
    }

    /**
     * Get upstreamProtectionType
     *
     * @return upstreamProtectionType
     **/

    @Valid
    public LookupType getUpstreamProtectionType() {
        return upstreamProtectionType;
    }

    public void setUpstreamProtectionType(LookupType upstreamProtectionType) {
        this.upstreamProtectionType = upstreamProtectionType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Embankment embankment = (Embankment) o;
        return Objects.equals(this.projectId, embankment.projectId)
            && Objects.equals(this.location, embankment.location)
            && Objects.equals(this.structureType, embankment.structureType)
            && Objects.equals(this.upstreamSideSlope, embankment.upstreamSideSlope)
            && Objects.equals(this.downstreamSideSlope, embankment.downstreamSideSlope)
            && Objects.equals(this.structureLength, embankment.structureLength)
            && Objects.equals(this.maxHeight, embankment.maxHeight)
            && Objects.equals(this.topWidth, embankment.topWidth)
            && this.lengthUnits == null || embankment.lengthUnits == null ?
            Objects.equals(this.lengthUnits, embankment.lengthUnits) :
            this.lengthUnits.equalsIgnoreCase(embankment.lengthUnits)
                && Objects.equals(this.downstreamProtectionType, embankment.downstreamProtectionType)
                && Objects.equals(this.upstreamProtectionType, embankment.upstreamProtectionType)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, location, structureType, upstreamSideSlope, downstreamSideSlope, structureLength,
            maxHeight, topWidth, lengthUnits == null ? 0 : lengthUnits.toLowerCase(), downstreamProtectionType,
            upstreamProtectionType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Embankment {\n");

        sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
        sb.append("    location: ").append(toIndentedString(location)).append("\n");
        sb.append("    structureType: ").append(toIndentedString(structureType)).append("\n");
        sb.append("    upstreamSideSlope: ").append(toIndentedString(upstreamSideSlope)).append("\n");
        sb.append("    downstreamSideSlope: ").append(toIndentedString(downstreamSideSlope)).append("\n");
        sb.append("    structureLength: ").append(toIndentedString(structureLength)).append("\n");
        sb.append("    maxHeight: ").append(toIndentedString(maxHeight)).append("\n");
        sb.append("    topWidth: ").append(toIndentedString(topWidth)).append("\n");
        sb.append("    lengthUnits: ").append(toIndentedString(lengthUnits)).append("\n");
        sb.append("    downstreamProtectionType: ").append(toIndentedString(downstreamProtectionType)).append("\n");
        sb.append("    upstreamProtectionType: ").append(toIndentedString(upstreamProtectionType)).append("\n");
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
