package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Objects;


/**
 * ParameterInfo
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-08-23T10:53:54.959292200-07:00[America/Los_Angeles]")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = ParameterInfoColumnar.class, name = "ParameterInfoColumnar"),
    @JsonSubTypes.Type(value = ParameterInfoIndexed.class, name = "ParameterInfoIndexed"),
})
public class ParameterInfo {

    @JsonProperty("parameter")
    private String parameter = null;

    @JsonProperty("unit")
    private String unit = null;

    @JsonProperty("parameterInfoString")
    private String parameterInfoString = null;

    @JsonProperty("type")
    private String type = null;

    public ParameterInfo parameter(String parameter) {
        this.parameter = parameter;
        return this;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public ParameterInfo unit(String unit) {
        this.unit = unit;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public ParameterInfo parameterInfoString(String parameterInfoString) {
        this.parameterInfoString = parameterInfoString;
        return this;
    }

    public String getParameterInfoString() {
        return parameterInfoString;
    }

    public void setParameterInfoString(String parameterInfoString) {
        this.parameterInfoString = parameterInfoString;
    }

    public ParameterInfo type(String type) {
        this.type = type;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParameterInfo parameterInfo = (ParameterInfo) o;
        return this.parameter == null || parameterInfo.parameter == null
                ? Objects.equals(this.parameter, parameterInfo.parameter)
                : this.parameter.equalsIgnoreCase(parameterInfo.parameter)
                && this.unit == null || parameterInfo.unit == null
                ? Objects.equals(this.unit, parameterInfo.unit)
                : this.unit.equalsIgnoreCase(parameterInfo.unit)
                && this.parameterInfoString == null || parameterInfo.parameterInfoString == null
                ? Objects.equals(this.parameterInfoString, parameterInfo.parameterInfoString)
                : this.parameterInfoString.equalsIgnoreCase(parameterInfo.parameterInfoString)
                && this.type == null || parameterInfo.type == null
                ? Objects.equals(this.type, parameterInfo.type)
                : this.type.equalsIgnoreCase(parameterInfo.type)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameter == null ? 0 : parameter.toLowerCase(),
                unit == null ? 0 : unit.toLowerCase(),
                parameterInfoString == null ? 0 : parameterInfoString.toLowerCase(),
                type == null ? 0 : type.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ParameterInfo {\n");

        sb.append("    parameter: ").append(toIndentedString(parameter)).append("\n");
        sb.append("    unit: ").append(toIndentedString(unit)).append("\n");
        sb.append("    parameterInfoString: ").append(toIndentedString(parameterInfoString)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
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
