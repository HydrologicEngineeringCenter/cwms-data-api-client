package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * ParameterColumnInfo
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-09-19T12:32:49.455402100-07:00[America/Los_Angeles]")
public class ParameterColumnInfo {

    @JsonProperty("parameter")
    private String parameter = null;

    @JsonProperty("ordinal")
    private Integer ordinal = null;

    @JsonProperty("unit")
    private String unit = null;

    public ParameterColumnInfo parameter(String parameter) {
        this.parameter = parameter;
        return this;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public ParameterColumnInfo ordinal(Integer ordinal) {
        this.ordinal = ordinal;
        return this;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public ParameterColumnInfo unit(String unit) {
        this.unit = unit;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParameterColumnInfo parameterColumnInfo = (ParameterColumnInfo) o;
        return this.parameter == null || parameterColumnInfo.parameter == null
                ? Objects.equals(this.parameter, parameterColumnInfo.parameter)
                : this.parameter.equalsIgnoreCase(parameterColumnInfo.parameter)
                && Objects.equals(this.ordinal, parameterColumnInfo.ordinal)
                && this.unit == null || parameterColumnInfo.unit == null
                ? Objects.equals(this.unit, parameterColumnInfo.unit)
                : this.unit.equalsIgnoreCase(parameterColumnInfo.unit)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameter == null ? 0 : parameter.toLowerCase(), ordinal, unit == null ? 0 : unit.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ParameterColumnInfo {\n");

        sb.append("    parameter: ").append(toIndentedString(parameter)).append("\n");
        sb.append("    ordinal: ").append(toIndentedString(ordinal)).append("\n");
        sb.append("    unit: ").append(toIndentedString(unit)).append("\n");
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
