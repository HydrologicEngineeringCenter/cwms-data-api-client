/*
 * MIT License
 *
 * Copyright (c) 2025 Hydrologic Engineering Center
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

package mil.army.usace.hec.cwms.data.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * VirtualLocationLevel
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-11-12T16:30:51.643037900-08:00[America/Los_Angeles]")
@JsonDeserialize()
public class VirtualLocationLevel extends LocationLevel {

    @JsonProperty("constituents")
    @Valid
    private List<RatingConstituent> constituents = new ArrayList<>();

    @JsonProperty("constituent-connections")
    private String constituentConnections = null;

    public VirtualLocationLevel constituents(List<RatingConstituent> constituents) {
        this.constituents = constituents;
        return this;
    }

    public VirtualLocationLevel addConstituentsItem(RatingConstituent constituentsItem) {
        if (this.constituents == null) {
            this.constituents = new ArrayList<>();
        }
        this.constituents.add(constituentsItem);
        return this;
    }

    public List<RatingConstituent> getConstituents() {
        return constituents;
    }

    public void setConstituents(List<RatingConstituent> constituents) {
        this.constituents = constituents;
    }

    public VirtualLocationLevel constituentConnections(String constituentConnections) {
        this.constituentConnections = constituentConnections;
        return this;
    }

    public String getConstituentConnections() {
        return constituentConnections;
    }

    public void setConstituentConnections(String constituentConnections) {
        this.constituentConnections = constituentConnections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VirtualLocationLevel virtualLocationLevel = (VirtualLocationLevel) o;
        return super.equals(o) && Objects.equals(this.constituents, virtualLocationLevel.constituents)
            && this.constituentConnections == null || virtualLocationLevel.constituentConnections == null ?
            Objects.equals(this.constituentConnections, virtualLocationLevel.constituentConnections) :
            this.constituentConnections.equalsIgnoreCase(virtualLocationLevel.constituentConnections)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), constituents,
            constituentConnections == null ? 0 : constituentConnections.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class VirtualLocationLevel {\n");

        sb.append("    officeId: ").append(toIndentedString(getOfficeId())).append("\n");
        sb.append("    locationLevelId: ").append(toIndentedString(getLocationLevelId())).append("\n");
        sb.append("    specifiedLevelId: ").append(toIndentedString(getSpecifiedLevelId())).append("\n");
        sb.append("    expirationDate: ").append(toIndentedString(getExpirationDate())).append("\n");
        sb.append("    parameterId: ").append(toIndentedString(getParameterId())).append("\n");
        sb.append("    parameterTypeId: ").append(toIndentedString(getParameterTypeId())).append("\n");
        sb.append("    interpolateString: ").append(toIndentedString(getInterpolateString())).append("\n");
        sb.append("    levelUnitsId: ").append(toIndentedString(getLevelUnitsId())).append("\n");
        sb.append("    levelDate: ").append(toIndentedString(getLevelDate())).append("\n");
        sb.append("    levelComment: ").append(toIndentedString(getLevelComment())).append("\n");
        sb.append("    durationId: ").append(toIndentedString(getDurationId())).append("\n");
        sb.append("    attributeValue: ").append(toIndentedString(getAttributeValue())).append("\n");
        sb.append("    attributeUnitsId: ").append(toIndentedString(getAttributeUnitsId())).append("\n");
        sb.append("    attributeParameterTypeId: ").append(toIndentedString(getAttributeParameterTypeId()))
            .append("\n");
        sb.append("    attributeParameterId: ").append(toIndentedString(getAttributeParameterId())).append("\n");
        sb.append("    attributeDurationId: ").append(toIndentedString(getAttributeDurationId())).append("\n");
        sb.append("    attributeComment: ").append(toIndentedString(getAttributeComment())).append("\n");
        sb.append("    constituents: ").append(toIndentedString(constituents)).append("\n");
        sb.append("    constituentConnections: ").append(toIndentedString(constituentConnections)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
