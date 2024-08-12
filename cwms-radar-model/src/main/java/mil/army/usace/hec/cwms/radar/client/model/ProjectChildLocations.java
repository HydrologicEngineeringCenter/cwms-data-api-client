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
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ProjectChildLocations
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-08-12T08:16:03.445647-07:00[America/Los_Angeles]")
public class ProjectChildLocations {

    @JsonProperty("project-id")
    private CwmsId projectId = null;

    @JsonProperty("locations-by-kind")
    @Valid
    private List<LocationsWithProjectKind> locationsByKind = new ArrayList<>();

    public ProjectChildLocations projectId(CwmsId projectId) {
        this.projectId = projectId;
        return this;
    }

    public CwmsId getProjectId() {
        return projectId;
    }

    public void setProjectId(CwmsId projectId) {
        this.projectId = projectId;
    }

    public ProjectChildLocations locationsByKind(List<LocationsWithProjectKind> locationsByKind) {
        this.locationsByKind = locationsByKind;
        return this;
    }

    public ProjectChildLocations addLocationsByKindItem(LocationsWithProjectKind locationsByKindItem) {
        if (this.locationsByKind == null) {
            this.locationsByKind = new ArrayList<>();
        }
        this.locationsByKind.add(locationsByKindItem);
        return this;
    }

    public List<LocationsWithProjectKind> getLocationsByKind() {
        return locationsByKind;
    }

    public void setLocationsByKind(List<LocationsWithProjectKind> locationsByKind) {
        this.locationsByKind = locationsByKind;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProjectChildLocations projectChildLocations = (ProjectChildLocations) o;
        return Objects.equals(this.projectId, projectChildLocations.projectId)
            && Objects.equals(this.locationsByKind, projectChildLocations.locationsByKind)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, locationsByKind);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ProjectChildLocations {\n");

        sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
        sb.append("    locationsByKind: ").append(toIndentedString(locationsByKind)).append("\n");
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
