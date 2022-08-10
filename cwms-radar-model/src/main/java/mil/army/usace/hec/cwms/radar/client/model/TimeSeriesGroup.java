/*
 * MIT License
 *
 * Copyright (c) 2022 Hydrologic Engineering Center
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
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;

/**
 * A representation of a timeseries group
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-09T09:29:33.859-07:00[America/Los_Angeles]")
public class TimeSeriesGroup {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("time-series-category")
    private TimeSeriesCategory timeSeriesCategory = null;

    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("shared-alias-id")
    private String sharedAliasId = null;

    @JsonProperty("shared-ref-ts-id")
    private String sharedRefTsId = null;

    @JsonProperty("assigned-time-series")
    @Valid
    private List<AssignedTimeSeries> assignedTimeSeries = new ArrayList<>();

    public TimeSeriesGroup id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TimeSeriesGroup timeSeriesCategory(TimeSeriesCategory timeSeriesCategory) {
        this.timeSeriesCategory = timeSeriesCategory;
        return this;
    }

    /**
     * Get timeSeriesCategory
     *
     * @return timeSeriesCategory
     **/

    @Valid
    public TimeSeriesCategory getTimeSeriesCategory() {
        return timeSeriesCategory;
    }

    public void setTimeSeriesCategory(TimeSeriesCategory timeSeriesCategory) {
        this.timeSeriesCategory = timeSeriesCategory;
    }

    public TimeSeriesGroup officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    /**
     * Get officeId
     *
     * @return officeId
     **/

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public TimeSeriesGroup description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get description
     *
     * @return description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TimeSeriesGroup sharedAliasId(String sharedAliasId) {
        this.sharedAliasId = sharedAliasId;
        return this;
    }

    /**
     * Get sharedAliasId
     *
     * @return sharedAliasId
     **/

    public String getSharedAliasId() {
        return sharedAliasId;
    }

    public void setSharedAliasId(String sharedAliasId) {
        this.sharedAliasId = sharedAliasId;
    }

    public TimeSeriesGroup sharedRefTsId(String sharedRefTsId) {
        this.sharedRefTsId = sharedRefTsId;
        return this;
    }

    /**
     * Get sharedRefTsId
     *
     * @return sharedRefTsId
     **/

    public String getSharedRefTsId() {
        return sharedRefTsId;
    }

    public void setSharedRefTsId(String sharedRefTsId) {
        this.sharedRefTsId = sharedRefTsId;
    }

    public TimeSeriesGroup assignedTimeSeries(List<AssignedTimeSeries> assignedTimeSeries) {
        this.assignedTimeSeries = assignedTimeSeries;
        return this;
    }

    public TimeSeriesGroup addAssignedTimeSeriesItem(AssignedTimeSeries assignedTimeSeriesItem) {
        if (this.assignedTimeSeries == null) {
            this.assignedTimeSeries = new ArrayList<AssignedTimeSeries>();
        }
        this.assignedTimeSeries.add(assignedTimeSeriesItem);
        return this;
    }

    /**
     * Get assignedTimeSeries
     *
     * @return assignedTimeSeries
     **/
    @Valid
    public List<AssignedTimeSeries> getAssignedTimeSeries() {
        return assignedTimeSeries;
    }

    public void setAssignedTimeSeries(List<AssignedTimeSeries> assignedTimeSeries) {
        this.assignedTimeSeries = assignedTimeSeries;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSeriesGroup timeSeriesGroup = (TimeSeriesGroup) o;
        return this.id == null || timeSeriesGroup.id == null ? Objects.equals(this.id, timeSeriesGroup.id) :
            this.id.equalsIgnoreCase(timeSeriesGroup.id) && Objects.equals(this.timeSeriesCategory, timeSeriesGroup.timeSeriesCategory) &&
                this.officeId == null || timeSeriesGroup.officeId == null ? Objects.equals(this.officeId, timeSeriesGroup.officeId) :
                this.officeId.equalsIgnoreCase(timeSeriesGroup.officeId) && this.description == null || timeSeriesGroup.description == null ?
                    Objects.equals(this.description, timeSeriesGroup.description) :
                    this.description.equalsIgnoreCase(timeSeriesGroup.description) && this.sharedAliasId == null ||
                        timeSeriesGroup.sharedAliasId == null ? Objects.equals(this.sharedAliasId, timeSeriesGroup.sharedAliasId) :
                        this.sharedAliasId.equalsIgnoreCase(timeSeriesGroup.sharedAliasId) && this.sharedRefTsId == null ||
                            timeSeriesGroup.sharedRefTsId == null ? Objects.equals(this.sharedRefTsId, timeSeriesGroup.sharedRefTsId) :
                            this.sharedRefTsId.equalsIgnoreCase(timeSeriesGroup.sharedRefTsId) &&
                                Objects.equals(this.assignedTimeSeries, timeSeriesGroup.assignedTimeSeries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id == null ? 0 : id.toLowerCase(), timeSeriesCategory, officeId == null ? 0 : officeId.toLowerCase(),
            description == null ? 0 : description.toLowerCase(), sharedAliasId == null ? 0 : sharedAliasId.toLowerCase(),
            sharedRefTsId == null ? 0 : sharedRefTsId.toLowerCase(), assignedTimeSeries);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesGroup {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    timeSeriesCategory: ").append(toIndentedString(timeSeriesCategory)).append("\n");
        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    sharedAliasId: ").append(toIndentedString(sharedAliasId)).append("\n");
        sb.append("    sharedRefTsId: ").append(toIndentedString(sharedRefTsId)).append("\n");
        sb.append("    assignedTimeSeries: ").append(toIndentedString(assignedTimeSeries)).append("\n");
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
