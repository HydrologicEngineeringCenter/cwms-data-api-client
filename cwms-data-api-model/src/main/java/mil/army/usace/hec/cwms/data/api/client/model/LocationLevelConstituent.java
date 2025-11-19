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
import java.util.Objects;

/**
 * LocationLevelConstituent
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-11-17T16:46:44.562396200-08:00[America/Los_Angeles]")
public class LocationLevelConstituent extends RatingConstituent {

    @JsonProperty("attribute-id")
    private String attributeId = null;

    @JsonProperty("attribute-value")
    private Double attributeValue = null;

    @JsonProperty("attribute-units")
    private String attributeUnits = null;

    public LocationLevelConstituent attributeId(String attributeId) {
        this.attributeId = attributeId;
        return this;
    }

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    public LocationLevelConstituent attributeValue(Double attributeValue) {
        this.attributeValue = attributeValue;
        return this;
    }

    public Double getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(Double attributeValue) {
        this.attributeValue = attributeValue;
    }

    public LocationLevelConstituent attributeUnits(String attributeUnits) {
        this.attributeUnits = attributeUnits;
        return this;
    }

    public String getAttributeUnits() {
        return attributeUnits;
    }

    public void setAttributeUnits(String attributeUnits) {
        this.attributeUnits = attributeUnits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LocationLevelConstituent locationLevelConstituent = (LocationLevelConstituent) o;
        return this.attributeId == null || locationLevelConstituent.attributeId == null ?
            Objects.equals(this.attributeId, locationLevelConstituent.attributeId) :
            this.attributeId.equalsIgnoreCase(locationLevelConstituent.attributeId)
                && Objects.equals(this.attributeValue, locationLevelConstituent.attributeValue)
                && this.attributeUnits == null || locationLevelConstituent.attributeUnits == null ?
                Objects.equals(this.attributeUnits, locationLevelConstituent.attributeUnits) :
                this.attributeUnits.equalsIgnoreCase(locationLevelConstituent.attributeUnits)
                    &&
                    super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributeId == null ? 0 : attributeId.toLowerCase(), attributeValue,
            attributeUnits == null ? 0 : attributeUnits.toLowerCase(), super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LocationLevelConstituent {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    attributeId: ").append(toIndentedString(attributeId)).append("\n");
        sb.append("    attributeValue: ").append(toIndentedString(attributeValue)).append("\n");
        sb.append("    attributeUnits: ").append(toIndentedString(attributeUnits)).append("\n");
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
