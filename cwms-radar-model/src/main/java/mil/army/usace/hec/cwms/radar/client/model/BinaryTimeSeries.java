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
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * BinaryTimeSeries
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-03-19T14:26:29.428379500-07:00[America/Los_Angeles]")
public class BinaryTimeSeries {
    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("interval-offset")
    private Long intervalOffset = null;

    @JsonProperty("time-zone")
    private String timeZone = null;

    @JsonProperty("date-version-type")
    private DateVersionTypeEnum dateVersionType = null;

    @JsonProperty("version-date")
    private Instant versionDate = null;

    @JsonProperty("binary-values")
    @Valid
    private List<BinaryTimeSeriesRow> binaryValues = new ArrayList<>();

    public BinaryTimeSeries officeId(String officeId) {
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

    public BinaryTimeSeries name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     **/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BinaryTimeSeries intervalOffset(Long intervalOffset) {
        this.intervalOffset = intervalOffset;
        return this;
    }

    /**
     * Get intervalOffset
     *
     * @return intervalOffset
     **/

    public Long getIntervalOffset() {
        return intervalOffset;
    }

    public void setIntervalOffset(Long intervalOffset) {
        this.intervalOffset = intervalOffset;
    }

    public BinaryTimeSeries timeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    /**
     * Get timeZone
     *
     * @return timeZone
     **/

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public BinaryTimeSeries dateVersionType(DateVersionTypeEnum dateVersionType) {
        this.dateVersionType = dateVersionType;
        return this;
    }

    /**
     * Version type specifies the type of timeseries response to be received. Can be max aggregate or single version. Max aggregate cannot be run if version date field is specified.
     *
     * @return dateVersionType
     **/

    public DateVersionTypeEnum getDateVersionType() {
        return dateVersionType;
    }

    public void setDateVersionType(DateVersionTypeEnum dateVersionType) {
        this.dateVersionType = dateVersionType;
    }

    public BinaryTimeSeries versionDate(Instant versionDate) {
        this.versionDate = versionDate;
        return this;
    }

    /**
     * The version date of the time series trace
     *
     * @return versionDate
     **/

    @Valid
    public Instant getVersionDate() {
        return versionDate;
    }

    public void setVersionDate(Instant versionDate) {
        this.versionDate = versionDate;
    }

    public BinaryTimeSeries binaryValues(List<BinaryTimeSeriesRow> binaryValues) {
        this.binaryValues = binaryValues;
        return this;
    }

    public BinaryTimeSeries addBinaryValuesItem(BinaryTimeSeriesRow binaryValuesItem) {
        if (this.binaryValues == null) {
            this.binaryValues = new ArrayList<BinaryTimeSeriesRow>();
        }
        this.binaryValues.add(binaryValuesItem);
        return this;
    }

    /**
     * Get binaryValues
     *
     * @return binaryValues
     **/
    @Valid
    public List<BinaryTimeSeriesRow> getBinaryValues() {
        return binaryValues;
    }

    public void setBinaryValues(List<BinaryTimeSeriesRow> binaryValues) {
        this.binaryValues = binaryValues;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BinaryTimeSeries binaryTimeSeries = (BinaryTimeSeries) o;
        return this.officeId == null || binaryTimeSeries.officeId == null ? Objects.equals(this.officeId, binaryTimeSeries.officeId) : this.officeId.equalsIgnoreCase(binaryTimeSeries.officeId)
                && this.name == null || binaryTimeSeries.name == null ? Objects.equals(this.name, binaryTimeSeries.name) : this.name.equalsIgnoreCase(binaryTimeSeries.name)
                && Objects.equals(this.intervalOffset, binaryTimeSeries.intervalOffset)
                && this.timeZone == null || binaryTimeSeries.timeZone == null ? Objects.equals(this.timeZone, binaryTimeSeries.timeZone) : this.timeZone.equalsIgnoreCase(binaryTimeSeries.timeZone)
                && this.dateVersionType == null || binaryTimeSeries.dateVersionType == null ? Objects.equals(this.dateVersionType, binaryTimeSeries.dateVersionType) : this.dateVersionType == binaryTimeSeries.dateVersionType
                && Objects.equals(this.versionDate, binaryTimeSeries.versionDate)
                && Objects.equals(this.binaryValues, binaryTimeSeries.binaryValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeId == null ? 0 : officeId.toLowerCase(), name == null ? 0 : name.toLowerCase(), intervalOffset, timeZone == null ? 0 : timeZone.toLowerCase(), dateVersionType, versionDate, binaryValues);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BinaryTimeSeries {\n");

        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    intervalOffset: ").append(toIndentedString(intervalOffset)).append("\n");
        sb.append("    timeZone: ").append(toIndentedString(timeZone)).append("\n");
        sb.append("    dateVersionType: ").append(toIndentedString(dateVersionType)).append("\n");
        sb.append("    versionDate: ").append(toIndentedString(versionDate)).append("\n");
        sb.append("    binaryValues: ").append(toIndentedString(binaryValues)).append("\n");
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
