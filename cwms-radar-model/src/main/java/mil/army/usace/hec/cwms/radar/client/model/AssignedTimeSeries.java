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

import java.math.BigDecimal;
import java.util.Objects;

/**
 * AssignedTimeSeries
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-09T09:29:33.859-07:00[America/Los_Angeles]")
public class AssignedTimeSeries {
    @JsonProperty("timeseries-id")
    private String timeseriesId = null;

    @JsonProperty("ts-code")
    private BigDecimal tsCode = null;

    @JsonProperty("alias-id")
    private String aliasId = null;

    @JsonProperty("ref-ts-id")
    private String refTsId = null;

    @JsonProperty("attribute")
    private Integer attribute = null;

    public AssignedTimeSeries timeseriesId(String timeseriesId) {
        this.timeseriesId = timeseriesId;
        return this;
    }

    /**
     * Get timeseriesId
     *
     * @return timeseriesId
     **/

    public String getTimeseriesId() {
        return timeseriesId;
    }

    public void setTimeseriesId(String timeseriesId) {
        this.timeseriesId = timeseriesId;
    }

    public AssignedTimeSeries tsCode(BigDecimal tsCode) {
        this.tsCode = tsCode;
        return this;
    }

    /**
     * Get tsCode
     *
     * @return tsCode
     **/

    @Valid
    public BigDecimal getTsCode() {
        return tsCode;
    }

    public void setTsCode(BigDecimal tsCode) {
        this.tsCode = tsCode;
    }

    public AssignedTimeSeries aliasId(String aliasId) {
        this.aliasId = aliasId;
        return this;
    }

    /**
     * Get aliasId
     *
     * @return aliasId
     **/

    public String getAliasId() {
        return aliasId;
    }

    public void setAliasId(String aliasId) {
        this.aliasId = aliasId;
    }

    public AssignedTimeSeries refTsId(String refTsId) {
        this.refTsId = refTsId;
        return this;
    }

    /**
     * Get refTsId
     *
     * @return refTsId
     **/

    public String getRefTsId() {
        return refTsId;
    }

    public void setRefTsId(String refTsId) {
        this.refTsId = refTsId;
    }

    public AssignedTimeSeries attribute(Integer attribute) {
        this.attribute = attribute;
        return this;
    }

    /**
     * Get attribute
     *
     * @return attribute
     **/

    public Integer getAttribute() {
        return attribute;
    }

    public void setAttribute(Integer attribute) {
        this.attribute = attribute;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AssignedTimeSeries assignedTimeSeries = (AssignedTimeSeries) o;
        return this.timeseriesId == null || assignedTimeSeries.timeseriesId == null ?
            Objects.equals(this.timeseriesId, assignedTimeSeries.timeseriesId) :
            this.timeseriesId.equalsIgnoreCase(assignedTimeSeries.timeseriesId) && Objects.equals(this.tsCode, assignedTimeSeries.tsCode) &&
                this.aliasId == null || assignedTimeSeries.aliasId == null ? Objects.equals(this.aliasId, assignedTimeSeries.aliasId) :
                this.aliasId.equalsIgnoreCase(assignedTimeSeries.aliasId) && this.refTsId == null || assignedTimeSeries.refTsId == null ?
                    Objects.equals(this.refTsId, assignedTimeSeries.refTsId) :
                    this.refTsId.equalsIgnoreCase(assignedTimeSeries.refTsId) && Objects.equals(this.attribute, assignedTimeSeries.attribute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeseriesId == null ? 0 : timeseriesId.toLowerCase(), tsCode, aliasId == null ? 0 : aliasId.toLowerCase(),
            refTsId == null ? 0 : refTsId.toLowerCase(), attribute);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AssignedTimeSeries {\n");

        sb.append("    timeseriesId: ").append(toIndentedString(timeseriesId)).append("\n");
        sb.append("    tsCode: ").append(toIndentedString(tsCode)).append("\n");
        sb.append("    aliasId: ").append(toIndentedString(aliasId)).append("\n");
        sb.append("    refTsId: ").append(toIndentedString(refTsId)).append("\n");
        sb.append("    attribute: ").append(toIndentedString(attribute)).append("\n");
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
