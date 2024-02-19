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
 * BinaryTimeSeriesRow
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-02-16T17:17:17.680135500-08:00[America/Los_Angeles]")
public class BinaryTimeSeriesRow {
    @JsonProperty("date-time")
    private ZonedDateTime dateTime = null;

    @JsonProperty("version-date")
    private ZonedDateTime versionDate = null;

    @JsonProperty("data-entry-date")
    private ZonedDateTime dataEntryDate = null;

    @JsonProperty("binary-id")
    private String binaryId = null;

    @JsonProperty("attribute")
    private Long attribute = null;

    @JsonProperty("media-type")
    private String mediaType = null;

    @JsonProperty("file-extension")
    private String fileExtension = null;

    @JsonProperty("binary-value")
    @Valid
    private byte[] binaryValue;

    public BinaryTimeSeriesRow dateTime(ZonedDateTime dateTime) {
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

    public BinaryTimeSeriesRow versionDate(ZonedDateTime versionDate) {
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

    public BinaryTimeSeriesRow dataEntryDate(ZonedDateTime dataEntryDate) {
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

    public BinaryTimeSeriesRow binaryId(String binaryId) {
        this.binaryId = binaryId;
        return this;
    }

    /**
     * Get binaryId
     *
     * @return binaryId
     **/

    public String getBinaryId() {
        return binaryId;
    }

    public void setBinaryId(String binaryId) {
        this.binaryId = binaryId;
    }

    public BinaryTimeSeriesRow attribute(Long attribute) {
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

    public BinaryTimeSeriesRow fileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
        return this;
    }

    /**
     * Get fileExtension
     *
     * @return fileExtension
     **/

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
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
                && Objects.equals(this.versionDate, binaryTimeSeriesRow.versionDate)
                && Objects.equals(this.dataEntryDate, binaryTimeSeriesRow.dataEntryDate)
                && this.binaryId == null || binaryTimeSeriesRow.binaryId == null ? Objects.equals(this.binaryId, binaryTimeSeriesRow.binaryId) : this.binaryId.equalsIgnoreCase(binaryTimeSeriesRow.binaryId)
                && Objects.equals(this.attribute, binaryTimeSeriesRow.attribute)
                && this.mediaType == null || binaryTimeSeriesRow.mediaType == null ? Objects.equals(this.mediaType, binaryTimeSeriesRow.mediaType) : this.mediaType.equalsIgnoreCase(binaryTimeSeriesRow.mediaType)
                && this.fileExtension == null || binaryTimeSeriesRow.fileExtension == null ? Objects.equals(this.fileExtension, binaryTimeSeriesRow.fileExtension) : this.fileExtension.equalsIgnoreCase(binaryTimeSeriesRow.fileExtension)
                && Objects.equals(this.binaryValue, binaryTimeSeriesRow.binaryValue)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, versionDate, dataEntryDate, binaryId == null ? 0 : binaryId.toLowerCase(), attribute, mediaType == null ? 0 : mediaType.toLowerCase(), fileExtension == null ? 0 : fileExtension.toLowerCase(), binaryValue);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BinaryTimeSeriesRow {\n");

        sb.append("    dateTime: ").append(toIndentedString(dateTime)).append("\n");
        sb.append("    versionDate: ").append(toIndentedString(versionDate)).append("\n");
        sb.append("    dataEntryDate: ").append(toIndentedString(dataEntryDate)).append("\n");
        sb.append("    binaryId: ").append(toIndentedString(binaryId)).append("\n");
        sb.append("    attribute: ").append(toIndentedString(attribute)).append("\n");
        sb.append("    mediaType: ").append(toIndentedString(mediaType)).append("\n");
        sb.append("    fileExtension: ").append(toIndentedString(fileExtension)).append("\n");
        sb.append("    binaryValue: ").append(toIndentedString(binaryValue)).append("\n");
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
