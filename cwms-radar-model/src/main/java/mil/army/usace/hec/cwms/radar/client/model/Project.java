/*
 * MIT License
 *
 * Copyright (c) 2024 Hydrologic Engineering Center
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Class to hold Reservoir Project information.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-07-01T15:41:30.828168500-07:00[America/Los_Angeles]")
public class Project {

    @JsonProperty("location")
    private Location location = null;

    @JsonProperty("federal-cost")
    private BigDecimal federalCost = null;

    @JsonProperty("non-federal-cost")
    private BigDecimal nonFederalCost = null;

    @JsonProperty("cost-year")
    private Instant costYear = null;

    @JsonProperty("cost-unit")
    private String costUnit = null;

    @JsonProperty("federal-o-and-m-cost")
    private BigDecimal federalOAndMCost = null;

    @JsonProperty("non-federal-o-and-m-cost")
    private BigDecimal nonFederalOAndMCost = null;

    @JsonProperty("authorizing-law")
    private String authorizingLaw = null;

    @JsonProperty("project-owner")
    private String projectOwner = null;

    @JsonProperty("hydropower-desc")
    private String hydropowerDesc = null;

    @JsonProperty("sedimentation-desc")
    private String sedimentationDesc = null;

    @JsonProperty("downstream-urban-desc")
    private String downstreamUrbanDesc = null;

    @JsonProperty("bank-full-capacity-desc")
    private String bankFullCapacityDesc = null;

    @JsonProperty("pump-back-location")
    private Location pumpBackLocation = null;

    @JsonProperty("near-gage-location")
    private Location nearGageLocation = null;

    @JsonProperty("yield-time-frame-start")
    private Instant yieldTimeFrameStart = null;

    @JsonProperty("yield-time-frame-end")
    private Instant yieldTimeFrameEnd = null;

    @JsonProperty("project-remarks")
    private String projectRemarks = null;

    public Project location(Location location) {
        this.location = location;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Project federalCost(BigDecimal federalCost) {
        this.federalCost = federalCost;
        return this;
    }

    public BigDecimal getFederalCost() {
        return federalCost;
    }

    public void setFederalCost(BigDecimal federalCost) {
        this.federalCost = federalCost;
    }

    public Project nonFederalCost(BigDecimal nonFederalCost) {
        this.nonFederalCost = nonFederalCost;
        return this;
    }

    public BigDecimal getNonFederalCost() {
        return nonFederalCost;
    }

    public void setNonFederalCost(BigDecimal nonFederalCost) {
        this.nonFederalCost = nonFederalCost;
    }

    public Project costYear(Instant costYear) {
        this.costYear = costYear;
        return this;
    }

    public Instant getCostYear() {
        return costYear;
    }

    public void setCostYear(Instant costYear) {
        this.costYear = costYear;
    }

    public Project costUnit(String costUnit) {
        this.costUnit = costUnit;
        return this;
    }

    public String getCostUnit() {
        return costUnit;
    }

    public void setCostUnit(String costUnit) {
        this.costUnit = costUnit;
    }

    public Project federalOAndMCost(BigDecimal federalOAndMCost) {
        this.federalOAndMCost = federalOAndMCost;
        return this;
    }

    public BigDecimal getFederalOAndMCost() {
        return federalOAndMCost;
    }

    public void setFederalOAndMCost(BigDecimal federalOAndMCost) {
        this.federalOAndMCost = federalOAndMCost;
    }

    public Project nonFederalOAndMCost(BigDecimal nonFederalOAndMCost) {
        this.nonFederalOAndMCost = nonFederalOAndMCost;
        return this;
    }

    public BigDecimal getNonFederalOAndMCost() {
        return nonFederalOAndMCost;
    }

    public void setNonFederalOAndMCost(BigDecimal nonFederalOAndMCost) {
        this.nonFederalOAndMCost = nonFederalOAndMCost;
    }

    public Project authorizingLaw(String authorizingLaw) {
        this.authorizingLaw = authorizingLaw;
        return this;
    }

    public String getAuthorizingLaw() {
        return authorizingLaw;
    }

    public void setAuthorizingLaw(String authorizingLaw) {
        this.authorizingLaw = authorizingLaw;
    }

    public Project projectOwner(String projectOwner) {
        this.projectOwner = projectOwner;
        return this;
    }

    public String getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(String projectOwner) {
        this.projectOwner = projectOwner;
    }

    public Project hydropowerDesc(String hydropowerDesc) {
        this.hydropowerDesc = hydropowerDesc;
        return this;
    }

    public String getHydropowerDesc() {
        return hydropowerDesc;
    }

    public void setHydropowerDesc(String hydropowerDesc) {
        this.hydropowerDesc = hydropowerDesc;
    }

    public Project sedimentationDesc(String sedimentationDesc) {
        this.sedimentationDesc = sedimentationDesc;
        return this;
    }

    public String getSedimentationDesc() {
        return sedimentationDesc;
    }

    public void setSedimentationDesc(String sedimentationDesc) {
        this.sedimentationDesc = sedimentationDesc;
    }

    public Project downstreamUrbanDesc(String downstreamUrbanDesc) {
        this.downstreamUrbanDesc = downstreamUrbanDesc;
        return this;
    }

    public String getDownstreamUrbanDesc() {
        return downstreamUrbanDesc;
    }

    public void setDownstreamUrbanDesc(String downstreamUrbanDesc) {
        this.downstreamUrbanDesc = downstreamUrbanDesc;
    }

    public Project bankFullCapacityDesc(String bankFullCapacityDesc) {
        this.bankFullCapacityDesc = bankFullCapacityDesc;
        return this;
    }

    public String getBankFullCapacityDesc() {
        return bankFullCapacityDesc;
    }

    public void setBankFullCapacityDesc(String bankFullCapacityDesc) {
        this.bankFullCapacityDesc = bankFullCapacityDesc;
    }

    public Project pumpBackLocation(Location pumpBackLocation) {
        this.pumpBackLocation = pumpBackLocation;
        return this;
    }

    public Location getPumpBackLocation() {
        return pumpBackLocation;
    }

    public void setPumpBackLocation(Location pumpBackLocation) {
        this.pumpBackLocation = pumpBackLocation;
    }

    public Project nearGageLocation(Location nearGageLocation) {
        this.nearGageLocation = nearGageLocation;
        return this;
    }

    public Location getNearGageLocation() {
        return nearGageLocation;
    }

    public void setNearGageLocation(Location nearGageLocation) {
        this.nearGageLocation = nearGageLocation;
    }

    public Project yieldTimeFrameStart(Instant yieldTimeFrameStart) {
        this.yieldTimeFrameStart = yieldTimeFrameStart;
        return this;
    }

    public Instant getYieldTimeFrameStart() {
        return yieldTimeFrameStart;
    }

    public void setYieldTimeFrameStart(Instant yieldTimeFrameStart) {
        this.yieldTimeFrameStart = yieldTimeFrameStart;
    }

    public Project yieldTimeFrameEnd(Instant yieldTimeFrameEnd) {
        this.yieldTimeFrameEnd = yieldTimeFrameEnd;
        return this;
    }

    public Instant getYieldTimeFrameEnd() {
        return yieldTimeFrameEnd;
    }

    public void setYieldTimeFrameEnd(Instant yieldTimeFrameEnd) {
        this.yieldTimeFrameEnd = yieldTimeFrameEnd;
    }

    public Project projectRemarks(String projectRemarks) {
        this.projectRemarks = projectRemarks;
        return this;
    }

    public String getProjectRemarks() {
        return projectRemarks;
    }

    public void setProjectRemarks(String projectRemarks) {
        this.projectRemarks = projectRemarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Project project = (Project) o;
        return Objects.equals(this.location, project.location) && Objects.equals(this.federalCost,
                project.federalCost) && Objects.equals(this.nonFederalCost, project.nonFederalCost) && Objects.equals(
                this.costYear, project.costYear) && this.costUnit == null || project.costUnit == null ? Objects.equals(
                this.costUnit, project.costUnit) : this.costUnit.equalsIgnoreCase(project.costUnit) && Objects.equals(
                this.federalOAndMCost, project.federalOAndMCost) && Objects.equals(this.nonFederalOAndMCost,
                project.nonFederalOAndMCost) && this.authorizingLaw == null || project.authorizingLaw == null ? Objects.equals(
                this.authorizingLaw, project.authorizingLaw) : this.authorizingLaw.equalsIgnoreCase(
                project.authorizingLaw) && this.projectOwner == null || project.projectOwner == null ? Objects.equals(
                this.projectOwner, project.projectOwner) : this.projectOwner.equalsIgnoreCase(
                project.projectOwner) && this.hydropowerDesc == null || project.hydropowerDesc == null ? Objects.equals(
                this.hydropowerDesc, project.hydropowerDesc) : this.hydropowerDesc.equalsIgnoreCase(
                project.hydropowerDesc) && this.sedimentationDesc == null || project.sedimentationDesc == null ? Objects.equals(
                this.sedimentationDesc, project.sedimentationDesc) : this.sedimentationDesc.equalsIgnoreCase(
                project.sedimentationDesc) && this.downstreamUrbanDesc == null || project.downstreamUrbanDesc == null ? Objects.equals(
                this.downstreamUrbanDesc, project.downstreamUrbanDesc) : this.downstreamUrbanDesc.equalsIgnoreCase(
                project.downstreamUrbanDesc) && this.bankFullCapacityDesc == null || project.bankFullCapacityDesc == null ? Objects.equals(
                this.bankFullCapacityDesc, project.bankFullCapacityDesc) : this.bankFullCapacityDesc.equalsIgnoreCase(
                project.bankFullCapacityDesc) && Objects.equals(this.pumpBackLocation,
                project.pumpBackLocation) && Objects.equals(this.nearGageLocation,
                project.nearGageLocation) && Objects.equals(this.yieldTimeFrameStart,
                project.yieldTimeFrameStart) && Objects.equals(this.yieldTimeFrameEnd,
                project.yieldTimeFrameEnd) && this.projectRemarks == null || project.projectRemarks == null ? Objects.equals(
                this.projectRemarks, project.projectRemarks) : this.projectRemarks.equalsIgnoreCase(
                project.projectRemarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, federalCost, nonFederalCost, costYear,
                costUnit == null ? 0 : costUnit.toLowerCase(), federalOAndMCost, nonFederalOAndMCost,
                authorizingLaw == null ? 0 : authorizingLaw.toLowerCase(),
                projectOwner == null ? 0 : projectOwner.toLowerCase(),
                hydropowerDesc == null ? 0 : hydropowerDesc.toLowerCase(),
                sedimentationDesc == null ? 0 : sedimentationDesc.toLowerCase(),
                downstreamUrbanDesc == null ? 0 : downstreamUrbanDesc.toLowerCase(),
                bankFullCapacityDesc == null ? 0 : bankFullCapacityDesc.toLowerCase(), pumpBackLocation,
                nearGageLocation, yieldTimeFrameStart, yieldTimeFrameEnd,
                projectRemarks == null ? 0 : projectRemarks.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Project {\n");

        sb.append("    location: ").append(toIndentedString(location)).append("\n");
        sb.append("    federalCost: ").append(toIndentedString(federalCost)).append("\n");
        sb.append("    nonFederalCost: ").append(toIndentedString(nonFederalCost)).append("\n");
        sb.append("    costYear: ").append(toIndentedString(costYear)).append("\n");
        sb.append("    costUnit: ").append(toIndentedString(costUnit)).append("\n");
        sb.append("    federalOAndMCost: ").append(toIndentedString(federalOAndMCost)).append("\n");
        sb.append("    nonFederalOAndMCost: ").append(toIndentedString(nonFederalOAndMCost)).append("\n");
        sb.append("    authorizingLaw: ").append(toIndentedString(authorizingLaw)).append("\n");
        sb.append("    projectOwner: ").append(toIndentedString(projectOwner)).append("\n");
        sb.append("    hydropowerDesc: ").append(toIndentedString(hydropowerDesc)).append("\n");
        sb.append("    sedimentationDesc: ").append(toIndentedString(sedimentationDesc)).append("\n");
        sb.append("    downstreamUrbanDesc: ").append(toIndentedString(downstreamUrbanDesc)).append("\n");
        sb.append("    bankFullCapacityDesc: ").append(toIndentedString(bankFullCapacityDesc)).append("\n");
        sb.append("    pumpBackLocation: ").append(toIndentedString(pumpBackLocation)).append("\n");
        sb.append("    nearGageLocation: ").append(toIndentedString(nearGageLocation)).append("\n");
        sb.append("    yieldTimeFrameStart: ").append(toIndentedString(yieldTimeFrameStart)).append("\n");
        sb.append("    yieldTimeFrameEnd: ").append(toIndentedString(yieldTimeFrameEnd)).append("\n");
        sb.append("    projectRemarks: ").append(toIndentedString(projectRemarks)).append("\n");
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
