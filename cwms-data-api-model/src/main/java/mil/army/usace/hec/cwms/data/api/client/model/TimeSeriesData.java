package mil.army.usace.hec.cwms.data.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * TimeSeriesData
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-09-19T12:32:49.455402100-07:00[America/Los_Angeles]")
public class TimeSeriesData {

    @JsonProperty("value")
    private Double value = null;

    @JsonProperty("quality")
    private Integer quality = null;

    public TimeSeriesData value(Double value) {
        this.value = value;
        return this;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public TimeSeriesData quality(Integer quality) {
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
        TimeSeriesData timeSeriesData = (TimeSeriesData) o;
        return Objects.equals(this.value, timeSeriesData.value)
                && Objects.equals(this.quality, timeSeriesData.quality)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, quality);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesData {\n");

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
