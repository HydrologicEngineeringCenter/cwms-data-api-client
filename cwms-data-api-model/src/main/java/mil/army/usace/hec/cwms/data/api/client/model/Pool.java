package mil.army.usace.hec.cwms.data.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * List of retrieved pools
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-05-03T12:34:59.760765100-07:00[America/Los_Angeles]")
public class Pool {

    @JsonProperty("pool-name")
    private PoolNameType poolName = null;

    @JsonProperty("project-id")
    private String projectId = null;

    @JsonProperty("bottom-level-id")
    private String bottomLevelId = null;

    @JsonProperty("top-level-id")
    private String topLevelId = null;

    @JsonProperty("implicit")
    private Boolean implicit = null;

    @JsonProperty("attribute")
    private BigDecimal attribute = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("clob-text")
    private String clobText = null;

    public Pool poolName(PoolNameType poolName) {
        this.poolName = poolName;
        return this;
    }

    public PoolNameType getPoolName() {
        return poolName;
    }

    public void setPoolName(PoolNameType poolName) {
        this.poolName = poolName;
    }

    public Pool projectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Pool bottomLevelId(String bottomLevelId) {
        this.bottomLevelId = bottomLevelId;
        return this;
    }

    public String getBottomLevelId() {
        return bottomLevelId;
    }

    public void setBottomLevelId(String bottomLevelId) {
        this.bottomLevelId = bottomLevelId;
    }

    public Pool topLevelId(String topLevelId) {
        this.topLevelId = topLevelId;
        return this;
    }

    public String getTopLevelId() {
        return topLevelId;
    }

    public void setTopLevelId(String topLevelId) {
        this.topLevelId = topLevelId;
    }

    public Pool implicit(Boolean implicit) {
        this.implicit = implicit;
        return this;
    }

    public Boolean isImplicit() {
        return implicit;
    }

    public void setImplicit(Boolean implicit) {
        this.implicit = implicit;
    }

    public Pool attribute(BigDecimal attribute) {
        this.attribute = attribute;
        return this;
    }

    public BigDecimal getAttribute() {
        return attribute;
    }

    public void setAttribute(BigDecimal attribute) {
        this.attribute = attribute;
    }

    public Pool description(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pool clobText(String clobText) {
        this.clobText = clobText;
        return this;
    }

    public String getClobText() {
        return clobText;
    }

    public void setClobText(String clobText) {
        this.clobText = clobText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        Pool pool = (Pool) o;
        return Objects.equals(this.poolName, pool.poolName)
         && this.projectId == null || pool.projectId == null?Objects.equals(this.projectId, pool.projectId):this.projectId.equalsIgnoreCase(pool.projectId)
         && this.bottomLevelId == null || pool.bottomLevelId == null?Objects.equals(this.bottomLevelId, pool.bottomLevelId):this.bottomLevelId.equalsIgnoreCase(pool.bottomLevelId)
         && this.topLevelId == null || pool.topLevelId == null?Objects.equals(this.topLevelId, pool.topLevelId):this.topLevelId.equalsIgnoreCase(pool.topLevelId)
         && Objects.equals(this.implicit, pool.implicit)
         && Objects.equals(this.attribute, pool.attribute)
         && this.description == null || pool.description == null?Objects.equals(this.description, pool.description):this.description.equalsIgnoreCase(pool.description)
         && this.clobText == null || pool.clobText == null?Objects.equals(this.clobText, pool.clobText):this.clobText.equalsIgnoreCase(pool.clobText)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(poolName, projectId==null?0:projectId.toLowerCase(), bottomLevelId==null?0:bottomLevelId.toLowerCase(), topLevelId==null?0:topLevelId.toLowerCase(), implicit, attribute, description==null?0:description.toLowerCase(), clobText==null?0:clobText.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Pool {\n");
        
        sb.append("    pool-name: ").append(toIndentedString(poolName)).append("\n");
        sb.append("    project-id: ").append(toIndentedString(projectId)).append("\n");
        sb.append("    bottom-level-id: ").append(toIndentedString(bottomLevelId)).append("\n");
        sb.append("    top-level-id: ").append(toIndentedString(topLevelId)).append("\n");
        sb.append("    implicit: ").append(toIndentedString(implicit)).append("\n");
        sb.append("    attribute: ").append(toIndentedString(attribute)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    clob-text: ").append(toIndentedString(clobText)).append("\n");
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
