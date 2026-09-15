package mil.army.usace.hec.cwms.data.api.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Describes time series to assign to, and/or unassign from, a timeseries group
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-08-20T15:50:00.721284-07:00[America/Los_Angeles]")
@JsonRootName("membership")
public class TimeSeriesGroupMembership {

    @JsonProperty("assign")
    @Valid
    private List<AssignedTimeSeries> assign = new ArrayList<>();

    @JsonProperty("unassign")
    @Valid
    private List<CwmsId> unassign = new ArrayList<>();

    public TimeSeriesGroupMembership assign(List<AssignedTimeSeries> assign) {
        this.assign = assign;
        return this;
    }

    public TimeSeriesGroupMembership addAssignItem(AssignedTimeSeries assignItem) {
            if (this.assign == null) {
            this.assign = new ArrayList<>();
            }
        this.assign.add(assignItem);
        return this;
    }

    public List<AssignedTimeSeries> getAssign() {
        return assign;
    }

    public void setAssign(List<AssignedTimeSeries> assign) {
        this.assign = assign;
    }

    public TimeSeriesGroupMembership unassign(List<CwmsId> unassign) {
        this.unassign = unassign;
        return this;
    }

    public TimeSeriesGroupMembership addUnassignItem(CwmsId unassignItem) {
            if (this.unassign == null) {
            this.unassign = new ArrayList<>();
            }
        this.unassign.add(unassignItem);
        return this;
    }

    public List<CwmsId> getUnassign() {
        return unassign;
    }

    public void setUnassign(List<CwmsId> unassign) {
        this.unassign = unassign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        TimeSeriesGroupMembership membership = (TimeSeriesGroupMembership) o;
        return Objects.equals(this.assign, membership.assign)
         && Objects.equals(this.unassign, membership.unassign)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(assign, unassign);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Membership {\n");
        
        sb.append("    assign: ").append(toIndentedString(assign)).append("\n");
        sb.append("    unassign: ").append(toIndentedString(unassign)).append("\n");
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
