package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * TimeSeriesProfileInstance
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-09-19T12:32:49.455402100-07:00[America/Los_Angeles]")
public class TimeSeriesProfileInstance {

    @JsonProperty("data-columns")
    @Valid
    private List<DataColumnInfo> dataColumns = new ArrayList<>();

    @JsonProperty("first-date")
    private OffsetDateTime firstDate = null;

    @JsonProperty("last-date")
    private OffsetDateTime lastDate = null;

    @JsonProperty("location-time-zone")
    private String locationTimeZone = null;

    @JsonProperty("next-page")
    private String nextPage = null;

    @JsonProperty("page")
    private String page = null;

    @JsonProperty("page-first-date")
    private OffsetDateTime pageFirstDate = null;

    @JsonProperty("page-last-date")
    private OffsetDateTime pageLastDate = null;

    @JsonProperty("page-size")
    private Integer pageSize = null;

    @JsonProperty("parameter-columns")
    @Valid
    private List<ParameterColumnInfo> parameterColumns = new ArrayList<>();

    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    @JsonProperty("time-series-list")
    @Valid
    private Map<Long, List<TimeSeriesData>> timeSeriesList = null;

    @JsonProperty("time-series-profile")
    private TimeSeriesProfile timeSeriesProfile = null;

    @JsonProperty("total")
    private Integer total = null;

    @JsonProperty("version")
    private String version = null;

    @JsonProperty("version-date")
    private OffsetDateTime versionDate = null;

    public TimeSeriesProfileInstance dataColumns(List<DataColumnInfo> dataColumns) {
        this.dataColumns = dataColumns;
        return this;
    }

    public TimeSeriesProfileInstance addDataColumnsItem(DataColumnInfo dataColumnsItem) {
        if (this.dataColumns == null) {
            this.dataColumns = new ArrayList<>();
        }
        this.dataColumns.add(dataColumnsItem);
        return this;
    }

    public List<DataColumnInfo> getDataColumns() {
        return dataColumns;
    }

    public void setDataColumns(List<DataColumnInfo> dataColumns) {
        this.dataColumns = dataColumns;
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

    public TimeSeriesProfileInstance locationTimeZone(String locationTimeZone) {
        this.locationTimeZone = locationTimeZone;
        return this;
    }

    public String getLocationTimeZone() {
        return locationTimeZone;
    }

    public void setLocationTimeZone(String locationTimeZone) {
        this.locationTimeZone = locationTimeZone;
    }

    public TimeSeriesProfileInstance nextPage(String nextPage) {
        this.nextPage = nextPage;
        return this;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public TimeSeriesProfileInstance page(String page) {
        this.page = page;
        return this;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public TimeSeriesProfileInstance pageFirstDate(OffsetDateTime pageFirstDate) {
        this.pageFirstDate = pageFirstDate;
        return this;
    }

    public OffsetDateTime getPageFirstDate() {
        return pageFirstDate;
    }

    public void setPageFirstDate(OffsetDateTime pageFirstDate) {
        this.pageFirstDate = pageFirstDate;
    }

    public TimeSeriesProfileInstance pageLastDate(OffsetDateTime pageLastDate) {
        this.pageLastDate = pageLastDate;
        return this;
    }

    public OffsetDateTime getPageLastDate() {
        return pageLastDate;
    }

    public void setPageLastDate(OffsetDateTime pageLastDate) {
        this.pageLastDate = pageLastDate;
    }

    public TimeSeriesProfileInstance pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public TimeSeriesProfileInstance parameterColumns(List<ParameterColumnInfo> parameterColumns) {
        this.parameterColumns = parameterColumns;
        return this;
    }

    public TimeSeriesProfileInstance addParameterColumnsItem(ParameterColumnInfo parameterColumnsItem) {
        if (this.parameterColumns == null) {
            this.parameterColumns = new ArrayList<>();
        }
        this.parameterColumns.add(parameterColumnsItem);
        return this;
    }

    public List<ParameterColumnInfo> getParameterColumns() {
        return parameterColumns;
    }

    public void setParameterColumns(List<ParameterColumnInfo> parameterColumns) {
        this.parameterColumns = parameterColumns;
    }

    public TimeSeriesProfileInstance timeSeriesList(Map<Long, List<TimeSeriesData>> timeSeriesList) {
        this.timeSeriesList = timeSeriesList;
        return this;
    }

    public TimeSeriesProfileInstance putTimeSeriesListItem(Long key, List<TimeSeriesData> timeSeriesListItem) {
        if (this.timeSeriesList == null) {
            this.timeSeriesList = new HashMap<>();
        }
        this.timeSeriesList.put(key, timeSeriesListItem);
        return this;
    }

    public Map<Long, List<TimeSeriesData>> getTimeSeriesList() {
        return timeSeriesList;
    }

    public void setTimeSeriesList(Map<Long, List<TimeSeriesData>> timeSeriesList) {
        this.timeSeriesList = timeSeriesList;
    }

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

    public TimeSeriesProfileInstance total(Integer total) {
        this.total = total;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSeriesProfileInstance timeSeriesProfileInstance = (TimeSeriesProfileInstance) o;
        return Objects.equals(this.dataColumns, timeSeriesProfileInstance.dataColumns)
                && Objects.equals(this.firstDate, timeSeriesProfileInstance.firstDate)
                && Objects.equals(this.lastDate, timeSeriesProfileInstance.lastDate)
                && this.locationTimeZone == null || timeSeriesProfileInstance.locationTimeZone == null
                ? Objects.equals(this.locationTimeZone, timeSeriesProfileInstance.locationTimeZone)
                : this.locationTimeZone.equalsIgnoreCase(timeSeriesProfileInstance.locationTimeZone)
                && this.nextPage == null || timeSeriesProfileInstance.nextPage == null
                ? Objects.equals(this.nextPage, timeSeriesProfileInstance.nextPage)
                : this.nextPage.equalsIgnoreCase(timeSeriesProfileInstance.nextPage)
                && this.page == null || timeSeriesProfileInstance.page == null
                ? Objects.equals(this.page, timeSeriesProfileInstance.page)
                : this.page.equalsIgnoreCase(timeSeriesProfileInstance.page)
                && Objects.equals(this.pageFirstDate, timeSeriesProfileInstance.pageFirstDate)
                && Objects.equals(this.pageLastDate, timeSeriesProfileInstance.pageLastDate)
                && Objects.equals(this.pageSize, timeSeriesProfileInstance.pageSize)
                && Objects.equals(this.parameterColumns, timeSeriesProfileInstance.parameterColumns)
                && Objects.equals(this.timeSeriesList, timeSeriesProfileInstance.timeSeriesList)
                && Objects.equals(this.timeSeriesProfile, timeSeriesProfileInstance.timeSeriesProfile)
                && Objects.equals(this.total, timeSeriesProfileInstance.total)
                && this.version == null || timeSeriesProfileInstance.version == null
                ? Objects.equals(this.version, timeSeriesProfileInstance.version)
                : this.version.equalsIgnoreCase(timeSeriesProfileInstance.version)
                && Objects.equals(this.versionDate, timeSeriesProfileInstance.versionDate)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataColumns, firstDate, lastDate, locationTimeZone == null ? 0 : locationTimeZone.toLowerCase(),
                nextPage == null ? 0 : nextPage.toLowerCase(), page == null ? 0 : page.toLowerCase(),
                pageFirstDate, pageLastDate, pageSize, parameterColumns, timeSeriesList, timeSeriesProfile, total,
                version == null ? 0 : version.toLowerCase(), versionDate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesProfileInstance {\n");

        sb.append("    dataColumns: ").append(toIndentedString(dataColumns)).append("\n");
        sb.append("    firstDate: ").append(toIndentedString(firstDate)).append("\n");
        sb.append("    lastDate: ").append(toIndentedString(lastDate)).append("\n");
        sb.append("    locationTimeZone: ").append(toIndentedString(locationTimeZone)).append("\n");
        sb.append("    nextPage: ").append(toIndentedString(nextPage)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    pageFirstDate: ").append(toIndentedString(pageFirstDate)).append("\n");
        sb.append("    pageLastDate: ").append(toIndentedString(pageLastDate)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    parameterColumns: ").append(toIndentedString(parameterColumns)).append("\n");
        sb.append("    timeSeriesList: ").append(toIndentedString(timeSeriesList)).append("\n");
        sb.append("    timeSeriesProfile: ").append(toIndentedString(timeSeriesProfile)).append("\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    version: ").append(toIndentedString(version)).append("\n");
        sb.append("    versionDate: ").append(toIndentedString(versionDate)).append("\n");
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
