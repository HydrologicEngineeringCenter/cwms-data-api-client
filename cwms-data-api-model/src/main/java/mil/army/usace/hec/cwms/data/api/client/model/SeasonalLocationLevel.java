/*
 * MIT License
 *
 * Copyright (c) 2025 Hydrologic Engineering Center
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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.Valid;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * SeasonalLocationLevel
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-11-17T16:46:44.562396200-08:00[America/Los_Angeles]")
@JsonDeserialize()
public class SeasonalLocationLevel extends LocationLevel {

    @JsonProperty("interval-origin")
    private ZonedDateTime intervalOrigin = null;

    @JsonProperty("interval-months")
    private Integer intervalMonths = null;

    @JsonProperty("interval-minutes")
    private Integer intervalMinutes = null;

    @JsonProperty("seasonal-values")
    @Valid
    private List<SeasonalValueBean> seasonalValues = new ArrayList<>();

    public SeasonalLocationLevel intervalOrigin(ZonedDateTime intervalOrigin) {
        this.intervalOrigin = intervalOrigin;
        return this;
    }

    public ZonedDateTime getIntervalOrigin() {
        return intervalOrigin;
    }

    public void setIntervalOrigin(ZonedDateTime intervalOrigin) {
        this.intervalOrigin = intervalOrigin;
    }

    public SeasonalLocationLevel intervalMonths(Integer intervalMonths) {
        this.intervalMonths = intervalMonths;
        return this;
    }

    public Integer getIntervalMonths() {
        return intervalMonths;
    }

    public void setIntervalMonths(Integer intervalMonths) {
        this.intervalMonths = intervalMonths;
    }

    public SeasonalLocationLevel intervalMinutes(Integer intervalMinutes) {
        this.intervalMinutes = intervalMinutes;
        return this;
    }

    public Integer getIntervalMinutes() {
        return intervalMinutes;
    }

    public void setIntervalMinutes(Integer intervalMinutes) {
        this.intervalMinutes = intervalMinutes;
    }

    public SeasonalLocationLevel seasonalValues(List<SeasonalValueBean> seasonalValues) {
        this.seasonalValues = seasonalValues;
        return this;
    }

    public SeasonalLocationLevel addSeasonalValuesItem(SeasonalValueBean seasonalValuesItem) {
        if (this.seasonalValues == null) {
            this.seasonalValues = new ArrayList<>();
        }
        this.seasonalValues.add(seasonalValuesItem);
        return this;
    }

    public List<SeasonalValueBean> getSeasonalValues() {
        return seasonalValues;
    }

    public void setSeasonalValues(List<SeasonalValueBean> seasonalValues) {
        this.seasonalValues = seasonalValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SeasonalLocationLevel seasonalLocationLevel = (SeasonalLocationLevel) o;
        return super.equals(o)
            && Objects.equals(this.intervalOrigin, seasonalLocationLevel.intervalOrigin)
            && Objects.equals(this.intervalMonths, seasonalLocationLevel.intervalMonths)
            && Objects.equals(this.intervalMinutes, seasonalLocationLevel.intervalMinutes)
            && Objects.equals(this.seasonalValues, seasonalLocationLevel.seasonalValues)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), intervalOrigin, intervalMonths, intervalMinutes, seasonalValues);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SeasonalLocationLevel {\n");

        sb.append("    officeId: ").append(toIndentedString(getOfficeId())).append("\n");
        sb.append("    locationLevelId: ").append(toIndentedString(getLocationLevelId())).append("\n");
        sb.append("    specifiedLevelId: ").append(toIndentedString(getSpecifiedLevelId())).append("\n");
        sb.append("    expirationDate: ").append(toIndentedString(getExpirationDate())).append("\n");
        sb.append("    parameterId: ").append(toIndentedString(getParameterId())).append("\n");
        sb.append("    parameterTypeId: ").append(toIndentedString(getParameterTypeId())).append("\n");
        sb.append("    interpolateString: ").append(toIndentedString(getInterpolateString())).append("\n");
        sb.append("    levelUnitsId: ").append(toIndentedString(getLevelUnitsId())).append("\n");
        sb.append("    levelDate: ").append(toIndentedString(getLevelDate())).append("\n");
        sb.append("    levelComment: ").append(toIndentedString(getLevelComment())).append("\n");
        sb.append("    durationId: ").append(toIndentedString(getDurationId())).append("\n");
        sb.append("    attributeValue: ").append(toIndentedString(getAttributeValue())).append("\n");
        sb.append("    attributeUnitsId: ").append(toIndentedString(getAttributeUnitsId())).append("\n");
        sb.append("    attributeParameterTypeId: ").append(toIndentedString(getAttributeParameterTypeId()))
            .append("\n");
        sb.append("    attributeParameterId: ").append(toIndentedString(getAttributeParameterId())).append("\n");
        sb.append("    attributeDurationId: ").append(toIndentedString(getAttributeDurationId())).append("\n");
        sb.append("    attributeComment: ").append(toIndentedString(getAttributeComment())).append("\n");
        sb.append("    intervalOrigin: ").append(toIndentedString(intervalOrigin)).append("\n");
        sb.append("    intervalMonths: ").append(toIndentedString(intervalMonths)).append("\n");
        sb.append("    intervalMinutes: ").append(toIndentedString(intervalMinutes)).append("\n");
        sb.append("    seasonalValues: ").append(toIndentedString(seasonalValues)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
