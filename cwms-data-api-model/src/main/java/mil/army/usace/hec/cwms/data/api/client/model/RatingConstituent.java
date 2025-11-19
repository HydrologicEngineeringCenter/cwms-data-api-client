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
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * RATING
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-11-17T16:46:44.562396200-08:00[America/Los_Angeles]")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = LocationLevelConstituent.class, name = "LOCATION_LEVEL"),
    @JsonSubTypes.Type(value = RatingConstituent.class, name = "RATING")
})
public class RatingConstituent {

    @JsonProperty("abbr")
    private String abbr = null;

    @JsonProperty("type")
    private String type = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("constituent-list")
    @Valid
    private List<String> constituentList = new ArrayList<>();

    public RatingConstituent abbr(String abbr) {
        this.abbr = abbr;
        return this;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public RatingConstituent type(String type) {
        this.type = type;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RatingConstituent name(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RatingConstituent constituentList(List<String> constituentList) {
        this.constituentList = constituentList;
        return this;
    }

    public RatingConstituent addConstituentListItem(String constituentListItem) {
        if (this.constituentList == null) {
            this.constituentList = new ArrayList<>();
        }
        this.constituentList.add(constituentListItem);
        return this;
    }

    public List<String> getConstituentList() {
        return constituentList;
    }

    public void setConstituentList(List<String> constituentList) {
        this.constituentList = constituentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RatingConstituent ratingConstituent = (RatingConstituent) o;
        return this.abbr == null || ratingConstituent.abbr == null ? Objects.equals(this.abbr, ratingConstituent.abbr) :
            this.abbr.equalsIgnoreCase(ratingConstituent.abbr)
                && this.type == null || ratingConstituent.type == null ?
                Objects.equals(this.type, ratingConstituent.type) :
                this.type.equalsIgnoreCase(ratingConstituent.type)
                    && this.name == null || ratingConstituent.name == null ?
                    Objects.equals(this.name, ratingConstituent.name) :
                    this.name.equalsIgnoreCase(ratingConstituent.name)
                        && Objects.equals(this.constituentList, ratingConstituent.constituentList)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(abbr == null ? 0 : abbr.toLowerCase(), type == null ? 0 : type.toLowerCase(),
            name == null ? 0 : name.toLowerCase(), constituentList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RATING {\n");

        sb.append("    abbr: ").append(toIndentedString(abbr)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    constituentList: ").append(toIndentedString(constituentList)).append("\n");
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
