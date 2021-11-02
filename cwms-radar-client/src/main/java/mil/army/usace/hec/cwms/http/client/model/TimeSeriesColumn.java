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

/**
 * TimeSeriesColumn
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class TimeSeriesColumn {
    @JsonProperty("name")
    private String name = null;

    @JsonProperty("ordinal")
    private Integer ordinal = null;

    @JsonProperty("datatype")
    private String datatype = null;

    public TimeSeriesColumn name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     **/
    @ApiModelProperty(value = "")

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimeSeriesColumn ordinal(Integer ordinal) {
        this.ordinal = ordinal;
        return this;
    }

    /**
     * Get ordinal
     *
     * @return ordinal
     **/
    @ApiModelProperty(value = "")

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public TimeSeriesColumn datatype(String datatype) {
        this.datatype = datatype;
        return this;
    }

    /**
     * Get datatype
     *
     * @return datatype
     **/
    @ApiModelProperty(value = "")

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSeriesColumn timeSeriesColumn = (TimeSeriesColumn) o;
        return Objects.equals(this.name, timeSeriesColumn.name) &&
            Objects.equals(this.ordinal, timeSeriesColumn.ordinal) &&
            Objects.equals(this.datatype, timeSeriesColumn.datatype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ordinal, datatype);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesColumn {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    ordinal: ").append(toIndentedString(ordinal)).append("\n");
        sb.append("    datatype: ").append(toIndentedString(datatype)).append("\n");
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
