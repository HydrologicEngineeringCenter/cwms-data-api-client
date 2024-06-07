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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Embankment
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-06-07T14:38:25.006765600-07:00[America/Los_Angeles]")
public class Embankment {
    @JsonProperty("upstream-side-slope")
    private Double upstreamSideSlope = null;

    @JsonProperty("downstream-side-slope")
    private Double downstreamSideSlope = null;

    @JsonProperty("structure-length")
    private Double structureLength = null;

    @JsonProperty("height-max")
    private Double heightMax = null;

    @JsonProperty("top-width")
    private Double topWidth = null;

    @JsonProperty("units-id")
    private String unitsId = null;

    @JsonProperty("downstream-prot-type")
    private LookupType downstreamProtType = null;

    @JsonProperty("upstream-prot-type")
    private LookupType upstreamProtType = null;

    @JsonProperty("structure-type")
    private LookupType structureType = null;

    @JsonProperty("location")
    private Location location = null;

    @JsonProperty("project-id")
    private String projectId = null;

    @JsonProperty("project-office-id")
    private String projectOfficeId = null;

    public Embankment upstreamSideSlope(Double upstreamSideSlope) {
        this.upstreamSideSlope = upstreamSideSlope;
        return this;
    }

    /**
     * Get upstreamSideSlope
     *
     * @return upstreamSideSlope
     **/
    @NotNull

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
    @NotNull

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
    @NotNull

    public Double getStructureLength() {
        return structureLength;
    }

    public void setStructureLength(Double structureLength) {
        this.structureLength = structureLength;
    }

    public Embankment heightMax(Double heightMax) {
        this.heightMax = heightMax;
        return this;
    }

    /**
     * Get heightMax
     *
     * @return heightMax
     **/
    @NotNull

    public Double getHeightMax() {
        return heightMax;
    }

    public void setHeightMax(Double heightMax) {
        this.heightMax = heightMax;
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
    @NotNull

    public Double getTopWidth() {
        return topWidth;
    }

    public void setTopWidth(Double topWidth) {
        this.topWidth = topWidth;
    }

    public Embankment unitsId(String unitsId) {
        this.unitsId = unitsId;
        return this;
    }

    /**
     * Get unitsId
     *
     * @return unitsId
     **/
    @NotNull

    public String getUnitsId() {
        return unitsId;
    }

    public void setUnitsId(String unitsId) {
        this.unitsId = unitsId;
    }

    public Embankment downstreamProtType(LookupType downstreamProtType) {
        this.downstreamProtType = downstreamProtType;
        return this;
    }

    /**
     * Get downstreamProtType
     *
     * @return downstreamProtType
     **/
    @NotNull

    @Valid
    public LookupType getDownstreamProtType() {
        return downstreamProtType;
    }

    public void setDownstreamProtType(LookupType downstreamProtType) {
        this.downstreamProtType = downstreamProtType;
    }

    public Embankment upstreamProtType(LookupType upstreamProtType) {
        this.upstreamProtType = upstreamProtType;
        return this;
    }

    /**
     * Get upstreamProtType
     *
     * @return upstreamProtType
     **/
    @NotNull

    @Valid
    public LookupType getUpstreamProtType() {
        return upstreamProtType;
    }

    public void setUpstreamProtType(LookupType upstreamProtType) {
        this.upstreamProtType = upstreamProtType;
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
    @NotNull

    @Valid
    public LookupType getStructureType() {
        return structureType;
    }

    public void setStructureType(LookupType structureType) {
        this.structureType = structureType;
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
    @NotNull

    @Valid
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Embankment projectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    /**
     * Get projectId
     *
     * @return projectId
     **/
    @NotNull

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Embankment projectOfficeId(String projectOfficeId) {
        this.projectOfficeId = projectOfficeId;
        return this;
    }

    /**
     * Get projectOfficeId
     *
     * @return projectOfficeId
     **/
    @NotNull

    public String getProjectOfficeId() {
        return projectOfficeId;
    }

    public void setProjectOfficeId(String projectOfficeId) {
        this.projectOfficeId = projectOfficeId;
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
        return Objects.equals(this.upstreamSideSlope, embankment.upstreamSideSlope)
                && Objects.equals(this.downstreamSideSlope, embankment.downstreamSideSlope)
                && Objects.equals(this.structureLength, embankment.structureLength)
                && Objects.equals(this.heightMax, embankment.heightMax)
                && Objects.equals(this.topWidth, embankment.topWidth)
                && this.unitsId == null || embankment.unitsId == null ? Objects.equals(this.unitsId, embankment.unitsId) : this.unitsId.equalsIgnoreCase(embankment.unitsId)
                && Objects.equals(this.downstreamProtType, embankment.downstreamProtType)
                && Objects.equals(this.upstreamProtType, embankment.upstreamProtType)
                && Objects.equals(this.structureType, embankment.structureType)
                && Objects.equals(this.location, embankment.location)
                && this.projectId == null || embankment.projectId == null ? Objects.equals(this.projectId, embankment.projectId) : this.projectId.equalsIgnoreCase(embankment.projectId)
                && this.projectOfficeId == null || embankment.projectOfficeId == null ? Objects.equals(this.projectOfficeId, embankment.projectOfficeId) : this.projectOfficeId.equalsIgnoreCase(embankment.projectOfficeId)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(upstreamSideSlope, downstreamSideSlope, structureLength, heightMax, topWidth, unitsId == null ? 0 : unitsId.toLowerCase(), downstreamProtType, upstreamProtType, structureType, location, projectId == null ? 0 : projectId.toLowerCase(), projectOfficeId == null ? 0 : projectOfficeId.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Embankment {\n");

        sb.append("    upstreamSideSlope: ").append(toIndentedString(upstreamSideSlope)).append("\n");
        sb.append("    downstreamSideSlope: ").append(toIndentedString(downstreamSideSlope)).append("\n");
        sb.append("    structureLength: ").append(toIndentedString(structureLength)).append("\n");
        sb.append("    heightMax: ").append(toIndentedString(heightMax)).append("\n");
        sb.append("    topWidth: ").append(toIndentedString(topWidth)).append("\n");
        sb.append("    unitsId: ").append(toIndentedString(unitsId)).append("\n");
        sb.append("    downstreamProtType: ").append(toIndentedString(downstreamProtType)).append("\n");
        sb.append("    upstreamProtType: ").append(toIndentedString(upstreamProtType)).append("\n");
        sb.append("    structureType: ").append(toIndentedString(structureType)).append("\n");
        sb.append("    location: ").append(toIndentedString(location)).append("\n");
        sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
        sb.append("    projectOfficeId: ").append(toIndentedString(projectOfficeId)).append("\n");
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
