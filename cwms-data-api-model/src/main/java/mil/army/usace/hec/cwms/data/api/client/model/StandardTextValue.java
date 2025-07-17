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
import jakarta.validation.Valid;

import java.util.Objects;

/**
 * StandardTextValue
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-02-15T16:09:12.352841800-08:00[America/Los_Angeles]")
public class StandardTextValue {
    @JsonProperty("id")
    private StandardTextId id = null;

    @JsonProperty("standard-text")
    private String standardText = null;

    public StandardTextValue id(StandardTextId id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/

    @Valid
    public StandardTextId getId() {
        return id;
    }

    public void setId(StandardTextId id) {
        this.id = id;
    }

    public StandardTextValue standardText(String standardText) {
        this.standardText = standardText;
        return this;
    }

    /**
     * Get standardText
     *
     * @return standardText
     **/

    public String getStandardText() {
        return standardText;
    }

    public void setStandardText(String standardText) {
        this.standardText = standardText;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StandardTextValue standardTextValue = (StandardTextValue) o;
        return Objects.equals(this.id, standardTextValue.id)
                && this.standardText == null || standardTextValue.standardText == null ? Objects.equals(this.standardText, standardTextValue.standardText) : this.standardText.equalsIgnoreCase(standardTextValue.standardText)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, standardText == null ? 0 : standardText.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StandardTextValue {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    standardText: ").append(toIndentedString(standardText)).append("\n");
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
