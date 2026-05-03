package mil.army.usace.hec.cwms.data.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * List of retrieved location levels ids and effective dates
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-05-03T12:34:59.760765100-07:00[America/Los_Angeles]")
public class LocationLevelRef {

    @JsonProperty("location-level-id")
    private CwmsId locationLevelId = null;

    @JsonProperty("aliases")
    @Valid
    private List<String> aliases = new ArrayList<>();

    @JsonProperty("effective-dates")
    @Valid
    private List<Instant> effectiveDates = new ArrayList<>();

    public LocationLevelRef locationLevelId(CwmsId locationLevelId) {
        this.locationLevelId = locationLevelId;
        return this;
    }

    public CwmsId getLocationLevelId() {
        return locationLevelId;
    }

    public void setLocationLevelId(CwmsId locationLevelId) {
        this.locationLevelId = locationLevelId;
    }

    public LocationLevelRef aliases(List<String> aliases) {
        this.aliases = aliases;
        return this;
    }

    public LocationLevelRef addAliasesItem(String aliasesItem) {
            if (this.aliases == null) {
            this.aliases = new ArrayList<>();
            }
        this.aliases.add(aliasesItem);
        return this;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public LocationLevelRef effectiveDates(List<Instant> effectiveDates) {
        this.effectiveDates = effectiveDates;
        return this;
    }

    public LocationLevelRef addEffectiveDatesItem(Instant effectiveDatesItem) {
            if (this.effectiveDates == null) {
            this.effectiveDates = new ArrayList<>();
            }
        this.effectiveDates.add(effectiveDatesItem);
        return this;
    }

    public List<Instant> getEffectiveDates() {
        return effectiveDates;
    }

    public void setEffectiveDates(List<Instant> effectiveDates) {
        this.effectiveDates = effectiveDates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        LocationLevelRef locationLevelRef = (LocationLevelRef) o;
        return Objects.equals(this.locationLevelId, locationLevelRef.locationLevelId)
         && Objects.equals(this.aliases, locationLevelRef.aliases)
         && Objects.equals(this.effectiveDates, locationLevelRef.effectiveDates)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationLevelId, aliases, effectiveDates);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LocationLevelRef {\n");
        
        sb.append("    locationLevelId: ").append(toIndentedString(locationLevelId)).append("\n");
        sb.append("    aliases: ").append(toIndentedString(aliases)).append("\n");
        sb.append("    effectiveDates: ").append(toIndentedString(effectiveDates)).append("\n");
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
