package mil.army.usace.hec.cwms.radar.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * ExpressionRating
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-10-04T12:33:40.401-07:00[America/Los_Angeles]")
public class ExpressionRating extends AbstractRatingMetadata {
    @JsonProperty("expression")
    private String expression = null;

    public ExpressionRating expression(String expression) {
        this.expression = expression;
        return this;
    }

    /**
     * Get expression
     *
     * @return expression
     **/

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExpressionRating expressionRating = (ExpressionRating) o;
        return this.expression == null || expressionRating.expression == null ? Objects.equals(this.expression, expressionRating.expression) :
            this.expression.equalsIgnoreCase(expressionRating.expression)
                &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression == null ? 0 : expression.toLowerCase(), super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExpressionRating {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    expression: ").append(toIndentedString(expression)).append("\n");
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
