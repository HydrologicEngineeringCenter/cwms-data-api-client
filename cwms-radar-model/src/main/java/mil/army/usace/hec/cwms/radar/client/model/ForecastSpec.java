/*
 * MIT License
 *
 * Copyright (c) 2024 Hydrologic Engineering Center
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ForecastSpec
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-04-09T15:43:11.361601200-07:00[America/Los_Angeles]")
public class ForecastSpec {
    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("spec-id")
    private String specId = null;

    @JsonProperty("designator")
    private String designator = null;

    @JsonProperty("location-ids")
    @Valid
    private List<String> locationIds = new ArrayList<>();

    @JsonProperty("source-entity-id")
    private String sourceEntityId = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("time-series-ids")
    @Valid
    private List<TimeSeriesIdentifierDescriptor> timeSeriesIds = new ArrayList<>();

    public ForecastSpec officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    /**
     * Owning office of object.
     *
     * @return officeId
     **/
    @NotNull

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public ForecastSpec specId(String specId) {
        this.specId = specId;
        return this;
    }

    /**
     * Forecast Spec ID
     *
     * @return specId
     **/

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public ForecastSpec designator(String designator) {
        this.designator = designator;
        return this;
    }

    /**
     * Forecast Designator
     *
     * @return designator
     **/

    public String getDesignator() {
        return designator;
    }

    public void setDesignator(String designator) {
        this.designator = designator;
    }

    public ForecastSpec locationIds(List<String> locationIds) {
        this.locationIds = locationIds;
        return this;
    }

    public ForecastSpec addLocationIdsItem(String locationIdsItem) {
        if (this.locationIds == null) {
            this.locationIds = new ArrayList<String>();
        }
        this.locationIds.add(locationIdsItem);
        return this;
    }

    /**
     * Location IDs
     *
     * @return locationIds
     **/

    public List<String> getLocationIds() {
        return locationIds;
    }

    public void setLocationIds(List<String> locationIds) {
        this.locationIds = locationIds;
    }

    public ForecastSpec sourceEntityId(String sourceEntityId) {
        this.sourceEntityId = sourceEntityId;
        return this;
    }

    /**
     * Source Entity ID
     *
     * @return sourceEntityId
     **/

    public String getSourceEntityId() {
        return sourceEntityId;
    }

    public void setSourceEntityId(String sourceEntityId) {
        this.sourceEntityId = sourceEntityId;
    }

    public ForecastSpec description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Description of Forecast
     *
     * @return description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ForecastSpec timeSeriesIds(List<TimeSeriesIdentifierDescriptor> timeSeriesIds) {
        this.timeSeriesIds = timeSeriesIds;
        return this;
    }

    public ForecastSpec addTimeSeriesIdsItem(TimeSeriesIdentifierDescriptor timeSeriesIdsItem) {
        if (this.timeSeriesIds == null) {
            this.timeSeriesIds = new ArrayList<TimeSeriesIdentifierDescriptor>();
        }
        this.timeSeriesIds.add(timeSeriesIdsItem);
        return this;
    }

    /**
     * List of Time Series IDs belonging to this Forecast Spec
     *
     * @return timeSeriesIds
     **/
    @Valid
    public List<TimeSeriesIdentifierDescriptor> getTimeSeriesIds() {
        return timeSeriesIds;
    }

    public void setTimeSeriesIds(List<TimeSeriesIdentifierDescriptor> timeSeriesIds) {
        this.timeSeriesIds = timeSeriesIds;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ForecastSpec forecastSpec = (ForecastSpec) o;
        return this.officeId == null || forecastSpec.officeId == null ? Objects.equals(this.officeId, forecastSpec.officeId) : this.officeId.equalsIgnoreCase(forecastSpec.officeId)
                && this.specId == null || forecastSpec.specId == null ? Objects.equals(this.specId, forecastSpec.specId) : this.specId.equalsIgnoreCase(forecastSpec.specId)
                && this.designator == null || forecastSpec.designator == null ? Objects.equals(this.designator, forecastSpec.designator) : this.designator.equalsIgnoreCase(forecastSpec.designator)
                && Objects.equals(this.locationIds, forecastSpec.locationIds)
                && this.sourceEntityId == null || forecastSpec.sourceEntityId == null ? Objects.equals(this.sourceEntityId, forecastSpec.sourceEntityId) : this.sourceEntityId.equalsIgnoreCase(forecastSpec.sourceEntityId)
                && this.description == null || forecastSpec.description == null ? Objects.equals(this.description, forecastSpec.description) : this.description.equalsIgnoreCase(forecastSpec.description)
                && Objects.equals(this.timeSeriesIds, forecastSpec.timeSeriesIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeId == null ? 0 : officeId.toLowerCase(), specId == null ? 0 : specId.toLowerCase(), designator == null ? 0 : designator.toLowerCase(), locationIds, sourceEntityId == null ? 0 : sourceEntityId.toLowerCase(), description == null ? 0 : description.toLowerCase(), timeSeriesIds);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ForecastSpec {\n");

        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    specId: ").append(toIndentedString(specId)).append("\n");
        sb.append("    designator: ").append(toIndentedString(designator)).append("\n");
        sb.append("    locationIds: ").append(toIndentedString(locationIds)).append("\n");
        sb.append("    sourceEntityId: ").append(toIndentedString(sourceEntityId)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    timeSeriesIds: ").append(toIndentedString(timeSeriesIds)).append("\n");
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
