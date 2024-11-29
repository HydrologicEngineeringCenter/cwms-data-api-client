package mil.army.usace.hec.cwms.radar.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * StreamflowMeasurement
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-11-29T09:10:21.459880-08:00[America/Los_Angeles]")
public class StreamflowMeasurement {

    @JsonProperty("gage-height")
    private Double gageHeight = null;

    @JsonProperty("flow")
    private Double flow = null;

    @JsonProperty("quality")
    private String quality = null;

    public StreamflowMeasurement gageHeight(Double gageHeight) {
        this.gageHeight = gageHeight;
        return this;
    }

    public Double getGageHeight() {
        return gageHeight;
    }

    public void setGageHeight(Double gageHeight) {
        this.gageHeight = gageHeight;
    }

    public StreamflowMeasurement flow(Double flow) {
        this.flow = flow;
        return this;
    }

    public Double getFlow() {
        return flow;
    }

    public void setFlow(Double flow) {
        this.flow = flow;
    }

    public StreamflowMeasurement quality(String quality) {
        this.quality = quality;
        return this;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        StreamflowMeasurement streamflowMeasurement = (StreamflowMeasurement) o;
        return Objects.equals(this.gageHeight, streamflowMeasurement.gageHeight)
         && Objects.equals(this.flow, streamflowMeasurement.flow)
         && this.quality == null || streamflowMeasurement.quality == null?Objects.equals(this.quality, streamflowMeasurement.quality):this.quality.equalsIgnoreCase(streamflowMeasurement.quality)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gageHeight, flow, quality==null?0:quality.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StreamflowMeasurement {\n");
        
        sb.append("    gageHeight: ").append(toIndentedString(gageHeight)).append("\n");
        sb.append("    flow: ").append(toIndentedString(flow)).append("\n");
        sb.append("    quality: ").append(toIndentedString(quality)).append("\n");
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
