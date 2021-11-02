/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;

/**
 * LocationLevel
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class LocationLevel {
    @JsonProperty("seasonal-time-series-id")
    private String seasonalTimeSeriesId = null;

    @JsonProperty("seasonal-values")
    @Valid
    private List<SeasonalValueBean> seasonalValues = null;

    @JsonProperty("specified-level-id")
    private String specifiedLevelId = null;

    @JsonProperty("parameter-type-id")
    private String parameterTypeId = null;

    @JsonProperty("parameter-id")
    private String parameterId = null;

    @JsonProperty("si-parameter-units-constant-value")
    private Double siParameterUnitsConstantValue = null;

    @JsonProperty("level-units-id")
    private String levelUnitsId = null;

    @JsonProperty("level-date")
    private OffsetDateTime levelDate = null;

    @JsonProperty("level-comment")
    private String levelComment = null;

    @JsonProperty("interval-origin")
    private OffsetDateTime intervalOrigin = null;

    @JsonProperty("interval-months")
    private Integer intervalMonths = null;

    @JsonProperty("interval-minutes")
    private Integer intervalMinutes = null;

    @JsonProperty("interpolate-string")
    private String interpolateString = null;

    @JsonProperty("duration-id")
    private String durationId = null;

    @JsonProperty("attribute-value")
    private BigDecimal attributeValue = null;

    @JsonProperty("attribute-units-id")
    private String attributeUnitsId = null;

    @JsonProperty("attribute-parameter-type-id")
    private String attributeParameterTypeId = null;

    @JsonProperty("attribute-parameter-id")
    private String attributeParameterId = null;

    @JsonProperty("attribute-duration-id")
    private String attributeDurationId = null;

    @JsonProperty("attribute-comment")
    private String attributeComment = null;

    @JsonProperty("location-id")
    private String locationId = null;

    @JsonProperty("office-id")
    private String officeId = null;

    public LocationLevel seasonalTimeSeriesId(String seasonalTimeSeriesId) {
        this.seasonalTimeSeriesId = seasonalTimeSeriesId;
        return this;
    }

    /**
     * Get seasonalTimeSeriesId
     *
     * @return seasonalTimeSeriesId
     **/
    @ApiModelProperty(value = "")

    public String getSeasonalTimeSeriesId() {
        return seasonalTimeSeriesId;
    }

    public void setSeasonalTimeSeriesId(String seasonalTimeSeriesId) {
        this.seasonalTimeSeriesId = seasonalTimeSeriesId;
    }

    public LocationLevel seasonalValues(List<SeasonalValueBean> seasonalValues) {
        this.seasonalValues = seasonalValues;
        return this;
    }

    public LocationLevel addSeasonalValuesItem(SeasonalValueBean seasonalValuesItem) {
        if (this.seasonalValues == null) {
            this.seasonalValues = new ArrayList<SeasonalValueBean>();
        }
        this.seasonalValues.add(seasonalValuesItem);
        return this;
    }

    /**
     * Get seasonalValues
     *
     * @return seasonalValues
     **/
    @ApiModelProperty(value = "")
    @Valid
    public List<SeasonalValueBean> getSeasonalValues() {
        return seasonalValues;
    }

    public void setSeasonalValues(List<SeasonalValueBean> seasonalValues) {
        this.seasonalValues = seasonalValues;
    }

    public LocationLevel specifiedLevelId(String specifiedLevelId) {
        this.specifiedLevelId = specifiedLevelId;
        return this;
    }

    /**
     * Get specifiedLevelId
     *
     * @return specifiedLevelId
     **/
    @ApiModelProperty(value = "")

    public String getSpecifiedLevelId() {
        return specifiedLevelId;
    }

    public void setSpecifiedLevelId(String specifiedLevelId) {
        this.specifiedLevelId = specifiedLevelId;
    }

    public LocationLevel parameterTypeId(String parameterTypeId) {
        this.parameterTypeId = parameterTypeId;
        return this;
    }

    /**
     * Get parameterTypeId
     *
     * @return parameterTypeId
     **/
    @ApiModelProperty(value = "")

    public String getParameterTypeId() {
        return parameterTypeId;
    }

    public void setParameterTypeId(String parameterTypeId) {
        this.parameterTypeId = parameterTypeId;
    }

    public LocationLevel parameterId(String parameterId) {
        this.parameterId = parameterId;
        return this;
    }

    /**
     * Get parameterId
     *
     * @return parameterId
     **/
    @ApiModelProperty(value = "")

    public String getParameterId() {
        return parameterId;
    }

    public void setParameterId(String parameterId) {
        this.parameterId = parameterId;
    }

    public LocationLevel siParameterUnitsConstantValue(Double siParameterUnitsConstantValue) {
        this.siParameterUnitsConstantValue = siParameterUnitsConstantValue;
        return this;
    }

    /**
     * Get siParameterUnitsConstantValue
     *
     * @return siParameterUnitsConstantValue
     **/
    @ApiModelProperty(value = "")

    public Double getSiParameterUnitsConstantValue() {
        return siParameterUnitsConstantValue;
    }

    public void setSiParameterUnitsConstantValue(Double siParameterUnitsConstantValue) {
        this.siParameterUnitsConstantValue = siParameterUnitsConstantValue;
    }

    public LocationLevel levelUnitsId(String levelUnitsId) {
        this.levelUnitsId = levelUnitsId;
        return this;
    }

    /**
     * Get levelUnitsId
     *
     * @return levelUnitsId
     **/
    @ApiModelProperty(value = "")

    public String getLevelUnitsId() {
        return levelUnitsId;
    }

    public void setLevelUnitsId(String levelUnitsId) {
        this.levelUnitsId = levelUnitsId;
    }

    public LocationLevel levelDate(OffsetDateTime levelDate) {
        this.levelDate = levelDate;
        return this;
    }

    /**
     * Get levelDate
     *
     * @return levelDate
     **/
    @ApiModelProperty(value = "")

    @Valid
    public OffsetDateTime getLevelDate() {
        return levelDate;
    }

    public void setLevelDate(OffsetDateTime levelDate) {
        this.levelDate = levelDate;
    }

    public LocationLevel levelComment(String levelComment) {
        this.levelComment = levelComment;
        return this;
    }

    /**
     * Get levelComment
     *
     * @return levelComment
     **/
    @ApiModelProperty(value = "")

    public String getLevelComment() {
        return levelComment;
    }

    public void setLevelComment(String levelComment) {
        this.levelComment = levelComment;
    }

    public LocationLevel intervalOrigin(OffsetDateTime intervalOrigin) {
        this.intervalOrigin = intervalOrigin;
        return this;
    }

    /**
     * Get intervalOrigin
     *
     * @return intervalOrigin
     **/
    @ApiModelProperty(value = "")

    @Valid
    public OffsetDateTime getIntervalOrigin() {
        return intervalOrigin;
    }

    public void setIntervalOrigin(OffsetDateTime intervalOrigin) {
        this.intervalOrigin = intervalOrigin;
    }

    public LocationLevel intervalMonths(Integer intervalMonths) {
        this.intervalMonths = intervalMonths;
        return this;
    }

    /**
     * Get intervalMonths
     *
     * @return intervalMonths
     **/
    @ApiModelProperty(value = "")

    public Integer getIntervalMonths() {
        return intervalMonths;
    }

    public void setIntervalMonths(Integer intervalMonths) {
        this.intervalMonths = intervalMonths;
    }

    public LocationLevel intervalMinutes(Integer intervalMinutes) {
        this.intervalMinutes = intervalMinutes;
        return this;
    }

    /**
     * Get intervalMinutes
     *
     * @return intervalMinutes
     **/
    @ApiModelProperty(value = "")

    public Integer getIntervalMinutes() {
        return intervalMinutes;
    }

    public void setIntervalMinutes(Integer intervalMinutes) {
        this.intervalMinutes = intervalMinutes;
    }

    public LocationLevel interpolateString(String interpolateString) {
        this.interpolateString = interpolateString;
        return this;
    }

    /**
     * Get interpolateString
     *
     * @return interpolateString
     **/
    @ApiModelProperty(value = "")

    public String getInterpolateString() {
        return interpolateString;
    }

    public void setInterpolateString(String interpolateString) {
        this.interpolateString = interpolateString;
    }

    public LocationLevel durationId(String durationId) {
        this.durationId = durationId;
        return this;
    }

    /**
     * Get durationId
     *
     * @return durationId
     **/
    @ApiModelProperty(value = "")

    public String getDurationId() {
        return durationId;
    }

    public void setDurationId(String durationId) {
        this.durationId = durationId;
    }

    public LocationLevel attributeValue(BigDecimal attributeValue) {
        this.attributeValue = attributeValue;
        return this;
    }

    /**
     * Get attributeValue
     *
     * @return attributeValue
     **/
    @ApiModelProperty(value = "")

    @Valid
    public BigDecimal getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(BigDecimal attributeValue) {
        this.attributeValue = attributeValue;
    }

    public LocationLevel attributeUnitsId(String attributeUnitsId) {
        this.attributeUnitsId = attributeUnitsId;
        return this;
    }

    /**
     * Get attributeUnitsId
     *
     * @return attributeUnitsId
     **/
    @ApiModelProperty(value = "")

    public String getAttributeUnitsId() {
        return attributeUnitsId;
    }

    public void setAttributeUnitsId(String attributeUnitsId) {
        this.attributeUnitsId = attributeUnitsId;
    }

    public LocationLevel attributeParameterTypeId(String attributeParameterTypeId) {
        this.attributeParameterTypeId = attributeParameterTypeId;
        return this;
    }

    /**
     * Get attributeParameterTypeId
     *
     * @return attributeParameterTypeId
     **/
    @ApiModelProperty(value = "")

    public String getAttributeParameterTypeId() {
        return attributeParameterTypeId;
    }

    public void setAttributeParameterTypeId(String attributeParameterTypeId) {
        this.attributeParameterTypeId = attributeParameterTypeId;
    }

    public LocationLevel attributeParameterId(String attributeParameterId) {
        this.attributeParameterId = attributeParameterId;
        return this;
    }

    /**
     * Get attributeParameterId
     *
     * @return attributeParameterId
     **/
    @ApiModelProperty(value = "")

    public String getAttributeParameterId() {
        return attributeParameterId;
    }

    public void setAttributeParameterId(String attributeParameterId) {
        this.attributeParameterId = attributeParameterId;
    }

    public LocationLevel attributeDurationId(String attributeDurationId) {
        this.attributeDurationId = attributeDurationId;
        return this;
    }

    /**
     * Get attributeDurationId
     *
     * @return attributeDurationId
     **/
    @ApiModelProperty(value = "")

    public String getAttributeDurationId() {
        return attributeDurationId;
    }

    public void setAttributeDurationId(String attributeDurationId) {
        this.attributeDurationId = attributeDurationId;
    }

    public LocationLevel attributeComment(String attributeComment) {
        this.attributeComment = attributeComment;
        return this;
    }

    /**
     * Get attributeComment
     *
     * @return attributeComment
     **/
    @ApiModelProperty(value = "")

    public String getAttributeComment() {
        return attributeComment;
    }

    public void setAttributeComment(String attributeComment) {
        this.attributeComment = attributeComment;
    }

    public LocationLevel locationId(String locationId) {
        this.locationId = locationId;
        return this;
    }

    /**
     * Get locationId
     *
     * @return locationId
     **/
    @ApiModelProperty(value = "")

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public LocationLevel officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    /**
     * Get officeId
     *
     * @return officeId
     **/
    @ApiModelProperty(value = "")

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LocationLevel locationLevel = (LocationLevel) o;
        return Objects.equals(this.seasonalTimeSeriesId, locationLevel.seasonalTimeSeriesId) &&
            Objects.equals(this.seasonalValues, locationLevel.seasonalValues) &&
            Objects.equals(this.specifiedLevelId, locationLevel.specifiedLevelId) &&
            Objects.equals(this.parameterTypeId, locationLevel.parameterTypeId) &&
            Objects.equals(this.parameterId, locationLevel.parameterId) &&
            Objects.equals(this.siParameterUnitsConstantValue,
                locationLevel.siParameterUnitsConstantValue) &&
            Objects.equals(this.levelUnitsId, locationLevel.levelUnitsId) &&
            Objects.equals(this.levelDate, locationLevel.levelDate) &&
            Objects.equals(this.levelComment, locationLevel.levelComment) &&
            Objects.equals(this.intervalOrigin, locationLevel.intervalOrigin) &&
            Objects.equals(this.intervalMonths, locationLevel.intervalMonths) &&
            Objects.equals(this.intervalMinutes, locationLevel.intervalMinutes) &&
            Objects.equals(this.interpolateString, locationLevel.interpolateString) &&
            Objects.equals(this.durationId, locationLevel.durationId) &&
            Objects.equals(this.attributeValue, locationLevel.attributeValue) &&
            Objects.equals(this.attributeUnitsId, locationLevel.attributeUnitsId) &&
            Objects.equals(this.attributeParameterTypeId, locationLevel.attributeParameterTypeId) &&
            Objects.equals(this.attributeParameterId, locationLevel.attributeParameterId) &&
            Objects.equals(this.attributeDurationId, locationLevel.attributeDurationId) &&
            Objects.equals(this.attributeComment, locationLevel.attributeComment) &&
            Objects.equals(this.locationId, locationLevel.locationId) &&
            Objects.equals(this.officeId, locationLevel.officeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seasonalTimeSeriesId, seasonalValues, specifiedLevelId, parameterTypeId,
            parameterId, siParameterUnitsConstantValue, levelUnitsId, levelDate, levelComment,
            intervalOrigin, intervalMonths, intervalMinutes, interpolateString, durationId,
            attributeValue, attributeUnitsId, attributeParameterTypeId, attributeParameterId,
            attributeDurationId, attributeComment, locationId, officeId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LocationLevel {\n");

        sb.append("    seasonalTimeSeriesId: ").append(toIndentedString(seasonalTimeSeriesId))
            .append("\n");
        sb.append("    seasonalValues: ").append(toIndentedString(seasonalValues)).append("\n");
        sb.append("    specifiedLevelId: ").append(toIndentedString(specifiedLevelId)).append("\n");
        sb.append("    parameterTypeId: ").append(toIndentedString(parameterTypeId)).append("\n");
        sb.append("    parameterId: ").append(toIndentedString(parameterId)).append("\n");
        sb.append("    siParameterUnitsConstantValue: ")
            .append(toIndentedString(siParameterUnitsConstantValue)).append("\n");
        sb.append("    levelUnitsId: ").append(toIndentedString(levelUnitsId)).append("\n");
        sb.append("    levelDate: ").append(toIndentedString(levelDate)).append("\n");
        sb.append("    levelComment: ").append(toIndentedString(levelComment)).append("\n");
        sb.append("    intervalOrigin: ").append(toIndentedString(intervalOrigin)).append("\n");
        sb.append("    intervalMonths: ").append(toIndentedString(intervalMonths)).append("\n");
        sb.append("    intervalMinutes: ").append(toIndentedString(intervalMinutes)).append("\n");
        sb.append("    interpolateString: ").append(toIndentedString(interpolateString))
            .append("\n");
        sb.append("    durationId: ").append(toIndentedString(durationId)).append("\n");
        sb.append("    attributeValue: ").append(toIndentedString(attributeValue)).append("\n");
        sb.append("    attributeUnitsId: ").append(toIndentedString(attributeUnitsId)).append("\n");
        sb.append("    attributeParameterTypeId: ")
            .append(toIndentedString(attributeParameterTypeId)).append("\n");
        sb.append("    attributeParameterId: ").append(toIndentedString(attributeParameterId))
            .append("\n");
        sb.append("    attributeDurationId: ").append(toIndentedString(attributeDurationId))
            .append("\n");
        sb.append("    attributeComment: ").append(toIndentedString(attributeComment)).append("\n");
        sb.append("    locationId: ").append(toIndentedString(locationId)).append("\n");
        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
