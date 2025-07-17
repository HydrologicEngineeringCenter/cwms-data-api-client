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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * TurbineSetting
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-07-12T12:05:19.921706600-07:00[America/Los_Angeles]")
public class TurbineSetting {

    @JsonProperty("location-id")
    private CwmsId locationId = null;

    @JsonProperty("discharge-units")
    private String dischargeUnits = null;

    @JsonProperty("old-discharge")
    private Double oldDischarge = null;

    @JsonProperty("new-discharge")
    private Double newDischarge = null;

    @JsonProperty("generation-units")
    private String generationUnits = null;

    @JsonProperty("scheduled-load")
    private Double scheduledLoad = null;

    @JsonProperty("real-power")
    private Double realPower = null;

    public TurbineSetting locationId(CwmsId locationId) {
        this.locationId = locationId;
        return this;
    }

    public CwmsId getLocationId() {
        return locationId;
    }

    public void setLocationId(CwmsId locationId) {
        this.locationId = locationId;
    }

    public TurbineSetting dischargeUnits(String dischargeUnits) {
        this.dischargeUnits = dischargeUnits;
        return this;
    }

    public String getDischargeUnits() {
        return dischargeUnits;
    }

    public void setDischargeUnits(String dischargeUnits) {
        this.dischargeUnits = dischargeUnits;
    }

    public TurbineSetting oldDischarge(Double oldDischarge) {
        this.oldDischarge = oldDischarge;
        return this;
    }

    public Double getOldDischarge() {
        return oldDischarge;
    }

    public void setOldDischarge(Double oldDischarge) {
        this.oldDischarge = oldDischarge;
    }

    public TurbineSetting newDischarge(Double newDischarge) {
        this.newDischarge = newDischarge;
        return this;
    }

    public Double getNewDischarge() {
        return newDischarge;
    }

    public void setNewDischarge(Double newDischarge) {
        this.newDischarge = newDischarge;
    }

    public TurbineSetting generationUnits(String generationUnits) {
        this.generationUnits = generationUnits;
        return this;
    }

    public String getGenerationUnits() {
        return generationUnits;
    }

    public void setGenerationUnits(String generationUnits) {
        this.generationUnits = generationUnits;
    }

    public TurbineSetting scheduledLoad(Double scheduledLoad) {
        this.scheduledLoad = scheduledLoad;
        return this;
    }

    public Double getScheduledLoad() {
        return scheduledLoad;
    }

    public void setScheduledLoad(Double scheduledLoad) {
        this.scheduledLoad = scheduledLoad;
    }

    public TurbineSetting realPower(Double realPower) {
        this.realPower = realPower;
        return this;
    }

    public Double getRealPower() {
        return realPower;
    }

    public void setRealPower(Double realPower) {
        this.realPower = realPower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TurbineSetting turbineSetting = (TurbineSetting) o;
        return Objects.equals(this.locationId, turbineSetting.locationId)
            && this.dischargeUnits == null || turbineSetting.dischargeUnits == null ?
            Objects.equals(this.dischargeUnits, turbineSetting.dischargeUnits) :
            this.dischargeUnits.equalsIgnoreCase(turbineSetting.dischargeUnits)
                && Objects.equals(this.oldDischarge, turbineSetting.oldDischarge)
                && Objects.equals(this.newDischarge, turbineSetting.newDischarge)
                && this.generationUnits == null || turbineSetting.generationUnits == null ?
                Objects.equals(this.generationUnits, turbineSetting.generationUnits) :
                this.generationUnits.equalsIgnoreCase(turbineSetting.generationUnits)
                    && Objects.equals(this.scheduledLoad, turbineSetting.scheduledLoad)
                    && Objects.equals(this.realPower, turbineSetting.realPower)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, dischargeUnits == null ? 0 : dischargeUnits.toLowerCase(), oldDischarge,
            newDischarge, generationUnits == null ? 0 : generationUnits.toLowerCase(), scheduledLoad, realPower);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TurbineSetting {\n");

        sb.append("    locationId: ").append(toIndentedString(locationId)).append("\n");
        sb.append("    dischargeUnits: ").append(toIndentedString(dischargeUnits)).append("\n");
        sb.append("    oldDischarge: ").append(toIndentedString(oldDischarge)).append("\n");
        sb.append("    newDischarge: ").append(toIndentedString(newDischarge)).append("\n");
        sb.append("    generationUnits: ").append(toIndentedString(generationUnits)).append("\n");
        sb.append("    scheduledLoad: ").append(toIndentedString(scheduledLoad)).append("\n");
        sb.append("    realPower: ").append(toIndentedString(realPower)).append("\n");
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
