/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;

/**
 * OfficesFMT
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class OfficesFMT {
    @JsonProperty("offices")
    @Valid
    private List<Office> offices = null;

    public OfficesFMT offices(List<Office> offices) {
        this.offices = offices;
        return this;
    }

    public OfficesFMT addOfficesItem(Office officesItem) {
        if (this.offices == null) {
            this.offices = new ArrayList<Office>();
        }
        this.offices.add(officesItem);
        return this;
    }

    /**
     * Get offices
     *
     * @return offices
     **/
    @ApiModelProperty(value = "")
    @Valid
    public List<Office> getOffices() {
        return offices;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OfficesFMT officesFMT = (OfficesFMT) o;
        return Objects.equals(this.offices, officesFMT.offices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offices);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OfficesFMT {\n");

        sb.append("    offices: ").append(toIndentedString(offices)).append("\n");
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
