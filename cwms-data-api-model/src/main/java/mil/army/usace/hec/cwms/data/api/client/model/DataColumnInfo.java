package mil.army.usace.hec.cwms.data.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * DataColumnInfo
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-09-19T12:32:49.455402100-07:00[America/Los_Angeles]")
public class DataColumnInfo {

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("ordinal")
    private Integer ordinal = null;

    @JsonProperty("datatype")
    private String datatype = null;

    public DataColumnInfo name(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataColumnInfo ordinal(Integer ordinal) {
        this.ordinal = ordinal;
        return this;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public DataColumnInfo datatype(String datatype) {
        this.datatype = datatype;
        return this;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DataColumnInfo dataColumnInfo = (DataColumnInfo) o;
        return this.name == null || dataColumnInfo.name == null ? Objects.equals(this.name, dataColumnInfo.name)
                : this.name.equalsIgnoreCase(dataColumnInfo.name)
                && Objects.equals(this.ordinal, dataColumnInfo.ordinal)
                && this.datatype == null || dataColumnInfo.datatype == null
                ? Objects.equals(this.datatype, dataColumnInfo.datatype)
                : this.datatype.equalsIgnoreCase(dataColumnInfo.datatype)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name == null ? 0 : name.toLowerCase(), ordinal,
                datatype == null ? 0 : datatype.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DataColumnInfo {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    ordinal: ").append(toIndentedString(ordinal)).append("\n");
        sb.append("    datatype: ").append(toIndentedString(datatype)).append("\n");
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
