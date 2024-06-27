package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import java.util.Objects;

/**
 * Basin
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-06-27T10:32:01.636615300-07:00[America/Los_Angeles]")
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

    public Basin basinId(CwmsId basinId) {
        this.basinId = basinId;
        return this;
    }

    /**
     * Get basinId
     *
     * @return basinId
     **/

    @Valid
    public CwmsId getBasinId() {
        return basinId;
    }

    public void setBasinId(CwmsId basinId) {
        this.basinId = basinId;
    }

    public Basin sortOrder(Double sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    /**
     * Get sortOrder
     *
     * @return sortOrder
     **/

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
     **/

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
     **/

    public Double getContributingDrainageArea() {
        return contributingDrainageArea;
    }

    public void setContributingDrainageArea(Double contributingDrainageArea) {
        this.contributingDrainageArea = contributingDrainageArea;
    }

    public Basin parentBasinId(CwmsId parentBasinId) {
        this.parentBasinId = parentBasinId;
        return this;
    }

    /**
     * Get parentBasinId
     *
     * @return parentBasinId
     **/

    @Valid
    public CwmsId getParentBasinId() {
        return parentBasinId;
    }

    public void setParentBasinId(CwmsId parentBasinId) {
        this.parentBasinId = parentBasinId;
    }

    public Basin areaUnit(String areaUnit) {
        this.areaUnit = areaUnit;
        return this;
    }

    /**
     * Get areaUnit
     *
     * @return areaUnit
     **/

    public String getAreaUnit() {
        return areaUnit;
    }

    public void setAreaUnit(String areaUnit) {
        this.areaUnit = areaUnit;
    }

    public Basin primaryStreamId(CwmsId primaryStreamId) {
        this.primaryStreamId = primaryStreamId;
        return this;
    }

    /**
     * Get primaryStreamId
     *
     * @return primaryStreamId
     **/

    @Valid
    public CwmsId getPrimaryStreamId() {
        return primaryStreamId;
    }

    public void setPrimaryStreamId(CwmsId primaryStreamId) {
        this.primaryStreamId = primaryStreamId;
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
        return Objects.equals(this.basinId, basin.basinId)
                && Objects.equals(this.sortOrder, basin.sortOrder)
                && Objects.equals(this.totalDrainageArea, basin.totalDrainageArea)
                && Objects.equals(this.contributingDrainageArea, basin.contributingDrainageArea)
                && Objects.equals(this.parentBasinId, basin.parentBasinId)
                && this.areaUnit == null || basin.areaUnit == null
                ? Objects.equals(this.areaUnit, basin.areaUnit) : this.areaUnit.equalsIgnoreCase(basin.areaUnit)
                && Objects.equals(this.primaryStreamId, basin.primaryStreamId)
                ;

    }

    @Override
    public int hashCode() {
        return Objects.hash(basinId, sortOrder, totalDrainageArea, contributingDrainageArea, parentBasinId,
                areaUnit == null ? 0 : areaUnit.toLowerCase(), primaryStreamId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Basin {\n");

        sb.append("    basinId: ").append(toIndentedString(basinId)).append("\n");
        sb.append("    sortOrder: ").append(toIndentedString(sortOrder)).append("\n");
        sb.append("    totalDrainageArea: ").append(toIndentedString(totalDrainageArea)).append("\n");
        sb.append("    contributingDrainageArea: ").append(toIndentedString(contributingDrainageArea)).append("\n");
        sb.append("    parentBasinId: ").append(toIndentedString(parentBasinId)).append("\n");
        sb.append("    areaUnit: ").append(toIndentedString(areaUnit)).append("\n");
        sb.append("    primaryStreamId: ").append(toIndentedString(primaryStreamId)).append("\n");
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
