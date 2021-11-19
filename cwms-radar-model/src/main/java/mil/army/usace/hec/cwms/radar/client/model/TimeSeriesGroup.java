/*
 * MIT License
 *
 * Copyright (c) 2021 Hydrologic Engineering Center
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
import io.swagger.annotations.ApiModel;
import java.util.Objects;
import javax.validation.Valid;

/**
 * A representation of a timeseries group
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "A representation of a timeseries group")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class TimeSeriesGroup {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("time-series-category")
    private TimeSeriesCategory timeSeriesCategory = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("shared-alias-id")
    private String sharedAliasId = null;

    @JsonProperty("shared-ref-ts-id")
    private String sharedRefTsId = null;

    /**
     * Get officeId
     *
     * @return officeId
     **/
    public String getOfficeId() {
        return officeId;
    }

    /**
     * Get sharedAliasId
     *
     * @return sharedAliasId
     **/
    public String getSharedAliasId() {
        return sharedAliasId;
    }

    /**
     * Get sharedRefTsId
     *
     * @return sharedRefTsId
     **/
    public String getSharedRefTsId() {
        return sharedRefTsId;
    }

    /**
     * Get id
     *
     * @return id
     **/
    public String getId() {
        return id;
    }

    /**
     * Get timeSeriesCategory
     *
     * @return timeSeriesCategory
     **/
    @Valid
    public TimeSeriesCategory getTimeSeriesCategory() {
        return timeSeriesCategory;
    }

    /**
     * Get description
     *
     * @return description
     **/
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSeriesGroup timeSeriesGroup = (TimeSeriesGroup) o;
        return Objects.equals(this.id, timeSeriesGroup.id)
            && Objects.equals(this.timeSeriesCategory, timeSeriesGroup.timeSeriesCategory)
            && Objects.equals(this.description, timeSeriesGroup.description)
            && Objects.equals(this.officeId, timeSeriesGroup.officeId)
            && Objects.equals(this.sharedAliasId, timeSeriesGroup.sharedAliasId)
            && Objects.equals(this.sharedRefTsId, timeSeriesGroup.sharedRefTsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeSeriesCategory, description, officeId, sharedAliasId, sharedRefTsId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesGroup {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    timeSeriesCategory: ").append(toIndentedString(timeSeriesCategory)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    sharedAliasId: ").append(toIndentedString(sharedAliasId)).append("\n");
        sb.append("    sharedRefTsId: ").append(toIndentedString(sharedRefTsId)).append("\n");
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
