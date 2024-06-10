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
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * RatingSpec
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-09T09:29:33.859-07:00[America/Los_Angeles]")
public class RatingSpec {
    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("rating-id")
    private String ratingId = null;

    @JsonProperty("template-id")
    private String templateId = null;

    @JsonProperty("location-id")
    private String locationId = null;

    @JsonProperty("version")
    private String version = null;

    @JsonProperty("source-agency")
    private String sourceAgency = null;

    @JsonProperty("in-range-method")
    private String inRangeMethod = null;

    @JsonProperty("out-range-low-method")
    private String outRangeLowMethod = null;

    @JsonProperty("out-range-high-method")
    private String outRangeHighMethod = null;

    @JsonProperty("active")
    private Boolean active = null;

    @JsonProperty("auto-update")
    private Boolean autoUpdate = null;

    @JsonProperty("auto-activate")
    private Boolean autoActivate = null;

    @JsonProperty("auto-migrate-extension")
    private Boolean autoMigrateExtension = null;

    @JsonProperty("independent-rounding-specs")
    @Valid
    private List<IndependentRoundingSpec> independentRoundingSpecs = new ArrayList<>();

    @JsonProperty("dependent-rounding-spec")
    private String dependentRoundingSpec = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("effective-dates")
    @Valid
    private List<OffsetDateTime> effectiveDates = new ArrayList<>();

    public RatingSpec officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    /**
     * Get officeId
     *
     * @return officeId
     **/

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public RatingSpec ratingId(String ratingId) {
        this.ratingId = ratingId;
        return this;
    }

    /**
     * Get ratingId
     *
     * @return ratingId
     **/

    public String getRatingId() {
        return ratingId;
    }

    public void setRatingId(String ratingId) {
        this.ratingId = ratingId;
    }

    public RatingSpec templateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    /**
     * Get templateId
     *
     * @return templateId
     **/

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public RatingSpec locationId(String locationId) {
        this.locationId = locationId;
        return this;
    }

    /**
     * Get locationId
     *
     * @return locationId
     **/

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public RatingSpec version(String version) {
        this.version = version;
        return this;
    }

    /**
     * Get version
     *
     * @return version
     **/

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public RatingSpec sourceAgency(String sourceAgency) {
        this.sourceAgency = sourceAgency;
        return this;
    }

    /**
     * Get sourceAgency
     *
     * @return sourceAgency
     **/

    public String getSourceAgency() {
        return sourceAgency;
    }

    public void setSourceAgency(String sourceAgency) {
        this.sourceAgency = sourceAgency;
    }

    public RatingSpec inRangeMethod(String inRangeMethod) {
        this.inRangeMethod = inRangeMethod;
        return this;
    }

    /**
     * Get inRangeMethod
     *
     * @return inRangeMethod
     **/

    public String getInRangeMethod() {
        return inRangeMethod;
    }

    public void setInRangeMethod(String inRangeMethod) {
        this.inRangeMethod = inRangeMethod;
    }

    public RatingSpec outRangeLowMethod(String outRangeLowMethod) {
        this.outRangeLowMethod = outRangeLowMethod;
        return this;
    }

    /**
     * Get outRangeLowMethod
     *
     * @return outRangeLowMethod
     **/

    public String getOutRangeLowMethod() {
        return outRangeLowMethod;
    }

    public void setOutRangeLowMethod(String outRangeLowMethod) {
        this.outRangeLowMethod = outRangeLowMethod;
    }

    public RatingSpec outRangeHighMethod(String outRangeHighMethod) {
        this.outRangeHighMethod = outRangeHighMethod;
        return this;
    }

    /**
     * Get outRangeHighMethod
     *
     * @return outRangeHighMethod
     **/

    public String getOutRangeHighMethod() {
        return outRangeHighMethod;
    }

    public void setOutRangeHighMethod(String outRangeHighMethod) {
        this.outRangeHighMethod = outRangeHighMethod;
    }

    public RatingSpec active(Boolean active) {
        this.active = active;
        return this;
    }

    /**
     * Get active
     *
     * @return active
     **/

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public RatingSpec autoUpdate(Boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
        return this;
    }

    /**
     * Get autoUpdate
     *
     * @return autoUpdate
     **/

    public Boolean isAutoUpdate() {
        return autoUpdate;
    }

    public void setAutoUpdate(Boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
    }

    public RatingSpec autoActivate(Boolean autoActivate) {
        this.autoActivate = autoActivate;
        return this;
    }

    /**
     * Get autoActivate
     *
     * @return autoActivate
     **/

    public Boolean isAutoActivate() {
        return autoActivate;
    }

    public void setAutoActivate(Boolean autoActivate) {
        this.autoActivate = autoActivate;
    }

    public RatingSpec autoMigrateExtension(Boolean autoMigrateExtension) {
        this.autoMigrateExtension = autoMigrateExtension;
        return this;
    }

    /**
     * Get autoMigrateExtension
     *
     * @return autoMigrateExtension
     **/

    public Boolean isAutoMigrateExtension() {
        return autoMigrateExtension;
    }

    public void setAutoMigrateExtension(Boolean autoMigrateExtension) {
        this.autoMigrateExtension = autoMigrateExtension;
    }

    public RatingSpec independentRoundingSpecs(List<IndependentRoundingSpec> independentRoundingSpecs) {
        this.independentRoundingSpecs = independentRoundingSpecs;
        return this;
    }

    public RatingSpec addIndependentRoundingSpecsItem(IndependentRoundingSpec independentRoundingSpecsItem) {
        if (this.independentRoundingSpecs == null) {
            this.independentRoundingSpecs = new ArrayList<IndependentRoundingSpec>();
        }
        this.independentRoundingSpecs.add(independentRoundingSpecsItem);
        return this;
    }

    /**
     * Get independentRoundingSpecs
     *
     * @return independentRoundingSpecs
     **/
    @Valid
    public List<IndependentRoundingSpec> getIndependentRoundingSpecs() {
        return independentRoundingSpecs;
    }

    public void setIndependentRoundingSpecs(List<IndependentRoundingSpec> independentRoundingSpecs) {
        this.independentRoundingSpecs = independentRoundingSpecs;
    }

    public RatingSpec dependentRoundingSpec(String dependentRoundingSpec) {
        this.dependentRoundingSpec = dependentRoundingSpec;
        return this;
    }

    /**
     * Get dependentRoundingSpec
     *
     * @return dependentRoundingSpec
     **/

    public String getDependentRoundingSpec() {
        return dependentRoundingSpec;
    }

    public void setDependentRoundingSpec(String dependentRoundingSpec) {
        this.dependentRoundingSpec = dependentRoundingSpec;
    }

    public RatingSpec description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get description
     *
     * @return description
     **/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RatingSpec effectiveDates(List<OffsetDateTime> effectiveDates) {
        this.effectiveDates = effectiveDates;
        return this;
    }

    public RatingSpec addEffectiveDatesItem(OffsetDateTime effectiveDatesItem) {
        if (this.effectiveDates == null) {
            this.effectiveDates = new ArrayList<OffsetDateTime>();
        }
        this.effectiveDates.add(effectiveDatesItem);
        return this;
    }

    /**
     * Get effectiveDates
     *
     * @return effectiveDates
     **/
    @Valid
    public List<OffsetDateTime> getEffectiveDates() {
        return effectiveDates;
    }

    public void setEffectiveDates(List<OffsetDateTime> effectiveDates) {
        this.effectiveDates = effectiveDates;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RatingSpec ratingSpec = (RatingSpec) o;
        return this.officeId == null || ratingSpec.officeId == null ? Objects.equals(this.officeId, ratingSpec.officeId) :
            this.officeId.equalsIgnoreCase(ratingSpec.officeId) && this.ratingId == null || ratingSpec.ratingId == null ?
                Objects.equals(this.ratingId, ratingSpec.ratingId) :
                this.ratingId.equalsIgnoreCase(ratingSpec.ratingId) && this.templateId == null || ratingSpec.templateId == null ?
                    Objects.equals(this.templateId, ratingSpec.templateId) :
                    this.templateId.equalsIgnoreCase(ratingSpec.templateId) && this.locationId == null || ratingSpec.locationId == null ?
                        Objects.equals(this.locationId, ratingSpec.locationId) :
                        this.locationId.equalsIgnoreCase(ratingSpec.locationId) && this.version == null || ratingSpec.version == null ?
                            Objects.equals(this.version, ratingSpec.version) :
                            this.version.equalsIgnoreCase(ratingSpec.version) && this.sourceAgency == null || ratingSpec.sourceAgency == null ?
                                Objects.equals(this.sourceAgency, ratingSpec.sourceAgency) :
                                this.sourceAgency.equalsIgnoreCase(ratingSpec.sourceAgency) && this.inRangeMethod == null ||
                                    ratingSpec.inRangeMethod == null ? Objects.equals(this.inRangeMethod, ratingSpec.inRangeMethod) :
                                    this.inRangeMethod.equalsIgnoreCase(ratingSpec.inRangeMethod) && this.outRangeLowMethod == null ||
                                        ratingSpec.outRangeLowMethod == null ? Objects.equals(this.outRangeLowMethod, ratingSpec.outRangeLowMethod) :
                                        this.outRangeLowMethod.equalsIgnoreCase(ratingSpec.outRangeLowMethod) && this.outRangeHighMethod == null ||
                                            ratingSpec.outRangeHighMethod == null ?
                                            Objects.equals(this.outRangeHighMethod, ratingSpec.outRangeHighMethod) :
                                            this.outRangeHighMethod.equalsIgnoreCase(ratingSpec.outRangeHighMethod) &&
                                                Objects.equals(this.active, ratingSpec.active) &&
                                                Objects.equals(this.autoUpdate, ratingSpec.autoUpdate) &&
                                                Objects.equals(this.autoActivate, ratingSpec.autoActivate) &&
                                                Objects.equals(this.autoMigrateExtension, ratingSpec.autoMigrateExtension) &&
                                                Objects.equals(this.independentRoundingSpecs, ratingSpec.independentRoundingSpecs) &&
                                                this.dependentRoundingSpec == null || ratingSpec.dependentRoundingSpec == null ?
                                                Objects.equals(this.dependentRoundingSpec, ratingSpec.dependentRoundingSpec) :
                                                this.dependentRoundingSpec.equalsIgnoreCase(ratingSpec.dependentRoundingSpec) &&
                                                    this.description == null || ratingSpec.description == null ?
                                                    Objects.equals(this.description, ratingSpec.description) :
                                                    this.description.equalsIgnoreCase(ratingSpec.description) &&
                                                        Objects.equals(this.effectiveDates, ratingSpec.effectiveDates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeId == null ? 0 : officeId.toLowerCase(), ratingId == null ? 0 : ratingId.toLowerCase(),
            templateId == null ? 0 : templateId.toLowerCase(), locationId == null ? 0 : locationId.toLowerCase(),
            version == null ? 0 : version.toLowerCase(), sourceAgency == null ? 0 : sourceAgency.toLowerCase(),
            inRangeMethod == null ? 0 : inRangeMethod.toLowerCase(), outRangeLowMethod == null ? 0 : outRangeLowMethod.toLowerCase(),
            outRangeHighMethod == null ? 0 : outRangeHighMethod.toLowerCase(), active, autoUpdate, autoActivate, autoMigrateExtension,
            independentRoundingSpecs, dependentRoundingSpec == null ? 0 : dependentRoundingSpec.toLowerCase(),
            description == null ? 0 : description.toLowerCase(), effectiveDates);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RatingSpec {\n");

        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    ratingId: ").append(toIndentedString(ratingId)).append("\n");
        sb.append("    templateId: ").append(toIndentedString(templateId)).append("\n");
        sb.append("    locationId: ").append(toIndentedString(locationId)).append("\n");
        sb.append("    version: ").append(toIndentedString(version)).append("\n");
        sb.append("    sourceAgency: ").append(toIndentedString(sourceAgency)).append("\n");
        sb.append("    inRangeMethod: ").append(toIndentedString(inRangeMethod)).append("\n");
        sb.append("    outRangeLowMethod: ").append(toIndentedString(outRangeLowMethod)).append("\n");
        sb.append("    outRangeHighMethod: ").append(toIndentedString(outRangeHighMethod)).append("\n");
        sb.append("    active: ").append(toIndentedString(active)).append("\n");
        sb.append("    autoUpdate: ").append(toIndentedString(autoUpdate)).append("\n");
        sb.append("    autoActivate: ").append(toIndentedString(autoActivate)).append("\n");
        sb.append("    autoMigrateExtension: ").append(toIndentedString(autoMigrateExtension)).append("\n");
        sb.append("    independentRoundingSpecs: ").append(toIndentedString(independentRoundingSpecs)).append("\n");
        sb.append("    dependentRoundingSpec: ").append(toIndentedString(dependentRoundingSpec)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    effectiveDates: ").append(toIndentedString(effectiveDates)).append("\n");
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
