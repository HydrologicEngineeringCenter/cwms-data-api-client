package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import javax.annotation.Generated;
import java.util.Objects;

/**
 * Basin
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-06-26T15:27:00.000-05:00")
public class Basin {
    @JsonProperty("basin-id")
    private CwmsId basinId = null;

    @JsonProperty("sort-order")
    private Double sortOrder = null;

    @JsonProperty("total-drainage-area")
    private Double totalDrainageArea = null;

    @JsonProperty("contributing-drainage-area")
    private Double contributingDrainageArea = null;

    @JsonProperty("parent-basin-id")
    private CwmsId parentBasinId = null;

    @JsonProperty("area-unit")
    private String areaUnit = null;

    @JsonProperty("primary-stream-id")
    private CwmsId primaryStreamId = null;

    public Basin sortOrder(Double sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    /**
     * Get sortOrder
     *
     * @return sortOrder
     */
    @NotNull

    public Double getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Double sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Basin totalDrainageArea(Double totalDrainageArea) {
        this.totalDrainageArea = totalDrainageArea;
        return this;
    }

    /**
     * Get totalDrainageArea
     *
     * @return totalDrainageArea
     */
    @NotNull

    public Double getTotalDrainageArea() {
        return totalDrainageArea;
    }

    public void setTotalDrainageArea(Double totalDrainageArea) {
        this.totalDrainageArea = totalDrainageArea;
    }

    public Basin contributingDrainageArea(Double contributingDrainageArea) {
        this.contributingDrainageArea = contributingDrainageArea;
        return this;
    }

    /**
     * Get contributingDrainageArea
     *
     * @return contributingDrainageArea
     */
    @NotNull

    public Double getContributingDrainageArea() {
        return contributingDrainageArea;
    }

    public void setContributingDrainageArea(Double contributingDrainageArea) {
        this.contributingDrainageArea = contributingDrainageArea;
    }

    public Basin areaUnit(String areaUnit) {
        this.areaUnit = areaUnit;
        return this;
    }

    /**
     * Get areaUnit
     *
     * @return areaUnit
     */
    @NotNull

    public String getAreaUnit() {
        return areaUnit;
    }

    public void setAreaUnit(String areaUnit) {
        this.areaUnit = areaUnit;
    }

    /**
     * Get basinId
     *
     * @return
     */
    @NotNull

    public CwmsId getBasinId() {
        return basinId;
    }

    public void setBasinId(String name, String office) {
        this.basinId = new CwmsId.Builder(name, office);
    }

    /**
     * Get parentBasinId
     *
     * @return
     */

    public CwmsId getParentBasinId() {
        return parentBasinId;
    }

    public void setParentBasinId(String name, String office) {
        this.parentBasinId = new CwmsId.Builder(name, office);
    }

    /**
     * Get primaryStreamId
     *
     * @return
     */

    public CwmsId getPrimaryStreamId() {
        return primaryStreamId;
    }

    public void setPrimaryStreamId(String name, String office) {
        this.primaryStreamId = new CwmsId.Builder(name, office);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Basin basin = (Basin) o;
        return Objects.equals(this.sortOrder, basin.sortOrder) &&
                Objects.equals(this.totalDrainageArea, basin.totalDrainageArea) &&
                Objects.equals(this.contributingDrainageArea, basin.contributingDrainageArea) &&
                Objects.equals(this.areaUnit, basin.areaUnit) &&
                Objects.equals(this.primaryStreamId.getName(), basin.primaryStreamId.getName()) &&
                Objects.equals(this.primaryStreamId.getOfficeId(), basin.primaryStreamId.getOfficeId()) &&
                Objects.equals(this.parentBasinId.getName(), basin.parentBasinId.getName()) &&
                Objects.equals(this.parentBasinId.getOfficeId(), basin.parentBasinId.getOfficeId()) &&
                Objects.equals(this.basinId.getName(), basin.basinId.getName()) &&
                Objects.equals(this.basinId.getOfficeId(), basin.basinId.getOfficeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(sortOrder, totalDrainageArea, contributingDrainageArea, areaUnit, primaryStreamId, primaryStreamOfficeId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Basin {\n");

        sb.append("    sortOrder: ").append(toIndentedString(sortOrder)).append("\n");
        sb.append("    totalDrainageArea: ").append(toIndentedString(totalDrainageArea)).append("\n");
        sb.append("    contributingDrainageArea: ").append(toIndentedString(contributingDrainageArea)).append("\n");
        sb.append("    areaUnit: ").append(toIndentedString(areaUnit)).append("\n");
        sb.append("    primaryStreamId: ").append(toIndentedString(primaryStreamId.getName())).append("\n");
        sb.append("    OfficeId: ").append(toIndentedString(basinId.getOffice())).append("\n");
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
