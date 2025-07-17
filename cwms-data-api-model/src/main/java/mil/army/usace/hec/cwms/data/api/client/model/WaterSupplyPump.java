package mil.army.usace.hec.cwms.data.api.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;

/**
 * WaterSupplyPump
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-07-17T11:05:51.342334900-07:00[America/Los_Angeles]")
public class WaterSupplyPump {

    @JsonProperty("pump-location")
    private Location pumpLocation = null;

    /**
    * Gets or Sets pumpType
    */
    public enum PumpTypeEnum {
        IN("IN"),

        OUT("OUT"),

        OUT_BELOW("BELOW");

        private final String value;

        PumpTypeEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static PumpTypeEnum fromValue(String text) {
            for (PumpTypeEnum b : PumpTypeEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    @JsonProperty("pump-type")
    private PumpTypeEnum pumpType = null;

    public WaterSupplyPump pumpLocation(Location pumpLocation) {
        this.pumpLocation = pumpLocation;
        return this;
    }

    public Location getPumpLocation() {
        return pumpLocation;
    }

    public void setPumpLocation(Location pumpLocation) {
        this.pumpLocation = pumpLocation;
    }

    public WaterSupplyPump pumpType(PumpTypeEnum pumpType) {
        this.pumpType = pumpType;
        return this;
    }

    public PumpTypeEnum getPumpType() {
        return pumpType;
    }

    public void setPumpType(PumpTypeEnum pumpType) {
        this.pumpType = pumpType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        WaterSupplyPump waterSupplyPump = (WaterSupplyPump) o;
        return Objects.equals(this.pumpLocation, waterSupplyPump.pumpLocation)
            && this.pumpType == null || waterSupplyPump.pumpType == null ? Objects.equals(this.pumpType,
                waterSupplyPump.pumpType) : this.pumpType.equals(waterSupplyPump.pumpType)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pumpLocation, pumpType == null ? 0 : pumpType.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WaterSupplyPump {\n");
        
        sb.append("    pumpLocation: ").append(toIndentedString(pumpLocation)).append("\n");
        sb.append("    pumpType: ").append(toIndentedString(pumpType)).append("\n");
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
