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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 * A representation of a time-series record
 */
@ApiModel(description = "A representation of a time-series record")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:49:54.974-07:00[America/Los_Angeles]")
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class TimeSeriesValues {
    private Long dateTime = null;
    private Double value = null;
    private Integer qualityCode = null;

    @JsonCreator
    public TimeSeriesValues(@JsonProperty("date-time") Long dateTime,
                            @JsonProperty("value") Double value,
                            @JsonProperty("quality-code") Integer qualityCode) {
        this.dateTime = dateTime;
        this.value = value;
        this.qualityCode = qualityCode;
    }

    /**
     * Milliseconds since 1970-01-01 (Unix Epoch)
     *
     * @return dateTime
     **/
    @ApiModelProperty(value = "Milliseconds since 1970-01-01 (Unix Epoch)")

    public Long getDateTime() {
        return dateTime;
    }

    /**
     * Requested time-series data value
     *
     * @return value
     **/
    @ApiModelProperty(value = "Requested time-series data value")

    public Double getValue() {
        return value;
    }

    /**
     * Get qualityCode
     *
     * @return qualityCode
     **/
    @ApiModelProperty(value = "")

    public Integer getQualityCode() {
        return qualityCode;
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
        return Objects.equals(this.dateTime, timeSeriesValues.dateTime)
            && Objects.equals(this.value, timeSeriesValues.value)
            && Objects.equals(this.qualityCode, timeSeriesValues.qualityCode);
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
