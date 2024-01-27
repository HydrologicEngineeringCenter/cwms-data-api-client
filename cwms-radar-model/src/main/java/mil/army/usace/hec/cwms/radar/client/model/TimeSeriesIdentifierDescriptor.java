/*
 * MIT License
 *
 * Copyright (c) 2023 Hydrologic Engineering Center
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
import java.util.Objects;

/**
 * TimeSeriesIdentifierDescriptor
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-03-07T08:09:37.578-08:00[America/Los_Angeles]")
public class TimeSeriesIdentifierDescriptor {
    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("time-series-id")
    private String timeSeriesId = null;

    @JsonProperty("timezone-name")
    private String timezoneName = null;

    @JsonProperty("interval-offset-minutes")
    private Long intervalOffsetMinutes = null;

    @JsonProperty("active")
    private Boolean active = null;

    public TimeSeriesIdentifierDescriptor officeId(String officeId) {
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

    public TimeSeriesIdentifierDescriptor timeSeriesId(String timeSeriesId) {
        this.timeSeriesId = timeSeriesId;
        return this;
    }

    /**
     * Get timeSeriesId
     *
     * @return timeSeriesId
     **/

    public String getTimeSeriesId() {
        return timeSeriesId;
    }

    public void setTimeSeriesId(String timeSeriesId) {
        this.timeSeriesId = timeSeriesId;
    }

    public TimeSeriesIdentifierDescriptor timezoneName(String timezoneName) {
        this.timezoneName = timezoneName;
        return this;
    }

    /**
     * Get timezoneName
     *
     * @return timezoneName
     **/

    public String getTimezoneName() {
        return timezoneName;
    }

    public void setTimezoneName(String timezoneName) {
        this.timezoneName = timezoneName;
    }

    public TimeSeriesIdentifierDescriptor intervalOffsetMinutes(Long intervalOffsetMinutes) {
        this.intervalOffsetMinutes = intervalOffsetMinutes;
        return this;
    }

    /**
     * Get intervalOffsetMinutes
     *
     * @return intervalOffsetMinutes
     **/

    public Long getIntervalOffsetMinutes() {
        return intervalOffsetMinutes;
    }

    public void setIntervalOffsetMinutes(Long intervalOffsetMinutes) {
        this.intervalOffsetMinutes = intervalOffsetMinutes;
    }

    public TimeSeriesIdentifierDescriptor active(Boolean active) {
        this.active = active;
        return this;
    }

    /**
     * Get active
     *
     * @return active
     **/

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSeriesIdentifierDescriptor timeSeriesIdentifierDescriptor = (TimeSeriesIdentifierDescriptor) o;
        return this.officeId == null || timeSeriesIdentifierDescriptor.officeId == null ?
            Objects.equals(this.officeId, timeSeriesIdentifierDescriptor.officeId) :
            this.officeId.equalsIgnoreCase(timeSeriesIdentifierDescriptor.officeId) && this.timeSeriesId == null ||
                timeSeriesIdentifierDescriptor.timeSeriesId == null ? Objects.equals(this.timeSeriesId, timeSeriesIdentifierDescriptor.timeSeriesId) :
                this.timeSeriesId.equalsIgnoreCase(timeSeriesIdentifierDescriptor.timeSeriesId) && this.timezoneName == null ||
                    timeSeriesIdentifierDescriptor.timezoneName == null ?
                    Objects.equals(this.timezoneName, timeSeriesIdentifierDescriptor.timezoneName) :
                    this.timezoneName.equalsIgnoreCase(timeSeriesIdentifierDescriptor.timezoneName) &&
                        Objects.equals(this.intervalOffsetMinutes, timeSeriesIdentifierDescriptor.intervalOffsetMinutes) &&
                        Objects.equals(this.active, timeSeriesIdentifierDescriptor.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeId == null ? 0 : officeId.toLowerCase(), timeSeriesId == null ? 0 : timeSeriesId.toLowerCase(),
            timezoneName == null ? 0 : timezoneName.toLowerCase(), intervalOffsetMinutes, active);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesIdentifierDescriptor {\n");

        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    timeSeriesId: ").append(toIndentedString(timeSeriesId)).append("\n");
        sb.append("    timezoneName: ").append(toIndentedString(timezoneName)).append("\n");
        sb.append("    intervalOffsetMinutes: ").append(toIndentedString(intervalOffsetMinutes)).append("\n");
        sb.append("    active: ").append(toIndentedString(active)).append("\n");
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
