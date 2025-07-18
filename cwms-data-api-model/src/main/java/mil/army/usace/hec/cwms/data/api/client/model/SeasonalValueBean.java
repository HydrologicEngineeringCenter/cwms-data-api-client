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
 * List of Repeating seasonal values. The values repeater after the specified interval. A yearly interval seasonable could have 12 different values, one for each month for example. Mutually exclusive with seasonalTimeSeriesId and siParameterUnitsConstantValue
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-11T12:56:36.285-07:00[America/Los_Angeles]")
public class SeasonalValueBean {
    @JsonProperty("value")
    private Double value = null;

    @JsonProperty("offset-months")
    private Integer offsetMonths = null;

    @JsonProperty("offset-minutes")
    private Integer offsetMinutes = null;

    public SeasonalValueBean value(Double value) {
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

    public SeasonalValueBean offsetMonths(Integer offsetMonths) {
        this.offsetMonths = offsetMonths;
        return this;
    }

    /**
     * Get offsetMonths
     *
     * @return offsetMonths
     **/

    public Integer getOffsetMonths() {
        return offsetMonths;
    }

    public void setOffsetMonths(Integer offsetMonths) {
        this.offsetMonths = offsetMonths;
    }

    public SeasonalValueBean offsetMinutes(Integer offsetMinutes) {
        this.offsetMinutes = offsetMinutes;
        return this;
    }

    /**
     * Get offsetMinutes
     *
     * @return offsetMinutes
     **/

    public Integer getOffsetMinutes() {
        return offsetMinutes;
    }

    public void setOffsetMinutes(Integer offsetMinutes) {
        this.offsetMinutes = offsetMinutes;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SeasonalValueBean seasonalValueBean = (SeasonalValueBean) o;
        return Objects.equals(this.value, seasonalValueBean.value) && Objects.equals(this.offsetMonths, seasonalValueBean.offsetMonths) &&
            Objects.equals(this.offsetMinutes, seasonalValueBean.offsetMinutes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, offsetMonths, offsetMinutes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SeasonalValueBean {\n");

        sb.append("    value: ").append(toIndentedString(value)).append("\n");
        sb.append("    offsetMonths: ").append(toIndentedString(offsetMonths)).append("\n");
        sb.append("    offsetMinutes: ").append(toIndentedString(offsetMinutes)).append("\n");
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
