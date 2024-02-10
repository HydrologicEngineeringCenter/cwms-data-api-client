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

import javax.validation.Valid;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * RegularTextTimeSeriesRow
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-02-09T17:34:24.994233-08:00[America/Los_Angeles]")
public class RegularTextTimeSeriesRow {
    @JsonProperty("date-time")
    private ZonedDateTime dateTime = null;

    @JsonProperty("version-date")
    private ZonedDateTime versionDate = null;

    @JsonProperty("data-entry-date")
    private ZonedDateTime dataEntryDate = null;

    @JsonProperty("attribute")
    private Long attribute = null;

    @JsonProperty("text-id")
    private String textId = null;

    @JsonProperty("text-value")
    private String textValue = null;

    @JsonProperty("new-data")
    private Boolean newData = null;

    public RegularTextTimeSeriesRow dateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    /**
     * Get dateTime
     *
     * @return dateTime
     **/

    @Valid
    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public RegularTextTimeSeriesRow versionDate(ZonedDateTime versionDate) {
        this.versionDate = versionDate;
        return this;
    }

    /**
     * Get versionDate
     *
     * @return versionDate
     **/

    @Valid
    public ZonedDateTime getVersionDate() {
        return versionDate;
    }

    public void setVersionDate(ZonedDateTime versionDate) {
        this.versionDate = versionDate;
    }

    public RegularTextTimeSeriesRow dataEntryDate(ZonedDateTime dataEntryDate) {
        this.dataEntryDate = dataEntryDate;
        return this;
    }

    /**
     * Get dataEntryDate
     *
     * @return dataEntryDate
     **/

    @Valid
    public ZonedDateTime getDataEntryDate() {
        return dataEntryDate;
    }

    public void setDataEntryDate(ZonedDateTime dataEntryDate) {
        this.dataEntryDate = dataEntryDate;
    }

    public RegularTextTimeSeriesRow attribute(Long attribute) {
        this.attribute = attribute;
        return this;
    }

    /**
     * Get attribute
     *
     * @return attribute
     **/

    public Long getAttribute() {
        return attribute;
    }

    public void setAttribute(Long attribute) {
        this.attribute = attribute;
    }

    public RegularTextTimeSeriesRow textId(String textId) {
        this.textId = textId;
        return this;
    }

    /**
     * Get textId
     *
     * @return textId
     **/

    public String getTextId() {
        return textId;
    }

    public void setTextId(String textId) {
        this.textId = textId;
    }

    public RegularTextTimeSeriesRow textValue(String textValue) {
        this.textValue = textValue;
        return this;
    }

    /**
     * Get textValue
     *
     * @return textValue
     **/

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public RegularTextTimeSeriesRow newData(Boolean newData) {
        this.newData = newData;
        return this;
    }

    /**
     * Get newData
     *
     * @return newData
     **/

    public Boolean isisNewData() {
        return newData;
    }

    public void setNewData(Boolean newData) {
        this.newData = newData;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RegularTextTimeSeriesRow regularTextTimeSeriesRow = (RegularTextTimeSeriesRow) o;
        return Objects.equals(this.dateTime, regularTextTimeSeriesRow.dateTime)
                && Objects.equals(this.versionDate, regularTextTimeSeriesRow.versionDate)
                && Objects.equals(this.dataEntryDate, regularTextTimeSeriesRow.dataEntryDate)
                && Objects.equals(this.attribute, regularTextTimeSeriesRow.attribute)
                && this.textId == null || regularTextTimeSeriesRow.textId == null ? Objects.equals(this.textId, regularTextTimeSeriesRow.textId) : this.textId.equalsIgnoreCase(regularTextTimeSeriesRow.textId)
                && this.textValue == null || regularTextTimeSeriesRow.textValue == null ? Objects.equals(this.textValue, regularTextTimeSeriesRow.textValue) : this.textValue.equalsIgnoreCase(regularTextTimeSeriesRow.textValue)
                && Objects.equals(this.newData, regularTextTimeSeriesRow.newData)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, versionDate, dataEntryDate, attribute, textId == null ? 0 : textId.toLowerCase(), textValue == null ? 0 : textValue.toLowerCase(), newData);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RegularTextTimeSeriesRow {\n");

        sb.append("    dateTime: ").append(toIndentedString(dateTime)).append("\n");
        sb.append("    versionDate: ").append(toIndentedString(versionDate)).append("\n");
        sb.append("    dataEntryDate: ").append(toIndentedString(dataEntryDate)).append("\n");
        sb.append("    attribute: ").append(toIndentedString(attribute)).append("\n");
        sb.append("    textId: ").append(toIndentedString(textId)).append("\n");
        sb.append("    textValue: ").append(toIndentedString(textValue)).append("\n");
        sb.append("    newData: ").append(toIndentedString(newData)).append("\n");
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
