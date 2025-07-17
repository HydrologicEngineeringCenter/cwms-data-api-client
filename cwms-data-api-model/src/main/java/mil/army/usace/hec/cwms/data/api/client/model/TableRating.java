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
import jakarta.annotation.Generated;

import java.util.Objects;

/**
 * TableRating
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-10-04T12:33:40.401-07:00[America/Los_Angeles]")
public class TableRating extends AbstractRatingMetadata {
    @JsonProperty("in-range-method")
    private String inRangeMethod = null;

    @JsonProperty("out-range-low-method")
    private String outRangeLowMethod = null;

    @JsonProperty("out-range-high-method")
    private String outRangeHighMethod = null;

    public TableRating inRangeMethod(String inRangeMethod) {
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

    public TableRating outRangeLowMethod(String outRangeLowMethod) {
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

    public TableRating outRangeHighMethod(String outRangeHighMethod) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TableRating tableRating = (TableRating) o;
        return this.inRangeMethod == null || tableRating.inRangeMethod == null ? Objects.equals(this.inRangeMethod, tableRating.inRangeMethod) :
            this.inRangeMethod.equalsIgnoreCase(tableRating.inRangeMethod)
                && this.outRangeLowMethod == null || tableRating.outRangeLowMethod == null ?
                Objects.equals(this.outRangeLowMethod, tableRating.outRangeLowMethod) :
                this.outRangeLowMethod.equalsIgnoreCase(tableRating.outRangeLowMethod)
                    && this.outRangeHighMethod == null || tableRating.outRangeHighMethod == null ?
                    Objects.equals(this.outRangeHighMethod, tableRating.outRangeHighMethod) :
                    this.outRangeHighMethod.equalsIgnoreCase(tableRating.outRangeHighMethod)
                        &&
                        super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inRangeMethod == null ? 0 : inRangeMethod.toLowerCase(), outRangeLowMethod == null ? 0 : outRangeLowMethod.toLowerCase(),
            outRangeHighMethod == null ? 0 : outRangeHighMethod.toLowerCase(), super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TableRating {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    inRangeMethod: ").append(toIndentedString(inRangeMethod)).append("\n");
        sb.append("    outRangeLowMethod: ").append(toIndentedString(outRangeLowMethod)).append("\n");
        sb.append("    outRangeHighMethod: ").append(toIndentedString(outRangeHighMethod)).append("\n");
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
