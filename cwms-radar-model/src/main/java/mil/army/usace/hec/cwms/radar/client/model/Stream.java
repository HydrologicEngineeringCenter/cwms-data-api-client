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
 * Stream
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-07-10T11:35:36.439001-07:00[America/Los_Angeles]")
public class Stream {

    @JsonProperty("starts-downstream")
    private Boolean startsDownstream = null;

    @JsonProperty("flows-into-stream-node")
    private StreamNode flowsIntoStreamNode = null;

    @JsonProperty("diverts-from-stream-node")
    private StreamNode divertsFromStreamNode = null;

    @JsonProperty("length")
    private Double length = null;

    @JsonProperty("average-slope")
    private Double averageSlope = null;

    @JsonProperty("length-units")
    private String lengthUnits = null;

    @JsonProperty("slope-units")
    private String slopeUnits = null;

    @JsonProperty("comment")
    private String comment = null;

    @JsonProperty("id")
    private CwmsId id = null;

    public Stream startsDownstream(Boolean startsDownstream) {
        this.startsDownstream = startsDownstream;
        return this;
    }

    public Boolean isStartsDownstream() {
        return startsDownstream;
    }

    public void setStartsDownstream(Boolean startsDownstream) {
        this.startsDownstream = startsDownstream;
    }

    public Stream flowsIntoStreamNode(StreamNode flowsIntoStreamNode) {
        this.flowsIntoStreamNode = flowsIntoStreamNode;
        return this;
    }

    public StreamNode getFlowsIntoStreamNode() {
        return flowsIntoStreamNode;
    }

    public void setFlowsIntoStreamNode(StreamNode flowsIntoStreamNode) {
        this.flowsIntoStreamNode = flowsIntoStreamNode;
    }

    public Stream divertsFromStreamNode(StreamNode divertsFromStreamNode) {
        this.divertsFromStreamNode = divertsFromStreamNode;
        return this;
    }

    public StreamNode getDivertsFromStreamNode() {
        return divertsFromStreamNode;
    }

    public void setDivertsFromStreamNode(StreamNode divertsFromStreamNode) {
        this.divertsFromStreamNode = divertsFromStreamNode;
    }

    public Stream length(Double length) {
        this.length = length;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Stream averageSlope(Double averageSlope) {
        this.averageSlope = averageSlope;
        return this;
    }

    public Double getAverageSlope() {
        return averageSlope;
    }

    public void setAverageSlope(Double averageSlope) {
        this.averageSlope = averageSlope;
    }

    public Stream lengthUnits(String lengthUnits) {
        this.lengthUnits = lengthUnits;
        return this;
    }

    public String getLengthUnits() {
        return lengthUnits;
    }

    public void setLengthUnits(String lengthUnits) {
        this.lengthUnits = lengthUnits;
    }

    public Stream slopeUnits(String slopeUnits) {
        this.slopeUnits = slopeUnits;
        return this;
    }

    public String getSlopeUnits() {
        return slopeUnits;
    }

    public void setSlopeUnits(String slopeUnits) {
        this.slopeUnits = slopeUnits;
    }

    public Stream comment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Stream id(CwmsId id) {
        this.id = id;
        return this;
    }

    public CwmsId getId() {
        return id;
    }

    public void setId(CwmsId id) {
        this.id = id;
    }

    @JsonIgnore
    public String getOfficeId() {
        String retVal = null;
        if(id != null)
        {
            retVal = id.getOfficeId();
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
        Stream stream = (Stream) o;
        return Objects.equals(this.startsDownstream, stream.startsDownstream)
         && Objects.equals(this.flowsIntoStreamNode, stream.flowsIntoStreamNode)
         && Objects.equals(this.divertsFromStreamNode, stream.divertsFromStreamNode)
         && Objects.equals(this.length, stream.length)
         && Objects.equals(this.averageSlope, stream.averageSlope)
         && this.lengthUnits == null || stream.lengthUnits == null?Objects.equals(this.lengthUnits, stream.lengthUnits):this.lengthUnits.equalsIgnoreCase(stream.lengthUnits)
         && this.slopeUnits == null || stream.slopeUnits == null?Objects.equals(this.slopeUnits, stream.slopeUnits):this.slopeUnits.equalsIgnoreCase(stream.slopeUnits)
         && this.comment == null || stream.comment == null?Objects.equals(this.comment, stream.comment):this.comment.equalsIgnoreCase(stream.comment)
         && Objects.equals(this.id, stream.id)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startsDownstream, flowsIntoStreamNode, divertsFromStreamNode, length, averageSlope, lengthUnits==null?0:lengthUnits.toLowerCase(), slopeUnits==null?0:slopeUnits.toLowerCase(), comment==null?0:comment.toLowerCase(), id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Stream {\n");
        
        sb.append("    startsDownstream: ").append(toIndentedString(startsDownstream)).append("\n");
        sb.append("    flowsIntoStreamNode: ").append(toIndentedString(flowsIntoStreamNode)).append("\n");
        sb.append("    divertsFromStreamNode: ").append(toIndentedString(divertsFromStreamNode)).append("\n");
        sb.append("    length: ").append(toIndentedString(length)).append("\n");
        sb.append("    averageSlope: ").append(toIndentedString(averageSlope)).append("\n");
        sb.append("    lengthUnits: ").append(toIndentedString(lengthUnits)).append("\n");
        sb.append("    slopeUnits: ").append(toIndentedString(slopeUnits)).append("\n");
        sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
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
