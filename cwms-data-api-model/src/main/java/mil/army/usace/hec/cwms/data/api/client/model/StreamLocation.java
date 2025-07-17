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
 * StreamLocation
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-07-10T11:35:36.439001-07:00[America/Los_Angeles]")
public class StreamLocation {

    @JsonProperty("stream-location-node")
    private StreamLocationNode streamLocationNode = null;

    @JsonProperty("published-station")
    private Double publishedStation = null;

    @JsonProperty("navigation-station")
    private Double navigationStation = null;

    @JsonProperty("lowest-measurable-stage")
    private Double lowestMeasurableStage = null;

    @JsonProperty("total-drainage-area")
    private Double totalDrainageArea = null;

    @JsonProperty("ungaged-drainage-area")
    private Double ungagedDrainageArea = null;

    @JsonProperty("area-units")
    private String areaUnits = null;

    @JsonProperty("stage-units")
    private String stageUnits = null;

    @JsonIgnore
    public String getOfficeId() {
        String retVal = null;
        StreamLocationNode streamLocNode = getStreamLocationNode();
        if(streamLocNode != null) {
            CwmsId id = streamLocNode.getId();
            if(id != null) {
                retVal = id.getOfficeId();
            }
        }
        return retVal;
    }

    public StreamLocation streamLocationNode(StreamLocationNode streamLocationNode) {
        this.streamLocationNode = streamLocationNode;
        return this;
    }

    public StreamLocationNode getStreamLocationNode() {
        return streamLocationNode;
    }

    public void setStreamLocationNode(StreamLocationNode streamLocationNode) {
        this.streamLocationNode = streamLocationNode;
    }

    @JsonIgnore
    public CwmsId getId() {
        CwmsId retVal = null;
        StreamLocationNode streamLocNode = getStreamLocationNode();
        if(streamLocNode != null) {
            retVal = streamLocNode.getId();
        }
        return retVal;
    }

    public StreamLocation publishedStation(Double publishedStation) {
        this.publishedStation = publishedStation;
        return this;
    }

    public Double getPublishedStation() {
        return publishedStation;
    }

    public void setPublishedStation(Double publishedStation) {
        this.publishedStation = publishedStation;
    }

    public StreamLocation navigationStation(Double navigationStation) {
        this.navigationStation = navigationStation;
        return this;
    }

    public Double getNavigationStation() {
        return navigationStation;
    }

    public void setNavigationStation(Double navigationStation) {
        this.navigationStation = navigationStation;
    }

    public StreamLocation lowestMeasurableStage(Double lowestMeasurableStage) {
        this.lowestMeasurableStage = lowestMeasurableStage;
        return this;
    }

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

    public Double getUngagedDrainageArea() {
        return ungagedDrainageArea;
    }

    public void setUngagedDrainageArea(Double ungagedDrainageArea) {
        this.ungagedDrainageArea = ungagedDrainageArea;
    }

    public StreamLocation areaUnits(String areaUnits) {
        this.areaUnits = areaUnits;
        return this;
    }

    public String getAreaUnits() {
        return areaUnits;
    }

    public void setAreaUnits(String areaUnits) {
        this.areaUnits = areaUnits;
    }

    public StreamLocation stageUnits(String stageUnits) {
        this.stageUnits = stageUnits;
        return this;
    }

    public String getStageUnits() {
        return stageUnits;
    }

    public void setStageUnits(String stageUnits) {
        this.stageUnits = stageUnits;
    }

    @JsonIgnore
    public CwmsId getStreamId() {
        CwmsId retVal = null;
        StreamLocationNode streamLocNode = getStreamLocationNode();
        if(streamLocNode != null) {
            StreamNode streamNode = streamLocNode.getStreamNode();
            if(streamNode != null) {
                retVal = streamNode.getStreamId();
            }
        }
        return retVal;
    }

    @JsonIgnore
    public String getStationUnits() {
        String retVal = null;
        StreamLocationNode streamLocNode = getStreamLocationNode();
        if(streamLocNode != null) {
            StreamNode streamNode = streamLocNode.getStreamNode();
            if(streamNode != null) {
                retVal = streamNode.getStationUnits();
            }
        }
        return retVal;
    }

    @JsonIgnore
    public Double getStation() {
        Double retVal = null;
        StreamLocationNode streamLocNode = getStreamLocationNode();
        if(streamLocNode != null) {
            StreamNode streamNode = streamLocNode.getStreamNode();
            if(streamNode != null) {
                retVal = streamNode.getStation();
            }
        }
        return retVal;
    }

    @JsonIgnore
    public Bank getBank() {
        Bank retVal = null;
        StreamLocationNode streamLocNode = getStreamLocationNode();
        if(streamLocNode != null) {
            StreamNode streamNode = streamLocNode.getStreamNode();
            if(streamNode != null) {
                retVal = streamNode.getBank();
            }
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
        StreamLocation streamLocation = (StreamLocation) o;
        return Objects.equals(this.streamLocationNode, streamLocation.streamLocationNode)
         && Objects.equals(this.publishedStation, streamLocation.publishedStation)
         && Objects.equals(this.navigationStation, streamLocation.navigationStation)
         && Objects.equals(this.lowestMeasurableStage, streamLocation.lowestMeasurableStage)
         && Objects.equals(this.totalDrainageArea, streamLocation.totalDrainageArea)
         && Objects.equals(this.ungagedDrainageArea, streamLocation.ungagedDrainageArea)
         && this.areaUnits == null || streamLocation.areaUnits == null?Objects.equals(this.areaUnits, streamLocation.areaUnits):this.areaUnits.equalsIgnoreCase(streamLocation.areaUnits)
         && this.stageUnits == null || streamLocation.stageUnits == null?Objects.equals(this.stageUnits, streamLocation.stageUnits):this.stageUnits.equalsIgnoreCase(streamLocation.stageUnits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streamLocationNode, publishedStation, navigationStation, lowestMeasurableStage, totalDrainageArea, ungagedDrainageArea, areaUnits==null?0:areaUnits.toLowerCase(), stageUnits==null?0:stageUnits.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StreamLocation {\n");
        sb.append("    streamLocationNode: ").append(toIndentedString(streamLocationNode)).append("\n");
        sb.append("    publishedStation: ").append(toIndentedString(publishedStation)).append("\n");
        sb.append("    navigationStation: ").append(toIndentedString(navigationStation)).append("\n");
        sb.append("    lowestMeasurableStage: ").append(toIndentedString(lowestMeasurableStage)).append("\n");
        sb.append("    totalDrainageArea: ").append(toIndentedString(totalDrainageArea)).append("\n");
        sb.append("    ungagedDrainageArea: ").append(toIndentedString(ungagedDrainageArea)).append("\n");
        sb.append("    areaUnits: ").append(toIndentedString(areaUnits)).append("\n");
        sb.append("    stageUnits: ").append(toIndentedString(stageUnits)).append("\n");
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
