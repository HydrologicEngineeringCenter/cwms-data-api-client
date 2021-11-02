/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 * StreamLocation
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class StreamLocation {
    @JsonProperty("locationName")
    private String locationName = null;

    @JsonProperty("streamName")
    private String streamName = null;

    @JsonProperty("station")
    private Double station = null;

    @JsonProperty("bank")
    private String bank = null;

    @JsonProperty("officeId")
    private String officeId = null;

    @JsonProperty("publishedStation")
    private Double publishedStation = null;

    @JsonProperty("lowestMeasurableStage")
    private Double lowestMeasurableStage = null;

    @JsonProperty("totalDrainageArea")
    private Double totalDrainageArea = null;

    @JsonProperty("ungagedDrainageArea")
    private Double ungagedDrainageArea = null;

    @JsonProperty("nagivationStation")
    private Double nagivationStation = null;

    public StreamLocation locationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    /**
     * Get locationName
     *
     * @return locationName
     **/
    @ApiModelProperty(value = "")

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public StreamLocation streamName(String streamName) {
        this.streamName = streamName;
        return this;
    }

    /**
     * Get streamName
     *
     * @return streamName
     **/
    @ApiModelProperty(value = "")

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    public StreamLocation station(Double station) {
        this.station = station;
        return this;
    }

    /**
     * Get station
     *
     * @return station
     **/
    @ApiModelProperty(value = "")

    public Double getStation() {
        return station;
    }

    public void setStation(Double station) {
        this.station = station;
    }

    public StreamLocation bank(String bank) {
        this.bank = bank;
        return this;
    }

    /**
     * Get bank
     *
     * @return bank
     **/
    @ApiModelProperty(value = "")

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public StreamLocation officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    /**
     * Get officeId
     *
     * @return officeId
     **/
    @ApiModelProperty(value = "")

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public StreamLocation publishedStation(Double publishedStation) {
        this.publishedStation = publishedStation;
        return this;
    }

    /**
     * Get publishedStation
     *
     * @return publishedStation
     **/
    @ApiModelProperty(value = "")

    public Double getPublishedStation() {
        return publishedStation;
    }

    public void setPublishedStation(Double publishedStation) {
        this.publishedStation = publishedStation;
    }

    public StreamLocation lowestMeasurableStage(Double lowestMeasurableStage) {
        this.lowestMeasurableStage = lowestMeasurableStage;
        return this;
    }

    /**
     * Get lowestMeasurableStage
     *
     * @return lowestMeasurableStage
     **/
    @ApiModelProperty(value = "")

    public Double getLowestMeasurableStage() {
        return lowestMeasurableStage;
    }

    public void setLowestMeasurableStage(Double lowestMeasurableStage) {
        this.lowestMeasurableStage = lowestMeasurableStage;
    }

    public StreamLocation totalDrainageArea(Double totalDrainageArea) {
        this.totalDrainageArea = totalDrainageArea;
        return this;
    }

    /**
     * Get totalDrainageArea
     *
     * @return totalDrainageArea
     **/
    @ApiModelProperty(value = "")

    public Double getTotalDrainageArea() {
        return totalDrainageArea;
    }

    public void setTotalDrainageArea(Double totalDrainageArea) {
        this.totalDrainageArea = totalDrainageArea;
    }

    public StreamLocation ungagedDrainageArea(Double ungagedDrainageArea) {
        this.ungagedDrainageArea = ungagedDrainageArea;
        return this;
    }

    /**
     * Get ungagedDrainageArea
     *
     * @return ungagedDrainageArea
     **/
    @ApiModelProperty(value = "")

    public Double getUngagedDrainageArea() {
        return ungagedDrainageArea;
    }

    public void setUngagedDrainageArea(Double ungagedDrainageArea) {
        this.ungagedDrainageArea = ungagedDrainageArea;
    }

    public StreamLocation nagivationStation(Double nagivationStation) {
        this.nagivationStation = nagivationStation;
        return this;
    }

    /**
     * Get nagivationStation
     *
     * @return nagivationStation
     **/
    @ApiModelProperty(value = "")

    public Double getNagivationStation() {
        return nagivationStation;
    }

    public void setNagivationStation(Double nagivationStation) {
        this.nagivationStation = nagivationStation;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StreamLocation streamLocation = (StreamLocation) o;
        return Objects.equals(this.locationName, streamLocation.locationName) &&
            Objects.equals(this.streamName, streamLocation.streamName) &&
            Objects.equals(this.station, streamLocation.station) &&
            Objects.equals(this.bank, streamLocation.bank) &&
            Objects.equals(this.officeId, streamLocation.officeId) &&
            Objects.equals(this.publishedStation, streamLocation.publishedStation) &&
            Objects.equals(this.lowestMeasurableStage, streamLocation.lowestMeasurableStage) &&
            Objects.equals(this.totalDrainageArea, streamLocation.totalDrainageArea) &&
            Objects.equals(this.ungagedDrainageArea, streamLocation.ungagedDrainageArea) &&
            Objects.equals(this.nagivationStation, streamLocation.nagivationStation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationName, streamName, station, bank, officeId, publishedStation,
            lowestMeasurableStage, totalDrainageArea, ungagedDrainageArea, nagivationStation);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StreamLocation {\n");

        sb.append("    locationName: ").append(toIndentedString(locationName)).append("\n");
        sb.append("    streamName: ").append(toIndentedString(streamName)).append("\n");
        sb.append("    station: ").append(toIndentedString(station)).append("\n");
        sb.append("    bank: ").append(toIndentedString(bank)).append("\n");
        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    publishedStation: ").append(toIndentedString(publishedStation)).append("\n");
        sb.append("    lowestMeasurableStage: ").append(toIndentedString(lowestMeasurableStage))
            .append("\n");
        sb.append("    totalDrainageArea: ").append(toIndentedString(totalDrainageArea))
            .append("\n");
        sb.append("    ungagedDrainageArea: ").append(toIndentedString(ungagedDrainageArea))
            .append("\n");
        sb.append("    nagivationStation: ").append(toIndentedString(nagivationStation))
            .append("\n");
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
