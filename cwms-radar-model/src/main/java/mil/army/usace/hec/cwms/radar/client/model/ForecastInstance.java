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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * ForecastInstance
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-04-18T23:14:24.845196800-07:00[America/Los_Angeles]")
public class ForecastInstance {
    @JsonProperty("spec")
    private ForecastSpec spec = null;

    @JsonProperty("date-time")
    private Instant dateTime = null;

    @JsonProperty("issue-date-time")
    private Instant issueDateTime = null;

    @JsonProperty("first-date-time")
    private Instant firstDateTime = null;

    @JsonProperty("last-date-time")
    private Instant lastDateTime = null;

    @JsonProperty("max-age")
    private Integer maxAge = null;

    @JsonProperty("notes")
    private String notes = null;

    @JsonProperty("metadata")
    @Valid
    private Map<String, String> metadata = null;

    @JsonProperty("filename")
    private String filename = null;

    @JsonProperty("file-description")
    private String fileDescription = null;

    @JsonProperty("file-media-type")
    private String fileMediaType = null;

    @JsonProperty("file-data")
    @Valid
    private byte[] fileData = null;

    @JsonProperty("file-data-url")
    private String fileDataUrl = null;

    public ForecastInstance spec(ForecastSpec spec) {
        this.spec = spec;
        return this;
    }

    /**
     * Get spec
     *
     * @return spec
     **/

    @Valid
    public ForecastSpec getSpec() {
        return spec;
    }

    public void setSpec(ForecastSpec spec) {
        this.spec = spec;
    }

    public ForecastInstance dateTime(Instant dateTime) {
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

    public ForecastInstance issueDateTime(Instant issueDateTime) {
        this.issueDateTime = issueDateTime;
        return this;
    }

    /**
     * Get issueDateTime
     *
     * @return issueDateTime
     **/

    @Valid
    public Instant getIssueDateTime() {
        return issueDateTime;
    }

    public void setIssueDateTime(Instant issueDateTime) {
        this.issueDateTime = issueDateTime;
    }

    public ForecastInstance firstDateTime(Instant firstDateTime) {
        this.firstDateTime = firstDateTime;
        return this;
    }

    /**
     * Get firstDateTime
     *
     * @return firstDateTime
     **/

    @Valid
    public Instant getFirstDateTime() {
        return firstDateTime;
    }

    public void setFirstDateTime(Instant firstDateTime) {
        this.firstDateTime = firstDateTime;
    }

    public ForecastInstance lastDateTime(Instant lastDateTime) {
        this.lastDateTime = lastDateTime;
        return this;
    }

    /**
     * Get lastDateTime
     *
     * @return lastDateTime
     **/

    @Valid
    public Instant getLastDateTime() {
        return lastDateTime;
    }

    public void setLastDateTime(Instant lastDateTime) {
        this.lastDateTime = lastDateTime;
    }

    public ForecastInstance maxAge(Integer maxAge) {
        this.maxAge = maxAge;
        return this;
    }

    /**
     * Get maxAge
     *
     * @return maxAge
     **/

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public ForecastInstance notes(String notes) {
        this.notes = notes;
        return this;
    }

    /**
     * Forecast Instance Notes
     *
     * @return notes
     **/

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ForecastInstance metadata(Map<String, String> metadata) {
        this.metadata = metadata;
        return this;
    }

    public ForecastInstance putMetadataItem(String key, String metadataItem) {
        if (this.metadata == null) {
            this.metadata = new HashMap<String, String>();
        }
        this.metadata.put(key, metadataItem);
        return this;
    }

    /**
     * Get metadata
     *
     * @return metadata
     **/

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public ForecastInstance filename(String filename) {
        this.filename = filename;
        return this;
    }

    /**
     * Forecast Filename
     *
     * @return filename
     **/

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public ForecastInstance fileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
        return this;
    }

    /**
     * Description of Forecast File
     *
     * @return fileDescription
     **/

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public ForecastInstance fileMediaType(String fileMediaType) {
        this.fileMediaType = fileMediaType;
        return this;
    }

    /**
     * Forecast File Media Type
     *
     * @return fileMediaType
     **/

    public String getFileMediaType() {
        return fileMediaType;
    }

    public void setFileMediaType(String fileMediaType) {
        this.fileMediaType = fileMediaType;
    }

    public ForecastInstance fileData(byte[] fileData) {
        this.fileData = fileData;
        return this;
    }

    /**
     * Forecast File binary data
     *
     * @return fileData
     **/

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public ForecastInstance fileDataUrl(String fileDataUrl) {
        this.fileDataUrl = fileDataUrl;
        return this;
    }

    /**
     * Link to Forecast File binary data
     *
     * @return fileDataUrl
     **/

    public String getFileDataUrl() {
        return fileDataUrl;
    }

    public void setFileDataUrl(String fileDataUrl) {
        this.fileDataUrl = fileDataUrl;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ForecastInstance forecastInstance = (ForecastInstance) o;
        return Objects.equals(this.spec, forecastInstance.spec)
                && Objects.equals(this.dateTime, forecastInstance.dateTime)
                && Objects.equals(this.issueDateTime, forecastInstance.issueDateTime)
                && Objects.equals(this.firstDateTime, forecastInstance.firstDateTime)
                && Objects.equals(this.lastDateTime, forecastInstance.lastDateTime)
                && Objects.equals(this.maxAge, forecastInstance.maxAge)
                && this.notes == null || forecastInstance.notes == null ? Objects.equals(this.notes, forecastInstance.notes) : this.notes.equalsIgnoreCase(forecastInstance.notes)
                && Objects.equals(this.metadata, forecastInstance.metadata)
                && this.filename == null || forecastInstance.filename == null ? Objects.equals(this.filename, forecastInstance.filename) : this.filename.equalsIgnoreCase(forecastInstance.filename)
                && this.fileDescription == null || forecastInstance.fileDescription == null ? Objects.equals(this.fileDescription, forecastInstance.fileDescription) : this.fileDescription.equalsIgnoreCase(forecastInstance.fileDescription)
                && this.fileMediaType == null || forecastInstance.fileMediaType == null ? Objects.equals(this.fileMediaType, forecastInstance.fileMediaType) : this.fileMediaType.equalsIgnoreCase(forecastInstance.fileMediaType)
                && Arrays.equals(this.fileData, forecastInstance.fileData)
                && this.fileDataUrl == null || forecastInstance.fileDataUrl == null ? Objects.equals(this.fileDataUrl, forecastInstance.fileDataUrl) : this.fileDataUrl.equalsIgnoreCase(forecastInstance.fileDataUrl)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(spec, dateTime, issueDateTime, firstDateTime, lastDateTime, maxAge, notes == null ? 0 : notes.toLowerCase(), metadata, filename == null ? 0 : filename.toLowerCase(), fileDescription == null ? 0 : fileDescription.toLowerCase(), fileMediaType == null ? 0 : fileMediaType.toLowerCase(), fileData, fileDataUrl == null ? 0 : fileDataUrl.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ForecastInstance {\n");

        sb.append("    spec: ").append(toIndentedString(spec)).append("\n");
        sb.append("    dateTime: ").append(toIndentedString(dateTime)).append("\n");
        sb.append("    issueDateTime: ").append(toIndentedString(issueDateTime)).append("\n");
        sb.append("    firstDateTime: ").append(toIndentedString(firstDateTime)).append("\n");
        sb.append("    lastDateTime: ").append(toIndentedString(lastDateTime)).append("\n");
        sb.append("    maxAge: ").append(toIndentedString(maxAge)).append("\n");
        sb.append("    notes: ").append(toIndentedString(notes)).append("\n");
        sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
        sb.append("    filename: ").append(toIndentedString(filename)).append("\n");
        sb.append("    fileDescription: ").append(toIndentedString(fileDescription)).append("\n");
        sb.append("    fileMediaType: ").append(toIndentedString(fileMediaType)).append("\n");
        sb.append("    fileData: ").append(toIndentedString(fileData)).append("\n");
        sb.append("    fileDataUrl: ").append(toIndentedString(fileDataUrl)).append("\n");
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
