package mil.army.usace.hec.cwms.radar.client.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * IndependentRoundingSpec
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-06-30T22:13:10.613Z[GMT]")
public class IndependentRoundingSpec
{
	@JsonProperty("position")
	private Integer position = null;

	@JsonProperty("value")
	private String value = null;

	public IndependentRoundingSpec position(Integer position)
	{
		this.position = position;
		return this;
	}

	/**
	 * Get position
	 *
	 * @return position
	 **/


	public Integer getPosition()
	{
		return position;
	}

	public void setPosition(Integer position)
	{
		this.position = position;
	}

	public IndependentRoundingSpec value(String value)
	{
		this.value = value;
		return this;
	}

	/**
	 * Get value
	 *
	 * @return value
	 **/


	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}


	@Override
	public boolean equals(java.lang.Object o)
	{
		if(this == o)
		{
			return true;
		}
		if(o == null || getClass() != o.getClass())
		{
			return false;
		}
		IndependentRoundingSpec independentRoundingSpec = (IndependentRoundingSpec) o;
		return Objects.equals(this.position, independentRoundingSpec.position) && Objects.equals(this.value,
				independentRoundingSpec.value);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(position, value);
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("class IndependentRoundingSpec {\n");

		sb.append("    position: ").append(toIndentedString(position)).append("\n");
		sb.append("    value: ").append(toIndentedString(value)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o)
	{
		if(o == null)
		{
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
