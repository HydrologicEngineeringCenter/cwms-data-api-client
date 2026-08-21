package mil.army.usace.hec.cwms.data.api.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A partial update (PATCH) of a timeseries group, describing time series membership changes rather than a full list of assigned time series
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-08-20T15:50:00.721284-07:00[America/Los_Angeles]")
public class TimeSeriesGroupPatch {

    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("id")
    private String id = null;

    @JsonProperty("time-series-category")
    private TimeSeriesCategory timeSeriesCategory = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("shared-alias-id")
    private String sharedAliasId = null;

    @JsonProperty("shared-ref-ts-id")
    private String sharedRefTsId = null;

    @JsonProperty("membership")
    private TimeSeriesGroupMembership membership = null;

    public TimeSeriesGroupPatch officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public TimeSeriesGroupPatch id(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TimeSeriesGroupPatch timeSeriesCategory(TimeSeriesCategory timeSeriesCategory) {
        this.timeSeriesCategory = timeSeriesCategory;
        return this;
    }

    public TimeSeriesCategory getTimeSeriesCategory() {
        return timeSeriesCategory;
    }

    public void setTimeSeriesCategory(TimeSeriesCategory timeSeriesCategory) {
        this.timeSeriesCategory = timeSeriesCategory;
    }

    public TimeSeriesGroupPatch description(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TimeSeriesGroupPatch sharedAliasId(String sharedAliasId) {
        this.sharedAliasId = sharedAliasId;
        return this;
    }

    public String getSharedAliasId() {
        return sharedAliasId;
    }

    public void setSharedAliasId(String sharedAliasId) {
        this.sharedAliasId = sharedAliasId;
    }

    public TimeSeriesGroupPatch sharedRefTsId(String sharedRefTsId) {
        this.sharedRefTsId = sharedRefTsId;
        return this;
    }

    public String getSharedRefTsId() {
        return sharedRefTsId;
    }

    public void setSharedRefTsId(String sharedRefTsId) {
        this.sharedRefTsId = sharedRefTsId;
    }

    public TimeSeriesGroupPatch membership(TimeSeriesGroupMembership membership) {
        this.membership = membership;
        return this;
    }

    public TimeSeriesGroupMembership getMembership() {
        return membership;
    }

    public void setMembership(TimeSeriesGroupMembership membership) {
        this.membership = membership;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        TimeSeriesGroupPatch timeSeriesGroupPatch = (TimeSeriesGroupPatch) o;
        return this.officeId == null || timeSeriesGroupPatch.officeId == null?Objects.equals(this.officeId, timeSeriesGroupPatch.officeId):this.officeId.equalsIgnoreCase(timeSeriesGroupPatch.officeId)
         && this.id == null || timeSeriesGroupPatch.id == null?Objects.equals(this.id, timeSeriesGroupPatch.id):this.id.equalsIgnoreCase(timeSeriesGroupPatch.id)
         && Objects.equals(this.timeSeriesCategory, timeSeriesGroupPatch.timeSeriesCategory)
         && this.description == null || timeSeriesGroupPatch.description == null?Objects.equals(this.description, timeSeriesGroupPatch.description):this.description.equalsIgnoreCase(timeSeriesGroupPatch.description)
         && this.sharedAliasId == null || timeSeriesGroupPatch.sharedAliasId == null?Objects.equals(this.sharedAliasId, timeSeriesGroupPatch.sharedAliasId):this.sharedAliasId.equalsIgnoreCase(timeSeriesGroupPatch.sharedAliasId)
         && this.sharedRefTsId == null || timeSeriesGroupPatch.sharedRefTsId == null?Objects.equals(this.sharedRefTsId, timeSeriesGroupPatch.sharedRefTsId):this.sharedRefTsId.equalsIgnoreCase(timeSeriesGroupPatch.sharedRefTsId)
         && Objects.equals(this.membership, timeSeriesGroupPatch.membership)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeId==null?0:officeId.toLowerCase(), id==null?0:id.toLowerCase(), timeSeriesCategory, description==null?0:description.toLowerCase(), sharedAliasId==null?0:sharedAliasId.toLowerCase(), sharedRefTsId==null?0:sharedRefTsId.toLowerCase(), membership);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesGroupPatch {\n");
        
        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    timeSeriesCategory: ").append(toIndentedString(timeSeriesCategory)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    sharedAliasId: ").append(toIndentedString(sharedAliasId)).append("\n");
        sb.append("    sharedRefTsId: ").append(toIndentedString(sharedRefTsId)).append("\n");
        sb.append("    membership: ").append(toIndentedString(membership)).append("\n");
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
