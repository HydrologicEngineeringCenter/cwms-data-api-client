package mil.army.usace.hec.cwms.data.api.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * TimeSeriesProfileParserColumnar
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-11-22T14:32:40.588746100-08:00[America/Los_Angeles]")
public class TimeSeriesProfileParserColumnar extends TimeSeriesProfileParser {

    @JsonProperty("time-start-column")
    private Integer timeStartColumn = null;

    @JsonProperty("time-end-column")
    private Integer timeEndColumn = null;

    public TimeSeriesProfileParserColumnar timeStartColumn(Integer timeStartColumn) {
        this.timeStartColumn = timeStartColumn;
        return this;
    }

    public Integer getTimeStartColumn() {
        return timeStartColumn;
    }

    public void setTimeStartColumn(Integer timeStartColumn) {
        this.timeStartColumn = timeStartColumn;
    }

    public TimeSeriesProfileParserColumnar timeEndColumn(Integer timeEndColumn) {
        this.timeEndColumn = timeEndColumn;
        return this;
    }

    public Integer getTimeEndColumn() {
        return timeEndColumn;
    }

    public void setTimeEndColumn(Integer timeEndColumn) {
        this.timeEndColumn = timeEndColumn;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSeriesProfileParserColumnar timeSeriesProfileParserColumnar = (TimeSeriesProfileParserColumnar) o;
        return Objects.equals(this.timeStartColumn, timeSeriesProfileParserColumnar.timeStartColumn)
                && Objects.equals(this.timeEndColumn, timeSeriesProfileParserColumnar.timeEndColumn)
                &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeStartColumn, timeEndColumn, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesProfileParserColumnar {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    timeStartColumn: ").append(toIndentedString(timeStartColumn)).append("\n");
        sb.append("    timeEndColumn: ").append(toIndentedString(timeEndColumn)).append("\n");
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
