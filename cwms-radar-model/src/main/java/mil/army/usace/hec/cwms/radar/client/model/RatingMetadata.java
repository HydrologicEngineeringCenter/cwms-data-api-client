package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;

/**
 * RatingMetadata
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-10-04T12:33:40.401-07:00[America/Los_Angeles]")
public class RatingMetadata {
    @JsonProperty("rating-spec")
    private RatingSpec ratingSpec = null;

    @JsonProperty("ratings")
    @Valid
    private List<AbstractRatingMetadata> ratings = new ArrayList<>();

    public RatingMetadata ratingSpec(RatingSpec ratingSpec) {
        this.ratingSpec = ratingSpec;
        return this;
    }

    /**
     * Get ratingSpec
     *
     * @return ratingSpec
     **/

    @Valid
    public RatingSpec getRatingSpec() {
        return ratingSpec;
    }

    public void setRatingSpec(RatingSpec ratingSpec) {
        this.ratingSpec = ratingSpec;
    }

    public RatingMetadata ratings(List<AbstractRatingMetadata> ratings) {
        this.ratings = ratings;
        return this;
    }

    public RatingMetadata addRatingsItem(AbstractRatingMetadata ratingsItem) {
        if (this.ratings == null) {
            this.ratings = new ArrayList<AbstractRatingMetadata>();
        }
        this.ratings.add(ratingsItem);
        return this;
    }

    /**
     * Get ratings
     *
     * @return ratings
     **/
    @Valid
    public List<AbstractRatingMetadata> getRatings() {
        return ratings;
    }

    public void setRatings(List<AbstractRatingMetadata> ratings) {
        this.ratings = ratings;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RatingMetadata ratingMetadata = (RatingMetadata) o;
        return Objects.equals(this.ratingSpec, ratingMetadata.ratingSpec)
            && Objects.equals(this.ratings, ratingMetadata.ratings)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratingSpec, ratings);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RatingMetadata {\n");

        sb.append("    ratingSpec: ").append(toIndentedString(ratingSpec)).append("\n");
        sb.append("    ratings: ").append(toIndentedString(ratings)).append("\n");
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
