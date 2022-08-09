/*
 * MIT License
 *
 * Copyright (c) 2022 Hydrologic Engineering Center
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
import java.util.Objects;

/**
 * TimeSeriesColumn
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-09T09:29:33.859-07:00[America/Los_Angeles]")
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimeSeriesColumn ordinal(Integer ordinal) {
        this.ordinal = ordinal;
        return this;
    }

    /**
     * Get ordinal
     *
     * @return ordinal
     **/

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public TimeSeriesColumn datatype(String datatype) {
        this.datatype = datatype;
        return this;
    }

    /**
     * Get datatype
     *
     * @return datatype
     **/

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
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
        return this.name == null || timeSeriesColumn.name == null ? Objects.equals(this.name, timeSeriesColumn.name) :
            this.name.equalsIgnoreCase(timeSeriesColumn.name) && Objects.equals(this.ordinal, timeSeriesColumn.ordinal) && this.datatype == null ||
                timeSeriesColumn.datatype == null ? Objects.equals(this.datatype, timeSeriesColumn.datatype) :
                this.datatype.equalsIgnoreCase(timeSeriesColumn.datatype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name == null ? 0 : name.toLowerCase(), ordinal, datatype == null ? 0 : datatype.toLowerCase());
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
