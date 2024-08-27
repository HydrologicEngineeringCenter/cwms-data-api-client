package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * TimeSeriesProfileParserIndexed
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-08-23T10:53:54.959292200-07:00[America/Los_Angeles]")
public class TimeSeriesProfileParserIndexed extends TimeSeriesProfileParser {

    @JsonProperty("location-id")
    private CwmsId locationId = null;

    @JsonProperty("key-parameter")
    private String keyParameter = null;

    @JsonProperty("record-delimiter")
    private char recordDelimiter = '\0';

    @JsonProperty("time-format")
    private String timeFormat = null;

    @JsonProperty("time-zone")
    private String timeZone = null;

    @JsonProperty("parameter-info-list")
    @Valid
    private List<ParameterInfo> parameterInfoList = new ArrayList<>();

    @JsonProperty("time-in-two-fields")
    private Boolean timeInTwoFields = null;

    @JsonProperty("field-delimiter")
    private String fieldDelimiter = null;

    @JsonProperty("time-field")
    private Integer timeField = null;

    public TimeSeriesProfileParserIndexed locationId(CwmsId locationId) {
        this.locationId = locationId;
        return this;
    }

    public CwmsId getLocationId() {
        return locationId;
    }

    public void setLocationId(CwmsId locationId) {
        this.locationId = locationId;
    }

    public TimeSeriesProfileParserIndexed keyParameter(String keyParameter) {
        this.keyParameter = keyParameter;
        return this;
    }

    public String getKeyParameter() {
        return keyParameter;
    }

    public void setKeyParameter(String keyParameter) {
        this.keyParameter = keyParameter;
    }

    public TimeSeriesProfileParserIndexed recordDelimiter(char recordDelimiter) {
        this.recordDelimiter = recordDelimiter;
        return this;
    }

    public char getRecordDelimiter() {
        return recordDelimiter;
    }

    public void setRecordDelimiter(char recordDelimiter) {
        this.recordDelimiter = recordDelimiter;
    }

    public TimeSeriesProfileParserIndexed timeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
        return this;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public TimeSeriesProfileParserIndexed timeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public TimeSeriesProfileParserIndexed parameterInfoList(List<ParameterInfo> parameterInfoList) {
        this.parameterInfoList = parameterInfoList;
        return this;
    }

    public TimeSeriesProfileParserIndexed addParameterInfoListItem(ParameterInfo parameterInfoListItem) {
        if (this.parameterInfoList == null) {
            this.parameterInfoList = new ArrayList<>();
        }
        this.parameterInfoList.add(parameterInfoListItem);
        return this;
    }

    public List<ParameterInfo> getParameterInfoList() {
        return parameterInfoList;
    }

    public void setParameterInfoList(List<ParameterInfo> parameterInfoList) {
        this.parameterInfoList = parameterInfoList;
    }

    public TimeSeriesProfileParserIndexed timeInTwoFields(Boolean timeInTwoFields) {
        this.timeInTwoFields = timeInTwoFields;
        return this;
    }

    public Boolean isTimeInTwoFields() {
        return timeInTwoFields;
    }

    public void setTimeInTwoFields(Boolean timeInTwoFields) {
        this.timeInTwoFields = timeInTwoFields;
    }

    public TimeSeriesProfileParserIndexed fieldDelimiter(String fieldDelimiter) {
        this.fieldDelimiter = fieldDelimiter;
        return this;
    }

    public String getFieldDelimiter() {
        return fieldDelimiter;
    }

    public void setFieldDelimiter(String fieldDelimiter) {
        this.fieldDelimiter = fieldDelimiter;
    }

    public TimeSeriesProfileParserIndexed timeField(Integer timeField) {
        this.timeField = timeField;
        return this;
    }

    public Integer getTimeField() {
        return timeField;
    }

    public void setTimeField(Integer timeField) {
        this.timeField = timeField;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSeriesProfileParserIndexed timeSeriesProfileParserIndexed = (TimeSeriesProfileParserIndexed) o;
        return Objects.equals(this.locationId, timeSeriesProfileParserIndexed.locationId)
                && this.keyParameter == null || timeSeriesProfileParserIndexed.keyParameter == null
                ? Objects.equals(this.keyParameter, timeSeriesProfileParserIndexed.keyParameter)
                : this.keyParameter.equalsIgnoreCase(timeSeriesProfileParserIndexed.keyParameter)
                && this.recordDelimiter == '\0' || timeSeriesProfileParserIndexed.recordDelimiter == '\0'
                ? Objects.equals(this.recordDelimiter, timeSeriesProfileParserIndexed.recordDelimiter)
                : this.recordDelimiter == timeSeriesProfileParserIndexed.recordDelimiter
                && this.timeFormat == null || timeSeriesProfileParserIndexed.timeFormat == null
                ? Objects.equals(this.timeFormat, timeSeriesProfileParserIndexed.timeFormat)
                : this.timeFormat.equalsIgnoreCase(timeSeriesProfileParserIndexed.timeFormat)
                && this.timeZone == null || timeSeriesProfileParserIndexed.timeZone == null
                ? Objects.equals(this.timeZone, timeSeriesProfileParserIndexed.timeZone)
                : this.timeZone.equalsIgnoreCase(timeSeriesProfileParserIndexed.timeZone)
                && Objects.equals(this.parameterInfoList, timeSeriesProfileParserIndexed.parameterInfoList)
                && Objects.equals(this.timeInTwoFields, timeSeriesProfileParserIndexed.timeInTwoFields)
                && this.fieldDelimiter == null || timeSeriesProfileParserIndexed.fieldDelimiter == null
                ? Objects.equals(this.fieldDelimiter, timeSeriesProfileParserIndexed.fieldDelimiter)
                : this.fieldDelimiter.equalsIgnoreCase(timeSeriesProfileParserIndexed.fieldDelimiter)
                && Objects.equals(this.timeField, timeSeriesProfileParserIndexed.timeField)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, keyParameter == null ? 0 : keyParameter.toLowerCase(),
                recordDelimiter == '\0' ? 0 : recordDelimiter,
                timeFormat == null ? 0 : timeFormat.toLowerCase(),
                timeZone == null ? 0 : timeZone.toLowerCase(), parameterInfoList, timeInTwoFields,
                fieldDelimiter == null ? 0 : fieldDelimiter.toLowerCase(), timeField);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesProfileParserIndexed {\n");

        sb.append("    locationId: ").append(toIndentedString(locationId)).append("\n");
        sb.append("    keyParameter: ").append(toIndentedString(keyParameter)).append("\n");
        sb.append("    recordDelimiter: ").append(toIndentedString(recordDelimiter)).append("\n");
        sb.append("    timeFormat: ").append(toIndentedString(timeFormat)).append("\n");
        sb.append("    timeZone: ").append(toIndentedString(timeZone)).append("\n");
        sb.append("    parameterInfoList: ").append(toIndentedString(parameterInfoList)).append("\n");
        sb.append("    timeInTwoFields: ").append(toIndentedString(timeInTwoFields)).append("\n");
        sb.append("    fieldDelimiter: ").append(toIndentedString(fieldDelimiter)).append("\n");
        sb.append("    timeField: ").append(toIndentedString(timeField)).append("\n");
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
