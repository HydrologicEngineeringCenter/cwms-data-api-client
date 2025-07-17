package mil.army.usace.hec.cwms.data.api.client.model;

import java.time.ZonedDateTime;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents the start and end times of an extent
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-12-13T13:39:01.902673100-08:00[America/Los_Angeles]")
public class TimeExtents {

    @JsonProperty("earliest-time")
    private ZonedDateTime earliestTime = null;

    @JsonProperty("latest-time")
    private ZonedDateTime latestTime = null;

    public TimeExtents earliestTime(ZonedDateTime earliestTime) {
        this.earliestTime = earliestTime;
        return this;
    }

    public ZonedDateTime getEarliestTime() {
        return earliestTime;
    }

    public void setEarliestTime(ZonedDateTime earliestTime) {
        this.earliestTime = earliestTime;
    }

    public TimeExtents latestTime(ZonedDateTime latestTime) {
        this.latestTime = latestTime;
        return this;
    }

    public ZonedDateTime getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(ZonedDateTime latestTime) {
        this.latestTime = latestTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        TimeExtents timeExtents = (TimeExtents) o;
        return Objects.equals(this.earliestTime, timeExtents.earliestTime)
         && Objects.equals(this.latestTime, timeExtents.latestTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(earliestTime, latestTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeExtents {\n");
        
        sb.append("    earliestTime: ").append(toIndentedString(earliestTime)).append("\n");
        sb.append("    latestTime: ").append(toIndentedString(latestTime)).append("\n");
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
