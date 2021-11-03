/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.radar.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 * TimeseriesCatalogEntry
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:49:54.974-07:00[America/Los_Angeles]")
public class TimeseriesCatalogEntry {
    @JsonProperty("office")
    private String office = null;

    @JsonProperty("tsName")
    private String tsName = null;

    @JsonProperty("longName")
    private String longName = null;

    @JsonProperty("units")
    private String units = null;

    @JsonProperty("fullName")
    private String fullName = null;

    public TimeseriesCatalogEntry office(String office) {
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

    public TimeseriesCatalogEntry tsName(String tsName) {
        this.tsName = tsName;
        return this;
    }

    /**
     * Get tsName
     *
     * @return tsName
     **/
    @ApiModelProperty(value = "")

    public String getTsName() {
        return tsName;
    }

    public void setTsName(String tsName) {
        this.tsName = tsName;
    }

    public TimeseriesCatalogEntry longName(String longName) {
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

    public TimeseriesCatalogEntry units(String units) {
        this.units = units;
        return this;
    }

    /**
     * Get units
     *
     * @return units
     **/
    @ApiModelProperty(value = "")

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public TimeseriesCatalogEntry fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    /**
     * Get fullName
     *
     * @return fullName
     **/
    @ApiModelProperty(value = "")

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeseriesCatalogEntry timeseriesCatalogEntry = (TimeseriesCatalogEntry) o;
        return Objects.equals(this.office, timeseriesCatalogEntry.office) &&
            Objects.equals(this.tsName, timeseriesCatalogEntry.tsName) &&
            Objects.equals(this.longName, timeseriesCatalogEntry.longName) &&
            Objects.equals(this.units, timeseriesCatalogEntry.units) &&
            Objects.equals(this.fullName, timeseriesCatalogEntry.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(office, tsName, longName, units, fullName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeseriesCatalogEntry {\n");

        sb.append("    office: ").append(toIndentedString(office)).append("\n");
        sb.append("    tsName: ").append(toIndentedString(tsName)).append("\n");
        sb.append("    longName: ").append(toIndentedString(longName)).append("\n");
        sb.append("    units: ").append(toIndentedString(units)).append("\n");
        sb.append("    fullName: ").append(toIndentedString(fullName)).append("\n");
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
