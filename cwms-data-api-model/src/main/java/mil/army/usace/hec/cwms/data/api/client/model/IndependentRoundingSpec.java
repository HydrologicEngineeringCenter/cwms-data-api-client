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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Generated;

import java.util.Objects;

/**
 * IndependentRoundingSpec
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-09T09:29:33.859-07:00[America/Los_Angeles]")
public class IndependentRoundingSpec {
    @JsonProperty("position")
    private Integer position = null;

    @JsonProperty("value")
    private String value = null;

    public IndependentRoundingSpec position(Integer position) {
        this.position = position;
        return this;
    }

    /**
     * Get position
     *
     * @return position
     **/

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public IndependentRoundingSpec value(String value) {
        this.value = value;
        return this;
    }

    /**
     * Get value
     *
     * @return value
     **/

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
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
        IndependentRoundingSpec independentRoundingSpec = (IndependentRoundingSpec) o;
        return Objects.equals(this.position, independentRoundingSpec.position) && this.value == null || independentRoundingSpec.value == null ?
            Objects.equals(this.value, independentRoundingSpec.value) : this.value.equalsIgnoreCase(independentRoundingSpec.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, value == null ? 0 : value.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class IndependentRoundingSpec {\n");

        sb.append("    position: ").append(toIndentedString(position)).append("\n");
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
