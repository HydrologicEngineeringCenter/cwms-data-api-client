package mil.army.usace.hec.cwms.radar.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * PumpAccounting
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-07-26T13:57:20.362288900-07:00[America/Los_Angeles]")
public class PumpAccounting {

    @JsonProperty("pumpLocation")
    private CwmsId pumpLocation = null;

    @JsonProperty("transferType")
    private LookupType transferType = null;

    @JsonProperty("flow")
    private Double flow = null;

    @JsonProperty("transferDate")
    private Date transferDate = null;

    @JsonProperty("comment")
    private String comment = null;

    public PumpAccounting pumpLocation(CwmsId pumpLocation) {
        this.pumpLocation = pumpLocation;
        return this;
    }

    public CwmsId getPumpLocation() {
        return pumpLocation;
    }

    public void setPumpLocation(CwmsId pumpLocation) {
        this.pumpLocation = pumpLocation;
    }

    public PumpAccounting transferType(LookupType transferType) {
        this.transferType = transferType;
        return this;
    }

    public LookupType getTransferType() {
        return transferType;
    }

    public void setTransferType(LookupType transferType) {
        this.transferType = transferType;
    }

    public PumpAccounting flow(Double flow) {
        this.flow = flow;
        return this;
    }

    public Double getFlow() {
        return flow;
    }

    public void setFlow(Double flow) {
        this.flow = flow;
    }

    public PumpAccounting transferDate(Date transferDate) {
        this.transferDate = transferDate;
        return this;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public PumpAccounting comment(String comment) {
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
        PumpAccounting pumpAccounting = (PumpAccounting) o;
        return Objects.equals(this.pumpLocation, pumpAccounting.pumpLocation)
                && Objects.equals(this.transferType, pumpAccounting.transferType)
                && Objects.equals(this.flow, pumpAccounting.flow)
                && Objects.equals(this.transferDate, pumpAccounting.transferDate)
                && this.comment == null || pumpAccounting.comment == null
                ? Objects.equals(this.comment, pumpAccounting.comment)
                : this.comment.equalsIgnoreCase(pumpAccounting.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pumpLocation, transferType, flow, transferDate,
                comment == null ? 0 : comment.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PumpAccounting {\n");

        sb.append("    pumpLocation: ").append(toIndentedString(pumpLocation)).append("\n");
        sb.append("    transferType: ").append(toIndentedString(transferType)).append("\n");
        sb.append("    flow: ").append(toIndentedString(flow)).append("\n");
        sb.append("    transferDate: ").append(toIndentedString(transferDate)).append("\n");
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
