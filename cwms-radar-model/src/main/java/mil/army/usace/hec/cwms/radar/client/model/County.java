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

import java.util.Objects;

/**
 * A representation of a county
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-06-12T13:29:39.827127300-07:00[America/Los_Angeles]")
public class County {
    @JsonProperty("name")
    private String name = null;

    @JsonProperty("county-id")
    private String countyId = null;

    @JsonProperty("state-initial")
    private String stateInitial = null;

    public County name(String name) {
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

    public County countyId(String countyId) {
        this.countyId = countyId;
        return this;
    }

    /**
     * Get countyId
     *
     * @return countyId
     **/

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public County stateInitial(String stateInitial) {
        this.stateInitial = stateInitial;
        return this;
    }

    /**
     * Get stateInitial
     *
     * @return stateInitial
     **/

    public String getStateInitial() {
        return stateInitial;
    }

    public void setStateInitial(String stateInitial) {
        this.stateInitial = stateInitial;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        County county = (County) o;
        return this.name == null || county.name == null ? Objects.equals(this.name, county.name) : this.name.equalsIgnoreCase(county.name) && this.countyId == null || county.countyId == null ? Objects.equals(this.countyId, county.countyId) : this.countyId.equalsIgnoreCase(county.countyId) && this.stateInitial == null || county.stateInitial == null ? Objects.equals(this.stateInitial, county.stateInitial) : this.stateInitial.equalsIgnoreCase(county.stateInitial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name == null ? 0 : name.toLowerCase(), countyId == null ? 0 : countyId.toLowerCase(), stateInitial == null ? 0 : stateInitial.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class County {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    countyId: ").append(toIndentedString(countyId)).append("\n");
        sb.append("    stateInitial: ").append(toIndentedString(stateInitial)).append("\n");
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
