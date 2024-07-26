package mil.army.usace.hec.cwms.radar.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.Valid;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * WaterSupplyAccounting
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-07-26T13:57:20.362288900-07:00[America/Los_Angeles]")
public class WaterSupplyAccounting {

    @JsonProperty("contract-name")
    private String contractName = null;

    @JsonProperty("water-user")
    private WaterUser waterUser = null;

    @JsonProperty("pump-accounting")
    @Valid
    private List<PumpAccounting> pumpAccounting = new ArrayList<>();

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

    public WaterSupplyAccounting pumpAccounting(List<PumpAccounting> pumpAccounting) {
        this.pumpAccounting = pumpAccounting;
        return this;
    }

    public WaterSupplyAccounting addPumpAccountingItem(PumpAccounting pumpAccountingItem) {
        if (this.pumpAccounting == null) {
            this.pumpAccounting = new ArrayList<>();
        }
        this.pumpAccounting.add(pumpAccountingItem);
        return this;
    }

    public List<PumpAccounting> getPumpAccounting() {
        return pumpAccounting;
    }

    public void setPumpAccounting(List<PumpAccounting> pumpAccounting) {
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
                && Objects.equals(this.pumpAccounting, waterSupplyAccounting.pumpAccounting);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractName == null ? 0 : contractName.toLowerCase(), waterUser, pumpAccounting);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WaterSupplyAccounting {\n");

        sb.append("    contractName: ").append(toIndentedString(contractName)).append("\n");
        sb.append("    waterUser: ").append(toIndentedString(waterUser)).append("\n");
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
