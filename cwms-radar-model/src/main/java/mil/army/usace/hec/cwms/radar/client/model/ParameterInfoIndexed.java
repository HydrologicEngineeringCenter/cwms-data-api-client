package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;


/**
 * ParameterInfoIndexed
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-08-23T10:53:54.959292200-07:00[America/Los_Angeles]")
public class ParameterInfoIndexed extends ParameterInfo {

    @JsonProperty("index")
    private Integer index = null;

    public ParameterInfoIndexed index(Integer index) {
        this.index = index;
        return this;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParameterInfoIndexed parameterInfoIndexed = (ParameterInfoIndexed) o;
        return Objects.equals(this.index, parameterInfoIndexed.index)
                &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ParameterInfoIndexed {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    index: ").append(toIndentedString(index)).append("\n");
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
