/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.validation.Valid;

/**
 * A representation of a timeseries group
 */
@ApiModel(description = "A representation of a timeseries group")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:49:54.974-07:00[America/Los_Angeles]")
public class TimeSeriesGroup {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("timeSeriesCategory")
    private TimeSeriesCategory timeSeriesCategory = null;

    @JsonProperty("description")
    private String description = null;

    public TimeSeriesGroup id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/
    @ApiModelProperty(value = "")

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TimeSeriesGroup timeSeriesCategory(TimeSeriesCategory timeSeriesCategory) {
        this.timeSeriesCategory = timeSeriesCategory;
        return this;
    }

    /**
     * Get timeSeriesCategory
     *
     * @return timeSeriesCategory
     **/
    @ApiModelProperty(value = "")

    @Valid
    public TimeSeriesCategory getTimeSeriesCategory() {
        return timeSeriesCategory;
    }

    public void setTimeSeriesCategory(TimeSeriesCategory timeSeriesCategory) {
        this.timeSeriesCategory = timeSeriesCategory;
    }

    public TimeSeriesGroup description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get description
     *
     * @return description
     **/
    @ApiModelProperty(value = "")

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSeriesGroup timeSeriesGroup = (TimeSeriesGroup) o;
        return Objects.equals(this.id, timeSeriesGroup.id) &&
            Objects.equals(this.timeSeriesCategory, timeSeriesGroup.timeSeriesCategory) &&
            Objects.equals(this.description, timeSeriesGroup.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeSeriesCategory, description);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesGroup {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    timeSeriesCategory: ").append(toIndentedString(timeSeriesCategory))
            .append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
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
