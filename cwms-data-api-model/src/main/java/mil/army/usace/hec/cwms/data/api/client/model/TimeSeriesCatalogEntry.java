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

package mil.army.usace.hec.cwms.data.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TimeSeriesCatalogEntry
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-09T09:29:33.859-07:00[America/Los_Angeles]")
public class TimeSeriesCatalogEntry {
    @JsonProperty("office")
    private String office = null;

    @JsonProperty("name")
    private String timeSeriesId = null;

    @JsonProperty("units")
    private String units = null;

    @JsonProperty("interval")
    private String interval = null;

    @JsonProperty("interval-offset")
    private Long intervalOffsetMinutes = null;

    @JsonProperty("time-zone")
    private String locationTimeZone = null;

    @JsonProperty("extents")
    @Valid
    private List<TimeSeriesExtents> extents = new ArrayList<>();

    public TimeSeriesCatalogEntry office(String office) {
        this.office = office;
        return this;
    }

    /**
     * Get office
     *
     * @return office
     **/

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public TimeSeriesCatalogEntry name(String name) {
        this.timeSeriesId = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     **/

    public String getTimeSeriesId() {
        return timeSeriesId;
    }

    public void setTimeSeriesId(String timeSeriesId) {
        this.timeSeriesId = timeSeriesId;
    }

    public TimeSeriesCatalogEntry units(String units) {
        this.units = units;
        return this;
    }

    /**
     * Get units
     *
     * @return units
     **/

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public TimeSeriesCatalogEntry interval(String interval) {
        this.interval = interval;
        return this;
    }

    /**
     * Get interval
     *
     * @return interval
     **/

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public TimeSeriesCatalogEntry intervalOffset(Long intervalOffset) {
        this.intervalOffsetMinutes = intervalOffset;
        return this;
    }

    /**
     * Offset from top of interval
     *
     * @return intervalOffset
     **/

    public Long getIntervalOffsetMinutes() {
        return intervalOffsetMinutes;
    }

    public void setIntervalOffsetMinutes(Long intervalOffsetMinutes) {
        this.intervalOffsetMinutes = intervalOffsetMinutes;
    }

    public TimeSeriesCatalogEntry timeZone(String timeZone) {
        this.locationTimeZone = timeZone;
        return this;
    }

    /**
     * Only on 21.1.1 Database. The timezone the Interval Offset is from.
     *
     * @return timeZone
     **/

    public String getLocationTimeZone() {
        return locationTimeZone;
    }

    public void setLocationTimeZone(String locationTimeZone) {
        this.locationTimeZone = locationTimeZone;
    }

    public TimeSeriesCatalogEntry extents(List<TimeSeriesExtents> extents) {
        this.extents = extents;
        return this;
    }

    public TimeSeriesCatalogEntry addExtentsItem(TimeSeriesExtents extentsItem) {
        if (this.extents == null) {
            this.extents = new ArrayList<TimeSeriesExtents>();
        }
        this.extents.add(extentsItem);
        return this;
    }

    /**
     * Get extents
     *
     * @return extents
     **/
    @Valid
    public List<TimeSeriesExtents> getExtents() {
        return extents;
    }

    public void setExtents(List<TimeSeriesExtents> extents) {
        this.extents = extents;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSeriesCatalogEntry timeseriesCatalogEntry = (TimeSeriesCatalogEntry) o;
        return this.office == null || timeseriesCatalogEntry.office == null ? Objects.equals(this.office, timeseriesCatalogEntry.office) :
            this.office.equalsIgnoreCase(timeseriesCatalogEntry.office) && this.timeSeriesId == null || timeseriesCatalogEntry.timeSeriesId == null ?
                Objects.equals(this.timeSeriesId, timeseriesCatalogEntry.timeSeriesId) :
                this.timeSeriesId.equalsIgnoreCase(timeseriesCatalogEntry.timeSeriesId) && this.units == null ||
                    timeseriesCatalogEntry.units == null ? Objects.equals(this.units, timeseriesCatalogEntry.units) :
                    this.units.equalsIgnoreCase(timeseriesCatalogEntry.units) && this.interval == null || timeseriesCatalogEntry.interval == null ?
                        Objects.equals(this.interval, timeseriesCatalogEntry.interval) :
                        this.interval.equalsIgnoreCase(timeseriesCatalogEntry.interval) &&
                            Objects.equals(this.intervalOffsetMinutes, timeseriesCatalogEntry.intervalOffsetMinutes) &&
                            this.locationTimeZone == null || timeseriesCatalogEntry.locationTimeZone == null ?
                            Objects.equals(this.locationTimeZone, timeseriesCatalogEntry.locationTimeZone) :
                            this.locationTimeZone.equalsIgnoreCase(timeseriesCatalogEntry.locationTimeZone) &&
                                Objects.equals(this.extents, timeseriesCatalogEntry.extents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(office == null ? 0 : office.toLowerCase(), timeSeriesId == null ? 0 : timeSeriesId.toLowerCase(),
            units == null ? 0 : units.toLowerCase(), interval == null ? 0 : interval.toLowerCase(), intervalOffsetMinutes,
            locationTimeZone == null ? 0 : locationTimeZone.toLowerCase(), extents);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeseriesCatalogEntry {\n");

        sb.append("    office: ").append(toIndentedString(office)).append("\n");
        sb.append("    name: ").append(toIndentedString(timeSeriesId)).append("\n");
        sb.append("    units: ").append(toIndentedString(units)).append("\n");
        sb.append("    interval: ").append(toIndentedString(interval)).append("\n");
        sb.append("    intervalOffset: ").append(toIndentedString(intervalOffsetMinutes)).append("\n");
        sb.append("    timeZone: ").append(toIndentedString(locationTimeZone)).append("\n");
        sb.append("    extents: ").append(toIndentedString(extents)).append("\n");
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
