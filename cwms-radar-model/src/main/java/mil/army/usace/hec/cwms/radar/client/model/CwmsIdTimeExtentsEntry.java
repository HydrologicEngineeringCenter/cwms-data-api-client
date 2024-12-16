package mil.army.usace.hec.cwms.radar.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * CwmsIdTimeExtentsEntry
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-12-13T13:39:01.902673100-08:00[America/Los_Angeles]")
public class CwmsIdTimeExtentsEntry {

    @JsonProperty("id")
    private CwmsId id = null;

    @JsonProperty("time-extents")
    private TimeExtents timeExtents = null;

    public CwmsIdTimeExtentsEntry id(CwmsId id) {
        this.id = id;
        return this;
    }

    public CwmsId getId() {
        return id;
    }

    public void setId(CwmsId id) {
        this.id = id;
    }

    public CwmsIdTimeExtentsEntry timeExtents(TimeExtents timeExtents) {
        this.timeExtents = timeExtents;
        return this;
    }

    public TimeExtents getTimeExtents() {
        return timeExtents;
    }

    public void setTimeExtents(TimeExtents timeExtents) {
        this.timeExtents = timeExtents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        CwmsIdTimeExtentsEntry cwmsIdTimeExtentsEntry = (CwmsIdTimeExtentsEntry) o;
        return Objects.equals(this.id, cwmsIdTimeExtentsEntry.id)
         && Objects.equals(this.timeExtents, cwmsIdTimeExtentsEntry.timeExtents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeExtents);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CwmsIdTimeExtentsEntry {\n");
        
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    timeExtents: ").append(toIndentedString(timeExtents)).append("\n");
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
