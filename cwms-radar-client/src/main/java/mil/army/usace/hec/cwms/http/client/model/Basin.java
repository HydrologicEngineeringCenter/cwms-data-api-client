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
 * Basin
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class Basin {
    @JsonProperty("basinName")
    private String basinName = null;

    @JsonProperty("officeId")
    private String officeId = null;

    @JsonProperty("primaryStream")
    private Stream primaryStream = null;

    @JsonProperty("sortOrder")
    private Double sortOrder = null;

    @JsonProperty("basinArea")
    private Double basinArea = null;

    @JsonProperty("contributingArea")
    private Double contributingArea = null;

    @JsonProperty("parentBasinId")
    private String parentBasinId = null;

    public Basin basinName(String basinName) {
        this.basinName = basinName;
        return this;
    }

    /**
     * Get basinName
     *
     * @return basinName
     **/
    @ApiModelProperty(value = "")

    public String getBasinName() {
        return basinName;
    }

    public void setBasinName(String basinName) {
        this.basinName = basinName;
    }

    public Basin officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    /**
     * Get officeId
     *
     * @return officeId
     **/
    @ApiModelProperty(value = "")

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public Basin primaryStream(Stream primaryStream) {
        this.primaryStream = primaryStream;
        return this;
    }

    /**
     * Get primaryStream
     *
     * @return primaryStream
     **/
    @ApiModelProperty(value = "")

    @Valid
    public Stream getPrimaryStream() {
        return primaryStream;
    }

    public void setPrimaryStream(Stream primaryStream) {
        this.primaryStream = primaryStream;
    }

    public Basin sortOrder(Double sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    /**
     * Get sortOrder
     *
     * @return sortOrder
     **/
    @ApiModelProperty(value = "")

    public Double getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Double sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Basin basinArea(Double basinArea) {
        this.basinArea = basinArea;
        return this;
    }

    /**
     * Get basinArea
     *
     * @return basinArea
     **/
    @ApiModelProperty(value = "")

    public Double getBasinArea() {
        return basinArea;
    }

    public void setBasinArea(Double basinArea) {
        this.basinArea = basinArea;
    }

    public Basin contributingArea(Double contributingArea) {
        this.contributingArea = contributingArea;
        return this;
    }

    /**
     * Get contributingArea
     *
     * @return contributingArea
     **/
    @ApiModelProperty(value = "")

    public Double getContributingArea() {
        return contributingArea;
    }

    public void setContributingArea(Double contributingArea) {
        this.contributingArea = contributingArea;
    }

    public Basin parentBasinId(String parentBasinId) {
        this.parentBasinId = parentBasinId;
        return this;
    }

    /**
     * Get parentBasinId
     *
     * @return parentBasinId
     **/
    @ApiModelProperty(value = "")

    public String getParentBasinId() {
        return parentBasinId;
    }

    public void setParentBasinId(String parentBasinId) {
        this.parentBasinId = parentBasinId;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Basin basin = (Basin) o;
        return Objects.equals(this.basinName, basin.basinName) &&
            Objects.equals(this.officeId, basin.officeId) &&
            Objects.equals(this.primaryStream, basin.primaryStream) &&
            Objects.equals(this.sortOrder, basin.sortOrder) &&
            Objects.equals(this.basinArea, basin.basinArea) &&
            Objects.equals(this.contributingArea, basin.contributingArea) &&
            Objects.equals(this.parentBasinId, basin.parentBasinId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basinName, officeId, primaryStream, sortOrder, basinArea,
            contributingArea, parentBasinId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Basin {\n");

        sb.append("    basinName: ").append(toIndentedString(basinName)).append("\n");
        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    primaryStream: ").append(toIndentedString(primaryStream)).append("\n");
        sb.append("    sortOrder: ").append(toIndentedString(sortOrder)).append("\n");
        sb.append("    basinArea: ").append(toIndentedString(basinArea)).append("\n");
        sb.append("    contributingArea: ").append(toIndentedString(contributingArea)).append("\n");
        sb.append("    parentBasinId: ").append(toIndentedString(parentBasinId)).append("\n");
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
