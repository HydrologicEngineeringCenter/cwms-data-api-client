package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;


/**
 * WaterUser
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-07-17T11:05:51.342334900-07:00[America/Los_Angeles]")
public class WaterUser {

    @JsonProperty("entity-name")
    private String entityName = null;

    @JsonProperty("parent-location-ref")
    private CwmsId parentLocationRef = null;

    @JsonProperty("water-right")
    private String waterRight = null;

    public WaterUser entityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public WaterUser parentLocationRef(CwmsId parentLocationRef) {
        this.parentLocationRef = parentLocationRef;
        return this;
    }

    public CwmsId getParentLocationRef() {
        return parentLocationRef;
    }

    public void setParentLocationRef(CwmsId parentLocationRef) {
        this.parentLocationRef = parentLocationRef;
    }

    public WaterUser waterRight(String waterRight) {
        this.waterRight = waterRight;
        return this;
    }

    public String getWaterRight() {
        return waterRight;
    }

    public void setWaterRight(String waterRight) {
        this.waterRight = waterRight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        WaterUser waterUser = (WaterUser) o;
        return this.entityName == null || waterUser.entityName == null ? Objects.equals(this.entityName,
                waterUser.entityName) : this.entityName.equalsIgnoreCase(waterUser.entityName)
                && Objects.equals(this.parentLocationRef, waterUser.parentLocationRef)
                && this.waterRight == null || waterUser.waterRight == null ? Objects.equals(this.waterRight,
                waterUser.waterRight) : this.waterRight.equalsIgnoreCase(waterUser.waterRight)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityName == null ? 0 : entityName.toLowerCase(), parentLocationRef,
                waterRight == null ? 0 : waterRight.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WaterUser {\n");
        
        sb.append("    entityName: ").append(toIndentedString(entityName)).append("\n");
        sb.append("    parentLocationRef: ").append(toIndentedString(parentLocationRef)).append("\n");
        sb.append("    waterRight: ").append(toIndentedString(waterRight)).append("\n");
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
