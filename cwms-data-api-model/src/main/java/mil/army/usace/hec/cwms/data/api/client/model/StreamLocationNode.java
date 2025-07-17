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
package mil.army.usace.hec.cwms.data.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * StreamLocationNode
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-07-10T11:35:36.439001-07:00[America/Los_Angeles]")
public class StreamLocationNode {

    @JsonProperty("id")
    private CwmsId id = null;

    @JsonProperty("stream-node")
    private StreamNode streamNode = null;

    public StreamLocationNode id(CwmsId id) {
        this.id = id;
        return this;
    }

    public CwmsId getId() {
        return id;
    }

    public void setId(CwmsId id) {
        this.id = id;
    }

    public StreamLocationNode streamNode(StreamNode streamNode) {
        this.streamNode = streamNode;
        return this;
    }

    public StreamNode getStreamNode() {
        return streamNode;
    }

    public void setStreamNode(StreamNode streamNode) {
        this.streamNode = streamNode;
    }

    //helper methods to extract from stream node
    @JsonIgnore
    public String getOfficeId() {
        String retVal = null;
        if(id != null)
        {
            retVal = id.getOfficeId();
        }
        return retVal;
    }

    @JsonIgnore
    public CwmsId getStreamId() {
        CwmsId retVal = null;
        if(streamNode != null)
        {
            retVal = streamNode.getStreamId();
        }
        return retVal;
    }

    @JsonIgnore
    public Bank getBank() {
        Bank retVal = null;
        if(streamNode != null)
        {
            retVal = streamNode.getBank();
        }
        return retVal;
    }

    @JsonIgnore
    public Double getStation() {
        Double retVal = null;
        if(streamNode != null)
        {
            retVal = streamNode.getStation();
        }
        return retVal;
    }

    @JsonIgnore
    public String getStationUnits() {
        String retVal = null;
        if(streamNode != null)
        {
            retVal = streamNode.getStationUnits();
        }
        return retVal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        StreamLocationNode streamLocationNode = (StreamLocationNode) o;
        return Objects.equals(this.id, streamLocationNode.id)
         && Objects.equals(this.streamNode, streamLocationNode.streamNode)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, streamNode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StreamLocationNode {\n");
        
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    streamNode: ").append(toIndentedString(streamNode)).append("\n");
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
