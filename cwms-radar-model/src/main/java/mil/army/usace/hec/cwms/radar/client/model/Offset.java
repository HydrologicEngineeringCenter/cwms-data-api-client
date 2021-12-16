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
import java.util.Objects;

/**
 * Offset
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-12-16T09:13:30.631614-08:00[America/Los_Angeles]")
public class Offset {
    @JsonProperty("estimate")
    private Boolean estimate = null;

    @JsonProperty("to-datum")
    private String toDatum = null;

    @JsonProperty("value")
    private Double value = null;

    public Offset estimate(Boolean estimate) {
        this.estimate = estimate;
        return this;
    }

    /**
     * Get estimate
     *
     * @return estimate
     **/

    public Boolean isEstimate() {
        return estimate;
    }

    public void setEstimate(Boolean estimate) {
        this.estimate = estimate;
    }

    public Offset toDatum(String toDatum) {
        this.toDatum = toDatum;
        return this;
    }

    /**
     * Get toDatum
     *
     * @return toDatum
     **/

    public String getToDatum() {
        return toDatum;
    }

    public void setToDatum(String toDatum) {
        this.toDatum = toDatum;
    }

    public Offset value(Double value) {
        this.value = value;
        return this;
    }

    /**
     * Get value
     *
     * @return value
     **/

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Offset offset = (Offset) o;
        return Objects.equals(this.estimate, offset.estimate)
            && this.toDatum == null || offset.toDatum == null ? Objects.equals(this.toDatum, offset.toDatum) :
            this.toDatum.equalsIgnoreCase(offset.toDatum)
                && Objects.equals(this.value, offset.value)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(estimate, toDatum == null ? 0 : toDatum.toLowerCase(), value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Offset {\n");

        sb.append("    estimate: ").append(toIndentedString(estimate)).append("\n");
        sb.append("    toDatum: ").append(toIndentedString(toDatum)).append("\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
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
