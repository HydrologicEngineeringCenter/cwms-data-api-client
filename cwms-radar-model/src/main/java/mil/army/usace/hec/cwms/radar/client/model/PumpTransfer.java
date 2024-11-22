package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;


/**
 * PumpTransfer
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-11-22T13:03:28.808153100-08:00[America/Los_Angeles]")
public class PumpTransfer {

    /**
     * Gets or Sets pumpType
     */
    public enum PumpTypeEnum {
        IN("IN"),

        OUT("OUT"),

        BELOW("BELOW");

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

    @JsonProperty("transfer-type-display")
    private String transferTypeDisplay = null;

    @JsonProperty("flow")
    private Double flow = null;

    @JsonProperty("comment")
    private String comment = null;

    public PumpTransfer pumpType(PumpTypeEnum pumpType) {
        this.pumpType = pumpType;
        return this;
    }

    public PumpTypeEnum getPumpType() {
        return pumpType;
    }

    public void setPumpType(PumpTypeEnum pumpType) {
        this.pumpType = pumpType;
    }

    public PumpTransfer transferTypeDisplay(String transferTypeDisplay) {
        this.transferTypeDisplay = transferTypeDisplay;
        return this;
    }

    public String getTransferTypeDisplay() {
        return transferTypeDisplay;
    }

    public void setTransferTypeDisplay(String transferTypeDisplay) {
        this.transferTypeDisplay = transferTypeDisplay;
    }

    public PumpTransfer flow(Double flow) {
        this.flow = flow;
        return this;
    }

    public Double getFlow() {
        return flow;
    }

    public void setFlow(Double flow) {
        this.flow = flow;
    }

    public PumpTransfer comment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PumpTransfer pumpTransfer = (PumpTransfer) o;
        return this.pumpType == null || pumpTransfer.pumpType == null
                ? Objects.equals(this.pumpType, pumpTransfer.pumpType)
                : this.pumpType.equals(pumpTransfer.pumpType)
                && this.transferTypeDisplay == null
                || pumpTransfer.transferTypeDisplay == null
                ? Objects.equals(this.transferTypeDisplay, pumpTransfer.transferTypeDisplay)
                : this.transferTypeDisplay.equalsIgnoreCase(pumpTransfer.transferTypeDisplay)
                && Objects.equals(this.flow, pumpTransfer.flow)
                && this.comment == null || pumpTransfer.comment == null
                ? Objects.equals(this.comment, pumpTransfer.comment)
                : this.comment.equalsIgnoreCase(pumpTransfer.comment)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pumpType == null ? 0 : pumpType, transferTypeDisplay == null
                ? 0 : transferTypeDisplay.toLowerCase(), flow, comment == null ? 0 : comment.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PumpTransfer {\n");

        sb.append("    pumpType: ").append(toIndentedString(pumpType)).append("\n");
        sb.append("    transferTypeDisplay: ").append(toIndentedString(transferTypeDisplay)).append("\n");
        sb.append("    flow: ").append(toIndentedString(flow)).append("\n");
        sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
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
