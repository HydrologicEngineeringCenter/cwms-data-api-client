package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TimeSeriesProfileParser
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-09-19T12:32:49.455402100-07:00[America/Los_Angeles]")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({ @JsonSubTypes.Type(name = "indexed-timeseries-profile-parser", value = TimeSeriesProfileParserIndexed.class),
    @JsonSubTypes.Type(name = "columnar-timeseries-profile-parser", value = TimeSeriesProfileParserColumnar.class)
})
public class TimeSeriesProfileParser {

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

    @JsonProperty("type")
    private String type = null;

    public TimeSeriesProfileParser locationId(CwmsId locationId) {
        this.locationId = locationId;
        return this;
    }

    public CwmsId getLocationId() {
        return locationId;
    }

    public void setLocationId(CwmsId locationId) {
        this.locationId = locationId;
    }

    public TimeSeriesProfileParser keyParameter(String keyParameter) {
        this.keyParameter = keyParameter;
        return this;
    }

    public String getKeyParameter() {
        return keyParameter;
    }

    public void setKeyParameter(String keyParameter) {
        this.keyParameter = keyParameter;
    }

    public TimeSeriesProfileParser recordDelimiter(char recordDelimiter) {
        this.recordDelimiter = recordDelimiter;
        return this;
    }

    public char getRecordDelimiter() {
        return recordDelimiter;
    }

    public void setRecordDelimiter(char recordDelimiter) {
        this.recordDelimiter = recordDelimiter;
    }

    public TimeSeriesProfileParser timeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
        return this;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public TimeSeriesProfileParser timeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public TimeSeriesProfileParser parameterInfoList(List<ParameterInfo> parameterInfoList) {
        this.parameterInfoList = parameterInfoList;
        return this;
    }

    public TimeSeriesProfileParser addParameterInfoListItem(ParameterInfo parameterInfoListItem) {
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

    public TimeSeriesProfileParser timeInTwoFields(Boolean timeInTwoFields) {
        this.timeInTwoFields = timeInTwoFields;
        return this;
    }

    public Boolean isTimeInTwoFields() {
        return timeInTwoFields;
    }

    public void setTimeInTwoFields(Boolean timeInTwoFields) {
        this.timeInTwoFields = timeInTwoFields;
    }

    public TimeSeriesProfileParser type(String type) {
        this.type = type;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSeriesProfileParser timeSeriesProfileParser = (TimeSeriesProfileParser) o;
        return Objects.equals(this.locationId, timeSeriesProfileParser.locationId)
                && this.keyParameter == null || timeSeriesProfileParser.keyParameter == null
                ? Objects.equals(this.keyParameter, timeSeriesProfileParser.keyParameter)
                : this.keyParameter.equalsIgnoreCase(timeSeriesProfileParser.keyParameter)
                && this.recordDelimiter == '\0' || timeSeriesProfileParser.recordDelimiter == '\0'
                ? Objects.equals(this.recordDelimiter, timeSeriesProfileParser.recordDelimiter)
                : this.recordDelimiter == timeSeriesProfileParser.recordDelimiter
                && this.timeFormat == null || timeSeriesProfileParser.timeFormat == null
                ? Objects.equals(this.timeFormat, timeSeriesProfileParser.timeFormat)
                : this.timeFormat.equalsIgnoreCase(timeSeriesProfileParser.timeFormat)
                && this.timeZone == null || timeSeriesProfileParser.timeZone == null
                ? Objects.equals(this.timeZone, timeSeriesProfileParser.timeZone)
                : this.timeZone.equalsIgnoreCase(timeSeriesProfileParser.timeZone)
                && Objects.equals(this.parameterInfoList, timeSeriesProfileParser.parameterInfoList)
                && Objects.equals(this.timeInTwoFields, timeSeriesProfileParser.timeInTwoFields)
                && this.type == null || timeSeriesProfileParser.type == null
                ? Objects.equals(this.type, timeSeriesProfileParser.type)
                : this.type.equalsIgnoreCase(timeSeriesProfileParser.type)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, keyParameter == null ? 0 : keyParameter.toLowerCase(),
                recordDelimiter == '\0' ? 0 : recordDelimiter,
                timeFormat == null ? 0 : timeFormat.toLowerCase(),
                timeZone == null ? 0 : timeZone.toLowerCase(), parameterInfoList, timeInTwoFields,
                type == null ? 0 : type.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesProfileParser {\n");

        sb.append("    locationId: ").append(toIndentedString(locationId)).append("\n");
        sb.append("    keyParameter: ").append(toIndentedString(keyParameter)).append("\n");
        sb.append("    recordDelimiter: ").append(toIndentedString(recordDelimiter)).append("\n");
        sb.append("    timeFormat: ").append(toIndentedString(timeFormat)).append("\n");
        sb.append("    timeZone: ").append(toIndentedString(timeZone)).append("\n");
        sb.append("    parameterInfoList: ").append(toIndentedString(parameterInfoList)).append("\n");
        sb.append("    timeInTwoFields: ").append(toIndentedString(timeInTwoFields)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
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
