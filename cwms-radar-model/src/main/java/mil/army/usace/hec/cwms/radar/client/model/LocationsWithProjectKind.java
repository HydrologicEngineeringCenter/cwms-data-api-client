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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * LocationsWithProjectKind
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-08-12T08:16:03.445647-07:00[America/Los_Angeles]")
public class LocationsWithProjectKind {

    /**
     * Gets or Sets kind
     */
    public enum KindEnum {
        EMBANKMENT("EMBANKMENT"),

        TURBINE("TURBINE"),

        OUTLET("OUTLET"),

        LOCK("LOCK"),

        GATE("GATE");

        private String value;

        KindEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static KindEnum fromValue(String text) {
            for (KindEnum b : KindEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    @JsonProperty("kind")
    private KindEnum kind = null;

    @JsonProperty("location-ids")
    @Valid
    private List<CwmsId> locationIds = new ArrayList<>();

    public LocationsWithProjectKind kind(KindEnum kind) {
        this.kind = kind;
        return this;
    }

    public KindEnum getKind() {
        return kind;
    }

    public void setKind(KindEnum kind) {
        this.kind = kind;
    }

    public LocationsWithProjectKind locationIds(List<CwmsId> locationIds) {
        this.locationIds = locationIds;
        return this;
    }

    public LocationsWithProjectKind addLocationIdsItem(CwmsId locationIdsItem) {
        if (this.locationIds == null) {
            this.locationIds = new ArrayList<>();
        }
        this.locationIds.add(locationIdsItem);
        return this;
    }

    public List<CwmsId> getLocationIds() {
        return locationIds;
    }

    public void setLocationIds(List<CwmsId> locationIds) {
        this.locationIds = locationIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LocationsWithProjectKind locationsWithProjectKind = (LocationsWithProjectKind) o;
        return this.kind == null || locationsWithProjectKind.kind == null ?
            Objects.equals(this.kind, locationsWithProjectKind.kind) :
            this.kind == locationsWithProjectKind.kind
                && Objects.equals(this.locationIds, locationsWithProjectKind.locationIds)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, locationIds);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LocationsWithProjectKind {\n");

        sb.append("    kind: ").append(toIndentedString(kind)).append("\n");
        sb.append("    locationIds: ").append(toIndentedString(locationIds)).append("\n");
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
