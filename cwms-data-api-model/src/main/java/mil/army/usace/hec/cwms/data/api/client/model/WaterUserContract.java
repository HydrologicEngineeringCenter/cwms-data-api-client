package mil.army.usace.hec.cwms.data.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Objects;


/**
 * WaterUserContract
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-07-17T11:05:51.342334900-07:00[America/Los_Angeles]")
public class WaterUserContract {

    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("water-user")
    private WaterUser waterUser = null;

    @JsonProperty("contract-id")
    private CwmsId contractId = null;

    @JsonProperty("contract-type")
    private LookupType contractType = null;

    @JsonProperty("contract-effective-date")
    private Date contractEffectiveDate = null;

    @JsonProperty("contract-expiration-date")
    private Date contractExpirationDate = null;

    @JsonProperty("contracted-storage")
    private Double contractedStorage = null;

    @JsonProperty("initial-use-allocation")
    private Double initialUseAllocation = null;

    @JsonProperty("future-use-allocation")
    private Double futureUseAllocation = null;

    @JsonProperty("storage-units-id")
    private String storageUnitsId = null;

    @JsonProperty("future-use-percent-activated")
    private Double futureUsePercentActivated = null;

    @JsonProperty("total-alloc-percent-activated")
    private Double totalAllocPercentActivated = null;

    @JsonProperty("pump-out-location")
    private WaterSupplyPump pumpOutLocation = null;

    @JsonProperty("pump-out-below-location")
    private WaterSupplyPump pumpOutBelowLocation = null;

    @JsonProperty("pump-in-location")
    private WaterSupplyPump pumpInLocation = null;

    public WaterUserContract officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public WaterUserContract waterUser(WaterUser waterUser) {
        this.waterUser = waterUser;
        return this;
    }

    public WaterUser getWaterUser() {
        return waterUser;
    }

    public void setWaterUser(WaterUser waterUser) {
        this.waterUser = waterUser;
    }

    public WaterUserContract contractId(CwmsId contractId) {
        this.contractId = contractId;
        return this;
    }

    public CwmsId getContractId() {
        return contractId;
    }

    public void setContractId(CwmsId contractId) {
        this.contractId = contractId;
    }

    public WaterUserContract contractType(LookupType contractType) {
        this.contractType = contractType;
        return this;
    }

    public LookupType getContractType() {
        return contractType;
    }

    public void setContractType(LookupType contractType) {
        this.contractType = contractType;
    }

    public WaterUserContract contractEffectiveDate(Date contractEffectiveDate) {
        this.contractEffectiveDate = contractEffectiveDate;
        return this;
    }

    public Date getContractEffectiveDate() {
        return contractEffectiveDate;
    }

    public void setContractEffectiveDate(Date contractEffectiveDate) {
        this.contractEffectiveDate = contractEffectiveDate;
    }

    public WaterUserContract contractExpirationDate(Date contractExpirationDate) {
        this.contractExpirationDate = contractExpirationDate;
        return this;
    }

    public Date getContractExpirationDate() {
        return contractExpirationDate;
    }

    public void setContractExpirationDate(Date contractExpirationDate) {
        this.contractExpirationDate = contractExpirationDate;
    }

    public WaterUserContract contractedStorage(Double contractedStorage) {
        this.contractedStorage = contractedStorage;
        return this;
    }

    public Double getContractedStorage() {
        return contractedStorage;
    }

    public void setContractedStorage(Double contractedStorage) {
        this.contractedStorage = contractedStorage;
    }

    public WaterUserContract initialUseAllocation(Double initialUseAllocation) {
        this.initialUseAllocation = initialUseAllocation;
        return this;
    }

    public Double getInitialUseAllocation() {
        return initialUseAllocation;
    }

    public void setInitialUseAllocation(Double initialUseAllocation) {
        this.initialUseAllocation = initialUseAllocation;
    }

    public WaterUserContract futureUseAllocation(Double futureUseAllocation) {
        this.futureUseAllocation = futureUseAllocation;
        return this;
    }

    public Double getFutureUseAllocation() {
        return futureUseAllocation;
    }

    public void setFutureUseAllocation(Double futureUseAllocation) {
        this.futureUseAllocation = futureUseAllocation;
    }

    public WaterUserContract storageUnitsId(String storageUnitsId) {
        this.storageUnitsId = storageUnitsId;
        return this;
    }

    public String getStorageUnitsId() {
        return storageUnitsId;
    }

    public void setStorageUnitsId(String storageUnitsId) {
        this.storageUnitsId = storageUnitsId;
    }

    public WaterUserContract futureUsePercentActivated(Double futureUsePercentActivated) {
        this.futureUsePercentActivated = futureUsePercentActivated;
        return this;
    }

    public Double getFutureUsePercentActivated() {
        return futureUsePercentActivated;
    }

    public void setFutureUsePercentActivated(Double futureUsePercentActivated) {
        this.futureUsePercentActivated = futureUsePercentActivated;
    }

    public WaterUserContract totalAllocPercentActivated(Double totalAllocPercentActivated) {
        this.totalAllocPercentActivated = totalAllocPercentActivated;
        return this;
    }

    public Double getTotalAllocPercentActivated() {
        return totalAllocPercentActivated;
    }

    public void setTotalAllocPercentActivated(Double totalAllocPercentActivated) {
        this.totalAllocPercentActivated = totalAllocPercentActivated;
    }

    public WaterUserContract pumpOutLocation(WaterSupplyPump pumpOutLocation) {
        this.pumpOutLocation = pumpOutLocation;
        return this;
    }

    public WaterSupplyPump getPumpOutLocation() {
        return pumpOutLocation;
    }

    public void setPumpOutLocation(WaterSupplyPump pumpOutLocation) {
        this.pumpOutLocation = pumpOutLocation;
    }

    public WaterUserContract pumpOutBelowLocation(WaterSupplyPump pumpOutBelowLocation) {
        this.pumpOutBelowLocation = pumpOutBelowLocation;
        return this;
    }

    public WaterSupplyPump getPumpOutBelowLocation() {
        return pumpOutBelowLocation;
    }

    public void setPumpOutBelowLocation(WaterSupplyPump pumpOutBelowLocation) {
        this.pumpOutBelowLocation = pumpOutBelowLocation;
    }

    public WaterUserContract pumpInLocation(WaterSupplyPump pumpInLocation) {
        this.pumpInLocation = pumpInLocation;
        return this;
    }

    public WaterSupplyPump getPumpInLocation() {
        return pumpInLocation;
    }

    public void setPumpInLocation(WaterSupplyPump pumpInLocation) {
        this.pumpInLocation = pumpInLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        WaterUserContract waterUserContract = (WaterUserContract) o;
        return this.officeId == null || waterUserContract.officeId == null ? Objects.equals(this.officeId,
                waterUserContract.officeId) : this.officeId.equalsIgnoreCase(waterUserContract.officeId)
                && Objects.equals(this.waterUser, waterUserContract.waterUser)
                && Objects.equals(this.contractId, waterUserContract.contractId)
                && Objects.equals(this.contractType, waterUserContract.contractType)
                && Objects.equals(this.contractEffectiveDate, waterUserContract.contractEffectiveDate)
                && Objects.equals(this.contractExpirationDate, waterUserContract.contractExpirationDate)
                && Objects.equals(this.contractedStorage, waterUserContract.contractedStorage)
                && Objects.equals(this.initialUseAllocation, waterUserContract.initialUseAllocation)
                && Objects.equals(this.futureUseAllocation, waterUserContract.futureUseAllocation)
                && this.storageUnitsId == null || waterUserContract.storageUnitsId == null ? Objects.equals(this.storageUnitsId,
                waterUserContract.storageUnitsId) : this.storageUnitsId.equalsIgnoreCase(waterUserContract.storageUnitsId)
                && Objects.equals(this.futureUsePercentActivated, waterUserContract.futureUsePercentActivated)
                && Objects.equals(this.totalAllocPercentActivated, waterUserContract.totalAllocPercentActivated)
                && Objects.equals(this.pumpOutLocation, waterUserContract.pumpOutLocation)
                && Objects.equals(this.pumpOutBelowLocation, waterUserContract.pumpOutBelowLocation)
                && Objects.equals(this.pumpInLocation, waterUserContract.pumpInLocation)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeId == null ? 0 : officeId.toLowerCase(), waterUser, contractId, contractType,
                contractEffectiveDate, contractExpirationDate, contractedStorage, initialUseAllocation,
                futureUseAllocation, storageUnitsId == null ? 0 : storageUnitsId.toLowerCase(), futureUsePercentActivated,
                totalAllocPercentActivated, pumpOutLocation, pumpOutBelowLocation, pumpInLocation);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WaterUserContract {\n");
        
        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    waterUser: ").append(toIndentedString(waterUser)).append("\n");
        sb.append("    contractId: ").append(toIndentedString(contractId)).append("\n");
        sb.append("    waterContract: ").append(toIndentedString(contractType)).append("\n");
        sb.append("    contractEffectiveDate: ").append(toIndentedString(contractEffectiveDate)).append("\n");
        sb.append("    contractExpirationDate: ").append(toIndentedString(contractExpirationDate)).append("\n");
        sb.append("    contractedStorage: ").append(toIndentedString(contractedStorage)).append("\n");
        sb.append("    initialUseAllocation: ").append(toIndentedString(initialUseAllocation)).append("\n");
        sb.append("    futureUseAllocation: ").append(toIndentedString(futureUseAllocation)).append("\n");
        sb.append("    storageUnitsId: ").append(toIndentedString(storageUnitsId)).append("\n");
        sb.append("    futureUsePercentActivated: ").append(toIndentedString(futureUsePercentActivated)).append("\n");
        sb.append("    totalAllocPercentActivated: ").append(toIndentedString(totalAllocPercentActivated)).append("\n");
        sb.append("    pumpOutLocation: ").append(toIndentedString(pumpOutLocation)).append("\n");
        sb.append("    pumpOutBelowLocation: ").append(toIndentedString(pumpOutBelowLocation)).append("\n");
        sb.append("    pumpInLocation: ").append(toIndentedString(pumpInLocation)).append("\n");
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
