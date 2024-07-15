/*
 * MIT License
 *
 * Copyright (c) 2024 Hydrologic Engineering Center
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * StreamReach
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-07-10T11:35:36.439001-07:00[America/Los_Angeles]")
public class StreamReach {

    @JsonProperty("comment")
    private String comment = null;

    @JsonProperty("downstream-node")
    private StreamLocationNode downstreamNode = null;

    @JsonProperty("upstream-node")
    private StreamLocationNode upstreamNode = null;

    @JsonProperty("configuration-id")
    private CwmsId configurationId = null;

    @JsonProperty("stream-id")
    private CwmsId streamId = null;

    @JsonProperty("id")
    private CwmsId id = null;

    @JsonIgnore
    public String getOfficeId() {
        String retVal = null;
        if(id != null)
        {
            retVal = id.getOfficeId();
        }
        return retVal;
    }

    public StreamReach comment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public StreamReach downstreamNode(StreamLocationNode downstreamNode) {
        this.downstreamNode = downstreamNode;
        return this;
    }

    public StreamLocationNode getDownstreamNode() {
        return downstreamNode;
    }

    public void setDownstreamNode(StreamLocationNode downstreamNode) {
        this.downstreamNode = downstreamNode;
    }

    public StreamReach upstreamNode(StreamLocationNode upstreamNode) {
        this.upstreamNode = upstreamNode;
        return this;
    }

    public StreamLocationNode getUpstreamNode() {
        return upstreamNode;
    }

    public void setUpstreamNode(StreamLocationNode upstreamNode) {
        this.upstreamNode = upstreamNode;
    }

    public StreamReach configurationId(CwmsId configurationId) {
        this.configurationId = configurationId;
        return this;
    }

    public CwmsId getConfigurationId() {
        return configurationId;
    }

    public void setConfigurationId(CwmsId configurationId) {
        this.configurationId = configurationId;
    }

    public StreamReach streamId(CwmsId streamId) {
        this.streamId = streamId;
        return this;
    }

    public CwmsId getStreamId() {
        return streamId;
    }

    public void setStreamId(CwmsId streamId) {
        this.streamId = streamId;
    }

    public StreamReach id(CwmsId id) {
        this.id = id;
        return this;
    }

    public CwmsId getId() {
        return id;
    }

    public void setId(CwmsId id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        StreamReach streamReach = (StreamReach) o;
        return this.comment == null || streamReach.comment == null?Objects.equals(this.comment, streamReach.comment):this.comment.equalsIgnoreCase(streamReach.comment)
         && Objects.equals(this.downstreamNode, streamReach.downstreamNode)
         && Objects.equals(this.upstreamNode, streamReach.upstreamNode)
         && Objects.equals(this.configurationId, streamReach.configurationId)
         && Objects.equals(this.streamId, streamReach.streamId)
         && Objects.equals(this.id, streamReach.id)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment==null?0:comment.toLowerCase(), downstreamNode, upstreamNode, configurationId, streamId, id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StreamReach {\n");
        
        sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
        sb.append("    downstreamNode: ").append(toIndentedString(downstreamNode)).append("\n");
        sb.append("    upstreamNode: ").append(toIndentedString(upstreamNode)).append("\n");
        sb.append("    configurationId: ").append(toIndentedString(configurationId)).append("\n");
        sb.append("    streamId: ").append(toIndentedString(streamId)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
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
