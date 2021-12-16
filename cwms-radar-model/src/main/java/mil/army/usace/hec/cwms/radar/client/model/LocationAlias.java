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
import java.util.Objects;

/**
 * LocationAlias
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-12-16T09:13:30.631614-08:00[America/Los_Angeles]")
public class LocationAlias {
    @JsonProperty("name")
    private String locationGroupId = null;

    @JsonProperty("value")
    private String aliasId = null;

    public LocationAlias name(String name) {
        this.locationGroupId = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     **/

    public String getLocationGroupId() {
        return locationGroupId;
    }

    public void setLocationGroupId(String locationGroupId) {
        this.locationGroupId = locationGroupId;
    }

    public LocationAlias value(String value) {
        this.aliasId = value;
        return this;
    }

    /**
     * Get value
     *
     * @return value
     **/

    public String getAliasId() {
        return aliasId;
    }

    public void setAliasId(String aliasId) {
        this.aliasId = aliasId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LocationAlias locationAlias = (LocationAlias) o;
        return this.locationGroupId == null || locationAlias.locationGroupId == null ?
            Objects.equals(this.locationGroupId, locationAlias.locationGroupId) :
            this.locationGroupId.equalsIgnoreCase(locationAlias.locationGroupId)
                && this.aliasId == null || locationAlias.aliasId == null ? Objects.equals(this.aliasId, locationAlias.aliasId) :
                this.aliasId.equalsIgnoreCase(locationAlias.aliasId)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationGroupId == null ? 0 : locationGroupId.toLowerCase(), aliasId == null ? 0 : aliasId.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LocationAlias {\n");

        sb.append("    name: ").append(toIndentedString(locationGroupId)).append("\n");
        sb.append("    value: ").append(toIndentedString(aliasId)).append("\n");
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
