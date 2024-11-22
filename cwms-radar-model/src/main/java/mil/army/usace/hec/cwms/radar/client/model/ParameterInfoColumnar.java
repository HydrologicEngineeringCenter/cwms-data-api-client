package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Objects;


/**
 * ParameterInfoColumnar
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-08-23T10:53:54.959292200-07:00[America/Los_Angeles]")
@JsonTypeName("columnar-parameter-info")
public class ParameterInfoColumnar extends ParameterInfo {

    @JsonProperty("start-column")
    private Integer startColumn = null;

    @JsonProperty("end-column")
    private Integer endColumn = null;

    public ParameterInfoColumnar startColumn(Integer startColumn) {
        this.startColumn = startColumn;
        return this;
    }

    public Integer getStartColumn() {
        return startColumn;
    }

    public void setStartColumn(Integer startColumn) {
        this.startColumn = startColumn;
    }

    public ParameterInfoColumnar endColumn(Integer endColumn) {
        this.endColumn = endColumn;
        return this;
    }

    public Integer getEndColumn() {
        return endColumn;
    }

    public void setEndColumn(Integer endColumn) {
        this.endColumn = endColumn;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParameterInfoColumnar parameterInfoColumnar = (ParameterInfoColumnar) o;
        return Objects.equals(this.startColumn, parameterInfoColumnar.startColumn)
                && Objects.equals(this.endColumn, parameterInfoColumnar.endColumn)
                &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startColumn, endColumn, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ParameterInfoColumnar {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    startColumn: ").append(toIndentedString(startColumn)).append("\n");
        sb.append("    endColumn: ").append(toIndentedString(endColumn)).append("\n");
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
