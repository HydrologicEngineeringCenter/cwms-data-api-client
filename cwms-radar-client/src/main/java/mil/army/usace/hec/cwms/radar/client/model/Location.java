package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class Location {
    @JsonProperty("name")
    private String name = null;
    @JsonProperty("latitude")
    private Double latitude = null;
    @JsonProperty("longitude")
    private Double longitude = null;
    @JsonProperty("public-name")
    private String publicName = null;
    @JsonProperty("long-name")
    private String longName = null;
    @JsonProperty("description")
    private String description = null;
    @JsonProperty("timezone-name")
    private String timezoneName = null;
    @JsonProperty("location-type")
    private String locationType = null;
    @JsonProperty("location-kind")
    private String locationKind = null;
    @JsonProperty("nation")
    private Nation nation = null;
    @JsonProperty("state-initial")
    private String stateInitial = null;
    @JsonProperty("county-name")
    private String countyName = null;
    @JsonProperty("nearest-city")
    private String nearestCity = null;
    @JsonProperty("horizontal-datum")
    private String horizontalDatum = null;
    @JsonProperty("published-longitude")
    private Double publishedLongitude = null;
    @JsonProperty("published-latitude")
    private Double publishedLatitude = null;
    @JsonProperty("vertical-datum")
    private String verticalDatum = null;
    @JsonProperty("elevation")
    private Double elevation = null;
    @JsonProperty("map-label")
    private String mapLabel = null;
    @JsonProperty("bounding-office-id")
    private String boundingOfficeId = null;
    @JsonProperty("office-id")
    private String officeId = null;

    /**
     * Get name
     *
     * @return name
     **/
    @ApiModelProperty(value = "Location's name")

    public String getName() {
        return name;
    }

    /**
     * Get latitude
     *
     * @return latitude
     **/
    @ApiModelProperty(value = "Location's latitude")

    public Double getLatitude() {
        return latitude;
    }

    /**
     * Get longitude
     *
     * @return longitude
     **/
    @ApiModelProperty(value = "Location's longitude")

    public Double getLongitude() {
        return longitude;
    }

    /**
     * Get publicName
     *
     * @return publicName
     **/
    @ApiModelProperty(value = "Location's public name")

    public String getPublicName() {
        return publicName;
    }

    /**
     * Get longName
     *
     * @return longName
     **/
    @ApiModelProperty(value = "Location's long name")

    public String getLongName() {
        return longName;
    }

    /**
     * Get description
     *
     * @return description
     **/
    @ApiModelProperty(value = "Description of Location")

    public String getDescription() {
        return description;
    }

    /**
     * Get timezoneName
     *
     * @return timezoneName
     **/
    @ApiModelProperty(value = "Location's time-zone")

    public String getTimezoneName() {
        return timezoneName;
    }

    /**
     * Get locationType
     *
     * @return locationType
     **/
    @ApiModelProperty(value = "Location's type")

    public String getLocationType() {
        return locationType;
    }

    /**
     * Get locationKind
     *
     * @return locationKind
     **/
    @ApiModelProperty(value = "Location's kind")

    public String getLocationKind() {
        return locationKind;
    }

    /**
     * Get nation
     *
     * @return nation
     **/
    @ApiModelProperty(value = "Nation that location belongs to")

    public Nation getNation() {
        return nation;
    }

    /**
     * Get stateInitial
     *
     * @return stateInitial
     **/
    @ApiModelProperty(value = "State that location belongs to")

    public String getStateInitial() {
        return stateInitial;
    }

    /**
     * Get countyName
     *
     * @return countyName
     **/
    @ApiModelProperty(value = "County that location belongs to")

    public String getCountyName() {
        return countyName;
    }

    /**
     * Get nearestCity
     *
     * @return nearestCity
     **/
    @ApiModelProperty(value = "Nearest city to location")

    public String getNearestCity() {
        return nearestCity;
    }

    /**
     * Get horizontalDatum
     *
     * @return horizontalDatum
     **/
    @ApiModelProperty(value = "Location's horizontal datum")

    public String getHorizontalDatum() {
        return horizontalDatum;
    }

    /**
     * Get publishedLongitude
     *
     * @return publishedLongitude
     **/
    @ApiModelProperty(value = "Location's published longitude")

    public Double getPublishedLongitude() {
        return publishedLongitude;
    }

    /**
     * Get publishedLatitude
     *
     * @return publishedLatitude
     **/
    @ApiModelProperty(value = "Location's published latitude")

    public Double getPublishedLatitude() {
        return publishedLatitude;
    }

    /**
     * Get verticalDatum
     *
     * @return verticalDatum
     **/
    @ApiModelProperty(value = "Location's veritical datum")

    public String getVerticalDatum() {
        return verticalDatum;
    }

    /**
     * Get elevation
     *
     * @return elevation
     **/
    @ApiModelProperty(value = "Location's elevation")

    public Double getElevation() {
        return elevation;
    }

    /**
     * Get mapLabel
     *
     * @return mapLabel
     **/
    @ApiModelProperty(value = "Location's map label")

    public String getMapLabel() {
        return mapLabel;
    }

    /**
     * Get boundingOfficeId
     *
     * @return boundingOfficeId
     **/
    @ApiModelProperty(value = "Bounding office ID of Location")

    public String getBoundingOfficeId() {
        return boundingOfficeId;
    }

    /**
     * Get officeId
     *
     * @return officeId
     **/
    @ApiModelProperty(value = "Office ID of Location")

    public String getOfficeId() {
        return officeId;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location = (Location) o;
        return Objects.equals(this.name, location.name) &&
                Objects.equals(this.latitude, location.latitude) &&
                Objects.equals(this.longitude, location.longitude) &&
                Objects.equals(this.publicName, location.publicName) &&
                Objects.equals(this.longName, location.longName) &&
                Objects.equals(this.description, location.description) &&
                Objects.equals(this.timezoneName, location.timezoneName) &&
                Objects.equals(this.locationType, location.locationType) &&
                Objects.equals(this.locationKind, location.locationKind) &&
                Objects.equals(this.nation, location.nation) &&
                Objects.equals(this.stateInitial, location.stateInitial) &&
                Objects.equals(this.countyName, location.countyName) &&
                Objects.equals(this.nearestCity, location.nearestCity) &&
                Objects.equals(this.horizontalDatum, location.horizontalDatum) &&
                Objects.equals(this.publishedLongitude, location.publishedLongitude) &&
                Objects.equals(this.publishedLatitude, location.publishedLatitude) &&
                Objects.equals(this.verticalDatum, location.verticalDatum) &&
                Objects.equals(this.elevation, location.elevation) &&
                Objects.equals(this.mapLabel, location.mapLabel) &&
                Objects.equals(this.boundingOfficeId, location.boundingOfficeId) &&
                Objects.equals(this.officeId, location.officeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, latitude, longitude, publicName, longName, description,
                timezoneName, locationType, locationKind, nation, stateInitial, countyName, nearestCity,
                horizontalDatum, publishedLongitude, publishedLatitude, verticalDatum, elevation,
                mapLabel, boundingOfficeId, officeId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Location {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
        sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
        sb.append("    publicName: ").append(toIndentedString(publicName)).append("\n");
        sb.append("    longName: ").append(toIndentedString(longName)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    timezoneName: ").append(toIndentedString(timezoneName)).append("\n");
        sb.append("    locationType: ").append(toIndentedString(locationType)).append("\n");
        sb.append("    locationKind: ").append(toIndentedString(locationKind)).append("\n");
        sb.append("    nation: ").append(toIndentedString(nation)).append("\n");
        sb.append("    stateInitial: ").append(toIndentedString(stateInitial)).append("\n");
        sb.append("    countyName: ").append(toIndentedString(countyName)).append("\n");
        sb.append("    nearestCity: ").append(toIndentedString(nearestCity)).append("\n");
        sb.append("    horizontalDatum: ").append(toIndentedString(horizontalDatum)).append("\n");
        sb.append("    publishedLongitude: ").append(toIndentedString(publishedLongitude))
                .append("\n");
        sb.append("    publishedLatitude: ").append(toIndentedString(publishedLatitude))
                .append("\n");
        sb.append("    verticalDatum: ").append(toIndentedString(verticalDatum)).append("\n");
        sb.append("    elevation: ").append(toIndentedString(elevation)).append("\n");
        sb.append("    mapLabel: ").append(toIndentedString(mapLabel)).append("\n");
        sb.append("    boundingOfficeId: ").append(toIndentedString(boundingOfficeId)).append("\n");
        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
