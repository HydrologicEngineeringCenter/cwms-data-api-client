package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * CwmsId
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-06-27T10:32:01.636615300-07:00[America/Los_Angeles]")
public class CwmsId {
    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("name")
    private String name = null;

    public CwmsId officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    /**
     * Get officeId
     *
     * @return officeId
     **/

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public CwmsId name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     **/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CwmsId cwmsId = (CwmsId) o;
        return this.officeId == null || cwmsId.officeId == null
                ? Objects.equals(this.officeId, cwmsId.officeId) : this.officeId.equalsIgnoreCase(cwmsId.officeId)
                && this.name == null || cwmsId.name == null
                ? Objects.equals(this.name, cwmsId.name) : this.name.equalsIgnoreCase(cwmsId.name)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeId == null ? 0 : officeId.toLowerCase(), name == null ? 0 : name.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CwmsId {\n");

        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
