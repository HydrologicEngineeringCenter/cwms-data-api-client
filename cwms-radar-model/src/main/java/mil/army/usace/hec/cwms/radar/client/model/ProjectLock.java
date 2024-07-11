package mil.army.usace.hec.cwms.radar.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * ProjectLock
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-07-10T09:39:47.344849700-07:00[America/Los_Angeles]")
public class ProjectLock {

    @JsonProperty("office-id")
    private String officeId = null;

    @JsonProperty("project-id")
    private String projectId = null;

    @JsonProperty("application-id")
    private String applicationId = null;

    @JsonProperty("acquire-time")
    private Date acquireTime = null;

    @JsonProperty("session-user")
    private String sessionUser = null;

    @JsonProperty("os-user")
    private String osUser = null;

    @JsonProperty("session-program")
    private String sessionProgram = null;

    @JsonProperty("session-machine")
    private String sessionMachine = null;

    public ProjectLock officeId(String officeId) {
        this.officeId = officeId;
        return this;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public ProjectLock projectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public ProjectLock applicationId(String applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public ProjectLock acquireTime(Date acquireTime) {
        this.acquireTime = acquireTime;
        return this;
    }

    public Date getAcquireTime() {
        return acquireTime;
    }

    public void setAcquireTime(Date acquireTime) {
        this.acquireTime = acquireTime;
    }

    public ProjectLock sessionUser(String sessionUser) {
        this.sessionUser = sessionUser;
        return this;
    }

    public String getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(String sessionUser) {
        this.sessionUser = sessionUser;
    }

    public ProjectLock osUser(String osUser) {
        this.osUser = osUser;
        return this;
    }

    public String getOsUser() {
        return osUser;
    }

    public void setOsUser(String osUser) {
        this.osUser = osUser;
    }

    public ProjectLock sessionProgram(String sessionProgram) {
        this.sessionProgram = sessionProgram;
        return this;
    }

    public String getSessionProgram() {
        return sessionProgram;
    }

    public void setSessionProgram(String sessionProgram) {
        this.sessionProgram = sessionProgram;
    }

    public ProjectLock sessionMachine(String sessionMachine) {
        this.sessionMachine = sessionMachine;
        return this;
    }

    public String getSessionMachine() {
        return sessionMachine;
    }

    public void setSessionMachine(String sessionMachine) {
        this.sessionMachine = sessionMachine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        ProjectLock projectLock = (ProjectLock) o;
        return this.officeId == null || projectLock.officeId == null?Objects.equals(this.officeId, projectLock.officeId):this.officeId.equalsIgnoreCase(projectLock.officeId)
         && this.projectId == null || projectLock.projectId == null?Objects.equals(this.projectId, projectLock.projectId):this.projectId.equalsIgnoreCase(projectLock.projectId)
         && this.applicationId == null || projectLock.applicationId == null?Objects.equals(this.applicationId, projectLock.applicationId):this.applicationId.equalsIgnoreCase(projectLock.applicationId)
         && Objects.equals(this.acquireTime, projectLock.acquireTime)
         && this.sessionUser == null || projectLock.sessionUser == null?Objects.equals(this.sessionUser, projectLock.sessionUser):this.sessionUser.equalsIgnoreCase(projectLock.sessionUser)
         && this.osUser == null || projectLock.osUser == null?Objects.equals(this.osUser, projectLock.osUser):this.osUser.equalsIgnoreCase(projectLock.osUser)
         && this.sessionProgram == null || projectLock.sessionProgram == null?Objects.equals(this.sessionProgram, projectLock.sessionProgram):this.sessionProgram.equalsIgnoreCase(projectLock.sessionProgram)
         && this.sessionMachine == null || projectLock.sessionMachine == null?Objects.equals(this.sessionMachine, projectLock.sessionMachine):this.sessionMachine.equalsIgnoreCase(projectLock.sessionMachine)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeId==null?0:officeId.toLowerCase(), projectId==null?0:projectId.toLowerCase(), applicationId==null?0:applicationId.toLowerCase(), acquireTime, sessionUser==null?0:sessionUser.toLowerCase(), osUser==null?0:osUser.toLowerCase(), sessionProgram==null?0:sessionProgram.toLowerCase(), sessionMachine==null?0:sessionMachine.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ProjectLock {\n");
        
        sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
        sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(applicationId)).append("\n");
        sb.append("    acquireTime: ").append(toIndentedString(acquireTime)).append("\n");
        sb.append("    sessionUser: ").append(toIndentedString(sessionUser)).append("\n");
        sb.append("    osUser: ").append(toIndentedString(osUser)).append("\n");
        sb.append("    sessionProgram: ").append(toIndentedString(sessionProgram)).append("\n");
        sb.append("    sessionMachine: ").append(toIndentedString(sessionMachine)).append("\n");
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
