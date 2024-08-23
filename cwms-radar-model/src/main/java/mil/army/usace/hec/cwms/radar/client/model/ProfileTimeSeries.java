package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * ProfileTimeSeries
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-08-23T14:33:50.088073-07:00[America/Los_Angeles]")
public class ProfileTimeSeries {

    @JsonProperty("parameter")
    private String parameter = null;

    @JsonProperty("unit")
    private String unit = null;

    @JsonProperty("time-zone")
    private String timeZone = null;

    @JsonProperty("values")
    @Valid
    private List<TimeValuePair> values = new ArrayList<>();

    public ProfileTimeSeries parameter(String parameter) {
        this.parameter = parameter;
        return this;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public ProfileTimeSeries unit(String unit) {
        this.unit = unit;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public ProfileTimeSeries timeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public ProfileTimeSeries values(List<TimeValuePair> values) {
        this.values = values;
        return this;
    }

    public ProfileTimeSeries addValuesItem(TimeValuePair valuesItem) {
        if (this.values == null) {
            this.values = new ArrayList<>();
        }
        this.values.add(valuesItem);
        return this;
    }

    public List<TimeValuePair> getValues() {
        return values;
    }

    public void setValues(List<TimeValuePair> values) {
        this.values = values;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProfileTimeSeries profileTimeSeries = (ProfileTimeSeries) o;
        return this.parameter == null || profileTimeSeries.parameter == null
                ? Objects.equals(this.parameter, profileTimeSeries.parameter)
                : this.parameter.equalsIgnoreCase(profileTimeSeries.parameter)
                && this.unit == null || profileTimeSeries.unit == null
                ? Objects.equals(this.unit, profileTimeSeries.unit)
                : this.unit.equalsIgnoreCase(profileTimeSeries.unit)
                && this.timeZone == null || profileTimeSeries.timeZone == null
                ? Objects.equals(this.timeZone, profileTimeSeries.timeZone)
                : this.timeZone.equalsIgnoreCase(profileTimeSeries.timeZone)
                && Objects.equals(this.values, profileTimeSeries.values)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameter == null ? 0 : parameter.toLowerCase(), unit == null ? 0 : unit.toLowerCase(),
                timeZone == null ? 0 : timeZone.toLowerCase(), values);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ProfileTimeSeries {\n");

        sb.append("    parameter: ").append(toIndentedString(parameter)).append("\n");
        sb.append("    unit: ").append(toIndentedString(unit)).append("\n");
        sb.append("    timeZone: ").append(toIndentedString(timeZone)).append("\n");
        sb.append("    values: ").append(toIndentedString(values)).append("\n");
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
