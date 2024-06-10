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

import java.time.Instant;
import java.util.Objects;

/**
 * RegularTextTimeSeriesRow
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-03-18T13:18:54.786175600-07:00[America/Los_Angeles]")
public class RegularTextTimeSeriesRow {
    @JsonProperty("date-time")
    private Instant dateTime = null;

    @JsonProperty("data-entry-date")
    private Instant dataEntryDate = null;

    @JsonProperty("text-value")
    private String textValue = null;

    @JsonProperty("filename")
    private String filename = null;

    @JsonProperty("media-type")
    private String mediaType = null;

    @JsonProperty("quality-code")
    private Long qualityCode = null;

    @JsonProperty("dest-flag")
    private Integer destFlag = null;

    @JsonProperty("value-url")
    private String valueUrl = null;

    public RegularTextTimeSeriesRow dateTime(Instant dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    /**
     * Get dateTime
     *
     * @return dateTime
     **/

    @Valid
    public Instant getDateTime() {
        return dateTime;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }

    public RegularTextTimeSeriesRow dataEntryDate(Instant dataEntryDate) {
        this.dataEntryDate = dataEntryDate;
        return this;
    }

    /**
     * Get dataEntryDate
     *
     * @return dataEntryDate
     **/

    @Valid
    public Instant getDataEntryDate() {
        return dataEntryDate;
    }

    public void setDataEntryDate(Instant dataEntryDate) {
        this.dataEntryDate = dataEntryDate;
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

    public RegularTextTimeSeriesRow filename(String filename) {
        this.filename = filename;
        return this;
    }

    /**
     * Get filename
     *
     * @return filename
     **/

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public RegularTextTimeSeriesRow mediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    /**
     * Get mediaType
     *
     * @return mediaType
     **/

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public RegularTextTimeSeriesRow qualityCode(Long qualityCode) {
        this.qualityCode = qualityCode;
        return this;
    }

    /**
     * Get qualityCode
     *
     * @return qualityCode
     **/

    public Long getQualityCode() {
        return qualityCode;
    }

    public void setQualityCode(Long qualityCode) {
        this.qualityCode = qualityCode;
    }

    public RegularTextTimeSeriesRow destFlag(Integer destFlag) {
        this.destFlag = destFlag;
        return this;
    }

    /**
     * Get destFlag
     *
     * @return destFlag
     **/

    public Integer getDestFlag() {
        return destFlag;
    }

    public void setDestFlag(Integer destFlag) {
        this.destFlag = destFlag;
    }

    public RegularTextTimeSeriesRow valueUrl(String valueUrl) {
        this.valueUrl = valueUrl;
        return this;
    }

    /**
     * Get valueUrl
     *
     * @return valueUrl
     **/

    public String getValueUrl() {
        return valueUrl;
    }

    public void setValueUrl(String valueUrl) {
        this.valueUrl = valueUrl;
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
                && Objects.equals(this.dataEntryDate, regularTextTimeSeriesRow.dataEntryDate)
                && this.textValue == null || regularTextTimeSeriesRow.textValue == null ? Objects.equals(this.textValue, regularTextTimeSeriesRow.textValue) : this.textValue.equalsIgnoreCase(regularTextTimeSeriesRow.textValue)
                && this.filename == null || regularTextTimeSeriesRow.filename == null ? Objects.equals(this.filename, regularTextTimeSeriesRow.filename) : this.filename.equalsIgnoreCase(regularTextTimeSeriesRow.filename)
                && this.mediaType == null || regularTextTimeSeriesRow.mediaType == null ? Objects.equals(this.mediaType, regularTextTimeSeriesRow.mediaType) : this.mediaType.equalsIgnoreCase(regularTextTimeSeriesRow.mediaType)
                && Objects.equals(this.qualityCode, regularTextTimeSeriesRow.qualityCode)
                && Objects.equals(this.destFlag, regularTextTimeSeriesRow.destFlag)
                && this.valueUrl == null || regularTextTimeSeriesRow.valueUrl == null ? Objects.equals(this.valueUrl, regularTextTimeSeriesRow.valueUrl) : this.valueUrl.equalsIgnoreCase(regularTextTimeSeriesRow.valueUrl)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, dataEntryDate, textValue == null ? 0 : textValue.toLowerCase(), filename == null ? 0 : filename.toLowerCase(), mediaType == null ? 0 : mediaType.toLowerCase(), qualityCode, destFlag, valueUrl == null ? 0 : valueUrl.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RegularTextTimeSeriesRow {\n");

        sb.append("    dateTime: ").append(toIndentedString(dateTime)).append("\n");
        sb.append("    dataEntryDate: ").append(toIndentedString(dataEntryDate)).append("\n");
        sb.append("    textValue: ").append(toIndentedString(textValue)).append("\n");
        sb.append("    filename: ").append(toIndentedString(filename)).append("\n");
        sb.append("    mediaType: ").append(toIndentedString(mediaType)).append("\n");
        sb.append("    qualityCode: ").append(toIndentedString(qualityCode)).append("\n");
        sb.append("    destFlag: ").append(toIndentedString(destFlag)).append("\n");
        sb.append("    valueUrl: ").append(toIndentedString(valueUrl)).append("\n");
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
