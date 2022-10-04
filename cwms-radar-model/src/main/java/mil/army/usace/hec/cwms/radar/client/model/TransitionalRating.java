package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;

/**
 * TransitionalRating
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-10-04T12:33:40.401-07:00[America/Los_Angeles]")
public class TransitionalRating extends AbstractRatingMetadata {
    @JsonProperty("source-ratings")
    @Valid
    private List<String> sourceRatings = new ArrayList<>();

    @JsonProperty("conditions")
    @Valid
    private List<String> conditions = new ArrayList<>();

    @JsonProperty("evaluations")
    @Valid
    private List<String> evaluations = new ArrayList<>();

    public TransitionalRating sourceRatings(List<String> sourceRatings) {
        this.sourceRatings = sourceRatings;
        return this;
    }

    public TransitionalRating addSourceRatingsItem(String sourceRatingsItem) {
        if (this.sourceRatings == null) {
            this.sourceRatings = new ArrayList<String>();
        }
        this.sourceRatings.add(sourceRatingsItem);
        return this;
    }

    /**
     * Get sourceRatings
     *
     * @return sourceRatings
     **/

    public List<String> getSourceRatings() {
        return sourceRatings;
    }

    public void setSourceRatings(List<String> sourceRatings) {
        this.sourceRatings = sourceRatings;
    }

    public TransitionalRating conditions(List<String> conditions) {
        this.conditions = conditions;
        return this;
    }

    public TransitionalRating addConditionsItem(String conditionsItem) {
        if (this.conditions == null) {
            this.conditions = new ArrayList<String>();
        }
        this.conditions.add(conditionsItem);
        return this;
    }

    /**
     * Get conditions
     *
     * @return conditions
     **/

    public List<String> getConditions() {
        return conditions;
    }

    public void setConditions(List<String> conditions) {
        this.conditions = conditions;
    }

    public TransitionalRating evaluations(List<String> evaluations) {
        this.evaluations = evaluations;
        return this;
    }

    public TransitionalRating addEvaluationsItem(String evaluationsItem) {
        if (this.evaluations == null) {
            this.evaluations = new ArrayList<String>();
        }
        this.evaluations.add(evaluationsItem);
        return this;
    }

    /**
     * Get evaluations
     *
     * @return evaluations
     **/

    public List<String> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<String> evaluations) {
        this.evaluations = evaluations;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TransitionalRating transitionalRating = (TransitionalRating) o;
        return Objects.equals(this.sourceRatings, transitionalRating.sourceRatings)
            && Objects.equals(this.conditions, transitionalRating.conditions)
            && Objects.equals(this.evaluations, transitionalRating.evaluations)
            &&
            super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceRatings, conditions, evaluations, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TransitionalRating {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    sourceRatings: ").append(toIndentedString(sourceRatings)).append("\n");
        sb.append("    conditions: ").append(toIndentedString(conditions)).append("\n");
        sb.append("    evaluations: ").append(toIndentedString(evaluations)).append("\n");
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
