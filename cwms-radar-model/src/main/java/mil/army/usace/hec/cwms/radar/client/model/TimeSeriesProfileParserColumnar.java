package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * TimeSeriesProfileParserColumnar
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-08-23T10:53:54.959292200-07:00[America/Los_Angeles]")
public class TimeSeriesProfileParserColumnar extends TimeSeriesProfileParser {

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

    @JsonProperty("time-start-column")
    private Integer timeStartColumn = null;

    @JsonProperty("time-end-column")
    private Integer timeEndColumn = null;

    public TimeSeriesProfileParserColumnar locationId(CwmsId locationId) {
        this.locationId = locationId;
        return this;
    }

    public CwmsId getLocationId() {
        return locationId;
    }

    public void setLocationId(CwmsId locationId) {
        this.locationId = locationId;
    }

    public TimeSeriesProfileParserColumnar keyParameter(String keyParameter) {
        this.keyParameter = keyParameter;
        return this;
    }

    public String getKeyParameter() {
        return keyParameter;
    }

    public void setKeyParameter(String keyParameter) {
        this.keyParameter = keyParameter;
    }

    public TimeSeriesProfileParserColumnar recordDelimiter(char recordDelimiter) {
        this.recordDelimiter = recordDelimiter;
        return this;
    }

    public char getRecordDelimiter() {
        return recordDelimiter;
    }

    public void setRecordDelimiter(char recordDelimiter) {
        this.recordDelimiter = recordDelimiter;
    }

    public TimeSeriesProfileParserColumnar timeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
        return this;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public TimeSeriesProfileParserColumnar timeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public TimeSeriesProfileParserColumnar parameterInfoList(List<ParameterInfo> parameterInfoList) {
        this.parameterInfoList = parameterInfoList;
        return this;
    }

    public TimeSeriesProfileParserColumnar addParameterInfoListItem(ParameterInfo parameterInfoListItem) {
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

    public TimeSeriesProfileParserColumnar timeInTwoFields(Boolean timeInTwoFields) {
        this.timeInTwoFields = timeInTwoFields;
        return this;
    }

    public Boolean isTimeInTwoFields() {
        return timeInTwoFields;
    }

    public void setTimeInTwoFields(Boolean timeInTwoFields) {
        this.timeInTwoFields = timeInTwoFields;
    }

    public TimeSeriesProfileParserColumnar timeStartColumn(Integer timeStartColumn) {
        this.timeStartColumn = timeStartColumn;
        return this;
    }

    public Integer getTimeStartColumn() {
        return timeStartColumn;
    }

    public void setTimeStartColumn(Integer timeStartColumn) {
        this.timeStartColumn = timeStartColumn;
    }

    public TimeSeriesProfileParserColumnar timeEndColumn(Integer timeEndColumn) {
        this.timeEndColumn = timeEndColumn;
        return this;
    }

    public Integer getTimeEndColumn() {
        return timeEndColumn;
    }

    public void setTimeEndColumn(Integer timeEndColumn) {
        this.timeEndColumn = timeEndColumn;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSeriesProfileParserColumnar timeSeriesProfileParserColumnar = (TimeSeriesProfileParserColumnar) o;
        return Objects.equals(this.locationId, timeSeriesProfileParserColumnar.locationId)
                && this.keyParameter == null || timeSeriesProfileParserColumnar.keyParameter == null
                ? Objects.equals(this.keyParameter, timeSeriesProfileParserColumnar.keyParameter)
                : this.keyParameter.equalsIgnoreCase(timeSeriesProfileParserColumnar.keyParameter)
                && this.recordDelimiter == '\0' || timeSeriesProfileParserColumnar.recordDelimiter == '\0'
                ? Objects.equals(this.recordDelimiter, timeSeriesProfileParserColumnar.recordDelimiter)
                : this.recordDelimiter == timeSeriesProfileParserColumnar.recordDelimiter
                && this.timeFormat == null || timeSeriesProfileParserColumnar.timeFormat == null
                ? Objects.equals(this.timeFormat, timeSeriesProfileParserColumnar.timeFormat)
                : this.timeFormat.equalsIgnoreCase(timeSeriesProfileParserColumnar.timeFormat)
                && this.timeZone == null || timeSeriesProfileParserColumnar.timeZone == null
                ? Objects.equals(this.timeZone, timeSeriesProfileParserColumnar.timeZone)
                : this.timeZone.equalsIgnoreCase(timeSeriesProfileParserColumnar.timeZone)
                && Objects.equals(this.parameterInfoList, timeSeriesProfileParserColumnar.parameterInfoList)
                && Objects.equals(this.timeInTwoFields, timeSeriesProfileParserColumnar.timeInTwoFields)
                && Objects.equals(this.timeStartColumn, timeSeriesProfileParserColumnar.timeStartColumn)
                && Objects.equals(this.timeEndColumn, timeSeriesProfileParserColumnar.timeEndColumn)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, keyParameter == null ? 0 : keyParameter.toLowerCase(),
                recordDelimiter == '\0' ? 0 : recordDelimiter,
                timeFormat == null ? 0 : timeFormat.toLowerCase(),
                timeZone == null ? 0 : timeZone.toLowerCase(), parameterInfoList,
                timeInTwoFields, timeStartColumn, timeEndColumn);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesProfileParserColumnar {\n");

        sb.append("    locationId: ").append(toIndentedString(locationId)).append("\n");
        sb.append("    keyParameter: ").append(toIndentedString(keyParameter)).append("\n");
        sb.append("    recordDelimiter: ").append(toIndentedString(recordDelimiter)).append("\n");
        sb.append("    timeFormat: ").append(toIndentedString(timeFormat)).append("\n");
        sb.append("    timeZone: ").append(toIndentedString(timeZone)).append("\n");
        sb.append("    parameterInfoList: ").append(toIndentedString(parameterInfoList)).append("\n");
        sb.append("    timeInTwoFields: ").append(toIndentedString(timeInTwoFields)).append("\n");
        sb.append("    timeStartColumn: ").append(toIndentedString(timeStartColumn)).append("\n");
        sb.append("    timeEndColumn: ").append(toIndentedString(timeEndColumn)).append("\n");
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
