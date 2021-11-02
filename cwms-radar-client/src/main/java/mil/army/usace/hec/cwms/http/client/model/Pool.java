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
import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.Valid;

/**
 * List of retrieved pools
 */
@ApiModel(description = "List of retrieved pools")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class Pool {
    @JsonProperty("attribute")
    private BigDecimal attribute = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("clobText")
    private String clobText = null;

    @JsonProperty("projectId")
    private String projectId = null;

    @JsonProperty("bottomLevelId")
    private String bottomLevelId = null;

    @JsonProperty("topLevelId")
    private String topLevelId = null;

    @JsonProperty("poolName")
    private PoolNameType poolName = null;

    @JsonProperty("implicit")
    private Boolean implicit = null;

    public Pool attribute(BigDecimal attribute) {
        this.attribute = attribute;
        return this;
    }

    /**
     * Get attribute
     *
     * @return attribute
     **/
    @ApiModelProperty(value = "")

    @Valid
    public BigDecimal getAttribute() {
        return attribute;
    }

    public void setAttribute(BigDecimal attribute) {
        this.attribute = attribute;
    }

    public Pool description(String description) {
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

    public Pool clobText(String clobText) {
        this.clobText = clobText;
        return this;
    }

    /**
     * Get clobText
     *
     * @return clobText
     **/
    @ApiModelProperty(value = "")

    public String getClobText() {
        return clobText;
    }

    public void setClobText(String clobText) {
        this.clobText = clobText;
    }

    public Pool projectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    /**
     * Get projectId
     *
     * @return projectId
     **/
    @ApiModelProperty(value = "")

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Pool bottomLevelId(String bottomLevelId) {
        this.bottomLevelId = bottomLevelId;
        return this;
    }

    /**
     * Get bottomLevelId
     *
     * @return bottomLevelId
     **/
    @ApiModelProperty(value = "")

    public String getBottomLevelId() {
        return bottomLevelId;
    }

    public void setBottomLevelId(String bottomLevelId) {
        this.bottomLevelId = bottomLevelId;
    }

    public Pool topLevelId(String topLevelId) {
        this.topLevelId = topLevelId;
        return this;
    }

    /**
     * Get topLevelId
     *
     * @return topLevelId
     **/
    @ApiModelProperty(value = "")

    public String getTopLevelId() {
        return topLevelId;
    }

    public void setTopLevelId(String topLevelId) {
        this.topLevelId = topLevelId;
    }

    public Pool poolName(PoolNameType poolName) {
        this.poolName = poolName;
        return this;
    }

    /**
     * Get poolName
     *
     * @return poolName
     **/
    @ApiModelProperty(value = "")

    @Valid
    public PoolNameType getPoolName() {
        return poolName;
    }

    public void setPoolName(PoolNameType poolName) {
        this.poolName = poolName;
    }

    public Pool implicit(Boolean implicit) {
        this.implicit = implicit;
        return this;
    }

    /**
     * Get implicit
     *
     * @return implicit
     **/
    @ApiModelProperty(value = "")

    public Boolean isImplicit() {
        return implicit;
    }

    public void setImplicit(Boolean implicit) {
        this.implicit = implicit;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pool pool = (Pool) o;
        return Objects.equals(this.attribute, pool.attribute) &&
            Objects.equals(this.description, pool.description) &&
            Objects.equals(this.clobText, pool.clobText) &&
            Objects.equals(this.projectId, pool.projectId) &&
            Objects.equals(this.bottomLevelId, pool.bottomLevelId) &&
            Objects.equals(this.topLevelId, pool.topLevelId) &&
            Objects.equals(this.poolName, pool.poolName) &&
            Objects.equals(this.implicit, pool.implicit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attribute, description, clobText, projectId, bottomLevelId, topLevelId,
            poolName, implicit);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Pool {\n");

        sb.append("    attribute: ").append(toIndentedString(attribute)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    clobText: ").append(toIndentedString(clobText)).append("\n");
        sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
        sb.append("    bottomLevelId: ").append(toIndentedString(bottomLevelId)).append("\n");
        sb.append("    topLevelId: ").append(toIndentedString(topLevelId)).append("\n");
        sb.append("    poolName: ").append(toIndentedString(poolName)).append("\n");
        sb.append("    implicit: ").append(toIndentedString(implicit)).append("\n");
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
