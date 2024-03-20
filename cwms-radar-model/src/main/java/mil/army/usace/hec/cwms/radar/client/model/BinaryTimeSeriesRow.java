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
import javax.validation.Valid;

/**
 * BinaryTimeSeriesRow
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-03-19T14:26:29.428379500-07:00[America/Los_Angeles]")
public class BinaryTimeSeriesRow {
    @JsonProperty("date-time")
    private Instant dateTime = null;

    @JsonProperty("data-entry-date")
    private Instant dataEntryDate = null;

    @JsonProperty("media-type")
    private String mediaType = null;

    @JsonProperty("filename")
    private String filename = null;

    @JsonProperty("dest-flag")
    private Integer destFlag = null;

    @JsonProperty("binary-value")
    @Valid
    private byte[] binaryValue = null;

    @JsonProperty("value-url")
    private String valueUrl = null;

    @JsonProperty("quality-code")
    private Long qualityCode = null;

    public BinaryTimeSeriesRow dateTime(Instant dateTime) {
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

    public BinaryTimeSeriesRow dataEntryDate(Instant dataEntryDate) {
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

    public BinaryTimeSeriesRow mediaType(String mediaType) {
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

    public BinaryTimeSeriesRow filename(String filename) {
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

    public BinaryTimeSeriesRow destFlag(Integer destFlag) {
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

    public BinaryTimeSeriesRow binaryValue(byte[] binaryValue) {
        this.binaryValue = binaryValue;
        return this;
    }

    /**
     * Get binaryValue
     *
     * @return binaryValue
     **/

    public byte[] getBinaryValue() {
        return binaryValue;
    }

    public void setBinaryValue(byte[] binaryValue) {
        this.binaryValue = binaryValue;
    }

    public BinaryTimeSeriesRow valueUrl(String valueUrl) {
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

    public BinaryTimeSeriesRow qualityCode(Long qualityCode) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BinaryTimeSeriesRow binaryTimeSeriesRow = (BinaryTimeSeriesRow) o;
        return Objects.equals(this.dateTime, binaryTimeSeriesRow.dateTime)
                && Objects.equals(this.dataEntryDate, binaryTimeSeriesRow.dataEntryDate)
                && this.mediaType == null || binaryTimeSeriesRow.mediaType == null ? Objects.equals(this.mediaType, binaryTimeSeriesRow.mediaType) : this.mediaType.equalsIgnoreCase(binaryTimeSeriesRow.mediaType)
                && this.filename == null || binaryTimeSeriesRow.filename == null ? Objects.equals(this.filename, binaryTimeSeriesRow.filename) : this.filename.equalsIgnoreCase(binaryTimeSeriesRow.filename)
                && Objects.equals(this.destFlag, binaryTimeSeriesRow.destFlag)
                && Objects.equals(this.binaryValue, binaryTimeSeriesRow.binaryValue)
                && this.valueUrl == null || binaryTimeSeriesRow.valueUrl == null ? Objects.equals(this.valueUrl, binaryTimeSeriesRow.valueUrl) : this.valueUrl.equalsIgnoreCase(binaryTimeSeriesRow.valueUrl)
                && Objects.equals(this.qualityCode, binaryTimeSeriesRow.qualityCode)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, dataEntryDate, mediaType == null ? 0 : mediaType.toLowerCase(), filename == null ? 0 : filename.toLowerCase(), destFlag, binaryValue, valueUrl == null ? 0 : valueUrl.toLowerCase(), qualityCode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BinaryTimeSeriesRow {\n");

        sb.append("    dateTime: ").append(toIndentedString(dateTime)).append("\n");
        sb.append("    dataEntryDate: ").append(toIndentedString(dataEntryDate)).append("\n");
        sb.append("    mediaType: ").append(toIndentedString(mediaType)).append("\n");
        sb.append("    filename: ").append(toIndentedString(filename)).append("\n");
        sb.append("    destFlag: ").append(toIndentedString(destFlag)).append("\n");
        sb.append("    binaryValue: ").append(toIndentedString(binaryValue)).append("\n");
        sb.append("    valueUrl: ").append(toIndentedString(valueUrl)).append("\n");
        sb.append("    qualityCode: ").append(toIndentedString(qualityCode)).append("\n");
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
