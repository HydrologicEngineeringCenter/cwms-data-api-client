package mil.army.usace.hec.cwms.data.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * PoolNameType
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-05-03T12:34:59.760765100-07:00[America/Los_Angeles]")
public class PoolNameType {

    @JsonProperty("pool-name")
    private String poolName = null;

    @JsonProperty("office-id")
    private String officeId = null;

    public PoolNameType poolName(String poolName) {
        this.poolName = poolName;
        return this;
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public PoolNameType officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        PoolNameType poolNameType = (PoolNameType) o;
        return this.poolName == null || poolNameType.poolName == null?Objects.equals(this.poolName, poolNameType.poolName):this.poolName.equalsIgnoreCase(poolNameType.poolName)
         && this.officeId == null || poolNameType.officeId == null?Objects.equals(this.officeId, poolNameType.officeId):this.officeId.equalsIgnoreCase(poolNameType.officeId)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(poolName==null?0:poolName.toLowerCase(), officeId==null?0:officeId.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PoolNameType {\n");
        
        sb.append("    pool-name: ").append(toIndentedString(poolName)).append("\n");
        sb.append("    office-id: ").append(toIndentedString(officeId)).append("\n");
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
