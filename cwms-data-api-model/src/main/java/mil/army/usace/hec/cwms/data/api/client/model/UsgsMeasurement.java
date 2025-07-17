package mil.army.usace.hec.cwms.data.api.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * UsgsMeasurement
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-11-29T09:10:21.459880-08:00[America/Los_Angeles]")
public class UsgsMeasurement {

    @JsonProperty("remarks")
    private String remarks = null;

    @JsonProperty("current-rating")
    private String currentRating = null;

    @JsonProperty("control-condition")
    private String controlCondition = null;

    @JsonProperty("shift-used")
    private Double shiftUsed = null;

    @JsonProperty("percent-difference")
    private Double percentDifference = null;

    @JsonProperty("flow-adjustment")
    private String flowAdjustment = null;

    @JsonProperty("delta-height")
    private Double deltaHeight = null;

    @JsonProperty("delta-time")
    private Double deltaTime = null;

    @JsonProperty("air-temp")
    private Double airTemp = null;

    @JsonProperty("water-temp")
    private Double waterTemp = null;

    public UsgsMeasurement remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public UsgsMeasurement currentRating(String currentRating) {
        this.currentRating = currentRating;
        return this;
    }

    public String getCurrentRating() {
        return currentRating;
    }

    public void setCurrentRating(String currentRating) {
        this.currentRating = currentRating;
    }

    public UsgsMeasurement controlCondition(String controlCondition) {
        this.controlCondition = controlCondition;
        return this;
    }

    public String getControlCondition() {
        return controlCondition;
    }

    public void setControlCondition(String controlCondition) {
        this.controlCondition = controlCondition;
    }

    public UsgsMeasurement shiftUsed(Double shiftUsed) {
        this.shiftUsed = shiftUsed;
        return this;
    }

    public Double getShiftUsed() {
        return shiftUsed;
    }

    public void setShiftUsed(Double shiftUsed) {
        this.shiftUsed = shiftUsed;
    }

    public UsgsMeasurement percentDifference(Double percentDifference) {
        this.percentDifference = percentDifference;
        return this;
    }

    public Double getPercentDifference() {
        return percentDifference;
    }

    public void setPercentDifference(Double percentDifference) {
        this.percentDifference = percentDifference;
    }

    public UsgsMeasurement flowAdjustment(String flowAdjustment) {
        this.flowAdjustment = flowAdjustment;
        return this;
    }

    public String getFlowAdjustment() {
        return flowAdjustment;
    }

    public void setFlowAdjustment(String flowAdjustment) {
        this.flowAdjustment = flowAdjustment;
    }

    public UsgsMeasurement deltaHeight(Double deltaHeight) {
        this.deltaHeight = deltaHeight;
        return this;
    }

    public Double getDeltaHeight() {
        return deltaHeight;
    }

    public void setDeltaHeight(Double deltaHeight) {
        this.deltaHeight = deltaHeight;
    }

    public UsgsMeasurement deltaTime(Double deltaTime) {
        this.deltaTime = deltaTime;
        return this;
    }

    public Double getDeltaTime() {
        return deltaTime;
    }

    public void setDeltaTime(Double deltaTime) {
        this.deltaTime = deltaTime;
    }

    public UsgsMeasurement airTemp(Double airTemp) {
        this.airTemp = airTemp;
        return this;
    }

    public Double getAirTemp() {
        return airTemp;
    }

    public void setAirTemp(Double airTemp) {
        this.airTemp = airTemp;
    }

    public UsgsMeasurement waterTemp(Double waterTemp) {
        this.waterTemp = waterTemp;
        return this;
    }

    public Double getWaterTemp() {
        return waterTemp;
    }

    public void setWaterTemp(Double waterTemp) {
        this.waterTemp = waterTemp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        UsgsMeasurement usgsMeasurement = (UsgsMeasurement) o;
        return this.remarks == null || usgsMeasurement.remarks == null?Objects.equals(this.remarks, usgsMeasurement.remarks):this.remarks.equalsIgnoreCase(usgsMeasurement.remarks)
         && this.currentRating == null || usgsMeasurement.currentRating == null?Objects.equals(this.currentRating, usgsMeasurement.currentRating):this.currentRating.equalsIgnoreCase(usgsMeasurement.currentRating)
         && this.controlCondition == null || usgsMeasurement.controlCondition == null?Objects.equals(this.controlCondition, usgsMeasurement.controlCondition):this.controlCondition.equalsIgnoreCase(usgsMeasurement.controlCondition)
         && Objects.equals(this.shiftUsed, usgsMeasurement.shiftUsed)
         && Objects.equals(this.percentDifference, usgsMeasurement.percentDifference)
         && this.flowAdjustment == null || usgsMeasurement.flowAdjustment == null?Objects.equals(this.flowAdjustment, usgsMeasurement.flowAdjustment):this.flowAdjustment.equalsIgnoreCase(usgsMeasurement.flowAdjustment)
         && Objects.equals(this.deltaHeight, usgsMeasurement.deltaHeight)
         && Objects.equals(this.deltaTime, usgsMeasurement.deltaTime)
         && Objects.equals(this.airTemp, usgsMeasurement.airTemp)
         && Objects.equals(this.waterTemp, usgsMeasurement.waterTemp)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(remarks==null?0:remarks.toLowerCase(), currentRating==null?0:currentRating.toLowerCase(), controlCondition==null?0:controlCondition.toLowerCase(), shiftUsed, percentDifference, flowAdjustment==null?0:flowAdjustment.toLowerCase(), deltaHeight, deltaTime, airTemp, waterTemp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UsgsMeasurement {\n");
        
        sb.append("    remarks: ").append(toIndentedString(remarks)).append("\n");
        sb.append("    currentRating: ").append(toIndentedString(currentRating)).append("\n");
        sb.append("    controlCondition: ").append(toIndentedString(controlCondition)).append("\n");
        sb.append("    shiftUsed: ").append(toIndentedString(shiftUsed)).append("\n");
        sb.append("    percentDifference: ").append(toIndentedString(percentDifference)).append("\n");
        sb.append("    flowAdjustment: ").append(toIndentedString(flowAdjustment)).append("\n");
        sb.append("    deltaHeight: ").append(toIndentedString(deltaHeight)).append("\n");
        sb.append("    deltaTime: ").append(toIndentedString(deltaTime)).append("\n");
        sb.append("    airTemp: ").append(toIndentedString(airTemp)).append("\n");
        sb.append("    waterTemp: ").append(toIndentedString(waterTemp)).append("\n");
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
