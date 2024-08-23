package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * TimeSeriesProfileInstance
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-08-23T14:33:50.088073-07:00[America/Los_Angeles]")
public class TimeSeriesProfileInstance {

    @JsonProperty("time-series-profile")
    private TimeSeriesProfile timeSeriesProfile = null;

    @JsonProperty("time-series-list")
    @Valid
    private List<ProfileTimeSeries> timeSeriesList = new ArrayList<>();

    @JsonProperty("version")
    private String version = null;

    @JsonProperty("version-date")
    private OffsetDateTime versionDate = null;

    @JsonProperty("first-date")
    private OffsetDateTime firstDate = null;

    @JsonProperty("last-date")
    private OffsetDateTime lastDate = null;

    public TimeSeriesProfileInstance timeSeriesProfile(TimeSeriesProfile timeSeriesProfile) {
        this.timeSeriesProfile = timeSeriesProfile;
        return this;
    }

    public TimeSeriesProfile getTimeSeriesProfile() {
        return timeSeriesProfile;
    }

    public void setTimeSeriesProfile(TimeSeriesProfile timeSeriesProfile) {
        this.timeSeriesProfile = timeSeriesProfile;
    }

    public TimeSeriesProfileInstance timeSeriesList(List<ProfileTimeSeries> timeSeriesList) {
        this.timeSeriesList = timeSeriesList;
        return this;
    }

    public TimeSeriesProfileInstance addTimeSeriesListItem(ProfileTimeSeries timeSeriesListItem) {
        if (this.timeSeriesList == null) {
            this.timeSeriesList = new ArrayList<>();
        }
        this.timeSeriesList.add(timeSeriesListItem);
        return this;
    }

    public List<ProfileTimeSeries> getTimeSeriesList() {
        return timeSeriesList;
    }

    public void setTimeSeriesList(List<ProfileTimeSeries> timeSeriesList) {
        this.timeSeriesList = timeSeriesList;
    }

    public TimeSeriesProfileInstance version(String version) {
        this.version = version;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public TimeSeriesProfileInstance versionDate(OffsetDateTime versionDate) {
        this.versionDate = versionDate;
        return this;
    }

    public OffsetDateTime getVersionDate() {
        return versionDate;
    }

    public void setVersionDate(OffsetDateTime versionDate) {
        this.versionDate = versionDate;
    }

    public TimeSeriesProfileInstance firstDate(OffsetDateTime firstDate) {
        this.firstDate = firstDate;
        return this;
    }

    public OffsetDateTime getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(OffsetDateTime firstDate) {
        this.firstDate = firstDate;
    }

    public TimeSeriesProfileInstance lastDate(OffsetDateTime lastDate) {
        this.lastDate = lastDate;
        return this;
    }

    public OffsetDateTime getLastDate() {
        return lastDate;
    }

    public void setLastDate(OffsetDateTime lastDate) {
        this.lastDate = lastDate;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSeriesProfileInstance timeSeriesProfileInstance = (TimeSeriesProfileInstance) o;
        return Objects.equals(this.timeSeriesProfile, timeSeriesProfileInstance.timeSeriesProfile)
                && Objects.equals(this.timeSeriesList, timeSeriesProfileInstance.timeSeriesList)
                && this.version == null || timeSeriesProfileInstance.version == null
                ? Objects.equals(this.version, timeSeriesProfileInstance.version)
                : this.version.equalsIgnoreCase(timeSeriesProfileInstance.version)
                && Objects.equals(this.versionDate, timeSeriesProfileInstance.versionDate)
                && Objects.equals(this.firstDate, timeSeriesProfileInstance.firstDate)
                && Objects.equals(this.lastDate, timeSeriesProfileInstance.lastDate)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeSeriesProfile, timeSeriesList, version == null ? 0 : version.toLowerCase(),
                versionDate, firstDate, lastDate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesProfileInstance {\n");

        sb.append("    timeSeriesProfile: ").append(toIndentedString(timeSeriesProfile)).append("\n");
        sb.append("    timeSeriesList: ").append(toIndentedString(timeSeriesList)).append("\n");
        sb.append("    version: ").append(toIndentedString(version)).append("\n");
        sb.append("    versionDate: ").append(toIndentedString(versionDate)).append("\n");
        sb.append("    firstDate: ").append(toIndentedString(firstDate)).append("\n");
        sb.append("    lastDate: ").append(toIndentedString(lastDate)).append("\n");
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
