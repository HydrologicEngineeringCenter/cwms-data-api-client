package mil.army.usace.hec.cwms.radar.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * SupplementalStreamflowMeasurement
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-11-29T09:10:21.459880-08:00[America/Los_Angeles]")
public class SupplementalStreamflowMeasurement {

    @JsonProperty("channel-flow")
    private Double channelFlow = null;

    @JsonProperty("overbank-flow")
    private Double overbankFlow = null;

    @JsonProperty("overbank-max-depth")
    private Double overbankMaxDepth = null;

    @JsonProperty("channel-max-depth")
    private Double channelMaxDepth = null;

    @JsonProperty("avg-velocity")
    private Double avgVelocity = null;

    @JsonProperty("surface-velocity")
    private Double surfaceVelocity = null;

    @JsonProperty("max-velocity")
    private Double maxVelocity = null;

    @JsonProperty("effective-flow-area")
    private Double effectiveFlowArea = null;

    @JsonProperty("cross-sectional-area")
    private Double crossSectionalArea = null;

    @JsonProperty("mean-gage")
    private Double meanGage = null;

    @JsonProperty("top-width")
    private Double topWidth = null;

    @JsonProperty("main-channel-area")
    private Double mainChannelArea = null;

    @JsonProperty("overbank-area")
    private Double overbankArea = null;

    public SupplementalStreamflowMeasurement channelFlow(Double channelFlow) {
        this.channelFlow = channelFlow;
        return this;
    }

    public Double getChannelFlow() {
        return channelFlow;
    }

    public void setChannelFlow(Double channelFlow) {
        this.channelFlow = channelFlow;
    }

    public SupplementalStreamflowMeasurement overbankFlow(Double overbankFlow) {
        this.overbankFlow = overbankFlow;
        return this;
    }

    public Double getOverbankFlow() {
        return overbankFlow;
    }

    public void setOverbankFlow(Double overbankFlow) {
        this.overbankFlow = overbankFlow;
    }

    public SupplementalStreamflowMeasurement overbankMaxDepth(Double overbankMaxDepth) {
        this.overbankMaxDepth = overbankMaxDepth;
        return this;
    }

    public Double getOverbankMaxDepth() {
        return overbankMaxDepth;
    }

    public void setOverbankMaxDepth(Double overbankMaxDepth) {
        this.overbankMaxDepth = overbankMaxDepth;
    }

    public SupplementalStreamflowMeasurement channelMaxDepth(Double channelMaxDepth) {
        this.channelMaxDepth = channelMaxDepth;
        return this;
    }

    public Double getChannelMaxDepth() {
        return channelMaxDepth;
    }

    public void setChannelMaxDepth(Double channelMaxDepth) {
        this.channelMaxDepth = channelMaxDepth;
    }

    public SupplementalStreamflowMeasurement avgVelocity(Double avgVelocity) {
        this.avgVelocity = avgVelocity;
        return this;
    }

    public Double getAvgVelocity() {
        return avgVelocity;
    }

    public void setAvgVelocity(Double avgVelocity) {
        this.avgVelocity = avgVelocity;
    }

    public SupplementalStreamflowMeasurement surfaceVelocity(Double surfaceVelocity) {
        this.surfaceVelocity = surfaceVelocity;
        return this;
    }

    public Double getSurfaceVelocity() {
        return surfaceVelocity;
    }

    public void setSurfaceVelocity(Double surfaceVelocity) {
        this.surfaceVelocity = surfaceVelocity;
    }

    public SupplementalStreamflowMeasurement maxVelocity(Double maxVelocity) {
        this.maxVelocity = maxVelocity;
        return this;
    }

    public Double getMaxVelocity() {
        return maxVelocity;
    }

    public void setMaxVelocity(Double maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    public SupplementalStreamflowMeasurement effectiveFlowArea(Double effectiveFlowArea) {
        this.effectiveFlowArea = effectiveFlowArea;
        return this;
    }

    public Double getEffectiveFlowArea() {
        return effectiveFlowArea;
    }

    public void setEffectiveFlowArea(Double effectiveFlowArea) {
        this.effectiveFlowArea = effectiveFlowArea;
    }

    public SupplementalStreamflowMeasurement crossSectionalArea(Double crossSectionalArea) {
        this.crossSectionalArea = crossSectionalArea;
        return this;
    }

    public Double getCrossSectionalArea() {
        return crossSectionalArea;
    }

    public void setCrossSectionalArea(Double crossSectionalArea) {
        this.crossSectionalArea = crossSectionalArea;
    }

    public SupplementalStreamflowMeasurement meanGage(Double meanGage) {
        this.meanGage = meanGage;
        return this;
    }

    public Double getMeanGage() {
        return meanGage;
    }

    public void setMeanGage(Double meanGage) {
        this.meanGage = meanGage;
    }

    public SupplementalStreamflowMeasurement topWidth(Double topWidth) {
        this.topWidth = topWidth;
        return this;
    }

    public Double getTopWidth() {
        return topWidth;
    }

    public void setTopWidth(Double topWidth) {
        this.topWidth = topWidth;
    }

    public SupplementalStreamflowMeasurement mainChannelArea(Double mainChannelArea) {
        this.mainChannelArea = mainChannelArea;
        return this;
    }

    public Double getMainChannelArea() {
        return mainChannelArea;
    }

    public void setMainChannelArea(Double mainChannelArea) {
        this.mainChannelArea = mainChannelArea;
    }

    public SupplementalStreamflowMeasurement overbankArea(Double overbankArea) {
        this.overbankArea = overbankArea;
        return this;
    }

    public Double getOverbankArea() {
        return overbankArea;
    }

    public void setOverbankArea(Double overbankArea) {
        this.overbankArea = overbankArea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        SupplementalStreamflowMeasurement supplementalStreamflowMeasurement = (SupplementalStreamflowMeasurement) o;
        return Objects.equals(this.channelFlow, supplementalStreamflowMeasurement.channelFlow)
         && Objects.equals(this.overbankFlow, supplementalStreamflowMeasurement.overbankFlow)
         && Objects.equals(this.overbankMaxDepth, supplementalStreamflowMeasurement.overbankMaxDepth)
         && Objects.equals(this.channelMaxDepth, supplementalStreamflowMeasurement.channelMaxDepth)
         && Objects.equals(this.avgVelocity, supplementalStreamflowMeasurement.avgVelocity)
         && Objects.equals(this.surfaceVelocity, supplementalStreamflowMeasurement.surfaceVelocity)
         && Objects.equals(this.maxVelocity, supplementalStreamflowMeasurement.maxVelocity)
         && Objects.equals(this.effectiveFlowArea, supplementalStreamflowMeasurement.effectiveFlowArea)
         && Objects.equals(this.crossSectionalArea, supplementalStreamflowMeasurement.crossSectionalArea)
         && Objects.equals(this.meanGage, supplementalStreamflowMeasurement.meanGage)
         && Objects.equals(this.topWidth, supplementalStreamflowMeasurement.topWidth)
         && Objects.equals(this.mainChannelArea, supplementalStreamflowMeasurement.mainChannelArea)
         && Objects.equals(this.overbankArea, supplementalStreamflowMeasurement.overbankArea)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(channelFlow, overbankFlow, overbankMaxDepth, channelMaxDepth, avgVelocity, surfaceVelocity, maxVelocity, effectiveFlowArea, crossSectionalArea, meanGage, topWidth, mainChannelArea, overbankArea);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SupplementalStreamflowMeasurement {\n");
        
        sb.append("    channelFlow: ").append(toIndentedString(channelFlow)).append("\n");
        sb.append("    overbankFlow: ").append(toIndentedString(overbankFlow)).append("\n");
        sb.append("    overbankMaxDepth: ").append(toIndentedString(overbankMaxDepth)).append("\n");
        sb.append("    channelMaxDepth: ").append(toIndentedString(channelMaxDepth)).append("\n");
        sb.append("    avgVelocity: ").append(toIndentedString(avgVelocity)).append("\n");
        sb.append("    surfaceVelocity: ").append(toIndentedString(surfaceVelocity)).append("\n");
        sb.append("    maxVelocity: ").append(toIndentedString(maxVelocity)).append("\n");
        sb.append("    effectiveFlowArea: ").append(toIndentedString(effectiveFlowArea)).append("\n");
        sb.append("    crossSectionalArea: ").append(toIndentedString(crossSectionalArea)).append("\n");
        sb.append("    meanGage: ").append(toIndentedString(meanGage)).append("\n");
        sb.append("    topWidth: ").append(toIndentedString(topWidth)).append("\n");
        sb.append("    mainChannelArea: ").append(toIndentedString(mainChannelArea)).append("\n");
        sb.append("    overbankArea: ").append(toIndentedString(overbankArea)).append("\n");
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
