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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * LocationLevel
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-11T12:56:36.285-07:00[America/Los_Angeles]")
public class LocationLevel {
    @JsonProperty("location-level-id")
    private String locationLevelId = null;

    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("seasonal-time-series-id")
    private String seasonalTimeSeriesId = null;

    @JsonProperty("seasonal-values")
    @Valid
    private List<SeasonalValueBean> seasonalValues = new ArrayList<>();

    @JsonProperty("specified-level-id")
    private String specifiedLevelId = null;

    /**
     * To indicate if single or aggregate value
     */
    public enum ParameterTypeIdEnum {
        INST("Inst"),

        AVE("Ave"),

        MIN("Min"),

        MAX("Max"),

        TOTAL("Total");

        private String value;

        ParameterTypeIdEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static ParameterTypeIdEnum fromValue(String text) {
            for (ParameterTypeIdEnum b : ParameterTypeIdEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    @JsonProperty("parameter-type-id")
    private ParameterTypeIdEnum parameterTypeId = null;

    @JsonProperty("parameter-id")
    private String parameterId = null;

    @JsonProperty("constant-value")
    private Double constantValue = null;

    @JsonProperty("level-units-id")
    private String levelUnitsId = null;

    @JsonProperty("level-date")
    private ZonedDateTime levelDate = null;

    @JsonProperty("level-comment")
    private String levelComment = null;

    @JsonProperty("interval-origin")
    private ZonedDateTime intervalOrigin = null;

    @JsonProperty("interval-months")
    private Integer intervalMonths = null;

    @JsonProperty("interval-minutes")
    private Integer intervalMinutes = null;

    /**
     * Indicating whether or not to interpolate between seasonal values.
     */
    public enum InterpolateStringEnum {
        T("T"),

        F("F");

        private String value;

        InterpolateStringEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static InterpolateStringEnum fromValue(String text) {
            for (InterpolateStringEnum b : InterpolateStringEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    @JsonProperty("interpolate-string")
    private InterpolateStringEnum interpolateString = null;

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

    public LocationLevel locationLevelId(String locationLevelId) {
        this.locationLevelId = locationLevelId;
        return this;
    }

    /**
     * Name of the location level
     *
     * @return locationLevelId
     **/
    @NotNull

    public String getLocationLevelId() {
        return locationLevelId;
    }

    public void setLocationLevelId(String locationLevelId) {
        this.locationLevelId = locationLevelId;
    }

    public LocationLevel officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    /**
     * Owning office of the level
     *
     * @return officeId
     **/
    @NotNull

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public LocationLevel seasonalTimeSeriesId(String seasonalTimeSeriesId) {
        this.seasonalTimeSeriesId = seasonalTimeSeriesId;
        return this;
    }

    /**
     * Timeseries ID (e.g. from the times series catalog) to use as the location level. Mutually exclusive with seasonalValues and siParameterUnitsConstantValue
     *
     * @return seasonalTimeSeriesId
     **/

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
     * List of Repeating seasonal values. The values repeater after the specified interval. A yearly interval seasonable could have 12 different values, one for each month for example. Mutually exclusive with seasonalTimeSeriesId and siParameterUnitsConstantValue
     *
     * @return seasonalValues
     **/
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
     * Generic name of this location level. Common names are 'Top of Dam', 'Streambed', 'Bottom of Dam'.
     *
     * @return specifiedLevelId
     **/

    public String getSpecifiedLevelId() {
        return specifiedLevelId;
    }

    public void setSpecifiedLevelId(String specifiedLevelId) {
        this.specifiedLevelId = specifiedLevelId;
    }

    public LocationLevel parameterTypeId(ParameterTypeIdEnum parameterTypeId) {
        this.parameterTypeId = parameterTypeId;
        return this;
    }

    /**
     * To indicate if single or aggregate value
     *
     * @return parameterTypeId
     **/

    public ParameterTypeIdEnum getParameterTypeId() {
        return parameterTypeId;
    }

    public void setParameterTypeId(ParameterTypeIdEnum parameterTypeId) {
        this.parameterTypeId = parameterTypeId;
    }

    public LocationLevel parameterId(String parameterId) {
        this.parameterId = parameterId;
        return this;
    }

    /**
     * Data Type such as Stage, Elevation, or others.
     *
     * @return parameterId
     **/

    public String getParameterId() {
        return parameterId;
    }

    public void setParameterId(String parameterId) {
        this.parameterId = parameterId;
    }

    public LocationLevel constantValue(Double constantValue) {
        this.constantValue = constantValue;
        return this;
    }

    /**
     * Single value for this location level. Mutually exclusive with seasonableTimeSeriesId and seasonValues.
     *
     * @return constantValue
     **/

    public Double getConstantValue() {
        return constantValue;
    }

    public void setConstantValue(Double constantValue) {
        this.constantValue = constantValue;
    }

    public LocationLevel levelUnitsId(String levelUnitsId) {
        this.levelUnitsId = levelUnitsId;
        return this;
    }

    /**
     * Units thhe provided levels are in
     *
     * @return levelUnitsId
     **/

    public String getLevelUnitsId() {
        return levelUnitsId;
    }

    public void setLevelUnitsId(String levelUnitsId) {
        this.levelUnitsId = levelUnitsId;
    }

    public LocationLevel levelDate(ZonedDateTime levelDate) {
        this.levelDate = levelDate;
        return this;
    }

    /**
     * The date/time at which this location level configuration takes effect.
     *
     * @return levelDate
     **/

    @Valid
    public ZonedDateTime getLevelDate() {
        return levelDate;
    }

    public void setLevelDate(ZonedDateTime levelDate) {
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

    public String getLevelComment() {
        return levelComment;
    }

    public void setLevelComment(String levelComment) {
        this.levelComment = levelComment;
    }

    public LocationLevel intervalOrigin(ZonedDateTime intervalOrigin) {
        this.intervalOrigin = intervalOrigin;
        return this;
    }

    /**
     * The start point of provided seasonal values
     *
     * @return intervalOrigin
     **/

    @Valid
    public ZonedDateTime getIntervalOrigin() {
        return intervalOrigin;
    }

    public void setIntervalOrigin(ZonedDateTime intervalOrigin) {
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

    public Integer getIntervalMinutes() {
        return intervalMinutes;
    }

    public void setIntervalMinutes(Integer intervalMinutes) {
        this.intervalMinutes = intervalMinutes;
    }

    public LocationLevel interpolateString(InterpolateStringEnum interpolateString) {
        this.interpolateString = interpolateString;
        return this;
    }

    /**
     * Indicating whether or not to interpolate between seasonal values.
     *
     * @return interpolateString
     **/

    public InterpolateStringEnum getInterpolateString() {
        return interpolateString;
    }

    public void setInterpolateString(InterpolateStringEnum interpolateString) {
        this.interpolateString = interpolateString;
    }

    public LocationLevel durationId(String durationId) {
        this.durationId = durationId;
        return this;
    }

    /**
     * 0 if parameterTypeId is Inst. Otherwise duration indicating the time window of the aggregate value.
     *
     * @return durationId
     **/

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

    public String getAttributeComment() {
        return attributeComment;
    }

    public void setAttributeComment(String attributeComment) {
        this.attributeComment = attributeComment;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LocationLevel locationLevel = (LocationLevel) o;
        return this.locationLevelId == null || locationLevel.locationLevelId == null ?
            Objects.equals(this.locationLevelId, locationLevel.locationLevelId) :
            this.locationLevelId.equalsIgnoreCase(locationLevel.locationLevelId) && this.officeId == null || locationLevel.officeId == null ?
                Objects.equals(this.officeId, locationLevel.officeId) :
                this.officeId.equalsIgnoreCase(locationLevel.officeId) && this.seasonalTimeSeriesId == null ||
                    locationLevel.seasonalTimeSeriesId == null ? Objects.equals(this.seasonalTimeSeriesId, locationLevel.seasonalTimeSeriesId) :
                    this.seasonalTimeSeriesId.equalsIgnoreCase(locationLevel.seasonalTimeSeriesId) &&
                        Objects.equals(this.seasonalValues, locationLevel.seasonalValues) && this.specifiedLevelId == null ||
                        locationLevel.specifiedLevelId == null ? Objects.equals(this.specifiedLevelId, locationLevel.specifiedLevelId) :
                        this.specifiedLevelId.equalsIgnoreCase(locationLevel.specifiedLevelId) && this.parameterTypeId == null ||
                            locationLevel.parameterTypeId == null ? Objects.equals(this.parameterTypeId, locationLevel.parameterTypeId) :
                            this.parameterTypeId.equals(locationLevel.parameterTypeId) && this.parameterId == null ||
                                locationLevel.parameterId == null ? Objects.equals(this.parameterId, locationLevel.parameterId) :
                                this.parameterId.equalsIgnoreCase(locationLevel.parameterId) &&
                                    Objects.equals(this.constantValue, locationLevel.constantValue) && this.levelUnitsId == null ||
                                    locationLevel.levelUnitsId == null ? Objects.equals(this.levelUnitsId, locationLevel.levelUnitsId) :
                                    this.levelUnitsId.equalsIgnoreCase(locationLevel.levelUnitsId) &&
                                        Objects.equals(this.levelDate, locationLevel.levelDate) && this.levelComment == null ||
                                        locationLevel.levelComment == null ? Objects.equals(this.levelComment, locationLevel.levelComment) :
                                        this.levelComment.equalsIgnoreCase(locationLevel.levelComment) &&
                                            Objects.equals(this.intervalOrigin, locationLevel.intervalOrigin) &&
                                            Objects.equals(this.intervalMonths, locationLevel.intervalMonths) &&
                                            Objects.equals(this.intervalMinutes, locationLevel.intervalMinutes) && this.interpolateString == null ||
                                            locationLevel.interpolateString == null ?
                                            Objects.equals(this.interpolateString, locationLevel.interpolateString) :
                                            this.interpolateString.equals(locationLevel.interpolateString) && this.durationId == null ||
                                                locationLevel.durationId == null ? Objects.equals(this.durationId, locationLevel.durationId) :
                                                this.durationId.equalsIgnoreCase(locationLevel.durationId) &&
                                                    Objects.equals(this.attributeValue, locationLevel.attributeValue) &&
                                                    this.attributeUnitsId == null || locationLevel.attributeUnitsId == null ?
                                                    Objects.equals(this.attributeUnitsId, locationLevel.attributeUnitsId) :
                                                    this.attributeUnitsId.equalsIgnoreCase(locationLevel.attributeUnitsId) &&
                                                        this.attributeParameterTypeId == null || locationLevel.attributeParameterTypeId == null ?
                                                        Objects.equals(this.attributeParameterTypeId, locationLevel.attributeParameterTypeId) :
                                                        this.attributeParameterTypeId.equalsIgnoreCase(locationLevel.attributeParameterTypeId) &&
                                                            this.attributeParameterId == null || locationLevel.attributeParameterId == null ?
                                                            Objects.equals(this.attributeParameterId, locationLevel.attributeParameterId) :
                                                            this.attributeParameterId.equalsIgnoreCase(locationLevel.attributeParameterId) &&
                                                                this.attributeDurationId == null || locationLevel.attributeDurationId == null ?
                                                                Objects.equals(this.attributeDurationId, locationLevel.attributeDurationId) :
                                                                this.attributeDurationId.equalsIgnoreCase(locationLevel.attributeDurationId) &&
                                                                    this.attributeComment == null || locationLevel.attributeComment == null ?
                                                                    Objects.equals(this.attributeComment, locationLevel.attributeComment) :
                                                                    this.attributeComment.equalsIgnoreCase(locationLevel.attributeComment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationLevelId == null ? 0 : locationLevelId.toLowerCase(), officeId == null ? 0 : officeId.toLowerCase(),
            seasonalTimeSeriesId == null ? 0 : seasonalTimeSeriesId.toLowerCase(), seasonalValues,
            specifiedLevelId == null ? 0 : specifiedLevelId.toLowerCase(), parameterTypeId, parameterId == null ? 0 : parameterId.toLowerCase(),
            constantValue, levelUnitsId == null ? 0 : levelUnitsId.toLowerCase(), levelDate, levelComment == null ? 0 : levelComment.toLowerCase(),
            intervalOrigin, intervalMonths, intervalMinutes, interpolateString, durationId == null ? 0 : durationId.toLowerCase(), attributeValue,
            attributeUnitsId == null ? 0 : attributeUnitsId.toLowerCase(),
            attributeParameterTypeId == null ? 0 : attributeParameterTypeId.toLowerCase(),
            attributeParameterId == null ? 0 : attributeParameterId.toLowerCase(),
            attributeDurationId == null ? 0 : attributeDurationId.toLowerCase(), attributeComment == null ? 0 : attributeComment.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LocationLevel {\n");

        sb.append("    locationLevelId: ").append(toIndentedString(locationLevelId)).append("\n");
        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    seasonalTimeSeriesId: ").append(toIndentedString(seasonalTimeSeriesId)).append("\n");
        sb.append("    seasonalValues: ").append(toIndentedString(seasonalValues)).append("\n");
        sb.append("    specifiedLevelId: ").append(toIndentedString(specifiedLevelId)).append("\n");
        sb.append("    parameterTypeId: ").append(toIndentedString(parameterTypeId)).append("\n");
        sb.append("    parameterId: ").append(toIndentedString(parameterId)).append("\n");
        sb.append("    constantValue: ").append(toIndentedString(constantValue)).append("\n");
        sb.append("    levelUnitsId: ").append(toIndentedString(levelUnitsId)).append("\n");
        sb.append("    levelDate: ").append(toIndentedString(levelDate)).append("\n");
        sb.append("    levelComment: ").append(toIndentedString(levelComment)).append("\n");
        sb.append("    intervalOrigin: ").append(toIndentedString(intervalOrigin)).append("\n");
        sb.append("    intervalMonths: ").append(toIndentedString(intervalMonths)).append("\n");
        sb.append("    intervalMinutes: ").append(toIndentedString(intervalMinutes)).append("\n");
        sb.append("    interpolateString: ").append(toIndentedString(interpolateString)).append("\n");
        sb.append("    durationId: ").append(toIndentedString(durationId)).append("\n");
        sb.append("    attributeValue: ").append(toIndentedString(attributeValue)).append("\n");
        sb.append("    attributeUnitsId: ").append(toIndentedString(attributeUnitsId)).append("\n");
        sb.append("    attributeParameterTypeId: ").append(toIndentedString(attributeParameterTypeId)).append("\n");
        sb.append("    attributeParameterId: ").append(toIndentedString(attributeParameterId)).append("\n");
        sb.append("    attributeDurationId: ").append(toIndentedString(attributeDurationId)).append("\n");
        sb.append("    attributeComment: ").append(toIndentedString(attributeComment)).append("\n");
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
