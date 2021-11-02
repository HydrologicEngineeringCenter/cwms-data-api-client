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
 * StreamReach
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class StreamReach {
    @JsonProperty("upstreamLocationName")
    private String upstreamLocationName = null;

    @JsonProperty("downstreamLocationName")
    private String downstreamLocationName = null;

    @JsonProperty("streamName")
    private String streamName = null;

    @JsonProperty("reachName")
    private String reachName = null;

    @JsonProperty("officeId")
    private String officeId = null;

    @JsonProperty("comment")
    private String comment = null;

    @JsonProperty("configuration")
    private String _configuration = null;

    public StreamReach upstreamLocationName(String upstreamLocationName) {
        this.upstreamLocationName = upstreamLocationName;
        return this;
    }

    /**
     * Get upstreamLocationName
     *
     * @return upstreamLocationName
     **/
    @ApiModelProperty(value = "")

    public String getUpstreamLocationName() {
        return upstreamLocationName;
    }

    public void setUpstreamLocationName(String upstreamLocationName) {
        this.upstreamLocationName = upstreamLocationName;
    }

    public StreamReach downstreamLocationName(String downstreamLocationName) {
        this.downstreamLocationName = downstreamLocationName;
        return this;
    }

    /**
     * Get downstreamLocationName
     *
     * @return downstreamLocationName
     **/
    @ApiModelProperty(value = "")

    public String getDownstreamLocationName() {
        return downstreamLocationName;
    }

    public void setDownstreamLocationName(String downstreamLocationName) {
        this.downstreamLocationName = downstreamLocationName;
    }

    public StreamReach streamName(String streamName) {
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

    public StreamReach reachName(String reachName) {
        this.reachName = reachName;
        return this;
    }

    /**
     * Get reachName
     *
     * @return reachName
     **/
    @ApiModelProperty(value = "")

    public String getReachName() {
        return reachName;
    }

    public void setReachName(String reachName) {
        this.reachName = reachName;
    }

    public StreamReach officeId(String officeId) {
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

    public StreamReach comment(String comment) {
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

    public StreamReach _configuration(String _configuration) {
        this._configuration = _configuration;
        return this;
    }

    /**
     * Get _configuration
     *
     * @return _configuration
     **/
    @ApiModelProperty(value = "")

    public String getConfiguration() {
        return _configuration;
    }

    public void setConfiguration(String _configuration) {
        this._configuration = _configuration;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StreamReach streamReach = (StreamReach) o;
        return Objects.equals(this.upstreamLocationName, streamReach.upstreamLocationName) &&
            Objects.equals(this.downstreamLocationName, streamReach.downstreamLocationName) &&
            Objects.equals(this.streamName, streamReach.streamName) &&
            Objects.equals(this.reachName, streamReach.reachName) &&
            Objects.equals(this.officeId, streamReach.officeId) &&
            Objects.equals(this.comment, streamReach.comment) &&
            Objects.equals(this._configuration, streamReach._configuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(upstreamLocationName, downstreamLocationName, streamName, reachName,
            officeId, comment, _configuration);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StreamReach {\n");

        sb.append("    upstreamLocationName: ").append(toIndentedString(upstreamLocationName))
            .append("\n");
        sb.append("    downstreamLocationName: ").append(toIndentedString(downstreamLocationName))
            .append("\n");
        sb.append("    streamName: ").append(toIndentedString(streamName)).append("\n");
        sb.append("    reachName: ").append(toIndentedString(reachName)).append("\n");
        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
        sb.append("    _configuration: ").append(toIndentedString(_configuration)).append("\n");
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
