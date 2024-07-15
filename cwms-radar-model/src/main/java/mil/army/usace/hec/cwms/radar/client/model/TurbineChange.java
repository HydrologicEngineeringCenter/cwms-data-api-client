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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TurbineChange
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-07-12T12:05:19.921706600-07:00[America/Los_Angeles]")
public class TurbineChange {

    @JsonProperty("project-id")
    private CwmsId projectId = null;

    @JsonProperty("change-date")
    private Instant changeDate = null;

    @JsonProperty("protected")
    private Boolean isProtected = null;

    @JsonProperty("discharge-computation-type")
    private LookupType dischargeComputationType = null;

    @JsonProperty("reason-type")
    private LookupType reasonType = null;

    @JsonProperty("notes")
    private String notes = null;

    @JsonProperty("new-total-discharge-override")
    private Double newTotalDischargeOverride = null;

    @JsonProperty("old-total-discharge-override")
    private Double oldTotalDischargeOverride = null;

    @JsonProperty("discharge-units")
    private String dischargeUnits = null;

    @JsonProperty("pool-elevation")
    private Double poolElevation = null;

    @JsonProperty("tailwater-elevation")
    private Double tailwaterElevation = null;

    @JsonProperty("elevation-units")
    private String elevationUnits = null;

    @JsonProperty("settings")
    @Valid
    private List<TurbineSetting> settings = new ArrayList<>();

    public TurbineChange projectId(CwmsId projectId) {
        this.projectId = projectId;
        return this;
    }

    public CwmsId getProjectId() {
        return projectId;
    }

    public void setProjectId(CwmsId projectId) {
        this.projectId = projectId;
    }

    public TurbineChange changeDate(Instant changeDate) {
        this.changeDate = changeDate;
        return this;
    }

    public Instant getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Instant changeDate) {
        this.changeDate = changeDate;
    }

    public TurbineChange isProtected(Boolean isProtected) {
        this.isProtected = isProtected;
        return this;
    }

    public Boolean isProtected() {
        return isProtected;
    }

    public void setProtected(Boolean _protected) {
        this.isProtected = _protected;
    }

    public TurbineChange dischargeComputationType(LookupType dischargeComputationType) {
        this.dischargeComputationType = dischargeComputationType;
        return this;
    }

    public LookupType getDischargeComputationType() {
        return dischargeComputationType;
    }

    public void setDischargeComputationType(LookupType dischargeComputationType) {
        this.dischargeComputationType = dischargeComputationType;
    }

    public TurbineChange reasonType(LookupType reasonType) {
        this.reasonType = reasonType;
        return this;
    }

    public LookupType getReasonType() {
        return reasonType;
    }

    public void setReasonType(LookupType reasonType) {
        this.reasonType = reasonType;
    }

    public TurbineChange notes(String notes) {
        this.notes = notes;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public TurbineChange newTotalDischargeOverride(Double newTotalDischargeOverride) {
        this.newTotalDischargeOverride = newTotalDischargeOverride;
        return this;
    }

    public Double getNewTotalDischargeOverride() {
        return newTotalDischargeOverride;
    }

    public void setNewTotalDischargeOverride(Double newTotalDischargeOverride) {
        this.newTotalDischargeOverride = newTotalDischargeOverride;
    }

    public TurbineChange oldTotalDischargeOverride(Double oldTotalDischargeOverride) {
        this.oldTotalDischargeOverride = oldTotalDischargeOverride;
        return this;
    }

    public Double getOldTotalDischargeOverride() {
        return oldTotalDischargeOverride;
    }

    public void setOldTotalDischargeOverride(Double oldTotalDischargeOverride) {
        this.oldTotalDischargeOverride = oldTotalDischargeOverride;
    }

    public TurbineChange dischargeUnits(String dischargeUnits) {
        this.dischargeUnits = dischargeUnits;
        return this;
    }

    public String getDischargeUnits() {
        return dischargeUnits;
    }

    public void setDischargeUnits(String dischargeUnits) {
        this.dischargeUnits = dischargeUnits;
    }

    public TurbineChange poolElevation(Double poolElevation) {
        this.poolElevation = poolElevation;
        return this;
    }

    public Double getPoolElevation() {
        return poolElevation;
    }

    public void setPoolElevation(Double poolElevation) {
        this.poolElevation = poolElevation;
    }

    public TurbineChange tailwaterElevation(Double tailwaterElevation) {
        this.tailwaterElevation = tailwaterElevation;
        return this;
    }

    public Double getTailwaterElevation() {
        return tailwaterElevation;
    }

    public void setTailwaterElevation(Double tailwaterElevation) {
        this.tailwaterElevation = tailwaterElevation;
    }

    public TurbineChange elevationUnits(String elevationUnits) {
        this.elevationUnits = elevationUnits;
        return this;
    }

    public String getElevationUnits() {
        return elevationUnits;
    }

    public void setElevationUnits(String elevationUnits) {
        this.elevationUnits = elevationUnits;
    }

    public TurbineChange settings(List<TurbineSetting> settings) {
        this.settings = settings;
        return this;
    }

    public TurbineChange addSettingsItem(TurbineSetting settingsItem) {
        if (this.settings == null) {
            this.settings = new ArrayList<>();
        }
        this.settings.add(settingsItem);
        return this;
    }

    public List<TurbineSetting> getSettings() {
        return settings;
    }

    public void setSettings(List<TurbineSetting> settings) {
        this.settings = settings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TurbineChange turbineChange = (TurbineChange) o;
        return Objects.equals(this.projectId, turbineChange.projectId)
            && Objects.equals(this.changeDate, turbineChange.changeDate)
            && Objects.equals(this.isProtected, turbineChange.isProtected)
            && Objects.equals(this.dischargeComputationType, turbineChange.dischargeComputationType)
            && Objects.equals(this.reasonType, turbineChange.reasonType)
            && this.notes == null || turbineChange.notes == null ? Objects.equals(this.notes, turbineChange.notes) :
            this.notes.equalsIgnoreCase(turbineChange.notes)
                && Objects.equals(this.newTotalDischargeOverride, turbineChange.newTotalDischargeOverride)
                && Objects.equals(this.oldTotalDischargeOverride, turbineChange.oldTotalDischargeOverride)
                && this.dischargeUnits == null || turbineChange.dischargeUnits == null ?
                Objects.equals(this.dischargeUnits, turbineChange.dischargeUnits) :
                this.dischargeUnits.equalsIgnoreCase(turbineChange.dischargeUnits)
                    && Objects.equals(this.poolElevation, turbineChange.poolElevation)
                    && Objects.equals(this.tailwaterElevation, turbineChange.tailwaterElevation)
                    && this.elevationUnits == null || turbineChange.elevationUnits == null ?
                    Objects.equals(this.elevationUnits, turbineChange.elevationUnits) :
                    this.elevationUnits.equalsIgnoreCase(turbineChange.elevationUnits)
                        && Objects.equals(this.settings, turbineChange.settings)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, changeDate, isProtected, dischargeComputationType, reasonType,
            notes == null ? 0 : notes.toLowerCase(), newTotalDischargeOverride, oldTotalDischargeOverride,
            dischargeUnits == null ? 0 : dischargeUnits.toLowerCase(), poolElevation, tailwaterElevation,
            elevationUnits == null ? 0 : elevationUnits.toLowerCase(), settings);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TurbineChange {\n");

        sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
        sb.append("    changeDate: ").append(toIndentedString(changeDate)).append("\n");
        sb.append("    _protected: ").append(toIndentedString(isProtected)).append("\n");
        sb.append("    dischargeComputationType: ").append(toIndentedString(dischargeComputationType)).append("\n");
        sb.append("    reasonType: ").append(toIndentedString(reasonType)).append("\n");
        sb.append("    notes: ").append(toIndentedString(notes)).append("\n");
        sb.append("    newTotalDischargeOverride: ").append(toIndentedString(newTotalDischargeOverride)).append("\n");
        sb.append("    oldTotalDischargeOverride: ").append(toIndentedString(oldTotalDischargeOverride)).append("\n");
        sb.append("    dischargeUnits: ").append(toIndentedString(dischargeUnits)).append("\n");
        sb.append("    poolElevation: ").append(toIndentedString(poolElevation)).append("\n");
        sb.append("    tailwaterElevation: ").append(toIndentedString(tailwaterElevation)).append("\n");
        sb.append("    elevationUnits: ").append(toIndentedString(elevationUnits)).append("\n");
        sb.append("    settings: ").append(toIndentedString(settings)).append("\n");
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
