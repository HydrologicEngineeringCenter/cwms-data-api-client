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

package mil.army.usace.hec.cwms.data.api.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * GateSetting
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-10-11T16:58:13.325841800-07:00[America/Los_Angeles]")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true )
@JsonSubTypes({@JsonSubTypes.Type(value = GateChange.class, name = "gate-change")})
@JsonTypeName("gate-setting")
public class GateSetting {

    @JsonProperty("location-id")
    private CwmsId locationId = null;

    @JsonProperty("opening")
    private Double opening = null;

    @JsonProperty("opening-parameter")
    private String openingParameter = null;

    @JsonProperty("opening-units")
    private String openingUnits = null;

    @JsonProperty("invert-elevation")
    private Double invertElevation = null;

    @JsonProperty("type")
    private final String type = "gate-setting";

    public GateSetting locationId(CwmsId locationId) {
        this.locationId = locationId;
        return this;
    }

    public CwmsId getLocationId() {
        return locationId;
    }

    public void setLocationId(CwmsId locationId) {
        this.locationId = locationId;
    }

    public GateSetting opening(Double opening) {
        this.opening = opening;
        return this;
    }

    public Double getOpening() {
        return opening;
    }

    public void setOpening(Double opening) {
        this.opening = opening;
    }

    public GateSetting openingParameter(String openingParameter) {
        this.openingParameter = openingParameter;
        return this;
    }

    public String getOpeningParameter() {
        return openingParameter;
    }

    public void setOpeningParameter(String openingParameter) {
        this.openingParameter = openingParameter;
    }

    public GateSetting openingUnits(String openingUnits) {
        this.openingUnits = openingUnits;
        return this;
    }

    public String getOpeningUnits() {
        return openingUnits;
    }

    public void setOpeningUnits(String openingUnits) {
        this.openingUnits = openingUnits;
    }

    public GateSetting invertElevation(Double invertElevation) {
        this.invertElevation = invertElevation;
        return this;
    }

    public Double getInvertElevation() {
        return invertElevation;
    }

    public void setInvertElevation(Double invertElevation) {
        this.invertElevation = invertElevation;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GateSetting gateSetting = (GateSetting) o;
        return Objects.equals(this.locationId, gateSetting.locationId)
                && Objects.equals(this.opening, gateSetting.opening)
                && this.openingParameter == null || gateSetting.openingParameter == null
                ? Objects.equals(this.openingParameter, gateSetting.openingParameter)
                : this.openingParameter.equalsIgnoreCase(gateSetting.openingParameter)
                && this.openingUnits == null || gateSetting.openingUnits == null
                ? Objects.equals(this.openingUnits, gateSetting.openingUnits)
                : this.openingUnits.equalsIgnoreCase(gateSetting.openingUnits)
                && Objects.equals(this.invertElevation, gateSetting.invertElevation)
                && this.type == null || gateSetting.type == null
                ? Objects.equals(this.type, gateSetting.type) : this.type.equalsIgnoreCase(gateSetting.type)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, opening, openingParameter == null
                ? 0 : openingParameter.toLowerCase(), openingUnits == null
                ? 0 : openingUnits.toLowerCase(), invertElevation, type == null ? 0 : type.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GateSetting {\n");

        sb.append("    locationId: ").append(toIndentedString(locationId)).append("\n");
        sb.append("    opening: ").append(toIndentedString(opening)).append("\n");
        sb.append("    openingParameter: ").append(toIndentedString(openingParameter)).append("\n");
        sb.append("    openingUnits: ").append(toIndentedString(openingUnits)).append("\n");
        sb.append("    invertElevation: ").append(toIndentedString(invertElevation)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
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
