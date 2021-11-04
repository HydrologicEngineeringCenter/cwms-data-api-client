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
 * CatalogEntry
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class TimeSeriesCatalogEntry {
    @JsonProperty("office")
    private String office = null;

    @JsonProperty("ts-name")
    private String tsName = null;

    @JsonProperty("units")
    private String units = null;

    @JsonProperty("full-name")
    private String fullName = null;

    /**
     * Get office
     *
     * @return office
     **/
    @ApiModelProperty(value = "")

    public String getOffice() {
        return office;
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

    /**
     * Get units
     *
     * @return units
     **/
    @ApiModelProperty(value = "")

    public String getUnits() {
        return units;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSeriesCatalogEntry catalogEntry = (TimeSeriesCatalogEntry) o;
        return Objects.equals(this.office, catalogEntry.office) &&
            Objects.equals(this.tsName, catalogEntry.tsName) &&
            Objects.equals(this.units, catalogEntry.units) &&
            Objects.equals(this.fullName, catalogEntry.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(office, tsName, units, fullName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CatalogEntry {\n");

        sb.append("    office: ").append(toIndentedString(office)).append("\n");
        sb.append("    tsName: ").append(toIndentedString(tsName)).append("\n");
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