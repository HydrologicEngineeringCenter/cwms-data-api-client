package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;


/**
 * Outlet
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-07-29T13:57:29.992917800-07:00[America/Los_Angeles]")
public class Outlet {

    @JsonProperty("project-id")
    private CwmsId projectId = null;

    @JsonProperty("location")
    private Location location = null;

    @JsonProperty("rating-group-id")
    private CwmsId ratingGroupId = null;

    @JsonProperty("rating-category-id")
    private CwmsId ratingCategoryId = null;

    public Outlet projectId(CwmsId projectId) {
        this.projectId = projectId;
        return this;
    }

    public CwmsId getProjectId() {
        return projectId;
    }

    public void setProjectId(CwmsId projectId) {
        this.projectId = projectId;
    }

    public Outlet location(Location location) {
        this.location = location;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Outlet ratingGroupId(CwmsId ratingGroupId) {
        this.ratingGroupId = ratingGroupId;
        return this;
    }

    public CwmsId getRatingGroupId() {
        return ratingGroupId;
    }

    public void setRatingGroupId(CwmsId ratingGroupId) {
        this.ratingGroupId = ratingGroupId;
    }

    public Outlet ratingCategoryId(CwmsId ratingCategoryId) {
        this.ratingCategoryId = ratingCategoryId;
        return this;
    }

    public CwmsId getRatingCategoryId() {
        return ratingCategoryId;
    }

    public void setRatingCategoryId(CwmsId ratingCategoryId) {
        this.ratingCategoryId = ratingCategoryId;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Outlet outlet = (Outlet) o;
        return Objects.equals(this.projectId, outlet.projectId)
                && Objects.equals(this.location, outlet.location)
                && Objects.equals(this.ratingGroupId, outlet.ratingGroupId)
                && Objects.equals(this.ratingCategoryId, outlet.ratingCategoryId)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, location, ratingGroupId, ratingCategoryId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Outlet {\n");

        sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
        sb.append("    location: ").append(toIndentedString(location)).append("\n");
        sb.append("    ratingGroupId: ").append(toIndentedString(ratingGroupId)).append("\n");
        sb.append("    ratingCategoryId: ").append(toIndentedString(ratingCategoryId)).append("\n");
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
