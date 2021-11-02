/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 * A representation of a CWMS office
 */
@ApiModel(description = "A representation of a CWMS office")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class Office {
    @JsonProperty("name")
    private String name = null;

    @JsonProperty("longName")
    private String longName = null;
    @JsonProperty("type")
    private TypeEnum type = null;
    @JsonProperty("reportsTo")
    private String reportsTo = null;

    public Office name(String name) {
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

    public Office longName(String longName) {
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

    public Office type(TypeEnum type) {
        this.type = type;
        return this;
    }

    /**
     * Get type
     *
     * @return type
     **/
    @ApiModelProperty(value = "")

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public Office reportsTo(String reportsTo) {
        this.reportsTo = reportsTo;
        return this;
    }

    /**
     * Reference to another office, like a division, that this office reports to.
     *
     * @return reportsTo
     **/
    @ApiModelProperty(value = "Reference to another office, like a division, that this office reports to.")

    public String getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(String reportsTo) {
        this.reportsTo = reportsTo;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Office office = (Office) o;
        return Objects.equals(this.name, office.name) &&
            Objects.equals(this.longName, office.longName) &&
            Objects.equals(this.type, office.type) &&
            Objects.equals(this.reportsTo, office.reportsTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, longName, type, reportsTo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Office {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    longName: ").append(toIndentedString(longName)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    reportsTo: ").append(toIndentedString(reportsTo)).append("\n");
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

    /**
     * Gets or Sets type
     */
    public enum TypeEnum {
        UNKNOWN("unknown"),

        CORPS_HEADQUARTERS("corps headquarters"),

        DIVISION_HEADQUARTERS("division headquarters"),

        DIVISION_REGIONAL("division regional"),

        DISTRICT("district"),

        FILED_OPERATING_ACTIVITY("filed operating activity");

        private String value;

        TypeEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static TypeEnum fromValue(String text) {
            for (TypeEnum b : TypeEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }
    }
}
