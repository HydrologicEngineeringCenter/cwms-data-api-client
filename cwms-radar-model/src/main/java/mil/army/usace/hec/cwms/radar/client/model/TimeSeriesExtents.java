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

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * TimeSeries extent information
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-10-14T13:21:38.937-07:00[America/Los_Angeles]")
public class TimeSeriesExtents extends TimeExtents {
    @JsonProperty("last-update")
    private ZonedDateTime lastUpdate = null;

    @JsonProperty("version-time")
    private ZonedDateTime versionTime = null;

    public TimeSeriesExtents lastUpdate(ZonedDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }

    /**
     * Last update in the timeseries
     *
     * @return lastUpdate
     **/

    @Valid
    public ZonedDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(ZonedDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
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
        return super.equals(o)
            && Objects.equals(this.lastUpdate, timeSeriesExtents.lastUpdate)
            && Objects.equals(this.versionTime, timeSeriesExtents.versionTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lastUpdate, versionTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesExtents {\n");

        sb.append("    earliestTime: ").append(toIndentedString(getEarliestTime())).append("\n");
        sb.append("    lastUpdate: ").append(toIndentedString(lastUpdate)).append("\n");
        sb.append("    latestTime: ").append(toIndentedString(getLatestTime())).append("\n");
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
