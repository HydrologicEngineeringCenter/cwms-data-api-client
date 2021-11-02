/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 * A representation of a time-series record
 */
@ApiModel(description = "A representation of a time-series record")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class TimeSeriesRecord {
    @JsonProperty("date-time")
    private Long dateTime = null;

    @JsonProperty("value")
    private Double value = null;

    @JsonProperty("quality-code")
    private Integer qualityCode = null;

    public TimeSeriesRecord dateTime(Long dateTime) {
        this.dateTime = dateTime;
        return this;
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

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    public TimeSeriesRecord value(Double value) {
        this.value = value;
        return this;
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

    public void setValue(Double value) {
        this.value = value;
    }

    public TimeSeriesRecord qualityCode(Integer qualityCode) {
        this.qualityCode = qualityCode;
        return this;
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

    public void setQualityCode(Integer qualityCode) {
        this.qualityCode = qualityCode;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSeriesRecord timeSeriesRecord = (TimeSeriesRecord) o;
        return Objects.equals(this.dateTime, timeSeriesRecord.dateTime) &&
            Objects.equals(this.value, timeSeriesRecord.value) &&
            Objects.equals(this.qualityCode, timeSeriesRecord.qualityCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, value, qualityCode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesRecord {\n");

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
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
