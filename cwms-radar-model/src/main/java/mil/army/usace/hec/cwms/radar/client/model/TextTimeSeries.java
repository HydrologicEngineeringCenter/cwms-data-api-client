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
import java.time.Instant;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TextTimeSeries
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-03-18T13:18:54.786175600-07:00[America/Los_Angeles]")
public class TextTimeSeries {
    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("interval-offset")
    private Long intervalOffset = null;

    @JsonProperty("time-zone")
    private String timeZone = null;

    /**
     * Version type specifies the type of timeseries response to be received. Can be max aggregate or single version. Max aggregate cannot be run if version date field is specified.
     */
    public enum DateVersionTypeEnum {
        MAX_AGGREGATE("MAX_AGGREGATE"),

        SINGLE_VERSION("SINGLE_VERSION"),

        UNVERSIONED("UNVERSIONED");

        private String value;

        DateVersionTypeEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static DateVersionTypeEnum fromValue(String text) {
            for (DateVersionTypeEnum b : DateVersionTypeEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    @JsonProperty("date-version-type")
    private DateVersionTypeEnum dateVersionType = null;

    @JsonProperty("version-date")
    private Instant versionDate = null;

    @JsonProperty("regular-text-values")
    @Valid
    private List<RegularTextTimeSeriesRow> regularTextValues = new ArrayList<>();

    public TextTimeSeries officeId(String officeId) {
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

    public TextTimeSeries name(String name) {
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

    public TextTimeSeries intervalOffset(Long intervalOffset) {
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

    public TextTimeSeries timeZone(String timeZone) {
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

    public TextTimeSeries dateVersionType(DateVersionTypeEnum dateVersionType) {
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

    public TextTimeSeries versionDate(Instant versionDate) {
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

    public TextTimeSeries regularTextValues(List<RegularTextTimeSeriesRow> regularTextValues) {
        this.regularTextValues = regularTextValues;
        return this;
    }

    public TextTimeSeries addRegularTextValuesItem(RegularTextTimeSeriesRow regularTextValuesItem) {
        if (this.regularTextValues == null) {
            this.regularTextValues = new ArrayList<RegularTextTimeSeriesRow>();
        }
        this.regularTextValues.add(regularTextValuesItem);
        return this;
    }

    /**
     * Get regularTextValues
     *
     * @return regularTextValues
     **/
    @Valid
    public List<RegularTextTimeSeriesRow> getRegularTextValues() {
        return regularTextValues;
    }

    public void setRegularTextValues(List<RegularTextTimeSeriesRow> regularTextValues) {
        this.regularTextValues = regularTextValues;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TextTimeSeries textTimeSeries = (TextTimeSeries) o;
        return this.officeId == null || textTimeSeries.officeId == null ? Objects.equals(this.officeId, textTimeSeries.officeId) : this.officeId.equalsIgnoreCase(textTimeSeries.officeId)
                && this.name == null || textTimeSeries.name == null ? Objects.equals(this.name, textTimeSeries.name) : this.name.equalsIgnoreCase(textTimeSeries.name)
                && Objects.equals(this.intervalOffset, textTimeSeries.intervalOffset)
                && this.timeZone == null || textTimeSeries.timeZone == null ? Objects.equals(this.timeZone, textTimeSeries.timeZone) : this.timeZone.equalsIgnoreCase(textTimeSeries.timeZone)
                && this.dateVersionType == null || textTimeSeries.dateVersionType == null ? Objects.equals(this.dateVersionType, textTimeSeries.dateVersionType) : this.dateVersionType == textTimeSeries.dateVersionType
                && Objects.equals(this.versionDate, textTimeSeries.versionDate)
                && Objects.equals(this.regularTextValues, textTimeSeries.regularTextValues)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeId == null ? 0 : officeId.toLowerCase(), name == null ? 0 : name.toLowerCase(), intervalOffset, timeZone == null ? 0 : timeZone.toLowerCase(), dateVersionType, versionDate, regularTextValues);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TextTimeSeries {\n");

        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    intervalOffset: ").append(toIndentedString(intervalOffset)).append("\n");
        sb.append("    timeZone: ").append(toIndentedString(timeZone)).append("\n");
        sb.append("    dateVersionType: ").append(toIndentedString(dateVersionType)).append("\n");
        sb.append("    versionDate: ").append(toIndentedString(versionDate)).append("\n");
        sb.append("    regularTextValues: ").append(toIndentedString(regularTextValues)).append("\n");
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
