package mil.army.usace.hec.cwms.data.api.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entity
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-12-17T14:19:46.612936700-08:00[America/Los_Angeles]")
public class Entity {

    @JsonProperty("id")
    private CwmsId id = null;

    @JsonProperty("parent-entity-id")
    private String parentEntityId = null;

    @JsonProperty("category-id")
    private String categoryId = null;

    @JsonProperty("long-name")
    private String longName = null;

    public Entity id(CwmsId id) {
        this.id = id;
        return this;
    }

    public CwmsId getId() {
        return id;
    }

    public void setId(CwmsId id) {
        this.id = id;
    }

    public Entity parentEntityId(String parentEntityId) {
        this.parentEntityId = parentEntityId;
        return this;
    }

    public String getParentEntityId() {
        return parentEntityId;
    }

    public void setParentEntityId(String parentEntityId) {
        this.parentEntityId = parentEntityId;
    }

    public Entity categoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Entity longName(String longName) {
        this.longName = longName;
        return this;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        Entity entity = (Entity) o;
        return Objects.equals(this.id, entity.id)
         && this.parentEntityId == null || entity.parentEntityId == null?Objects.equals(this.parentEntityId, entity.parentEntityId):this.parentEntityId.equalsIgnoreCase(entity.parentEntityId)
         && this.categoryId == null || entity.categoryId == null?Objects.equals(this.categoryId, entity.categoryId):this.categoryId.equalsIgnoreCase(entity.categoryId)
         && this.longName == null || entity.longName == null?Objects.equals(this.longName, entity.longName):this.longName.equalsIgnoreCase(entity.longName)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentEntityId==null?0:parentEntityId.toLowerCase(), categoryId==null?0:categoryId.toLowerCase(), longName==null?0:longName.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Entity {\n");
        
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    parentEntityId: ").append(toIndentedString(parentEntityId)).append("\n");
        sb.append("    categoryId: ").append(toIndentedString(categoryId)).append("\n");
        sb.append("    longName: ").append(toIndentedString(longName)).append("\n");
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
