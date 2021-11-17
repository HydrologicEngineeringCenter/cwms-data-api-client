/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.annotation.Generated;

/**
 * CatalogEntry
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class TimeSeriesCatalogEntry {
    @JsonProperty("office")
    private String office = null;

    @JsonProperty("name")
    private String timeSeriesId = null;

    @JsonProperty("units")
    private String units = null;

    @JsonProperty("time-zone")
    private String locationTimeZone;

    @JsonProperty("interval-offset")
    private Integer intervalOffsetMinutes;

    @JsonProperty("earliest-time")
    private ZonedDateTime earliestTime;

    @JsonProperty("latest-time")
    private ZonedDateTime latestTime;

    /**
     * Get office
     *
     * @return office
     **/
    public String getOffice() {
        return office;
    }

    /**
     * Get tsName
     *
     * @return tsName
     **/
    public String getTimeSeriesId() {
        return timeSeriesId;
    }

    /**
     * Get units
     *
     * @return units
     **/
    public String getUnits() {
        return units;
    }


    /**
     * Get locationTimeZone
     *
     * @return locationTimeZone
     **/
    public String getLocationTimeZone() {
        return locationTimeZone;
    }


    /**
     * Get intervalOffsetMinutes
     *
     * @return intervalOffsetMinutes
     **/
    public Integer getIntervalOffsetMinutes() {
        return intervalOffsetMinutes;
    }


    /**
     * Get earliestTime
     *
     * @return earliestTime
     **/
    public ZonedDateTime getEarliestTime() {
        return earliestTime;
    }


    /**
     * Get locationTimeZone
     *
     * @return locationTimeZone
     **/
    public ZonedDateTime getLatestTime() {
        return latestTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSeriesCatalogEntry catalogEntry = (TimeSeriesCatalogEntry) o;
        return Objects.equals(this.office, catalogEntry.office)
            && Objects.equals(this.timeSeriesId, catalogEntry.timeSeriesId)
            && Objects.equals(this.units, catalogEntry.units)
            && Objects.equals(this.locationTimeZone, catalogEntry.locationTimeZone)
            && Objects.equals(this.intervalOffsetMinutes, catalogEntry.intervalOffsetMinutes)
            && Objects.equals(this.earliestTime, catalogEntry.earliestTime)
            && Objects.equals(this.latestTime, catalogEntry.latestTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(office, timeSeriesId, units, locationTimeZone, intervalOffsetMinutes, earliestTime, latestTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CatalogEntry {\n");

        sb.append("    office: ").append(toIndentedString(office)).append("\n");
        sb.append("    timeSeriesId: ").append(toIndentedString(timeSeriesId)).append("\n");
        sb.append("    units: ").append(toIndentedString(units)).append("\n");
        sb.append("    locationTimeZone: ").append(toIndentedString(locationTimeZone)).append("\n");
        sb.append("    intervalOffsetMinutes: ").append(toIndentedString(intervalOffsetMinutes)).append("\n");
        sb.append("    earliestTime: ").append(toIndentedString(earliestTime)).append("\n");
        sb.append("    latestTime: ").append(toIndentedString(latestTime)).append("\n");
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