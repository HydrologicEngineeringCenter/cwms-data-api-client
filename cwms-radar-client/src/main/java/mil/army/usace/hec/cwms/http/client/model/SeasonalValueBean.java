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
 * SeasonalValueBean
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
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
    @ApiModelProperty(value = "")

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
    @ApiModelProperty(value = "")

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
    @ApiModelProperty(value = "")

    public Integer getOffsetMinutes() {
        return offsetMinutes;
    }

    public void setOffsetMinutes(Integer offsetMinutes) {
        this.offsetMinutes = offsetMinutes;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SeasonalValueBean seasonalValueBean = (SeasonalValueBean) o;
        return Objects.equals(this.value, seasonalValueBean.value) &&
            Objects.equals(this.offsetMonths, seasonalValueBean.offsetMonths) &&
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
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
