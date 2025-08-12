package mil.army.usace.hec.cwms.data.api.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * RatingEffectiveDatesMap
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-08-11T17:27:45.767435500-07:00[America/Los_Angeles]")
public class RatingEffectiveDatesMap {

    @JsonProperty("office-to-spec-dates")
    @Valid
    private Map<String, List<RatingSpecEffectiveDates>> officeToSpecDates = null;

    public RatingEffectiveDatesMap officeToSpecDates(Map<String, List<RatingSpecEffectiveDates>> officeToSpecDates) {
        this.officeToSpecDates = officeToSpecDates;
        return this;
    }

    public RatingEffectiveDatesMap putOfficeToSpecDatesItem(String key, List<RatingSpecEffectiveDates> officeToSpecDatesItem) {
            if (this.officeToSpecDates == null) {
            this.officeToSpecDates = new HashMap<>();
            }
        this.officeToSpecDates.put(key, officeToSpecDatesItem);
        return this;
    }

    public Map<String, List<RatingSpecEffectiveDates>> getOfficeToSpecDates() {
        return officeToSpecDates;
    }

    public void setOfficeToSpecDates(Map<String, List<RatingSpecEffectiveDates>> officeToSpecDates) {
        this.officeToSpecDates = officeToSpecDates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        RatingEffectiveDatesMap ratingEffectiveDatesMap = (RatingEffectiveDatesMap) o;
        return Objects.equals(this.officeToSpecDates, ratingEffectiveDatesMap.officeToSpecDates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeToSpecDates);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RatingEffectiveDatesMap {\n");
        
        sb.append("    officeToSpecDates: ").append(toIndentedString(officeToSpecDates)).append("\n");
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
