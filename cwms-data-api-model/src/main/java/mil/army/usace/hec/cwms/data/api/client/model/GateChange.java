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

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * GateChange
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-10-11T16:58:13.325841800-07:00[America/Los_Angeles]")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true )
@JsonTypeName("gate-change")
public class GateChange {

    @JsonProperty("project-id")
    private CwmsId projectId = null;

    @JsonProperty("change-date")
    private OffsetDateTime changeDate = null;

    @JsonProperty("reference-elevation")
    private Double referenceElevation = null;

    @JsonProperty("pool-elevation")
    private Double poolElevation = null;

    @JsonProperty("protected")
    private Boolean _protected = null;

    @JsonProperty("discharge-computation-type")
    private LookupType dischargeComputationType = null;

    @JsonProperty("reason-type")
    private LookupType reasonType = null;

    @JsonProperty("notes")
    private String notes = null;

    @JsonProperty("type")
    private final String type = "gate-change";

    @JsonProperty("new-total-discharge-override")
    private Double newTotalDischargeOverride = null;

    @JsonProperty("old-total-discharge-override")
    private Double oldTotalDischargeOverride = null;

    @JsonProperty("discharge-units")
    private String dischargeUnits = null;

    @JsonProperty("tailwater-elevation")
    private Double tailwaterElevation = null;

    @JsonProperty("elevation-units")
    private String elevationUnits = null;

    @JsonProperty("settings")
    @Valid
    private List<GateSetting> settings = new ArrayList<>();

    public GateChange projectId(CwmsId projectId) {
        this.projectId = projectId;
        return this;
    }

    public CwmsId getProjectId() {
        return projectId;
    }

    public void setProjectId(CwmsId projectId) {
        this.projectId = projectId;
    }

    public GateChange changeDate(OffsetDateTime changeDate) {
        this.changeDate = changeDate;
        return this;
    }

    public OffsetDateTime getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(OffsetDateTime changeDate) {
        this.changeDate = changeDate;
    }

    public GateChange referenceElevation(Double referenceElevation) {
        this.referenceElevation = referenceElevation;
        return this;
    }

    public Double getReferenceElevation() {
        return referenceElevation;
    }

    public void setReferenceElevation(Double referenceElevation) {
        this.referenceElevation = referenceElevation;
    }

    public GateChange poolElevation(Double poolElevation) {
        this.poolElevation = poolElevation;
        return this;
    }

    public Double getPoolElevation() {
        return poolElevation;
    }

    public void setPoolElevation(Double poolElevation) {
        this.poolElevation = poolElevation;
    }

    public GateChange _protected(Boolean _protected) {
        this._protected = _protected;
        return this;
    }

    public Boolean isProtected() {
        return _protected;
    }

    public void setProtected(Boolean _protected) {
        this._protected = _protected;
    }

    public GateChange dischargeComputationType(LookupType dischargeComputationType) {
        this.dischargeComputationType = dischargeComputationType;
        return this;
    }

    public LookupType getDischargeComputationType() {
        return dischargeComputationType;
    }

    public void setDischargeComputationType(LookupType dischargeComputationType) {
        this.dischargeComputationType = dischargeComputationType;
    }

    public GateChange reasonType(LookupType reasonType) {
        this.reasonType = reasonType;
        return this;
    }

    public LookupType getReasonType() {
        return reasonType;
    }

    public void setReasonType(LookupType reasonType) {
        this.reasonType = reasonType;
    }

    public GateChange notes(String notes) {
        this.notes = notes;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public GateChange newTotalDischargeOverride(Double newTotalDischargeOverride) {
        this.newTotalDischargeOverride = newTotalDischargeOverride;
        return this;
    }

    public Double getNewTotalDischargeOverride() {
        return newTotalDischargeOverride;
    }

    public void setNewTotalDischargeOverride(Double newTotalDischargeOverride) {
        this.newTotalDischargeOverride = newTotalDischargeOverride;
    }

    public GateChange oldTotalDischargeOverride(Double oldTotalDischargeOverride) {
        this.oldTotalDischargeOverride = oldTotalDischargeOverride;
        return this;
    }

    public Double getOldTotalDischargeOverride() {
        return oldTotalDischargeOverride;
    }

    public void setOldTotalDischargeOverride(Double oldTotalDischargeOverride) {
        this.oldTotalDischargeOverride = oldTotalDischargeOverride;
    }

    public GateChange dischargeUnits(String dischargeUnits) {
        this.dischargeUnits = dischargeUnits;
        return this;
    }

    public String getDischargeUnits() {
        return dischargeUnits;
    }

    public void setDischargeUnits(String dischargeUnits) {
        this.dischargeUnits = dischargeUnits;
    }

    public GateChange tailwaterElevation(Double tailwaterElevation) {
        this.tailwaterElevation = tailwaterElevation;
        return this;
    }

    public Double getTailwaterElevation() {
        return tailwaterElevation;
    }

    public void setTailwaterElevation(Double tailwaterElevation) {
        this.tailwaterElevation = tailwaterElevation;
    }

    public GateChange elevationUnits(String elevationUnits) {
        this.elevationUnits = elevationUnits;
        return this;
    }

    public String getElevationUnits() {
        return elevationUnits;
    }

    public void setElevationUnits(String elevationUnits) {
        this.elevationUnits = elevationUnits;
    }

    public GateChange settings(List<GateSetting> settings) {
        this.settings = settings;
        return this;
    }

    public GateChange addSettingsItem(GateSetting settingsItem) {
        if (this.settings == null) {
            this.settings = new ArrayList<>();
        }
        this.settings.add(settingsItem);
        return this;
    }

    public List<GateSetting> getSettings() {
        return settings;
    }

    public void setSettings(List<GateSetting> settings) {
        this.settings = settings;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GateChange gateChange = (GateChange) o;
        return Objects.equals(this.projectId, gateChange.projectId)
                && Objects.equals(this.changeDate, gateChange.changeDate)
                && Objects.equals(this.referenceElevation, gateChange.referenceElevation)
                && Objects.equals(this.poolElevation, gateChange.poolElevation)
                && Objects.equals(this._protected, gateChange._protected)
                && Objects.equals(this.dischargeComputationType, gateChange.dischargeComputationType)
                && Objects.equals(this.reasonType, gateChange.reasonType)
                && this.notes == null || gateChange.notes == null?Objects.equals(this.notes, gateChange.notes)
                    : this.notes.equalsIgnoreCase(gateChange.notes)
                && this.type == null || gateChange.type == null?Objects.equals(this.type, gateChange.type)
                    : this.type.equalsIgnoreCase(gateChange.type)
                && Objects.equals(this.newTotalDischargeOverride, gateChange.newTotalDischargeOverride)
                && Objects.equals(this.oldTotalDischargeOverride, gateChange.oldTotalDischargeOverride)
                && this.dischargeUnits == null || gateChange.dischargeUnits == null
                    ? Objects.equals(this.dischargeUnits, gateChange.dischargeUnits)
                    : this.dischargeUnits.equalsIgnoreCase(gateChange.dischargeUnits)
                && Objects.equals(this.tailwaterElevation, gateChange.tailwaterElevation)
                && this.elevationUnits == null || gateChange.elevationUnits == null
                    ? Objects.equals(this.elevationUnits, gateChange.elevationUnits)
                    : this.elevationUnits.equalsIgnoreCase(gateChange.elevationUnits)
                && Objects.equals(this.settings, gateChange.settings)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, changeDate, referenceElevation, poolElevation, _protected,
                dischargeComputationType, reasonType, notes == null ? 0 : notes.toLowerCase(), type == null
                        ? 0 : type.toLowerCase(), newTotalDischargeOverride, oldTotalDischargeOverride,
                dischargeUnits == null ? 0 : dischargeUnits.toLowerCase(), tailwaterElevation,
                elevationUnits == null ? 0 : elevationUnits.toLowerCase(), settings);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GateChange {\n");

        sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
        sb.append("    changeDate: ").append(toIndentedString(changeDate)).append("\n");
        sb.append("    referenceElevation: ").append(toIndentedString(referenceElevation)).append("\n");
        sb.append("    poolElevation: ").append(toIndentedString(poolElevation)).append("\n");
        sb.append("    _protected: ").append(toIndentedString(_protected)).append("\n");
        sb.append("    dischargeComputationType: ").append(toIndentedString(dischargeComputationType)).append("\n");
        sb.append("    reasonType: ").append(toIndentedString(reasonType)).append("\n");
        sb.append("    notes: ").append(toIndentedString(notes)).append("\n");
        sb.append("    newTotalDischargeOverride: ").append(toIndentedString(newTotalDischargeOverride)).append("\n");
        sb.append("    oldTotalDischargeOverride: ").append(toIndentedString(oldTotalDischargeOverride)).append("\n");
        sb.append("    dischargeUnits: ").append(toIndentedString(dischargeUnits)).append("\n");
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
