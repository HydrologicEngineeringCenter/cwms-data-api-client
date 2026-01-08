/*
 * MIT License
 *
 * Copyright (c) 2026 Hydrologic Engineering Center
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
import java.util.Objects;

/**
 * CwmsIdLocationKind
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-01-07T20:20:46.213231800-08:00[America/Los_Angeles]")
public class CwmsIdLocationKind {

    @JsonProperty("location-kind-id")
    private String locationKindId = null;

    @JsonProperty("location-id")
    private CwmsId locationId = null;

    public CwmsIdLocationKind locationKindId(String locationKindId) {
        this.locationKindId = locationKindId;
        return this;
    }

    public String getLocationKindId() {
        return locationKindId;
    }

    public void setLocationKindId(String locationKindId) {
        this.locationKindId = locationKindId;
    }

    public CwmsIdLocationKind locationId(CwmsId locationId) {
        this.locationId = locationId;
        return this;
    }

    public CwmsId getLocationId() {
        return locationId;
    }

    public void setLocationId(CwmsId locationId) {
        this.locationId = locationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CwmsIdLocationKind cwmsIdLocationKind = (CwmsIdLocationKind) o;
        return this.locationKindId == null || cwmsIdLocationKind.locationKindId == null ?
            Objects.equals(this.locationKindId, cwmsIdLocationKind.locationKindId) :
            this.locationKindId.equalsIgnoreCase(cwmsIdLocationKind.locationKindId)
                && Objects.equals(this.locationId, cwmsIdLocationKind.locationId)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationKindId == null ? 0 : locationKindId.toLowerCase(), locationId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CwmsIdLocationKind {\n");

        sb.append("    locationKindId: ").append(toIndentedString(locationKindId)).append("\n");
        sb.append("    locationId: ").append(toIndentedString(locationId)).append("\n");
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
