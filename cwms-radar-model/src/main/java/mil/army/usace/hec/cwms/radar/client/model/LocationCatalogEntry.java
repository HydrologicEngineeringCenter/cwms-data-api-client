/*
 * MIT License
 *
 * Copyright (c) 2022 Hydrologic Engineering Center
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;

/**
 * LocationCatalogEntry
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-09T09:29:33.859-07:00[America/Los_Angeles]")
public class LocationCatalogEntry {
    @JsonProperty("office")
    private String office = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("nearest-city")
    private String nearestCity = null;

    @JsonProperty("public-name")
    private String publicName = null;

    @JsonProperty("long-name")
    private String longName = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("kind")
    private String kind = null;

    @JsonProperty("type")
    private String type = null;

    @JsonProperty("time-zone")
    private String timeZone = null;

    @JsonProperty("latitude")
    private Double latitude = null;

    @JsonProperty("longitude")
    private Double longitude = null;

    @JsonProperty("published-latitude")
    private Double publishedLatitude = null;

    @JsonProperty("published-longitude")
    private Double publishedLongitude = null;

    @JsonProperty("horizontal-datum")
    private String horizontalDatum = null;

    @JsonProperty("elevation")
    private Double elevation = null;

    @JsonProperty("unit")
    private String unit = null;

    @JsonProperty("vertical-datum")
    private String verticalDatum = null;

    @JsonProperty("nation")
    private String nation = null;

    @JsonProperty("state")
    private String state = null;

    @JsonProperty("county")
    private String county = null;

    @JsonProperty("bounding-office")
    private String boundingOffice = null;

    @JsonProperty("map-label")
    private String mapLabel = null;

    @JsonProperty("active")
    private Boolean active = null;

    @JsonProperty("aliases")
    @Valid
    private List<LocationAlias> aliases = new ArrayList<>();

    public LocationCatalogEntry office(String office) {
        this.office = office;
        return this;
    }

    /**
     * Get office
     *
     * @return office
     **/

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public LocationCatalogEntry name(String name) {
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

    public LocationCatalogEntry nearestCity(String nearestCity) {
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

    public LocationCatalogEntry publicName(String publicName) {
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

    public LocationCatalogEntry longName(String longName) {
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

    public LocationCatalogEntry description(String description) {
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

    public LocationCatalogEntry kind(String kind) {
        this.kind = kind;
        return this;
    }

    /**
     * Get kind
     *
     * @return kind
     **/

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public LocationCatalogEntry type(String type) {
        this.type = type;
        return this;
    }

    /**
     * Get type
     *
     * @return type
     **/

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocationCatalogEntry timeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    /**
     * Get timeZone
     *
     * @return timeZone
     **/

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public LocationCatalogEntry latitude(Double latitude) {
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

    public LocationCatalogEntry longitude(Double longitude) {
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

    public LocationCatalogEntry publishedLatitude(Double publishedLatitude) {
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

    public LocationCatalogEntry publishedLongitude(Double publishedLongitude) {
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

    public LocationCatalogEntry horizontalDatum(String horizontalDatum) {
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

    public LocationCatalogEntry elevation(Double elevation) {
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

    public LocationCatalogEntry unit(String unit) {
        this.unit = unit;
        return this;
    }

    /**
     * Get unit
     *
     * @return unit
     **/

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public LocationCatalogEntry verticalDatum(String verticalDatum) {
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

    public LocationCatalogEntry nation(String nation) {
        this.nation = nation;
        return this;
    }

    /**
     * Get nation
     *
     * @return nation
     **/

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public LocationCatalogEntry state(String state) {
        this.state = state;
        return this;
    }

    /**
     * Get state
     *
     * @return state
     **/

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocationCatalogEntry county(String county) {
        this.county = county;
        return this;
    }

    /**
     * Get county
     *
     * @return county
     **/

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public LocationCatalogEntry boundingOffice(String boundingOffice) {
        this.boundingOffice = boundingOffice;
        return this;
    }

    /**
     * Get boundingOffice
     *
     * @return boundingOffice
     **/

    public String getBoundingOffice() {
        return boundingOffice;
    }

    public void setBoundingOffice(String boundingOffice) {
        this.boundingOffice = boundingOffice;
    }

    public LocationCatalogEntry mapLabel(String mapLabel) {
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

    public LocationCatalogEntry active(Boolean active) {
        this.active = active;
        return this;
    }

    /**
     * Get active
     *
     * @return active
     **/

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocationCatalogEntry aliases(List<LocationAlias> aliases) {
        this.aliases = aliases;
        return this;
    }

    public LocationCatalogEntry addAliasesItem(LocationAlias aliasesItem) {
        if (this.aliases == null) {
            this.aliases = new ArrayList<LocationAlias>();
        }
        this.aliases.add(aliasesItem);
        return this;
    }

    /**
     * Get aliases
     *
     * @return aliases
     **/
    @Valid
    public List<LocationAlias> getAliases() {
        return aliases;
    }

    public void setAliases(List<LocationAlias> aliases) {
        this.aliases = aliases;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LocationCatalogEntry locationCatalogEntry = (LocationCatalogEntry) o;
        return this.office == null || locationCatalogEntry.office == null ? Objects.equals(this.office, locationCatalogEntry.office) :
            this.office.equalsIgnoreCase(locationCatalogEntry.office) && this.name == null || locationCatalogEntry.name == null ?
                Objects.equals(this.name, locationCatalogEntry.name) :
                this.name.equalsIgnoreCase(locationCatalogEntry.name) && this.nearestCity == null || locationCatalogEntry.nearestCity == null ?
                    Objects.equals(this.nearestCity, locationCatalogEntry.nearestCity) :
                    this.nearestCity.equalsIgnoreCase(locationCatalogEntry.nearestCity) && this.publicName == null ||
                        locationCatalogEntry.publicName == null ? Objects.equals(this.publicName, locationCatalogEntry.publicName) :
                        this.publicName.equalsIgnoreCase(locationCatalogEntry.publicName) && this.longName == null ||
                            locationCatalogEntry.longName == null ? Objects.equals(this.longName, locationCatalogEntry.longName) :
                            this.longName.equalsIgnoreCase(locationCatalogEntry.longName) && this.description == null ||
                                locationCatalogEntry.description == null ? Objects.equals(this.description, locationCatalogEntry.description) :
                                this.description.equalsIgnoreCase(locationCatalogEntry.description) && this.kind == null ||
                                    locationCatalogEntry.kind == null ? Objects.equals(this.kind, locationCatalogEntry.kind) :
                                    this.kind.equalsIgnoreCase(locationCatalogEntry.kind) && this.type == null || locationCatalogEntry.type == null ?
                                        Objects.equals(this.type, locationCatalogEntry.type) :
                                        this.type.equalsIgnoreCase(locationCatalogEntry.type) && this.timeZone == null ||
                                            locationCatalogEntry.timeZone == null ? Objects.equals(this.timeZone, locationCatalogEntry.timeZone) :
                                            this.timeZone.equalsIgnoreCase(locationCatalogEntry.timeZone) &&
                                                Objects.equals(this.latitude, locationCatalogEntry.latitude) &&
                                                Objects.equals(this.longitude, locationCatalogEntry.longitude) &&
                                                Objects.equals(this.publishedLatitude, locationCatalogEntry.publishedLatitude) &&
                                                Objects.equals(this.publishedLongitude, locationCatalogEntry.publishedLongitude) &&
                                                this.horizontalDatum == null || locationCatalogEntry.horizontalDatum == null ?
                                                Objects.equals(this.horizontalDatum, locationCatalogEntry.horizontalDatum) :
                                                this.horizontalDatum.equalsIgnoreCase(locationCatalogEntry.horizontalDatum) &&
                                                    Objects.equals(this.elevation, locationCatalogEntry.elevation) && this.unit == null ||
                                                    locationCatalogEntry.unit == null ? Objects.equals(this.unit, locationCatalogEntry.unit) :
                                                    this.unit.equalsIgnoreCase(locationCatalogEntry.unit) && this.verticalDatum == null ||
                                                        locationCatalogEntry.verticalDatum == null ?
                                                        Objects.equals(this.verticalDatum, locationCatalogEntry.verticalDatum) :
                                                        this.verticalDatum.equalsIgnoreCase(locationCatalogEntry.verticalDatum) &&
                                                            this.nation == null || locationCatalogEntry.nation == null ?
                                                            Objects.equals(this.nation, locationCatalogEntry.nation) :
                                                            this.nation.equalsIgnoreCase(locationCatalogEntry.nation) && this.state == null ||
                                                                locationCatalogEntry.state == null ?
                                                                Objects.equals(this.state, locationCatalogEntry.state) :
                                                                this.state.equalsIgnoreCase(locationCatalogEntry.state) && this.county == null ||
                                                                    locationCatalogEntry.county == null ?
                                                                    Objects.equals(this.county, locationCatalogEntry.county) :
                                                                    this.county.equalsIgnoreCase(locationCatalogEntry.county) &&
                                                                        this.boundingOffice == null || locationCatalogEntry.boundingOffice == null ?
                                                                        Objects.equals(this.boundingOffice, locationCatalogEntry.boundingOffice) :
                                                                        this.boundingOffice.equalsIgnoreCase(locationCatalogEntry.boundingOffice) &&
                                                                            this.mapLabel == null || locationCatalogEntry.mapLabel == null ?
                                                                            Objects.equals(this.mapLabel, locationCatalogEntry.mapLabel) :
                                                                            this.mapLabel.equalsIgnoreCase(locationCatalogEntry.mapLabel) &&
                                                                                Objects.equals(this.active, locationCatalogEntry.active) &&
                                                                                Objects.equals(this.aliases, locationCatalogEntry.aliases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(office == null ? 0 : office.toLowerCase(), name == null ? 0 : name.toLowerCase(),
            nearestCity == null ? 0 : nearestCity.toLowerCase(), publicName == null ? 0 : publicName.toLowerCase(),
            longName == null ? 0 : longName.toLowerCase(), description == null ? 0 : description.toLowerCase(), kind == null ? 0 : kind.toLowerCase(),
            type == null ? 0 : type.toLowerCase(), timeZone == null ? 0 : timeZone.toLowerCase(), latitude, longitude, publishedLatitude,
            publishedLongitude, horizontalDatum == null ? 0 : horizontalDatum.toLowerCase(), elevation, unit == null ? 0 : unit.toLowerCase(),
            verticalDatum == null ? 0 : verticalDatum.toLowerCase(), nation == null ? 0 : nation.toLowerCase(),
            state == null ? 0 : state.toLowerCase(), county == null ? 0 : county.toLowerCase(),
            boundingOffice == null ? 0 : boundingOffice.toLowerCase(), mapLabel == null ? 0 : mapLabel.toLowerCase(), active, aliases);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LocationCatalogEntry {\n");

        sb.append("    office: ").append(toIndentedString(office)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    nearestCity: ").append(toIndentedString(nearestCity)).append("\n");
        sb.append("    publicName: ").append(toIndentedString(publicName)).append("\n");
        sb.append("    longName: ").append(toIndentedString(longName)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    kind: ").append(toIndentedString(kind)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    timeZone: ").append(toIndentedString(timeZone)).append("\n");
        sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
        sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
        sb.append("    publishedLatitude: ").append(toIndentedString(publishedLatitude)).append("\n");
        sb.append("    publishedLongitude: ").append(toIndentedString(publishedLongitude)).append("\n");
        sb.append("    horizontalDatum: ").append(toIndentedString(horizontalDatum)).append("\n");
        sb.append("    elevation: ").append(toIndentedString(elevation)).append("\n");
        sb.append("    unit: ").append(toIndentedString(unit)).append("\n");
        sb.append("    verticalDatum: ").append(toIndentedString(verticalDatum)).append("\n");
        sb.append("    nation: ").append(toIndentedString(nation)).append("\n");
        sb.append("    state: ").append(toIndentedString(state)).append("\n");
        sb.append("    county: ").append(toIndentedString(county)).append("\n");
        sb.append("    boundingOffice: ").append(toIndentedString(boundingOffice)).append("\n");
        sb.append("    mapLabel: ").append(toIndentedString(mapLabel)).append("\n");
        sb.append("    active: ").append(toIndentedString(active)).append("\n");
        sb.append("    aliases: ").append(toIndentedString(aliases)).append("\n");
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
