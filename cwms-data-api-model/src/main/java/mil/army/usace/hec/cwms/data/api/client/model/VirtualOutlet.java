package mil.army.usace.hec.cwms.data.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * VirtualOutlet
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-07-29T13:57:29.992917800-07:00[America/Los_Angeles]")
public class VirtualOutlet {

    @JsonProperty("project-id")
    private CwmsId projectId = null;

    @JsonProperty("virtual-outlet-id")
    private CwmsId virtualOutletId = null;

    @JsonProperty("virtual-records")
    @Valid
    private List<VirtualOutletRecord> virtualRecords = new ArrayList<>();

    public VirtualOutlet projectId(CwmsId projectId) {
        this.projectId = projectId;
        return this;
    }

    public CwmsId getProjectId() {
        return projectId;
    }

    public void setProjectId(CwmsId projectId) {
        this.projectId = projectId;
    }

    public VirtualOutlet virtualOutletId(CwmsId virtualOutletId) {
        this.virtualOutletId = virtualOutletId;
        return this;
    }

    public CwmsId getVirtualOutletId() {
        return virtualOutletId;
    }

    public void setVirtualOutletId(CwmsId virtualOutletId) {
        this.virtualOutletId = virtualOutletId;
    }

    public VirtualOutlet virtualRecords(List<VirtualOutletRecord> virtualRecords) {
        this.virtualRecords = virtualRecords;
        return this;
    }

    public VirtualOutlet addVirtualRecordsItem(VirtualOutletRecord virtualRecordsItem) {
        if (this.virtualRecords == null) {
            this.virtualRecords = new ArrayList<>();
        }
        this.virtualRecords.add(virtualRecordsItem);
        return this;
    }

    public List<VirtualOutletRecord> getVirtualRecords() {
        return virtualRecords;
    }

    public void setVirtualRecords(List<VirtualOutletRecord> virtualRecords) {
        this.virtualRecords = virtualRecords;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VirtualOutlet virtualOutlet = (VirtualOutlet) o;
        return Objects.equals(this.projectId, virtualOutlet.projectId)
                && Objects.equals(this.virtualOutletId, virtualOutlet.virtualOutletId)
                && Objects.equals(this.virtualRecords, virtualOutlet.virtualRecords)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, virtualOutletId, virtualRecords);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class VirtualOutlet {\n");

        sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
        sb.append("    virtualOutletId: ").append(toIndentedString(virtualOutletId)).append("\n");
        sb.append("    virtualRecords: ").append(toIndentedString(virtualRecords)).append("\n");
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
