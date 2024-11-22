package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * LockLocationLevelRef
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
        date = "2024-11-08T14:48:35.102974200-08:00[America/Los_Angeles]")
public final class LockLocationLevelRef {

    @JsonProperty("level-link")
    private String levelLink = null;

    @JsonProperty("level-value")
    private Double levelValue = null;

    public LockLocationLevelRef levelLink(String levelLink) {
        this.levelLink = levelLink;
        return this;
    }

    public String getLevelLink() {
        return levelLink;
    }

    public void setLevelLink(String levelLink) {
        this.levelLink = levelLink;
    }

    public LockLocationLevelRef levelValue(Double levelValue) {
        this.levelValue = levelValue;
        return this;
    }

    public Double getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(Double levelValue) {
        this.levelValue = levelValue;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LockLocationLevelRef lockLocationLevelRef = (LockLocationLevelRef) o;
        return this.levelLink == null || lockLocationLevelRef.levelLink == null
                ? Objects.equals(this.levelLink, lockLocationLevelRef.levelLink)
                : this.levelLink.equalsIgnoreCase(lockLocationLevelRef.levelLink)
                && Objects.equals(this.levelValue, lockLocationLevelRef.levelValue)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(levelLink == null ? 0 : levelLink.toLowerCase(), levelValue);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LockLocationLevelRef {\n");

        sb.append("    levelLink: ").append(toIndentedString(levelLink)).append("\n");
        sb.append("    levelValue: ").append(toIndentedString(levelValue)).append("\n");
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
