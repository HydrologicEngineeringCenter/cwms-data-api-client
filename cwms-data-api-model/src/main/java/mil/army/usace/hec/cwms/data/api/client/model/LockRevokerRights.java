package mil.army.usace.hec.cwms.data.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;


/**
 * LockRevokerRights
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-07-10T09:39:47.344849700-07:00[America/Los_Angeles]")
public class LockRevokerRights {

    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("project-id")
    private String projectId = null;

    @JsonProperty("application-id")
    private String applicationId = null;

    @JsonProperty("user-id")
    private String userId = null;

    public LockRevokerRights officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public LockRevokerRights projectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public LockRevokerRights applicationId(String applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public LockRevokerRights userId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LockRevokerRights lockRevokerRights = (LockRevokerRights) o;
        return this.officeId == null || lockRevokerRights.officeId == null ? Objects.equals(this.officeId, lockRevokerRights.officeId) : this.officeId.equalsIgnoreCase(lockRevokerRights.officeId)
                && this.projectId == null || lockRevokerRights.projectId == null ? Objects.equals(this.projectId, lockRevokerRights.projectId) : this.projectId.equalsIgnoreCase(lockRevokerRights.projectId)
                && this.applicationId == null || lockRevokerRights.applicationId == null ? Objects.equals(this.applicationId, lockRevokerRights.applicationId) : this.applicationId.equalsIgnoreCase(lockRevokerRights.applicationId)
                && this.userId == null || lockRevokerRights.userId == null ? Objects.equals(this.userId, lockRevokerRights.userId) : this.userId.equalsIgnoreCase(lockRevokerRights.userId)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeId == null ? 0 : officeId.toLowerCase(),
                projectId == null ? 0 : projectId.toLowerCase(),
                applicationId == null ? 0 : applicationId.toLowerCase(),
                userId == null ? 0 : userId.toLowerCase());
    }

    @Override
    public String toString() {

        return "class LockRevokerRights {\n"
                + "    officeId: " + toIndentedString(officeId) + "\n"
                + "    projectId: " + toIndentedString(projectId) + "\n"
                + "    applicationId: " + toIndentedString(applicationId) + "\n"
                + "    userId: " + toIndentedString(userId) + "\n"
                + "}";
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
