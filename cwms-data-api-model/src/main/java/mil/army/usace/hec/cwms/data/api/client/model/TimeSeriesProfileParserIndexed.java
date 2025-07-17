package mil.army.usace.hec.cwms.data.api.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * TimeSeriesProfileParserIndexed
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-11-22T14:32:40.588746100-08:00[America/Los_Angeles]")
public class TimeSeriesProfileParserIndexed extends TimeSeriesProfileParser {

    @JsonProperty("field-delimiter")
    private String fieldDelimiter = null;

    @JsonProperty("time-field")
    private Long timeField = null;

    public TimeSeriesProfileParserIndexed fieldDelimiter(String fieldDelimiter) {
        this.fieldDelimiter = fieldDelimiter;
        return this;
    }

    public String getFieldDelimiter() {
        return fieldDelimiter;
    }

    public void setFieldDelimiter(String fieldDelimiter) {
        this.fieldDelimiter = fieldDelimiter;
    }

    public TimeSeriesProfileParserIndexed timeField(Long timeField) {
        this.timeField = timeField;
        return this;
    }

    public Long getTimeField() {
        return timeField;
    }

    public void setTimeField(Long timeField) {
        this.timeField = timeField;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSeriesProfileParserIndexed timeSeriesProfileParserIndexed = (TimeSeriesProfileParserIndexed) o;
        return this.fieldDelimiter == null || timeSeriesProfileParserIndexed.fieldDelimiter == null
                ? Objects.equals(this.fieldDelimiter, timeSeriesProfileParserIndexed.fieldDelimiter)
                : this.fieldDelimiter.equalsIgnoreCase(timeSeriesProfileParserIndexed.fieldDelimiter)
                && Objects.equals(this.timeField, timeSeriesProfileParserIndexed.timeField)
                &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldDelimiter == null ? 0 : fieldDelimiter.toLowerCase(),
                timeField, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesProfileParserIndexed {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    fieldDelimiter: ").append(toIndentedString(fieldDelimiter)).append("\n");
        sb.append("    timeField: ").append(toIndentedString(timeField)).append("\n");
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
