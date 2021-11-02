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
 * Single Office or List of Offices in tab separated format
 */
@ApiModel(description = "Single Office or List of Offices in tab separated format")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class TabV1Office {
    @JsonProperty("Office")
    private String office = null;

    @JsonProperty("longName")
    private String longName = null;

    @JsonProperty("officeType")
    private String officeType = null;

    @JsonProperty("reportsToOffice")
    private String reportsToOffice = null;

    public TabV1Office office(String office) {
        this.office = office;
        return this;
    }

    /**
     * Get office
     *
     * @return office
     **/
    @ApiModelProperty(value = "")

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public TabV1Office longName(String longName) {
        this.longName = longName;
        return this;
    }

    /**
     * Get longName
     *
     * @return longName
     **/
    @ApiModelProperty(value = "")

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public TabV1Office officeType(String officeType) {
        this.officeType = officeType;
        return this;
    }

    /**
     * Get officeType
     *
     * @return officeType
     **/
    @ApiModelProperty(value = "")

    public String getOfficeType() {
        return officeType;
    }

    public void setOfficeType(String officeType) {
        this.officeType = officeType;
    }

    public TabV1Office reportsToOffice(String reportsToOffice) {
        this.reportsToOffice = reportsToOffice;
        return this;
    }

    /**
     * Get reportsToOffice
     *
     * @return reportsToOffice
     **/
    @ApiModelProperty(value = "")

    public String getReportsToOffice() {
        return reportsToOffice;
    }

    public void setReportsToOffice(String reportsToOffice) {
        this.reportsToOffice = reportsToOffice;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TabV1Office tabV1Office = (TabV1Office) o;
        return Objects.equals(this.office, tabV1Office.office) &&
            Objects.equals(this.longName, tabV1Office.longName) &&
            Objects.equals(this.officeType, tabV1Office.officeType) &&
            Objects.equals(this.reportsToOffice, tabV1Office.reportsToOffice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(office, longName, officeType, reportsToOffice);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TabV1Office {\n");

        sb.append("    office: ").append(toIndentedString(office)).append("\n");
        sb.append("    longName: ").append(toIndentedString(longName)).append("\n");
        sb.append("    officeType: ").append(toIndentedString(officeType)).append("\n");
        sb.append("    reportsToOffice: ").append(toIndentedString(reportsToOffice)).append("\n");
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
