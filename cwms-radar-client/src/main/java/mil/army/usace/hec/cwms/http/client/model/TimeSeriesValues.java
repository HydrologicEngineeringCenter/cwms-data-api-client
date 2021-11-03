package mil.army.usace.hec.cwms.http.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 * A representation of a time-series record
 */
@ApiModel(description = "A representation of a time-series record")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:49:54.974-07:00[America/Los_Angeles]")
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class TimeSeriesValues {
    private Long dateTime = null;
    private Double value = null;
    private Integer qualityCode = null;

    @JsonCreator
    public TimeSeriesValues(@JsonProperty("date-time") Long dateTime,
                            @JsonProperty("value") Double value,
                            @JsonProperty("quality-code") Integer qualityCode) {
        this.dateTime = dateTime;
        this.value = value;
        this.qualityCode = qualityCode;
    }

    public TimeSeriesValues dateTime(Long dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    /**
     * Milliseconds since 1970-01-01 (Unix Epoch)
     *
     * @return dateTime
     **/
    @ApiModelProperty(value = "Milliseconds since 1970-01-01 (Unix Epoch)")

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    public TimeSeriesValues value(Double value) {
        this.value = value;
        return this;
    }

    /**
     * Requested time-series data value
     *
     * @return value
     **/
    @ApiModelProperty(value = "Requested time-series data value")

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public TimeSeriesValues qualityCode(Integer qualityCode) {
        this.qualityCode = qualityCode;
        return this;
    }

    /**
     * Get qualityCode
     *
     * @return qualityCode
     **/
    @ApiModelProperty(value = "")

    public Integer getQualityCode() {
        return qualityCode;
    }

    public void setQualityCode(Integer qualityCode) {
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
        TimeSeriesValues timeSeriesValues = (TimeSeriesValues) o;
        return Objects.equals(this.dateTime, timeSeriesValues.dateTime) &&
            Objects.equals(this.value, timeSeriesValues.value) &&
            Objects.equals(this.qualityCode, timeSeriesValues.qualityCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, value, qualityCode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesValues {\n");

        sb.append("    dateTime: ").append(toIndentedString(dateTime)).append("\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
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
