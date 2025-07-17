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
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Rating Metadata
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-10-04T12:33:40.401-07:00[America/Los_Angeles]")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "rating-type", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = TableRating.class, name = "table"),
    @JsonSubTypes.Type(value = TransitionalRating.class, name = "transitional"),
    @JsonSubTypes.Type(value = VirtualRating.class, name = "virtual"),
    @JsonSubTypes.Type(value = ExpressionRating.class, name = "expression-rating"),
    @JsonSubTypes.Type(value = UsgsStreamRating.class, name = "usgs"),
})
public class AbstractRatingMetadata {
    @JsonProperty("rating-type")
    private String ratingType = null;

    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("rating-spec-id")
    private String ratingSpecId = null;

    @JsonProperty("units-id")
    private String unitsId = null;

    @JsonProperty("active")
    private Boolean active = null;

    @JsonProperty("effective-date")
    private ZonedDateTime effectiveDate = null;

    @JsonProperty("create-date")
    private ZonedDateTime createDate = null;

    @JsonProperty("transition-date")
    private ZonedDateTime transitionDate = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("vertical-datum-info")
    private VerticalDatumInfo verticalDatumInfo = null;

    public AbstractRatingMetadata ratingType(String ratingType) {
        this.ratingType = ratingType;
        return this;
    }

    /**
     * Get ratingType
     *
     * @return ratingType
     **/

    public String getRatingType() {
        return ratingType;
    }

    public void setRatingType(String ratingType) {
        this.ratingType = ratingType;
    }

    public AbstractRatingMetadata officeId(String officeId) {
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

    public AbstractRatingMetadata ratingSpecId(String ratingSpecId) {
        this.ratingSpecId = ratingSpecId;
        return this;
    }

    /**
     * Get ratingSpecId
     *
     * @return ratingSpecId
     **/

    public String getRatingSpecId() {
        return ratingSpecId;
    }

    public void setRatingSpecId(String ratingSpecId) {
        this.ratingSpecId = ratingSpecId;
    }

    public AbstractRatingMetadata unitsId(String unitsId) {
        this.unitsId = unitsId;
        return this;
    }

    /**
     * Get unitsId
     *
     * @return unitsId
     **/

    public String getUnitsId() {
        return unitsId;
    }

    public void setUnitsId(String unitsId) {
        this.unitsId = unitsId;
    }

    public AbstractRatingMetadata active(Boolean active) {
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

    public AbstractRatingMetadata effectiveDate(ZonedDateTime effectiveDate) {
        this.effectiveDate = effectiveDate;
        return this;
    }

    /**
     * Get effectiveDate
     *
     * @return effectiveDate
     **/

    @Valid
    public ZonedDateTime getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(ZonedDateTime effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public AbstractRatingMetadata createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    /**
     * Get createDate
     *
     * @return createDate
     **/

    @Valid
    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public AbstractRatingMetadata transitionDate(ZonedDateTime transitionDate) {
        this.transitionDate = transitionDate;
        return this;
    }

    /**
     * Get transitionDate
     *
     * @return transitionDate
     **/

    @Valid
    public ZonedDateTime getTransitionDate() {
        return transitionDate;
    }

    public void setTransitionDate(ZonedDateTime transitionDate) {
        this.transitionDate = transitionDate;
    }

    public AbstractRatingMetadata description(String description) {
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

    public AbstractRatingMetadata verticalDatumInfo(VerticalDatumInfo verticalDatumInfo) {
        this.verticalDatumInfo = verticalDatumInfo;
        return this;
    }

    /**
     * Get verticalDatumInfo
     *
     * @return verticalDatumInfo
     **/

    @Valid
    public VerticalDatumInfo getVerticalDatumInfo() {
        return verticalDatumInfo;
    }

    public void setVerticalDatumInfo(VerticalDatumInfo verticalDatumInfo) {
        this.verticalDatumInfo = verticalDatumInfo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractRatingMetadata abstractRatingMetadata = (AbstractRatingMetadata) o;
        return this.ratingType == null || abstractRatingMetadata.ratingType == null ?
            Objects.equals(this.ratingType, abstractRatingMetadata.ratingType) : this.ratingType.equalsIgnoreCase(abstractRatingMetadata.ratingType)
            && this.officeId == null || abstractRatingMetadata.officeId == null ? Objects.equals(this.officeId, abstractRatingMetadata.officeId) :
            this.officeId.equalsIgnoreCase(abstractRatingMetadata.officeId)
                && this.ratingSpecId == null || abstractRatingMetadata.ratingSpecId == null ?
                Objects.equals(this.ratingSpecId, abstractRatingMetadata.ratingSpecId) :
                this.ratingSpecId.equalsIgnoreCase(abstractRatingMetadata.ratingSpecId)
                    && this.unitsId == null || abstractRatingMetadata.unitsId == null ? Objects.equals(this.unitsId, abstractRatingMetadata.unitsId) :
                    this.unitsId.equalsIgnoreCase(abstractRatingMetadata.unitsId)
                        && Objects.equals(this.active, abstractRatingMetadata.active)
                        && Objects.equals(this.effectiveDate, abstractRatingMetadata.effectiveDate)
                        && Objects.equals(this.createDate, abstractRatingMetadata.createDate)
                        && Objects.equals(this.transitionDate, abstractRatingMetadata.transitionDate)
                        && this.description == null || abstractRatingMetadata.description == null ?
                        Objects.equals(this.description, abstractRatingMetadata.description) :
                        this.description.equalsIgnoreCase(abstractRatingMetadata.description)
                            && Objects.equals(this.verticalDatumInfo, abstractRatingMetadata.verticalDatumInfo)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratingType == null ? 0 : ratingType.toLowerCase(), officeId == null ? 0 : officeId.toLowerCase(),
            ratingSpecId == null ? 0 : ratingSpecId.toLowerCase(), unitsId == null ? 0 : unitsId.toLowerCase(), active, effectiveDate, createDate,
            transitionDate, description == null ? 0 : description.toLowerCase(), verticalDatumInfo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AbstractRatingMetadata {\n");

        sb.append("    ratingType: ").append(toIndentedString(ratingType)).append("\n");
        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    ratingSpecId: ").append(toIndentedString(ratingSpecId)).append("\n");
        sb.append("    unitsId: ").append(toIndentedString(unitsId)).append("\n");
        sb.append("    active: ").append(toIndentedString(active)).append("\n");
        sb.append("    effectiveDate: ").append(toIndentedString(effectiveDate)).append("\n");
        sb.append("    createDate: ").append(toIndentedString(createDate)).append("\n");
        sb.append("    transitionDate: ").append(toIndentedString(transitionDate)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    verticalDatumInfo: ").append(toIndentedString(verticalDatumInfo)).append("\n");
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
