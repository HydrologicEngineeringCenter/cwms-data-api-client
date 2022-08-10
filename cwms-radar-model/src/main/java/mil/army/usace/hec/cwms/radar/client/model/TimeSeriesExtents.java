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
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.validation.Valid;

/**
 * TimeSeries extent information
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-09T09:29:33.859-07:00[America/Los_Angeles]")
public class TimeSeriesExtents {
    @JsonProperty("earliest-time")
    private ZonedDateTime earliestTime = null;

    @JsonProperty("latest-time")
    private ZonedDateTime latestTime = null;

    @JsonProperty("version-time")
    private ZonedDateTime versionTime = null;

    public TimeSeriesExtents earliestTime(ZonedDateTime earliestTime) {
        this.earliestTime = earliestTime;
        return this;
    }

    /**
     * Earliest value in the timeseries
     *
     * @return earliestTime
     **/

    @Valid
    public ZonedDateTime getEarliestTime() {
        return earliestTime;
    }

    public void setEarliestTime(ZonedDateTime earliestTime) {
        this.earliestTime = earliestTime;
    }

    public TimeSeriesExtents latestTime(ZonedDateTime latestTime) {
        this.latestTime = latestTime;
        return this;
    }

    /**
     * Latest value in the timeseries
     *
     * @return latestTime
     **/

    @Valid
    public ZonedDateTime getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(ZonedDateTime latestTime) {
        this.latestTime = latestTime;
    }

    public TimeSeriesExtents versionTime(ZonedDateTime versionTime) {
        this.versionTime = versionTime;
        return this;
    }

    /**
     * TimeSeries version to which this extent information applies
     *
     * @return versionTime
     **/

    @Valid
    public ZonedDateTime getVersionTime() {
        return versionTime;
    }

    public void setVersionTime(ZonedDateTime versionTime) {
        this.versionTime = versionTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSeriesExtents timeSeriesExtents = (TimeSeriesExtents) o;
        return Objects.equals(this.earliestTime, timeSeriesExtents.earliestTime) && Objects.equals(this.latestTime, timeSeriesExtents.latestTime) &&
            Objects.equals(this.versionTime, timeSeriesExtents.versionTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(earliestTime, latestTime, versionTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesExtents {\n");

        sb.append("    earliestTime: ").append(toIndentedString(earliestTime)).append("\n");
        sb.append("    latestTime: ").append(toIndentedString(latestTime)).append("\n");
        sb.append("    versionTime: ").append(toIndentedString(versionTime)).append("\n");
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
