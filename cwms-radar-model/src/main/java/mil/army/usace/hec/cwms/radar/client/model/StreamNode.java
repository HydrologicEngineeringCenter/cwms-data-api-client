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

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * StreamNode
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-07-10T11:35:36.439001-07:00[America/Los_Angeles]")
public class StreamNode {

    @JsonProperty("stream-id")
    private CwmsId streamId = null;

    @JsonProperty("bank")
    private Bank bank = null;

    @JsonProperty("station")
    private Double station = null;

    @JsonProperty("station-units")
    private String stationUnits = null;

    public StreamNode streamId(CwmsId streamId) {
        this.streamId = streamId;
        return this;
    }

    public CwmsId getStreamId() {
        return streamId;
    }

    public void setStreamId(CwmsId streamId) {
        this.streamId = streamId;
    }

    public StreamNode bank(Bank bank) {
        this.bank = bank;
        return this;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public StreamNode station(Double station) {
        this.station = station;
        return this;
    }

    public Double getStation() {
        return station;
    }

    public void setStation(Double station) {
        this.station = station;
    }

    public StreamNode stationUnits(String stationUnits) {
        this.stationUnits = stationUnits;
        return this;
    }

    public String getStationUnits() {
        return stationUnits;
    }

    public void setStationUnits(String stationUnits) {
        this.stationUnits = stationUnits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        StreamNode streamNode = (StreamNode) o;
        return Objects.equals(this.streamId, streamNode.streamId)
         && this.bank == null || streamNode.bank == null ? Objects.equals(this.bank, streamNode.bank) : this.bank.getCode().equalsIgnoreCase(streamNode.bank.getCode())
         && Objects.equals(this.station, streamNode.station)
         && this.stationUnits == null || streamNode.stationUnits == null?Objects.equals(this.stationUnits, streamNode.stationUnits):this.stationUnits.equalsIgnoreCase(streamNode.stationUnits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streamId, bank==null?0:bank.getCode().toLowerCase(), station, stationUnits==null?0:stationUnits.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StreamNode {\n");
        
        sb.append("    streamId: ").append(toIndentedString(streamId)).append("\n");
        sb.append("    bank: ").append(toIndentedString(bank)).append("\n");
        sb.append("    station: ").append(toIndentedString(station)).append("\n");
        sb.append("    stationUnits: ").append(toIndentedString(stationUnits)).append("\n");
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
