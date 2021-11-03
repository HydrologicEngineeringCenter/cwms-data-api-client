/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 * TimeSeriesIntervalDuration
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:49:54.974-07:00[America/Los_Angeles]")
public class TimeSeriesIntervalDuration {
    @JsonProperty("seconds")
    private Long seconds = null;

    @JsonProperty("nano")
    private Integer nano = null;

    @JsonProperty("zero")
    private Boolean zero = null;

    @JsonProperty("negative")
    private Boolean negative = null;

    public TimeSeriesIntervalDuration seconds(Long seconds) {
        this.seconds = seconds;
        return this;
    }

    /**
     * Get seconds
     *
     * @return seconds
     **/
    @ApiModelProperty(value = "")

    public Long getSeconds() {
        return seconds;
    }

    public void setSeconds(Long seconds) {
        this.seconds = seconds;
    }

    public TimeSeriesIntervalDuration nano(Integer nano) {
        this.nano = nano;
        return this;
    }

    /**
     * Get nano
     *
     * @return nano
     **/
    @ApiModelProperty(value = "")

    public Integer getNano() {
        return nano;
    }

    public void setNano(Integer nano) {
        this.nano = nano;
    }

    public TimeSeriesIntervalDuration zero(Boolean zero) {
        this.zero = zero;
        return this;
    }

    /**
     * Get zero
     *
     * @return zero
     **/
    @ApiModelProperty(value = "")

    public Boolean isZero() {
        return zero;
    }

    public void setZero(Boolean zero) {
        this.zero = zero;
    }

    public TimeSeriesIntervalDuration negative(Boolean negative) {
        this.negative = negative;
        return this;
    }

    /**
     * Get negative
     *
     * @return negative
     **/
    @ApiModelProperty(value = "")

    public Boolean isNegative() {
        return negative;
    }

    public void setNegative(Boolean negative) {
        this.negative = negative;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSeriesIntervalDuration timeSeriesIntervalDuration = (TimeSeriesIntervalDuration) o;
        return Objects.equals(this.seconds, timeSeriesIntervalDuration.seconds) &&
            Objects.equals(this.nano, timeSeriesIntervalDuration.nano) &&
            Objects.equals(this.zero, timeSeriesIntervalDuration.zero) &&
            Objects.equals(this.negative, timeSeriesIntervalDuration.negative);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seconds, nano, zero, negative);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesIntervalDuration {\n");

        sb.append("    seconds: ").append(toIndentedString(seconds)).append("\n");
        sb.append("    nano: ").append(toIndentedString(nano)).append("\n");
        sb.append("    zero: ").append(toIndentedString(zero)).append("\n");
        sb.append("    negative: ").append(toIndentedString(negative)).append("\n");
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
