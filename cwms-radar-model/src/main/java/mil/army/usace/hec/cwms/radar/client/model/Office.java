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
 * A representation of a CWMS office
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-09T09:29:33.859-07:00[America/Los_Angeles]")
public class Office {
    @JsonProperty("name")
    private String name = null;

    @JsonProperty("long-name")
    private String longName = null;

    @JsonProperty("type")
    private String type = null;

    @JsonProperty("reports-to")
    private String reportsTo = null;

    public Office name(String name) {
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

    public Office longName(String longName) {
        this.longName = longName;
        return this;
    }

    /**
     * Get longName
     *
     * @return longName
     **/

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public Office type(String type) {
        this.type = type;
        return this;
    }

    /**
     * Get type
     *
     * @return type
     **/

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Office reportsTo(String reportsTo) {
        this.reportsTo = reportsTo;
        return this;
    }

    /**
     * Reference to another office, like a division, that this office reports to.
     *
     * @return reportsTo
     **/

    public String getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(String reportsTo) {
        this.reportsTo = reportsTo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Office office = (Office) o;
        return this.name == null || office.name == null ? Objects.equals(this.name, office.name) :
            this.name.equalsIgnoreCase(office.name) && this.longName == null || office.longName == null ?
                Objects.equals(this.longName, office.longName) :
                this.longName.equalsIgnoreCase(office.longName) && this.type == null || office.type == null ? Objects.equals(this.type, office.type) :
                    this.type.equals(office.type) && this.reportsTo == null || office.reportsTo == null ?
                        Objects.equals(this.reportsTo, office.reportsTo) : this.reportsTo.equalsIgnoreCase(office.reportsTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name == null ? 0 : name.toLowerCase(), longName == null ? 0 : longName.toLowerCase(), type == null ? 0 : type,
            reportsTo == null ? 0 : reportsTo.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Office {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    longName: ").append(toIndentedString(longName)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    reportsTo: ").append(toIndentedString(reportsTo)).append("\n");
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
