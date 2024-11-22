package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.Valid;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * WaterSupplyAccounting
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-11-22T13:03:28.808153100-08:00[America/Los_Angeles]")
public class WaterSupplyAccounting {

    @JsonProperty("contract-name")
    private String contractName = null;

    @JsonProperty("water-user")
    private WaterUser waterUser = null;

    @JsonProperty("pump-locations")
    private PumpLocation pumpLocations = null;

    @JsonProperty("pump-accounting")
    @Valid
    private Map<Instant, List<PumpTransfer>> pumpAccounting = null;

    public WaterSupplyAccounting contractName(String contractName) {
        this.contractName = contractName;
        return this;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public WaterSupplyAccounting waterUser(WaterUser waterUser) {
        this.waterUser = waterUser;
        return this;
    }

    public WaterUser getWaterUser() {
        return waterUser;
    }

    public void setWaterUser(WaterUser waterUser) {
        this.waterUser = waterUser;
    }

    public WaterSupplyAccounting pumpLocations(PumpLocation pumpLocations) {
        this.pumpLocations = pumpLocations;
        return this;
    }

    public PumpLocation getPumpLocations() {
        return pumpLocations;
    }

    public void setPumpLocations(PumpLocation pumpLocations) {
        this.pumpLocations = pumpLocations;
    }

    public WaterSupplyAccounting pumpAccounting(Map<Instant, List<PumpTransfer>> pumpAccounting) {
        this.pumpAccounting = pumpAccounting;
        return this;
    }

    public WaterSupplyAccounting putPumpAccountingItem(Instant key, List<PumpTransfer> pumpAccountingItem) {
        if (this.pumpAccounting == null) {
            this.pumpAccounting = new HashMap<>();
        }
        this.pumpAccounting.put(key, pumpAccountingItem);
        return this;
    }

    @JsonValue
    public Map<Instant, List<PumpTransfer>> getPumpAccounting() {
        return pumpAccounting;
    }

    public void setPumpAccounting(Map<Instant, List<PumpTransfer>> pumpAccounting) {
        this.pumpAccounting = pumpAccounting;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WaterSupplyAccounting waterSupplyAccounting = (WaterSupplyAccounting) o;
        return this.contractName == null || waterSupplyAccounting.contractName == null
                ? Objects.equals(this.contractName, waterSupplyAccounting.contractName)
                : this.contractName.equalsIgnoreCase(waterSupplyAccounting.contractName)
                && Objects.equals(this.waterUser, waterSupplyAccounting.waterUser)
                && Objects.equals(this.pumpLocations, waterSupplyAccounting.pumpLocations)
                && Objects.equals(this.pumpAccounting, waterSupplyAccounting.pumpAccounting)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractName == null
                ? 0 : contractName.toLowerCase(), waterUser, pumpLocations, pumpAccounting);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WaterSupplyAccounting {\n");

        sb.append("    contractName: ").append(toIndentedString(contractName)).append("\n");
        sb.append("    waterUser: ").append(toIndentedString(waterUser)).append("\n");
        sb.append("    pumpLocations: ").append(toIndentedString(pumpLocations)).append("\n");
        sb.append("    pumpAccounting: ").append(toIndentedString(pumpAccounting)).append("\n");
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
