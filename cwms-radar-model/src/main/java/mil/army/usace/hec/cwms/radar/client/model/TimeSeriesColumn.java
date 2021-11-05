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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 * TimeSeriesColumn
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:49:54.974-07:00[America/Los_Angeles]")
public class TimeSeriesColumn {
    @JsonProperty("name")
    private String name = null;

    @JsonProperty("ordinal")
    private Integer ordinal = null;

    @JsonProperty("datatype")
    private String datatype = null;

    public TimeSeriesColumn name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     **/
    @ApiModelProperty(value = "")

    public String getName() {
        return name;
    }

    /**
     * Get ordinal
     *
     * @return ordinal
     **/
    @ApiModelProperty(value = "")

    public Integer getOrdinal() {
        return ordinal;
    }

    /**
     * Get datatype
     *
     * @return datatype
     **/
    @ApiModelProperty(value = "")

    public String getDatatype() {
        return datatype;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSeriesColumn timeSeriesColumn = (TimeSeriesColumn) o;
        return Objects.equals(this.name, timeSeriesColumn.name) &&
            Objects.equals(this.ordinal, timeSeriesColumn.ordinal) &&
            Objects.equals(this.datatype, timeSeriesColumn.datatype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ordinal, datatype);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesColumn {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    ordinal: ").append(toIndentedString(ordinal)).append("\n");
        sb.append("    datatype: ").append(toIndentedString(datatype)).append("\n");
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
