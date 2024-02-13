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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TextTimeSeries
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-02-13T10:48:01.962877200-08:00[America/Los_Angeles]")
public class TextTimeSeries {
    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("interval-offset")
    private Long intervalOffset = null;

    @JsonProperty("time-zone")
    private String timeZone = null;

    @JsonProperty("regular-text-values")
    @Valid
    private List<RegularTextTimeSeriesRow> regularTextValues = new ArrayList<>();

    @JsonProperty("standard-text-values")
    @Valid
    private List<StandardTextTimeSeriesRow> standardTextValues = new ArrayList<>();

    @JsonProperty("standard-text-catalog")
    @Valid
    private List<StandardCatalog> standardTextCatalog = new ArrayList<>();

    public TextTimeSeries officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    /**
     * Owning office of object.
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

    public TextTimeSeries name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     **/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TextTimeSeries intervalOffset(Long intervalOffset) {
        this.intervalOffset = intervalOffset;
        return this;
    }

    /**
     * Get intervalOffset
     *
     * @return intervalOffset
     **/

    public Long getIntervalOffset() {
        return intervalOffset;
    }

    public void setIntervalOffset(Long intervalOffset) {
        this.intervalOffset = intervalOffset;
    }

    public TextTimeSeries timeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    /**
     * Get timeZone
     *
     * @return timeZone
     **/

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public TextTimeSeries regularTextValues(List<RegularTextTimeSeriesRow> regularTextValues) {
        this.regularTextValues = regularTextValues;
        return this;
    }

    public TextTimeSeries addRegularTextValuesItem(RegularTextTimeSeriesRow regularTextValuesItem) {
        if (this.regularTextValues == null) {
            this.regularTextValues = new ArrayList<RegularTextTimeSeriesRow>();
        }
        this.regularTextValues.add(regularTextValuesItem);
        return this;
    }

    /**
     * Get regularTextValues
     *
     * @return regularTextValues
     **/
    @Valid
    public List<RegularTextTimeSeriesRow> getRegularTextValues() {
        return regularTextValues;
    }

    public void setRegularTextValues(List<RegularTextTimeSeriesRow> regularTextValues) {
        this.regularTextValues = regularTextValues;
    }

    public TextTimeSeries standardTextValues(List<StandardTextTimeSeriesRow> standardTextValues) {
        this.standardTextValues = standardTextValues;
        return this;
    }

    public TextTimeSeries addStandardTextValuesItem(StandardTextTimeSeriesRow standardTextValuesItem) {
        if (this.standardTextValues == null) {
            this.standardTextValues = new ArrayList<StandardTextTimeSeriesRow>();
        }
        this.standardTextValues.add(standardTextValuesItem);
        return this;
    }

    /**
     * Get standardTextValues
     *
     * @return standardTextValues
     **/
    @Valid
    public List<StandardTextTimeSeriesRow> getStandardTextValues() {
        return standardTextValues;
    }

    public void setStandardTextValues(List<StandardTextTimeSeriesRow> standardTextValues) {
        this.standardTextValues = standardTextValues;
    }

    public TextTimeSeries standardTextCatalog(List<StandardCatalog> standardTextCatalog) {
        this.standardTextCatalog = standardTextCatalog;
        return this;
    }

    public TextTimeSeries addStandardTextCatalogItem(StandardCatalog standardTextCatalogItem) {
        if (this.standardTextCatalog == null) {
            this.standardTextCatalog = new ArrayList<StandardCatalog>();
        }
        this.standardTextCatalog.add(standardTextCatalogItem);
        return this;
    }

    /**
     * Get standardTextCatalog
     *
     * @return standardTextCatalog
     **/
    @Valid
    public List<StandardCatalog> getStandardTextCatalog() {
        return standardTextCatalog;
    }

    public void setStandardTextCatalog(List<StandardCatalog> standardTextCatalog) {
        this.standardTextCatalog = standardTextCatalog;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TextTimeSeries textTimeSeries = (TextTimeSeries) o;
        return this.officeId == null || textTimeSeries.officeId == null ? Objects.equals(this.officeId, textTimeSeries.officeId) : this.officeId.equalsIgnoreCase(textTimeSeries.officeId)
                && this.name == null || textTimeSeries.name == null ? Objects.equals(this.name, textTimeSeries.name) : this.name.equalsIgnoreCase(textTimeSeries.name)
                && Objects.equals(this.intervalOffset, textTimeSeries.intervalOffset)
                && this.timeZone == null || textTimeSeries.timeZone == null ? Objects.equals(this.timeZone, textTimeSeries.timeZone) : this.timeZone.equalsIgnoreCase(textTimeSeries.timeZone)
                && Objects.equals(this.regularTextValues, textTimeSeries.regularTextValues)
                && Objects.equals(this.standardTextValues, textTimeSeries.standardTextValues)
                && Objects.equals(this.standardTextCatalog, textTimeSeries.standardTextCatalog)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeId == null ? 0 : officeId.toLowerCase(), name == null ? 0 : name.toLowerCase(), intervalOffset, timeZone == null ? 0 : timeZone.toLowerCase(), regularTextValues, standardTextValues, standardTextCatalog);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TextTimeSeries {\n");

        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    intervalOffset: ").append(toIndentedString(intervalOffset)).append("\n");
        sb.append("    timeZone: ").append(toIndentedString(timeZone)).append("\n");
        sb.append("    regularTextValues: ").append(toIndentedString(regularTextValues)).append("\n");
        sb.append("    standardTextValues: ").append(toIndentedString(standardTextValues)).append("\n");
        sb.append("    standardTextCatalog: ").append(toIndentedString(standardTextCatalog)).append("\n");
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
