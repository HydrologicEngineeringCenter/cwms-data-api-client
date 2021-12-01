/*
 * MIT License
 *
 * Copyright (c) 2021 Hydrologic Engineering Center
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;

/**
 * Location
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-12-01T13:20:30.413-08:00[America/Los_Angeles]")
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
    private NationEnum nation = null;
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

    public Location name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     **/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    /**
     * Get latitude
     *
     * @return latitude
     **/

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Location longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    /**
     * Get longitude
     *
     * @return longitude
     **/

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Location publicName(String publicName) {
        this.publicName = publicName;
        return this;
    }

    /**
     * Get publicName
     *
     * @return publicName
     **/

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public Location longName(String longName) {
        this.longName = longName;
        return this;
    }

    /**
     * Get longName
     *
     * @return longName
     **/

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public Location description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get description
     *
     * @return description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location timezoneName(String timezoneName) {
        this.timezoneName = timezoneName;
        return this;
    }

    /**
     * Get timezoneName
     *
     * @return timezoneName
     **/

    public String getTimezoneName() {
        return timezoneName;
    }

    public void setTimezoneName(String timezoneName) {
        this.timezoneName = timezoneName;
    }

    public Location locationType(String locationType) {
        this.locationType = locationType;
        return this;
    }

    /**
     * Get locationType
     *
     * @return locationType
     **/

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public Location locationKind(String locationKind) {
        this.locationKind = locationKind;
        return this;
    }

    /**
     * Get locationKind
     *
     * @return locationKind
     **/

    public String getLocationKind() {
        return locationKind;
    }

    public void setLocationKind(String locationKind) {
        this.locationKind = locationKind;
    }

    public Location nation(NationEnum nation) {
        this.nation = nation;
        return this;
    }

    /**
     * Get nation
     *
     * @return nation
     **/

    public NationEnum getNation() {
        return nation;
    }

    public void setNation(NationEnum nation) {
        this.nation = nation;
    }

    public Location stateInitial(String stateInitial) {
        this.stateInitial = stateInitial;
        return this;
    }

    /**
     * Get stateInitial
     *
     * @return stateInitial
     **/

    public String getStateInitial() {
        return stateInitial;
    }

    public void setStateInitial(String stateInitial) {
        this.stateInitial = stateInitial;
    }

    public Location countyName(String countyName) {
        this.countyName = countyName;
        return this;
    }

    /**
     * Get countyName
     *
     * @return countyName
     **/

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public Location nearestCity(String nearestCity) {
        this.nearestCity = nearestCity;
        return this;
    }

    /**
     * Get nearestCity
     *
     * @return nearestCity
     **/

    public String getNearestCity() {
        return nearestCity;
    }

    public void setNearestCity(String nearestCity) {
        this.nearestCity = nearestCity;
    }

    public Location horizontalDatum(String horizontalDatum) {
        this.horizontalDatum = horizontalDatum;
        return this;
    }

    /**
     * Get horizontalDatum
     *
     * @return horizontalDatum
     **/

    public String getHorizontalDatum() {
        return horizontalDatum;
    }

    public void setHorizontalDatum(String horizontalDatum) {
        this.horizontalDatum = horizontalDatum;
    }

    public Location publishedLongitude(Double publishedLongitude) {
        this.publishedLongitude = publishedLongitude;
        return this;
    }

    /**
     * Get publishedLongitude
     *
     * @return publishedLongitude
     **/

    public Double getPublishedLongitude() {
        return publishedLongitude;
    }

    public void setPublishedLongitude(Double publishedLongitude) {
        this.publishedLongitude = publishedLongitude;
    }

    public Location publishedLatitude(Double publishedLatitude) {
        this.publishedLatitude = publishedLatitude;
        return this;
    }

    /**
     * Get publishedLatitude
     *
     * @return publishedLatitude
     **/

    public Double getPublishedLatitude() {
        return publishedLatitude;
    }

    public void setPublishedLatitude(Double publishedLatitude) {
        this.publishedLatitude = publishedLatitude;
    }

    public Location verticalDatum(String verticalDatum) {
        this.verticalDatum = verticalDatum;
        return this;
    }

    /**
     * Get verticalDatum
     *
     * @return verticalDatum
     **/

    public String getVerticalDatum() {
        return verticalDatum;
    }

    public void setVerticalDatum(String verticalDatum) {
        this.verticalDatum = verticalDatum;
    }

    public Location elevation(Double elevation) {
        this.elevation = elevation;
        return this;
    }

    /**
     * Get elevation
     *
     * @return elevation
     **/

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    public Location mapLabel(String mapLabel) {
        this.mapLabel = mapLabel;
        return this;
    }

    /**
     * Get mapLabel
     *
     * @return mapLabel
     **/

    public String getMapLabel() {
        return mapLabel;
    }

    public void setMapLabel(String mapLabel) {
        this.mapLabel = mapLabel;
    }

    public Location boundingOfficeId(String boundingOfficeId) {
        this.boundingOfficeId = boundingOfficeId;
        return this;
    }

    /**
     * Get boundingOfficeId
     *
     * @return boundingOfficeId
     **/

    public String getBoundingOfficeId() {
        return boundingOfficeId;
    }

    public void setBoundingOfficeId(String boundingOfficeId) {
        this.boundingOfficeId = boundingOfficeId;
    }

    public Location officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    /**
     * Get officeId
     *
     * @return officeId
     **/

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location = (Location) o;
        return this.name == null || location.name == null ? Objects.equals(this.name, location.name) : this.name.equalsIgnoreCase(location.name)
            && Objects.equals(this.latitude, location.latitude)
            && Objects.equals(this.longitude, location.longitude)
            && this.publicName == null || location.publicName == null ? Objects.equals(this.publicName, location.publicName) :
            this.publicName.equalsIgnoreCase(location.publicName)
                && this.longName == null || location.longName == null ? Objects.equals(this.longName, location.longName) :
                this.longName.equalsIgnoreCase(location.longName)
                    && this.description == null || location.description == null ? Objects.equals(this.description, location.description) :
                    this.description.equalsIgnoreCase(location.description)
                        && this.timezoneName == null || location.timezoneName == null ? Objects.equals(this.timezoneName, location.timezoneName) :
                        this.timezoneName.equalsIgnoreCase(location.timezoneName)
                            && this.locationType == null || location.locationType == null ? Objects.equals(this.locationType, location.locationType) :
                            this.locationType.equalsIgnoreCase(location.locationType)
                                && this.locationKind == null || location.locationKind == null ?
                                Objects.equals(this.locationKind, location.locationKind) : this.locationKind.equalsIgnoreCase(location.locationKind)
                                && Objects.equals(this.nation, location.nation)
                                && this.stateInitial == null || location.stateInitial == null ?
                                Objects.equals(this.stateInitial, location.stateInitial) :
                                this.stateInitial.equalsIgnoreCase(location.stateInitial)
                                    && this.countyName == null || location.countyName == null ?
                                    Objects.equals(this.countyName, location.countyName) : this.countyName.equalsIgnoreCase(location.countyName)
                                    && this.nearestCity == null || location.nearestCity == null ?
                                    Objects.equals(this.nearestCity, location.nearestCity) :
                                    this.nearestCity.equalsIgnoreCase(location.nearestCity)
                                        && this.horizontalDatum == null || location.horizontalDatum == null ?
                                        Objects.equals(this.horizontalDatum, location.horizontalDatum) :
                                        this.horizontalDatum.equalsIgnoreCase(location.horizontalDatum)
                                            && Objects.equals(this.publishedLongitude, location.publishedLongitude)
                                            && Objects.equals(this.publishedLatitude, location.publishedLatitude)
                                            && this.verticalDatum == null || location.verticalDatum == null ?
                                            Objects.equals(this.verticalDatum, location.verticalDatum) :
                                            this.verticalDatum.equalsIgnoreCase(location.verticalDatum)
                                                && Objects.equals(this.elevation, location.elevation)
                                                && this.mapLabel == null || location.mapLabel == null ?
                                                Objects.equals(this.mapLabel, location.mapLabel) :
                                                this.mapLabel.equalsIgnoreCase(location.mapLabel)
                                                    && this.boundingOfficeId == null || location.boundingOfficeId == null ?
                                                    Objects.equals(this.boundingOfficeId, location.boundingOfficeId) :
                                                    this.boundingOfficeId.equalsIgnoreCase(location.boundingOfficeId)
                                                        && this.officeId == null || location.officeId == null ?
                                                        Objects.equals(this.officeId, location.officeId) :
                                                        this.officeId.equalsIgnoreCase(location.officeId)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name == null ? 0 : name.toLowerCase(), latitude, longitude, publicName == null ? 0 : publicName.toLowerCase(),
            longName == null ? 0 : longName.toLowerCase(), description == null ? 0 : description.toLowerCase(),
            timezoneName == null ? 0 : timezoneName.toLowerCase(), locationType == null ? 0 : locationType.toLowerCase(),
            locationKind == null ? 0 : locationKind.toLowerCase(), nation,
            stateInitial == null ? 0 : stateInitial.toLowerCase(), countyName == null ? 0 : countyName.toLowerCase(),
            nearestCity == null ? 0 : nearestCity.toLowerCase(), horizontalDatum == null ? 0 : horizontalDatum.toLowerCase(), publishedLongitude,
            publishedLatitude, verticalDatum == null ? 0 : verticalDatum.toLowerCase(), elevation, mapLabel == null ? 0 : mapLabel.toLowerCase(),
            boundingOfficeId == null ? 0 : boundingOfficeId.toLowerCase(), officeId == null ? 0 : officeId.toLowerCase());
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
        sb.append("    publishedLongitude: ").append(toIndentedString(publishedLongitude)).append("\n");
        sb.append("    publishedLatitude: ").append(toIndentedString(publishedLatitude)).append("\n");
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
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    /**
     * Gets or Sets nation
     */
    public enum NationEnum {
        US("US"),

        CANADA("CANADA"),

        MEXICO("MEXICO");

        private String value;

        NationEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static NationEnum fromValue(String text) {
            for (NationEnum b : NationEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }
    }
}
