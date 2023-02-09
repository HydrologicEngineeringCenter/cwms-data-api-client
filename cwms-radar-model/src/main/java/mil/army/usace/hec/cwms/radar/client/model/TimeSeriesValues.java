/*
 * MIT License
 *
 * Copyright (c) 2022 Hydrologic Engineering Center
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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Objects;

/**
 * A representation of a time-series record
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-09T09:29:33.859-07:00[America/Los_Angeles]")
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonPropertyOrder({"date-time", "value", "quality-code"})
public class TimeSeriesValues {
    @JsonProperty("date-time")
    private Long dateTime = null;
    @JsonProperty("value")
    private Double value = null;
    @JsonProperty("quality-code")
    private Integer qualityCode = null;

    @JsonCreator
    public TimeSeriesValues(@JsonProperty("date-time") Long dateTime, @JsonProperty("value") Double value,
                            @JsonProperty("quality-code") Integer qualityCode) {
        this.dateTime = dateTime;
        this.value = value;
        this.qualityCode = qualityCode;
    }

    public TimeSeriesValues dateTime(Long dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    /**
     * Milliseconds since 1970-01-01 (Unix Epoch)
     *
     * @return dateTime
     **/

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    public TimeSeriesValues value(Double value) {
        this.value = value;
        return this;
    }

    /**
     * Requested time-series data value
     *
     * @return value
     **/

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public TimeSeriesValues qualityCode(Integer qualityCode) {
        this.qualityCode = qualityCode;
        return this;
    }

    /**
     * Get qualityCode
     *
     * @return qualityCode
     **/

    public Integer getQualityCode() {
        return qualityCode;
    }

    public void setQualityCode(Integer qualityCode) {
        this.qualityCode = qualityCode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSeriesValues timeSeriesValues = (TimeSeriesValues) o;
        return Objects.equals(this.dateTime, timeSeriesValues.dateTime) && Objects.equals(this.value, timeSeriesValues.value) &&
            Objects.equals(this.qualityCode, timeSeriesValues.qualityCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, value, qualityCode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesValues {\n");

        sb.append("    dateTime: ").append(toIndentedString(dateTime)).append("\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
        sb.append("    qualityCode: ").append(toIndentedString(qualityCode)).append("\n");
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
