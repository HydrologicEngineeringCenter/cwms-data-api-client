/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.validation.Valid;

/**
 * TimeSeriesIntervalUnits
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class TimeSeriesIntervalUnits {
    @JsonProperty("dateBased")
    private Boolean dateBased = null;

    @JsonProperty("durationEstimated")
    private Boolean durationEstimated = null;

    @JsonProperty("timeBased")
    private Boolean timeBased = null;

    @JsonProperty("duration")
    private TimeSeriesIntervalDuration duration = null;

    public TimeSeriesIntervalUnits dateBased(Boolean dateBased) {
        this.dateBased = dateBased;
        return this;
    }

    /**
     * Get dateBased
     *
     * @return dateBased
     **/
    @ApiModelProperty(value = "")

    public Boolean isDateBased() {
        return dateBased;
    }

    public void setDateBased(Boolean dateBased) {
        this.dateBased = dateBased;
    }

    public TimeSeriesIntervalUnits durationEstimated(Boolean durationEstimated) {
        this.durationEstimated = durationEstimated;
        return this;
    }

    /**
     * Get durationEstimated
     *
     * @return durationEstimated
     **/
    @ApiModelProperty(value = "")

    public Boolean isDurationEstimated() {
        return durationEstimated;
    }

    public void setDurationEstimated(Boolean durationEstimated) {
        this.durationEstimated = durationEstimated;
    }

    public TimeSeriesIntervalUnits timeBased(Boolean timeBased) {
        this.timeBased = timeBased;
        return this;
    }

    /**
     * Get timeBased
     *
     * @return timeBased
     **/
    @ApiModelProperty(value = "")

    public Boolean isTimeBased() {
        return timeBased;
    }

    public void setTimeBased(Boolean timeBased) {
        this.timeBased = timeBased;
    }

    public TimeSeriesIntervalUnits duration(TimeSeriesIntervalDuration duration) {
        this.duration = duration;
        return this;
    }

    /**
     * Get duration
     *
     * @return duration
     **/
    @ApiModelProperty(value = "")

    @Valid
    public TimeSeriesIntervalDuration getDuration() {
        return duration;
    }

    public void setDuration(TimeSeriesIntervalDuration duration) {
        this.duration = duration;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSeriesIntervalUnits timeSeriesIntervalUnits = (TimeSeriesIntervalUnits) o;
        return Objects.equals(this.dateBased, timeSeriesIntervalUnits.dateBased) &&
            Objects.equals(this.durationEstimated, timeSeriesIntervalUnits.durationEstimated) &&
            Objects.equals(this.timeBased, timeSeriesIntervalUnits.timeBased) &&
            Objects.equals(this.duration, timeSeriesIntervalUnits.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateBased, durationEstimated, timeBased, duration);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesIntervalUnits {\n");

        sb.append("    dateBased: ").append(toIndentedString(dateBased)).append("\n");
        sb.append("    durationEstimated: ").append(toIndentedString(durationEstimated))
            .append("\n");
        sb.append("    timeBased: ").append(toIndentedString(timeBased)).append("\n");
        sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
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
