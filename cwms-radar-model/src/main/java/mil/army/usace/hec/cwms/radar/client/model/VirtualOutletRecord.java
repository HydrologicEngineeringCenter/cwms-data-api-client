package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * VirtualOutletRecord
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-07-29T13:57:29.992917800-07:00[America/Los_Angeles]")
public class VirtualOutletRecord {

    @JsonProperty("outlet-id")
    private CwmsId outletId = null;

    @JsonProperty("downstream-outlet-ids")
    @Valid
    private List<CwmsId> downstreamOutletIds = new ArrayList<>();

    public VirtualOutletRecord outletId(CwmsId outletId) {
        this.outletId = outletId;
        return this;
    }

    public CwmsId getOutletId() {
        return outletId;
    }

    public void setOutletId(CwmsId outletId) {
        this.outletId = outletId;
    }

    public VirtualOutletRecord downstreamOutletIds(List<CwmsId> downstreamOutletIds) {
        this.downstreamOutletIds = downstreamOutletIds;
        return this;
    }

    public VirtualOutletRecord addDownstreamOutletIdsItem(CwmsId downstreamOutletIdsItem) {
        if (this.downstreamOutletIds == null) {
            this.downstreamOutletIds = new ArrayList<>();
        }
        this.downstreamOutletIds.add(downstreamOutletIdsItem);
        return this;
    }

    public List<CwmsId> getDownstreamOutletIds() {
        return downstreamOutletIds;
    }

    public void setDownstreamOutletIds(List<CwmsId> downstreamOutletIds) {
        this.downstreamOutletIds = downstreamOutletIds;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VirtualOutletRecord virtualOutletRecord = (VirtualOutletRecord) o;
        return Objects.equals(this.outletId, virtualOutletRecord.outletId)
                && Objects.equals(this.downstreamOutletIds, virtualOutletRecord.downstreamOutletIds)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(outletId, downstreamOutletIds);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class VirtualOutletRecord {\n");

        sb.append("    outletId: ").append(toIndentedString(outletId)).append("\n");
        sb.append("    downstreamOutletIds: ").append(toIndentedString(downstreamOutletIds)).append("\n");
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
