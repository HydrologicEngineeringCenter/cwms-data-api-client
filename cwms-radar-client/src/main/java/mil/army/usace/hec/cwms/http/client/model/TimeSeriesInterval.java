package mil.army.usace.hec.cwms.http.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;

/**
 * The interval of the time-series, in ISO-8601 duration format
 */
@ApiModel(description = "The interval of the time-series, in ISO-8601 duration format")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:49:54.974-07:00[America/Los_Angeles]")
public class TimeSeriesInterval   {
  @JsonProperty("seconds")
  private Long seconds = null;

  @JsonProperty("units")
  @Valid
  private List<TimeSeriesIntervalUnits> units = null;

  @JsonProperty("nano")
  private Integer nano = null;

  @JsonProperty("zero")
  private Boolean zero = null;

  @JsonProperty("negative")
  private Boolean negative = null;

  public TimeSeriesInterval seconds(Long seconds) {
    this.seconds = seconds;
    return this;
  }

  /**
   * Get seconds
   * @return seconds
  **/
  @ApiModelProperty(value = "")

  public Long getSeconds() {
    return seconds;
  }

  public void setSeconds(Long seconds) {
    this.seconds = seconds;
  }

  public TimeSeriesInterval units(List<TimeSeriesIntervalUnits> units) {
    this.units = units;
    return this;
  }

  public TimeSeriesInterval addUnitsItem(TimeSeriesIntervalUnits unitsItem) {
    if (this.units == null) {
      this.units = new ArrayList<TimeSeriesIntervalUnits>();
    }
    this.units.add(unitsItem);
    return this;
  }

  /**
   * Get units
   * @return units
  **/
  @ApiModelProperty(value = "")
  @Valid
  public List<TimeSeriesIntervalUnits> getUnits() {
    return units;
  }

  public void setUnits(List<TimeSeriesIntervalUnits> units) {
    this.units = units;
  }

  public TimeSeriesInterval nano(Integer nano) {
    this.nano = nano;
    return this;
  }

  /**
   * Get nano
   * @return nano
  **/
  @ApiModelProperty(value = "")

  public Integer getNano() {
    return nano;
  }

  public void setNano(Integer nano) {
    this.nano = nano;
  }

  public TimeSeriesInterval zero(Boolean zero) {
    this.zero = zero;
    return this;
  }

  /**
   * Get zero
   * @return zero
  **/
  @ApiModelProperty(value = "")

  public Boolean isZero() {
    return zero;
  }

  public void setZero(Boolean zero) {
    this.zero = zero;
  }

  public TimeSeriesInterval negative(Boolean negative) {
    this.negative = negative;
    return this;
  }

  /**
   * Get negative
   * @return negative
  **/
  @ApiModelProperty(value = "")

  public Boolean isNegative() {
    return negative;
  }

  public void setNegative(Boolean negative) {
    this.negative = negative;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TimeSeriesInterval timeSeriesInterval = (TimeSeriesInterval) o;
    return Objects.equals(this.seconds, timeSeriesInterval.seconds) &&
        Objects.equals(this.units, timeSeriesInterval.units) &&
        Objects.equals(this.nano, timeSeriesInterval.nano) &&
        Objects.equals(this.zero, timeSeriesInterval.zero) &&
        Objects.equals(this.negative, timeSeriesInterval.negative);
  }

  @Override
  public int hashCode() {
    return Objects.hash(seconds, units, nano, zero, negative);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TimeSeriesInterval {\n");
    
    sb.append("    seconds: ").append(toIndentedString(seconds)).append("\n");
    sb.append("    units: ").append(toIndentedString(units)).append("\n");
    sb.append("    nano: ").append(toIndentedString(nano)).append("\n");
    sb.append("    zero: ").append(toIndentedString(zero)).append("\n");
    sb.append("    negative: ").append(toIndentedString(negative)).append("\n");
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
