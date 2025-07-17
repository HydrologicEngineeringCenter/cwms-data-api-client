package mil.army.usace.hec.cwms.data.api.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * AssignedTimeSeries
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-12-06T12:43:40.669367400-08:00[America/Los_Angeles]")
public class AssignedTimeSeries {

    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("timeseries-id")
    private String timeseriesId = null;

    @JsonProperty("ts-code")
    private BigDecimal tsCode = null;

    @JsonProperty("alias-id")
    private String aliasId = null;

    @JsonProperty("refTs-id")
    private String refTsId = null;

    @JsonProperty("attribute")
    private Integer attribute = null;

    public AssignedTimeSeries officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public AssignedTimeSeries timeseriesId(String timeseriesId) {
        this.timeseriesId = timeseriesId;
        return this;
    }

    public String getTimeseriesId() {
        return timeseriesId;
    }

    public void setTimeseriesId(String timeseriesId) {
        this.timeseriesId = timeseriesId;
    }

    public AssignedTimeSeries tsCode(BigDecimal tsCode) {
        this.tsCode = tsCode;
        return this;
    }

    public BigDecimal getTsCode() {
        return tsCode;
    }

    public void setTsCode(BigDecimal tsCode) {
        this.tsCode = tsCode;
    }

    public AssignedTimeSeries aliasId(String aliasId) {
        this.aliasId = aliasId;
        return this;
    }

    public String getAliasId() {
        return aliasId;
    }

    public void setAliasId(String aliasId) {
        this.aliasId = aliasId;
    }

    public AssignedTimeSeries refTsId(String refTsId) {
        this.refTsId = refTsId;
        return this;
    }

    public String getRefTsId() {
        return refTsId;
    }

    public void setRefTsId(String refTsId) {
        this.refTsId = refTsId;
    }

    public AssignedTimeSeries attribute(Integer attribute) {
        this.attribute = attribute;
        return this;
    }

    public Integer getAttribute() {
        return attribute;
    }

    public void setAttribute(Integer attribute) {
        this.attribute = attribute;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AssignedTimeSeries assignedTimeSeries = (AssignedTimeSeries) o;
        return this.officeId == null || assignedTimeSeries.officeId == null
                ? Objects.equals(this.officeId, assignedTimeSeries.officeId)
                : this.officeId.equalsIgnoreCase(assignedTimeSeries.officeId)
                && this.timeseriesId == null || assignedTimeSeries.timeseriesId == null
                ? Objects.equals(this.timeseriesId, assignedTimeSeries.timeseriesId)
                : this.timeseriesId.equalsIgnoreCase(assignedTimeSeries.timeseriesId)
                && Objects.equals(this.tsCode, assignedTimeSeries.tsCode)
                && this.aliasId == null || assignedTimeSeries.aliasId == null
                ? Objects.equals(this.aliasId, assignedTimeSeries.aliasId)
                : this.aliasId.equalsIgnoreCase(assignedTimeSeries.aliasId)
                && this.refTsId == null || assignedTimeSeries.refTsId == null
                ? Objects.equals(this.refTsId, assignedTimeSeries.refTsId)
                : this.refTsId.equalsIgnoreCase(assignedTimeSeries.refTsId)
                && Objects.equals(this.attribute, assignedTimeSeries.attribute)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeId == null ? 0 : officeId.toLowerCase(),
                timeseriesId == null ? 0 : timeseriesId.toLowerCase(), tsCode,
                aliasId == null ? 0 : aliasId.toLowerCase(),
                refTsId == null ? 0 : refTsId.toLowerCase(), attribute);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AssignedTimeSeries {\n");

        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    timeseriesId: ").append(toIndentedString(timeseriesId)).append("\n");
        sb.append("    tsCode: ").append(toIndentedString(tsCode)).append("\n");
        sb.append("    aliasId: ").append(toIndentedString(aliasId)).append("\n");
        sb.append("    refTsId: ").append(toIndentedString(refTsId)).append("\n");
        sb.append("    attribute: ").append(toIndentedString(attribute)).append("\n");
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
