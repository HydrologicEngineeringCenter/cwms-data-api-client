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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;

/**
 * VerticalDatumInfo
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-07-22T11:14:35.029-07:00[America/Los_Angeles]")
public class VerticalDatumInfo {
    @JsonProperty("office")
    private String office = null;

    @JsonProperty("unit")
    private String unit = null;

    @JsonProperty("location")
    private String location = null;

    @JsonProperty("native-datum")
    private String nativeDatum = null;

    @JsonProperty("elevation")
    private Double elevation = null;

    @JsonProperty("offsets")
    @Valid
    private List<Offset> offsets = new ArrayList<>();

    public VerticalDatumInfo office(String office) {
        this.office = office;
        return this;
    }

    /**
     * Get office
     *
     * @return office
     **/

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public VerticalDatumInfo unit(String unit) {
        this.unit = unit;
        return this;
    }

    /**
     * Get unit
     *
     * @return unit
     **/

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public VerticalDatumInfo location(String location) {
        this.location = location;
        return this;
    }

    /**
     * Get location
     *
     * @return location
     **/

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public VerticalDatumInfo nativeDatum(String nativeDatum) {
        this.nativeDatum = nativeDatum;
        return this;
    }

    /**
     * Get nativeDatum
     *
     * @return nativeDatum
     **/

    public String getNativeDatum() {
        return nativeDatum;
    }

    public void setNativeDatum(String nativeDatum) {
        this.nativeDatum = nativeDatum;
    }

    public VerticalDatumInfo elevation(Double elevation) {
        this.elevation = elevation;
        return this;
    }

    /**
     * Get elevation
     *
     * @return elevation
     **/

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    public VerticalDatumInfo offsets(List<Offset> offsets) {
        this.offsets = offsets;
        return this;
    }

    public VerticalDatumInfo addOffsetsItem(Offset offsetsItem) {
        if (this.offsets == null) {
            this.offsets = new ArrayList<Offset>();
        }
        this.offsets.add(offsetsItem);
        return this;
    }

    /**
     * Get offsets
     *
     * @return offsets
     **/
    @Valid
    public List<Offset> getOffsets() {
        return offsets;
    }

    public void setOffsets(List<Offset> offsets) {
        this.offsets = offsets;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VerticalDatumInfo verticalDatumInfo = (VerticalDatumInfo) o;
        return this.office == null || verticalDatumInfo.office == null ? Objects.equals(this.office, verticalDatumInfo.office) :
            this.office.equalsIgnoreCase(verticalDatumInfo.office)
                && this.unit == null || verticalDatumInfo.unit == null ? Objects.equals(this.unit, verticalDatumInfo.unit) :
                this.unit.equalsIgnoreCase(verticalDatumInfo.unit)
                    && this.location == null || verticalDatumInfo.location == null ? Objects.equals(this.location, verticalDatumInfo.location) :
                    this.location.equalsIgnoreCase(verticalDatumInfo.location)
                        && this.nativeDatum == null || verticalDatumInfo.nativeDatum == null ?
                        Objects.equals(this.nativeDatum, verticalDatumInfo.nativeDatum) :
                        this.nativeDatum.equalsIgnoreCase(verticalDatumInfo.nativeDatum)
                            && Objects.equals(this.elevation, verticalDatumInfo.elevation)
                            && Objects.equals(this.offsets, verticalDatumInfo.offsets)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(office == null ? 0 : office.toLowerCase(), unit == null ? 0 : unit.toLowerCase(),
            location == null ? 0 : location.toLowerCase(), nativeDatum == null ? 0 : nativeDatum.toLowerCase(), elevation, offsets);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class VerticalDatumInfo {\n");

        sb.append("    office: ").append(toIndentedString(office)).append("\n");
        sb.append("    unit: ").append(toIndentedString(unit)).append("\n");
        sb.append("    location: ").append(toIndentedString(location)).append("\n");
        sb.append("    nativeDatum: ").append(toIndentedString(nativeDatum)).append("\n");
        sb.append("    elevation: ").append(toIndentedString(elevation)).append("\n");
        sb.append("    offsets: ").append(toIndentedString(offsets)).append("\n");
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
