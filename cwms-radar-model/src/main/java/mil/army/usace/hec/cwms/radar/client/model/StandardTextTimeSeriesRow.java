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
 * StandardTextTimeSeriesRow
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-02-13T10:48:01.962877200-08:00[America/Los_Angeles]")
public class StandardTextTimeSeriesRow {
    @JsonProperty("date-time")
    private ZonedDateTime dateTime = null;

    @JsonProperty("version-date")
    private ZonedDateTime versionDate = null;

    @JsonProperty("data-entry-date")
    private ZonedDateTime dataEntryDate = null;

    @JsonProperty("attribute")
    private Long attribute = null;

    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("standard-text-id")
    private String standardTextId = null;

    @JsonProperty("text-value")
    private String textValue = null;

    public StandardTextTimeSeriesRow dateTime(ZonedDateTime dateTime) {
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

    public StandardTextTimeSeriesRow versionDate(ZonedDateTime versionDate) {
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

    public StandardTextTimeSeriesRow dataEntryDate(ZonedDateTime dataEntryDate) {
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

    public StandardTextTimeSeriesRow attribute(Long attribute) {
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

    public StandardTextTimeSeriesRow officeId(String officeId) {
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

    public StandardTextTimeSeriesRow standardTextId(String standardTextId) {
        this.standardTextId = standardTextId;
        return this;
    }

    /**
     * Get standardTextId
     *
     * @return standardTextId
     **/

    public String getStandardTextId() {
        return standardTextId;
    }

    public void setStandardTextId(String standardTextId) {
        this.standardTextId = standardTextId;
    }

    public StandardTextTimeSeriesRow textValue(String textValue) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StandardTextTimeSeriesRow standardTextTimeSeriesRow = (StandardTextTimeSeriesRow) o;
        return Objects.equals(this.dateTime, standardTextTimeSeriesRow.dateTime)
                && Objects.equals(this.versionDate, standardTextTimeSeriesRow.versionDate)
                && Objects.equals(this.dataEntryDate, standardTextTimeSeriesRow.dataEntryDate)
                && Objects.equals(this.attribute, standardTextTimeSeriesRow.attribute)
                && this.officeId == null || standardTextTimeSeriesRow.officeId == null ? Objects.equals(this.officeId, standardTextTimeSeriesRow.officeId) : this.officeId.equalsIgnoreCase(standardTextTimeSeriesRow.officeId)
                && this.standardTextId == null || standardTextTimeSeriesRow.standardTextId == null ? Objects.equals(this.standardTextId, standardTextTimeSeriesRow.standardTextId) : this.standardTextId.equalsIgnoreCase(standardTextTimeSeriesRow.standardTextId)
                && this.textValue == null || standardTextTimeSeriesRow.textValue == null ? Objects.equals(this.textValue, standardTextTimeSeriesRow.textValue) : this.textValue.equalsIgnoreCase(standardTextTimeSeriesRow.textValue)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, versionDate, dataEntryDate, attribute, officeId == null ? 0 : officeId.toLowerCase(), standardTextId == null ? 0 : standardTextId.toLowerCase(), textValue == null ? 0 : textValue.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StandardTextTimeSeriesRow {\n");

        sb.append("    dateTime: ").append(toIndentedString(dateTime)).append("\n");
        sb.append("    versionDate: ").append(toIndentedString(versionDate)).append("\n");
        sb.append("    dataEntryDate: ").append(toIndentedString(dataEntryDate)).append("\n");
        sb.append("    attribute: ").append(toIndentedString(attribute)).append("\n");
        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    standardTextId: ").append(toIndentedString(standardTextId)).append("\n");
        sb.append("    textValue: ").append(toIndentedString(textValue)).append("\n");
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
