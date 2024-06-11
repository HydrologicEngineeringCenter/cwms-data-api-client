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

import java.util.Objects;

/**
 * LookupType
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-06-10T15:17:37.553488400-07:00[America/Los_Angeles]")
public class LookupType {
    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("display-value")
    private String displayValue = null;

    @JsonProperty("tooltip")
    private String tooltip = null;

    @JsonProperty("active")
    private Boolean active = null;

    public LookupType officeId(String officeId) {
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

    public LookupType displayValue(String displayValue) {
        this.displayValue = displayValue;
        return this;
    }

    /**
     * Get displayValue
     *
     * @return displayValue
     **/

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public LookupType tooltip(String tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    /**
     * Get tooltip
     *
     * @return tooltip
     **/

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public LookupType active(Boolean active) {
        this.active = active;
        return this;
    }

    /**
     * Get active
     *
     * @return active
     **/

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LookupType lookupType = (LookupType) o;
        return this.officeId == null || lookupType.officeId == null ? Objects.equals(this.officeId, lookupType.officeId) : this.officeId.equalsIgnoreCase(lookupType.officeId)
                && this.displayValue == null || lookupType.displayValue == null ? Objects.equals(this.displayValue, lookupType.displayValue) : this.displayValue.equalsIgnoreCase(lookupType.displayValue)
                && this.tooltip == null || lookupType.tooltip == null ? Objects.equals(this.tooltip, lookupType.tooltip) : this.tooltip.equalsIgnoreCase(lookupType.tooltip)
                && Objects.equals(this.active, lookupType.active)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeId == null ? 0 : officeId.toLowerCase(), displayValue == null ? 0 : displayValue.toLowerCase(), tooltip == null ? 0 : tooltip.toLowerCase(), active);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LookupType {\n");

        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    displayValue: ").append(toIndentedString(displayValue)).append("\n");
        sb.append("    tooltip: ").append(toIndentedString(tooltip)).append("\n");
        sb.append("    active: ").append(toIndentedString(active)).append("\n");
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
