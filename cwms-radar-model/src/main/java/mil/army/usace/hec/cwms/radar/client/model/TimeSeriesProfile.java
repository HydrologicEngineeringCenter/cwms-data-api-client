package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TimeSeriesProfile
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-09-19T12:32:49.455402100-07:00[America/Los_Angeles]")
public class TimeSeriesProfile {

    @JsonProperty("location-id")
    private CwmsId locationId = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("parameter-list")
    @Valid
    private List<String> parameterList = new ArrayList<>();

    @JsonProperty("key-parameter")
    private String keyParameter = null;

    @JsonProperty("reference-ts-id")
    private CwmsId referenceTsId = null;

    public TimeSeriesProfile locationId(CwmsId locationId) {
        this.locationId = locationId;
        return this;
    }

    public CwmsId getLocationId() {
        return locationId;
    }

    public void setLocationId(CwmsId locationId) {
        this.locationId = locationId;
    }

    public TimeSeriesProfile description(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TimeSeriesProfile parameterList(List<String> parameterList) {
        this.parameterList = parameterList;
        return this;
    }

    public TimeSeriesProfile addParameterListItem(String parameterListItem) {
        if (this.parameterList == null) {
            this.parameterList = new ArrayList<>();
        }
        this.parameterList.add(parameterListItem);
        return this;
    }

    public List<String> getParameterList() {
        return parameterList;
    }

    public void setParameterList(List<String> parameterList) {
        this.parameterList = parameterList;
    }

    public TimeSeriesProfile keyParameter(String keyParameter) {
        this.keyParameter = keyParameter;
        return this;
    }

    public String getKeyParameter() {
        return keyParameter;
    }

    public void setKeyParameter(String keyParameter) {
        this.keyParameter = keyParameter;
    }

    public TimeSeriesProfile referenceTsId(CwmsId referenceTsId) {
        this.referenceTsId = referenceTsId;
        return this;
    }

    public CwmsId getReferenceTsId() {
        return referenceTsId;
    }

    public void setReferenceTsId(CwmsId referenceTsId) {
        this.referenceTsId = referenceTsId;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSeriesProfile timeSeriesProfile = (TimeSeriesProfile) o;
        return Objects.equals(this.locationId, timeSeriesProfile.locationId)
                && this.description == null || timeSeriesProfile.description == null
                ? Objects.equals(this.description, timeSeriesProfile.description)
                : this.description.equalsIgnoreCase(timeSeriesProfile.description)
                && Objects.equals(this.parameterList, timeSeriesProfile.parameterList)
                && this.keyParameter == null || timeSeriesProfile.keyParameter == null
                ? Objects.equals(this.keyParameter, timeSeriesProfile.keyParameter)
                : this.keyParameter.equalsIgnoreCase(timeSeriesProfile.keyParameter)
                && Objects.equals(this.referenceTsId, timeSeriesProfile.referenceTsId)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, description == null ? 0 : description.toLowerCase(), parameterList,
                keyParameter == null ? 0 : keyParameter.toLowerCase(), referenceTsId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesProfile {\n");

        sb.append("    locationId: ").append(toIndentedString(locationId)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    parameterList: ").append(toIndentedString(parameterList)).append("\n");
        sb.append("    keyParameter: ").append(toIndentedString(keyParameter)).append("\n");
        sb.append("    referenceTsId: ").append(toIndentedString(referenceTsId)).append("\n");
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
