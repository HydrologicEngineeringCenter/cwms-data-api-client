package mil.army.usace.hec.cwms.data.api.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import jakarta.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * PublishedTimeSeriesData
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-06-05T16:57:21.059473900-07:00[America/Los_Angeles]")
public class PublishedTimeSeriesData {

    @JsonProperty("time-series-id")
    private CwmsId timeSeriesId = null;

    @JsonProperty("timezone-name")
    private String timezoneName = null;

    @JsonProperty("interval-offset-minutes")
    private Integer intervalOffsetMinutes = null;

    @JsonProperty("active")
    private Boolean active = null;

    @JsonProperty("date-refreshed")
    private OffsetDateTime dateRefreshed = null;

    @JsonProperty("notes")
    private String notes = null;

    public PublishedTimeSeriesData timeSeriesId(CwmsId timeSeriesId) {
        this.timeSeriesId = timeSeriesId;
        return this;
    }

    public CwmsId getTimeSeriesId() {
        return timeSeriesId;
    }

    public void setTimeSeriesId(CwmsId timeSeriesId) {
        this.timeSeriesId = timeSeriesId;
    }

    public PublishedTimeSeriesData timezoneName(String timezoneName) {
        this.timezoneName = timezoneName;
        return this;
    }

    public String getTimezoneName() {
        return timezoneName;
    }

    public void setTimezoneName(String timezoneName) {
        this.timezoneName = timezoneName;
    }

    public PublishedTimeSeriesData intervalOffsetMinutes(Integer intervalOffsetMinutes) {
        this.intervalOffsetMinutes = intervalOffsetMinutes;
        return this;
    }

    public Integer getIntervalOffsetMinutes() {
        return intervalOffsetMinutes;
    }

    public void setIntervalOffsetMinutes(Integer intervalOffsetMinutes) {
        this.intervalOffsetMinutes = intervalOffsetMinutes;
    }

    public PublishedTimeSeriesData active(Boolean active) {
        this.active = active;
        return this;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public PublishedTimeSeriesData dateRefreshed(OffsetDateTime dateRefreshed) {
        this.dateRefreshed = dateRefreshed;
        return this;
    }

    public OffsetDateTime getDateRefreshed() {
        return dateRefreshed;
    }

    public void setDateRefreshed(OffsetDateTime dateRefreshed) {
        this.dateRefreshed = dateRefreshed;
    }

    public PublishedTimeSeriesData notes(String notes) {
        this.notes = notes;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        PublishedTimeSeriesData publishedTimeSeriesData = (PublishedTimeSeriesData) o;
        return Objects.equals(this.timeSeriesId, publishedTimeSeriesData.timeSeriesId)
         && this.timezoneName == null || publishedTimeSeriesData.timezoneName == null?Objects.equals(this.timezoneName, publishedTimeSeriesData.timezoneName):this.timezoneName.equalsIgnoreCase(publishedTimeSeriesData.timezoneName)
         && Objects.equals(this.intervalOffsetMinutes, publishedTimeSeriesData.intervalOffsetMinutes)
         && Objects.equals(this.active, publishedTimeSeriesData.active)
         && Objects.equals(this.dateRefreshed, publishedTimeSeriesData.dateRefreshed)
         && this.notes == null || publishedTimeSeriesData.notes == null?Objects.equals(this.notes, publishedTimeSeriesData.notes):this.notes.equalsIgnoreCase(publishedTimeSeriesData.notes)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeSeriesId, timezoneName==null?0:timezoneName.toLowerCase(), intervalOffsetMinutes, active, dateRefreshed, notes==null?0:notes.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PublishedTimeSeriesData {\n");
        
        sb.append("    timeSeriesId: ").append(toIndentedString(timeSeriesId)).append("\n");
        sb.append("    timezoneName: ").append(toIndentedString(timezoneName)).append("\n");
        sb.append("    intervalOffsetMinutes: ").append(toIndentedString(intervalOffsetMinutes)).append("\n");
        sb.append("    active: ").append(toIndentedString(active)).append("\n");
        sb.append("    dateRefreshed: ").append(toIndentedString(dateRefreshed)).append("\n");
        sb.append("    notes: ").append(toIndentedString(notes)).append("\n");
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
