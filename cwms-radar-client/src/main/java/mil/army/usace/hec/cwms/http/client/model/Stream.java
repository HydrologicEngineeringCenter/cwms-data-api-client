/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;

/**
 * Stream
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class Stream {
    @JsonProperty("streamName")
    private String streamName = null;

    @JsonProperty("tributaries")
    @Valid
    private List<Stream> tributaries = null;

    @JsonProperty("streamReaches")
    @Valid
    private List<StreamReach> streamReaches = null;

    @JsonProperty("divertingStreamId")
    private String divertingStreamId = null;

    @JsonProperty("receivingStreamId")
    private String receivingStreamId = null;

    @JsonProperty("confluenceBank")
    private String confluenceBank = null;

    @JsonProperty("diversionBank")
    private String diversionBank = null;

    @JsonProperty("streamLength")
    private Double streamLength = null;

    @JsonProperty("confluenceStation")
    private Double confluenceStation = null;

    @JsonProperty("diversionStation")
    private Double diversionStation = null;

    @JsonProperty("streamLocations")
    @Valid
    private List<StreamLocation> streamLocations = null;

    @JsonProperty("officeId")
    private String officeId = null;

    @JsonProperty("comment")
    private String comment = null;

    @JsonProperty("averageSlope")
    private Double averageSlope = null;

    public Stream streamName(String streamName) {
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

    public Stream tributaries(List<Stream> tributaries) {
        this.tributaries = tributaries;
        return this;
    }

    public Stream addTributariesItem(Stream tributariesItem) {
        if (this.tributaries == null) {
            this.tributaries = new ArrayList<Stream>();
        }
        this.tributaries.add(tributariesItem);
        return this;
    }

    /**
     * Get tributaries
     *
     * @return tributaries
     **/
    @ApiModelProperty(value = "")
    @Valid
    public List<Stream> getTributaries() {
        return tributaries;
    }

    public void setTributaries(List<Stream> tributaries) {
        this.tributaries = tributaries;
    }

    public Stream streamReaches(List<StreamReach> streamReaches) {
        this.streamReaches = streamReaches;
        return this;
    }

    public Stream addStreamReachesItem(StreamReach streamReachesItem) {
        if (this.streamReaches == null) {
            this.streamReaches = new ArrayList<StreamReach>();
        }
        this.streamReaches.add(streamReachesItem);
        return this;
    }

    /**
     * Get streamReaches
     *
     * @return streamReaches
     **/
    @ApiModelProperty(value = "")
    @Valid
    public List<StreamReach> getStreamReaches() {
        return streamReaches;
    }

    public void setStreamReaches(List<StreamReach> streamReaches) {
        this.streamReaches = streamReaches;
    }

    public Stream divertingStreamId(String divertingStreamId) {
        this.divertingStreamId = divertingStreamId;
        return this;
    }

    /**
     * Get divertingStreamId
     *
     * @return divertingStreamId
     **/
    @ApiModelProperty(value = "")

    public String getDivertingStreamId() {
        return divertingStreamId;
    }

    public void setDivertingStreamId(String divertingStreamId) {
        this.divertingStreamId = divertingStreamId;
    }

    public Stream receivingStreamId(String receivingStreamId) {
        this.receivingStreamId = receivingStreamId;
        return this;
    }

    /**
     * Get receivingStreamId
     *
     * @return receivingStreamId
     **/
    @ApiModelProperty(value = "")

    public String getReceivingStreamId() {
        return receivingStreamId;
    }

    public void setReceivingStreamId(String receivingStreamId) {
        this.receivingStreamId = receivingStreamId;
    }

    public Stream confluenceBank(String confluenceBank) {
        this.confluenceBank = confluenceBank;
        return this;
    }

    /**
     * Get confluenceBank
     *
     * @return confluenceBank
     **/
    @ApiModelProperty(value = "")

    public String getConfluenceBank() {
        return confluenceBank;
    }

    public void setConfluenceBank(String confluenceBank) {
        this.confluenceBank = confluenceBank;
    }

    public Stream diversionBank(String diversionBank) {
        this.diversionBank = diversionBank;
        return this;
    }

    /**
     * Get diversionBank
     *
     * @return diversionBank
     **/
    @ApiModelProperty(value = "")

    public String getDiversionBank() {
        return diversionBank;
    }

    public void setDiversionBank(String diversionBank) {
        this.diversionBank = diversionBank;
    }

    public Stream streamLength(Double streamLength) {
        this.streamLength = streamLength;
        return this;
    }

    /**
     * Get streamLength
     *
     * @return streamLength
     **/
    @ApiModelProperty(value = "")

    public Double getStreamLength() {
        return streamLength;
    }

    public void setStreamLength(Double streamLength) {
        this.streamLength = streamLength;
    }

    public Stream confluenceStation(Double confluenceStation) {
        this.confluenceStation = confluenceStation;
        return this;
    }

    /**
     * Get confluenceStation
     *
     * @return confluenceStation
     **/
    @ApiModelProperty(value = "")

    public Double getConfluenceStation() {
        return confluenceStation;
    }

    public void setConfluenceStation(Double confluenceStation) {
        this.confluenceStation = confluenceStation;
    }

    public Stream diversionStation(Double diversionStation) {
        this.diversionStation = diversionStation;
        return this;
    }

    /**
     * Get diversionStation
     *
     * @return diversionStation
     **/
    @ApiModelProperty(value = "")

    public Double getDiversionStation() {
        return diversionStation;
    }

    public void setDiversionStation(Double diversionStation) {
        this.diversionStation = diversionStation;
    }

    public Stream streamLocations(List<StreamLocation> streamLocations) {
        this.streamLocations = streamLocations;
        return this;
    }

    public Stream addStreamLocationsItem(StreamLocation streamLocationsItem) {
        if (this.streamLocations == null) {
            this.streamLocations = new ArrayList<StreamLocation>();
        }
        this.streamLocations.add(streamLocationsItem);
        return this;
    }

    /**
     * Get streamLocations
     *
     * @return streamLocations
     **/
    @ApiModelProperty(value = "")
    @Valid
    public List<StreamLocation> getStreamLocations() {
        return streamLocations;
    }

    public void setStreamLocations(List<StreamLocation> streamLocations) {
        this.streamLocations = streamLocations;
    }

    public Stream officeId(String officeId) {
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

    public Stream comment(String comment) {
        this.comment = comment;
        return this;
    }

    /**
     * Get comment
     *
     * @return comment
     **/
    @ApiModelProperty(value = "")

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Stream averageSlope(Double averageSlope) {
        this.averageSlope = averageSlope;
        return this;
    }

    /**
     * Get averageSlope
     *
     * @return averageSlope
     **/
    @ApiModelProperty(value = "")

    public Double getAverageSlope() {
        return averageSlope;
    }

    public void setAverageSlope(Double averageSlope) {
        this.averageSlope = averageSlope;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Stream stream = (Stream) o;
        return Objects.equals(this.streamName, stream.streamName) &&
            Objects.equals(this.tributaries, stream.tributaries) &&
            Objects.equals(this.streamReaches, stream.streamReaches) &&
            Objects.equals(this.divertingStreamId, stream.divertingStreamId) &&
            Objects.equals(this.receivingStreamId, stream.receivingStreamId) &&
            Objects.equals(this.confluenceBank, stream.confluenceBank) &&
            Objects.equals(this.diversionBank, stream.diversionBank) &&
            Objects.equals(this.streamLength, stream.streamLength) &&
            Objects.equals(this.confluenceStation, stream.confluenceStation) &&
            Objects.equals(this.diversionStation, stream.diversionStation) &&
            Objects.equals(this.streamLocations, stream.streamLocations) &&
            Objects.equals(this.officeId, stream.officeId) &&
            Objects.equals(this.comment, stream.comment) &&
            Objects.equals(this.averageSlope, stream.averageSlope);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streamName, tributaries, streamReaches, divertingStreamId,
            receivingStreamId, confluenceBank, diversionBank, streamLength, confluenceStation,
            diversionStation, streamLocations, officeId, comment, averageSlope);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Stream {\n");

        sb.append("    streamName: ").append(toIndentedString(streamName)).append("\n");
        sb.append("    tributaries: ").append(toIndentedString(tributaries)).append("\n");
        sb.append("    streamReaches: ").append(toIndentedString(streamReaches)).append("\n");
        sb.append("    divertingStreamId: ").append(toIndentedString(divertingStreamId))
            .append("\n");
        sb.append("    receivingStreamId: ").append(toIndentedString(receivingStreamId))
            .append("\n");
        sb.append("    confluenceBank: ").append(toIndentedString(confluenceBank)).append("\n");
        sb.append("    diversionBank: ").append(toIndentedString(diversionBank)).append("\n");
        sb.append("    streamLength: ").append(toIndentedString(streamLength)).append("\n");
        sb.append("    confluenceStation: ").append(toIndentedString(confluenceStation))
            .append("\n");
        sb.append("    diversionStation: ").append(toIndentedString(diversionStation)).append("\n");
        sb.append("    streamLocations: ").append(toIndentedString(streamLocations)).append("\n");
        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
        sb.append("    averageSlope: ").append(toIndentedString(averageSlope)).append("\n");
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
