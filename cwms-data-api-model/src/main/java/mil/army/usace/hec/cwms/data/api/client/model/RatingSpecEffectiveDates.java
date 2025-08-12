package mil.army.usace.hec.cwms.data.api.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * RatingSpecEffectiveDates
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-08-11T17:27:45.767435500-07:00[America/Los_Angeles]")
public class RatingSpecEffectiveDates {

    @JsonProperty("rating-spec-id")
    private String ratingSpecId = null;

    @JsonProperty("effective-dates")
    @Valid
    private List<Instant> effectiveDates = new ArrayList<>();

    public RatingSpecEffectiveDates ratingSpecId(String ratingSpecId) {
        this.ratingSpecId = ratingSpecId;
        return this;
    }

    public String getRatingSpecId() {
        return ratingSpecId;
    }

    public void setRatingSpecId(String ratingSpecId) {
        this.ratingSpecId = ratingSpecId;
    }

    public RatingSpecEffectiveDates effectiveDates(List<Instant> effectiveDates) {
        this.effectiveDates = effectiveDates;
        return this;
    }

    public RatingSpecEffectiveDates addEffectiveDatesItem(Instant effectiveDatesItem) {
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
        RatingSpecEffectiveDates ratingSpecEffectiveDates = (RatingSpecEffectiveDates) o;
        return this.ratingSpecId == null || ratingSpecEffectiveDates.ratingSpecId == null?Objects.equals(this.ratingSpecId, ratingSpecEffectiveDates.ratingSpecId):this.ratingSpecId.equalsIgnoreCase(ratingSpecEffectiveDates.ratingSpecId)
         && Objects.equals(this.effectiveDates, ratingSpecEffectiveDates.effectiveDates)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratingSpecId==null?0:ratingSpecId.toLowerCase(), effectiveDates);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RatingSpecEffectiveDates {\n");
        
        sb.append("    ratingSpecId: ").append(toIndentedString(ratingSpecId)).append("\n");
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
