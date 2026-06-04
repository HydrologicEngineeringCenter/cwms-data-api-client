package mil.army.usace.hec.cwms.data.api.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * LocationToPublishedData
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-06-05T16:57:21.059473900-07:00[America/Los_Angeles]")
public class LocationToPublishedData {

    @JsonProperty("location-id")
    private CwmsId locationId = null;

    @JsonProperty("kind")
    private String kind = null;

    @JsonProperty("bounding-office-id")
    private String boundingOfficeId = null;

    @JsonProperty("published-times-series")
    @Valid
    private Map<String, PublishedTimeSeriesData> publishedTimesSeries = null;

    public LocationToPublishedData locationId(CwmsId locationId) {
        this.locationId = locationId;
        return this;
    }

    public CwmsId getLocationId() {
        return locationId;
    }

    public void setLocationId(CwmsId locationId) {
        this.locationId = locationId;
    }

    public LocationToPublishedData kind(String kind) {
        this.kind = kind;
        return this;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public LocationToPublishedData boundingOfficeId(String boundingOfficeId) {
        this.boundingOfficeId = boundingOfficeId;
        return this;
    }

    public String getBoundingOfficeId() {
        return boundingOfficeId;
    }

    public void setBoundingOfficeId(String boundingOfficeId) {
        this.boundingOfficeId = boundingOfficeId;
    }

    public LocationToPublishedData publishedTimesSeries(Map<String, PublishedTimeSeriesData> publishedTimesSeries) {
        this.publishedTimesSeries = publishedTimesSeries;
        return this;
    }

    public LocationToPublishedData putPublishedTimesSeriesItem(String key, PublishedTimeSeriesData publishedTimesSeriesItem) {
            if (this.publishedTimesSeries == null) {
            this.publishedTimesSeries = new HashMap<>();
            }
        this.publishedTimesSeries.put(key, publishedTimesSeriesItem);
        return this;
    }

    public Map<String, PublishedTimeSeriesData> getPublishedTimesSeries() {
        return publishedTimesSeries;
    }

    public void setPublishedTimesSeries(Map<String, PublishedTimeSeriesData> publishedTimesSeries) {
        this.publishedTimesSeries = publishedTimesSeries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        LocationToPublishedData locationToPublishedData = (LocationToPublishedData) o;
        return Objects.equals(this.locationId, locationToPublishedData.locationId)
         && this.kind == null || locationToPublishedData.kind == null?Objects.equals(this.kind, locationToPublishedData.kind):this.kind.equalsIgnoreCase(locationToPublishedData.kind)
         && this.boundingOfficeId == null || locationToPublishedData.boundingOfficeId == null?Objects.equals(this.boundingOfficeId, locationToPublishedData.boundingOfficeId):this.boundingOfficeId.equalsIgnoreCase(locationToPublishedData.boundingOfficeId)
         && Objects.equals(this.publishedTimesSeries, locationToPublishedData.publishedTimesSeries)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, kind==null?0:kind.toLowerCase(), boundingOfficeId==null?0:boundingOfficeId.toLowerCase(), publishedTimesSeries);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LocationToPublishedData {\n");
        
        sb.append("    locationId: ").append(toIndentedString(locationId)).append("\n");
        sb.append("    kind: ").append(toIndentedString(kind)).append("\n");
        sb.append("    boundingOfficeId: ").append(toIndentedString(boundingOfficeId)).append("\n");
        sb.append("    publishedTimesSeries: ").append(toIndentedString(publishedTimesSeries)).append("\n");
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
