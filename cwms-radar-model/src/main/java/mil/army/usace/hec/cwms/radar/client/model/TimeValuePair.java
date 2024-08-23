package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.Objects;


/**
 * TimeValuePair
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-08-23T14:33:50.088073-07:00[America/Los_Angeles]")
public class TimeValuePair {

    @JsonProperty("date-time")
    private OffsetDateTime dateTime = null;

    @JsonProperty("value")
    private Double value = null;

    @JsonProperty("quality")
    private Integer quality = null;

    public TimeValuePair dateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public TimeValuePair value(Double value) {
        this.value = value;
        return this;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public TimeValuePair quality(Integer quality) {
        this.quality = quality;
        return this;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeValuePair timeValuePair = (TimeValuePair) o;
        return Objects.equals(this.dateTime, timeValuePair.dateTime)
                && Objects.equals(this.value, timeValuePair.value)
                && Objects.equals(this.quality, timeValuePair.quality)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, value, quality);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeValuePair {\n");

        sb.append("    dateTime: ").append(toIndentedString(dateTime)).append("\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
        sb.append("    quality: ").append(toIndentedString(quality)).append("\n");
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
